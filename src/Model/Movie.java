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
public class Movie {
	private String title; //titre
	private String date; //date de sortie
	private ArrayList<Director> director; //réalisateur
	private ArrayList<Actor> actors; //acteurs participants
	private ArrayList<String> sort; //genre
	private String synopsis;

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Director> getDirector() {
		return director;
	}

	public void setDirector(ArrayList<Director> director) {
		this.director = director;
	}

	public ArrayList<String> getSort() {
		return sort;
	}

	public void setSort(ArrayList<String> sort) {
		this.sort = sort;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Movie(String title, String date, ArrayList<Director> director, ArrayList<Actor> actors, ArrayList<String> sort, String synopsis) {
		this.title = title;
		this.date = date;
		this.director = director;
		this.actors = actors;
		this.sort = sort;
		this.synopsis = synopsis;
	}
}
