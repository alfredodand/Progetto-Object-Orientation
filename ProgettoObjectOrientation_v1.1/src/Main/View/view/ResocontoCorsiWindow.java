package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

import main.Controller;
import main.GeneralActionListener;
import main.Tabella;


public class ResocontoCorsiWindow extends JFrame {
	private Controller controller;
	private JPanel contentPane;
	private Tabella tableVisualizerRequest;
	private JTable table;
	private JFrame current;
	
	public ResocontoCorsiWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}

	private void init(Controller controller) {
		GeneralActionListener MediaS = new GeneralActionListener(controller);
		GeneralActionListener MinimoS = new GeneralActionListener(controller);
		GeneralActionListener MassimoS = new GeneralActionListener(controller);
		GeneralActionListener PercentualeMediaS = new GeneralActionListener(controller);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 477);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Panel panel = new Panel();
		panel.setBackground(new Color(245, 255, 250));
		contentPane.add(panel);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 255, 250));
		
		JLabel operazioneLabel = new JLabel("Scegli l'operazione");
		operazioneLabel.setBounds(35, 21, 155, 19);
		operazioneLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		operazioneLabel.setPreferredSize(new Dimension(0, 14));
		panel_3.setLayout(null);
		operazioneLabel.setToolTipText("");
		panel_3.add(operazioneLabel);
		
		JButton visualizzaNumeroMedioStudenti = new JButton("Numero medio di studenti per lezione      ");
		visualizzaNumeroMedioStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				visualizzaNumeroMedioStudenti.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				visualizzaNumeroMedioStudenti.setBackground(new Color(65, 105, 225));
			}
		});
		visualizzaNumeroMedioStudenti.setBounds(35, 44, 249, 23);
		visualizzaNumeroMedioStudenti.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		visualizzaNumeroMedioStudenti.setForeground(new Color(255, 255, 255));
		visualizzaNumeroMedioStudenti.setBorderPainted(false);
		visualizzaNumeroMedioStudenti.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.add(visualizzaNumeroMedioStudenti);
		visualizzaNumeroMedioStudenti.setPreferredSize(new Dimension(80, 50));
		visualizzaNumeroMedioStudenti.setMinimumSize(new Dimension(100, 23));
		visualizzaNumeroMedioStudenti.setHorizontalTextPosition(SwingConstants.LEFT);
		visualizzaNumeroMedioStudenti.setBackground(new Color(65, 105, 225));
		visualizzaNumeroMedioStudenti.addActionListener(MediaS);
		visualizzaNumeroMedioStudenti.setActionCommand("Visualizza1");
		
		JButton visualizzaNumeroMinimoStudenti = new JButton("Numero minimo di studenti per lezione     ");
		visualizzaNumeroMinimoStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				visualizzaNumeroMinimoStudenti.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				visualizzaNumeroMinimoStudenti.setBackground(new Color(65, 105, 225));
			}
		});
		visualizzaNumeroMinimoStudenti.setBounds(35, 78, 249, 23);
		visualizzaNumeroMinimoStudenti.setBorderPainted(false);
		visualizzaNumeroMinimoStudenti.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		visualizzaNumeroMinimoStudenti.setForeground(new Color(255, 255, 255));
		panel_3.add(visualizzaNumeroMinimoStudenti);
		visualizzaNumeroMinimoStudenti.setPreferredSize(new Dimension(80, 50));
		visualizzaNumeroMinimoStudenti.setMinimumSize(new Dimension(100, 23));
		visualizzaNumeroMinimoStudenti.setHorizontalAlignment(SwingConstants.LEFT);
		visualizzaNumeroMinimoStudenti.setBackground(new Color(65, 105, 225));
		visualizzaNumeroMinimoStudenti.addActionListener(MinimoS);
		visualizzaNumeroMinimoStudenti.setActionCommand("Visualizza2");
		
		JButton visualizzaNumeroMassimoStudenti = new JButton("Numero massimo di studenti per lezione  ");
		visualizzaNumeroMassimoStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				visualizzaNumeroMassimoStudenti.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				visualizzaNumeroMassimoStudenti.setBackground(new Color(65, 105, 225));
			}
		});
		visualizzaNumeroMassimoStudenti.setBounds(35, 112, 249, 23);
		visualizzaNumeroMassimoStudenti.setBorderPainted(false);
		visualizzaNumeroMassimoStudenti.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		visualizzaNumeroMassimoStudenti.setForeground(new Color(255, 255, 255));
		panel_3.add(visualizzaNumeroMassimoStudenti);
		visualizzaNumeroMassimoStudenti.setPreferredSize(new Dimension(80, 50));
		visualizzaNumeroMassimoStudenti.setMinimumSize(new Dimension(100, 23));
		visualizzaNumeroMassimoStudenti.setHorizontalAlignment(SwingConstants.LEFT);
		visualizzaNumeroMassimoStudenti.setBackground(new Color(65, 105, 225));
		visualizzaNumeroMassimoStudenti.addActionListener(MassimoS);
		visualizzaNumeroMassimoStudenti.setActionCommand("Visualizza3");
		
		JPanel tableCorsiPanel = new JPanel();
		tableCorsiPanel.setBackground(new Color(245, 255, 250));
		JScrollPane scrollablePane = new JScrollPane(tableCorsiPanel);
		scrollablePane.setBorder(null);
		
		tableVisualizerRequest = new Tabella(panel, tableCorsiPanel);
		GridBagLayout gbl_tableCorsiPanel = new GridBagLayout();
		gbl_tableCorsiPanel.columnWidths = new int[]{87, 103, 72, 0};
		gbl_tableCorsiPanel.rowHeights = new int[]{1, 0, 0};
		gbl_tableCorsiPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_tableCorsiPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		tableCorsiPanel.setLayout(gbl_tableCorsiPanel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		panel.add(panel_3);
		
		JButton visualizzaPercentualeMediaStudenti = new JButton("Percentuale media di studenti a lezione   ");
		visualizzaPercentualeMediaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				visualizzaPercentualeMediaStudenti.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				visualizzaPercentualeMediaStudenti.setBackground(new Color(65, 105, 225));
			}
		});
		visualizzaPercentualeMediaStudenti.setBounds(35, 146, 249, 23);
		visualizzaPercentualeMediaStudenti.setBorderPainted(false);
		visualizzaPercentualeMediaStudenti.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		visualizzaPercentualeMediaStudenti.setForeground(new Color(255, 255, 255));
		panel_3.add(visualizzaPercentualeMediaStudenti);
		visualizzaPercentualeMediaStudenti.setPreferredSize(new Dimension(80, 50));
		visualizzaPercentualeMediaStudenti.setMinimumSize(new Dimension(100, 23));
		visualizzaPercentualeMediaStudenti.setHorizontalAlignment(SwingConstants.LEFT);
		visualizzaPercentualeMediaStudenti.setBackground(new Color(65, 105, 225));
		visualizzaPercentualeMediaStudenti.addActionListener(PercentualeMediaS);
		visualizzaPercentualeMediaStudenti.setActionCommand("Visualizza4");
		panel.add(scrollablePane);
		
		MediaS.setTable(tableVisualizerRequest);
		MinimoS.setTable(tableVisualizerRequest);
		MassimoS.setTable(tableVisualizerRequest);
		PercentualeMediaS.setTable(tableVisualizerRequest);
		
		JLabel tableTitle = new JLabel("<html><p style=\"margin-top:10px\">Visualizza la tua tabella qui</p></html>");
		GridBagConstraints gbc_tableTitle = new GridBagConstraints();
		gbc_tableTitle.insets = new Insets(0, 0, 5, 5);
		gbc_tableTitle.gridx = 1;
		gbc_tableTitle.gridy = 0;
		tableCorsiPanel.add(tableTitle, gbc_tableTitle);
		
		MediaS.setLabel(tableTitle);
		MinimoS.setLabel(tableTitle);
		MassimoS.setLabel(tableTitle);
		PercentualeMediaS.setLabel(tableTitle);
		
		table = tableVisualizerRequest.getTabella();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		tableCorsiPanel.add(table, gbc_table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 50));
		panel_1.setBackground(new Color(65, 105, 225));
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setLayout(null);
		homeButtonPanel.setPreferredSize(new Dimension(50, 10));
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		panel_1.add(homeButtonPanel, BorderLayout.WEST);
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
		homebutton.setIcon(new ImageIcon(ResocontoCorsiWindow.class.getResource("/icon/arrow-88-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);
	}
}
