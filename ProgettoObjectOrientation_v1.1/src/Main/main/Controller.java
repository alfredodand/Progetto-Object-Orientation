package main;
import java.sql.*;
import javax.swing.JFrame;

import DAOs.CorsiDAOimpl;
import DAOs.CorsiDAOinterf;
import DAOs.LoginDAOimpl;
import DAOs.LoginDAOinterf;
import DAOs.TableDAOimpl;
import DAOs.TableDAOinterf;
import view.AggiungiAreaTematicaWindow;
import view.AggiungiLezioneWindow;
import view.AggiungiPresenzeWindow;
import view.AggiungiProfessoreWindow;
import view.AssegnaCorsoWindow;
import view.CancellaCorsoWindow;
import view.CancellaProfessoreWindow;
import view.CancellaStudenteWindow;
import view.ContattiWindow;
import view.DocumentazioneWindow;
import view.ImmatricolaWindow;
import view.IscriviAlCorsoWindow;
import view.LoggedWindow;
import view.LoginWindow;
import view.ResocontoCorsiWindow;
import view.StatisticheLezioniWindow;
import view.StatisticheS;
import view.StatisticheStudenteWindow;
import view.HomeWindow;
import view.ProfiloWindow;
import view.VisualizzaCorsiWindow;
import view.NuovoCorsoWindow;


public class Controller {
	private Operatore operatore;
	private int idLoggedOperator;
	private String loggedOperatorName, loggedOperatorSurname, loggedOperatorEmail, loggedOperatorUsername;
	private LoginDAOinterf login;
	private TableDAOinterf tabledao = new TableDAOimpl(this);
	private CorsiDAOinterf corsidao = new CorsiDAOimpl(this);
	private ProfiloWindow profilowindow;
	private LoginWindow loginwindow;
	private LoggedWindow loggedwindow; 
	private ImmatricolaWindow immatricolawindow;
	private ResocontoCorsiWindow resocontocorsiwindow;
	private IscriviAlCorsoWindow iscrivialcorsowindow;
	private NuovoCorsoWindow nuovoCorsoWindow;
	private AssegnaCorsoWindow assegnacorsowindow;
	private AggiungiProfessoreWindow aggiungiprofessorewindow;
	private CancellaCorsoWindow cancellacorsowindow;
	private CancellaProfessoreWindow cancellaprofessorewindow;
	private CancellaStudenteWindow cancellastudentewindow;
	private AggiungiLezioneWindow aggiungilezionewindow;
	private StatisticheStudenteWindow statistichestudentewindow;
	private HomeWindow homewindow;
	private ContattiWindow contattiwindow;
	private DocumentazioneWindow documentazionewindow;
	private StatisticheS statistichestudenti;
	private AggiungiPresenzeWindow aggiungipresenzewindow;
	private VisualizzaCorsiWindow visualizzacorsi;
	private ConnessioneDb databaseconnection;
	private StatisticheLezioniWindow statisticheLezioni;
	private AggiungiAreaTematicaWindow aggiungiareatematicawindow;
	public static void main(String[] args) {
		Controller controller = new Controller();
	}
	
	public Controller() {
		openLoginWindow();
		databaseconnection = new ConnessioneDb();
		databaseconnection.generaDatabase(loginwindow);
	}
	
	public void openStatisticheStudenteWindow() {
		statistichestudentewindow = new StatisticheStudenteWindow(this);
		statistichestudentewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openAssegnaCorsoWindow() {
		operatore.getProfFromDb();
		operatore.getCorsiFromDb();
		assegnacorsowindow = new AssegnaCorsoWindow(this);
		assegnacorsowindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openAggiungiProfessoreWindow() {
		aggiungiprofessorewindow = new AggiungiProfessoreWindow(this);
		aggiungiprofessorewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openCancellaCorsoWindow() {
		operatore.getCorsiFromDb();
		cancellacorsowindow = new CancellaCorsoWindow(this);
		cancellacorsowindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openContattiWindow() {
		contattiwindow = new ContattiWindow(this);
		contattiwindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openDocumentazioneWindow() {
		documentazionewindow = new DocumentazioneWindow(this);
		documentazionewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openCancellaProfessoreWindow() {
		operatore.getProfFromDb();
		cancellaprofessorewindow = new CancellaProfessoreWindow(this);
		cancellaprofessorewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openCancellaStudenteWindow() {
		cancellastudentewindow = new CancellaStudenteWindow(this);
		cancellastudentewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openAggiungiLezioneWindow() {
		operatore.getCorsiFromDb();
		aggiungilezionewindow = new AggiungiLezioneWindow(this);
		aggiungilezionewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	void createLogin() {
		login = new LoginDAOimpl(this);
	}
	
	public void openLoginWindow() {
		loginwindow = new LoginWindow(this);
		loginwindow.getFrame().setVisible(true);
	}
	
	public void closeLoginWindow() {
		loginwindow.getFrame().setVisible(false);
	}
	
	public void openLoggedWindow() {
		loggedwindow = new LoggedWindow(this);
		loggedwindow.getFrame().setVisible(true);
	}
	
	public void openLoggedWindowFromAnotherWindow(JFrame window) {
		window.setVisible(false);
		loggedwindow.getFrame().setVisible(true);
	}
	
	public void openImmatricolaWindow() {
		immatricolawindow = new ImmatricolaWindow(this);
		immatricolawindow.setVisible(true);
		closeLoggedWindow();
		
	}
	
	public void openResocontoCorsiWindow() {
		resocontocorsiwindow = new ResocontoCorsiWindow(this);
		resocontocorsiwindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openIscriviAlCorsoWindow() {
		operatore.getCorsiFromDb();
		iscrivialcorsowindow = new IscriviAlCorsoWindow(this);
		iscrivialcorsowindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void closeLoggedWindow() {
		loggedwindow.getFrame().setVisible(false);
	}
	
	public void openProfiloWindow() {
		profilowindow = new ProfiloWindow(this);
		profilowindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openAggiungiPresenzeWindow() {
		operatore.getCorsiFromDb();
		aggiungipresenzewindow = new AggiungiPresenzeWindow(this);
		aggiungipresenzewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openNuovoCorsoWindow() {
		operatore.getAreeTematicheFromDb();
		nuovoCorsoWindow = new NuovoCorsoWindow(this);
		nuovoCorsoWindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openAggiungiAreaTematica() {
		aggiungiareatematicawindow = new AggiungiAreaTematicaWindow(this);
		aggiungiareatematicawindow.setVisible(true);
		closeLoggedWindow();
	}	
	
	
	public void openStatisticheLezioniWindow() {
		operatore.getCorsiFromDb();
		
		statisticheLezioni = new StatisticheLezioniWindow(this);
		statisticheLezioni.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openHomeWindow() {
		homewindow = new HomeWindow(this);
		homewindow.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openStatisticheStudenti() {
		operatore.getCorsiFromDb();
		statistichestudenti = new StatisticheS(this, operatore);
		statistichestudenti.setVisible(true);
		closeLoggedWindow();
	}
	
	public void openVisualizzaCorsiWindow() {
		operatore.getAreeTematicheFromDb();
		visualizzacorsi = new VisualizzaCorsiWindow(this, operatore);
		visualizzacorsi.setVisible(true);
		closeLoggedWindow();
	}
	
	public Studenti getStudenteInfo(String matricola) {
		Studenti studente = new Studenti(this);
		studente.getStudenteInfo(matricola);
		studente.getStudenteCorsiFrequentati(matricola);
		return studente;
	}

	
	public void dbConnect() {
		databaseconnection.dbConnect();
	}	
	
	public void dbClose(ResultSet result, Statement statement, Connection dbConnect) {
		try {
			if (result != null) 
				result.close();
			if (statement != null)
				statement.close();
			if (dbConnect != null)
				dbConnect.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void numeroMedioPresenze(Tabella tbl) {
		tabledao.numeroMedioPresenze(tbl);
	}
	
	public void numeroMinimoPresenze(Tabella tbl) {
		tabledao.numeroMinimoPresenze(tbl);
	}
	
	public void numeroMassimoPresenze(Tabella tbl) {
		tabledao.numeroMassimoPresenze(tbl);
	}

	public void percentualeMediaPresenze(Tabella tbl) {
		tabledao.percentualeMediaPresenze(tbl);
	}
	
    public void visualizzaIscrizioni(Tabella tbl) throws SQLException {
    	operatore.visualizzaIscrizioni(tbl);
    }

	public void newOperatore() {
		operatore = new Operatore(this);
	}
	
	public void setIDOperatoreLogged(int id) {
		this.idLoggedOperator = id;
	}
	
	public Operatore getOperatore() {
		return operatore;
	}
	
	public ResultSet getResult() {
		return databaseconnection.getResult();
	}
	
	public Statement getStatement() {
		return databaseconnection.getStatement();
	}
	
	public Connection getDbConnect() {
		return databaseconnection.getDbConnect();
	}
	
	public void getOperatoreInfo(int id){
		operatore.getOperatoreInfo(id);
	}
	
	public LoginDAOinterf getLogin() {
		return login;
	}

	public String addCorso(String nome, String descrizione, int tassoMinimoPresenze, String areaTematica, int massimoStudenti) {
		Corsi corso = new Corsi(nome, descrizione, tassoMinimoPresenze, areaTematica, this, massimoStudenti);
		return corso.addCorso();
	}


}
