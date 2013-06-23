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
	
	public ArrayList<String> albumsList(){
		String req = "Select libelle from album";
		ResultSet result = Database.read(req);
		ArrayList<String> album = new ArrayList<String>();
		try {
			while(result.next()){
				String libelle = result.getString(1);
				album.add(libelle);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			Database.disconnect();
		}
		return album;
	}
}
