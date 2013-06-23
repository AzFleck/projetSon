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
public class Movie extends Media{
	private ArrayList<Director> director; //réalisateur
	private ArrayList<Actor> actors; //acteurs participants
	private String synopsis;

	
	public ArrayList<String> sortsList(){
		return this.genresList(1);
	}
}
