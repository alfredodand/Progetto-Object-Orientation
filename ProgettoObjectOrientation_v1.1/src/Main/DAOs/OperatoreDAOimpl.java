package DAOs;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.AreaTematica;
import main.Controller;
import main.Corsi;
import main.Insegnanti;
import main.Lezioni;
import main.Operatore;
import main.Tabella;

public class OperatoreDAOimpl implements OperatoreDAOinterf{
	Operatore operatore;
	Controller controller;
	
	public OperatoreDAOimpl(Operatore operatore, Controller controller) {
		this.operatore = operatore;
		this.controller = controller;
	}
	
	public String IscriviStudenteAlCorso(String matr, String nomeCorso){
		int id_c = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		if(matricolaExist(matr)) {
	        try {
	            String query = "SELECT id_corsi as ID FROM corsi WHERE nome = '" + nomeCorso + "'";
	            result = statement.executeQuery(query);
	            while (result.next()) {
	            	id_c = result.getInt("ID");
	            }
	            if(!iscritto(id_c, matr)) {
		            query = "INSERT INTO iscrizione (matricola, id_corsi) values ('" + matr + "', " + id_c + ")";
		            statement.execute(query);
		            return "Iscrizione avvenuta con successo";
	            }else {
	            	return "Studente già iscritto al corso: " + nomeCorso;
	            }

	        } catch (SQLException e) {
	        	if(e.getMessage().contains("Numero di iscritti massimo raggiunto")) {
	        		return "Numero massimo di iscritti raggiunto";
	        	}

	            return "Errore nell'iscrizione";
	        }finally {
	        	controller.dbClose(result, statement, dbConnect);
	        }
		}else {
			return "La matricola non esiste";
		}
	}
	
	private boolean iscritto(int id, String matricola) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT * FROM iscrizione WHERE matricola = '" + matricola + "' AND id_corsi = " + id + ";";
            result = statement.executeQuery(query);
            	return result.isBeforeFirst();
		}catch(SQLException e) {
			return false;
		}
	}

	public String AggiungiInsegnanteAlCorso(String nome, String cognome, String corso){
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		int ID = 0;
		try {
			String query = "SELECT id FROM insegnanti as I WHERE I.nome = '" + nome + "' AND I.cognome = '" + cognome + "';";
			result = statement.executeQuery(query);			
			while (result.next()) {
				ID = result.getInt("ID");
	        }
			if(!assigned(corso)) {
				query = "UPDATE corsi SET docente = '" + ID + "' WHERE nome = '" + corso + "' AND docente IS NULL;";
				statement.execute(query);
				controller.dbClose(result, statement, dbConnect);
				return "Assegnazione avvenuta con successo";
			}
			else {
				controller.dbClose(result, statement, dbConnect);
				return "Corso già assegnato";
			}
		} catch (SQLException e) {
			return "Errore nell'assegnazione del corso";
		}
	}
	
	private boolean assigned(String corso) throws SQLException {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String query = "SELECT docente FROM corsi WHERE nome = '" + corso + "';";
		result = statement.executeQuery(query);			
		while (result.next()) {
			if(result.getInt("docente") != 0) {
				controller.dbClose(result, statement, dbConnect);
				return true;
			}
	    }
		controller.dbClose(result, statement, dbConnect);
		return false;
	}
	
	public String AggiungiInsegnante (String nome, String cognome, String day, String month, String year, String descrizione){
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "INSERT INTO insegnanti values (DEFAULT, '" + nome + "', '" + cognome + "', '" + year + "-" + month + "-" + day + "', '" + descrizione + "');";
			statement.execute(query);
			controller.dbClose(result, statement, dbConnect);
			return "Insegnante aggiunto con successo";
		} catch (SQLException e) {
			controller.dbClose(result, statement, dbConnect);
			return "Aggiunta insegnante fallita";
		}
	}
	
	public ArrayList<String> getOperatoreInfo(int id){
		ArrayList<String> info = new ArrayList<String>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT nome, cognome, email, username FROM operatore WHERE id_operatore = '" + id +"'" ;
			result = statement.executeQuery(query);
				while (result.next()) {
					info.add(result.getString("nome"));
					info.add(result.getString("cognome"));
					info.add(result.getString("email"));
					info.add(result.getString("username"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return info;
	}
	
	
	public ArrayList<Corsi> getCorsi() {
		String id;
		String nome;
		int docente;
		ArrayList<Corsi> corsi = new ArrayList<Corsi>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
        try {
            String query = "SELECT id_corsi, nome, docente FROM corsi";
            result = statement.executeQuery(query);
            while (result.next()) {
                id = result.getString("id_corsi");
                nome = result.getString("nome");
                docente = result.getInt("docente");
                Corsi corso = new Corsi(nome, id, docente, controller);
                corsi.add(corso);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	controller.dbClose(result, statement, dbConnect);
    	}
        return corsi;
	}
	
	public ArrayList<Insegnanti> getProf() {
		String nome;
		String cognome;
		int id;
		ArrayList<Insegnanti> insegnanti = new ArrayList<Insegnanti>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		java.sql.Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
	    try {
	        String query = "SELECT nome, cognome, id FROM insegnanti";
	        result = statement.executeQuery(query);
	        while (result.next()) {
	            nome = result.getString("nome");
	            cognome = result.getString("cognome");
	            id = result.getInt("id");
	            Insegnanti Insegnanti = new Insegnanti(nome, cognome, id, controller);
	            insegnanti.add(Insegnanti);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }finally {
	    	controller.dbClose(result, statement, dbConnect);
		}
	    return insegnanti;
	}
	
    public void visualizzaIscrizioni(Tabella tbl) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
        String data[] = {"", ""};
        Tabella tabella = tbl;
        try {
            String query = "SELECT I.matricola as matr, C.nome as corso FROM iscrizione as I inner join corsi as C on I.id_corsi = C.id_corsi "
                    + "ORDER BY I.matricola;";
            result = statement.executeQuery(query);
            while (result.next()) {
                data[0] = result.getString("matr");
                data[1] = result.getString("corso");
                tabella.setRow(data);
                tabella.fillTable();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	controller.dbClose(result, statement, dbConnect);
        }
        
    }
    
	public boolean deleteStudente(String matricola){
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Statement statement2 = controller.getStatement();
		Statement statement3 = null;
		Connection dbConnect = controller.getDbConnect();
		String data[] = new String [1];
		int i = 0;
		if(matricolaExist(matricola)) {
	        try {
	        	statement3 = controller.getDbConnect().createStatement();
	        	String query2 = "SELECT id_corsi FROM iscrizione WHERE matricola = '" + matricola + "'";
	        	result = statement2.executeQuery(query2);
	        	while(result.next()) {
	        		String query3 = "UPDATE corsi SET numero_partecipanti = (numero_partecipanti - 1) WHERE id_corsi = " + result.getInt("id_corsi");
	        		statement3.execute(query3);
	        	}	        	
	            String query = "DELETE FROM presenza WHERE matricola = '" + matricola + "' ; DELETE FROM iscrizione WHERE matricola = '" + matricola + "'; DELETE FROM studenti WHERE matricola = '" + matricola + "';";
	            statement.execute(query);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        controller.dbClose(result, statement, dbConnect);
	        return true;
		}
		controller.dbClose(result, statement, dbConnect);
		return false;
	}

	public boolean deleteCorso(String nomeCorso){
		int idCorso = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		idCorso = corsoExist(nomeCorso);
		if(idCorso != -1) {
	        try {
	            String query = "DELETE FROM lezioni WHERE id_corso = '" + idCorso + "'; DELETE FROM iscrizione WHERE id_corsi = '" + idCorso + "'; DELETE FROM area_per_corsi WHERE id_corsi = '" + idCorso + "' ;DELETE FROM corsi WHERE id_corsi = '" + idCorso + "';";
	        	String query1 = "SELECT id_lezioni FROM lezioni WHERE id_corso = " + idCorso;
		        System.out.println(query1);	
	        	result = statement.executeQuery(query1);
	        	String query2 = "";
	        	while(result.next()) {
	        		query2 = query2 + "DELETE FROM presenza WHERE id_lezione = " + result.getInt("id_lezioni") + ";";
	        	}
	        	statement.execute(query2);
	            statement.execute(query);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        controller.dbClose(result, statement, dbConnect);
	        return true;
		}
		controller.dbClose(result, statement, dbConnect);
		return false;
	}
	
	private int corsoExist(String nomeCorso){
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
        try {
            String query = "SELECT id_corsi FROM corsi WHERE nome = '" + nomeCorso + "'";
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
            	while(result.next()) {
            		return result.getInt("id_corsi");
            	}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return -1;
	}

	private boolean matricolaExist(String matricola) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
        try {
            String query = "SELECT matricola FROM studenti WHERE matricola = '" + matricola + "'";
            result = statement.executeQuery(query);
            return result.isBeforeFirst();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        controller.dbClose(result, statement, dbConnect);
		return false;
	}
	
	public String deleteInsegnante(String id){
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
        try {
        	String query = "UPDATE Corsi SET docente = NULL WHERE docente = " + id + ";";
            statement.execute(query);
            query = "DELETE FROM Insegnanti WHERE id = " + id + ";";
            statement.execute(query);
            controller.dbClose(result, statement, dbConnect);
            return "Insegnante cancellato con successo";
        } catch (SQLException e) {
        	controller.dbClose(result, statement, dbConnect);
        	return "Errore durante la cancellazione del professore";
        }
	}
	
	public int getNumeroMatricole() {
		controller.dbConnect();
		int numeroMatricole = 0;
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
        try {
            String query = "SELECT (count(matricola)) as N FROM studenti";
            result = statement.executeQuery(query);
            while(result.next()) {
            	numeroMatricole = result.getInt("N");
            } 
            return numeroMatricole;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	controller.dbClose(result, statement, dbConnect);
    	}
        return -1;
	}
	
	public ArrayList<String> getIscrittiACorso(String nomeCorso){
		ArrayList<String> iscritti = new ArrayList<String>();
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
            String query = "SELECT I.matricola "
            		+ "FROM corsi as C JOIN Iscrizione as I ON C.id_corsi = I.id_corsi "
            		+ "WHERE C.nome = '" + nomeCorso + "'";
            result = statement.executeQuery(query);
            while(result.next()) {
            	iscritti.add(result.getString("matricola"));
            }
            controller.dbClose(result, statement, dbConnect);
            return iscritti;
		} catch (SQLException e) {
			controller.dbClose(result, statement, dbConnect);
			return null;
        }
	}
	
	
	//Il titolo non è utilizzato ma può essere utile se si vuole tenere traccia precisamente a quali lezioni lo studente ha partecipato 
	public void aggiungiPresenze(String matricola, String titolo, String nomeCorso) {
		int idCorso = 0;
		int idLezione = 0;
		controller.dbConnect();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		ResultSet result = controller.getResult();
		try {
			String query = "SELECT id_lezioni FROM lezioni WHERE titolo = '" + titolo + "'";
			
            result = statement.executeQuery(query);
            if(result.isBeforeFirst()) {
            	while(result.next()) {
            		idLezione = result.getInt("id_lezioni");
            	}
            }
            
            idCorso = findIdCorso(nomeCorso, statement);
            if(idCorso != -1) {
            	query = "SELECT matricola FROM presenza WHERE id_lezione = '" + idLezione + "' AND matricola = '" + matricola + "'";
            	
            	result = statement.executeQuery(query);
            	if(!result.isBeforeFirst()) {
            		query = "INSERT INTO presenza VALUES('" + matricola + "', '" + idLezione + "', DEFAULT )";
            		statement.execute(query);
               }
               controller.dbClose(null, statement, dbConnect);
            }

		} catch (SQLException e) {
			controller.dbClose(null, statement, dbConnect);
        }
	}

	private int findIdCorso(String nomeCorso, Statement statement) throws SQLException {
		ResultSet result;
		String query;
		query = "SELECT id_corsi FROM corsi WHERE nome = '" + nomeCorso + "'";
		result = statement.executeQuery(query);
		if(result.isBeforeFirst()) {
			while(result.next()) {
				return result.getInt("id_corsi");
			}
		}
		return -1;
	}
	
	public String addLezione(String selectedDate, String titolo, String descrizione, String durata, String ora, String nomeCorso) {
		int idCorso = 0;
		controller.dbConnect();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		ResultSet result = controller.getResult();
		try {
			idCorso = findIdCorso(nomeCorso, statement);
			if(idCorso != -1) {
				String query = "SELECT id_lezioni FROM lezioni WHERE titolo = '" + titolo + "'";
				System.out.println(query);
	            result = statement.executeQuery(query);
	            if(!result.isBeforeFirst()) {
	            	query = "INSERT INTO lezioni VALUES(DEFAULT, '" + titolo + "', '" + descrizione + "', " + Integer.parseInt(durata) + ", '" + selectedDate + " " + ora + ":00:00" + "', 0,  " + idCorso + ")";
	            	statement.execute(query);
	            	controller.dbClose(null, statement, dbConnect);
	            	return "Aggiunta andata a buon fine";
	            }else {
	            	controller.dbClose(null, statement, dbConnect);
	            	return "Esiste un altra lezione con lo stesso titolo";
	            }
			}else {
				return "Errore nella recapitazione del corso";
			}
		} catch (SQLException e) {
			controller.dbClose(null, statement, dbConnect);
        }
		return "Aggiunta fallita";
	}
	
	public ArrayList<AreaTematica> getAreeTematiche(){
		ArrayList<AreaTematica> areeTematiche = new ArrayList<AreaTematica>();
		controller.dbConnect();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		ResultSet result = controller.getResult();
		try {
	           String query = "SELECT * FROM areaTematica";
	           result = statement.executeQuery(query);
	           while(result.next()) {
	        	   AreaTematica area = new AreaTematica(result.getString("nome"), controller);
	        	   areeTematiche.add(area);
	           }
	            controller.dbClose(null, statement, dbConnect);
		} catch (SQLException e) {
			controller.dbClose(null, statement, dbConnect);
        }
		return areeTematiche;
	}
	
	public boolean addAreaTematica(String nome) {
		controller.dbConnect();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			if(existAreaTematica(nome) == -1) {
				String query = "INSERT INTO AreaTematica VALUES(DEFAULT, '" + nome + "')";
		        statement.execute(query);
		        controller.dbClose(null, statement, dbConnect);
		        return true;
			}
		} catch (SQLException e) {
			controller.dbClose(null, statement, dbConnect);
        }
		return false;
	}


	
	public String assegnaAreaTematica(String areaTematica, String nomeCorso) {
		int idArea = -1;
		int idCorso = -1;
		controller.dbConnect();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			idArea = existAreaTematica(areaTematica);
			idCorso = findIdCorso(nomeCorso, statement);
			
			String query = "INSERT INTO area_per_corsi VALUES(" + idArea + ", " + idCorso + ")";
		    statement.execute(query);
		    controller.dbClose(null, statement, dbConnect);
		    return "Aggiunta avvenuta con successo";
		} catch (SQLException e) {
			controller.dbClose(null, statement, dbConnect);
			return "Le aree uguali sono state aggiunte una volta";
        }
	}
	
	private int existAreaTematica(String nome) {
		controller.dbConnect();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		ResultSet result = controller.getResult();
		try {
			String query = "SELECT id_area_tematica FROM AreaTematica WHERE nome = '" + nome + "'";
		    result = statement.executeQuery(query);
		    while(result.next()) {
		    	return result.getInt("id_area_tematica");
		    }

		} catch (SQLException e) {
			controller.dbClose(null, statement, dbConnect);
        }
		return -1;
	}
	
}
