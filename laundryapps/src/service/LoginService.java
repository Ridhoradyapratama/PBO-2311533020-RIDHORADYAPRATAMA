package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import confg.Database;
import model.User;

public class LoginService {
	
	public boolean authenticate(User user) {
		String query = "SELECT * FROM user WHERE username = ? AND password =?";
		
		try (Connection conn = Database.getConnection();
				PreparedStatement Statement = conn.prepareStatement(query)){
			Statement.setString(1, user.getUsername());
			Statement.setString(2, user.getPassword());
			
			ResultSet resultSet = Statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			//TODO: handle exception
			e.printStackTrace();
		}
		return false;
	
	
	}

}
