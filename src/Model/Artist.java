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
		
	
	public ArrayList<String> artistsList(){
		return this.generateList("Artist");
	}
	
}
