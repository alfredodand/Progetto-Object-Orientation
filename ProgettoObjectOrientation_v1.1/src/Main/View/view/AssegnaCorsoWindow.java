package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.Controller;
import main.Corsi;
import main.GeneralActionListener;
import main.Insegnanti;

public class AssegnaCorsoWindow extends JFrame {
	private Controller controller;
	private JFrame current;
	private GeneralActionListener assegnaCorsoListener;
	private JComboBox corsoCombobox;
	
	public AssegnaCorsoWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		current = this;
		assegnaCorsoListener = new GeneralActionListener(controller);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 686, 512);
        
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
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
		panel_1.setBackground(new Color(65, 105, 225));
		panel_1.setPreferredSize(new Dimension(50, 10));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AssegnaCorsoWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel nomeCorsoLabel = new JLabel("Corsi non ancora assegnati");
		nomeCorsoLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeCorsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeCorsoLabel.setBounds(32, 67, 185, 14);
		panel_2.add(nomeCorsoLabel);
		
		JLabel descrizioneCorsoLabel_1 = new JLabel("Professore");
		descrizioneCorsoLabel_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		descrizioneCorsoLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		descrizioneCorsoLabel_1.setBounds(32, 92, 185, 14);
		panel_2.add(descrizioneCorsoLabel_1);
		
		JButton invioButton = new JButton("Invio");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.setBounds(279, 133, 105, 23);
		panel_2.add(invioButton);
		invioButton.addActionListener(assegnaCorsoListener);
		invioButton.setActionCommand("AssegnaCorso");
		
		Object[] temp;
		generateComboboxCorsi(panel_2);
		assegnaCorsoListener.setJComboBox4(corsoCombobox);
		JComboBox insegnanteCombobox =  generateComboboxInsegnanti(panel_2);
		assegnaCorsoListener.setJComboBox5(insegnanteCombobox);
		
		JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(176, 215, 245, 14);
		panel_2.add(errorLbl);
		assegnaCorsoListener.setLabel(errorLbl);
	}
	
	private void generateComboboxCorsi(JPanel panel_1) {
		int i = 0;
		ArrayList<Corsi> corsi = new ArrayList<Corsi>();
		corsi = controller.getOperatore().getCorsi();
		ArrayList<String> nomiCorsi = new ArrayList<String>();
		Object[] temp;
			
			for(Corsi c : corsi) {
				if(c.getDocente() == 0) {
					String nome = c.getNome();
					nomiCorsi.add(nome);
					i++;
				}
				
			}
			
		temp = nomiCorsi.toArray();
		
		corsoCombobox = new JComboBox(temp);
		corsoCombobox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		if(i != 0) {
			corsoCombobox.setSelectedIndex(0);
		}
		corsoCombobox.setBounds(227, 61, 157, 20);
		panel_1.add(corsoCombobox);


	}
	
	private JComboBox generateComboboxInsegnanti(JPanel panel_1) {
		ArrayList<Insegnanti> insegnanti = new ArrayList<Insegnanti>();
		insegnanti = controller.getOperatore().getProf();
		ArrayList<String> nomiInsegnanti = new ArrayList<String>();
		Object[] temp;
		
		for(Insegnanti c : insegnanti) {
			String nome = c.getNome();
			String cognome = c.getCognome();
			nomiInsegnanti.add(nome + " " + cognome);
		}
		
		temp = nomiInsegnanti.toArray();
		JComboBox insegnante = new JComboBox(temp);
		insegnante.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		insegnante.setSelectedIndex(0);
		insegnante.setBounds(227, 86, 157, 20);
		panel_1.add(insegnante);
		return insegnante;
	}
}