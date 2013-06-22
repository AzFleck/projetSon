
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quentin
 */
public class Actor extends Person{
	
	
	public static ArrayList<String> actorsList(){
		String req = "Select LastName, FirstName from person";
		ResultSet result = Database.read(req);
		ArrayList<String> actors = new ArrayList<String>();
		try {
			while(result.next()){
				String nom = result.getString(1);
				String prenom = result.getString(2);
				actors.add(nom+" "+prenom);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			Database.disconnect();
		}
		return actors;
	}
}
