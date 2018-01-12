package com.dao.jpa;

import com.dao.DaoUtente;
import com.videoondemand.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class DaoUtenteImplJPA implements DaoUtente {
    private static DaoUtenteImplJPA instance;
    private static final Object o = new Object();

    private DaoUtenteImplJPA() {}

    public static DaoUtenteImplJPA getInstance() {
        if(instance == null) {
            synchronized (o) {
                return instance == null ? instance = new DaoUtenteImplJPA() : instance;
            }
        }

        return instance;
    }

    @Override
    public User login(String username, char[] password) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        Query q = em.createNamedQuery("UtenteEntity.findUser", User.class);
        q.setParameter("usr", username);
        q.setParameter("pswd", String.valueOf(password));
        User user = (User)q.getSingleResult();
        em.close();
        return user;
    }

    @Override
    public int inserisciUtente(User user) {
        EntityManager em = DaoFactoryJPA.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(user);
        et.commit();
        em.close();
        return 1;
    }
}
