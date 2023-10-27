package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.text.AttributeSet.ColorAttribute;

public class LoginActionListener implements ActionListener {
	private Controller controller;
	private JTextField gettedusername;
	private JPasswordField gettedPassword;
	private String username;
	private char[] psw;
	private String password;
	private JLabel lblError;
	private JFrame JF = new JFrame();
	
	public LoginActionListener(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		username = gettedusername.getText();
		psw = gettedPassword.getPassword();
		password = String.valueOf(psw);
		JButton btnSource = (JButton) e.getSource(); 
		if(btnSource.getActionCommand().equals("Submit"))
			if(areFieldsEmpty())
				 JOptionPane.showMessageDialog(JF,"Riempire entrambi i campi", "System Error", JOptionPane.WARNING_MESSAGE);  
			else
				Login();
	}

	private boolean areFieldsEmpty() {
		return username.isEmpty() || password.isEmpty();
	}

	private void Login() {
		int id;
		controller.createLogin();
		id = controller.getLogin().LoginCheck(username, password, "operatore");
		if (id != -1) {
			controller.closeLoginWindow();
			controller.newOperatore();
			controller.setIDOperatoreLogged(id);
			controller.getOperatoreInfo(id);
			controller.openLoggedWindow();
		}
		else {
			lblError.setText("Errore nell'inserimento dei dati, riprovare");
			lblError.setForeground(Color.red);
			gettedusername.setText("");
			gettedPassword.setText("");
		}
	}
	
	public void setUsername(JTextField username) {
		this.gettedusername = username;
	}

	public void setPasswordField(JPasswordField password) {
		this.gettedPassword = password;
	}

	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}
	
}
