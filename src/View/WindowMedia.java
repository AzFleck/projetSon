/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Database;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Quentin
 */
public class WindowMedia extends JFrame implements Observer{
	private JButton btn_play;
	private JButton btn_stop;
	private JButton btn_next;
	private JButton btn_previous;
	private JButton btn_repeat;
	private JButton btn_random;
	private DefaultTreeModel treeModelMovie;
	private DefaultTreeModel treeModelSong;
	private DefaultMutableTreeNode rootMovie;
	private DefaultMutableTreeNode rootSong;
	private JTree treeMovie;
	private JTree treeSong;
	private Controller controller;
	private ArrayList<String> actorslist;
	private ArrayList<String> directorslist;
	private ArrayList<String> sortslist;
	private ArrayList<String> artistslist;
	private ArrayList<String> styleslist;
	private ArrayList<String> albumslist;
	
	public WindowMedia(){
		controller = new Controller();
		this.setTitle("Mound Manager");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel down = new JPanel();
		JPanel middle = new JPanel();
		left.setLayout(new GridLayout(2,1));
		this.add(left,BorderLayout.WEST);
		this.add(right,BorderLayout.EAST);
		this.add(down,BorderLayout.SOUTH);
		this.add(middle,BorderLayout.CENTER);
		
		btn_play = new JButton("Play");
		btn_stop = new JButton("Stop");
		btn_next = new JButton("Next");
		btn_previous = new JButton("Previous");
		btn_repeat = new JButton("Repeat");
		btn_random = new JButton("Random");
		
		down.setLayout(new BoxLayout(down, BoxLayout.LINE_AXIS));
		down.add(btn_repeat);
		down.add(btn_random);
		down.add(btn_previous);
		down.add(btn_stop);
		down.add(btn_play);
		down.add(btn_next);
		this.createTreeMovie();
		left.add(new JScrollPane(treeMovie));
		this.createTreeSong();
		left.add(new JScrollPane(treeSong));
	}
	
	public void createTree(DefaultMutableTreeNode parent, ArrayList<String> children){
		for(int i = 0; i < children.size(); i++){
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(children.get(i));
			parent.add(node);
		}
	}
	
	public void createTreeMovie(){
		rootMovie = new DefaultMutableTreeNode("Movies");
		DefaultMutableTreeNode actors = new DefaultMutableTreeNode("Actor");
		DefaultMutableTreeNode directors = new DefaultMutableTreeNode("Director");
		DefaultMutableTreeNode sorts = new DefaultMutableTreeNode("Sort");
		actorslist = controller.actorsList();
		directorslist = controller.directorsList();
		sortslist = controller.sortsList();
		this.createTree(actors, actorslist);
		this.createTree(directors, directorslist);
		this.createTree(sorts, sortslist);
		rootMovie.add(actors);
		rootMovie.add(directors);
		rootMovie.add(sorts);
		treeModelMovie = new DefaultTreeModel(rootMovie);
		treeMovie = new JTree(treeModelMovie);
		
	}
	
	public void createTreeSong(){
		rootSong = new DefaultMutableTreeNode("Song");
		DefaultMutableTreeNode artists = new DefaultMutableTreeNode("Artist");
		DefaultMutableTreeNode styles = new DefaultMutableTreeNode("Style");
		DefaultMutableTreeNode album = new DefaultMutableTreeNode("Album");
		artistslist = controller.artistsList();
		styleslist = controller.stylesList();
		albumslist = controller.albumsList();
		this.createTree(artists, artistslist);
		this.createTree(styles, styleslist);
		this.createTree(album, albumslist);
		rootSong.add(artists);
		rootSong.add(styles);
		rootSong.add(album);
		treeModelSong = new DefaultTreeModel(rootSong);
		treeSong = new JTree(treeModelSong);
		
	}
	
	public static void main(String args[]){
<<<<<<< HEAD
		Database.createDatabase("BddSonVideo.sql");
		Database.createDatabase("Insert.sql");
=======
>>>>>>> 48e2640ad582b7368a9acdecdf59ea09f4504136
		new WindowMedia().setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
