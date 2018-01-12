package com.facade;

import com.dao.to.FilmDto;
import com.dao.to.FilmGenereDto;
import com.dao.to.UserDto;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genere;
import com.videoondemand.model.User;

import java.util.Map;

public class FacadeUtils {
    public static Film trasformDtoIntoFilm(FilmGenereDto dto) {
        FilmDto film = dto.getFilm();
        Map<Integer, String> genere = film.getGenere();

        return new Film(
                film.getId(),
                film.getNome(),
                genere.keySet().iterator().next(),
                film.getAnno(),
                film.getDurata(),
                film.getCast(),
                film.getDescrizione(),
                film.getFileName()
        );
    }

    public static FilmGenereDto trasformFilmIntoDto(Film film, Genere genere) {
        return new FilmGenereDto(
                new FilmDto(
                        film.getId(),
                        film.getTitolo(),
                        genere.getId(), genere.getNome(),
                        film.getAnno(),
                        film.getDurata(),
                        film.getCast(),
                        film.getDescrizione(),
                        film.getFilename()
                )
        );
    }

    public static User trasformDtoIntoUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRuolo()
        );
    }
}
