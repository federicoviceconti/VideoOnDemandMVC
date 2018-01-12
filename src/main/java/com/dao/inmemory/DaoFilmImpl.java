/*
package com.dao.inmemory;

import java.util.ArrayList;
import java.util.List;

import com.dao.DaoFilm;
import com.videoondemand.model.Database;
import com.videoondemand.model.Film;

public class DaoFilmImpl implements DaoFilm{
	private static Database db;
	private static DaoFilmImpl daoFilmImpl;

	private DaoFilmImpl() {
		// TODO Auto-generated constructor stub	
		db = Database.getInstance();
	}
	*/
/**//*

	@Override
	public boolean addFilm(Film filmtoAdd) {
		if(!filmtoAdd.getNome().isEmpty() && filmtoAdd.getIdGenere() > 0 && filmtoAdd.getAnno() > 0) {
			db.getFilm().add(new Film(db.getLastId(), filmtoAdd.getNome(), filmtoAdd.getIdGenere(), filmtoAdd.getAnno(), 0, null, null));
			return true;
		}			
		
		return false;
	}
	
	public static synchronized DaoFilmImpl getInstance() {
		return daoFilmImpl == null ? (daoFilmImpl = new DaoFilmImpl()) : daoFilmImpl;
	}

	@Override
	public int returnSize() {
		// TODO Auto-generated method stub
		return db.getFilm().size();
	}

	@Override
	public List<Film> getAllFilm() {
		List<Film> res = new ArrayList<>();
		// TODO Auto-generated method stub
		for(Film film: db.getFilm()) {
			res.add(new Film(film.getId(), film.getNome(), film.getIdGenere(), film.getAnno(), 0, null, null));
		}
		return res;
	}

	@Override
	public boolean modifyFilm(Film toModify, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFilm(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Film getFilmById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
*/
