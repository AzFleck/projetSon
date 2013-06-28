/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Quentin
 */
public class Album {
	private int idAlbum;
	private String libelle;
	private String releaseDate;
	private ArrayList<Media> musics;

	public ArrayList<Media> getMusics() {
		return musics;
	}

	public void setMusics(ArrayList<Media> musics) {
		this.musics = musics;
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	/**
	 * Renvoi le nom de tous les albums de la base
	 * @return Arraylist des noms des albums
	 * @throws MonException 
	 */
	public ArrayList<String> albumsList() throws MonException{
		String req = "Select libelle from album";
		ArrayList<String> album = new ArrayList<String>();
		try {
			ResultSet result = Database.read(req);
			while(result.next()){
				album.add(result.getString(1));
			}
		}
		catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return album;
	}
	
	/**
	 * Crée un album à partir de son nom
	 * @param name
	 * @throws MonException 
	 */
	public void createAlbum(String name) throws MonException{
		String req = "Select * from album where libelle = \"" + name + "\"";
		this.createAlbumReq(req);
	}
	
	/**
	 * Crée un album à partir de son id
	 * @param id
	 * @throws MonException 
	 */
	public void createAlbum(int id) throws MonException{
		String req = "Select * from album where idplaylist = " + id;
		this.createAlbumReq(req);
	}
	
	/**
	 * Utilitaire auquel on envoi une requête pour synthétiser le code
	 * @param requête qui renvoi l'id, le nom et la date de l'album désiré.
	 */
	private void createAlbumReq(String req) throws MonException{
		try {
			ResultSet result = Database.read(req);
			result.next();
			this.setIdAlbum(result.getInt(1));
			this.setLibelle(result.getString(2));
			this.setReleaseDate(result.getString(3));
			this.setMusics(this.getMediasByAlbum(this.getIdAlbum()));
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
	}
	
	/**
	 * Permet de récupérer les médias d'un album à l'aide de son id
	 * @param idalbum
	 * @return médias dans la playlist
	 */
	private ArrayList<Media> getMediasByAlbum(int idalbum) throws MonException {
		String req = "Select idmusic from music where idalbum = " + idalbum;
		ArrayList<Media> medias = new ArrayList<Media>();
		Media m = new Media();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				medias.add(m.getMediaById(result.getInt(1)));
			}
		}
		catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return medias;
	}
}
