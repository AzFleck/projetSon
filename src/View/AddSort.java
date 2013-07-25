/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Quentin
 */
public class AddSort extends Observable implements ActionListener {

	private JFrame frame;
	private JPanel pnl_sort;
	private Controller controller;
	private JComboBox<String> cbb_Sort;
	private JButton btn_add;

	public AddSort(Controller controller, JFrame parent, int parentType) {
		this.controller = controller;
		this.frame = new JFrame();
		this.frame.setLocationRelativeTo(parent);
		this.frame.setMinimumSize(new Dimension(100, 80));
		this.frame.setTitle("Add a sort to the movie");
		this.frame.setLayout(new BorderLayout());
		this.frame.setVisible(true);
		pnl_sort = new JPanel();
		pnl_sort.setLayout(new BorderLayout());
		cbb_Sort = new JComboBox<String>();
		ArrayList<String> sorts;
		if (parentType == 1) {
			sorts = controller.sortsList();
		} else {
			sorts = controller.stylesList();
		}
		for (int i = 0; i < sorts.size(); i++) {
			cbb_Sort.addItem(sorts.get(i));
		}
		pnl_sort.add(cbb_Sort, BorderLayout.CENTER);
		this.frame.add(pnl_sort, BorderLayout.CENTER);
		this.frame.setResizable(false);
		btn_add = new JButton("Add");
		btn_add.addActionListener(this);
		this.frame.add(btn_add, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_add){
			controller.addSortToFile(cbb_Sort.getSelectedItem().toString());
			frame.dispose();
		}
	}
}
