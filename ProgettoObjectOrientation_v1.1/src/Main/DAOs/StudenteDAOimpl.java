package DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.Controller;

public class StudenteDAOimpl implements StudenteDAOinterf{
	Controller controller;
	
	public StudenteDAOimpl(Controller controller){
		this.controller = controller;
	}
	
	public boolean immatricolazione(String nome, String cognome, String matricola, String DataNascita, String genere) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
            String query = "INSERT INTO studenti VALUES ('" + matricola + "', '" + nome + "', '" + cognome + "', '" + genere + "', '" + DataNascita + "');";
            statement.execute(query);
            controller.dbClose(result, statement, dbConnect);
            return true;
		} catch (SQLException e) {
			controller.dbClose(result, statement, dbConnect);
            return false;
        }
	}
	
	public ArrayList<String> getStudenteInfo(String matricola) {
		ArrayList<String> dati = new ArrayList<String>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();

		try {
            String query = "SELECT S.nome, S.cognome, S.matricola, S.sesso, S.data_nascita FROM Studenti AS S"
            		+ " WHERE S.matricola = '" + matricola + "'";
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
                while(result.next()) {
                	dati.add(result.getString("nome"));
                	dati.add(result.getString("cognome"));
                	dati.add(result.getString("matricola"));
                	dati.add(result.getString("data_nascita"));
                	dati.add(result.getString("sesso"));
                }
            }
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		controller.dbClose(result, statement, dbConnect);
		return dati;
	}
	
	public ArrayList<String> getStudenteCorsiFrequentati(String matricola) {
		ArrayList<String> dati = new ArrayList<String>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
            String query = "SELECT C.nome AS nome_corso, C.id_corsi FROM corsi AS C NATURAL JOIN ISCRIZIONE AS I JOIN Studenti AS S ON I.matricola = S.matricola"
            		+ " WHERE S.matricola = '" + matricola + "'";
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
	            while(result.next()) {
	            	dati.add(result.getString("nome_corso"));
	            	dati.add(result.getString("id_corsi"));
	            }
            }
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		controller.dbClose(result, statement, dbConnect);
		return dati;
	}
	
	public String getAssenze(String nomeCorso, String matricola) {
		Integer assenze = 0;
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
            query = "SELECT (c.numero_lezioni - p.num_presenze) AS assenze "
            		+ "FROM Corsi AS c INNER JOIN Presenze AS p ON p.id_corso = c.id_corsi "
            		+ "WHERE p.matricola = '" + matricola + "' AND p.id_corso = '" + idCorso + "' ";
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
	            while(result.next()) {
	            	assenze = result.getInt("assenze");
	            }
            }
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		controller.dbClose(result, statement, dbConnect);
		
		return assenze.toString();
	}
	
	
	public String getPresenze(String nomeCorso, String matricola) {
		Integer presenze = 0;
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
            query = "SELECT p.num_presenze as presenze "
            		+ "FROM Corsi AS c INNER JOIN Presenze AS p ON p.id_corso = c.id_corsi "
            		+ "WHERE p.matricola = '" + matricola + "' AND p.id_corso = '" + idCorso + "' ";
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
	            while(result.next()) {
	            	presenze = result.getInt("presenze");
	            }
            }
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		controller.dbClose(result, statement, dbConnect);
		
		return presenze.toString();
	}
	
	public boolean getPercentualeMinima(String nomeCorso, String matricola) {
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
            query = "SELECT matricola "
            		+ "FROM tasso_minimo_presenza "
            		+ "WHERE matricola = '" + matricola + "' AND id_corsi = '" + idCorso + "' ";
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
	            return true;
            }
		} catch (SQLException e) {
			controller.dbClose(result, statement, dbConnect);
			return false;
        }
		controller.dbClose(result, statement, dbConnect);
		
		return false;
	}
	
    public String massimoMatricola() {
    	String matricola = "";
    	controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
        try {
            String query = "SELECT max(matricola) as matr FROM studenti";
            result = statement.executeQuery(query);
            while (result.next()) {
            	matricola = result.getString("matr");
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        controller.dbClose(result, statement, dbConnect);
        return matricola;
    }

}
