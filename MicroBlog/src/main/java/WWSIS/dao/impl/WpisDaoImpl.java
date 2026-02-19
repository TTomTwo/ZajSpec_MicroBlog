package WWSIS.dao.impl;

import WWSIS.dao.WpisDao;
import WWSIS.model.Wpis;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WpisDaoImpl implements WpisDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Wpis> timeline(Integer userId) {  // Integer, bo FK
        return entityManager.createQuery(
                        "select w from Wpis w " +
                                "where w.uzytkownikId = :userId " +  // FK uzytkownikId, nie uzytkownik.id
                                "order by w.dataUtworzenia desc", Wpis.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Wpis> publicTimeline() {
        return entityManager.createQuery(
                        "select w from Wpis w " +
                                "order by w.dataUtworzenia desc", Wpis.class)
                .getResultList();
    }

    @Override
    public List<Wpis> fullTimeline(Integer userId) {
        // full timeline = moje wpisy + wpisy osób, które obserwuję
        // Zakładam FK uzytkownikId w Wpis, followerId/uzytkownikId w Follower

        return entityManager.createQuery(
                        "select w from Wpis w " +
                                "where w.uzytkownikId = :userId " +
                                "   or w.uzytkownikId in (" +
                                "       select f.uzytkownikId from Follower f " +
                                "       where f.followerId = :userId" +
                                "   ) " +
                                "order by w.dataUtworzenia desc", Wpis.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    @Transactional
    public void insert(Wpis wpis) {
        entityManager.persist(wpis);
    }
}