/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Fabien
 */
public class PlayList {

	private int id;
	private String name;
	private ArrayList<Media> medias;

	public PlayList() {
		this.id = 0;
		this.name = null;
		this.medias = null;
	}

	public ArrayList<Media> getMedias() {
		return medias;
	}

	public void setMedias(ArrayList<Media> medias) {
		this.medias = medias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Permet de créer un objet playlist telle qu'elle est dans la base à partir
	 * de son nom
	 *
	 * @param name playlist
	 */
	public void createPlaylist(String name) throws MonException {
		String req = "Select * from playlist where name = \"" + name + "\"";
		this.createPlaylistReq(req);
	}

	/**
	 * Permet de créer un objet playlist telle qu'elle est dans la base à partir
	 * de son id
	 *
	 * @param idplaylist
	 */
	public void createPlaylist(int id) throws MonException {
		String req = "Select * from playlist where idplaylist = " + id;
		this.createPlaylistReq(req);
	}

	/**
	 * Utilitaire auquel on envoi une requête qui obtient l'id puis le nom de la
	 * playlist
	 *
	 * @param requête qui renvoi l'id et le nom de la playlist désiré.
	 */
	private void createPlaylistReq(String req) throws MonException {
		try {
			ResultSet result = Database.read(req);
			result.next();
			this.setId(result.getInt(1));
			this.setName(result.getString(2));
			this.setMedias(this.getMediasByPlaylist(result.getInt(1)));
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
	}

	/**
	 * Permet de créer la liste de toutes les playlists présentes dans la base
	 * de données
	 *
	 * @return ArrayList de Playlist
	 */
	public HashMap<String, PlayList> getAllPlaylist() throws MonException {
		HashMap<String, PlayList> playlists = new HashMap<String, PlayList>();
		String req = "Select * from playlist";
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				PlayList pl = new PlayList();
				pl.setId(result.getInt(1));
				pl.setName(result.getString(2));
				pl.setMedias(this.getMediasByPlaylist(result.getInt(1)));
				playlists.put(pl.getName(), pl);
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return playlists;
	}

	/**
	 * Renvoi l'id maximum de la playlist
	 *
	 * @return id max dans la base
	 */
	public int getMaxIdPlayList() throws MonException {
		String req = "Select max(idplaylist) from playlist";
		int maxId = -1;
		try {
			ResultSet result = Database.read(req);
			result.next();
			maxId = result.getInt(1);
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return maxId;
	}

	/**
	 * Permet de sauvegarder la playlist dans la base, si l'id n'est pas précisé
	 * il l'ajoute en plus sinon elle remplace l'existante
	 */
	public void savePlaylist() throws MonException {
		if (this.getMedias() == null) {
			throw new MonException("Il faut renseigner les médias pour créer une playlist");
		} else if (this.getName() == null) {
			throw new MonException("Il faut renseigner le nom pour créer une playlist");
		} else {
			if (this.getId() == 0) {
				this.insertPlaylist();
			} else {
				if (this.playlistExist(this.getId())) {
					this.updatePlaylist();
				} else {
					this.setId(this.getMaxIdPlayList() + 1);// par soucis de cohérence
					this.insertPlaylist();
				}
			}
		}
	}

	/**
	 * Fais l'insertion dans la base d'une nouvelle playlist
	 */
	private void insertPlaylist() throws MonException {
		// Insertion dans la table Playlist
		int newIdPlaylist = this.getMaxIdPlayList() + 1;
		this.setId(newIdPlaylist);
		String reqPlaylist = "insert into playlist values(" + newIdPlaylist + ", '" + this.getName() + "');";
		try {
			Database.write(reqPlaylist);
		} catch (MonException e) {
			throw e;
		}
		this.insertLiaison(newIdPlaylist);
	}

	/**
	 * Fais l'insertion des différentes liaisons entre médias et playlist
	 *
	 * @param idplaylist
	 * @throws MonException
	 */
	private void insertLiaison(int idplaylist) throws MonException {
		String reqLiaison = "";
		for (int i = 0; i < this.getMedias().size(); i++) {
			reqLiaison += "insert into filePlaylist values ";
			int idmedia = this.getMedias().get(i).getIdFile();
			reqLiaison += "(" + idmedia + "," + idplaylist + "); ";
		}
		//Suppression de la dernière virgule
		reqLiaison = reqLiaison.substring(0, reqLiaison.length() - 1);
		try {
			Database.write(reqLiaison);
		} catch (MonException e) {
			throw e;
		}
	}

	/**
	 * Fais l'update dans la base d'une nouvelle playlist
	 */
	private void updatePlaylist() throws MonException {
		// Insertion dans la table Playlist
		String reqPlaylist = "update playlist set name = " + this.getName() + " where idplaylist = " + this.getId();
		try {
			Database.write(reqPlaylist);
		} catch (MonException e) {
			throw e;
		}
		// Insertion pour la liaison entre les médias et la playlist
		String reqLiaison = "delete from filePlaylist where idplaylist = " + this.getId();
		try {
			Database.write(reqLiaison);
		} catch (MonException e) {
			throw e;
		}
		this.insertLiaison(this.getId());
	}

	/**
	 * Renvoi true si la playlist existe déjà dans la base
	 *
	 * @param idplaylist
	 * @return true si elle existe, false si elle n'existe pas
	 * @throws MonException
	 */
	public boolean playlistExist(int idplaylist) throws MonException {
		boolean result = false;
		try {
			ResultSet rs = Database.read("SELECT count(*) FROM playlist WHERE idplaylist = '" + idplaylist + "'");
			rs.next();
			if (rs.getInt(1) != 0) {
				result = true;
			}
		} catch (Exception e) {
			throw new MonException(e.getMessage());
		}
		return result;
	}

	/**
	 * Permet de récupérer les médias d'une playlist à l'aide de son id
	 *
	 * @param idplaylist
	 * @return médias dans la playlist
	 */
	private ArrayList<Media> getMediasByPlaylist(int idplaylist) throws MonException {
		String req = "Select * from file f join fileplaylist fp on f.idfile = fp.idfile where fp.idplaylist = " + idplaylist;
		ArrayList<Media> medias = new ArrayList<Media>();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				Media m = new Media();
				m.setIdFile(result.getInt(1));
				m.setTitle(result.getString(2));
				m.setDate(result.getString(3));
				m.setLength(result.getString(4));
				m.setPath(result.getString(5));
				m.setFind(result.getBoolean(6));
				medias.add(m);
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return medias;
	}
}
