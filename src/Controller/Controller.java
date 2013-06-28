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
import View.ImportFile;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Quentin
 */
public class Controller extends Observable {

	private String currentPlayList; //nom de la playlist en cours
	private String parentSelectedItem; //arborescence à gauche
	private String selectedItem; //arborescence à gauche
	private ArrayList<Media> selection; // la sélection en cours pour le milieu
	private HashMap<String, PlayList> playlists; // toutes les playlists
	private ArrayList<Media> selectionPlaylist; //la playlist en cours

	public Controller() {
		this.currentPlayList = null;
		this.parentSelectedItem = null;
		this.selectedItem = null;
		this.selection = new ArrayList<Media>();
		this.playlists = new HashMap<String, PlayList>();
		this.selectionPlaylist = new ArrayList<Media>();
	}

	public ArrayList<Media> getSelectionPlaylist() {
		return selectionPlaylist;
	}

	public void setSelectionPlaylist(ArrayList<Media> selectionPlaylist) {
		this.selectionPlaylist = selectionPlaylist;
	}

	public HashMap<String, PlayList> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(HashMap<String, PlayList> playlists) {
		this.playlists = playlists;
	}

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
		this.notifyObservers("Middle");
	}

	public void changeStatement() {
		if (parentSelectedItem.equals("Actor")) {
			recoverActor();
		} else if (parentSelectedItem.equals("Director")) {
			recoverDirector();
		} else if (parentSelectedItem.equals("Sort")) {
			recoverSort();
		} else if (parentSelectedItem.equals("Artist")) {
			recoverArtist();
		} else if (parentSelectedItem.equals("Style")) {
			recoverStyle();
		} else if (parentSelectedItem.equals("Album")) {
			recoverAlbum();
		} else if (parentSelectedItem.equals("List of files")) {
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
		} catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}

	public void recoverListOfFiles() {
		try {
			if (selectedItem.equals("Movies")) {
				Movie m = new Movie();
				this.setSelection(m.getAllMovie());
			} else if (selectedItem.equals("Song")) {
				Music m = new Music();
				this.setSelection(m.getAllSong());
			}
		} catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}

	public ArrayList<String> actorsList() {
		Actor a = new Actor();
		ArrayList<String> tab = new ArrayList<String>();
		try {
			tab = a.actorsList();
		} catch (MonException ex) {
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
		} catch (MonException ex) {
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
		} catch (MonException ex) {
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
		} catch (MonException ex) {
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
		} catch (MonException ex) {
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
		} catch (MonException ex) {
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

	/**
	 * Renvoi l'objet playlist en cours
	 *
	 * @return Playlist
	 */
	public PlayList getCurrentPlayList() {
		return playlists.get(this.getCurrentPlayListName());
	}

	public String getCurrentPlayListName() {
		return this.currentPlayList;
	}

	public void setCurrentPlayList(String currentPlayList) {
		this.currentPlayList = currentPlayList;
	}

	/**
	 * Permet de sauvegarder la playlist courante ou de l'update
	 */
	public void savePlaylist(String name) {
		if (this.getSelectionPlaylist().size() > 0) {
			this.setCurrentPlayList(name);
			PlayList pl = new PlayList();
			pl.setName(name);
			ArrayList<Media> array = (ArrayList<Media>) this.getSelectionPlaylist().clone();
			pl.setMedias(array);
			try {
				pl.savePlaylist();
				this.getPlaylists().put(name, pl);
			} catch (MonException ex) {
				this.setChanged();
				this.notifyObservers(ex);
			}
		}
	}

	public void updatePlayList(String namePlayList) {
		PlayList p = new PlayList();
		try {
			p.createPlaylist(namePlayList);
		} catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		this.setCurrentPlayList(p.getName());
		this.setSelectionPlaylist(this.getCurrentPlayList().getMedias());
		this.setChanged();
		this.notifyObservers("Playlist");
	}

	public HashMap<String, PlayList> getAllPlaylist() {
		if (this.playlists.isEmpty()) {
			PlayList pl = new PlayList();
			try {
				this.setPlaylists(pl.getAllPlaylist());
			} catch (MonException ex) {
				this.setChanged();
				this.notifyObservers(ex);
			}
		}
		return playlists;
	}

	public void getAllFiles(String pathOfDirectory, Observer o) {
		try {
			FindFiles ff = new FindFiles(pathOfDirectory);
			ImportFile importFile = new ImportFile();
			Thread tImport = new Thread(importFile);
			ff.addObserver(o);
			ff.addObserver(importFile);
			tImport.start();
			Thread t = new Thread(ff);
			t.start();
		} catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}

	public boolean playlistExist(String name) {
		return this.getPlaylists().containsKey(name);
	}

	public Media createMediaByName(String name) {
		Media med = new Media();
		try {
			med = med.getMediaByName(name);
		} catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
		return med;
	}
}
