package DAOs;
import java.util.ArrayList;

import main.AreaTematica;
import main.Corsi;
import main.Insegnanti;
import main.Lezioni;
import main.Tabella;

public interface OperatoreDAOinterf {
	public String IscriviStudenteAlCorso(String matr, String nomeCorso);
	public ArrayList<Corsi> getCorsi();
	public ArrayList<String> getOperatoreInfo(int id);
	public void visualizzaIscrizioni(Tabella tbl);
	public boolean deleteStudente(String matricola);
	public ArrayList<Insegnanti> getProf();
	public String AggiungiInsegnanteAlCorso(String nome, String cognome, String corso);
	public boolean deleteCorso(String nomeCorso);
	public String AggiungiInsegnante (String nome, String cognome, String day, String month, String year, String descrizione);
	public String deleteInsegnante(String id);
	public int getNumeroMatricole();
	public ArrayList<String> getIscrittiACorso(String nomeCorso);
	public void aggiungiPresenze(String matricola, String titolo, String nomeCorso);
	public String addLezione(String selectedDate, String titolo, String descrizione, String durata, String ora, String nomeCorso);
	public ArrayList<AreaTematica> getAreeTematiche();
	public boolean addAreaTematica(String nome);
	public String assegnaAreaTematica(String areaTematica, String nomeCorso);
}
