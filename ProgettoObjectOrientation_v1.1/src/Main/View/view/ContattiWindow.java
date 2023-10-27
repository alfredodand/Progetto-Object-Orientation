package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class ContattiWindow extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JFrame current = this;
	
	public ContattiWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 686, 512);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(10, 50));
		header.setBackground(new Color(65, 105, 225));
		contentPane.add(header, BorderLayout.NORTH);
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
		backPanel.setLayout(null);
		backPanel.setPreferredSize(new Dimension(50, 10));
		backPanel.setBackground(new Color(65, 105, 225));
		header.add(backPanel, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ContattiWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		backPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel contattiNewLabel = new JLabel("<html>\r\n<p align=\"center\"><b>Per assistenza contattare :</b> <br>cr.carrella@studenti.unina.it<br>\r\noppure<br>\r\nalf.dandrea@studenti.unina.it<br></p>\r\n</html>");
		contattiNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		contattiNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(contattiNewLabel, BorderLayout.CENTER);
	}

}
