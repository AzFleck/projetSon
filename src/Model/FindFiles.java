/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fabien
 */
public class FindFiles {

	Integer lastId;
	String reqFile;
	String reqTypeFile;

	public FindFiles() throws MonException {
		lastId = getMaxIdFile() + 1;
		reqFile = "";
		reqTypeFile = "";
	}

	/**
	 *
	 * @param pathOfDirectory
	 * @throws MonException
	 */
	public void getAllFiles(String pathOfDirectory) throws MonException {
		//Fichier en cours d'importation.
		Media fileSelect = new Media();

		//Liste de tous les fichiers à importer.
		String[] list;

		//Chemin du dossier selectionné + \ pour
		//récuperer les sous dossier
		String pathOfAllFiles = pathOfDirectory + "\\";

		//Création d'un File pour recuperer la liste
		//des sous dossier et fichiers contenu dans
		//le dossier sélectionner
		File f = new File(pathOfAllFiles);

		//Liste les dossier et fichiers
		list = f.list();


		for (int i = 0; i < list.length; i++) {
			File selectedFile = new File(pathOfAllFiles + list[i]);
			if (selectedFile.isDirectory()) {
				getAllFilesInSubDirectory(selectedFile.getAbsolutePath() + "\\", fileSelect);
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				fileSelect.setIdFile(lastId);
				fileSelect.setTitle(list[i].substring(0, list[i].length() - 4));
				fileSelect.setPath(pathOfAllFiles + fileSelect.getTitle());
				fileSelect.setLength("0");
				fileSelect.setDate("2000-01-01");
				fileSelect.setFind(true);
				if (!checkExistentFile(fileSelect.getTitle())) {
					this.reqFile += "INSERT INTO File VALUES ('" + lastId + "', '" + fileSelect.getTitle().replaceAll("'", "''") + "', '" + fileSelect.getDate()
							+ "', '" + fileSelect.getLength() + "', '" + fileSelect.getPath().replaceAll("'", "''") + "', '" + fileSelect.isFind() + "');";
					if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mkv")) {
						this.reqTypeFile += "INSERT INTO Movie VALUES ('" + fileSelect.getIdFile() + "', 'Pas de synopsis');";
					} else if (list[i].endsWith(".mp3")) {
						this.reqTypeFile += "INSERT INTO Music VALUES ('" + fileSelect.getIdFile() + "', '0');";
					}
					lastId++;
				}
			}
		}
		insertAllFiles();
	}

	public void getAllFilesInSubDirectory(String pathOfSubDirectory, Media fileSelect) throws MonException {

		String[] list = new File(pathOfSubDirectory).list();

		for (int i = 0; i < list.length; i++) {
			File selectedFile = new File(pathOfSubDirectory + "\\" + list[i]);
			if (selectedFile.isDirectory()) {
				getAllFilesInSubDirectory(selectedFile.getAbsolutePath() + "\\", fileSelect);
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				fileSelect.setIdFile(lastId);
				fileSelect.setTitle(list[i].substring(0, list[i].length() - 4));
				fileSelect.setPath(pathOfSubDirectory + list[i]);
				fileSelect.setLength("0");
				fileSelect.setDate("2000-01-01");
				fileSelect.setFind(true);
				if (!checkExistentFile(fileSelect.getTitle())) {
					this.reqFile += "INSERT INTO File VALUES ('" + lastId + "', '" + fileSelect.getTitle().replaceAll("'", "''") + "', '" + fileSelect.getDate()
							+ "', '" + fileSelect.getLength() + "', '" + fileSelect.getPath().replaceAll("'", "''") + "', '" + fileSelect.isFind() + "');";
					if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mkv")) {
						this.reqTypeFile += "INSERT INTO Movie VALUES ('" + fileSelect.getIdFile() + "', 'Pas de synopsis');";
					} else if (list[i].endsWith(".mp3")) {
						this.reqTypeFile += "INSERT INTO Music VALUES ('" + fileSelect.getIdFile() + "', '0');";
					}
					lastId++;
				}
			}
		}
	}

	/**
	 * *
	 * Insert un fichier dans la base de données
	 *
	 * @param media
	 * @throws MonException
	 */
	public void insertAllFiles() throws MonException {
		try {
			Database.write(this.reqFile + this.reqTypeFile);
		} catch (Exception e) {
			throw new MonException(e.getMessage());
		} finally {
			Database.disconnect();
		}
	}

	public int getMaxIdFile() throws MonException {
		String req = "Select max(idFile) from file";
		ResultSet result = Database.read(req);
		int maxId = -1;
		try {
			result.next();
			maxId = result.getInt(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			Database.disconnect();
		}
		return maxId;
	}

	public boolean checkExistentFile(String pathOfFile) throws MonException {
		boolean result = false;
		try {
			ResultSet rs = Database.read("SELECT count(idFile) FROM File WHERE Title = '" + pathOfFile.replaceAll("'", "''") + "'");
			rs.next();
			if (rs.getInt(1) != 0) {
				result = true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Database.disconnect();
		}
		return result;
	}
}
