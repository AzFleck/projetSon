/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Quentin
 */
public class Music extends Media{
	/**
	 * Renvoi la liste des styles de musiques présentes dans la base
	 * @return Arraylist des styles
	 * @throws MonException 
	 */
	public ArrayList<String> stylesList() throws MonException{
		return this.genresList(2);
	}
	
	/**
	 * Récupère toutes les musiques
	 * @return ArrayList de Media
	 * @throws MonException 
	 */
	public ArrayList<Media> getAllSong() throws MonException{
		String req = "Select idmusic from music";
		return this.getMediasBySomething(req);
	}
	
	/**
	 * Enregistre un média en tant que musique
	 * @param media
	 * @return Objet music avec les champs de média renseignés
	 */
	public Music mediaToMusic(Media media){
		Music m = new Music();
		m.setIdFile(media.getIdFile());
		m.setTitle(media.getTitle());
		m.setDate(media.getDate());
		m.setSort(media.getSort());
		m.setPath(media.getPath());
		m.setFind(media.isFind());
		m.setLength(media.getLength());
		return m;
	}
	
	/**
	 * Transforme un arraylist de média en arraylist de musiques
	 * @param medias arraylist de Media
	 * @return arraylist de music
	 */
	public ArrayList<Music> arrayMediaToArrayMusic(ArrayList<Media> medias){
		ArrayList<Music> musics = new ArrayList<Music>();
		for(int i=0; i < medias.size(); i++){
			musics.add(this.mediaToMusic(medias.get(i)));
		}
		return musics;
	}
}
