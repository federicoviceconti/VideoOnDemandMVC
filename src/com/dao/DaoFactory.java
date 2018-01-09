package com.dao;

import com.dao.jdbc.DaoFactoryJDBC;

public abstract class DaoFactory {
	public enum DaoType { INMEMORY, JDBCCONNECTION }
	
	public abstract DaoFilm getDaoFilm();
	public abstract DaoGenere getDaoGenere();
	public abstract DaoUtente getDaoUtente();
	
	public static DaoFactory getDao(DaoType type) {
		switch (type) {
			case INMEMORY:
/*
				return DaoFactoryMemory.getInstace();
*/
			case JDBCCONNECTION:
				return DaoFactoryJDBC.getInstace();
			default: 
				throw new RuntimeException("Not valid type");
		}
	}
}
