/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.sun.jna.NativeLibrary;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


/**
 *
 * @author Quentin
 */
public class MediaPlayer extends JFrame implements WindowListener{
	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	private String mediaPath = "";

	public EmbeddedMediaPlayerComponent getOurMediaPlayer() {
		return ourMediaPlayer;
	}

	public void setOurMediaPlayer(EmbeddedMediaPlayerComponent ourMediaPlayer) {
		this.ourMediaPlayer = ourMediaPlayer;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public MediaPlayer(String mediaPath) {
		this.mediaPath = mediaPath;
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "BibliVlcJ\\");
		ourMediaPlayer = new EmbeddedMediaPlayerComponent();
		this.setContentPane(ourMediaPlayer);
		this.setSize(1200, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void run() throws InterruptedException {
		this.getOurMediaPlayer().getMediaPlayer().playMedia(mediaPath, "0");
	}
	public void pause(){
		this.getOurMediaPlayer().getMediaPlayer().setPause(true);
	}
	public void play(){
		this.getOurMediaPlayer().getMediaPlayer().setPause(false);
	}
	public void stop(){
		this.getOurMediaPlayer().getMediaPlayer().setPause(true);
		this.getOurMediaPlayer().getMediaPlayer().setTime(0);
	}
	public void goTo(long time){
		this.getOurMediaPlayer().getMediaPlayer().setTime(0);
	}
	public void changeFile(String path){
		this.setMediaPath(path);
		this.getOurMediaPlayer().getMediaPlayer().playMedia(mediaPath, "0");
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
}
