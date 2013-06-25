/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import Model.Actor;
import Model.Album;
import Model.Artist;
import Model.Director;
import Model.Media;
import Model.Movie;
import Model.Music;
import Model.PlayList;
import java.io.File;
import java.util.Observable;

/**
 *
 * @author Quentin
 */
public class Controller extends Observable{
	
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
	
	public void fileChanged(String selectedItem, String parentItem){
		this.setSelectedItem(selectedItem);
		this.setParentSelectedItem(parentItem);
		this.changeStatement();
		this.setChanged();
		this.notifyObservers();
	}
	
	public void changeStatement(){
		if(parentSelectedItem.equals("Actor")){
			recoverActor();
		}
		else if(parentSelectedItem.equals("Director")){
			recoverDirector();
		}
		else if(parentSelectedItem.equals("Sort")){
			recoverSort();
		}
		else if(parentSelectedItem.equals("Artist")){
			recoverArtist();
		}
		else if(parentSelectedItem.equals("Style")){
			recoverStyle();
		}
		else if(parentSelectedItem.equals("Album")){
			recoverAlbum();
		}
		else if(parentSelectedItem.equals("List of files")){
			recoverListOfFiles();
		}
	}
	
	public void recoverActor(){
		
	}
	public void recoverDirector(){
		
	}	
	public void recoverSort(){
		
	}	
	public void recoverArtist(){
		
	}	
	public void recoverStyle(){
		
	}	
	public void recoverAlbum(){
		Media m = new Media();
		Album a = new Album();
		a.createAlbum(selectedItem);
		this.setSelection(m.getMediaInAlbum(a.getIdAlbum()));
	}	
	public void recoverListOfFiles(){
		Media m = new Media();
		if(selectedItem.equals("Movies")){
			this.setSelection(m.getAllMovie());
		}
		else if(selectedItem.equals("Song")){
			this.setSelection(m.getAllSong());
		}
	}
	
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

	public ArrayList<Media> getCurrentPlayList() {
		return currentPlayList;
	}

	public void setCurrentPlayList(ArrayList<Media> currentPlayList) {
		this.currentPlayList = currentPlayList;
	}
	
	public void savePlaylist(String name){
		if(this.getCurrentPlayList().size() > 0){
			PlayList pl = new PlayList();
			pl.savePlaylist(name, this.getCurrentPlayList());
		}
	}
	
	public void updatePlayList(String namePlayList)
	{
		Media m = new Media();
		PlayList p = new PlayList();
		p.createPlaylist(namePlayList);
		this.setCurrentPlayList(m.getPlaylistsElement(p.getId()));
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<PlayList> getAllPlaylist(){
		PlayList pl = new PlayList();
		return pl.getAllPlaylist();
	}
	
	public void getAllFiles(String pathOfDirectory) {
		String[] list;
		ArrayList<String> files = new ArrayList<String>();
		String pathOfAllFiles = pathOfDirectory + "\\";
		File f = new File(pathOfAllFiles);
		
		list = f.list();
		for (int i = 0; i < list.length; i++) {
			files.add(list[i]);
		}
	}
}
