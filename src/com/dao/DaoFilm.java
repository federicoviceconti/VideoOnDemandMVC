package com.dao;

import java.util.List;

import com.dao.to.FilmDto;
import com.videoondemand.model.Film;

public interface DaoFilm {
	boolean addFilm(Film toAdd);
	boolean modifyFilm(Film toModify, int id);
	boolean deleteFilm(int id);
	Film getFilmById(int id);
	List<Film> getAllFilm(String order);
	int returnSize();
}
