package UserGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserGeneratorBtnListener implements ActionListener {
	private JPasswordField pf;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private char[] text;
	private String RandomUsername, nome, cognome, mail;
	private RandomGenerator rg;
	private DbManagerUserGenerator dbm = new DbManagerUserGenerator();
	private boolean checked;
	private JLabel lbl;

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnSource = (JButton) e.getSource();
		if (btnSource.getActionCommand().equals("Submit")) {
			text = pf.getPassword();
			do {
				RandomUsername = rg.Generate();
				try {
					checked = dbm.ExistInDatabase(RandomUsername, "operatore");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}while(checked == true);
			nome = name.getText();
			cognome = surname.getText();
			mail = email.getText();
			String password = String.valueOf(text);
			try {
				dbm.AddOperatore(nome, cognome, mail, password, RandomUsername);
				lbl.setText("Operatore aggiunto con successo, il tuo username: " + RandomUsername);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}

	public void setPasswordField(JPasswordField pf) {
		this.pf = pf;
	}

	public void setName(JTextField name) {
		this.name = name;
	}

	public void setSurname(JTextField surname) {
		this.surname = surname;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public void setLabel(JLabel lbl) {
		this.lbl = lbl;
	}

}