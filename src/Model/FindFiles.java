/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.jna.NativeLibrary;
import com.xuggle.xuggler.IContainer;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Fabien
 */
public class FindFiles extends Observable implements Runnable {

	Integer lastId;
	String pathOfDirectory;
	Integer numberOfFile;
	Integer currentFile;
	IContainer container;

	public FindFiles(String pathOfDirectory) throws MonException {
		lastId = this.getMaxIdFile() + 1;
		numberOfFile = 0;
		currentFile = 0;
		this.pathOfDirectory = pathOfDirectory;
		container = IContainer.make();
	}

	public void findNumberOfFile() throws MonException {
		Media fileSelect = new Media();
		String pathOfAllFiles = pathOfDirectory + "\\";
		File f = new File(pathOfAllFiles);
		String[] list = f.list();
		for (int i = 0; i < list.length; i++) {
			File selectedFile = new File(pathOfAllFiles + list[i]);
			if (selectedFile.isDirectory()) {
				findNumberOfFileInSubDirectory(selectedFile.getAbsolutePath() + "\\", fileSelect);
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				fileSelect.setTitle(list[i].substring(0, list[i].length() - 4));
				if (!checkExistentFile(selectedFile.getAbsolutePath())) {
					numberOfFile++;
				}
			}
		}
	}

	public void findNumberOfFileInSubDirectory(String pathOfSubDirectory, Media fileSelect) throws MonException {
		String[] list = new File(pathOfSubDirectory).list();
		for (int i = 0; i < list.length; i++) {
			File selectedFile = new File(pathOfSubDirectory + "\\" + list[i]);
			if (selectedFile.isDirectory()) {
				findNumberOfFileInSubDirectory(selectedFile.getAbsolutePath() + "\\", fileSelect);
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				fileSelect.setTitle(list[i].substring(0, list[i].length() - 4));
				if (!checkExistentFile(selectedFile.getAbsolutePath())) {
					numberOfFile++;
				}
			}
		}
	}

	/**
	 *
	 * @param pathOfDirectory
	 * @throws MonException
	 */
	public void getAllFiles() throws MonException {
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
			this.createReq(f.getAbsolutePath(), fileSelect, list[i]);
		}
		this.setChanged();
		this.notifyObservers(currentFile);
	}

	public void getAllFilesInSubDirectory(String pathOfSubDirectory, Media fileSelect) throws MonException {

		String[] list = new File(pathOfSubDirectory).list();

		for (int i = 0; i < list.length; i++) {
			File selectedFile = new File(pathOfSubDirectory + "\\" + list[i]);
			if (selectedFile.isDirectory()) {
				getAllFilesInSubDirectory(selectedFile.getAbsolutePath() + "\\", fileSelect);
			}
			this.createReq(pathOfSubDirectory, fileSelect, list[i]);
		}
	}

	private void createReq(String pathOfSubDirectory, Media fileSelect, String file) throws MonException {
		if (file.endsWith(".avi") || file.endsWith(".mp4") || file.endsWith(".mp3") || file.endsWith(".mkv")) {
			fileSelect.setIdFile(lastId);
			fileSelect.setTitle(file.substring(0, file.length() - 4));
			fileSelect.setPath(pathOfSubDirectory + file);
			fileSelect.setLength(this.GetInfoContainer(fileSelect.getPath()));
			fileSelect.setDate("2000-01-01");
			fileSelect.setFind(true);
			if (!checkExistentFile(fileSelect.getPath())) {
				String reqFile = "INSERT INTO File VALUES ('" + lastId + "', '" + fileSelect.getTitle().replaceAll("'", "''") + "', '" + fileSelect.getDate()
						+ "', '" + fileSelect.getLength() + "', '" + fileSelect.getPath().replaceAll("'", "''") + "', '" + fileSelect.isFind() + "');";
				if (file.endsWith(".avi") || file.endsWith(".mp4") || file.endsWith(".mkv")) {
					reqFile += "INSERT INTO Movie VALUES ('" + fileSelect.getIdFile() + "', 'Pas de synopsis');";
				} else if (file.endsWith(".mp3")) {
					reqFile += "INSERT INTO Music VALUES ('" + fileSelect.getIdFile() + "', '0');";
				}
				this.insertFiles(reqFile);
				lastId++;
				currentFile++;
				boolean test = true;
				if (numberOfFile >= 100) {
					test = currentFile % (numberOfFile / 100) == 0 || currentFile == numberOfFile;
				}
				if (test) {
					this.setChanged();
					this.notifyObservers(currentFile);
				}
			}
		}
	}

	/**
	 * Insert un fichier dans la base de données
	 *
	 * @param media
	 * @throws MonException
	 */
	public void insertFiles(String req) throws MonException {
		try {
			Database.write(req);
		} catch (Exception e) {
			throw new MonException(e.getMessage());
		}
	}

	/**
	 * Récupère l'id max dans la table file
	 *
	 * @return
	 * @throws MonException
	 */
	public int getMaxIdFile() throws MonException {
		String req = "Select max(idFile) from file";
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

	public boolean checkExistentFile(String pathOfFile) throws MonException {
		boolean result = false;
		try {
			ResultSet rs = Database.read("SELECT count(idFile) FROM File WHERE Title = '" + pathOfFile.replaceAll("'", "''") + "'");
			rs.next();
			if (rs.getInt(1) != 0) {
				result = true;
			}
		} catch (Exception e) {
			throw new MonException(e.getMessage());
		}
		return result;
	}

	@Override
	public void run() {
		try {
			this.findNumberOfFile();
			this.setChanged();
			this.notifyObservers(numberOfFile);
			this.getAllFiles();
		} catch (MonException ex) {
			this.setChanged();
			this.notifyObservers(ex);
		}
	}

	public int getNumberOfFile() {
		return numberOfFile;
	}
	
	public String GetInfoContainer(String path) {
		String filename = path, time = "";
		// Create a Xuggler container object
		container.open(filename, IContainer.Type.READ, null);
		Long heure = (container.getDuration() / 1000000) / 3600;
		Long minutes = ((container.getDuration() / 1000000) / 60) - heure * 60;
		Long seconds = (container.getDuration() / 1000000) - heure * 3600 - minutes * 60;
		time = heure + ":" + minutes + ":" + seconds;
		return time;
	}
}
