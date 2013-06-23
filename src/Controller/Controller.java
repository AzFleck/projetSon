/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.Actor;
import Model.Director;
import Model.Movie;

/**
 *
 * @author Quentin
 */
public class Controller implements ActionListener{
	
	public ArrayList<String> actorsList(){
		return Actor.actorsList();
	}
	public ArrayList<String> sortsList(){
		return Movie.sortsList();
	}
	public ArrayList<String> directorsList(){
		return Director.directorsList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public ArrayList<String> artistsList() {
		ArrayList<String> artists = new ArrayList<String>();
		artists.add("Art1");
		artists.add("Art2");
		artists.add("Art3");
		return artists;
	}

	public ArrayList<String> stylesList() {
		ArrayList<String> styles = new ArrayList<String>();
		styles.add("Sty1");
		styles.add("Sty2");
		styles.add("Sty3");
		return styles;
	}

	public ArrayList<String> albumsList() {
		ArrayList<String> albums = new ArrayList<String>();
		albums.add("Alb1");
		albums.add("Alb2");
		albums.add("Alb3");
		return albums;
	}
	
}
