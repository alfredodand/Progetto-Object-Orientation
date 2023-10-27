package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;

import DAOs.TableDAOimpl;
import DAOs.TableDAOinterf;
import main.AreaTematica;
import main.Controller;
import main.Operatore;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VisualizzaCorsiWindow extends JFrame {

	private Controller controller;
	private Operatore operatore;
	private TableDAOinterf tabella = new TableDAOimpl(controller);
	private int count = 0;
	private DefaultTableModel model;
	private JFrame current = this;
	private JTextField generalTextField;
	private AreaTematica areaTematica;
	private String ComboBoxText;
	private ArrayList<AreaTematica> areeTematiche;
	
	public VisualizzaCorsiWindow(Controller controller, Operatore operatore) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(961, 563));
		this.operatore = operatore;		
		init(controller);
	}

	private void init(Controller controller) {
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
		filtraggioComboBox.addItem("Nome");
		filtraggioComboBox.addItem("Parola Chiave");
		filtraggioComboBox.addItem("Area Tematica");
		filtraggioComboBox.addItem("Docente");
		
		JLabel inserisciLabel = new JLabel("Inserisci");
		panel_3.add(inserisciLabel);
		
		JLabel nomeLabel = new JLabel("[nome o parte del nome]");
		panel_3.add(nomeLabel);
		
		JLabel parolaChiaveLabel = new JLabel("[parola chiave nella descrizione]");
		panel_3.add(parolaChiaveLabel);
		
		generalTextField = new JTextField();
		panel_3.add(generalTextField);
		generalTextField.setColumns(10);
		
		areeTematiche = controller.getOperatore().getAreeTematiche();
		
		JComboBox areaTematicaComboBox = new JComboBox();
		areaTematicaComboBox.setPreferredSize(new Dimension(150, 22));
		panel_3.add(areaTematicaComboBox);
		int i = 0;
		for(AreaTematica a : areeTematiche) {
			areaTematicaComboBox.addItem(a.getNome());
			i++;
		}
		
		JButton ricercaButton = new JButton("Ricerca");
		panel_3.add(ricercaButton);
		
		String[] data = new String[7];
		model = new DefaultTableModel(new String[]{"Nome", "Descrizione", "Tasso Minimo Presenze", "Numero Partecipanti", "Numero Lezioni", "Area Tematica", "Docente"}, 0);
	
		JTable tableCorsi = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(tableCorsi);
		panel_1.add(scrollPane);	
		
		tableCorsi.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		tableCorsi.setFillsViewportHeight(true);
		count = tabella.visualizzaCorsi(model, controller);
		
		inserisciLabel.setVisible(false);
		nomeLabel.setVisible(false);
		parolaChiaveLabel.setVisible(false);
		generalTextField.setVisible(false);
		areaTematicaComboBox.setVisible(false);
		ricercaButton.setVisible(false);
		
		
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
						ricercaButton.setVisible(true);
						if (ComboBoxText.equals(" ")) {
							inserisciLabel.setVisible(false);
							ricercaButton.setVisible(false);
							generalTextField.setVisible(false);
							areaTematicaComboBox.setVisible(false);
							nomeLabel.setVisible(false);
							parolaChiaveLabel.setVisible(false);
							svuotaTable();
							count = tabella.visualizzaCorsi(model, controller);
						}
						if (ComboBoxText.equals("Nome")) {
							inserisciLabel.setVisible(true);
							ricercaButton.setVisible(true);
							generalTextField.setVisible(true);
							nomeLabel.setVisible(true);							
							parolaChiaveLabel.setVisible(false);
							areaTematicaComboBox.setVisible(false);
						}
						if (ComboBoxText.equals("Parola Chiave")) {

							inserisciLabel.setVisible(true);
							ricercaButton.setVisible(true);
							generalTextField.setVisible(true);
							parolaChiaveLabel.setVisible(true);
							nomeLabel.setVisible(false);
							areaTematicaComboBox.setVisible(false);
						}
						if (ComboBoxText.equals("Area Tematica")) {
							inserisciLabel.setVisible(true);
							ricercaButton.setVisible(true);
							areaTematicaComboBox.setVisible(true);
							generalTextField.setVisible(false);
							nomeLabel.setVisible(false);
							parolaChiaveLabel.setVisible(false);
						}
						if (ComboBoxText.equals("Docente")) {
							inserisciLabel.setVisible(true);
							ricercaButton.setVisible(true);
							generalTextField.setVisible(true);
							nomeLabel.setVisible(true);
							areaTematicaComboBox.setVisible(false);
							parolaChiaveLabel.setVisible(false);
						}
						generalTextField.setText("");
					}
				}
			}
		});
		
		ricercaButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (ComboBoxText == (" ")) {
					svuotaTable();
					count = tabella.visualizzaCorsi(model, controller);
				}
				if (ComboBoxText.equals("Nome")) {
					String str = generalTextField.getText();
					svuotaTable();
					count = tabella.visualizzaCorsiNome(model, controller, str);
				}
				if (ComboBoxText.equals("Parola Chiave")) {
					String str = generalTextField.getText();
					svuotaTable();
					count = tabella.visualizzaCorsiParolaChiave(model, controller, str);
				}
				if (ComboBoxText.equals("Area Tematica")) {
					String str = areaTematicaComboBox.getSelectedItem().toString();
					svuotaTable();
					count = tabella.visualizzaCorsiAreaTematica(model, controller, str);
				}
				if (ComboBoxText == "Docente") {
					String str = generalTextField.getText();
					svuotaTable();
					count = tabella.visualizzaCorsiDocente(model, controller, str);
				}
				generalTextField.setText("");
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