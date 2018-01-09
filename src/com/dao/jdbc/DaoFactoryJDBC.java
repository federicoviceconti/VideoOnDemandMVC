package com.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dao.DaoFactory;
import com.dao.DaoFilm;
import com.dao.DaoGenere;
import com.dao.DaoUtente;

public class DaoFactoryJDBC extends DaoFactory {
	private static DaoFactoryJDBC daoFactoryJDBC;
	public static final String JAVA_JDBC_CONN = "java:/comp/env/jdbc/videoondemand";
	
	private DaoFactoryJDBC() {
	}
	
	@Override
	public DaoFilm getDaoFilm() {
		return DaoFilmImplJDBC.getInstance();
	}
	
	@Override
	public DaoGenere getDaoGenere() {
		return DaoGenereImplJDBC.getInstance();
	}
	
	@Override
	public DaoUtente getDaoUtente() {
		// TODO Auto-generated method stub
		return DaoUtenteImplJDBC.getInstance();
	}
	
	public static DaoFactoryJDBC getInstace() {
		return daoFactoryJDBC == null ? daoFactoryJDBC = new DaoFactoryJDBC() : daoFactoryJDBC;
	}
	
	public static Connection getConnection() {
		try {
			InitialContext ic = new InitialContext();
			DataSource dSource = (DataSource)ic.lookup(JAVA_JDBC_CONN);
			return dSource.getConnection();
		}catch(NamingException namingException) {
			System.out.println("Error naming: " + namingException);
		}catch (SQLException sqlEx) {
			System.out.println("Error sql: " + sqlEx);
		}
		
		return null;
	}	
}
