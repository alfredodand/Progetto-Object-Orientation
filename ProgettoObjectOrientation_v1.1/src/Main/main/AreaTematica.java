package main;

public class AreaTematica {
	
	String nome;
	Controller controller;
	
	public String getNome() {
		return nome;
	}

	public AreaTematica(String nome, Controller controller) {
		this.nome = nome;
		this.controller = controller;
	}
	
}
