package WWSIS.dao.impl;

import WWSIS.dao.UzytkownikDao;
import WWSIS.model.Uzytkownik;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository  // Dodane – Spring bean
public class UzytkownikDaoImpl implements UzytkownikDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Uzytkownik findByLogin(String login) {
        TypedQuery<Uzytkownik> q = entityManager.createQuery(
                "select u from Uzytkownik u where u.login = :login", Uzytkownik.class);
        q.setParameter("login", login);
        return q.getResultList().isEmpty() ? null : q.getResultList().get(0);
    }

    @Override
    public void insert(Uzytkownik uzytkownik) {
        entityManager.persist(uzytkownik);
    }
}