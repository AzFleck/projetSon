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
	/**
	 * Renvoi la liste des réalisateurs présents dans la base
	 * @return Arraylist des réalisateurs
	 * @throws MonException 
	 */
	public ArrayList<String> directorsList() throws MonException{
		return this.generateList("Director");
	}
}
