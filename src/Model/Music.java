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
public class Music extends Media{
	
	
	
	public ArrayList<String> stylesList(){
		return this.genresList(2);
	}
}
