 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
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

	public static boolean createDatabase(String file) {
		try {
			BufferedReader sql;
			String requestSql = "";

			sql = new BufferedReader(new FileReader("BddMysql/"+file));
			while (sql.ready()) {
				requestSql += sql.readLine();				
			}
			Database.write(requestSql);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
}
