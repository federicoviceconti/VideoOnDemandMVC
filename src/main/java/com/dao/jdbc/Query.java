package com.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class Query implements QueryForResultSet {
	@Override
	public <T, R> T getResultFromStatement(String query, 
			Function<PreparedStatement, R> function,
			Function<R, T> resultSet) {
		try(Connection connection = DaoFactoryJDBC.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			return resultSet.apply(function.apply(statement));
		} catch (SQLException ignored) {
		}
		
		return null;
	}
}