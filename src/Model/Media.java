/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Quentin
 */
public class Media implements Cloneable{

	protected int idFile;
	protected String title; //titre
	protected String date; //date de sortie
	protected ArrayList<String> sort; //genre
	protected String path;
	protected boolean find;
	protected String length;
	protected ArrayList<Person> artists; // acteurs ou chanteurs

	public Media() {
		sort = new ArrayList<String>();
		artists = new ArrayList<Person>();
	}
	
	public ArrayList<Person> getArtists() {
		return artists;
	}

	public void setArtists(ArrayList<Person> artists) {
		this.artists = artists;
	}

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<String> getSort() {
		return sort;
	}

	public void setSort(ArrayList<String> sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isFind() {
		return find;
	}

	public void setFind(boolean find) {
		this.find = find;
	}

	/**
	 * Crée la liste des genres du type passé en param
	 *
	 * @param type : 1 pour film et 2 pour musiques
	 * @return liste des genres demandés
	 */
	protected ArrayList<String> genresList(int type) throws MonException {
		String req = "Select libelle from sort where \"type\" = " + type;
		ArrayList<String> sorts = new ArrayList<String>();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				sorts.add(result.getString(1));
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return sorts;
	}

	/**
	 * Permet d'attribuer un resultset dans un média (récupère les libelles des
	 * genres)
	 *
	 * @param ResultSet
	 */
	public void attributeMedia(ResultSet rs) throws MonException {
		try {
			this.setTitle(rs.getString("Title"));
			this.setDate(rs.getString("ReleaseDate"));
			this.setSort(this.getLibellesSortsOfFile(rs.getInt("idfile")));
			this.setPath(rs.getString("path"));
			this.setIdFile(rs.getInt("idfile"));
			this.setLength(rs.getString("length"));
			this.setFind(rs.getBoolean("find"));
			this.getAllArtists();
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
	}

	/**
	 * Renvoi les libelles de genres qui correspondent au film
	 *
	 * @param idfile l'id du média dont on veut les genres
	 * @return ArrayList des nom des genres
	 */
	public ArrayList<String> getLibellesSortsOfFile(int idfile) throws MonException {
		String req = "Select libelle from sort s "
				+ "join filesort fs on fs.idsort=s.idsort where idfile = " + idfile;
		ArrayList<String> genres = new ArrayList<String>();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				genres.add(result.getString(1));
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return genres;
	}

	/**
	 * Crée un média grâce à son id
	 *
	 * @param idmedia
	 * @return Le média crée
	 * @throws MonException
	 */
	public Media getMediaById(int idmedia) throws MonException {
		String req = "Select * from file where idfile = " + idmedia;
		Media med = new Media();
		try {
			ResultSet rs = Database.read(req);
			med.attributeMedia(rs);
		} catch (MonException ex) {
			throw ex;
		}
		return med;
	}

	/**
	 * Crée un média grâce à son nom
	 *
	 * @param idmedia
	 * @return Le média crée
	 * @throws MonException
	 */
	public Media getMediaByName(String nomMedia) throws MonException {
		String req = "Select * from file where title = '" + nomMedia.replaceAll("'", "''") + "'";
		Media med = new Media();
		try {
			ResultSet rs = Database.read(req);
			med.attributeMedia(rs);
		} catch (MonException ex) {
			throw ex;
		}
		return med;
	}

	public ArrayList<Media> getMediasByPerson(int idperson) throws MonException {
		String req = "Select idfile from personfile where idperson = " + idperson;
		return this.getMediasBySomething(req);
	}

	public ArrayList<Media> getMediasBySort(int idsort) throws MonException {
		String req = "Select idfile from filesort where idsort = " + idsort;
		return this.getMediasBySomething(req);
	}

	protected ArrayList<Media> getMediasBySomething(String req) throws MonException {
		ArrayList<Media> medias = new ArrayList<Media>();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				medias.add(this.getMediaById(result.getInt(1)));
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
		return medias;
	}

	public ArrayList<Media> getMediaInAlbum(int numAlbum) throws MonException {
		String req = "Select idmusic from music where idalbum = " + numAlbum;
		return this.getMediasBySomething(req);
	}
	
	@Override
	public Media clone(){
		Media med = new Media();
		med.setIdFile(this.getIdFile());
		med.setTitle(this.getTitle());
		med.setDate(this.getDate());
		med.setLength(this.getLength());
		med.setPath(this.getPath());
		med.setFind(this.isFind());
		med.setSort(this.getSort());
		return med;
	}
	
	private void getAllArtists() throws MonException{
		String req = "Select idperson from personfile where idtype != 2 and idfile = " + this.getIdFile();
		Person p = new Person();
		try {
			ResultSet result = Database.read(req);
			while (result.next()) {
				this.getArtists().add(p.getPersonById(result.getInt(1)));
			}
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
	}
	
	public void addSortToFile(String sort, String file) throws MonException{
		String reqIdSort = "Select idsort from sort where libelle = \""+ sort + "\"";
		String reqIdMedia = "Select idfile from file where title = \""+ file + "\"";
		int idSort = 0;
		int idMedia = 0;
		try {
			ResultSet result = Database.read(reqIdSort);
			while (result.next()) {
				idSort = result.getInt(1);
			}
			result = Database.read(reqIdMedia);
			while (result.next()) {
				idMedia = result.getInt(1);
			}
			String reqInsert = "Insert into filesort(idfile, idsort) values ("+ idMedia +", " + idSort +")";
			Database.write(reqInsert);
		} catch (SQLException ex) {
			throw new MonException(ex.getMessage());
		}
	}
}
