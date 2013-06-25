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
 * @author Fabien
 */
public class PlayList {
	private int id;
	private String name;

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
	
	public void createPlaylist(String name){
		String req = "Select * from playlist where name = \"" + name + "\"";
		System.out.println(req);
		ResultSet result = Database.read(req);
		try {
			result.next();
			this.setId(result.getInt(1));
			this.setName(result.getString(2));
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
	}
	
	public void createPlaylist(int id){
		String req = "Select * from playlist where idplaylist = " + id;
		ResultSet result = Database.read(req);
		try {
			result.next();
			this.setId(result.getInt(1));
			this.setName(result.getString(2));
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
	}
	
	public ArrayList<PlayList> getAllPlaylist(){
		ArrayList<PlayList> playlists = new ArrayList<PlayList>();
		String req = "Select idplaylist from playlist";
		ResultSet result = Database.read(req);
		try {
			while(result.next()){
				PlayList pl = new PlayList();
				pl.createPlaylist(result.getInt(1));
				playlists.add(pl);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
		return playlists;
	}
}
