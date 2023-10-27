package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.UIManager;

import main.AreaTematica;
import main.Controller;
import main.GeneralActionListener;


public class NuovoCorsoWindow extends JFrame {
	
	private JTextField nomeCorsoTextField;
	private JTextArea descrizioneCorsoTextField;
	private JTextField tassoMinimoPresenzeTextField;
	private GeneralActionListener GAL;
	private JFrame current;
	private Controller controller;
	private AreaTematica areaTematica;
	private JTextField massimoIscrittiField;
	private ArrayList<AreaTematica> areeTematiche;
	private int shift = 171;
	private int max_click = 5;
	private int count_click = 0;
	private ArrayList<JComboBox> areeTematicheCombo = new ArrayList<JComboBox>();
	public NuovoCorsoWindow(Controller controller) {
		
		this.controller = controller;		
		init(controller);
	}

	private void init(Controller controller) {
		GAL = new GeneralActionListener(controller);		
		
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
		BackLabel.setIcon(new ImageIcon(NuovoCorsoWindow.class.getResource("/icon/arrow-88-48.png")));
		BackLabel.setBounds(0, 0, 50, 50);
		panel_1.add(BackLabel);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 255, 250));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		nomeCorsoTextField = new JTextField();
		nomeCorsoTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeCorsoTextField.setBounds(171, 11, 105, 20);
		panel_2.add(nomeCorsoTextField);
		nomeCorsoTextField.setColumns(10);
		
		
		JLabel nomeCorsoLabel = new JLabel("Corso");
		nomeCorsoLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		nomeCorsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeCorsoLabel.setBounds(10, 14, 151, 14);
		panel_2.add(nomeCorsoLabel);
		
		JLabel descrizioneCorsoLabel_1 = new JLabel("Descrizione");
		descrizioneCorsoLabel_1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		descrizioneCorsoLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		descrizioneCorsoLabel_1.setBounds(10, 39, 151, 14);
		panel_2.add(descrizioneCorsoLabel_1);
		
		descrizioneCorsoTextField = new JTextArea();
		descrizioneCorsoTextField.setBorder(UIManager.getBorder("TextField.border"));
		descrizioneCorsoTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		descrizioneCorsoTextField.setBounds(171, 36, 220, 70);
		panel_2.add(descrizioneCorsoTextField);
		descrizioneCorsoTextField.setColumns(10);
		
		descrizioneCorsoTextField.setLineWrap(true);
		descrizioneCorsoTextField.setWrapStyleWord(true);
		
		JLabel areaTematicaLabel = new JLabel("Area Tematica Principale");
		areaTematicaLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		areaTematicaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		areaTematicaLabel.setBounds(10, 195, 151, 14);
		panel_2.add(areaTematicaLabel);
		
		areeTematiche = controller.getOperatore().getAreeTematiche();
		
		
		JComboBox areaTematicaComboBox = new JComboBox();
		areeTematicheCombo.add(areaTematicaComboBox);
		areaTematicaComboBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		areaTematicaComboBox.setBounds(171, 191, 105, 22);
		panel_2.add(areaTematicaComboBox);
		int i = 0;
		for(AreaTematica a : areeTematiche) {
			areaTematicaComboBox.addItem(a.getNome());
			i++;
		}
		
		JLabel tassoMinimoPresenzeLabel = new JLabel("Tasso Minimo Presenze (%)");
		tassoMinimoPresenzeLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		tassoMinimoPresenzeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		tassoMinimoPresenzeLabel.setBounds(10, 128, 151, 14);
		panel_2.add(tassoMinimoPresenzeLabel);
		
		JLabel errorLbl = new JLabel("");
		errorLbl.setVerticalAlignment(SwingConstants.TOP);
		errorLbl.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		errorLbl.setForeground(Color.RED);
		errorLbl.setBounds(73, 274, 596, 58);
		panel_2.add(errorLbl);
		
		tassoMinimoPresenzeTextField = new JTextField();
		tassoMinimoPresenzeTextField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		tassoMinimoPresenzeTextField.setBounds(171, 125, 70, 20);
		panel_2.add(tassoMinimoPresenzeTextField);
		tassoMinimoPresenzeTextField.setColumns(10);
		tassoMinimoPresenzeTextField.addKeyListener(new KeyListener() {
			@Override
	        public void keyPressed(KeyEvent ke) {
				String value = tassoMinimoPresenzeTextField.getText();
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            	tassoMinimoPresenzeTextField.setEditable(true);
	            	errorLbl.setText("OK");
	            	errorLbl.setForeground(Color.green);
	            	
	            } else {
	            	tassoMinimoPresenzeTextField.setEditable(false);
	            	errorLbl.setText("Puoi inserire solo numeri nel campo 'tasso minimo di presenze'");
	            	errorLbl.setForeground(Color.red);
	            }
	         }
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
	      });
		
		JButton InvioButton = new JButton("Conferma");
		InvioButton.addActionListener(GAL);
		InvioButton.setActionCommand("NuovoCorso");
		
		GAL.setNome(nomeCorsoTextField);
		GAL.setDescrizione(descrizioneCorsoTextField);
		GAL.setTassoMinimoPresenze(tassoMinimoPresenzeTextField);
		GAL.setJComboBox(areaTematicaComboBox);
		
		InvioButton.setBounds(327, 240, 113, 23);
		panel_2.add(InvioButton);
		
		JLabel successlbl = new JLabel("");
		successlbl.setVerticalAlignment(SwingConstants.TOP);
		successlbl.setBounds(73, 288, 596, 70);
		panel_2.add(successlbl);
		GAL.setLabel(successlbl);
		
		massimoIscrittiField = new JTextField();
		massimoIscrittiField.addKeyListener(new KeyListener() {
			@Override
	        public void keyPressed(KeyEvent ke) {
				String value = tassoMinimoPresenzeTextField.getText();
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					massimoIscrittiField.setEditable(true);
	            	errorLbl.setText("OK");
	            	errorLbl.setForeground(Color.green);
	            	
	            } else {
	            	massimoIscrittiField.setEditable(false);
	            	errorLbl.setText("Puoi inserire solo numeri nel campo 'tasso minimo di presenze'");
	            	errorLbl.setForeground(Color.red);
	            }
	         }
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
	      });
		massimoIscrittiField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		massimoIscrittiField.setColumns(10);
		massimoIscrittiField.setBounds(171, 160, 70, 20);
		panel_2.add(massimoIscrittiField);
		
		GAL.setMassimoStudenti(massimoIscrittiField);
		
		JLabel massimoIscrittiLabel = new JLabel("Massimo numero di iscritti");
		massimoIscrittiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		massimoIscrittiLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		massimoIscrittiLabel.setBounds(10, 163, 151, 14);
		panel_2.add(massimoIscrittiLabel);
		
		JButton altreAree = new JButton("Altre Aree");
		altreAree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shift = shift + 150;
				count_click++;
				if(count_click >= max_click) {
					errorLbl.setText("Non puoi cliccare più di 5 volte");
				}else {
					JComboBox areaTematicaComboBox = new JComboBox();
					areeTematicheCombo.add(areaTematicaComboBox);
					areaTematicaComboBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
					areaTematicaComboBox.setBounds(shift, 191, 105, 22);
					panel_2.add(areaTematicaComboBox);
					int i = 0;
					for(AreaTematica a : areeTematiche) {
						areaTematicaComboBox.addItem(a.getNome());
						i++;
					}
				}

			}
		});
		GAL.setAreeTematiche(areeTematicheCombo);
		altreAree.setBounds(171, 221, 105, 20);
		panel_2.add(altreAree);
	}
}
