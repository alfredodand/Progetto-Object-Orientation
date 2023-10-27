package DAOs;
import java.util.ArrayList;

public interface StudenteDAOinterf {
	public boolean immatricolazione(String nome, String cognome, String matricola, String DataNascita, String genere);
	public ArrayList<String> getStudenteInfo(String matricola);
	public ArrayList<String> getStudenteCorsiFrequentati(String matricola);
	public String getAssenze(String nomeCorso, String matricola);
	public String getPresenze(String nomeCorso, String matricola);
	public boolean getPercentualeMinima(String nomeCorso, String matricola);
	public String massimoMatricola();
}
