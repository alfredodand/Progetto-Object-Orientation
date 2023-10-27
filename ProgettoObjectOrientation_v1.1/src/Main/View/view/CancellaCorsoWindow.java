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

public class CancellaCorsoWindow extends JFrame {
	private Controller controller;
	private JFrame current;
	
	public CancellaCorsoWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		current = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 686, 512);
        GeneralActionListener cancellacorsolistener = new GeneralActionListener(controller);
		JPanel header = new JPanel();
		header.setBackground(new Color(65, 105, 225));
		header.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel backPanel = new JPanel();
		backPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openLoggedWindowFromAnotherWindow(current);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				backPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backPanel.setBackground(new Color(65, 105, 225));
			}
		});
		backPanel.setBackground(new Color(65, 105, 225));
		backPanel.setPreferredSize(new Dimension(50, 10));
		header.add(backPanel, BorderLayout.WEST);
		backPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CancellaCorsoWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		backPanel.add(lblNewLabel);
		
		JPanel centeredPanel = new JPanel();
		centeredPanel.setBackground(new Color(245, 255, 250));
		getContentPane().add(centeredPanel, BorderLayout.CENTER);
		centeredPanel.setLayout(null);
		
		JButton invioButton = new JButton("Invio");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.setBounds(173, 150, 105, 23);
		centeredPanel.add(invioButton);
		invioButton.addActionListener(cancellacorsolistener);
		invioButton.setActionCommand("cancellaCorso");
		
		JLabel nomeCorsoLbl = new JLabel("Nome corso");
		nomeCorsoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeCorsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeCorsoLbl.setBounds(31, 92, 131, 14);
		centeredPanel.add(nomeCorsoLbl);
		JLabel successLbl = new JLabel("");
		successLbl.setBounds(114, 267, 469, 14);
		centeredPanel.add(successLbl);
		
		cancellacorsolistener.setLabel(successLbl);
		JComboBox combobox = generateComboboxCorsi(centeredPanel);
		cancellacorsolistener.setJComboBox4(combobox);
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
		JComboBox corso = new JComboBox(temp);
		corso.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		corso.setSelectedIndex(0);
		corso.setBounds(173, 89, 157, 20);
		panel_1.add(corso);
		return corso;
	}
}
