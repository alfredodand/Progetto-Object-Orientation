package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;

import main.Controller;

public class ProfiloWindow extends JFrame{
	Controller controller;
	JFrame current;
	
	public ProfiloWindow(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 512);
		this.controller = controller;		
		init(controller);
	}

	private void init(Controller controller) {
		String nome = controller.getOperatore().getNome();
		String cognome = controller.getOperatore().getCognome();
		String email = controller.getOperatore().getEmail();
		String username = controller.getOperatore().getUsername();
		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(10, 50));
		header.setBackground(new Color(65, 105, 225));
		getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		header.add(homeButtonPanel, BorderLayout.WEST);
		current = this;
		homeButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				homeButtonPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				homeButtonPanel.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openLoggedWindowFromAnotherWindow(current);
			}
		});
		
		JLabel homebutton = new JLabel("");
		homebutton.setIcon(new ImageIcon(ProfiloWindow.class.getResource("/icon/house-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel profiloLabel = new JLabel("<html><p style=\"margin-top:10px\">ECCO IL TUO PROFILO</p>");
		profiloLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		profiloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(profiloLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(245, 255, 250));
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(167, 90, 66, 22);
		panel_1.add(usernameLabel);
		
		JLabel usernameDynamicLbl = new JLabel(username);
		usernameDynamicLbl.setBounds(313, 94, 88, 14);
		panel_1.add(usernameDynamicLbl);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(167, 121, 66, 22);
		panel_1.add(nomeLabel);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setBounds(167, 154, 66, 22);
		panel_1.add(cognomeLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(167, 183, 66, 22);
		panel_1.add(emailLabel);
		
		JLabel nomeDynamicLabel = new JLabel(nome);
		nomeDynamicLabel.setBounds(313, 125, 88, 14);
		panel_1.add(nomeDynamicLabel);
		
		JLabel cognomeDynamicLabel = new JLabel(cognome);
		cognomeDynamicLabel.setBounds(313, 158, 88, 14);
		panel_1.add(cognomeDynamicLabel);
		
		JLabel emailDynamicLabel = new JLabel(email);
		emailDynamicLabel.setBounds(313, 187, 88, 14);
		panel_1.add(emailDynamicLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(167, 110, 236, 2);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(167, 141, 234, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(167, 174, 234, 2);
		panel_1.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(167, 203, 234, 2);
		panel_1.add(separator_1_2);
	}
}
