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
	
	public void createAlbum(String name){
		String req = "Select * from album where libelle = \"" + name + "\"";
		ResultSet result = Database.read(req);
		try {
			result.next();
			this.setIdAlbum(result.getInt(1));
			this.setLibelle(result.getString(2));
			this.setReleaseDate(result.getString(3));
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
	}
	
	public void createAlbum(int id){
		String req = "Select * from album where idplaylist = " + id;
		ResultSet result = Database.read(req);
		try {
			result.next();
			this.setIdAlbum(result.getInt(1));
			this.setLibelle(result.getString(2));
			this.setReleaseDate(result.getString(3));
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
	}
}
