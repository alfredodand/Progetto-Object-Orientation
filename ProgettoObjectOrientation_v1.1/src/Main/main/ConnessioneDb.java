package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import view.LoginWindow;
  
public class ConnessioneDb {
	private Connection dbConnect = null;
	private Statement statement = null;
	private ResultSet result = null;
	private String database1 = "postgres1"; //prova creazione nuovo database
	private String database = "postgres1";
	private String url = "jdbc:postgresql://localhost:5432/";
	private String user = "postgres";
	private String password = "password";
	private static ConnessioneDb connDb = null;
	public ConnessioneDb(){}
	
	public static ConnessioneDb getConnessioneDb() {
		if (connDb == null) {
			connDb = new ConnessioneDb();
		}
		return connDb;
	}
	
	//genera database
	public void generaDatabase(LoginWindow loginwindow){
			try {
				loginwindow.getLable().setText("Database in creazione... Attendere");
				loginwindow.getLable().setVisible(true);
				dbConnect = DriverManager.getConnection(url, user, password);
				statement = dbConnect.createStatement();
				String query = "CREATE DATABASE " + database1;
				statement.executeUpdate(query);
				dbConnect.close();
				statement.close();
				dbConnect = DriverManager.getConnection(url + database1, user, password); 
				statement = dbConnect.createStatement();
				String basePath = new File("").getAbsolutePath();
				String path = new File("src/Main/define_tables.txt").getAbsolutePath();
			    File myObj = new File(path);
			    Scanner myReader = new Scanner(myObj);
			    String data = "";
			    while (myReader.hasNextLine()) {
			        data = data + myReader.nextLine();
			    }
			    myReader.close();
			    statement.execute(data);
			    
				path = new File("src/Main/popolamentodb1.txt").getAbsolutePath();
				myObj = new File(path);
				myReader = new Scanner(myObj);
				data = "";
				while (myReader.hasNextLine()) {
				    data = data + myReader.nextLine();
				}
				myReader.close();
				statement.execute(data);
				
				path = new File("src/Main/popolamentodb2.txt").getAbsolutePath();
				myObj = new File(path);
				myReader = new Scanner(myObj);
				data = "";
				while (myReader.hasNextLine()) {
				    data = data + myReader.nextLine();
				}
				myReader.close();
				statement.execute(data);
				
				path = new File("src/Main/popolamentodb3.txt").getAbsolutePath();
				myObj = new File(path);
				myReader = new Scanner(myObj);
				data = "";
				while (myReader.hasNextLine()) {
				    data = data + myReader.nextLine();
				}
				myReader.close();
				statement.execute(data);
				
				path = new File("src/Main/popolamentodb4.txt").getAbsolutePath();
				myObj = new File(path);
				myReader = new Scanner(myObj);
				data = "";
				while (myReader.hasNextLine()) {
				    data = data + myReader.nextLine();
				}
				myReader.close();
				statement.execute(data);
//		        statement.executeUpdate(data);
				loginwindow.getBtn().setEnabled(true);
				loginwindow.getLable().setText("Database creato");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				if(e.getMessage().contains("esiste")) {
					loginwindow.getBtn().setEnabled(true);
					loginwindow.getLable().setText("");
				}

			}
	}
	
	public void dbConnect() {
		try {
			Class.forName("org.postgresql.Driver");
			dbConnect = DriverManager.getConnection(url + database, user, password);
			statement = dbConnect.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getResult() {
		return result;
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public Connection getDbConnect() {
		return dbConnect;
	}
}