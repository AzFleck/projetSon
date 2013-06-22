/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model/Actor.java;

/**
 *
 * @author Quentin
 */
public class Controller implements ActionListener{
	
	public ArrayList<String> actorsList(){
		return Actor.actorsList();
	}
	public ArrayList<String> sortsList(){
		ArrayList<String> actors = new ArrayList<String>();
		actors.add("Gore");
		actors.add("Horreur");
		actors.add("Action");
		return actors;
	}
	public ArrayList<String> directorsList(){
		ArrayList<String> actors = new ArrayList<String>();
		actors.add("Dir1");
		actors.add("Dir2");
		actors.add("Dir3");
		return actors;
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
