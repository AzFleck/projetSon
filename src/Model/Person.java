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
public class Person {
	private String nom;
	private String prenom;
	private String nationality;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public static ArrayList<String> generateList(String req){
		ResultSet result = Database.read(req);
		ArrayList<String> actors = new ArrayList<String>();
		try {
			while(result.next()){
				String nom = result.getString(1);
				String prenom = result.getString(2);
				actors.add(nom+" "+prenom);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			Database.disconnect();
		}
		return actors;
	}
}
