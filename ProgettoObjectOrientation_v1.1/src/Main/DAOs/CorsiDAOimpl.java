package DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.Controller;
import main.Corsi;
import main.Lezioni;
import main.Studenti;

public class CorsiDAOimpl implements CorsiDAOinterf{
	Controller controller;
	
	public CorsiDAOimpl(Controller controller) {
		this.controller = controller;
	}
	
	public ArrayList<Studenti> getStudentiDiCorso(Corsi corso) {
		ArrayList<Studenti> studenti = new ArrayList<Studenti>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola FROM Studenti AS S INNER JOIN Iscrizione AS I ON S.matricola = I.Matricola INNER JOIN Corsi AS C ON I.id_corsi = C.id_corsi "
					+ "WHERE C.nome = '" + corso.getNome() + "' AND C.docente = '" + corso.getDocente() + "'";
			result = statement.executeQuery(query);
			while(result.next()) {
				Studenti studente = new Studenti(result.getString("nome"),result.getString("cognome"),result.getString("matricola"),result.getString("data_nascita"),result.getString("sesso"), controller);
				studenti.add(studente);
			}
			return studenti;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return null;
	}
	
	public int getIdFromCorso(String corso) {
		int id_corso = -1;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT id_corsi FROM Corsi WHERE nome LIKE '" + corso + "%'";
			result = statement.executeQuery(query);
			while(result.next()) {
				id_corso = result.getInt("id_corsi");
			}
			return id_corso;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return -1;
	}
	
	public String addCorso(String nome, String descrizione, int tassoPresenzeMinimo, String areaTematica, int massimoStudenti) {
		int id_area = -1;
		Statement statement = null;
		Connection dbConnect = null;
		//secondo layer di sicurezza (già esiste il controllo nel db)
		id_area = existCorso(nome, descrizione, tassoPresenzeMinimo, areaTematica);
		if(id_area != -1)  {
			if(tassoPresenzeMinimo>1 && tassoPresenzeMinimo<100) {
				try {
					controller.dbConnect();
					statement  = controller.getStatement();
					dbConnect = controller.getDbConnect();
					String query = "INSERT INTO Corsi VALUES (DEFAULT, '" + nome + "', '" + descrizione + "', " + tassoPresenzeMinimo + ", 0, 0, '" + id_area + "', NULL, " + massimoStudenti +")";
					statement.execute(query);
					controller.getOperatore().assegnaAreaTematica(areaTematica, nome);
					return "Il corso è stato creato correttamente";
				} catch (SQLException e) {
					System.out.println(e.getErrorCode());
				}
				finally {
					controller.dbClose(null, statement, dbConnect);
				}
			}
			else {
				return "Il tasso di presenze minimo deve essere compreso tra 1 e 100";
			}
		}else {
			return "Il corso già esiste nel database";
		}
		return "Il corso non è stato creato correttamente";
	}

	private int existCorso(String nome, String descrizione, int tassoPresenzeMinimo, String areaTematica)  {
		int id = -1;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT id_area_tematica FROM areaTematica WHERE nome = '" + areaTematica + "'"; 
			result = statement.executeQuery(query);
			while(result.next()) {
				id = result.getInt("id_area_tematica");
			}
			query = "SELECT * FROM Corsi WHERE nome = '" + nome + "' AND descrizione = '" + descrizione + "' AND tasso_minimo_presenze = " + tassoPresenzeMinimo + " AND id_area_tematica = " + id + ";";
			result = statement.executeQuery(query);
			if(result.isBeforeFirst()) {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return id;
	}
	
	public ArrayList<Lezioni> getLezioni(String nomeCorso){
		ArrayList<Lezioni> lezioni = new ArrayList<Lezioni>();
		int idCorso = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT id_corsi FROM Corsi WHERE nome = '" + nomeCorso + "'";
			result = statement.executeQuery(query);
			while(result.next()) {
				idCorso = result.getInt("id_corsi");
			}
			query = "SELECT titolo FROM Lezioni WHERE id_corso = " + idCorso + ";";
			result = statement.executeQuery(query);
			while(result.next()) {
				Lezioni lezione = new Lezioni(result.getString("titolo"), controller);
				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return null;
	}
}
