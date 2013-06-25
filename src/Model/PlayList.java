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
	
	public int getMaxIdPlayList(){
		String req = "Select max(idplaylist) from playlist";
		ResultSet result = Database.read(req);
		int maxId = -1;
		try {
			result.next();
			maxId = result.getInt(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
		return maxId;
	}
	
	public void savePlaylist(String name, ArrayList<Media> medias){
		int newId = this.getMaxIdPlayList() + 1;
		String reqPlaylist = "insert into playlist values("+newId+", "+name+")";
		Database.write(reqPlaylist);
		String reqLiaison = "insert into filePlaylist values ";
		for(int i = 0; i < medias.size(); i++){
			int idmedia = medias.get(i).getIdFile();
			reqLiaison += "("+idmedia+","+newId+"),";
		}
		reqLiaison = reqLiaison.substring(0, reqLiaison.length()-1);
		Database.write(reqLiaison);
	}
}
