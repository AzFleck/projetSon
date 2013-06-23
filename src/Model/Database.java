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

	public static Connection connect(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:" + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static ResultSet read(String request) {
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

	public static void write(String request) {
		try {
			connection = Database.connect("BddSonVideo.db");
			Statement requete = connection.createStatement();
			requete.executeUpdate(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static boolean createDatabase() {
		try {
			String request = "CREATE  TABLE 'File' (\n"
					+ "  'idFile' INT NOT NULL ,\n"
					+ "  'Title' VARCHAR(45) NULL ,\n"
					+ "  'ReleaseDate' DATE NULL ,\n"
					+ "  'Length' VARCHAR(255) NULL ,\n"
					+ "  'Path' VARCHAR(255) NULL ,\n"
					+ "  'Find' TINYINT(1) NULL ,\n"
					+ "  PRIMARY KEY ('idFile') );\n"
					+ "\n"
					+ "CREATE  TABLE 'Movie' (\n"
					+ "  'idMovie' INT NOT NULL ,\n"
					+ "  'Synopsis' VARCHAR(255) NULL ,\n"
					+ "  PRIMARY KEY ('idMovie') ,\n"
					+ "  CONSTRAINT 'fk_Movie_File1'\n"
					+ "    FOREIGN KEY ('idMovie' )\n"
					+ "    REFERENCES 'File' ('idFile' ) );\n"
					+ "\n"
					+ "CREATE  TABLE 'Person' (\n"
					+ "  'idPerson' INT NOT NULL ,\n"
					+ "  'LastName' VARCHAR(45) NOT NULL ,\n"
					+ "  'FirstName' VARCHAR(45) NOT NULL ,\n"
					+ "  'Nationality' VARCHAR(45) NULL ,\n"
					+ "  PRIMARY KEY ('idPerson') );\n"
					+ "\n"
					+ "CREATE  TABLE 'Type' (\n"
					+ "  'idType' INT NOT NULL ,\n"
					+ "  'Libelle' VARCHAR(45) NOT NULL ,\n"
					+ "  PRIMARY KEY ('idType') );\n"
					+ "\n"
					+ "CREATE  TABLE 'PersonType' (\n"
					+ "  'idPerson' INT NOT NULL ,\n"
					+ "  'idType' INT NOT NULL ,\n"
					+ "  PRIMARY KEY ('idPerson', 'idType') ,\n"
					+ "  CONSTRAINT 'fk_Person_has_Type_Person1'\n"
					+ "    FOREIGN KEY ('idPerson' )\n"
					+ "    REFERENCES 'Person' ('idPerson' ),\n"
					+ "  CONSTRAINT 'fk_Person_has_Type_Type1'\n"
					+ "    FOREIGN KEY ('idType' )\n"
					+ "    REFERENCES 'Type' ('idType' ) );\n"
					+ "\n"
					+ "CREATE  TABLE 'Sort' (\n"
					+ "  'idSort' INT NOT NULL ,\n"
					+ "  'Libelle' VARCHAR(45) NOT NULL ,\n"
					+ "  PRIMARY KEY ('idSort') );\n"
					+ "\n"
					+ "CREATE  TABLE 'PersonFile' (\n"
					+ "  'idPerson' INT NOT NULL ,\n"
					+ "  'idFile' INT NOT NULL ,\n"
					+ "  PRIMARY KEY ('idPerson', 'idFile') ,\n"
					+ "  CONSTRAINT 'fk_Person_has_File_Person1'\n"
					+ "    FOREIGN KEY ('idPerson' )\n"
					+ "    REFERENCES 'Person' ('idPerson' ),\n"
					+ "  CONSTRAINT 'fk_Person_has_File_File1'\n"
					+ "    FOREIGN KEY ('idFile' )\n"
					+ "    REFERENCES 'File' ('idFile' ) );\n"
					+ "\n"
					+ "CREATE  TABLE 'FileSort' (\n"
					+ "  'idFile' INT NOT NULL ,\n"
					+ "  'idSort' INT NOT NULL ,\n"
					+ "  PRIMARY KEY ('idFile', 'idSort') ,\n"
					+ "  CONSTRAINT 'fk_File_has_Sort_File1'\n"
					+ "    FOREIGN KEY ('idFile' )\n"
					+ "    REFERENCES 'File' ('idFile' ),\n"
					+ "  CONSTRAINT 'fk_File_has_Sort_Sort1'\n"
					+ "    FOREIGN KEY ('idSort' )\n"
					+ "    REFERENCES 'Sort' ('idSort' ) );\n"
					+ "\n"
					+ "CREATE  TABLE 'Album' (\n"
					+ "  'idAlbum' INT NOT NULL ,\n"
					+ "  'Libelle' VARCHAR(45) NULL ,\n"
					+ "  'ReleaseDate' DATE NULL ,\n"
					+ "  PRIMARY KEY ('idAlbum') );\n"
					+ "\n"
					+ "CREATE  TABLE 'Music' (\n"
					+ "  'idMusic' INT NOT NULL ,\n"
					+ "  'idAlbum' INT NOT NULL ,\n"
					+ "  PRIMARY KEY ('idMusic', 'idAlbum') ,\n"
					+ "  CONSTRAINT 'fk_Music_File1'\n"
					+ "    FOREIGN KEY ('idMusic' )\n"
					+ "    REFERENCES 'File' ('idFile' ),\n"
					+ "  CONSTRAINT 'fk_Music_Album1'\n"
					+ "    FOREIGN KEY ('idAlbum' )\n"
					+ "    REFERENCES 'Album' ('idAlbum' ) );";
			Database.write(request);
			//System.out.println(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
