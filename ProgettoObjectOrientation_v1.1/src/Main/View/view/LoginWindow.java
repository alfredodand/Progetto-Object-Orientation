package view;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import main.Controller;
import main.LoginActionListener;

public class LoginWindow {

	private JFrame frame;
	private JTextField usernameTextArea;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JPanel panel;
	private JPanel panel_1;
	private JButton submitButton;
	private JLabel loginLabel;
	private JLabel mancateCredenzialiLabel;
	private JLabel lblError;
	
	Controller controller;
	
	public JFrame getFrame() {
		return frame;
	}

	public LoginWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}
	
	private void init(Controller controller) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(310, 270));
		frame.getContentPane().setPreferredSize(new Dimension(700, 200));
		frame.setBounds(100, 100, 311, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
		frame.getContentPane().setLayout(flowLayout);
		loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginLabel.setPreferredSize(new Dimension(700, 25));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(loginLabel);
		
		panel = new JPanel();
		frame.getContentPane().add(panel);
		
		usernameLabel = new JLabel("Username");
		panel.add(usernameLabel);
		
		usernameTextArea = new JTextField();
		panel.add(usernameTextArea);
		usernameTextArea.setColumns(20);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		
		passwordLabel = new JLabel("Password");
		panel_1.add(passwordLabel);
		
		passwordField = new JPasswordField();
		panel_1.add(passwordField);
		passwordField.setColumns(20);
		
		submitButton = new JButton("Submit\r\n");
		frame.getContentPane().add(submitButton);
		LoginActionListener log_listener = new LoginActionListener(controller);
		submitButton.addActionListener(log_listener);
		submitButton.setActionCommand("Submit");
		submitButton.setEnabled(false);
		
		mancateCredenzialiLabel = new JLabel("<html>\r\n<p style=\"margin-left: 10px\">\r\nSe non hai le credenziali per accedere \u00E8 necessario <br>\r\ncontattare gli operatori per generare un nuovo <br>\r\n username <br>\r\n</p>");
		mancateCredenzialiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mancateCredenzialiLabel.setPreferredSize(new Dimension(300, 100));
		frame.getContentPane().add(mancateCredenzialiLabel);
		log_listener.setUsername(usernameTextArea);
		log_listener.setPasswordField(passwordField);
		lblError = new JLabel("");
		lblError.setBackground(Color.WHITE);
		frame.getContentPane().add(lblError);
		log_listener.setLblError(lblError);
		
	}

	public JLabel getLable() {
		return lblError;
	}
	
	public JButton getBtn() {
		return submitButton;
	}

}
