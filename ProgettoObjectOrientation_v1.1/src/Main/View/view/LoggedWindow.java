package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

import main.Controller;

public class LoggedWindow extends JFrame {
	Controller controller;
	private JPanel contentPane;
	boolean clicked = false;
	
	public LoggedWindow(Controller controller) {
		this.controller = controller;
		init(controller);
	}
	public JFrame getFrame() {
		return this;
	}
	public void init(Controller controller) {
		setMinimumSize(new Dimension(216, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel containerMenu = new JPanel();
		containerMenu.setBackground(new Color(65, 105, 225));
		containerMenu.setPreferredSize(new Dimension(100, 10));
		panel.add(containerMenu, BorderLayout.WEST);
		containerMenu.setLayout(null);
		
		JPanel homeButtonPanel = new JPanel();
		homeButtonPanel.setBackground(new Color(65, 105, 225));
		homeButtonPanel.setBounds(0, 0, 50, 50);
		containerMenu.add(homeButtonPanel);
		homeButtonPanel.setLayout(null);
		
		JLabel homebutton = new JLabel("");
		homebutton.addMouseListener(new MouseAdapter() {
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
				controller.openHomeWindow();
			}
		});
		homebutton.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/house-48.png")));
		homebutton.setPreferredSize(new Dimension(50, 50));
		homebutton.setHorizontalAlignment(SwingConstants.CENTER);
		homebutton.setBounds(0, 0, 50, 50);
		homeButtonPanel.add(homebutton);
		
		JPanel menu = new JPanel();
		menu.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menu.setBackground(new Color(65, 105, 225));
		menu.setPreferredSize(new Dimension(0, 100));
		contentPane.add(menu, BorderLayout.WEST);
		menu.setLayout(null);
		
		
		JPanel contattiPanel = new JPanel();
		contattiPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				contattiPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contattiPanel.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openContattiWindow();
			}
		});
		contattiPanel.setBorder(new MatteBorder(0, 0, 1, 1, (Color) new Color(0, 0, 0)));
		contattiPanel.setBackground(new Color(65, 105, 225));
		contattiPanel.setBounds(0, 0, 200, 50);
		menu.add(contattiPanel);
		contattiPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Contatti");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(10, 0, 135, 50);
		contattiPanel.add(lblNewLabel_2_1);
		
		JPanel profiloPanel = new JPanel();
		profiloPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profiloPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				profiloPanel.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openProfiloWindow();
			}
		});
		profiloPanel.setBorder(new MatteBorder(0, 0, 1, 1, (Color) new Color(0, 0, 0)));
		profiloPanel.setBackground(new Color(65, 105, 225));
		profiloPanel.setBounds(0, 50, 200, 50);
		menu.add(profiloPanel);
		profiloPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Profilo");
		lblNewLabel_2_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblNewLabel_2_1_1_1.setBounds(10, 0, 135, 50);
		profiloPanel.add(lblNewLabel_2_1_1_1);
		
		JPanel documentazionePanel = new JPanel();
		documentazionePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				documentazionePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				documentazionePanel.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openDocumentazioneWindow();
			}
		});
		documentazionePanel.setBorder(new MatteBorder(0, 0, 1, 1, (Color) new Color(0, 0, 0)));
		documentazionePanel.setBackground(new Color(65, 105, 225));
		documentazionePanel.setBounds(0, 100, 200, 50);
		menu.add(documentazionePanel);
		documentazionePanel.setLayout(null);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Documentazione");
		lblNewLabel_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		lblNewLabel_2_1_1.setBounds(10, 0, 135, 50);
		documentazionePanel.add(lblNewLabel_2_1_1);
		
		JPanel showMenuButtonPanel = new JPanel();
		showMenuButtonPanel.setBackground(new Color(65, 105, 225));
		showMenuButtonPanel.setBounds(50, 0, 50, 50);
		containerMenu.add(showMenuButtonPanel);
		showMenuButtonPanel.setLayout(null);
		
		JLabel showmenu = new JLabel("");
		showmenu.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				showMenuButtonPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				showMenuButtonPanel.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(clicked == false) {
					menu.setPreferredSize(new Dimension(200, 0));
					clicked = true;
				}else {
					menu.setPreferredSize(new Dimension(0, 0));
					clicked = false;
				}
				
				SwingUtilities.updateComponentTreeUI(menu);
			}
		});
		showmenu.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/list-view-32.png")));
		showmenu.setPreferredSize(new Dimension(50, 50));
		showmenu.setHorizontalAlignment(SwingConstants.CENTER);
		showmenu.setBounds(0, 0, 50, 50);
		showMenuButtonPanel.add(showmenu);

		JPanel dashboard = new JPanel();
		dashboard.setBackground(new Color(245, 255, 250));
		contentPane.add(dashboard, BorderLayout.CENTER);
		dashboard.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
		JPanel immatricolaPanel = new JPanel( );
		immatricolaPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		immatricolaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				immatricolaPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				immatricolaPanel.setBackground(new Color(65, 105, 225));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openImmatricolaWindow();
			}
		});
		immatricolaPanel.setPreferredSize(new Dimension(90, 90));
		immatricolaPanel.setSize(new Dimension(90, 90));
		immatricolaPanel.setOpaque(true);
		immatricolaPanel.setBackground(new Color(65, 105, 225));

		dashboard.add(immatricolaPanel);
		immatricolaPanel.setLayout(null);
		
		JLabel iconImmatricola = new JLabel("");
		iconImmatricola.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/add-user-32.png")));
		iconImmatricola.setBounds(30, 15, 32, 32);
		immatricolaPanel.add(iconImmatricola);
		
		JLabel immatricolaLbl = new JLabel("Immatricola");
		immatricolaLbl.setForeground(Color.WHITE);
		immatricolaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		immatricolaLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		immatricolaLbl.setBounds(10, 45, 70, 26);
		immatricolaPanel.add(immatricolaLbl);
		
		JPanel statisticheCorsiPanel = new JPanel( );
		statisticheCorsiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		statisticheCorsiPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openResocontoCorsiWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				statisticheCorsiPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				statisticheCorsiPanel.setBackground(new Color(65, 105, 225));
			}
		});
		statisticheCorsiPanel.setPreferredSize(new Dimension(90, 90));
		statisticheCorsiPanel.setSize(new Dimension(90, 90));
		statisticheCorsiPanel.setOpaque(true);
		statisticheCorsiPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(statisticheCorsiPanel);
		statisticheCorsiPanel.setLayout(null);
		
		JLabel iconStatisticheCorsi = new JLabel("New label");
		iconStatisticheCorsi.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/chart-2-32.png")));
		iconStatisticheCorsi.setBounds(30, 15, 32, 32);
		statisticheCorsiPanel.add(iconStatisticheCorsi);
		
		JLabel lblStatisticheCorsi = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Statistiche dei corsi</p><br>\r\n</html>");
		lblStatisticheCorsi.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatisticheCorsi.setForeground(Color.WHITE);
		lblStatisticheCorsi.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblStatisticheCorsi.setBounds(10, 45, 70, 34);
		statisticheCorsiPanel.add(lblStatisticheCorsi);
		
		JPanel iscriviAlCorsoPanel = new JPanel( );
		iscriviAlCorsoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		iscriviAlCorsoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openIscriviAlCorsoWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				iscriviAlCorsoPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iscriviAlCorsoPanel.setBackground(new Color(65, 105, 225));
			}
		});
		iscriviAlCorsoPanel.setPreferredSize(new Dimension(90, 90));
		iscriviAlCorsoPanel.setSize(new Dimension(90, 90));
		iscriviAlCorsoPanel.setOpaque(true);
		iscriviAlCorsoPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(iscriviAlCorsoPanel);
		iscriviAlCorsoPanel.setLayout(null);
		
		JLabel iconIscriviAlCorso = new JLabel("");
		iconIscriviAlCorso.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/edit-6-32.png")));
		iconIscriviAlCorso.setBounds(30, 15, 32, 32);
		iscriviAlCorsoPanel.add(iconIscriviAlCorso);
		
		JLabel iscriviAlCorsoLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Iscrivi al corso</p><br>\r\n</html>");
		iscriviAlCorsoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		iscriviAlCorsoLbl.setForeground(Color.WHITE);
		iscriviAlCorsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		iscriviAlCorsoLbl.setBounds(10, 45, 70, 34);
		iscriviAlCorsoPanel.add(iscriviAlCorsoLbl);
		
		JPanel riepilogoStudentePanel = new JPanel( );
		riepilogoStudentePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		riepilogoStudentePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openStatisticheStudenteWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				riepilogoStudentePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				riepilogoStudentePanel.setBackground(new Color(65, 105, 225));
			}
		});
		riepilogoStudentePanel.setPreferredSize(new Dimension(90, 90));
		riepilogoStudentePanel.setSize(new Dimension(90, 90));
		riepilogoStudentePanel.setOpaque(true);
		riepilogoStudentePanel.setBackground(new Color(65, 105, 225));
		dashboard.add(riepilogoStudentePanel);
		riepilogoStudentePanel.setLayout(null);
		
		JLabel iconRiepilogoStudentePanel = new JLabel("");
		iconRiepilogoStudentePanel.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/edit-11-32.png")));
		iconRiepilogoStudentePanel.setBounds(30, 15, 32, 32);
		riepilogoStudentePanel.add(iconRiepilogoStudentePanel);
		
		JLabel riepilogoStudentePanelLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Riepilogo studente</p><br>\r\n</html>");
		riepilogoStudentePanelLbl.setForeground(Color.WHITE);
		riepilogoStudentePanelLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		riepilogoStudentePanelLbl.setBounds(10, 45, 70, 34);
		riepilogoStudentePanel.add(riepilogoStudentePanelLbl);
		
		JPanel assegnaCorsoPanel = new JPanel( );
		assegnaCorsoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		assegnaCorsoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openAssegnaCorsoWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				assegnaCorsoPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				assegnaCorsoPanel.setBackground(new Color(65, 105, 225));
			}
		});
		assegnaCorsoPanel.setPreferredSize(new Dimension(90, 90));
		assegnaCorsoPanel.setSize(new Dimension(90, 90));
		assegnaCorsoPanel.setOpaque(true);
		assegnaCorsoPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(assegnaCorsoPanel);
		assegnaCorsoPanel.setLayout(null);
		
		JLabel assegnaCorsoIcon = new JLabel("");
		assegnaCorsoIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/kindpng_7561211 (1).png")));
		assegnaCorsoIcon.setBounds(30, 15, 32, 32);
		assegnaCorsoPanel.add(assegnaCorsoIcon);
		
		JLabel assegnaCorsolbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Assegna corso</p><br>\r\n</html>");
		assegnaCorsolbl.setForeground(Color.WHITE);
		assegnaCorsolbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		assegnaCorsolbl.setBounds(10, 45, 70, 34);
		assegnaCorsoPanel.add(assegnaCorsolbl);
		
		JPanel nuovoCorsoPanel = new JPanel();
		nuovoCorsoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nuovoCorsoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openNuovoCorsoWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				nuovoCorsoPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nuovoCorsoPanel.setBackground(new Color(65, 105, 225));
			}
		});
		nuovoCorsoPanel.setPreferredSize(new Dimension(90, 90));
		nuovoCorsoPanel.setSize(new Dimension(90, 90));
		nuovoCorsoPanel.setOpaque(true);
		nuovoCorsoPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(nuovoCorsoPanel);
		nuovoCorsoPanel.setLayout(null);
		
		JLabel nuovoCorsoIcon = new JLabel("");
		nuovoCorsoIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/image.png")));
		nuovoCorsoIcon.setBounds(30, 15, 32, 32);
		nuovoCorsoPanel.add(nuovoCorsoIcon);
		
		JLabel nuovoCorsoLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Crea nuovo corso</p><br>\r\n</html>");
		nuovoCorsoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nuovoCorsoLbl.setForeground(Color.WHITE);
		nuovoCorsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		nuovoCorsoLbl.setBounds(10, 45, 70, 34);
		nuovoCorsoPanel.add(nuovoCorsoLbl);
		
		JPanel statisticheStudentiPanel = new JPanel();
		statisticheStudentiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		statisticheStudentiPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openStatisticheStudenti();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				statisticheStudentiPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				statisticheStudentiPanel.setBackground(new Color(65, 105, 225));
			}
		});
		statisticheStudentiPanel.setPreferredSize(new Dimension(90, 90));
		statisticheStudentiPanel.setSize(new Dimension(90, 90));
		statisticheStudentiPanel.setOpaque(true);
		statisticheStudentiPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(statisticheStudentiPanel);
		statisticheStudentiPanel.setLayout(null);
		
		JLabel statisticheStudentiIcon = new JLabel("");
		statisticheStudentiIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/teacher-32 (1).png")));
		statisticheStudentiIcon.setBounds(30, 15, 32, 32);
		statisticheStudentiPanel.add(statisticheStudentiIcon);
		
		JLabel statisticheStudentiLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Statistiche studenti</p><br>\r\n</html>");
		statisticheStudentiLbl.setHorizontalAlignment(SwingConstants.CENTER);
		statisticheStudentiLbl.setForeground(Color.WHITE);
		statisticheStudentiLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		statisticheStudentiLbl.setBounds(10, 45, 70, 34);
		statisticheStudentiPanel.add(statisticheStudentiLbl);
		
		JPanel AggiungiLezionePanel = new JPanel();
		AggiungiLezionePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AggiungiLezionePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openAggiungiLezioneWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AggiungiLezionePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AggiungiLezionePanel.setBackground(new Color(65, 105, 225));
			}
		});
		AggiungiLezionePanel.setPreferredSize(new Dimension(90, 90));
		AggiungiLezionePanel.setSize(new Dimension(90, 90));
		AggiungiLezionePanel.setOpaque(true);
		AggiungiLezionePanel.setBackground(new Color(65, 105, 225));
		dashboard.add(AggiungiLezionePanel);
		AggiungiLezionePanel.setLayout(null);
		
		JLabel AggiungiLezioneIcon = new JLabel("");
		AggiungiLezioneIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/upload-32.png")));
		AggiungiLezioneIcon.setBounds(30, 11, 32, 32);
		AggiungiLezionePanel.add(AggiungiLezioneIcon);
		
		JLabel AggiungiLezioneLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Aggiungi Lezione</p><br>\r\n</html>");
		AggiungiLezioneLbl.setHorizontalAlignment(SwingConstants.CENTER);
		AggiungiLezioneLbl.setForeground(Color.WHITE);
		AggiungiLezioneLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		AggiungiLezioneLbl.setBounds(10, 41, 70, 34);
		AggiungiLezionePanel.add(AggiungiLezioneLbl);
		
		JPanel AggiungiProfessorePanel = new JPanel();
		AggiungiProfessorePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openAggiungiProfessoreWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AggiungiProfessorePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AggiungiProfessorePanel.setBackground(new Color(65, 105, 225));
			}
		});
		AggiungiProfessorePanel.setLayout(null);
		AggiungiProfessorePanel.setSize(new Dimension(90, 90));
		AggiungiProfessorePanel.setPreferredSize(new Dimension(90, 90));
		AggiungiProfessorePanel.setOpaque(true);
		AggiungiProfessorePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AggiungiProfessorePanel.setBackground(new Color(65, 105, 225));
		dashboard.add(AggiungiProfessorePanel);
		
		JLabel AggiungiProfessoreIcon = new JLabel("");
		AggiungiProfessoreIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/plus-3-32.png")));
		AggiungiProfessoreIcon.setBounds(30, 11, 32, 32);
		AggiungiProfessorePanel.add(AggiungiProfessoreIcon);
		
		JLabel AggiungiProfessoreLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Aggiungi professore</p><br>\r\n</html>");
		AggiungiProfessoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		AggiungiProfessoreLbl.setForeground(Color.WHITE);
		AggiungiProfessoreLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		AggiungiProfessoreLbl.setBounds(10, 41, 70, 34);
		AggiungiProfessorePanel.add(AggiungiProfessoreLbl);
		
		JPanel CancellaStudentePanel = new JPanel();
		CancellaStudentePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openCancellaStudenteWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CancellaStudentePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CancellaStudentePanel.setBackground(new Color(65, 105, 225));
			}
		});
		
		JPanel VisualizzaCorsiPanel = new JPanel();
		VisualizzaCorsiPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openVisualizzaCorsiWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				VisualizzaCorsiPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				VisualizzaCorsiPanel.setBackground(new Color(65, 105, 225));
			}
		});
		VisualizzaCorsiPanel.setLayout(null);
		VisualizzaCorsiPanel.setSize(new Dimension(90, 90));
		VisualizzaCorsiPanel.setPreferredSize(new Dimension(90, 90));
		VisualizzaCorsiPanel.setOpaque(true);
		VisualizzaCorsiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		VisualizzaCorsiPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(VisualizzaCorsiPanel);
		
		JLabel VisualizzaCorsiIcon = new JLabel("");
		VisualizzaCorsiIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/website-optimization-2-32.png")));
		VisualizzaCorsiIcon.setBounds(30, 11, 32, 32);
		VisualizzaCorsiPanel.add(VisualizzaCorsiIcon);
		
		JLabel VisualizzaCorsiLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Visualizza Corsi</p><br>\r\n</html>");
		VisualizzaCorsiLbl.setHorizontalAlignment(SwingConstants.CENTER);
		VisualizzaCorsiLbl.setForeground(Color.WHITE);
		VisualizzaCorsiLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		VisualizzaCorsiLbl.setBounds(10, 41, 70, 34);
		VisualizzaCorsiPanel.add(VisualizzaCorsiLbl);
		
		JPanel AggiungiPresenzePanel = new JPanel();
		AggiungiPresenzePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openAggiungiPresenzeWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AggiungiPresenzePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AggiungiPresenzePanel.setBackground(new Color(65, 105, 225));
			}
		});
		
		JPanel AggiungiAreaTematicaPanel = new JPanel();
		AggiungiAreaTematicaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openAggiungiAreaTematica();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AggiungiAreaTematicaPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AggiungiAreaTematicaPanel.setBackground(new Color(65, 105, 225));
			}
		});
		AggiungiAreaTematicaPanel.setLayout(null);
		AggiungiAreaTematicaPanel.setSize(new Dimension(90, 90));
		AggiungiAreaTematicaPanel.setPreferredSize(new Dimension(90, 90));
		AggiungiAreaTematicaPanel.setOpaque(true);
		AggiungiAreaTematicaPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AggiungiAreaTematicaPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(AggiungiAreaTematicaPanel);
		
		JLabel AggiungiAreaTematicaLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Aggiungi Area Tematica</p><br>\r\n</html>");
		AggiungiAreaTematicaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		AggiungiAreaTematicaLbl.setForeground(Color.WHITE);
		AggiungiAreaTematicaLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		AggiungiAreaTematicaLbl.setBounds(5, 41, 80, 38);
		AggiungiAreaTematicaPanel.add(AggiungiAreaTematicaLbl);
		
		JLabel AggiungiPresenzeIcon_2 = new JLabel("");
		AggiungiPresenzeIcon_2.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/categorize-32.png")));
		AggiungiPresenzeIcon_2.setBounds(29, 11, 32, 32);
		AggiungiAreaTematicaPanel.add(AggiungiPresenzeIcon_2);
		
		JPanel statisticheLezioniLabel = new JPanel();
		statisticheLezioniLabel.setLayout(null);
		statisticheLezioniLabel.setSize(new Dimension(90, 90));
		statisticheLezioniLabel.setPreferredSize(new Dimension(90, 90));
		statisticheLezioniLabel.setOpaque(true);
		statisticheLezioniLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		statisticheLezioniLabel.setBackground(new Color(65, 105, 225));
		dashboard.add(statisticheLezioniLabel);
		
		statisticheLezioniLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openStatisticheLezioniWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				statisticheLezioniLabel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				statisticheLezioniLabel.setBackground(new Color(65, 105, 225));
			}
		});
		
		
		JLabel statisticheLezioniLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Statistiche Lezioni</p><br>\r\n</html>");
		statisticheLezioniLbl.setHorizontalAlignment(SwingConstants.CENTER);
		statisticheLezioniLbl.setForeground(Color.WHITE);
		statisticheLezioniLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		statisticheLezioniLbl.setBounds(5, 41, 80, 38);
		statisticheLezioniLabel.add(statisticheLezioniLbl);
		
		JLabel AggiungiPresenzeIcon_1 = new JLabel("");
		AggiungiPresenzeIcon_1.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/check-mark-11-32.png")));
		AggiungiPresenzeIcon_1.setBounds(29, 11, 32, 32);
		statisticheLezioniLabel.add(AggiungiPresenzeIcon_1);
		AggiungiPresenzePanel.setLayout(null);
		AggiungiPresenzePanel.setSize(new Dimension(90, 90));
		AggiungiPresenzePanel.setPreferredSize(new Dimension(90, 90));
		AggiungiPresenzePanel.setOpaque(true);
		AggiungiPresenzePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AggiungiPresenzePanel.setBackground(new Color(65, 105, 225));
		dashboard.add(AggiungiPresenzePanel);
		
		JLabel AggiungiPresenzeIcon = new JLabel("");
		AggiungiPresenzeIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/add-list-32.png")));
		AggiungiPresenzeIcon.setBounds(30, 11, 32, 32);
		AggiungiPresenzePanel.add(AggiungiPresenzeIcon);
		
		JLabel AggiungiPresenzeLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Aggiungi Presenze</p><br>\r\n</html>");
		AggiungiPresenzeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		AggiungiPresenzeLbl.setForeground(Color.WHITE);
		AggiungiPresenzeLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		AggiungiPresenzeLbl.setBounds(10, 41, 70, 34);
		AggiungiPresenzePanel.add(AggiungiPresenzeLbl);
		CancellaStudentePanel.setLayout(null);
		CancellaStudentePanel.setSize(new Dimension(90, 90));
		CancellaStudentePanel.setPreferredSize(new Dimension(90, 90));
		CancellaStudentePanel.setOpaque(true);
		CancellaStudentePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CancellaStudentePanel.setBackground(new Color(65, 105, 225));
		dashboard.add(CancellaStudentePanel);
		
		JLabel CancellaStudenteIcon = new JLabel("");
		CancellaStudenteIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/x-mark-3-32.png")));
		CancellaStudenteIcon.setBounds(30, 11, 32, 32);
		CancellaStudentePanel.add(CancellaStudenteIcon);
		
		JLabel CancellaStudenteLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Cancella studente</p><br>\r\n</html>");
		CancellaStudenteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		CancellaStudenteLbl.setForeground(Color.WHITE);
		CancellaStudenteLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CancellaStudenteLbl.setBounds(10, 41, 70, 34);
		CancellaStudentePanel.add(CancellaStudenteLbl);
		
		JPanel CancellaCorsoPanel = new JPanel();
		CancellaCorsoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openCancellaCorsoWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CancellaCorsoPanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CancellaCorsoPanel.setBackground(new Color(65, 105, 225));
			}
		});
		CancellaCorsoPanel.setLayout(null);
		CancellaCorsoPanel.setSize(new Dimension(90, 90));
		CancellaCorsoPanel.setPreferredSize(new Dimension(90, 90));
		CancellaCorsoPanel.setOpaque(true);
		CancellaCorsoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CancellaCorsoPanel.setBackground(new Color(65, 105, 225));
		dashboard.add(CancellaCorsoPanel);
		
		JLabel CancellaCorsoIcon = new JLabel("");
		CancellaCorsoIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/x-mark-3-32.png")));
		CancellaCorsoIcon.setBounds(30, 11, 32, 32);
		CancellaCorsoPanel.add(CancellaCorsoIcon);
		
		JLabel CancellaCorsoLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Cancella corso</p><br>\r\n</html>");
		CancellaCorsoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		CancellaCorsoLbl.setForeground(Color.WHITE);
		CancellaCorsoLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CancellaCorsoLbl.setBounds(10, 41, 70, 34);
		CancellaCorsoPanel.add(CancellaCorsoLbl);
		
		JPanel CancellaProfessorePanel = new JPanel();
		CancellaProfessorePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.openCancellaProfessoreWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CancellaProfessorePanel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CancellaProfessorePanel.setBackground(new Color(65, 105, 225));
			}
		});
		CancellaProfessorePanel.setLayout(null);
		CancellaProfessorePanel.setSize(new Dimension(90, 90));
		CancellaProfessorePanel.setPreferredSize(new Dimension(90, 90));
		CancellaProfessorePanel.setOpaque(true);
		CancellaProfessorePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CancellaProfessorePanel.setBackground(new Color(65, 105, 225));
		dashboard.add(CancellaProfessorePanel);
		
		JLabel CancellaProfessoreIcon = new JLabel("");
		CancellaProfessoreIcon.setIcon(new ImageIcon(LoggedWindow.class.getResource("/icon/x-mark-3-32.png")));
		CancellaProfessoreIcon.setBounds(30, 11, 32, 32);
		CancellaProfessorePanel.add(CancellaProfessoreIcon);
		
		JLabel CancellaProfessoreLbl = new JLabel("<html>\r\n<p align=\"center\" style=\"margin-top:15px;\">Cancella professore</p><br>\r\n</html>");
		CancellaProfessoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		CancellaProfessoreLbl.setForeground(Color.WHITE);
		CancellaProfessoreLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		CancellaProfessoreLbl.setBounds(10, 41, 70, 34);
		CancellaProfessorePanel.add(CancellaProfessoreLbl);
	}
}