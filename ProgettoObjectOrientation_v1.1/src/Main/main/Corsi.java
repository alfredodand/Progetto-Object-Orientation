package main;


import java.util.ArrayList;

import DAOs.CorsiDAOimpl;
import DAOs.CorsiDAOinterf;

public class Corsi {
	private String nome;
	private String descrizione;
	private int tassoPresenzeMinimo;
	private int numeroMassimoPartecipanti;
	private Insegnanti insegnante;
	private int docente;
	private String areaTematica;
	private String id;
	private CorsiDAOinterf corsiDAO;
	private Controller controller;
	private int massimoStudenti; 
	private ArrayList<Studenti> studenti = new ArrayList<Studenti>();
	private ArrayList<Lezioni> lezioni = new ArrayList<Lezioni>();
	public Corsi(String nome, String id, int docente, Controller controller) {
		insegnante = new Insegnanti();
		this.nome = nome;
		this.id = id;
		this.controller = controller;
		this.insegnante.setid(docente);
		corsiDAO = new CorsiDAOimpl(controller);
	}
	
	public Corsi(String nome, String id, Controller controller) {
		this.nome = nome;
		this.id = id;
		this.controller = controller;
		corsiDAO = new CorsiDAOimpl(controller);
	}
	
	public Corsi(String nome, Controller controller) {
		this.nome = nome;
		this.controller = controller;
		corsiDAO = new CorsiDAOimpl(controller);
	}
	
	public Corsi(String nome, String descrizione, int tassoPresenzeMinimo, String areaTematica, Controller controller, int massimoStudenti) {
		this.nome = nome;
		this.tassoPresenzeMinimo = tassoPresenzeMinimo;
		this.descrizione = descrizione;
		this.controller = controller;
		this.areaTematica = areaTematica;
		this.massimoStudenti = massimoStudenti;
		corsiDAO = new CorsiDAOimpl(controller);
	}
	
	public void getStudentiFromDb(Corsi corso) {
		studenti = corsiDAO.getStudentiDiCorso(corso);
	}
	
	public int getDocente() {
		return insegnante.getId();
	}
	
	public String getAreaTematica() {
		return areaTematica;
	}
	
	public void setAreaTematica(String areaTematica) {
		this.areaTematica = areaTematica;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public int getTassoPresenzeMinimo() {
		return tassoPresenzeMinimo;
	}
	
	public int getNumeroMassimoPartecipanti() {
		return numeroMassimoPartecipanti;
	}
	
	public String addCorso() {
		if(!nome.isEmpty() && !descrizione.isEmpty()  && !areaTematica.isEmpty() && tassoPresenzeMinimo != 0 && massimoStudenti != 0) {
			return corsiDAO.addCorso(nome, descrizione, tassoPresenzeMinimo, areaTematica, massimoStudenti);
		}

		
		return "Il corso non è stato creato correttamente";
	}

	public ArrayList<Lezioni> getLezioni(String nomeCorso) {
		lezioni = corsiDAO.getLezioni(nomeCorso);
		return lezioni;
	}
	

	
}
