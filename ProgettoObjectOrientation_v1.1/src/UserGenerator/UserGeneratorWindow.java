package UserGenerator;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class UserGeneratorWindow {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JLabel title;
	private JPasswordField passwordField;
	private JTextField textArea;
	private JLabel emailLbl;
	private JLabel nomeLbl;
	private JLabel cognomeLbl;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField textArea_1;
	private JTextField textArea_2;
	private JLabel passwordLbl;

	public UserGeneratorWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 255, 250));
		frame.setPreferredSize(new Dimension(700, 200));
		frame.getContentPane().setPreferredSize(new Dimension(700, 200));
		frame.setBounds(100, 100, 718, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		title = new JLabel("Username Generator");
		title.setForeground(new Color(0, 0, 255));
		title.setBounds(24, 5, 700, 25);
		title.setFont(new Font("Tahoma", Font.PLAIN, 17));
		title.setPreferredSize(new Dimension(700, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(title);
		
		panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBounds(10, 73, 334, 30);
		panel.setPreferredSize(new Dimension(250, 30));
		frame.getContentPane().add(panel);
		
		cognomeLbl = new JLabel("Cognome");
		cognomeLbl.setForeground(new Color(0, 0, 255));
		panel.add(cognomeLbl);
		cognomeLbl.setHorizontalAlignment(SwingConstants.LEFT);
		cognomeLbl.setPreferredSize(new Dimension(150, 20));
		
		textArea_1 = new JTextField();
		textArea_1.setPreferredSize(new Dimension(150, 20));
		panel.add(textArea_1);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 255, 250));
		panel_1.setBounds(10, 41, 334, 30);
		frame.getContentPane().add(panel_1);
		
		nomeLbl = new JLabel("Nome");
		nomeLbl.setForeground(new Color(0, 0, 255));
		panel_1.add(nomeLbl);
		nomeLbl.setHorizontalAlignment(SwingConstants.LEFT);
		nomeLbl.setPreferredSize(new Dimension(150, 20));
		
		textArea_2 = new JTextField();
		textArea_2.setPreferredSize(new Dimension(150, 20));
		panel_1.add(textArea_2);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		panel_2.setBounds(10, 103, 334, 30);
		frame.getContentPane().add(panel_2);
		
		emailLbl = new JLabel("Email");
		emailLbl.setForeground(new Color(0, 0, 255));
		panel_2.add(emailLbl);
		emailLbl.setHorizontalAlignment(SwingConstants.LEFT);
		emailLbl.setPreferredSize(new Dimension(150, 20));
		
		textArea = new JTextField();
		panel_2.add(textArea);
		textArea.setPreferredSize(new Dimension(150, 20));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 255, 250));
		panel_3.setBounds(10, 132, 334, 30);
		panel_3.setPreferredSize(new Dimension(100, 30));
		frame.getContentPane().add(panel_3);
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setBounds(243, 201, 85, 25);
		UserGeneratorBtnListener btnActionListener = new UserGeneratorBtnListener();
		
		passwordLbl = new JLabel("Password");
		passwordLbl.setForeground(new Color(0, 0, 255));
		passwordLbl.setPreferredSize(new Dimension(150, 20));
		passwordLbl.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(passwordLbl);
		passwordField = new JPasswordField();
		panel_3.add(passwordField);
		passwordField.setPreferredSize(new Dimension(150, 20));
		btnActionListener.setPasswordField(passwordField);
		btnActionListener.setEmail(textArea);
		btnActionListener.setName(textArea_2);
		btnActionListener.setSurname(textArea_1);

		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(btnActionListener);
		btnNewButton.setActionCommand("Submit");
		
		JLabel successLbl = new JLabel("");
		successLbl.setBounds(10, 261, 427, 25);
		frame.getContentPane().add(successLbl);
		
		btnActionListener.setLabel(successLbl);
		

	}
}
