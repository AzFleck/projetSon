/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JList;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Quentin
 */
public class MediaPlayer extends Observable implements WindowListener, MouseListener {

	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	private String mediaPath = "";
	private JList<String> lb_list;
	private ButtonBar buttonBar;
	private JFrame frame;

	public EmbeddedMediaPlayerComponent getOurMediaPlayer() {
		return ourMediaPlayer;
	}

	public void setOurMediaPlayer(EmbeddedMediaPlayerComponent ourMediaPlayer) {
		this.ourMediaPlayer = ourMediaPlayer;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) throws InterruptedException {
		this.mediaPath = mediaPath;
	}

	public MediaPlayer(String mediaPath, ButtonBar buttonBar) {
		this.buttonBar = buttonBar;
		this.mediaPath = mediaPath;
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "BibliVlcJ\\");
		ourMediaPlayer = new EmbeddedMediaPlayerComponent();
		this.ourMediaPlayer.setPreferredSize(new Dimension(1200, 600));
		frame.setLayout(new BorderLayout());
		frame.addMouseListener(this);
		frame.add(ourMediaPlayer, BorderLayout.CENTER);
		frame.add(buttonBar, BorderLayout.SOUTH);
		frame.setSize(1200, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void run() throws InterruptedException {
		this.getOurMediaPlayer().getMediaPlayer().playMedia(mediaPath, "0");
	}

	public void pause() {
		this.getOurMediaPlayer().getMediaPlayer().setPause(true);
	}

	public void play() {
		this.getOurMediaPlayer().getMediaPlayer().setPause(false);
	}

	public void stop() {
		this.getOurMediaPlayer().getMediaPlayer().setPause(true);
		this.getOurMediaPlayer().getMediaPlayer().setTime(0);
	}

	public void goTo(long time) {
		this.getOurMediaPlayer().getMediaPlayer().setTime(0);
	}

	public void changeFile(String path) throws InterruptedException {
		this.ourMediaPlayer.getMediaPlayer().release();
		frame.remove(ourMediaPlayer);
		this.ourMediaPlayer = new EmbeddedMediaPlayerComponent();
		frame.add(ourMediaPlayer, BorderLayout.CENTER);
		this.mediaPath = path;
		frame.revalidate();
		frame.repaint();
	}

	public void fullScreen() {
		this.ourMediaPlayer.getMediaPlayer().setFullScreen(true);
	}

	public void setVolume(int newValue) {
		this.ourMediaPlayer.getMediaPlayer().setVolume(newValue);
	}
	
	public void setTime(long newValue) {
		long length = this.ourMediaPlayer.getMediaPlayer().getLength();
		long newValueInMs = (length * newValue) / 3600;
		this.ourMediaPlayer.getMediaPlayer().setTime(newValueInMs);
	}
	
	public void repeat() {
		ourMediaPlayer.getMediaPlayer().setRepeat(!ourMediaPlayer.getMediaPlayer().getRepeat());
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ourMediaPlayer.getMediaPlayer().release();
		ourMediaPlayer.release();
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == ourMediaPlayer) {
			if (e.getClickCount() == 2) {
				System.out.println("ici");
				this.fullScreen();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
