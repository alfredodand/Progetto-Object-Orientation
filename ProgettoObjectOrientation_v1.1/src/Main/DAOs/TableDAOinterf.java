package DAOs;
import javax.swing.table.DefaultTableModel;

import main.Controller;
import main.Tabella;

public interface TableDAOinterf {
	public void numeroMedioPresenze(Tabella tbl);
	public void numeroMinimoPresenze(Tabella tbl);
	public void numeroMassimoPresenze(Tabella tbl);
	public void percentualeMediaPresenze(Tabella tbl);
	public int statisticheStudenti(DefaultTableModel model, Controller controller);
	public int statisticheStudentiNomeCognome(DefaultTableModel model, Controller controller, String src);
	public int statisticheStudentiDataNascita(DefaultTableModel model, Controller controller, String src);
	public int statisticheStudentiMatricola(DefaultTableModel model, Controller controller, String src);
	public int statisticheStudentiCorsiIscritto(DefaultTableModel model, Controller controller, String src);
	public int statisticheStudentiCorsiIdonei(DefaultTableModel model, Controller controller, String src);
	public int statisticheStudentiSesso(DefaultTableModel model, Controller controller, String src);
	public int visualizzaCorsi(DefaultTableModel model, Controller controller);
	public int visualizzaCorsiNome(DefaultTableModel model, Controller controller, String src);
	public int visualizzaCorsiParolaChiave(DefaultTableModel model, Controller controller, String src);
	public int visualizzaCorsiAreaTematica(DefaultTableModel model, Controller controller, String src);
	public int visualizzaCorsiDocente(DefaultTableModel model, Controller controller, String src);
	public int presentiALezione(DefaultTableModel model, Controller controller, String lezione);
	public int assentiALezione(DefaultTableModel model, Controller controller, String lezione);
}
