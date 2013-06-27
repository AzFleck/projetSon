 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabien
 */
public class Database {

	private static Connection connection;

	public static Connection getConnection() throws MonException {
		try {
			if (connection instanceof Connection) {
				if (connection.isValid(1)) {
					return connection;
				} else {
					return null;
				}
			} else {
				Class.forName("org.sqlite.JDBC");
				return DriverManager.getConnection("jdbc:sqlite:BddSonVideo.db");
			}
		} catch (SQLException e) {
			throw new MonException(e.getMessage());
		} catch (ClassNotFoundException ex) {
			throw new MonException(ex.getMessage());
		}
	}

	public static ResultSet read(String request) throws MonException {
		try {
			Statement requete = Database.getConnection().createStatement();
			ResultSet result = requete.executeQuery(request);
			return result;
		} catch (Exception e) {
			throw new MonException(e.getMessage());
		}
	}

	public static void write(String request) throws MonException {
		try {
			Statement requete = Database.getConnection().createStatement();
			requete.executeUpdate(request);
		} catch (Exception e) {
			throw new MonException(e.getMessage());
		}
	}

	public static void disconnect() throws MonException {
		try {
			Database.getConnection().close();
			connection = null;
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
	}

	public static void createDatabase(String file) throws MonException {
		File f = new File("BddSonVideo.db");
		//methode pour tester l'existence
		if (!f.exists()) {
			try {
				BufferedReader sql;
				String requestSql = "";

				sql = new BufferedReader(new FileReader("BddMysql/" + file));
				while (sql.ready()) {
					requestSql += sql.readLine();
				}
				Database.write(requestSql);

				requestSql = "";

				sql = new BufferedReader(new FileReader("BddMysql/Insert.sql"));
				while (sql.ready()) {
					requestSql += sql.readLine();
				}
				Database.write(requestSql);
			} catch (Exception e) {
				throw new MonException(e.getMessage());
			} finally {
				Database.disconnect();
			}
		}
	}
}
