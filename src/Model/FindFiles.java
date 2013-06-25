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
	ArrayList<String> nameOfFiles;
	ArrayList<String> pathOfFiles;

	public FindFiles() {
		this.nameOfFiles = new ArrayList<String>();
		this.pathOfFiles = new ArrayList<String>();
	}
	
	public void getAllFiles(String pathOfDirectory) {
		String[] list;
		ArrayList<String> files = new ArrayList<String>();
		ArrayList<String> pathOfFile = new ArrayList<String>();
		String pathOfAllFiles = pathOfDirectory + "\\";
		File f = new File(pathOfAllFiles);
		
		list = f.list();
		for (int i = 0; i < list.length; i++) {
			File isDirectory = new File(pathOfAllFiles+list[i]);
			if (isDirectory.isDirectory()) {
				ArrayList<String> filesInSubDirectory = getAllFilesInSubDirectory(isDirectory.getAbsolutePath()+"\\");
				ArrayList<String> pathOfFileInSubDirectory = getAllPathsInSubDirectory(isDirectory.getAbsolutePath()+"\\");
				for(int y = 0; y < filesInSubDirectory.size(); y++) {
					files.add(filesInSubDirectory.get(y));
					pathOfFile.add(pathOfFileInSubDirectory.get(y));
				}
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				files.add(list[i]);
				pathOfFile.add(isDirectory.getAbsolutePath());
			}
		}
		setNameOfFiles(files);
		setPathOfFiles(pathOfFile);
		insertAllFiles();
	}
	
	public ArrayList<String> getAllFilesInSubDirectory(String pathOfSubDirectory) {
		String[] list = new File(pathOfSubDirectory).list();
		ArrayList<String> file = new ArrayList<String>();
		for(int i = 0; i < list.length; i++) {
			File isDirectory = new File(pathOfSubDirectory+list[i]);
			if (isDirectory.isDirectory()) {
				ArrayList<String> filesInSubDirectory = getAllFilesInSubDirectory(isDirectory.getAbsolutePath()+"\\");
				for(int y = 0; y < filesInSubDirectory.size(); y++) {
					file.add(filesInSubDirectory.get(y));
				}
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				file.add(list[i]);
			}
		}
		return file;
	}
	
	public ArrayList<String> getAllPathsInSubDirectory(String pathOfSubDirectory) {
		String[] list = new File(pathOfSubDirectory).list();
		ArrayList<String> pathOfFile = new ArrayList<String>();
		for(int i = 0; i < list.length; i++) {
			File isDirectory = new File(pathOfSubDirectory+list[i]);
			if (isDirectory.isDirectory()) {
				ArrayList<String> filesInSubDirectory = getAllFilesInSubDirectory(isDirectory.getAbsolutePath()+"/");
				for(int y = 0; y < filesInSubDirectory.size(); y++) {
					pathOfFile.add(isDirectory.getAbsolutePath() + "/" +filesInSubDirectory.get(y));
				}
			}
			if (list[i].endsWith(".avi") || list[i].endsWith(".mp4") || list[i].endsWith(".mp3") || list[i].endsWith(".mkv")) {
				pathOfFile.add(isDirectory.getAbsolutePath());
			}
		}
		return pathOfFile;
	}
	
	public void insertAllFiles()
	{
		try {
			int lastId = getMaxIdFile() + 1;
			for(int i = 0; i < nameOfFiles.size(); i++) {
				String req = "INSERT INTO File VALUES ('"+ lastId +"', '" + nameOfFiles.get(i) +"', '2000-01-01', '0', '" + pathOfFiles.get(i) + "', 0)";
				if(!checkExistentFile(nameOfFiles.get(i))) {
					Database.write(req);
					lastId++;
				}
				
			}
		} catch (Exception e) {
		}
		finally {
			Database.disconnect();
		}
	}
	
	public int getMaxIdFile(){
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
	
	public boolean checkExistentFile(String nameOfFile) {
		boolean result = false;
		try {
			ResultSet rs = Database.read("SELECT count(idFile) FROM File WHERE Title = '"+ nameOfFile +"'");
			rs.next();
			if (rs.getInt(1) != 0) {
				result = true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			Database.disconnect();
		}
		return result;
	}

	public ArrayList<String> getNameOfFiles() {
		return nameOfFiles;
	}

	public void setNameOfFiles(ArrayList<String> nameOfFiles) {
		this.nameOfFiles = nameOfFiles;
	}

	public ArrayList<String> getPathOfFiles() {
		return pathOfFiles;
	}

	public void setPathOfFiles(ArrayList<String> pathOfFiles) {
		this.pathOfFiles = pathOfFiles;
	}
	
}
