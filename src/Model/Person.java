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
	private int id;
	private String nom;
	private String prenom;
	private String nationality;
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		String req = "Select distinct LastName, FirstName from person p"
					 + " join personfile pf on pf.idperson = p.idperson"
					 + " join \"type\" t on pf.idtype = t.idtype"
					 + " where t.libelle = \""+type+"\" "
					 + "order by FirstName";
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
	
	public ArrayList<Media> getMediasByPerson(String lastname, String firstname, String type) throws MonException {
		String req = "Select * from file f "
				+ "join personfile pf on f.idfile = pf.idfile "
				+ "join person p on p.idperson = pf.idperson "
				+ "join type t on t.idtype = pf.idtype "
				+ "where p.lastname like '%" + lastname + "' "
				+ "and p.firstname like '" + firstname +"%' "
				+ "and t.libelle = '" + type + "'";
		ArrayList<Media> medias = new ArrayList<Media>();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				Media m = new Media();
				m.setIdFile(result.getInt(1));
				m.setTitle(result.getString(2));
				m.setDate(result.getString(3));
				m.setLength(result.getString(4));
				m.setPath(result.getString(5));
				m.setFind(result.getBoolean(6));
				medias.add(m);
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return medias;
	}
	
	public Person getPersonById(int idPerson) throws MonException{
		Person person = new Person();
		String req = "select * from Person where idperson = " + idPerson;
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				person.setId(result.getInt("idperson"));
				person.setNom(result.getString("lastname"));
				person.setPrenom(result.getString("firstname"));
				person.setNationality(result.getString("nationality"));
				person.setDate(result.getString("dateofbirth"));
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return person;
	}

	/**
	 * Renvoi l'id maximum des personnes
	 *
	 * @return id max dans la base
	 */
	public int getMaxIdPerson() throws MonException {
		String req = "Select max(idperson) from person";
		int maxId = -1;
		try {
			ResultSet result = Database.read(req);
			result.next();
			maxId = result.getInt(1);
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return maxId;
	}

	/**
	 * Fais l'insertion dans la base d'un nouvel album
	 */
	public void insertPerson(String nom, String prenom, String nationality, String date) throws MonException {
		this.setId(this.getMaxIdPerson() + 1);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNationality(nationality);
		this.setDate(date);
		String reqPers = "insert into person values(" + this.getId() + ", '" + this.getNom() + "', '" + this.getPrenom() + "', '" + this.getNationality()+ "', "+ this.getDate()+ ");";
		try {
			Database.write(reqPers);
		} catch (MonException e) {
			throw e;
		}
	}
}
