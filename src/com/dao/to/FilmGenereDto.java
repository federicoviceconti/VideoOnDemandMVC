package com.dao.to;

public class FilmGenereDto {
	private FilmDto film;
	
	public FilmGenereDto(FilmDto film) {
		super();
		this.film = film;
	}

	public FilmDto getFilm() {
		return film;
	}

	public void setFilm(FilmDto film) {
		this.film = film;
	}
	
	
}
