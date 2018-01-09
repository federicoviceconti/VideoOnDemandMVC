package com.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.common.Field;
import com.dao.DaoFactory;
import com.dao.DaoFactory.DaoType;
import com.dao.DaoFilm;
import com.dao.DaoGenere;
import com.dao.to.FilmDto;
import com.dao.to.FilmGenereDto;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genere;

public class FacadeFilmImpl implements FacadeFilm {		
	private String getCompleteUri(String filename) {
		return Field.PATH + File.separator + filename;
	}
	
	@Override
	public List<FilmGenereDto> returnFilmInformation(String order) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		DaoFilm daoFilm = daoFactory.getDaoFilm();
		DaoGenere daoGenere = daoFactory.getDaoGenere();
		
		List<FilmGenereDto> results = new ArrayList<>();	
		
		List<Film> films = daoFilm.getAllFilm(order);
		
		for(Film film: films) {
			Genere genere = daoGenere.getGenereById(film.getIdGenere());
			results.add(new FilmGenereDto(
					new FilmDto(
							film.getId(),
							film.getNome(), 
							genere.getId(), genere.getNome(), 
							film.getAnno(), 
							film.getDurata(),
							film.getCast(),
							film.getDescrizione(),
							getCompleteUri(film.getFileName()))
			));
		}
				
		return results;
	}

	@Override
	public int returnSizeFilm() {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		return daoFactory.getDaoFilm().returnSize();
	}

	@Override
	public boolean modifyFilm(int id, FilmGenereDto filmDto) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		DaoFilm daoFilm = daoFactory.getDaoFilm();
		daoFilm.modifyFilm(new Film(
				filmDto.getFilm().getNome(),
				filmDto.getFilm().getGenere().keySet().iterator().next(),
				filmDto.getFilm().getAnno(),
				filmDto.getFilm().getFileName()), id);
		return false;
	}

	@Override
	public boolean deleteFilm(int id) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		DaoFilm daoFilm = daoFactory.getDaoFilm();
		return daoFilm.deleteFilm(id);
	}

	@Override
	public boolean addFilm(FilmGenereDto toAdd) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		DaoFilm daoFilm = daoFactory.getDaoFilm();
        return daoFilm.addFilm(
        		new Film(
        				toAdd.getFilm().getNome(), 
        				toAdd.getFilm().getGenere().keySet().iterator().next(),
        				toAdd.getFilm().getAnno(),
        				toAdd.getFilm().getFileName())
        );	
	}

	@Override
	public FilmGenereDto getFilmById(int id) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		DaoFilm daoFilm = daoFactory.getDaoFilm();
		DaoGenere daoGenere = daoFactory.getDaoGenere();
				
		Film film = daoFilm.getFilmById(id);
		Genere genere = daoGenere.getGenereById(film.getIdGenere());
		return new FilmGenereDto(
				new FilmDto(
						film.getId(),
						film.getNome(), 
						genere.getId(), genere.getNome(), 
						film.getAnno(), 
						film.getDurata(),
						film.getCast(),
						film.getDescrizione(),
        				film.getFileName())
		);
	}

	@Override
	public List<Genere> getAllGeneri() {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		DaoGenere daoGenere = daoFactory.getDaoGenere();
		return daoGenere.getAllGeneri();
	}
}
