package main;
import java.util.ArrayList;
import DAOs.StudenteDAOimpl;
import DAOs.StudenteDAOinterf;

public class Studenti {
	private String nome;
	private String cognome;
	private String matricola;
	private String datanascita;
	private String genere;
	private StudenteDAOinterf studentedao;
	private Controller controller;
	
	private ArrayList<Corsi> corsiFrequentati = new ArrayList<Corsi>();
	
	public Studenti(String nome, String cognome, String matricola, String DataNascita, String genere, Controller controller) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.datanascita = DataNascita;
		this.genere = genere;
		this.controller = controller;
		studentedao = new StudenteDAOimpl(controller);
	}
	
	public Studenti(Controller controller) {
		this.controller = controller;
		studentedao = new StudenteDAOimpl(controller);
	}
	
	public ArrayList<Corsi> getCorsiFrequentati() {
		return corsiFrequentati;
	}

	public void setCorsiFrequentati(ArrayList<Corsi> corsiFrequentati) {
		this.corsiFrequentati = corsiFrequentati;
	}

	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getMatricola() {
		return matricola;
	}
	public String getNascita() {
		return datanascita;
	}
	public String getGenere() {
		return genere;
	}
	
	public void getStudenteInfo(String matricola) {
		ArrayList<String> dati = new ArrayList<String>();
		dati = studentedao.getStudenteInfo(matricola);
		if(!dati.isEmpty()) {
			this.nome = dati.get(0);
			this.cognome = dati.get(1);
			this.matricola = dati.get(2);
			this.datanascita = dati.get(3);
			this.genere = dati.get(4);
		}
	}
	
	public void getStudenteCorsiFrequentati(String matricola) {
		int i = 0;
		ArrayList<String> corsi = new ArrayList<String>();
		corsi = studentedao.getStudenteCorsiFrequentati(matricola);
		if(!corsi.isEmpty()) {
			for(i = 0; i<corsi.size(); i++) {
				Corsi corso = new Corsi(corsi.get(i), corsi.get(i + 1), controller);
				corsiFrequentati.add(corso);
				i++;
			}
		}
	}

	public String getAssenze(String nomeCorso, String matricola) {
		return studentedao.getAssenze(nomeCorso, matricola);
	}
	public String getPresenze(String nomeCorso, String matricola) {
		return studentedao.getPresenze(nomeCorso, matricola);
	}
	
	public boolean getPercentualeMinima(String nomeCorso, String matricola) {
		return studentedao.getPercentualeMinima(nomeCorso, matricola);
	}
	
	public boolean immatricolazione() {
		return studentedao.immatricolazione(nome, cognome, matricola, datanascita, genere);
	}

}
