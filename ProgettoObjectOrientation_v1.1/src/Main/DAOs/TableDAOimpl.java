package DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import main.Controller;
import main.Tabella;

public class TableDAOimpl implements TableDAOinterf {
	Controller controller;
	DecimalFormat numberFormat = new DecimalFormat("#.00");
	public TableDAOimpl(Controller controller){
		this.controller = controller;
	}
	
	public void numeroMedioPresenze(Tabella tbl){
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", ""};
		Tabella tabella = tbl;
		try {
			String query = "SELECT nome_corso, media_presenze FROM tasso_frequenza";
			result = statement.executeQuery(query);
			while (result.next()) {
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("media_presenze");
				Double tmp = Double.parseDouble(data[1]);
				String tmp2 = numberFormat.format(tmp);
				data[1] = tmp2;
				tabella.setRow(data);
				tabella.fillTable();
			}		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
	}
	
	public void numeroMinimoPresenze(Tabella tbl) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", ""};
		Tabella tabella = tbl;
		try {
			String query = "SELECT nome_corso, minimo_presenze FROM tasso_frequenza";
			result = statement.executeQuery(query);
			while (result.next()) {
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("minimo_presenze");
				tabella.setRow(data);
				tabella.fillTable();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
	}
	
	public void numeroMassimoPresenze(Tabella tbl) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", ""};
		Tabella tabella = tbl;
		try {
			String query = "SELECT nome_corso, massimo_presenze FROM tasso_frequenza";
			result = statement.executeQuery(query);
			while (result.next()) {
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("massimo_presenze");
				tabella.setRow(data);
				tabella.fillTable();
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
	}
	
	public void percentualeMediaPresenze(Tabella tbl) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", ""};
		Tabella tabella = tbl;
		try {
			String query = "SELECT nome_corso, riempimento_medio FROM tasso_frequenza";
			result = statement.executeQuery(query);
			while (result.next()) {
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("riempimento_medio");
				Double tmp = Double.parseDouble(data[1]);
				String tmp2 = numberFormat.format(tmp);
				data[1] = tmp2;
				tabella.setRow(data);
				tabella.fillTable();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
	}
	
	public int statisticheStudenti(DefaultTableModel model, Controller controller) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		ResultSet result2 = null;
		Statement statement = controller.getStatement();
		Statement statement2 = null;
		Integer corsiIdonei = 0;
		try {
			statement2 = controller.getDbConnect().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", ""};
		int count = 0;
		try {
			String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola";
			result = statement.executeQuery(query);			
			while (result.next()) {
				String str = result.getString("nome") + " " + result.getString("cognome");
				
				data[0] = str;
				data[1] = result.getString("data_nascita");
				data[2] = result.getString("corsi_iscritto");
				
				String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola";
				result2 = statement2.executeQuery(query2);
				while (result2.next()) {					
					corsiIdonei = result2.getInt("corsi_idonei");
				}
				
				data[3] = corsiIdonei.toString();
				corsiIdonei = 0;
				data[4] = result.getString("sesso");
				data[5] = result.getString("matricola");
				model.addRow(data);	
				count++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int statisticheStudentiNomeCognome(DefaultTableModel model, Controller controller, String src) {
		int count = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		ResultSet result2 = null;
		Statement statement = controller.getStatement();
		Statement statement2 = null;
		Integer corsiIdonei = 0;
		try {
			statement2 = controller.getDbConnect().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String nome = "";
		String cognome = "";
		if(src.indexOf(" ") != -1) {
			nome = src.substring(0, src.indexOf(" "));
			cognome = src.substring(src.indexOf(" ") + 1);
		}else {
			nome = src;
			cognome = src;
		}
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", ""};
		try {
			
			String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola WHERE UPPER(S.nome) LIKE '" + nome.toUpperCase() + "%' OR UPPER(S.cognome) LIKE '" + cognome.toUpperCase() + "%' GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola";
			result = statement.executeQuery(query);				
			while (result.next()) {
				data[0] = result.getString("nome") + " " + result.getString("cognome");
				data[1] = result.getString("data_nascita");
				data[2] = result.getString("corsi_iscritto");
				
				String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola";
				result2 = statement2.executeQuery(query2);
				while (result2.next()) {					
					corsiIdonei = result2.getInt("corsi_idonei");
				}
				
				data[3] = corsiIdonei.toString();
				corsiIdonei = 0;
				data[4] = result.getString("sesso");
				data[5] = result.getString("matricola");
				model.addRow(data);		
				count++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	
	public int statisticheStudentiDataNascita(DefaultTableModel model, Controller controller, String src) {
		int count = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		ResultSet result2 = null;
		Statement statement = controller.getStatement();
		Statement statement2 = null;
		Integer corsiIdonei = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", ""};
		try {
			statement2 = controller.getDbConnect().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			formatter.parse(src);
			try {
				String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola WHERE S.data_nascita = '" + src + "' GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola";
				result = statement.executeQuery(query);				
				while (result.next()) {
					String str = result.getString("nome") + " " + result.getString("cognome");
					
					data[0] = str;
					data[1] = result.getString("data_nascita");
					data[2] = result.getString("corsi_iscritto");
					
					String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola";
					result2 = statement2.executeQuery(query2);
					while (result2.next()) {					
						corsiIdonei = result2.getInt("corsi_idonei");
					}
					
					data[3] = corsiIdonei.toString();
					corsiIdonei = 0;
					data[4] = result.getString("sesso");
					data[5] = result.getString("matricola");
					model.addRow(data);	
					count++;
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				controller.dbClose(result, statement, dbConnect);
			}
			
		} catch (ParseException e2) {
			return 0;
		}

		return count;
	}
	
	public int statisticheStudentiMatricola(DefaultTableModel model, Controller controller, String src) {
		int count = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		ResultSet result2 = null;
		Statement statement = controller.getStatement();
		Statement statement2 = null;
		Integer corsiIdonei = 0;
		try {
			statement2 = controller.getDbConnect().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", ""};
		try {
			String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola WHERE UPPER(S.matricola) LIKE '%" + src.toUpperCase() + "%' GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola";
			result = statement.executeQuery(query);				
			while (result.next()) {
				String str = result.getString("nome") + " " + result.getString("cognome");
				
				data[0] = str;
				data[1] = result.getString("data_nascita");
				data[2] = result.getString("corsi_iscritto");
				
				String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola";
				result2 = statement2.executeQuery(query2);
				while (result2.next()) {					
					corsiIdonei = result2.getInt("corsi_idonei");
				}
				
				data[3] = corsiIdonei.toString();
				corsiIdonei = 0;
				data[4] = result.getString("sesso");
				data[5] = result.getString("matricola");
				model.addRow(data);	
				count++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int statisticheStudentiCorsiIscritto(DefaultTableModel model, Controller controller, String src) {
		int count = 0;
		if(!src.isEmpty()) {
			controller.dbConnect();
			ResultSet result = controller.getResult();
			ResultSet result2 = null;
			Statement statement = controller.getStatement();
			Statement statement2 = null;
			Integer corsiIdonei = 0;
			try {
				statement2 = controller.getDbConnect().createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			Connection dbConnect = controller.getDbConnect();
			String data[] = {"", "", "", "", "", ""};
			try {
				String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola HAVING (count(I.id_corsi)) = " + src;
				result = statement.executeQuery(query);				
				while (result.next()) {
					String str = result.getString("nome") + " " + result.getString("cognome");
					
					data[0] = str;
					data[1] = result.getString("data_nascita");
					data[2] = result.getString("corsi_iscritto");
					
					String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola";
					result2 = statement2.executeQuery(query2);
					while (result2.next()) {					
						corsiIdonei = result2.getInt("corsi_idonei");
					}
					
					data[3] = corsiIdonei.toString();
					corsiIdonei = 0;
					data[4] = result.getString("sesso");
					data[5] = result.getString("matricola");
					model.addRow(data);	
					count++;
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				controller.dbClose(result, statement, dbConnect);
			}
		}
		
		return count;
	}
	
	public int statisticheStudentiCorsiIdonei(DefaultTableModel model, Controller controller, String src) {
		int count = 0;
		if(!src.isEmpty()) {
			controller.dbConnect();
			ResultSet result = controller.getResult();
			ResultSet result2 = null;
			Statement statement = controller.getStatement();
			Statement statement2 = null;
			Integer corsiIdonei = 0;
			try {
				statement2 = controller.getDbConnect().createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	
			Connection dbConnect = controller.getDbConnect();
			String data[] = {"", "", "", "", "", ""};
			try {
				String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola";
				result = statement.executeQuery(query);				
				while (result.next()) {
					String str = result.getString("nome") + " " + result.getString("cognome");
					
					data[0] = str;
					data[1] = result.getString("data_nascita");
					data[2] = result.getString("corsi_iscritto");
					
					data[4] = result.getString("sesso");
					data[5] = result.getString("matricola");
					
					String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola HAVING (count(matricola)) = " + src;
					result2 = statement2.executeQuery(query2);
					if(result2.isBeforeFirst()) {
						while (result2.next()) {					
							corsiIdonei = result2.getInt("corsi_idonei");
						}
						data[3] = corsiIdonei.toString();
						model.addRow(data);
						count++;
					}		
					
					corsiIdonei = 0;
					
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				controller.dbClose(result, statement, dbConnect);
			}
		}
		return count;
	}
	
	public int statisticheStudentiSesso(DefaultTableModel model, Controller controller, String src) {
		int count = 0;
		controller.dbConnect();
		ResultSet result = controller.getResult();
		ResultSet result2 = null;
		Statement statement = controller.getStatement();
		Statement statement2 = null;
		Integer corsiIdonei = 0;
		try {
			statement2 = controller.getDbConnect().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", ""};
		try {
			String query = "SELECT S.nome, S.cognome, S.sesso, S.data_nascita, S.matricola, (count(I.id_corsi)) as corsi_iscritto FROM studenti as S left outer join iscrizione as I on S.matricola = I.matricola WHERE UPPER(S.sesso) = '" + src.toUpperCase() + "' GROUP BY S.nome, S.cognome, S.sesso, s.data_nascita, S.matricola";
			result = statement.executeQuery(query);
			while (result.next()) {
				String str = result.getString("nome") + result.getString("cognome");
				
				data[0] = str;
				data[1] = result.getString("data_nascita");
				data[2] = result.getString("corsi_iscritto");
				
				String query2 = "SELECT matricola, (count(matricola)) as corsi_idonei FROM tasso_minimo_presenza WHERE matricola = '" + result.getString("matricola") + "' GROUP BY matricola";
				result2 = statement2.executeQuery(query2);
				while (result2.next()) {					
					corsiIdonei = result2.getInt("corsi_idonei");
				}
				
				data[3] = corsiIdonei.toString();
				corsiIdonei = 0;
				data[4] = result.getString("sesso");
				data[5] = result.getString("matricola");
				model.addRow(data);
				count++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int visualizzaCorsi(DefaultTableModel model, Controller controller) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		ResultSet result2 = null;
		Statement statement2 = null;
		String data[] = {"", "", "", "", "", "", ""};
		int count = 0;
		
		try {
			statement2 = controller.getDbConnect().createStatement();
			String query = "SELECT c.nome as nome_corso, c.descrizione, tasso_minimo_presenze, numero_partecipanti, numero_lezioni, a.nome as area_tematica, i.nome as nome_docente, i.cognome as cognome_docente FROM corsi as c INNER JOIN insegnanti as i on i.id = c.docente INNER JOIN areaTematica as a ON c.id_area_tematica = a.id_area_tematica";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("descrizione");
				data[2] = result.getString("tasso_minimo_presenze");				
				data[3] = result.getString("numero_partecipanti");
				data[4] = result.getString("numero_lezioni");
				String query2 = "SELECT a.nome as nome_area FROM areaTematica as a INNER JOIN area_per_corsi as apc ON a.id_area_tematica = apc.id_area_tematica INNER JOIN corsi as c ON c.id_corsi = apc.id_corsi WHERE c.nome = '" + result.getString("nome_corso") + "'";
				result2 = statement2.executeQuery(query2);
				while(result2.next()) {
					data[5] = data[5] + result2.getString("nome_area") + ", ";
				}
				data[5] = data[5].substring(0, data[5].length()-2);
				data[6] = result.getString("nome_docente") + " " + result.getString("cognome_docente");
				model.addRow(data);	
				count++;
				data[5] = "";
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int visualizzaCorsiNome(DefaultTableModel model, Controller controller, String src) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		ResultSet result2 = null;
		Statement statement2 = null;
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", "", ""};
		int count = 0;
		
		try {
			statement2 = controller.getDbConnect().createStatement();
			String query = "SELECT c.nome as nome_corso, c.descrizione, tasso_minimo_presenze, numero_partecipanti, numero_lezioni, a.nome as area_tematica, i.nome as nome_docente, i.cognome as cognome_docente FROM corsi as c INNER JOIN insegnanti as i on i.id = c.docente INNER JOIN areaTematica as a ON c.id_area_tematica = a.id_area_tematica WHERE UPPER(c.nome) LIKE '%" + src.toUpperCase() + "%'";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("descrizione");
				data[2] = result.getString("tasso_minimo_presenze");				
				data[3] = result.getString("numero_partecipanti");
				data[4] = result.getString("numero_lezioni");
				String query2 = "SELECT a.nome as nome_area FROM areaTematica as a INNER JOIN area_per_corsi as apc ON a.id_area_tematica = apc.id_area_tematica INNER JOIN corsi as c ON c.id_corsi = apc.id_corsi WHERE c.nome = '" + result.getString("nome_corso") + "'";
				result2 = statement2.executeQuery(query2);
				while(result2.next()) {
					data[5] = data[5] + result2.getString("nome_area") + ", ";
				}
				data[5] = data[5].substring(0, data[5].length()-2);
				data[6] = result.getString("nome_docente") + " " + result.getString("cognome_docente");
				model.addRow(data);	
				count++;
				data[5] = "";
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int visualizzaCorsiParolaChiave(DefaultTableModel model, Controller controller, String src) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		ResultSet result2 = null;
		Statement statement2 = null;
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", "", ""};
		int count = 0;
		
		try {
			statement2 = controller.getDbConnect().createStatement();
			String query = "SELECT c.nome as nome_corso, c.descrizione, tasso_minimo_presenze, numero_partecipanti, numero_lezioni, a.nome as area_tematica, i.nome as nome_docente, i.cognome as cognome_docente FROM corsi as c INNER JOIN insegnanti as i on i.id = c.docente INNER JOIN areaTematica as a ON c.id_area_tematica = a.id_area_tematica WHERE UPPER(c.descrizione) LIKE '%" + src.toUpperCase() + "%'";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("descrizione");
				data[2] = result.getString("tasso_minimo_presenze");				
				data[3] = result.getString("numero_partecipanti");
				data[4] = result.getString("numero_lezioni");
				String query2 = "SELECT a.nome as nome_area FROM areaTematica as a INNER JOIN area_per_corsi as apc ON a.id_area_tematica = apc.id_area_tematica INNER JOIN corsi as c ON c.id_corsi = apc.id_corsi WHERE c.nome = '" + result.getString("nome_corso") + "'";
				result2 = statement2.executeQuery(query2);
				while(result2.next()) {
					data[5] = data[5] + result2.getString("nome_area") + ", ";
				}
				data[5] = data[5].substring(0, data[5].length()-2);
				data[6] = result.getString("nome_docente") + " " + result.getString("cognome_docente");
				model.addRow(data);	
				count++;
				data[5] = "";
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int visualizzaCorsiAreaTematica(DefaultTableModel model, Controller controller, String src) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		ResultSet result2 = null;
		Statement statement2 = null;
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", "", ""};
		int count = 0;
		
		try {
			statement2 = controller.getDbConnect().createStatement();
			String query = "SELECT apc.id_corsi, c1.nome as nome_corso, c1.descrizione, c1.tasso_minimo_presenze, c1.numero_lezioni, c1.numero_partecipanti, a_t.nome as area_tematica, i.nome as nome_docente, i.cognome as cognome_docente FROM area_per_corsi AS apc INNER JOIN areatematica AS a_t ON apc.id_area_tematica = a_t.id_area_tematica INNER JOIN corsi as c1 on c1.id_corsi = apc.id_corsi INNER JOIN insegnanti as i on c1.docente = i.id WHERE a_t.nome = '" + src + "'";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("descrizione");
				data[2] = result.getString("tasso_minimo_presenze");				
				data[3] = result.getString("numero_partecipanti");
				data[4] = result.getString("numero_lezioni");
				String query2 = "SELECT a.nome as nome_area FROM areaTematica as a INNER JOIN area_per_corsi as apc ON a.id_area_tematica = apc.id_area_tematica INNER JOIN corsi as c ON c.id_corsi = apc.id_corsi WHERE c.nome = '" + result.getString("nome_corso") + "'";
				result2 = statement2.executeQuery(query2);
				while(result2.next()) {
					data[5] = data[5] + result2.getString("nome_area") + ", ";
				}
				data[5] = data[5].substring(0, data[5].length()-2);
				data[6] = result.getString("nome_docente") + " " + result.getString("cognome_docente");
				model.addRow(data);	
				count++;
				data[5] = "";
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int visualizzaCorsiDocente(DefaultTableModel model, Controller controller, String nomeCompleto) {
		ResultSet result2 = null;
		Statement statement2 = null;
		String nome_docente = "";
		String cognome_docente = "";
		if(nomeCompleto.indexOf(" ") != -1) {
			nome_docente = nomeCompleto.substring(0, nomeCompleto.indexOf(" "));
			cognome_docente = nomeCompleto.substring(nomeCompleto.indexOf(" ") + 1);
		}else {
			nome_docente = nomeCompleto;
			cognome_docente = nomeCompleto;
		}
		

		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {"", "", "", "", "", "", ""};
		int count = 0;
		
		try {
			statement2 = controller.getDbConnect().createStatement();
			String query = "SELECT c.nome as nome_corso, c.descrizione, tasso_minimo_presenze, numero_partecipanti, numero_lezioni, a.nome as area_tematica, i.nome as nome_docente, i.cognome as cognome_docente FROM corsi as c INNER JOIN insegnanti as i on i.id = c.docente INNER JOIN areaTematica as a ON c.id_area_tematica = a.id_area_tematica WHERE UPPER(i.nome) LIKE '%" + nome_docente.toUpperCase() + "%' OR UPPER(i.cognome) LIKE '%" + cognome_docente.toUpperCase() + "%'";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				data[0] = result.getString("nome_corso");
				data[1] = result.getString("descrizione");
				data[2] = result.getString("tasso_minimo_presenze");				
				data[3] = result.getString("numero_partecipanti");
				data[4] = result.getString("numero_lezioni");
				String query2 = "SELECT a.nome as nome_area FROM areaTematica as a INNER JOIN area_per_corsi as apc ON a.id_area_tematica = apc.id_area_tematica INNER JOIN corsi as c ON c.id_corsi = apc.id_corsi WHERE c.nome = '" + result.getString("nome_corso") + "'";
				result2 = statement2.executeQuery(query2);
				while(result2.next()) {
					data[5] = data[5] + result2.getString("nome_area") + ", ";
				}
				data[5] = data[5].substring(0, data[5].length()-2);
				data[6] = result.getString("nome_docente") + " " + result.getString("cognome_docente");
				model.addRow(data);	
				count++;
				data[5] = "";
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	
	public int presentiALezione(DefaultTableModel model, Controller controller, String lezione) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {""};
		int count = 0;
		try {
			String query = "SELECT DISTINCT p.matricola FROM presenza AS p INNER JOIN lezioni AS l ON p.id_lezione = l.id_lezioni WHERE l.titolo = '" + lezione + "'";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				data[0] = result.getString("matricola");
				model.addRow(data);	
				count++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
	public int assentiALezione(DefaultTableModel model, Controller controller, String lezione) {
		controller.dbConnect();
		Statement statement2 = null;
		ResultSet result = controller.getResult();
		ResultSet result2 = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		String data[] = {""};
		int count = 0;
		String tmp = "";
		
		try {
			String query = "SELECT id_corso FROM lezioni AS l WHERE l.titolo = '" + lezione + "'";
			result = statement.executeQuery(query);			
			while (result.next()) {				
				tmp = result.getString("id_corso");
			}
			statement2 = controller.getDbConnect().createStatement();
			String query2 = "SELECT matricola FROM iscrizione AS i WHERE i.id_corsi = " + tmp + " AND i.matricola NOT IN (SELECT DISTINCT p.matricola FROM presenza AS p INNER JOIN lezioni AS l ON p.id_lezione = l.id_lezioni WHERE l.titolo = '" + lezione + "')";
			result2 = statement2.executeQuery(query2);
			while (result2.next()) {				
				data[0] = result2.getString("matricola");
				model.addRow(data);	
				count++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return count;
	}
	
}
