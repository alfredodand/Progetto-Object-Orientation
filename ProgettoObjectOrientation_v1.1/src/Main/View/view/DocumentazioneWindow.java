package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.FlowLayout;

import main.Controller;

public class DocumentazioneWindow extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JFrame current = this;
	
	public DocumentazioneWindow(Controller controller) {
		this.controller = controller;
		init(controller);		
	}

	private void init(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 804, 512);
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
		lblNewLabel.setIcon(new ImageIcon(DocumentazioneWindow.class.getResource("/icon/arrow-88-48.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		backPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 255, 250));
		panel_1.setPreferredSize(new Dimension(10, 50));
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel documentazioneNewLabel = new JLabel("La documentazione al progetto di Oject Orientation \u00E8 possibile reperirla cliccando l'apposito pulsante");
		documentazioneNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		panel_1.add(documentazioneNewLabel);

		
		JLabel loading = new JLabel("");
		
		panel_1.add(loading);
		loading.setIcon(new ImageIcon(DocumentazioneWindow.class.getResource("/icon/Ajax-loader.gif")));
		loading.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		panel.add(panel_2, BorderLayout.CENTER);
		JButton docLink = new JButton("Clicca per andare alla documentazione");
		docLink.addMouseListener(new MouseAdapter() {
			//URI uri = new URI("http://java.sun.com");
			@Override
			public void mouseClicked(MouseEvent e) {
				String basePath = new File("").getAbsolutePath();
				String path = new File("src/Main/Documentazione_Object_Orientation.pdf").getAbsolutePath();
			    File myFile = new File(path);
				   if (Desktop.isDesktopSupported()) {
					      try {
							Desktop.getDesktop().open(myFile);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					      loading.setVisible(true);
					      slp(loading);
					}
			}
		});
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		docLink.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		panel_2.add(docLink);
	}
	public void slp(JLabel label) {
	    new Thread(() -> {
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        SwingUtilities.invokeLater(() -> label.setVisible(false));
	    }).start();
	}
}
