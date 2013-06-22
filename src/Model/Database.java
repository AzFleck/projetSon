/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fabien
 */
public class Database {       
    
	private static Connection connection;
	
    public static Connection connect(String dbName)
    {        
        try {
            Class.forName("org.sqlite.JDBC");
            return  DriverManager.getConnection("jdbc:sqlite:" + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }    
		return null;
    }
    
    public static ResultSet read(String request)
    {
		try {
			connection = Database.connect("BddSonVideo.bd3");
			Statement requete = connection.createStatement();
			ResultSet result = requete.executeQuery(request);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static void write(String request)
    {
        try {
			connection = Database.connect("BddSonVideo.bd3");
			Statement requete = connection.createStatement();
			requete.executeUpdate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void disconnect()
	{
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
