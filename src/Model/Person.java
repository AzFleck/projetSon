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
	
	protected ArrayList<String> generateList(String type){
		String req = "Select LastName, FirstName from person p"
					 + " join persontype pt on pt.idperson = p.idperson"
					 + " join \"type\" t on pt.idtype = t.idtype"
					 + " where t.libelle = \""+type+"\"";
		ResultSet result = Database.read(req);
		ArrayList<String> persons = new ArrayList<String>();
		try {
			while(result.next()){
				String nom = result.getString(1);
				String prenom = result.getString(2);
				persons.add(prenom+" "+nom);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			Database.disconnect();
		}
		return persons;
	}
}
