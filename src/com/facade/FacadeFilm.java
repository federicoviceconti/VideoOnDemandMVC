package com.facade;

import java.util.List;

import com.dao.to.FilmDto;
import com.dao.to.FilmGenereDto;
import com.videoondemand.model.Genere;

public interface FacadeFilm extends Facade {
	List<FilmGenereDto> returnFilmInformation(String order);
	int returnSizeFilm();
	boolean modifyFilm(int id, FilmGenereDto filmDto);
	boolean deleteFilm(int id);
	List<Genere> getAllGeneri();
	FilmGenereDto getFilmById(int id);
	boolean addFilm(FilmGenereDto toAdd);
}
