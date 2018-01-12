package com.dao.jdbc;

import com.common.Field;
import com.dao.DaoFilm;
import com.videoondemand.controller.Validate;
import com.videoondemand.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoFilmImplJDBC implements DaoFilm {
    private static DaoFilmImplJDBC daoFilmImplJDBC;

    private static final String COUNT_NAME_FIELD = "size";
    private static final String INSERT_INTO_FILM = "INSERT INTO film(titolo, anno, id_genere, cast, durata, descrizione, filename) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_FILM = "SELECT id, titolo, anno, id_genere, cast, durata, descrizione, filename FROM film ORDER BY titolo ";
    private static final String SELECT_ONE_FILM = "SELECT id, titolo, anno, id_genere, cast, durata, descrizione, filename FROM film WHERE id=? ";
    private static final String MODIFY_FILM = "UPDATE " + Field.DB.Table.TABLE_FILM + " SET titolo=?, anno=?, id_genere=?, cast=?, durata=?, descrizione=?, filename=? WHERE id=?";
    private static final String DELETE_FILM = "DELETE FROM " + Field.DB.Table.TABLE_FILM + " WHERE id=?";
    private static final String COUNT_FILM = "SELECT COUNT(id) as " + COUNT_NAME_FIELD + " FROM film";

    private DaoFilmImplJDBC() {
    }

    public static synchronized DaoFilm getInstance() {
        return daoFilmImplJDBC == null ? (daoFilmImplJDBC = new DaoFilmImplJDBC()) : daoFilmImplJDBC;
    }

    @Override
    public boolean addFilm(Film toAdd) {
        return new Query().getResultFromStatement(INSERT_INTO_FILM,
                st -> {
                    try {
                        st.setString(1,toAdd.getTitolo());
                        st.setInt(2, toAdd.getAnno());
                        st.setInt(3, toAdd.getIdGenereFk());
                        st.setString(4, toAdd.getCast());
                        st.setInt(5, toAdd.getDurata());
                        st.setString(6, toAdd.getDescrizione());
                        st.setString(7, toAdd.getFilename());
                        return st.executeUpdate() > 0;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return false;
                }, rs -> rs);
    }

    @Override
    public boolean modifyFilm(Film toModify, int id) {
        return new Query().getResultFromStatement(MODIFY_FILM,
                st -> {
                    try {
                        st.setString(1, toModify.getTitolo());
                        st.setInt(2, toModify.getAnno());
                        st.setInt(3, toModify.getIdGenereFk());
                        st.setString(4, toModify.getCast());
                        st.setInt(5, toModify.getDurata());
                        st.setString(6, Validate.checkIfIsNullOrEmpty(toModify.getDescrizione()));
                        st.setInt(6, id);
                        int res = st.executeUpdate();
                        return res > 0;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return false;
                }, rs -> rs);
    }

    @Override
    public boolean deleteFilm(int id) {
        return new Query().getResultFromStatement(DELETE_FILM,
                st -> {
                    try {
                        st.setInt(1, id);
                        return st.executeUpdate() > 0;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return false;
                }, rs -> rs);
    }

    @Override
    public List<Film> getAllFilm(String order) {
        String orderBy = Validate.checkIfIsNullOrEmpty(order).isEmpty() ? "ASC" : order;
        return new Query().getResultFromStatement(SELECT_ALL_FILM + orderBy,
                params -> {
                    ResultSet rs = null;

                    try {
                        return params.executeQuery();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return rs;
                },
                resultSet -> {
                    List<Film> listFilm = new ArrayList<>();

                    try {
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                int id = resultSet.getInt(Field.DB.ID);
                                String titolo = resultSet.getString(Field.DB.TITOLO) != null ? resultSet.getString(Field.DB.TITOLO) : "";
                                int id_genere = resultSet.getInt(Field.DB.ID_GENERE);
                                int anno = resultSet.getInt(Field.DB.ANNO);

                                if (id > 0 && !titolo.isEmpty() && id_genere > 0 && anno > 0) {
                                    listFilm.add(new Film(
                                            resultSet.getInt(Field.DB.ID),
                                            resultSet.getString(Field.DB.TITOLO),
                                            resultSet.getInt(Field.DB.ID_GENERE),
                                            resultSet.getInt(Field.DB.ANNO), 0, null, null,
                                            resultSet.getString(Field.DB.FILENAME)));
                                }
                            }
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    return listFilm;
                }
        );
    }

    @Override
    public int returnSize() {
        return new Query().getResultFromStatement(SELECT_ALL_FILM,
                (PreparedStatement params) -> {
                    try {
                        return params.executeQuery(COUNT_FILM);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    return null;
                },
                resultSet -> {
                    try {
                        if (resultSet.next()) {
                            return resultSet.getInt(resultSet.findColumn(COUNT_NAME_FIELD));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    @Override
    public Film getFilmById(int id) {
        if (id > 0) {
            return new Query().getResultFromStatement(SELECT_ONE_FILM,
                    statement -> {
                        try {
                            statement.setInt(1, id);
                            return statement.executeQuery();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }, resultSet -> {
                        try {
                            while (resultSet.next()) {
                                int idFilm = resultSet.getInt(Field.DB.ID);
                                String titolo = resultSet.getString(Field.DB.TITOLO) != null ? resultSet.getString(Field.DB.TITOLO) : "";
                                int id_genere = resultSet.getInt(Field.DB.ID_GENERE);
                                int anno = resultSet.getInt(Field.DB.ANNO);
                                String filename = resultSet.getString(Field.DB.FILENAME);

                                if (!titolo.isEmpty() && id_genere > 0 && anno > 0) {
                                    return new Film(idFilm, titolo, id_genere, anno, 0, null, null, filename);
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

            );
        }

        return null;
    }
}
