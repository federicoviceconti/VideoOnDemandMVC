/*
package com.dao.inmemory;

import com.dao.DaoFactory;
import com.dao.DaoFilm;
import com.dao.DaoGenere;
import com.dao.jdbc.DaoGenereImplJDBC;

public class DaoFactoryMemory extends DaoFactory{
	private static DaoFactoryMemory daoFactoryMemory;
	
	private DaoFactoryMemory() {
	}
	
	@Override
	public DaoFilm getDaoFilm() {
		return DaoFilmImpl.getInstance();
	}
	
	@Override
	public DaoGenere getDaoGenere() {
		//TODO
		return null;
	}
	
	public static DaoFactoryMemory getInstace() {
		return daoFactoryMemory == null ? (daoFactoryMemory = new DaoFactoryMemory()) : daoFactoryMemory;
	}	
}
*/
