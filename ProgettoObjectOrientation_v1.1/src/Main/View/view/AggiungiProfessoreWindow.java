package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import main.Controller;
import main.GeneralActionListener;

public class AggiungiProfessoreWindow extends JFrame {
	private JTextField nomeProfessoreTextField;
	private JTextField cognomeProfessoreTextField;
	private Controller controller;
	private JFrame current;
	private GeneralActionListener aggiungiProfListener;
	private JComboBox day, month, anno;
	
	public AggiungiProfessoreWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		aggiungiProfListener = new GeneralActionListener(controller);
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AggiungiProfessoreWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		nomeProfessoreTextField = new JTextField();
		nomeProfessoreTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeProfessoreTextField.setBounds(173, 65, 160, 20);
		panel_2.add(nomeProfessoreTextField);
		nomeProfessoreTextField.setColumns(10);
		aggiungiProfListener.setNome(nomeProfessoreTextField);
		JLabel nomeProfessore = new JLabel("Nome");
		nomeProfessore.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeProfessore.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeProfessore.setBounds(31, 68, 131, 14);
		panel_2.add(nomeProfessore);
		
		cognomeProfessoreTextField = new JTextField();
		cognomeProfessoreTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		cognomeProfessoreTextField.setColumns(10);
		cognomeProfessoreTextField.setBounds(173, 90, 160, 20);
		panel_2.add(cognomeProfessoreTextField);
		aggiungiProfListener.setCognome(cognomeProfessoreTextField);
		
		JButton invioButton = new JButton("Invio");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.setBounds(173, 224, 105, 23);
		panel_2.add(invioButton);
		invioButton.addActionListener(aggiungiProfListener);
		invioButton.setActionCommand("Nuovo Prof");
		JLabel cognomeProfessore = new JLabel("Cognome");
		cognomeProfessore.setHorizontalAlignment(SwingConstants.RIGHT);
		cognomeProfessore.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		cognomeProfessore.setBounds(31, 92, 131, 14);
		panel_2.add(cognomeProfessore);
		
		JLabel dataNascitaLbl = new JLabel("Data di nascita");
		dataNascitaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dataNascitaLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		dataNascitaLbl.setBounds(31, 196, 131, 14);
		panel_2.add(dataNascitaLbl);
		generateComboBoxDayYearMonth(aggiungiProfListener, panel_2);
		
		JLabel descrizioneLbl = new JLabel("Descrizione");
		descrizioneLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		descrizioneLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		descrizioneLbl.setBounds(31, 127, 131, 14);
		panel_2.add(descrizioneLbl);
		
		JTextArea descrizioneTextArea = new JTextArea();
		descrizioneTextArea.setBorder(UIManager.getBorder("TextField.border"));
		descrizioneTextArea.setBounds(173, 121, 160, 63);
		panel_2.add(descrizioneTextArea);
		aggiungiProfListener.setDescrizione(descrizioneTextArea);
		
		JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(173, 295, 288, 14);
		panel_2.add(errorLbl);
		
		aggiungiProfListener.setLabel(errorLbl);
	}
	
	private void generateComboBoxDayYearMonth(GeneralActionListener ListenerConferma, JPanel panel_2) {
		int i;
		Object[] temp;
		ArrayList tmp = new ArrayList<Integer>();
		for(i = 1; i<32; i++) {
			tmp.add(i);
		}
		temp = tmp.toArray();
		day = new JComboBox(temp);
		day.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		day.setBounds(173, 195, 40, 20);
		panel_2.add(day);
		ListenerConferma.setJComboBox1(day);
		
		tmp.removeAll(tmp);
		for(i = 1; i<13; i++) {
			tmp.add(i);
		}
		temp = tmp.toArray();
		month = new JComboBox(temp);
		month.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		month.setBounds(223, 195, 40, 20);
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
		anno.setBounds(273, 195, 60, 20);
		panel_2.add(anno);
		ListenerConferma.setJComboBox3(anno);
	}
}
