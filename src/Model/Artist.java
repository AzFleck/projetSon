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
public class Artist extends Person{
	/**
	 * Renvoi la liste des artistes présents dans la base
	 * @return Arraylist des artistes
	 * @throws MonException 
	 */
	public ArrayList<String> artistsList() throws MonException{
		return this.generateList("Artist");
	}
	
}
