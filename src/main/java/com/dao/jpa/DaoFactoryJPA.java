package com.dao.jpa;

import com.common.Field;
import com.dao.DaoFactory;
import com.dao.DaoFilm;
import com.dao.DaoGenere;
import com.dao.DaoUtente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactoryJPA extends DaoFactory {
    private static DaoFactoryJPA daoFactoryJPA;
    private static final Object o = new Object();
    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(Field.JPA_CONN);

    private DaoFactoryJPA() {}

    public static DaoFactoryJPA getInstance() {
        if(daoFactoryJPA == null) {
            synchronized (o) {
                return daoFactoryJPA == null ? daoFactoryJPA = new DaoFactoryJPA() : daoFactoryJPA;
            }
        }

        return daoFactoryJPA;
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    @Override
    public DaoFilm getDaoFilm() {
        return DaoFilmImplJPA.getInstance();
    }

    @Override
    public DaoGenere getDaoGenere() {
        return DaoGenereImplJPA.getInstance();
    }

    @Override
    public DaoUtente getDaoUtente() {
        return DaoUtenteImplJPA.getInstance();
    }
}
