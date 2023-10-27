package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;

import DAOs.TableDAOimpl;
import DAOs.TableDAOinterf;
import main.Controller;
import main.Operatore;
import main.Studenti;

public class StatisticheS extends JFrame {
	Controller controller;
	
	private JTable tableStatistiche;
	private JFrame current = this;
	Studenti studente = new Studenti(controller);
	Operatore operatore;
	TableDAOinterf tabella = new TableDAOimpl(controller);
	private JTextField generalTextField;
	JButton Btn = new JButton();
	JLabel inserisciLabel, nomeCognomeLabel, dataLabel, corsiIscrittoLabel, MFLabel, matricolaLabel;
	JButton ricercaButton;
	private int count;
	JLabel errorLbl;
	private JComboBox day, month, anno;
	DefaultTableModel model;
	String ComboBoxText = "";
	private JTextField textField_n;
	
	public StatisticheS(Controller controller, Operatore operatore) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(961, 563));
		
		this.operatore = operatore;		
		init(controller, operatore);
	}

	private void init(Controller controller, Operatore operatore) {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 50));
		panel_2.setMinimumSize(new Dimension(10, 50));
		panel_2.setBackground(new Color(65, 105, 225));
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 255, 250));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(30);
		panel_1.add(panel_3, BorderLayout.NORTH);
		
		JLabel filtraggioLabel = new JLabel("Filtra per");
		panel_3.add(filtraggioLabel);
		
		JComboBox filtraggioComboBox = new JComboBox();
		filtraggioComboBox.setPreferredSize(new Dimension(150, 22));
		panel_3.add(filtraggioComboBox);
		
		filtraggioComboBox.addItem(" ");
		filtraggioComboBox.addItem("Nome e Cognome");
		filtraggioComboBox.addItem("Data di Nascita");
		filtraggioComboBox.addItem("Numero di Corsi Iscritto");
		filtraggioComboBox.addItem("Numero di Corsi Idonei");
		filtraggioComboBox.addItem("Sesso");
		filtraggioComboBox.addItem("Matricola");
		
		inserisciLabel = new JLabel("Inserisci");
		panel_3.add(inserisciLabel);
		inserisciLabel.setVisible(false);
		
		JLabel nomeCognomeLabel = new JLabel("[inizio del Nome o del Cognome]");
		panel_3.add(nomeCognomeLabel);
		nomeCognomeLabel.setVisible(false);
		
		JLabel dataLabel = new JLabel("[data AAAA-MM-GG]");
		panel_3.add(dataLabel);
		dataLabel.setVisible(false);
		
		JLabel corsiIscrittoLabel = new JLabel("[numero corsi]");
		panel_3.add(corsiIscrittoLabel);
		corsiIscrittoLabel.setVisible(false);
		
		JLabel MFLabel = new JLabel("[sesso \"M\" or \"F\"]");
		panel_3.add(MFLabel);
		MFLabel.setVisible(false);
		
		JLabel matricolaLabel = new JLabel("[matrricola \"NA8600XXXX\"]");
		panel_3.add(matricolaLabel);
		matricolaLabel.setVisible(false);
		
		generalTextField = new JTextField();
		panel_3.add(generalTextField);
		generalTextField.setColumns(10);
		generalTextField.setVisible(false);
		
		errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		panel_3.add(errorLbl);
		
		ricercaButton = new JButton("Ricerca");
		panel_3.add(ricercaButton);
		ricercaButton.setVisible(false);
		
		String[] data = new String[6];
		model = new DefaultTableModel(new String[]{"Nome e Cognome", "Data di Nascita", "Numero di Corsi Iscritto", "Numero di Corsi Idonei", "Sesso", "Matricola"}, 0);
	
		tableStatistiche = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(tableStatistiche);
		panel_1.add(scrollPane);	
		
		tableStatistiche.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		tableStatistiche.setFillsViewportHeight(true);
		count = tabella.statisticheStudenti(model, controller);	
		
		filtraggioComboBox.addItemListener(new ItemListener() {
			int count1 = 0;
			public void itemStateChanged(ItemEvent e) {
				if(count1 == 0) {
					count1++;
				}
				if(count1 > 0) {
					count1++;
					if(count1 == 3) {
						count1 = 1;
						ComboBoxText = filtraggioComboBox.getSelectedItem().toString();
						inserisciLabel.setVisible(true);
						generalTextField.setVisible(true);
						ricercaButton.setVisible(true);
						if (ComboBoxText.equals(" ")) {
							inserisciLabel.setVisible(false);
							generalTextField.setVisible(false);
							ricercaButton.setVisible(false);;
							nomeCognomeLabel.setVisible(false);
							dataLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(false);
							MFLabel.setVisible(false);
							matricolaLabel.setVisible(false);
							svuotaTable();
							count = tabella.statisticheStudenti(model, controller);	
							errorLbl.setVisible(false);
							generalTextField.setEditable(true);
						}
						if (ComboBoxText.equals("Nome e Cognome")) {
							dataLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(false);
							MFLabel.setVisible(false);
							matricolaLabel.setVisible(false);
							nomeCognomeLabel.setVisible(true);
							errorLbl.setVisible(false);
							generalTextField.setEditable(true);
						}
						if (ComboBoxText.equals("Data di Nascita")) {
							nomeCognomeLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(false);
							MFLabel.setVisible(false);
							matricolaLabel.setVisible(false);
							dataLabel.setVisible(true);
							errorLbl.setVisible(false);
							generalTextField.setEditable(true);
						}
						if (ComboBoxText.equals("Numero di Corsi Iscritto")) {
							errorLbl.setVisible(false);
							nomeCognomeLabel.setVisible(false);
							dataLabel.setVisible(false);
							MFLabel.setVisible(false);
							matricolaLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(true);
							generalTextField.setEditable(true);
						}
						if ( ComboBoxText.equals("Numero di Corsi Idonei")) {
							errorLbl.setVisible(false);
							nomeCognomeLabel.setVisible(false);
							dataLabel.setVisible(false);
							MFLabel.setVisible(false);
							matricolaLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(true);
							generalTextField.setEditable(true);
						}
						if (ComboBoxText.equals("Sesso")) {
							nomeCognomeLabel.setVisible(false);
							dataLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(false);
							matricolaLabel.setVisible(false);
							MFLabel.setVisible(true);
							errorLbl.setVisible(false);
							generalTextField.setEditable(true);
						}
						if (ComboBoxText == "Matricola") {
							nomeCognomeLabel.setVisible(false);
							dataLabel.setVisible(false);
							corsiIscrittoLabel.setVisible(false);
							MFLabel.setVisible(false);
							matricolaLabel.setVisible(true);
							errorLbl.setVisible(false);
							generalTextField.setEditable(true);
						}
						generalTextField.setText("");
					}
				}
			}
		});
		
		generalTextField.addKeyListener(new KeyListener() {
			@Override
		    public void keyPressed(KeyEvent ke) {
				if (ComboBoxText.equals("Numero di Corsi Iscritto") || ComboBoxText.equals("Numero di Corsi Idonei")) {
					if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
						generalTextField.setEditable(true);
			        }else {
			        	errorLbl.setText("Puoi inserire solo numeri");
			        	errorLbl.setForeground(Color.red);
			        	generalTextField.setEditable(false);
			        }
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
	    });
		
		int numeroMatricole = operatore.getNumeroMatricole();
		
		ricercaButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(numeroMatricole != -1) {
					if (ComboBoxText == (" ")) {
						svuotaTable();
						count = tabella.statisticheStudenti(model, controller);
					}
					if (ComboBoxText.equals("Nome e Cognome")) {
						String str = generalTextField.getText();
						svuotaTable();
						count = tabella.statisticheStudentiNomeCognome(model, controller, str);
					}
					if (ComboBoxText.equals("Data di Nascita")) {
						String str = generalTextField.getText();
						svuotaTable();
						count = tabella.statisticheStudentiDataNascita(model, controller, str);
					}
					if (ComboBoxText.equals("Numero di Corsi Iscritto")) {
						String str = generalTextField.getText();
						svuotaTable();
						count = tabella.statisticheStudentiCorsiIscritto(model, controller, str);
					}
					if (ComboBoxText == "Numero di Corsi Idonei") {
						String str = generalTextField.getText();
						svuotaTable();
						count = tabella.statisticheStudentiCorsiIdonei(model, controller, str);
					}
					if (ComboBoxText.equals("Sesso")) {
						String str = generalTextField.getText();
						svuotaTable();
						count = tabella.statisticheStudentiSesso(model, controller, str);
					}
					if (ComboBoxText == "Matricola") {
						String str = generalTextField.getText();
						svuotaTable();
						count = tabella.statisticheStudentiMatricola(model, controller, str);
					}
					generalTextField.setText("");
				}
			}
		});
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		panel_2.add(homeButtonPanel, BorderLayout.WEST);
		JLabel homebutton = new JLabel("");
		homeButtonPanel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				homeButtonPanel.setBackground(new Color(211, 211, 211));
			}
			public void mouseExited(MouseEvent e) {
				homeButtonPanel.setBackground(new Color(65, 105, 225));
			}
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
    
    public void svuotaTable() {
	    for (int i = count-1; i >= 0; i--) {
	    	model.removeRow(i);
	    }
	    count = 0;
    }	
}