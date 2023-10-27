package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class HomeWindow extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JFrame current = this;
	
	public HomeWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 512);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBackground(new Color(65, 105, 225));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		panel.add(homeButtonPanel, BorderLayout.WEST);
		
		JLabel homebutton = new JLabel("");
		homebutton.addMouseListener(new MouseAdapter() {
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
		homebutton.setIcon(new ImageIcon(HomeWindow.class.getResource("/icon/arrow-88-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 255, 250));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel BenvenutoLabel = new JLabel("<html>\r\n<p align=\"center\">BENVENUTO<br> NEL PROGETTO DI OBJECT ORIENTATION <br>&<br> BASI DI DATI I<br><br>\r\n\r\n</p>");
		BenvenutoLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		BenvenutoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BenvenutoLabel.setBounds(0, 11, 670, 263);
		panel_1.add(BenvenutoLabel);
		
		JLabel informazioniLabel = new JLabel("<html>\r\n<p align=\"center\">Professore: Sergio Di Martino - OO<br>\r\nProfessore: Adriano Peron, Silvio Barra - BDI <br>\r\nRealizzatori: Alfredo D'Andrea - NA86003615 & Cristian Carrella - NA86003677</p>");
		informazioniLabel.setForeground(new Color(105, 105, 105));
		informazioniLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		informazioniLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informazioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informazioniLabel.setBounds(67, 183, 542, 128);
		panel_1.add(informazioniLabel);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(HomeWindow.class.getResource("/icon/602px-Napoli_university_seal_alfachannel.png")));
		logo.setBounds(30, 30, 80, 80);
		panel_1.add(logo);
	}

}
