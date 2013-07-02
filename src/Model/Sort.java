/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Quentin
 */
public class Sort {
	private int idSort;
	private String libelle;
	private int type;
	
	public int getIdSort() {
		return idSort;
	}

	public void setIdSort(int idSort) {
		this.idSort = idSort;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Renvoi l'id maximum des genres
	 *
	 * @return id max dans la base
	 */
	public int getMaxIdPlayList() throws MonException {
		String req = "Select max(idsort) from sort";
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
	 * Fais l'insertion dans la base d'un nouveau genre
	 */
	public void insertSort(String lib, int type) throws MonException {
		this.idSort = getMaxIdPlayList() + 1;
		this.libelle = lib;
		this.type = type;
		String reqSort = "insert into sort values(" + this.getIdSort() + ", '" + this.getLibelle() + "', "+ this.getType() + ");";
		try {
			Database.write(reqSort);
		} catch (MonException e) {
			throw e;
		}
	}
}
