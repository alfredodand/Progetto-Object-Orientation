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
import main.GeneralActionListener;
import main.Insegnanti;

public class CancellaProfessoreWindow extends JFrame {
	private Controller controller;
	private JFrame current;
	
	public CancellaProfessoreWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {

		GeneralActionListener cancellaProfListener = new GeneralActionListener(controller);
		cancellaProfListener = new GeneralActionListener(controller);
		
		current = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 686, 512);
        
		JPanel header = new JPanel();
		header.setBackground(new Color(65, 105, 225));
		header.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
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
		header.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CancellaProfessoreWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		panel_1.add(lblNewLabel);
		
		JPanel centeredPanel = new JPanel();
		centeredPanel.setBackground(new Color(245, 255, 250));
		getContentPane().add(centeredPanel, BorderLayout.CENTER);
		centeredPanel.setLayout(null);
		
		JButton invioButton = new JButton("Invio");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.setBounds(171, 150, 105, 23);
		centeredPanel.add(invioButton);
		invioButton.addActionListener(cancellaProfListener);
		invioButton.setActionCommand("CancellaProf");
		
		JLabel insegnanteLbl = new JLabel("Insegnante");
		insegnanteLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		insegnanteLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		insegnanteLbl.setBounds(31, 92, 131, 14);
		centeredPanel.add(insegnanteLbl);
		int i = 0;
		JComboBox insegnanteCombobox =  generateComboboxInsegnanti(centeredPanel, i);
		cancellaProfListener.setJComboBox5(insegnanteCombobox);
		
		JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(171, 203, 315, 14);
		centeredPanel.add(errorLbl);
		cancellaProfListener.setLabel(errorLbl);
	}
	
	private JComboBox generateComboboxInsegnanti(JPanel panel_1, int i) {
		ArrayList<Insegnanti> insegnanti = new ArrayList<Insegnanti>();
		insegnanti = controller.getOperatore().getProf();
		ArrayList<String> nomiInsegnanti = new ArrayList<String>();
		Object[] temp;
		
		for(Insegnanti c : insegnanti) {
			String nome = insegnanti.get(i).getNome().toString();
			String cognome = insegnanti.get(i).getCognome().toString();
			int id = insegnanti.get(i).getId();
			
			nomiInsegnanti.add(nome + " " + cognome + " " + id);
			i++;
		}
		
		temp = nomiInsegnanti.toArray();
		JComboBox insegnanteComboBox = new JComboBox(temp);
		insegnanteComboBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		insegnanteComboBox.setSelectedIndex(0);
		insegnanteComboBox.setBounds(171, 89, 157, 20);
		panel_1.add(insegnanteComboBox);
		return insegnanteComboBox;
	}
}
