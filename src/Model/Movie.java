package Model;

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

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public ArrayList<Director> getDirector() {
		return director;
	}

	public void setDirector(ArrayList<Director> director) {
		this.director = director;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	/**
	 * Renvoi la liste des genres pour des films
	 * @return Arraylist de String
	 * @throws MonException 
	 */
	public ArrayList<String> sortsList() throws MonException{
		return this.genresList(1);
	}
	
	/**
	 * Récupère tous les films
	 * @return ArrayList de Media
	 * @throws MonException 
	 */
	public ArrayList<Media> getAllMovie() throws MonException{
		String req = "Select idmovie from movie";
		return this.getMediasBySomething(req);
	}
	
	/**
	 * Enregistre un média en tant que film
	 * @param media
	 * @return Objet Movie avec les champs de média renseignés
	 */
	public Movie mediaToMovie(Media media){
		Movie m = new Movie();
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
	 * Transforme un arraylist de média en arraylist de films
	 * @param medias arraylist de Media
	 * @return arraylist de Movie
	 */
	public ArrayList<Movie> arrayMediaToArrayMovie(ArrayList<Media> medias){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for(int i=0; i < medias.size(); i++){
			movies.add(this.mediaToMovie(medias.get(i)));
		}
		return movies;
	}
}
