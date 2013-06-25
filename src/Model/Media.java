/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quentin
 */
public class Media {
	protected int idFile;
	protected String title; //titre
	protected String date; //date de sortie
	protected ArrayList<String> sort; //genre
	protected String path;
	protected boolean find;
	protected String length;

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<String> getSort() {
		return sort;
	}

	public void setSort(ArrayList<String> sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isFind() {
		return find;
	}

	public void setFind(boolean find) {
		this.find = find;
	}

	/**
	 * Crée la liste des genres du type passé en param
	 * @param type : 1 pour film et 2 pour musiques
	 * @return liste des genres demandés
	 */
	protected ArrayList<String> genresList(int type) {
		String req = "Select libelle from sort where \"type\" = " + type;
		ResultSet result = Database.read(req);
		ArrayList<String> sorts = new ArrayList<String>();
		try {
			while (result.next()) {
				String libelle = result.getString(1);
				sorts.add(libelle);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
		return sorts;
	}
	/**
	 * Permet d'attribuer un resultset dans un média (récupère les libelles des genres)
	 * @param ResultSet
	 */
	public void attributeMedia(ResultSet rs){
		try {
			this.setTitle(rs.getString("Title"));
			this.setDate(rs.getString("ReleaseDate"));
			this.setSort(this.getLibellesSortsOfFile(rs.getInt("idfile")));
			this.setPath(rs.getString("path"));
			this.setIdFile(rs.getInt("idfile"));
			this.setLength(rs.getString("length"));
			this.setFind(rs.getBoolean("find"));
		} catch (SQLException ex) {
			Logger.getLogger(Media.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Renvoi les libelles de genre qui correspondent au film
	 * @param idfile
	 * @return 
	 */
	public ArrayList<String> getLibellesSortsOfFile(int idfile){
		String req = "Select libelle from sort s "
				+ "join filesort fs on fs.idsort=s.idsort where idfile = " + idfile;
		ResultSet result = Database.read(req);
		ArrayList<String> genres = new ArrayList<String>();
		try {
			while (result.next()) {
				genres.add(result.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Media.class.getName()).log(Level.SEVERE, null, ex);
		}
		return genres;
	}

	/**
	 * Renvoi les id des éléments présents dans la playlist
	 * @param idplaylist
	 * @return 
	 */
	public ArrayList<Media> getPlaylistsElement(int idplaylist) {
		ArrayList<Media> medias = new ArrayList<Media>();
		String req = "Select idfile from fileplaylist where idplaylist = " + idplaylist;
		ResultSet result = Database.read(req);
		try {
			while (result.next()) {
				String req2 = "Select * from file where idfile = " + result.getInt(1);
				ResultSet rs = Database.read(req2);
				Media med = new Media();
				med.attributeMedia(rs);
				medias.add(med);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
		return medias;
	}
	
	public ArrayList<Media> getMediasByPerson(int idperson){
		ArrayList<Media> medias = new ArrayList<Media>();
		return medias;
	}
}
