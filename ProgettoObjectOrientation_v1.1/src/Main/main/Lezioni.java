package main;

import java.sql.Time;
import java.util.Date;

public class Lezioni {
	private String titolo;
	private String descrizione;
	private int durataOre;
	private Date dataInizio;
	private Time orarioInizio;
	private Controller controller;
	private Corsi corso;
	
	public Lezioni(String titolo, Controller controller) {
		this.controller = controller;
		this.titolo = titolo;
	}
	
	public String getTitolo() {
		return titolo;
	}

	public String getNome() {
		return titolo;
	}
}
