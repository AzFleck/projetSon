/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Fabien
 */
public class ImportFile extends JFrame implements Observer, Runnable {

	private JProgressBar progressBar;
	private JLabel labelFile;
	private boolean maxValueIsSet;

	public ImportFile() {
	}

	public void createProgressBar(int maxValue) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(200, 75));
		JPanel panel = new JPanel();
		panel.add(new JLabel("Import files in progress..."));
		progressBar = new JProgressBar(0, maxValue);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		panel.add(progressBar);
		labelFile = new JLabel();
		panel.add(labelFile);
		this.add(panel);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	public void setProgressBarValue(int newValue) {
		progressBar.setValue(newValue);
	}

	@Override
	public void update(Observable o, Object arg) {
		Integer i = (Integer) arg;
		if (!maxValueIsSet) {
			this.createProgressBar(i);
			this.maxValueIsSet = true;
		} else {
			if (i != progressBar.getMaximum()) {
				this.setProgressBarValue(i);
				this.labelFile.setText("File " + i + " of " + progressBar.getMaximum());
			}
			else {
				this.dispose();
			}
		}
	}

	@Override
	public void run() {
		this.maxValueIsSet = false;
	}
}
