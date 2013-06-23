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
public class Media {
	protected String title; //titre
	protected String date; //date de sortie
	protected ArrayList<String> sort; //genre

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
	
	
	protected ArrayList<String> genresList(int type){
		String req = "Select libelle from sort where \"type\" = "+ type;
		ResultSet result = Database.read(req);
		ArrayList<String> sorts = new ArrayList<String>();
		try {
			while(result.next()){
				String libelle = result.getString(1);
				sorts.add(libelle);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			Database.disconnect();
		}
		return sorts;
	}
}
