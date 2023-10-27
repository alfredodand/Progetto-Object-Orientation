package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Controller;
import main.Corsi;
import main.GeneralActionListener;

public class AggiungiLezioneWindow extends JFrame {
	
	private JTextField nomeLezioneTextField;
	private JTextField durata;
	private JFrame current;
	private Controller controller;
	private MouseAdapter MouseAdapter;
	private JTextField nome, tassoMinimoPresenze;
	private String nomeCorso, descrizioneCorso, tassoMinimoPresenzeCorso;
	private JTextField ora;
	private JTextArea descrizione;
	private JComboBox corso;
	
	public AggiungiLezioneWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}

	private void init(Controller controller) {
		GeneralActionListener aggiungiLezioneListener = new GeneralActionListener(controller);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 512);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		current = this;
		
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
		panel_1.setBackground(new Color(65, 105, 225));
		panel_1.setPreferredSize(new Dimension(50, 10));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(null);
		
		JLabel BackLabel = new JLabel("");
		BackLabel.setIcon(new ImageIcon(AggiungiLezioneWindow.class.getResource("/icon/arrow-88-48.png")));
		BackLabel.setBounds(0, 0, 50, 50);
		panel_1.add(BackLabel);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		nomeLezioneTextField = new JTextField();
		nomeLezioneTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeLezioneTextField.setBounds(151, 11, 105, 20);
		panel_2.add(nomeLezioneTextField);
		nomeLezioneTextField.setColumns(10);
		JLabel titoloLezione = new JLabel("Nome");
		titoloLezione.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		titoloLezione.setHorizontalAlignment(SwingConstants.RIGHT);
		titoloLezione.setBounds(10, 14, 131, 14);
		panel_2.add(titoloLezione);
		
		JLabel descrizioneLezione = new JLabel("Descrizione");
		descrizioneLezione.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		descrizioneLezione.setHorizontalAlignment(SwingConstants.RIGHT);
		descrizioneLezione.setBounds(10, 49, 131, 14);
		panel_2.add(descrizioneLezione);
		
		JLabel dataInizio = new JLabel("Data Inizio");
		dataInizio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		dataInizio.setHorizontalAlignment(SwingConstants.RIGHT);
		dataInizio.setBounds(10, 170, 131, 14);
		panel_2.add(dataInizio);
		JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(151, 317, 309, 14);
		panel_2.add(errorLbl);
		JLabel durataLbl = new JLabel("Durata");
		durataLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		durataLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		durataLbl.setBounds(10, 128, 131, 14);
		panel_2.add(durataLbl);
		
		durata = new JTextField();
		durata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			        errorLbl.setText("ok");
			        errorLbl.setForeground(Color.green);
					durata.setEditable(true);
			    }else {
			        errorLbl.setText("Puoi inserire solo numeri");
			        errorLbl.setForeground(Color.red);
			        durata.setEditable(false);
			    }
			}
		});
		durata.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		durata.setBounds(151, 125, 70, 20);
		panel_2.add(durata);
		durata.setColumns(10);
		
		JButton InvioButton = new JButton("Conferma");

		InvioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		InvioButton.setBounds(151, 283, 89, 23);
		panel_2.add(InvioButton);
		
		JLabel oraInizio = new JLabel("Ora Inizio");
		oraInizio.setHorizontalAlignment(SwingConstants.RIGHT);
		oraInizio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		oraInizio.setBounds(10, 212, 131, 14);
		panel_2.add(oraInizio);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(151, 165, 200, 26);
		
		panel_2.add(datePicker); 
		
		
		ora = new JTextField();
		ora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					errorLbl.setText("ok");
				    errorLbl.setForeground(Color.green);
				    ora.setEditable(true);
			    }else {
			        errorLbl.setText("Puoi inserire solo numeri");
			        errorLbl.setForeground(Color.red);
			        ora.setEditable(false);
			    }
			}
		});
		ora.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		ora.setColumns(10);
		ora.setBounds(151, 209, 70, 20);
		panel_2.add(ora);
		
		descrizione = new JTextArea();
		descrizione.setBorder(UIManager.getBorder("TextField.border"));
		descrizione.setBounds(151, 42, 220, 57);
		panel_2.add(descrizione);
		descrizione.setColumns(10);
		
		descrizione.setLineWrap(true);
		descrizione.setWrapStyleWord(true);
		
		JLabel corsoLbl = new JLabel("Corso");
		corsoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		corsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corsoLbl.setBounds(10, 243, 131, 14);
		panel_2.add(corsoLbl);
		
		generateComboboxCorsi(panel_2);
		
		InvioButton.addActionListener(aggiungiLezioneListener);
		InvioButton.setActionCommand("aggiungiLezione");
		aggiungiLezioneListener.setDatePicker(datePicker);
		aggiungiLezioneListener.setDescrizione(descrizione);
		aggiungiLezioneListener.setNome(nomeLezioneTextField);
		aggiungiLezioneListener.setJComboBox4(corso);
		aggiungiLezioneListener.setDurata(durata);
		aggiungiLezioneListener.setOra(ora);
		aggiungiLezioneListener.setLabel(errorLbl);

		
		JLabel dateStd = new JLabel("Se trascurato verr\u00E0 inserita la data attuale");
		dateStd.setHorizontalTextPosition(SwingConstants.LEFT);
		dateStd.setHorizontalAlignment(SwingConstants.LEFT);
		dateStd.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		dateStd.setBounds(374, 169, 233, 14);
		panel_2.add(dateStd);
	}
	
	private JComboBox generateComboboxCorsi(JPanel panel_1) {
		int i = 0;
		ArrayList<Corsi> corsi = new ArrayList<Corsi>();
		corsi = controller.getOperatore().getCorsi();
		ArrayList<String> nomiCorsi = new ArrayList<String>();
		Object[] temp;
		
		for(Corsi c : corsi) {
				String nome = c.getNome();
				nomiCorsi.add(nome);
			i++;
		}
		
		temp = nomiCorsi.toArray();
		corso = new JComboBox(temp);
		corso.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corso.setSelectedIndex(0);
		corso.setBounds(151, 240, 157, 20);
		panel_1.add(corso);
		return corso;
	}
}