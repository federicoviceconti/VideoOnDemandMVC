package com.dao.jpa;

import com.dao.DaoGenere;
import com.videoondemand.model.Genere;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DaoGenereImplJPA implements DaoGenere {
    private static DaoGenereImplJPA instance;
    private static final Object o = new Object();

    private DaoGenereImplJPA() {}

    public static DaoGenereImplJPA getInstance() {
        if(instance == null) {
            synchronized (o) {
                return instance == null ? instance = new DaoGenereImplJPA() : instance;
            }
        }

        return instance;
    }

    @Override
    public List<Genere> getAllGeneri() {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        Query q = em.createNamedQuery("GenereEntity.findAll");
        List<Genere> list = (List<Genere>)q.getResultList();
        em.close();
        return list;
    }

    @Override
    public Genere getGenereById(int id) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        Genere genere = em.find(Genere.class, id);
        em.close();
        return null;
    }

    @Override
    public boolean addGenere(Genere genere) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        em.persist(genere);
        em.close();
        return true;
    }

    @Override
    public boolean modifyGenere() {
        return false;
    }

    @Override
    public boolean deleteGenere() {
        return false;
    }
}
