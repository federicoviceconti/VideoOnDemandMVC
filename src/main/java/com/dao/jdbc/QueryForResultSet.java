package com.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Function;

public interface QueryForResultSet {
	/**
	 * This method is used to return result set
	 * @param query the string SQL
	 * @param convertIntoResultSet the function
	 * @param resultSet the result
	 * @return the result set
	 */
	<T, R> T getResultFromStatement(String query, 
			Function<PreparedStatement, R> convertIntoResultSet,
			Function<R, T> resultSet);	
}
