package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import main.Controller;
import main.Corsi;
import main.Studenti;

public class StatisticheStudenteWindow extends JFrame {

	private JPanel contentPane;
	private JTable tableAssenzePresenze;
	private Controller controller;
	private JFrame current = this;
	private JTextField matricolaTextField;
	private JTextField matricolaField;
	
	public StatisticheStudenteWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}

	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 250, 250));
		tabbedPane.setBorder(null);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel profiloPanel = new JPanel();
		profiloPanel.setBackground(new Color(245, 255, 250));
		tabbedPane.addTab("Profilo Studente", null, profiloPanel, null);
		profiloPanel.setLayout(null);
		
		JLabel nomelbl = new JLabel("Nome");
		nomelbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		nomelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nomelbl.setBounds(43, 60, 90, 14);
		profiloPanel.add(nomelbl);
		
		JLabel cognomelbl = new JLabel("Cognome");
		cognomelbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		cognomelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		cognomelbl.setBounds(43, 85, 90, 14);
		profiloPanel.add(cognomelbl);
		
		JLabel matricolalbl = new JLabel("Matricola");
		matricolalbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		matricolalbl.setHorizontalAlignment(SwingConstants.RIGHT);
		matricolalbl.setBounds(43, 110, 90, 14);
		profiloPanel.add(matricolalbl);
		
		JLabel iscrittolbl = new JLabel("Corsi a cui \u00E8 iscritto");
		iscrittolbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		iscrittolbl.setHorizontalAlignment(SwingConstants.RIGHT);
		iscrittolbl.setBounds(25, 185, 108, 14);
		profiloPanel.add(iscrittolbl);
		
		JLabel sessolbl = new JLabel("Sesso");
		sessolbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		sessolbl.setHorizontalAlignment(SwingConstants.RIGHT);
		sessolbl.setBounds(43, 135, 90, 14);
		profiloPanel.add(sessolbl);
		
		JLabel nascitalbl = new JLabel("Data di nascita");
		nascitalbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		nascitalbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nascitalbl.setBounds(43, 160, 90, 14);
		profiloPanel.add(nascitalbl);
		
		JLabel nomelbl_1 = new JLabel("");
		nomelbl_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomelbl_1.setBounds(178, 60, 90, 14);
		profiloPanel.add(nomelbl_1);
		
		JLabel cognomelbl_1 = new JLabel("");
		cognomelbl_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		cognomelbl_1.setBounds(178, 85, 90, 14);
		profiloPanel.add(cognomelbl_1);
		
		JLabel matricolalbl_1 = new JLabel("");
		matricolalbl_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		matricolalbl_1.setBounds(178, 110, 90, 14);
		profiloPanel.add(matricolalbl_1);
		
		JLabel sessolbl_1 = new JLabel("");
		sessolbl_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		sessolbl_1.setBounds(178, 135, 90, 14);
		profiloPanel.add(sessolbl_1);
		
		JLabel nascitalbl_1 = new JLabel("");
		nascitalbl_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nascitalbl_1.setBounds(178, 160, 90, 14);
		profiloPanel.add(nascitalbl_1);
		
		JLabel iscrittolbl_1 = new JLabel("");
		iscrittolbl_1.setVerticalAlignment(SwingConstants.TOP);
		iscrittolbl_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		iscrittolbl_1.setBounds(178, 185, 597, 175);
		profiloPanel.add(iscrittolbl_1);
		
		matricolaTextField = new JTextField();
		matricolaTextField.setBounds(402, 11, 149, 20);
		profiloPanel.add(matricolaTextField);
		matricolaTextField.setColumns(10);
		
		JLabel matricolalbl_2 = new JLabel("Inserisci la matricola di cui vuoi sapere le informazioni");
		matricolalbl_2.setHorizontalAlignment(SwingConstants.RIGHT);
		matricolalbl_2.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		matricolalbl_2.setBounds(43, 15, 333, 14);
		profiloPanel.add(matricolalbl_2);
		
		JLabel errorlbl = new JLabel("");
		errorlbl.setForeground(new Color(255, 0, 0));
		errorlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		errorlbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		errorlbl.setBounds(43, 236, 333, 14);
		profiloPanel.add(errorlbl);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				matricolalbl_1.setText("");
				nascitalbl_1.setText("");
				sessolbl_1.setText("");
				nomelbl_1.setText("");
				cognomelbl_1.setText("");
				errorlbl.setText("");
				iscrittolbl_1.setText("");
				if(!matricolaTextField.getText().isEmpty()) {
					String fullText = "";
					Studenti studente = controller.getStudenteInfo(matricolaTextField.getText().toUpperCase());
					if(studente.getCorsiFrequentati().size() != 0) {
						matricolalbl_1.setText(studente.getMatricola());
						nascitalbl_1.setText(studente.getNascita());
						sessolbl_1.setText(studente.getGenere());
						nomelbl_1.setText(studente.getNome());
						cognomelbl_1.setText(studente.getCognome());
						int i = 0;
						for(Corsi c : studente.getCorsiFrequentati()) {
							fullText = fullText + studente.getCorsiFrequentati().get(i).getNome().toString() + ",";
							i++;
						}
						fullText = fullText.substring(0, fullText.length()-1);
						iscrittolbl_1.setText(fullText);
					}
					else {
						if(studente.getMatricola() != null) {
							matricolalbl_1.setText(studente.getMatricola());
							nascitalbl_1.setText(studente.getNascita());
							sessolbl_1.setText(studente.getGenere());
							nomelbl_1.setText(studente.getNome());
							cognomelbl_1.setText(studente.getCognome());
							iscrittolbl_1.setText("Non frequenta corsi");
						}else{
							errorlbl.setText("Studente non trovato");
						}
						
					}
					
				}
				matricolaTextField.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_1.setBackground(new Color(65, 105, 225));
			}
		});
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(65, 105, 225));
		panel_1.setBounds(560, 8, 25, 25);
		profiloPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StatisticheStudenteWindow.class.getResource("/icon/search-3-16.png")));
		lblNewLabel.setBounds(5, 0, 25, 25);
		panel_1.add(lblNewLabel);
		

		
		JPanel tracciamentoPanel = new JPanel();
		tracciamentoPanel.setBackground(new Color(245, 255, 250));
		tabbedPane.addTab("Tracciamento assenze/presenze", null, tracciamentoPanel, null);
		tracciamentoPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		tracciamentoPanel.add(panel, BorderLayout.CENTER);
		
		
        DefaultTableModel model = new DefaultTableModel(new String[]{"Corso", "Assenze", "Presenze", "Percentuale minima raggiunta"}, 0);
		panel.setLayout(new BorderLayout(0, 0));
		tableAssenzePresenze = new JTable(model);
		tableAssenzePresenze.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		panel.add(tableAssenzePresenze);
		JScrollPane scrollPane = new JScrollPane(tableAssenzePresenze);
		
		tableAssenzePresenze.setFillsViewportHeight(true); 
		panel.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(12);
		panel_3.setBackground(new Color(245, 255, 250));
		panel_3.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		panel_3.setPreferredSize(new Dimension(10, 50));
		panel.add(panel_3, BorderLayout.NORTH);
		
		JLabel inserisciMatricolaLabel = new JLabel("Inserisci matricola ");
		inserisciMatricolaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		inserisciMatricolaLabel.setPreferredSize(new Dimension(100, 24));
		inserisciMatricolaLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		panel_3.add(inserisciMatricolaLabel);
		
		matricolaField = new JTextField();
		matricolaField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		matricolaField.setPreferredSize(new Dimension(70, 24));
		matricolaField.setColumns(20);
		panel_3.add(matricolaField);
		
		JLabel errorlbl2 = new JLabel("");
		errorlbl2.setPreferredSize(new Dimension(280, 24));
		errorlbl2.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		
		JPanel search = new JPanel();
		search.setPreferredSize(new Dimension(24, 24));
		search.setLayout(null);
		search.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		search.setBackground(new Color(65, 105, 225));
		panel_3.add(search);
		search.addMouseListener(new MouseAdapter() {
			int count = 0;
			String[] data = new String[4];
			@Override
			public void mouseEntered(MouseEvent e) {
				search.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				search.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				errorlbl2.setText("");
				svuotaTable();
				Studenti studente = controller.getStudenteInfo(matricolaField.getText().toUpperCase());
				if(studente.getCorsiFrequentati().size() != 0) {
					int i = 0;
					for(Corsi c : studente.getCorsiFrequentati()) {
						data[0] = studente.getCorsiFrequentati().get(i).getNome().toString();
						data[1] = studente.getAssenze(data[0], matricolaField.getText().toUpperCase());
						data[2] = studente.getPresenze(data[0], matricolaField.getText().toUpperCase());
						if(studente.getPercentualeMinima(data[0], matricolaField.getText().toUpperCase())) {
							data[3] = "SI";
						}else {
							data[3] = "NO";
						}
						fillTable();
						i++;
					}
				} else {
					if(studente.getMatricola() != null) {
						errorlbl2.setForeground(Color.red);
						errorlbl2.setText("Lo studente non è iscritto a nessun corso");

					}else{
						errorlbl2.setForeground(Color.red);
						errorlbl2.setText("Studente non trovato");
					}
					
				}
				
				
			}
			
			
		    public void fillTable() {
		    	count++;
		    	model.addRow(data);
		    }
		    
		    public void svuotaTable() {
			    for (int i = count-1; i >= 0; i--) {
			    	model.removeRow(i);
			    }
			    count = 0;
		    }
		});
		JLabel ricercaIcon = new JLabel("");
		ricercaIcon.setIcon(new ImageIcon(StatisticheStudenteWindow.class.getResource("/icon/search-3-16.png")));
		ricercaIcon.setBounds(5, 0, 25, 25);
		search.add(ricercaIcon);
		

		panel_3.add(errorlbl2);
		

		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(65, 105, 225));
		panel_2.setPreferredSize(new Dimension(10, 50));
		panel_2.setMinimumSize(new Dimension(10, 50));
		contentPane.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		panel_2.add(homeButtonPanel, BorderLayout.WEST);
		JLabel homebutton = new JLabel("");
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
		homebutton.setIcon(new ImageIcon(StatisticheStudenteWindow.class.getResource("/icon/arrow-88-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);
	}
}
