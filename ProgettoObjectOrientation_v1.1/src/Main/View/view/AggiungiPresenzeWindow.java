package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import main.Controller;
import main.Corsi;
import main.Lezioni;

public class AggiungiPresenzeWindow extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private String nomeCorso;
	private JComboBox corsiBox;
	private JComboBox lezioni;
	private boolean clicked = true;
	private JFrame current = this;
	private JLabel errorLbl;
	public int count = 0;
	public ArrayList<JLabel> labels = new ArrayList<JLabel>();
	public ArrayList<JCheckBox> check = new ArrayList<JCheckBox>();
	public ArrayList<String> dati;
	
	public AggiungiPresenzeWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBackground(new Color(65, 105, 225));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				controller.openLoggedWindowFromAnotherWindow(current);
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
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(50, 10));
		panel_1.setBackground(new Color(65, 105, 225));
		panel.add(panel_1, BorderLayout.WEST);
		
		JLabel BackLabel = new JLabel("");
		BackLabel.setIcon(new ImageIcon(AggiungiPresenzeWindow.class.getResource("/icon/arrow-88-48.png")));
		BackLabel.setBounds(0, 0, 50, 50);
		panel_1.add(BackLabel);
		int i = 0;
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		generateComboboxCorsi(panel_2);
		
		JButton invioButton = new JButton("Invio");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		invioButton.setBounds(300, 11, 105, 23);
		panel_2.add(invioButton);

		JLabel selezionaCorsoLabel = new JLabel("Seleziona corso");
		selezionaCorsoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		selezionaCorsoLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		selezionaCorsoLabel.setBounds(10, 14, 97, 14);
		panel_2.add(selezionaCorsoLabel);
		
		JLabel selezionaLezioneLabel = new JLabel("Seleziona lezione");
		selezionaLezioneLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		selezionaLezioneLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		selezionaLezioneLabel.setBounds(10, 15, 97, 14);
		selezionaLezioneLabel.setVisible(false);
		panel_2.add(selezionaLezioneLabel);
		
//		DefaultTableModel model = new DefaultTableModel(new String[]{"Nome e Cognome", "Presente"}, 0);
//		JTable table = new JTable(model);
//		table.setFillsViewportHeight(true);
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(0, 63, 670, 360);
//		panel_2.add(scrollPane);
		
		errorLbl = new JLabel("");
		errorLbl.setBounds(415, 15, 245, 14);
		panel_2.add(errorLbl);
		JButton btnInviaPresenze = new JButton("Invia Presenze");
		JButton btnBack = new JButton("Back");
		
		invioButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(clicked == true) {
					errorLbl.setText("");
					selezionaLezioneLabel.setVisible(true);
					nomeCorso = corsiBox.getSelectedItem().toString();
					generateComboboxLezioni(panel_2, nomeCorso);
					corsiBox.setVisible(false);
					selezionaCorsoLabel.setVisible(false);
					invioButton.setText("Reset");
					clicked = false;
					try {
						String nomeLezione = lezioni.getSelectedItem().toString();
						//svuotaTable();
						dati = controller.getOperatore().getIscrittiACorso(nomeCorso);
						int i = 0;
						int z = 40;
						for (Object s : dati) {
							JLabel newLabel = new JLabel("");
							JCheckBox cb = new JCheckBox();
							check.add(cb);
							labels.add(newLabel);
							//data[0] = dati.get(i).toString();
							//data[1] = ;
							labels.get(i).setText(dati.get(i).toString());
							labels.get(i).setBounds(18, z, 168, 14);
							panel_2.add(labels.get(i));
							check.get(i).setBounds(150, z, 168, 14);
							check.get(i).setBackground(new Color(245, 255, 250));
							panel_2.add(check.get(i));
							i++;
							z = z + 20;
							count++;
						}
						invioButton.setVisible(false);
						btnInviaPresenze.setVisible(true);
						btnBack.setVisible(true);
						panel_2.repaint();
						panel_2.revalidate();
					}catch(NullPointerException e1) {
						errorLbl.setText("Non esistono lezioni per questo corso");
					}
				}else {
					reset(panel_2, invioButton, selezionaCorsoLabel, selezionaLezioneLabel);
				}
			}
		});
		
		
		btnInviaPresenze.setVisible(false);
		btnInviaPresenze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invioButton.setVisible(true);
				btnInviaPresenze.setVisible(false);
				btnBack.setVisible(false);
				int i = 0;
				errorLbl.setForeground(Color.green);
				for (Object s : dati) {
					if (check.get(i).isSelected()) {
			            controller.getOperatore().aggiungiPresenze(labels.get(i).getText(), lezioni.getSelectedItem().toString(), nomeCorso);
			        }
					i++;
				}
				reset(panel_2, invioButton, selezionaCorsoLabel, selezionaLezioneLabel);

				errorLbl.setText("Presenze inviate con successo");
			}
		});
		btnInviaPresenze.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		btnInviaPresenze.setBounds(425, 11, 144, 23);
		panel_2.add(btnInviaPresenze);
		
		
		btnBack.setVisible(false);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invioButton.setVisible(true);
				btnInviaPresenze.setVisible(false);
				btnBack.setVisible(false);
				reset(panel_2, invioButton, selezionaCorsoLabel, selezionaLezioneLabel);
			}
		});
		btnBack.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		btnBack.setBounds(425, 44, 144, 23);
		panel_2.add(btnBack);
	}
	
	private JComboBox generateComboboxCorsi(JPanel panel_1) {
		int i = 0;
		ArrayList<Corsi> corsi = new ArrayList<Corsi>();
		corsi = controller.getOperatore().getCorsi();
		ArrayList<String> nomiCorsi = new ArrayList<String>();
		Object[] temp;
		
		for(Corsi c : corsi) {
				String nome = corsi.get(i).getNome().toString();
				nomiCorsi.add(nome);
			i++;
		}
		
		temp = nomiCorsi.toArray();
		corsiBox = new JComboBox(temp);
		corsiBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		corsiBox.setSelectedIndex(0);
		corsiBox.setBounds(117, 12, 157, 20);
		panel_1.add(corsiBox);
		return corsiBox;
	}
	
	private JComboBox generateComboboxLezioni(JPanel panel_1, String nomeCorso) {
		int i = 0;
		Corsi corso = new Corsi(nomeCorso, controller);
		
		ArrayList<Lezioni> lezione = new ArrayList<Lezioni>();
		lezione = corso.getLezioni(nomeCorso);
		ArrayList<String> nomiLezioni = new ArrayList<String>();
		Object[] temp;
		if(!lezione.isEmpty()) {
			for(Lezioni l : lezione) {
				String nome = lezione.get(i).getTitolo().toString();
				nomiLezioni.add(nome);
				i++;
			}
			temp = nomiLezioni.toArray();
			lezioni = new JComboBox(temp);
			lezioni.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
			lezioni.setSelectedIndex(0);
			lezioni.setBounds(117, 12, 157, 20);
		}else {
			lezioni = new JComboBox();
			lezioni.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
			lezioni.setBounds(117, 12, 157, 20);
			errorLbl.setForeground(Color.red);
			errorLbl.setText("Non ci sono lezioni per questo corso");
		}
		panel_1.add(lezioni);	

		return lezioni;
	}
	
	private void reset(JPanel panel_2, JButton btnNewButton, JLabel lblNewLabel, JLabel lblNewLabel_1) {
		for(int i = 0; i < count; i++) {
			panel_2.remove(labels.get(i));
			panel_2.remove(check.get(i));
		}
		panel_2.repaint();
		panel_2.revalidate();
		count = 0;
		lezioni.setVisible(false);
		corsiBox.setVisible(true);
		lblNewLabel_1.setVisible(false);
		lblNewLabel.setVisible(true);
		btnNewButton.setText("Invio");
		clicked = true;
		errorLbl.setText("");
	}
}
