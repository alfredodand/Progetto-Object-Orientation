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

public class IscriviAlCorsoWindow extends JFrame{
	private JFrame current;
	private JTextField matricolaTextField;
	Controller controller;
	private JPanel contentPane;
	
	public IscriviAlCorsoWindow(Controller controller) {
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
		
		JLabel iscrizioneLbl = new JLabel("Iscrivi uno studente");
		iscrizioneLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		iscrizioneLbl.setBounds(312, 11, 150, 14);
		panel_1.add(iscrizioneLbl);
		
		//corsi
		JLabel corsoLbl = new JLabel("Corso");
		corsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corsoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		corsoLbl.setBounds(43, 141, 98, 20);
		panel_1.add(corsoLbl);
		

		Object[] temp;
		JComboBox corso = generateComboboxCorsi(panel_1);
		
		GeneralActionListener iscrizioneListener = new GeneralActionListener(controller);
		JButton invioButton = new JButton("Submit");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.addActionListener(iscrizioneListener);
		invioButton.setActionCommand("iscrivi_studente");
		invioButton.setBounds(164, 191, 89, 20);
		panel_1.add(invioButton);
		
		matricolaTextField = new JTextField();
		matricolaTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		matricolaTextField.setColumns(10);
		matricolaTextField.setBounds(166, 103, 157, 20);
		panel_1.add(matricolaTextField);
		iscrizioneListener.setMatricola(matricolaTextField);
		iscrizioneListener.setJComboBox4(corso);
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		lblMatricola.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricola.setBounds(43, 103, 98, 20);
		panel_1.add(lblMatricola);
		
		JList list = new JList();
		list.setBounds(195, 404, 58, -91);
		panel_1.add(list);
		
		JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(166, 236, 321, 14);
		panel_1.add(errorLbl);
		
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
		iscrizioneListener.setLabel(errorLbl);
	}



	private JComboBox generateComboboxCorsi(Panel panel_1) {
		ArrayList<Corsi> corsi = new ArrayList<Corsi>();
		corsi = controller.getOperatore().getCorsi();
		ArrayList<String> nomiCorsi = new ArrayList<String>();
		Object[] temp;
		for(Corsi c : corsi) {
			String nome = c.getNome();
			nomiCorsi.add(nome);
		}
		
		temp = nomiCorsi.toArray();
		JComboBox corso = new JComboBox(temp);
		corso.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corso.setSelectedIndex(0);
		corso.setBounds(166, 141, 157, 20);
		panel_1.add(corso);
		return corso;
	}
}