package main;
import java.sql.SQLException;
import java.util.ArrayList;
import DAOs.OperatoreDAOimpl;
import DAOs.OperatoreDAOinterf;

public class Operatore {
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private OperatoreDAOinterf operatoreDAO;
	private Controller controller;
	private ArrayList<Corsi> corsi;
	private ArrayList<Lezioni> lezioni;
	private ArrayList<Insegnanti> prof;
	private ArrayList<AreaTematica> areeTematiche;
	//Insegnanti insegnanti = new Insegnanti();
	
	Operatore(String nome, String cognome, String email, String username, Controller controller){
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
		this.controller = controller;
		operatoreDAO = new OperatoreDAOimpl(this, controller);
		ArrayList<Corsi> corso = new ArrayList<Corsi>();
	}
	
	Operatore(Controller controller){
		this.controller = controller;
		operatoreDAO = new OperatoreDAOimpl(this, controller);
	}
	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String IscriviStudenteAlCorso(String matr, String nomeCorso) {
		return operatoreDAO.IscriviStudenteAlCorso(matr, nomeCorso);
	}
	
	public void getCorsiFromDb() {
		corsi = operatoreDAO.getCorsi();
	}
	
	public void getProfFromDb() {
		prof = operatoreDAO.getProf();
	}
	
	public void getOperatoreInfo(int id) {
		ArrayList<String> info = new ArrayList<String>();
		info = operatoreDAO.getOperatoreInfo(id);
		setNome(info.get(0));
		setCognome(info.get(1));
		setEmail(info.get(2));
		setUsername(info.get(3));
	}
	
	public String addInsegnanteAlCorso(String nome, String cognome, String corso){
		return operatoreDAO.AggiungiInsegnanteAlCorso(nome, cognome, corso);
	}
	
	public String addInsegnante(String nome, String cognome, String day, String month, String year, String descrizione){
		return operatoreDAO.AggiungiInsegnante(nome, cognome, day, month, year, descrizione);
	}
	
	public ArrayList<Corsi> getCorsi() {
		return corsi;
	}
	
	public ArrayList<Insegnanti> getProf() {
		return prof;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
    public void visualizzaIscrizioni(Tabella tbl) throws SQLException {
    	operatoreDAO.visualizzaIscrizioni(tbl);
    }
    
	public boolean deleteStudente(String matricola) {
		return operatoreDAO.deleteStudente(matricola);
	}
	
	public boolean deleteCorso(String nomeCorso) {
		return operatoreDAO.deleteCorso(nomeCorso);
	}

	public String deleteInsegnante(String id) {
		return operatoreDAO.deleteInsegnante(id);
	}
	
	public int getNumeroMatricole() {
		return operatoreDAO.getNumeroMatricole();
	}

	public ArrayList<String> getIscrittiACorso(String nomeCorso) {
		return operatoreDAO.getIscrittiACorso(nomeCorso);
	}

	public void aggiungiPresenze(String matricola, String titolo, String nomeCorso) {
		 operatoreDAO.aggiungiPresenze(matricola, titolo, nomeCorso);
	}

	public String addLezione(String selectedDate, String titolo, String descrizione, String durata, String ora, String nomeCorso) {
		return operatoreDAO.addLezione(selectedDate, titolo, descrizione, durata, ora, nomeCorso);
	}

	public void getAreeTematicheFromDb(){
		areeTematiche = operatoreDAO.getAreeTematiche();
	}
	public ArrayList<AreaTematica> getAreeTematiche() {
		return areeTematiche;
	}

	public boolean addAreaTematica(String nome) {
		return operatoreDAO.addAreaTematica(nome);
	}

	public String assegnaAreaTematica(String areaTematica, String nomeCorso) {
		return operatoreDAO.assegnaAreaTematica(areaTematica, nomeCorso);
	}

}
