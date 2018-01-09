package com.dao.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

import com.common.Field;
import com.dao.DaoUtente;
import com.videoondemand.model.User;


public class DaoUtenteImplJDBC implements DaoUtente {
	private static DaoUtente instance;
	private static Object object = new Object();
	private static final String LOGIN_USER = "SELECT * FROM utente WHERE username=? AND password=?";
	private static final String ADD_USER = "INSERT INTO user(username, password, ruolo) VALUES(?,?,2)";
	
	private DaoUtenteImplJDBC() {
	}
	
	public static DaoUtente getInstance() {
		if(instance == null) {
			synchronized (object) {
				if(instance == null) {
					return instance = new DaoUtenteImplJDBC();
				}
			}
		}
		
		return instance;
	}
	
	@Override
	public User login(String username, char[] password) {
		return new Query().getResultFromStatement(LOGIN_USER, params -> {
			try {
				params.setString(1, username);
				params.setString(2, String.valueOf(password));
				return params.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}, (resultSet) -> {
			try {
				if(resultSet != null && resultSet.next()) {
					return new User.UserBuilder()
							.setUsername(resultSet.getString(Field.DB.USERNAME))
							.setPassword(resultSet.getString(Field.DB.PASSWORD).toCharArray())
							.build();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		});
	}

	@Override
	public int inserisciUtente(User user) {
		return new Query().getResultFromStatement(ADD_USER, params -> {
			try {
				params.setString(1, user.getUsername());
				params.setCharacterStream(2, new FileReader(user.getPassword().toString()));
				return params.executeUpdate();

			} catch (SQLException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}, (rowAffected) -> rowAffected);
	}

}
