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
	
	/**
	 * Renvoi la list des acteurs ou des producteurs ou des artistes en fonction du paramètre
	 * @param type Actor / Director / Artist
	 * @return Liste des personnes correspondant à la demande
	 * @throws MonException 
	 */
	protected ArrayList<String> generateList(String type) throws MonException{
		String req = "Select LastName, FirstName from person p"
					 + " join persontype pt on pt.idperson = p.idperson"
					 + " join \"type\" t on pt.idtype = t.idtype"
					 + " where t.libelle = \""+type+"\"";
		ArrayList<String> persons = new ArrayList<String>();
		try {
			ResultSet result = Database.read(req);
			while(result.next()){
				String lastname = result.getString(1);//nom
				String firstname = result.getString(2);//prenom
				persons.add(firstname+" "+lastname);
			}
		}
		catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return persons;
	}
}
