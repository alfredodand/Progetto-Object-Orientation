package main;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAOs.CorsiDAOimpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class GeneralActionListener implements ActionListener{
	private Controller controller;
	private Tabella tbl;
	private JLabel lbl;
	private JTextField name, surname, matr, tassoMinimoPresenzeCorso, durata, ora, massimo;
	private JTextArea descr;
	private ArrayList<JComboBox> areeTematiche = new ArrayList<JComboBox>();
	private JComboBox day, month, year, corsoCombo, areaTematicaCorso, insegnante;
	private String selectedDay, selectedYear, selectedMonth, nome, cognome, matricola, selectedCorso, genere, descrizione, areaTematica, corso;
	private JDatePickerImpl datePicker;
	private int tassoMinimoPresenze;
	
	public void setDatePicker(JDatePickerImpl datePicker) {
		this.datePicker = datePicker;
	}	
	
	public void setAreeTematiche(ArrayList<JComboBox> areeTematiche) {
		this.areeTematiche = areeTematiche;
	}
	
	public void setDurata(JTextField durata) {
		this.durata = durata;
	}
	
	public void setMassimoStudenti(JTextField massimo) {
		this.massimo = massimo;
	}
	
	public void setOra(JTextField ora) {
		this.ora = ora;
	}
	
	public void setGenere(String genere) {
		this.genere = genere;
	}	
	public GeneralActionListener(Controller controller) {
		this.controller = controller;
	}
	public void setNome(JTextField nome) {
		this.name = nome;
	}
	public void setNomeTxt(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setCognomeTxt(String cognome) {
		this.cognome = cognome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setMatricola(JTextField matr) {
		this.matr = matr;
	}
	public void setCognome(JTextField cognome) {
		this.surname = cognome;
	}
	public void setDescrizione(JTextArea descrizione) {
		this.descr = descrizione;
	}
	public void setTassoMinimoPresenze(JTextField tassoMinimoPresenze) {
		this.tassoMinimoPresenzeCorso = tassoMinimoPresenze;
	}
	public void setJComboBox(JComboBox areaTematica) {
		this.areaTematicaCorso = areaTematica;
	}
	public void setJComboBox1(JComboBox day) {
		this.day = day;
	}
	public void setJComboBox2(JComboBox month) {
		this.month = month;
	}
	public void setJComboBox3(JComboBox year) {
		this.year = year;
	}
	public void setJComboBox4(JComboBox corso) {
		this.corsoCombo = corso;
	}
	public void setTable(Tabella tbl) {
		this.tbl = tbl;
	}
	
	public void setLabel(JLabel lbl) {
		this.lbl = lbl;
	}
	
	public void setJComboBox5(JComboBox insegnanteCombobox) {
		this.insegnante = insegnanteCombobox;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JButton Btn = (JButton) e.getSource();
			if(Btn.getActionCommand().equals("Visualizza1")) {
				tbl.svuotaTable();
				lbl.setText("NUMERO MEDIO STUDENTI");
				controller.numeroMedioPresenze(tbl);
			}
			
			if(Btn.getActionCommand().equals("Visualizza2")) {
				tbl.svuotaTable();
				lbl.setText("NUMERO MINIMO STUDENTI");
				controller.numeroMinimoPresenze(tbl);
			}
			
			if(Btn.getActionCommand().equals("Visualizza3")) {
				tbl.svuotaTable();
				lbl.setText("NUMERO MASSIMO STUDENTI");
				controller.numeroMassimoPresenze(tbl);
			}
			
			if(Btn.getActionCommand().equals("Visualizza4")) {
				tbl.svuotaTable();
				lbl.setText("PERCENTUALE MEDIA");
				controller.percentualeMediaPresenze(tbl);
			}
			
			if(Btn.getActionCommand().equals("iscrivi_studente")) {
				iscriviStudente();
			}
			
			if(Btn.getActionCommand().equals("Immatricolazione")) {
				immatricolaStudente();
			}
			
			if(Btn.getActionCommand().equals("NuovoCorso")) {
				creaNuovoCorso();
			}
			
			if(Btn.getActionCommand().equals("cancellaStudente")) {
				cancellaStudente();
			}
			
			if(Btn.getActionCommand().equals("cancellaCorso")) {
				cancellaCorso();
			}
			
			if(Btn.getActionCommand().equals("aggiungi_tema")) {
				aggiungiTema();
			}
			
			if(Btn.getActionCommand().equals("AssegnaCorso")) {
				assegnaCorso();
			}
			
			if(Btn.getActionCommand().equals("Nuovo Prof")) {
				aggiungiProfessore();
			}
			
			if(Btn.getActionCommand().equals("CancellaProf")) {
				cancellaProfessore();
			}
			if(Btn.getActionCommand().equals("aggiungiLezione")) {
				aggiungiLezione();	
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}

	private void aggiungiTema() {
		nome = name.getText();
		if(!nome.isEmpty()) {
			if(controller.getOperatore().addAreaTematica(nome)) {
				lbl.setForeground(Color.green);
				lbl.setText("Aggiunta andata a buon fine");
			}else {
				lbl.setForeground(Color.red);
				lbl.setText("Aggiunta fallita");
			}
		}
		
	}

	private void aggiungiLezione() {
		Integer selectedDay = datePicker.getModel().getDay();
		Integer selectedMonth = datePicker.getModel().getMonth();
		selectedMonth = selectedMonth + 1;
		Integer selectedYear = datePicker.getModel().getYear();
		String selectedDate = selectedYear.toString() + "-" + selectedMonth.toString() + "-" + selectedDay.toString();
		String nome = name.getText();
		String descrizione = descr.getText();
		String corso = corsoCombo.getSelectedItem().toString();
		String durata_ = durata.getText();
		String ora_ = ora.getText();
		String msg = "";
		//System.out.println(nome.isEmpty());
		if(!selectedDate.isEmpty() && !nome.isEmpty() && !descrizione.isEmpty() && !corso.isEmpty() && !ora_.isEmpty() && !durata_.isEmpty()) {
			if(Integer.parseInt(ora_) < 24 && Integer.parseInt(ora_) > 0) {
				msg = controller.getOperatore().addLezione(selectedDate, nome, descrizione, durata_, ora_, corso);
				if(msg.equals("Aggiunta andata a buon fine")) {
					lbl.setForeground(Color.green);
				}else {
					lbl.setForeground(Color.red);
				}
				
			}else {
				msg = "L'ora d'inizio deve essere compresa tra 0 e 24";
				lbl.setForeground(Color.red);
			}
		}else {
			msg = "I campi non possono essere vuoti";
			lbl.setForeground(Color.red);
		}
		lbl.setText(msg);
	}
	
	private void cancellaProfessore() {
		String nomeCompleto = insegnante.getSelectedItem().toString();
		nomeCompleto = nomeCompleto.substring(nomeCompleto.indexOf(" ") + 1);
		nomeCompleto = reverseString(nomeCompleto);
		String id = nomeCompleto.substring(0, nomeCompleto.indexOf(" "));
		id = reverseString(id);
		String msg = controller.getOperatore().deleteInsegnante(id);
		if(msg == "Insegnante cancellato con successo") {
			lbl.setForeground(Color.green);
		}else {
			lbl.setForeground(Color.red);
		}
		lbl.setText(msg);
	}
	
	private void aggiungiProfessore() throws SQLException {
		String msg;
		nome = name.getText();
		cognome = surname.getText();
		selectedDay = day.getSelectedItem().toString();
		selectedMonth = month.getSelectedItem().toString();
		selectedYear = year.getSelectedItem().toString();
		descrizione = descr.getText();
		if(!nome.isEmpty() && !cognome.isEmpty()) {
			msg = controller.getOperatore().addInsegnante(nome, cognome, selectedDay, selectedMonth, selectedYear, descrizione);
			if(msg == "Insegnante aggiunto con successo") {
				lbl.setForeground(Color.green);
				name.setText("");
				surname.setText("");
				descr.setText("");
			}else {
				lbl.setForeground(Color.red);
			}
		}else {
			lbl.setForeground(Color.red);
			msg = "Non è possibile lasciare vuoti nome e cognome";
		}
		lbl.setText(msg);
	}
	
	private void assegnaCorso() throws SQLException {
		corso = corsoCombo.getSelectedItem().toString();
		String nomeCompleto = insegnante.getSelectedItem().toString();
		nome = nomeCompleto.substring(0, nomeCompleto.indexOf(" "));
		cognome = nomeCompleto.substring(nomeCompleto.indexOf(" ") + 1);
		String msg = controller.getOperatore().addInsegnanteAlCorso(nome, cognome, corso);
		if(msg == "Assegnazione avvenuta con successo") {
			lbl.setForeground(Color.green);
		}else {
			lbl.setForeground(Color.red);
		}
		lbl.setText(msg);
	}
	
	private void cancellaCorso() {
		selectedCorso = corsoCombo.getSelectedItem().toString();
		if(controller.getOperatore().deleteCorso(selectedCorso)) {
			lbl.setForeground(Color.green);
			lbl.setText("Il corso esiste ed è stata cancellata");
		}
	}
	
	private void cancellaStudente() {
		matricola = matr.getText().toUpperCase();
		if(controller.getOperatore().deleteStudente(matricola)) {
			lbl.setForeground(Color.green);
			lbl.setText("La matricola esiste ed è stata cancellata");
		}else {
			lbl.setForeground(Color.red);
			lbl.setText("La matricola non esiste");
		}
	}
	
	private void creaNuovoCorso() {
		String msg;
		int massimoStudenti = 0;
		tassoMinimoPresenze = 0;
		nome = name.getText();
		descrizione = descr.getText();
		if(!tassoMinimoPresenzeCorso.getText().isEmpty()) {
			tassoMinimoPresenze = Integer.parseInt(tassoMinimoPresenzeCorso.getText());
		}else {
			msg = "Il tasso minimo di presenze deve essere riempito";
		}
		if(!massimo.getText().isEmpty()) {
			massimoStudenti = Integer.parseInt(massimo.getText());
		}
		areaTematica = areaTematicaCorso.getSelectedItem().toString();
		if(!nome.isEmpty() && !descrizione.isEmpty() && !areaTematica.isEmpty()) {
			msg = controller.addCorso(nome, descrizione, tassoMinimoPresenze, areaTematica, massimoStudenti);
			if(msg == "Il corso è stato creato correttamente") {
				name.setText("");
				descr.setText("");
				tassoMinimoPresenzeCorso.setText("");
				lbl.setForeground(Color.green);
			}else {
				lbl.setForeground(Color.red);
			}
		}else {
			msg = "I campi nome, descrizione, area tematica e tasso minimo di presenze non possono essere vuoti";
			lbl.setForeground(Color.red);
		}
		for(JComboBox cb : areeTematiche) {
			if(!cb.getSelectedItem().toString().isEmpty()) {
				msg = controller.getOperatore().assegnaAreaTematica(cb.getSelectedItem().toString(), nome);
			}
		}
		lbl.setText(msg);
		

	}
	
	private void immatricolaStudente() {
		nome = name.getText();
		cognome = surname.getText();
		selectedDay = day.getSelectedItem().toString();
		selectedMonth = month.getSelectedItem().toString();
		selectedYear = year.getSelectedItem().toString();
		if(!nome.isEmpty() && !cognome.isEmpty()) {
			MatricolaGenerator MG = new MatricolaGenerator(controller);
			matricola = MG.nuovaMatricola();
			Studenti studente = new Studenti(nome, cognome, matricola, selectedYear + "-" + selectedMonth + "-" + selectedDay, genere, controller);
			if(studente.immatricolazione()) {
				lbl.setForeground(Color.green);
				lbl.setText("<html><p>Iscrizione effettuata con successo.<br> La tua matricola è: </p>" + matricola);
			}else {
				lbl.setForeground(Color.red);
				lbl.setText("Iscrizione fallita");
			}
		}else {
			lbl.setForeground(Color.red);
			lbl.setText("Non è possibile lasciare vuota la sezione nome e cognome");
		}
	}
	
	private void iscriviStudente() {
		selectedCorso = corsoCombo.getSelectedItem().toString();
		matricola = matr.getText();
		String msg = controller.getOperatore().IscriviStudenteAlCorso(matricola, selectedCorso);
		if(msg == "Iscrizione avvenuta con successo") {
			lbl.setForeground(Color.green);
		}else{
			lbl.setForeground(Color.red);
		}
		lbl.setText(msg);
	}
	
	private String reverseString(String reverse) {
		String reversed = new String ("");
		char ch;
	      for (int i=0; i<reverse.length(); i++){
	        ch= reverse.charAt(i);
	        reversed= ch + reversed; 
	      }
	      return reversed;
	}
	
}
