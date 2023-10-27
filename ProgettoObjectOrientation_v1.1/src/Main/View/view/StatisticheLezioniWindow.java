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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

import main.Controller;
import main.Corsi;
import main.GeneralActionListener;
import main.Lezioni;
import main.Tabella;

import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import DAOs.TableDAOimpl;
import DAOs.TableDAOinterf;

public class StatisticheLezioniWindow extends JFrame{
	private JFrame current;
	private Controller controller;
	private JPanel contentPane;
	private JComboBox lezioni;
	private String ComboBoxText;
	private int count, count3, count2 = 0;
	private JTable table;
	private JTable table_1;
	private JLabel filtraLezione;
	private TableDAOinterf tabella = new TableDAOimpl(controller);
	DefaultTableModel model, model2;
	
	public StatisticheLezioniWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}
	
	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 583);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 255, 250));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		panel_2.setPreferredSize(new Dimension(10, 70));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 255, 250));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 5));
		
		JLabel corsoLbl = new JLabel("Filtra per corso");
		corsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corsoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		corsoLbl.setBounds(56, 11, 98, 20);
		panel_2.add(corsoLbl);
		
		Object[] temp;
		JComboBox corso = generateComboboxCorsi(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 400));
		panel_3.add(scrollPane);
		
		model = new DefaultTableModel(new String[]{"Presenti"}, 0);
		table = new JTable(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.setPreferredSize(new Dimension(400, 400));
		scrollPane.setViewportView(table);
		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 400));
		panel_3.add(scrollPane_1);
		
		model2 = new DefaultTableModel(new String[]{"Assenti"}, 0);
		table_1 = new JTable(model2);
		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(300);
		table_1.setPreferredSize(new Dimension(400, 400));
		scrollPane_1.setViewportView(table_1);
		
		
		filtraLezione = new JLabel("Filtra per lezione");
		filtraLezione.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		filtraLezione.setHorizontalAlignment(SwingConstants.RIGHT);
		filtraLezione.setBounds(46, 42, 108, 20);
		panel_2.add(filtraLezione);
		filtraLezione.setVisible(false);
		
		corso.addItemListener(new ItemListener() {
			int count1 = 0;
			public void itemStateChanged(ItemEvent e) {
				if(count1 == 0) {
					count1++;
				}
				if(count1 > 0) {
					count1++;
					if(count1 == 3) {
						count1 = 1;
						ComboBoxText = corso.getSelectedItem().toString();
						filtraLezione.setVisible(true);
						if(count2 != 0) {							
							lezioni.setVisible(false);
							if(model.getRowCount()>0) {
								count = svuotaTable(model, count);
							}
							if(model2.getRowCount()>0) {
								count3 = svuotaTable(model2, count3);
							}
						}
						count2++;
						generateComboboxLezioni(panel_2, ComboBoxText);
						lezioni.addItemListener(new ItemListener() {
							int count1 = 0;
							public void itemStateChanged(ItemEvent e) {
								if(count1 == 0) {
									count1++;
								}
								if(count1 > 0) {
									count1++;
									if(count1 == 3) {
										count = svuotaTable(model, count);
										count3 = svuotaTable(model2, count3);
										count1 = 1;
										ComboBoxText = lezioni.getSelectedItem().toString();
										if(!ComboBoxText.isEmpty()) {
											count3 = tabella.assentiALezione(model2, controller, ComboBoxText);
											count = tabella.presentiALezione(model, controller, ComboBoxText);
										}
									}
								}
							}
						});

					}
				}
			}
		});
		

		
		GeneralActionListener iscrizioneListener = new GeneralActionListener(controller);
		iscrizioneListener.setJComboBox4(corso);
		
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
		
		
	}



	private JComboBox generateComboboxCorsi(JPanel panel_2) {
		ArrayList<Corsi> corsi = new ArrayList<Corsi>();
		corsi = controller.getOperatore().getCorsi();
		ArrayList<String> nomiCorsi = new ArrayList<String>();
		Object[] temp;
		nomiCorsi.add("");
		for(Corsi c : corsi) {
			String nome = c.getNome();
			nomiCorsi.add(nome);
		}
		temp = nomiCorsi.toArray();
		JComboBox corso = new JComboBox(temp);		
		
		corso.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corso.setSelectedIndex(0);
		corso.setBounds(164, 11, 157, 20);
		panel_2.add(corso);
		return corso;
	}
	
	private void generateComboboxLezioni(JPanel panel_2, String nomeCorso) {
		int i = 0;
		ArrayList<Corsi> corso = new ArrayList<Corsi>();
		corso = controller.getOperatore().getCorsi();
		ArrayList<Lezioni> lezione = new ArrayList<Lezioni>();
		for(Corsi c: corso) {
			if(c.getNome().equals(nomeCorso)) {
				lezione = c.getLezioni(nomeCorso);
			}
		}
		ArrayList<String> nomiLezioni = new ArrayList<String>();
		nomiLezioni.add("");
		Object[] temp;		
		for(Lezioni l : lezione) {
			String nome = l.getTitolo().toString();
			nomiLezioni.add(nome);
			i++;
		}
		temp = nomiLezioni.toArray();
		lezioni = new JComboBox(temp);
		lezioni.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		lezioni.setSelectedIndex(0);
		lezioni.setBounds(164, 42, 157, 20);
		panel_2.add(lezioni);	
	}
	
	public int svuotaTable(DefaultTableModel table, int count) {
	    for (int i = count-1; i >= 0; i--) {
	    	table.removeRow(i);
	    }
	    return 0;
    }	
}