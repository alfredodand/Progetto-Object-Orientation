package DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Controller;

public class LoginDAOimpl implements LoginDAOinterf {
	Controller controller;
	
	public LoginDAOimpl(Controller controller){
		this.controller = controller;
	}
	
	public int LoginCheck(String username, String password, String tbl) {
		controller.dbConnect();
		ResultSet result = controller.getResult();
		Statement statement = controller.getStatement();
		Connection dbConnect = controller.getDbConnect();
		try {
			String query = "SELECT username, password, id_operatore FROM " + tbl + " WHERE username = '" + username + "' AND password = '" + password + "'";
			result = statement.executeQuery(query);
			if(result.isBeforeFirst()) {
				while(result.next()) {
					return result.getInt("id_operatore");
				}
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			controller.dbClose(result, statement, dbConnect);
		}
		return -1;
	}
	
}
