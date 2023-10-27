package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

import main.Controller;
import main.Corsi;
import main.GeneralActionListener;

public class AggiungiAreaTematicaWindow extends JFrame{
	private JFrame current;
	private JTextField temaField;
	private Controller controller;
	private JPanel contentPane;
	
	public AggiungiAreaTematicaWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}
	
	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 449);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(245, 255, 250));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel Title = new JLabel("Aggiungi una nuova Area Tematica");
		Title.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		Title.setBounds(269, 11, 325, 14);
		panel_1.add(Title);

		GeneralActionListener aggiungiTema = new GeneralActionListener(controller);
		JButton invioButton = new JButton("Submit");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.addActionListener(aggiungiTema);
		invioButton.setActionCommand("aggiungi_tema");
		invioButton.setBounds(234, 134, 89, 20);
		panel_1.add(invioButton);
		
		temaField = new JTextField();
		temaField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		temaField.setColumns(10);
		temaField.setBounds(166, 103, 157, 20);
		panel_1.add(temaField);
		aggiungiTema.setMatricola(temaField);
		JLabel lblAreaTematica = new JLabel("Nuova Area Tematica");
		lblAreaTematica.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		lblAreaTematica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAreaTematica.setBounds(10, 103, 131, 20);
		panel_1.add(lblAreaTematica);
		aggiungiTema.setNome(temaField);
		JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(166, 236, 321, 14);
		panel_1.add(errorLbl);
		aggiungiTema.setLabel(errorLbl);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBackground(new Color(65, 105, 225));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		panel.add(homeButtonPanel, BorderLayout.WEST);
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
		homebutton.setIcon(new ImageIcon(IscriviAlCorsoWindow.class.getResource("/icon/arrow-88-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);
		aggiungiTema.setLabel(errorLbl);
	}


}