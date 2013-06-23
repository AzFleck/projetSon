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
		
	
	public ArrayList<String> directorsList(){
		return this.generateList("Director");
	}
}
