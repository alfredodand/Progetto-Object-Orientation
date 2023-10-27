package main;

public class Insegnanti {
	private String nome;
	private String cognome;
	private int id;
	private Controller controller;	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	
	public int getId() {
		return id;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public Insegnanti(String nome, String cognome, int id, Controller controller) {
		this.nome = nome;
		this.cognome = cognome;
		this.controller = controller;
		this.id = id;
	}
	public Insegnanti() {}
	
	public void setid(int id) {
		this.id = id;
	}
	
}
