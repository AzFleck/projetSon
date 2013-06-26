/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MonException;
import java.util.ArrayList;
import Model.Actor;
import Model.Album;
import Model.Artist;
import Model.Director;
import Model.FindFiles;
import Model.Media;
import Model.Movie;
import Model.Music;
import Model.PlayList;
import java.util.Observable;

/**
 *
 * @author Quentin
 */
public class Controller extends Observable {

	private ArrayList<Media> currentPlayList;
	private String parentSelectedItem;
	private String selectedItem;
	private ArrayList<Media> selection;

	public ArrayList<Media> getSelection() {
		return selection;
	}

	public void setSelection(ArrayList<Media> selection) {
		this.selection = selection;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getParentSelectedItem() {
		return parentSelectedItem;
	}

	public void setParentSelectedItem(String parentSelectedItem) {
		this.parentSelectedItem = parentSelectedItem;
	}

	public void fileChanged(String selectedItem, String parentItem) {
		this.setSelectedItem(selectedItem);
		this.setParentSelectedItem(parentItem);
		this.changeStatement();
		this.setChanged();
		this.notifyObservers();
	}

	public void changeStatement() {
		if (parentSelectedItem.equals("Actor")) {
			recoverActor();
		}
		else if (parentSelectedItem.equals("Director")) {
			recoverDirector();
		}
		else if (parentSelectedItem.equals("Sort")) {
			recoverSort();
		}
		else if (parentSelectedItem.equals("Artist")) {
			recoverArtist();
		}
		else if (parentSelectedItem.equals("Style")) {
			recoverStyle();
		}
		else if (parentSelectedItem.equals("Album")) {
			recoverAlbum();
		}
		else if (parentSelectedItem.equals("List of files")) {
			recoverListOfFiles();
		}
	}

	public void recoverActor() {
	}

	public void recoverDirector() {
	}

	public void recoverSort() {
	}

	public void recoverArtist() {
	}

	public void recoverStyle() {
	}

	public void recoverAlbum() {
		try {
			Media m = new Media();
			Album a = new Album();
			a.createAlbum(selectedItem);
			this.setSelection(m.getMediaInAlbum(a.getIdAlbum()));
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}

	public void recoverListOfFiles() {
		try {
			if (selectedItem.equals("Movies")) {
				Movie m = new Movie();
				this.setSelection(m.getAllMovie());
			}
			else if (selectedItem.equals("Song")) {
				Music m = new Music();
				this.setSelection(m.getAllSong());
			}
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}

	public ArrayList<String> actorsList() {
		Actor a = new Actor();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = a.actorsList();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return tab;
	}

	public ArrayList<String> sortsList() {
		Movie m = new Movie();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = m.sortsList();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return tab;
	}

	public ArrayList<String> directorsList() {
		Director d = new Director();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = d.directorsList();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return tab;
	}

	public ArrayList<String> artistsList() {
		Artist a = new Artist();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = a.artistsList();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return tab;
	}

	public ArrayList<String> stylesList() {
		Music m = new Music();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = m.stylesList();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return tab;
	}

	public ArrayList<String> albumsList() {
		Album a = new Album();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = a.albumsList();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return tab;
	}

	public void play() {
	}

	public void pause() {
	}

	public void stop() {
	}

	public void next() {
	}

	public void previous() {
	}

	public void random() {
	}

	public void repeat() {
	}

	public void chooseFolder() {
	}

	public ArrayList<Media> getCurrentPlayList() {
		return currentPlayList;
	}

	public void setCurrentPlayList(ArrayList<Media> currentPlayList) {
		this.currentPlayList = currentPlayList;
	}

	public void savePlaylist(String name) {
		if (this.getCurrentPlayList().size() > 0) {
			PlayList pl = new PlayList();
			pl.setMedias(this.getCurrentPlayList());
			pl.setName(name);
			try {
				pl.savePlaylist();
			}
			catch (MonException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void updatePlayList(String namePlayList) {
		PlayList p = new PlayList();
		try {
			p.createPlaylist(namePlayList);
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		this.setCurrentPlayList(p.getMedias());
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<PlayList> getAllPlaylist() {
		PlayList pl = new PlayList();
		ArrayList<PlayList> playlists = new ArrayList<PlayList>();
		try {
			playlists = pl.getAllPlaylist();
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return playlists;
	}

	public void getAllFiles(String pathOfDirectory) {
		FindFiles files = new FindFiles();
		try {
			files.getAllFiles(pathOfDirectory);
		}
		catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}
}
