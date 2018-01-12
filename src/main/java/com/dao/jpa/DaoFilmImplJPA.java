package com.dao.jpa;

import com.dao.DaoFilm;
import com.videoondemand.model.Film;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DaoFilmImplJPA implements DaoFilm {
    private static DaoFilmImplJPA instance;
    private static final Object o = new Object();

    private DaoFilmImplJPA() {}

    public static DaoFilmImplJPA getInstance() {
        if(instance == null) {
            synchronized (o) {
                return instance == null ? instance = new DaoFilmImplJPA() : instance;
            }
        }

        return instance;
    }

    @Override
    public boolean addFilm(Film toAdd) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        em.persist(toAdd);
        return true;
    }

    @Override
    public boolean modifyFilm(Film toModify, int id) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        Film film = em.find(Film.class, id);
        film.setTitolo(toModify.getTitolo());
        film.setDescrizione(toModify.getDescrizione());
        film.setAnno(toModify.getAnno());
        film.setCast(toModify.getCast());
        film.setDescrizione(toModify.getDescrizione());
        film.setIdGenere(toModify.getIdGenere());
        return false;
    }

    @Override
    public boolean deleteFilm(int id) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        Query q = em.createNamedQuery("FilmEntity.deleteById");
        q.setParameter("id", id);
        q.getResultList();
        return true;
    }

    @Override
    public Film getFilmById(int id) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        return em.find(Film.class, id);
    }

    @Override
    public List<Film> getAllFilm(String order) {
         EntityManager em = DaoFactoryJPA.getEntityManager();
         Query q = em.createNamedQuery("FilmEntity.findAllOrderedBy");
         q.setParameter("order", order);
         return q.getResultList();
    }

    @Override
    public int returnSize() {
        return DaoFactoryJPA.getEntityManager().createNamedQuery("FilmEntity.findAll").getResultList().size();
    }
}
