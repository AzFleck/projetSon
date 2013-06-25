/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Database;
import Model.Media;
import Model.PlayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Quentin
 */
public class WindowMedia extends JFrame implements Observer, ActionListener, ItemListener{
	//Elements menu
	private JMenuBar mb_menuBar;
	private JMenu m_file;
	private JMenuItem mi_chooseFolder;
	
	private JButton btn_play;
	private JButton btn_stop;
	private JButton btn_next;
	private JButton btn_previous;
	private JButton btn_repeat;
	private JButton btn_random;
	private JComboBox<String> cbb_playList;
	private JList<String> lb_list;
	
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode rootMovie;
	private DefaultMutableTreeNode rootSong;
	private JTree tree;
	private Controller controller;
	private ArrayList<String> actorslist;
	private ArrayList<String> directorslist;
	private ArrayList<String> sortslist;
	private ArrayList<String> artistslist;
	private ArrayList<String> styleslist;
	private ArrayList<String> albumslist;
	
	public WindowMedia(){
		controller = new Controller();
		controller.addObserver(this);
		JPanel total = new JPanel();
		this.add(total);
		this.setTitle("Mound Manager");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Gestion menubar
		mb_menuBar = new JMenuBar();
		m_file = new JMenu("Fichier");
		mi_chooseFolder = new JMenuItem("Choisir dossier");
		
		mb_menuBar.add(m_file);
		m_file.add(mi_chooseFolder);
		mi_chooseFolder.addActionListener(this);
		this.setJMenuBar(mb_menuBar);
		
		//Gestion des Jpanel
		total.setLayout(new BorderLayout());
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel down = new JPanel();
		JPanel middle = new JPanel();
		JPanel up = new JPanel();
		left.setLayout(new BorderLayout());
		total.add(left,BorderLayout.WEST);
		total.add(right,BorderLayout.EAST);
		total.add(down,BorderLayout.SOUTH);
		total.add(middle,BorderLayout.CENTER);
		total.add(up, BorderLayout.NORTH);
		
		//Panel right
		String data[] = {"test", "test2", "test3"};
		cbb_playList = new JComboBox<String>();
		this.generateCbbPlaylist();
		cbb_playList.addItemListener(this);
		lb_list = new JList<String>(data);
		
		right.setLayout(new BorderLayout());
		cbb_playList.setPreferredSize(new Dimension(200, 30));
		cbb_playList.setEditable(true);
		right.add(cbb_playList, BorderLayout.NORTH);
		right.add(lb_list, BorderLayout.CENTER);
		
		//Panel down
		btn_play = new JButton("Play");
		btn_stop = new JButton("Stop");
		btn_next = new JButton("Next");
		btn_previous = new JButton("Previous");
		btn_repeat = new JButton("Repeat");
		btn_random = new JButton("Random");
		
		btn_play.addActionListener(this);
		btn_stop.addActionListener(this);
		btn_next.addActionListener(this);
		btn_previous.addActionListener(this);
		btn_random.addActionListener(this);
		btn_repeat.addActionListener(this);
		
		down.add(btn_repeat);
		down.add(btn_random);
		down.add(btn_previous);
		down.add(btn_stop);
		down.add(btn_play);
		down.add(btn_next);
		
		//Panel left
		this.createTree();
		tree.setPreferredSize(new Dimension(200,0));
		left.add(new JScrollPane(tree),BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_play) {
			controller.play();
		}
		else if (e.getSource() == btn_next) {
			controller.next();
		}
		else if (e.getSource() == btn_previous) {
			controller.previous();
		}
		else if (e.getSource() == btn_random) {
			controller.random();
		}
		else if (e.getSource() == btn_repeat) {
			controller.repeat();
		}
		else if (e.getSource() == btn_stop) {
			controller.stop();
		}
	}
	
	public void createBranches(DefaultMutableTreeNode parent, ArrayList<String> children){
		for(int i = 0; i < children.size(); i++){
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(children.get(i));
			parent.add(node);
		}
	}
	
	public void createTree(){
		//Noeaud Files
		root = new DefaultMutableTreeNode("List of files");
		
		//Movie
		rootMovie = new DefaultMutableTreeNode("Movies");
		DefaultMutableTreeNode actors = new DefaultMutableTreeNode("Actor");
		DefaultMutableTreeNode directors = new DefaultMutableTreeNode("Director");
		DefaultMutableTreeNode sorts = new DefaultMutableTreeNode("Sort");
		actorslist = controller.actorsList();
		directorslist = controller.directorsList();
		sortslist = controller.sortsList();
		this.createBranches(actors, actorslist);
		this.createBranches(directors, directorslist);
		this.createBranches(sorts, sortslist);
		rootMovie.add(actors);
		rootMovie.add(directors);
		rootMovie.add(sorts);
		
		//Song
		rootSong = new DefaultMutableTreeNode("Song");
		DefaultMutableTreeNode artists = new DefaultMutableTreeNode("Artist");
		DefaultMutableTreeNode styles = new DefaultMutableTreeNode("Style");
		DefaultMutableTreeNode album = new DefaultMutableTreeNode("Album");
		artistslist = controller.artistsList();
		styleslist = controller.stylesList();
		albumslist = controller.albumsList();
		this.createBranches(artists, artistslist);
		this.createBranches(styles, styleslist);
		this.createBranches(album, albumslist);
		rootSong.add(artists);
		rootSong.add(styles);
		rootSong.add(album);
		root.add(rootMovie);
		root.add(rootSong);
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		DefaultMutableTreeNode currentNode = root.getNextNode();
		do {			
			if(currentNode.getLevel() == 1)
			{
				tree.expandPath(new TreePath(currentNode.getPath()));
			}
			currentNode = currentNode.getNextNode();
		} while (currentNode != null);
	}
	
	public static void main(String args[]){
		Database.createDatabase("BddSonVideo.sql");
		WindowMedia windowMedia = new WindowMedia();
		windowMedia.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.updatePlayList();
	}
	
	public void updatePlayList()
	{
		lb_list.removeAll();
		DefaultListModel<String> data = new DefaultListModel<String>();
		for (Iterator<Media> it = controller.getCurrentPlayList().iterator(); it.hasNext();) {
			data.addElement(it.next().getTitle());
		}
		lb_list.setModel(data);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cbb_playList) {
			controller.updatePlayList(cbb_playList.getSelectedItem().toString());
		}
	}
	
	public void generateCbbPlaylist(){
		cbb_playList.addItem("");
		ArrayList<PlayList> playlists = controller.getAllPlaylist();
		for(int i = 0; i < playlists.size() ; i++){
			cbb_playList.addItem(playlists.get(i).getName());
		}
	}
}
