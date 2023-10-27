package DAOs;

import java.util.ArrayList;

import main.Corsi;
import main.Lezioni;
import main.Studenti;

public interface CorsiDAOinterf {

	public String addCorso(String nome, String descrizione, int tassoPresenzeMinimo, String areaTematica, int massimoStudenti);
	public ArrayList<Studenti> getStudentiDiCorso(Corsi corso);
	public ArrayList<Lezioni> getLezioni(String nomeCorso);
	public int getIdFromCorso(String corso);
	
}
