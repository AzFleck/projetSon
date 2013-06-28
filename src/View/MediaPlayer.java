/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.sun.jna.NativeLibrary;
import javax.swing.JFrame;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


/**
 *
 * @author Quentin
 */
public class MediaPlayer extends JFrame{
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run() throws InterruptedException {
		ourMediaPlayer.getMediaPlayer().playMedia(mediaPath, "0");
	}
	public void pause(){
		this.getOurMediaPlayer().getMediaPlayer().setPause(true);
	}
	public void play(){
		this.getOurMediaPlayer().getMediaPlayer().setPause(false);
	}
}
