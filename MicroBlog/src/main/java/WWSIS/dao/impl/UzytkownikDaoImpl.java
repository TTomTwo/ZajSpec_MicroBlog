package WWSIS.dao.impl;

import WWSIS.dao.UzytkownikDao;
import WWSIS.model.Uzytkownik;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
public class UzytkownikDaoImpl implements UzytkownikDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Uzytkownik getUserByLogin(String login) {
        String jpql = "SELECT u FROM Uzytkownik u WHERE u.login = :login";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("login", login);

        List<Uzytkownik> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    @Override
    public void registerUser(Uzytkownik uzytkownik) {
        entityManager.persist(uzytkownik);
    }
}