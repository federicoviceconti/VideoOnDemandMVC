package com.videoondemand.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private List<Film> films;
	private static Database db;
	
	private Database() {
		films = new ArrayList<>();
	}
	
	public List<Film> getFilm() {
		return films;
	}

	public static synchronized Database getInstance() {
		return db != null ? db : (db = new Database());
	}

	public int getLastId() {
		if(films.size() == 0) {
			return 1;
		}
		return films.get(films.size() - 1).getId();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return films.toString();
	}
}
