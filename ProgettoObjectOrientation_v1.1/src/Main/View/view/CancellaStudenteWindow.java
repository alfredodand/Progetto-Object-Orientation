package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;
import main.GeneralActionListener;

public class CancellaStudenteWindow extends JFrame {
	private JTextField matricolaField;
	private Controller controller;
	private JFrame current;
	
	public CancellaStudenteWindow(Controller controller) {
		GeneralActionListener cancellaStudenteListener = new GeneralActionListener(controller); 
		this.controller = controller;
		init(controller, cancellaStudenteListener);
	}

	private void init(Controller controller, GeneralActionListener cancellaStudenteListener) {
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
		lblNewLabel.setIcon(new ImageIcon(CancellaStudenteWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		matricolaField = new JTextField();
		matricolaField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		matricolaField.setColumns(10);
		matricolaField.setBounds(173, 90, 153, 20);
		panel_2.add(matricolaField);
		
		JButton invioButton = new JButton("Invio");
		invioButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		invioButton.setBounds(173, 150, 105, 23);
		panel_2.add(invioButton);
		invioButton.addActionListener(cancellaStudenteListener);
		invioButton.setActionCommand("cancellaStudente");
		cancellaStudenteListener.setMatricola(matricolaField);
		
		
		JLabel matricolaLbl = new JLabel("Matricola");
		matricolaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		matricolaLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		matricolaLbl.setBounds(31, 92, 131, 14);
		panel_2.add(matricolaLbl);
		
		JLabel isdeletedlbl = new JLabel("");
		isdeletedlbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		isdeletedlbl.setBounds(89, 214, 385, 20);
		panel_2.add(isdeletedlbl);
		
		cancellaStudenteListener.setLabel(isdeletedlbl);
	}
}
