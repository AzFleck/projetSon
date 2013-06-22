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
			connection = Database.connect("BddSonVideo.db");
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
			connection = Database.connect("BddSonVideo.db");
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
	
	public static boolean createDatabase()
	{
		try {
			String request = "CREATE TABLE 'Movie' (\n" +
								"  'idMovie' INT NOT NULL ,\n" +
								"  'Title' VARCHAR(45) NOT NULL ,\n" +
								"  'Sort' VARCHAR(45) NULL ,\n" +
								"  'ReleaseDate' DATE NULL ,\n" +
								"  'Length' VARCHAR(45) NULL ,\n" +
								"  'Synopsis' VARCHAR(255) NULL ,\n" +
								"  'Path' VARCHAR(255) NULL ,\n" +
								"  'Find' TINYINT(1) NULL ,\n" +
								"  PRIMARY KEY ('idMovie') );\n" +
								"\n" +
								"CREATE  TABLE 'Person' (\n" +
								"  'idPerson' INT NOT NULL ,\n" +
								"  'LastName' VARCHAR(45) NOT NULL ,\n" +
								"  'FirstName' VARCHAR(45) NOT NULL ,\n" +
								"  'Nationality' VARCHAR(45) NULL ,\n" +
								"  'Type' INT NULL ,\n" +
								"  PRIMARY KEY ('idPerson') );\n" +
								"\n" +
								"CREATE  TABLE 'PersonMovie' (\n" +
								"  'Person_idPerson' INT NOT NULL ,\n" +
								"  'Movie_idMovie' INT NOT NULL ,\n" +
								"  PRIMARY KEY ('Person_idPerson', 'Movie_idMovie') \n" +								
								"  CONSTRAINT 'fk_Person_has_Movie_Person'\n" +
								"    FOREIGN KEY ('Person_idPerson' )\n" +
								"    REFERENCES 'Person' ('idPerson' )\n" +
								"    ON DELETE NO ACTION\n" +
								"    ON UPDATE NO ACTION,\n" +
								"  CONSTRAINT 'fk_Person_has_Movie_Movie1'\n" +
								"    FOREIGN KEY ('Movie_idMovie' )\n" +
								"    REFERENCES 'Movie' ('idMovie' ));\n" +
								"\n" +
								"CREATE  TABLE 'Type' (\n" +
								"  'idType' INT NOT NULL ,\n" +
								"  'Libelle' VARCHAR(45) NULL ,\n" +
								"  PRIMARY KEY ('idType') );\n" +
								"\n" +
								"CREATE  TABLE 'PersonType' (\n" +
								"  'Person_idPerson' INT NOT NULL ,\n" +
								"  'Type_idType' INT NOT NULL ,\n" +
								"  PRIMARY KEY ('Person_idPerson', 'Type_idType') ,\n" +
								"  CONSTRAINT 'fk_Person_has_Type_Person1'\n" +
								"    FOREIGN KEY ('Person_idPerson' )\n" +
								"    REFERENCES 'Person' ('idPerson' )\n" +
								"    ON DELETE NO ACTION\n" +
								"    ON UPDATE NO ACTION,\n" +
								"  CONSTRAINT 'fk_Person_has_Type_Type1'\n" +
								"    FOREIGN KEY ('Type_idType' )\n" +
								"    REFERENCES 'Type' ('idType' ))\n";
			Database.write(request);			
			//System.out.println(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
