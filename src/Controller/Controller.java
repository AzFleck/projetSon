/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.Actor;
import Model.Album;
import Model.Artist;
import Model.Director;
import Model.Movie;
import Model.Music;

/**
 *
 * @author Quentin
 */
public class Controller implements ActionListener{
	
	public ArrayList<String> actorsList(){
		Actor a = new Actor();
		return a.actorsList();
	}
	public ArrayList<String> sortsList(){
		Movie m = new Movie();
		return m.sortsList();
	}
	public ArrayList<String> directorsList(){
		Director d = new Director();
		return d.directorsList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public ArrayList<String> artistsList() {
		Artist a = new Artist();
		return a.artistsList();
	}

	public ArrayList<String> stylesList() {
		Music m = new Music();
		return m.stylesList();
	}

	public ArrayList<String> albumsList() {
		Album a = new Album();
		return a.albumsList();
	}
	
	public void play()
	{
		
	}
	
	public void pause()
	{
		
	}
	
	public void stop()
	{
		
	}
	
	public void next()
	{
		
	}
	
	public void previous()
	{
		
	}
	
	public void random()
	{
		
	}
	
	public void repeat()
	{
		
	}
	
	public void chooseFolder()
	{
		
	}
}
