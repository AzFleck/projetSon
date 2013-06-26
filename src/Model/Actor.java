
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
public class Actor extends Person{
	/**
	 * Renvoi la liste des acteurs présents dans la base
	 * @return Arraylist des acteurs
	 * @throws MonException 
	 */
	public ArrayList<String> actorsList() throws MonException{
		
		return this.generateList("Actor");
	}
}
