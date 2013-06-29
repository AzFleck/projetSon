/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fabien
 */
public class ButtonBar extends JPanel implements ActionListener, ChangeListener, Observer {

	private JButton btn_play;
	private JButton btn_stop;
	private JButton btn_next;
	private JButton btn_previous;
	private JButton btn_repeat;
	private JButton btn_random;
	private JButton btn_mute;
	private JSlider s_time;
	private JSlider s_volume;
	private Controller controller;
	private boolean playPause;
	private boolean isPlayed;
	private JList<String> lb_list;

	public ButtonBar(Controller controller, JList<String> lb_list) {
		this.controller = controller;
		this.lb_list = lb_list;
		btn_play = new JButton(new ImageIcon("resources/play.png"));
		btn_stop = new JButton(new ImageIcon("resources/stop.png"));
		btn_next = new JButton(new ImageIcon("resources/next.png"));
		btn_previous = new JButton(new ImageIcon("resources/previous.png"));
		btn_repeat = new JButton("Repeat");
		btn_random = new JButton("Random");
		btn_mute = new JButton("Mute");

		btn_play.addActionListener(this);
		btn_stop.addActionListener(this);
		btn_next.addActionListener(this);
		btn_previous.addActionListener(this);
		btn_random.addActionListener(this);
		btn_repeat.addActionListener(this);

		s_volume = new JSlider(0, 200);
		s_time = new JSlider(0, 3600);
		s_volume.setMaximumSize(new Dimension(90, 20));

		s_time.setValue(0);
		
		s_time.addChangeListener(this);
		s_volume.addChangeListener(this);

		JPanel down = new JPanel();
		down.setLayout(new BoxLayout(down, BoxLayout.LINE_AXIS));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		down.add(btn_repeat);
		down.add(btn_random);
		down.add(btn_previous);
		down.add(btn_stop);
		down.add(btn_play);
		down.add(btn_next);
		down.add(btn_mute);
		down.add(s_volume);
		this.add(s_time);
		this.add(down);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_play) {
			if (!isPlayed) {
				isPlayed = true;
				if (lb_list.getModel().getSize() != 0) {
					if (!lb_list.isSelectionEmpty()) {
						controller.play(lb_list.getSelectedValue(), this);
					} else {
						controller.play(lb_list.getModel().getElementAt(0), this);
					}
				}
			} else {
				if (playPause) {
					controller.playPause(playPause);
					playPause = false;
				} else {
					controller.playPause(playPause);
					playPause = true;
				}
			}
		} else if (e.getSource() == btn_next) {
			controller.next();
		} else if (e.getSource() == btn_previous) {
			controller.previous();
		} else if (e.getSource() == btn_random) {
			controller.random();
		} else if (e.getSource() == btn_repeat) {
			controller.repeat();
		} else if (e.getSource() == btn_stop) {
			controller.stop();
			isPlayed = false;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == s_time) {
			controller.setTime(s_time.getValue());
		} else if (e.getSource() == s_volume) {
			controller.setVolume(s_volume.getValue());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
