/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MonException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Quentin
 */
public class AddSomething extends Observable implements ActionListener{

	private JFrame frame;
	private int typeOfAdd;
	private JTextField[] text_fields;
	private JButton btn_add;
	private JPanel pnl_total;

	public AddSomething(int typeOfAdd, JFrame parent) throws MonException {
		this.typeOfAdd = typeOfAdd;
		int nb_text;
		this.frame = new JFrame();
		this.frame.setLocationRelativeTo(parent);
		if (this.typeOfAdd == 1) {
			this.frame.setTitle("Add a person");
			nb_text = 4;
		} else if (this.typeOfAdd == 2) {
			this.frame.setTitle("Add a sort");
			nb_text = 1;
		} else {
			throw new MonException("Erreur de paramètre pour l'ajout");
		}
		this.text_fields = new JTextField[nb_text];
		this.frame.setLayout(new BorderLayout());
		this.frame.setMinimumSize(new Dimension(300, 150));
		this.frame.setVisible(true);
		pnl_total = new JPanel();
		if (this.typeOfAdd == 1) {
			this.addPerson();
		} else {
			this.addSort();
		}
		this.frame.add(pnl_total, BorderLayout.CENTER);
		this.frame.setResizable(false);
		btn_add = new JButton("Add");
		btn_add.addActionListener(this);
		this.frame.add(btn_add, BorderLayout.SOUTH);
	}

	private void addPerson() {
		JLabel lbl_nom = new JLabel("Last name : ");
		text_fields[0] = new JTextField();
		JLabel lbl_prenom = new JLabel("First name : ");
		text_fields[1] = new JTextField();
		JLabel lbl_nat = new JLabel("Nationality : ");
		text_fields[2] = new JTextField();
		JLabel lbl_date = new JLabel("Date of birth : ");
		text_fields[3] = new JTextField("YYYY-MM-DD");
		pnl_total.setLayout(new GridLayout(4, 2));
		pnl_total.add(lbl_nom);
		pnl_total.add(text_fields[0]);
		pnl_total.add(lbl_prenom);
		pnl_total.add(text_fields[1]);
		pnl_total.add(lbl_nat);
		pnl_total.add(text_fields[2]);
		pnl_total.add(lbl_date);
		pnl_total.add(text_fields[3]);
	}

	private void addSort() {
		JLabel lbl_nom = new JLabel("Sort name : ");
		JTextField txt_nom = new JTextField();
		JLabel lbl_type = new JLabel("Sort's Category : ");
		JComboBox<String> cbb_Type = new JComboBox<String>();
		cbb_Type.addItem("Music");
		cbb_Type.addItem("Movie");
		pnl_total.setLayout(new GridLayout(2, 2));
		pnl_total.add(lbl_nom);
		pnl_total.add(txt_nom);
		pnl_total.add(lbl_type);
		pnl_total.add(cbb_Type);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_add){
			
		}
	}
}
