package UserGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManagerUserGenerator {

	Connection dbConnect = null;
	Statement statement = null;
	ResultSet result = null;
	ResultSet result2 = null;

	public void AddOperatore(String nome, String cognome, String email, String password, String username) throws SQLException {
		this.dbConnect();
		String query = "INSERT INTO operatore(nome, cognome, email, password, username) VALUES('" +nome+"','"+cognome+"','"+email+"','"+password+"','"+username+"')";
		statement.executeUpdate(query);
	}
	
	
	public boolean ExistInDatabase(String username, String nometabella) throws SQLException {
		this.dbConnect();
		String query = "SELECT * FROM " + nometabella;
		result = statement.executeQuery(query);
			while (result.next()) {
				if(username.equals(result.getString("username"))){
					return true;
				}
			}
			return false;
	}
	
	public void dbConnect() throws SQLException {
		dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres1", "postgres", "password");
		statement = dbConnect.createStatement();
	}	

}
