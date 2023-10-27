package view;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;

import main.Controller;
import main.GeneralActionListener;

public class ImmatricolaWindow extends JFrame {
	private Controller controller;
	private JPanel contentPane;
	private JTextField nomeImmatricolazione;
	private JTextField cognomeImmatricolazione;
	private Panel panel_2;
	private JLabel iscrizioneEffettuata;
	private JComboBox day, month, anno;
	private JFrame current = this;
	
	public ImmatricolaWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}

	private void init(Controller controller) {
		GeneralActionListener ListenerConferma = new GeneralActionListener(controller);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 512);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(245, 255, 250));
		contentPane.add(panel_2, BorderLayout.CENTER);
		
		nomeImmatricolazione = new JTextField();
		nomeImmatricolazione.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeImmatricolazione.setColumns(10);
		nomeImmatricolazione.setBounds(238, 62, 160, 20);
		panel_2.add(nomeImmatricolazione);
		ListenerConferma.setNome(nomeImmatricolazione);
		
		cognomeImmatricolazione = new JTextField();
		cognomeImmatricolazione.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		cognomeImmatricolazione.setColumns(10);
		cognomeImmatricolazione.setBounds(238, 87, 160, 20);
		panel_2.add(cognomeImmatricolazione);
		ListenerConferma.setCognome(cognomeImmatricolazione);
		generateComboBoxDayYearMonth(ListenerConferma);
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(115, 62, 98, 20);
		panel_2.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		lblCognome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCognome.setBounds(115, 87, 98, 20);
		panel_2.add(lblCognome);
		
		JLabel dataNLbl = new JLabel("Data di nascita");
		dataNLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		dataNLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dataNLbl.setBounds(115, 115, 98, 20);
		panel_2.add(dataNLbl);
		
		JButton ImmatricolaButton = new JButton("Conferma");
		ImmatricolaButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ImmatricolaButton.setActionCommand("Immatricolazione");
		ImmatricolaButton.setBounds(306, 262, 89, 23);
		panel_2.add(ImmatricolaButton);
		ImmatricolaButton.addActionListener(ListenerConferma);
		ImmatricolaButton.setActionCommand("Immatricolazione");
		ImmatricolaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cognomeImmatricolazione.setText("");
				nomeImmatricolazione.setText("");
				day.setSelectedIndex(0);
				month.setSelectedIndex(0);
				anno.setSelectedIndex(101);
			}
		});
		
		JRadioButton FemminaRadioButton = new JRadioButton("Femmina");
		FemminaRadioButton.setBackground(new Color(245, 255, 250));
		FemminaRadioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		FemminaRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListenerConferma.setGenere("F");
			}
		});
		
		JRadioButton MaschioRadioButton = new JRadioButton("Maschio");
		MaschioRadioButton.setBackground(new Color(245, 255, 250));
		MaschioRadioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		MaschioRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListenerConferma.setGenere("M");
			}
		});
		
		FemminaRadioButton.setBounds(324, 152, 71, 20);
		panel_2.add(FemminaRadioButton);
		MaschioRadioButton.setBounds(239, 152, 71, 20);
		panel_2.add(MaschioRadioButton);
		ButtonGroup group = new ButtonGroup();
	    group.add(MaschioRadioButton);
	    group.add(FemminaRadioButton);
	    
	    
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBackground(new Color(65, 105, 225));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		JPanel homeButtonPanel = new JPanel();
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
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		panel.add(homeButtonPanel, BorderLayout.WEST);
		
		JLabel homebutton = new JLabel("");
		homebutton.setIcon(new ImageIcon(ImmatricolaWindow.class.getResource("/icon/arrow-88-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);

	    iscrizioneEffettuata = new JLabel("");
	    iscrizioneEffettuata.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
	    iscrizioneEffettuata.setBounds(10, 11, 629, 40);
	    panel_2.add(iscrizioneEffettuata);
	    ListenerConferma.setLabel(iscrizioneEffettuata);
	    
	}
	
	private void generateComboBoxDayYearMonth(GeneralActionListener ListenerConferma) {
		int i;
		Object[] temp;
		ArrayList tmp = new ArrayList<Integer>();
		for(i = 1; i<32; i++) {
			tmp.add(i);
		}
		temp = tmp.toArray();
		day = new JComboBox(temp);
		day.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		day.setBounds(238, 115, 40, 20);
		panel_2.add(day);
		ListenerConferma.setJComboBox1(day);
		
		tmp.removeAll(tmp);
		for(i = 1; i<13; i++) {
			tmp.add(i);
		}
		temp = tmp.toArray();
		month = new JComboBox(temp);
		month.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		month.setBounds(288, 115, 40, 20);
		panel_2.add(month);
		ListenerConferma.setJComboBox2(month);

		tmp.removeAll(tmp);
		int last = 101;
		for(i = 1920; i<2022; i++) {
			tmp.add(i);
		}
		temp = tmp.toArray();
		anno = new JComboBox(temp);
		anno.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		anno.setSelectedIndex(101);
		anno.setBounds(338, 115, 60, 20);
		panel_2.add(anno);
		ListenerConferma.setJComboBox3(anno);
	}
	
}
