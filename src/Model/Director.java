package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quentin
 */
public class Director extends Person{
		
	
	public static ArrayList<String> directorsList(){
		String req = "Select LastName, FirstName from person p"
				+ " join persontype pt on pt.idperson = p.idperson"
				+ " join \"type\" t on pt.idtype = t.idtype"
				+ " where t.libelle = \"Director\"";
		return Person.generateList(req);
	}
}
