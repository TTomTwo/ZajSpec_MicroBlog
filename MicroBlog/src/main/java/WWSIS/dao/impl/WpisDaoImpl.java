package WWSIS.dao.impl;

import WWSIS.dao.WpisDao;
import WWSIS.model.Wpis;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
public class WpisDaoImpl implements WpisDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Wpis> getWpisyByUserId(Integer uzytkownikId) {
        String jpql = "SELECT w FROM Wpis w WHERE w.uzytkownikId = :userId ORDER BY w.dataUtworzenia DESC";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("userId", uzytkownikId);
        return query.getResultList();
    }

    @Override
    public List<Wpis> getFullTimelineForUser(Integer uzytkownikId) {
        // Pobierz wpisy użytkownika + wpisy od użytkowników których śledzi
        String jpql = "SELECT w FROM Wpis w WHERE w.uzytkownikId = :userId " +
                "OR w.uzytkownikId IN (SELECT f.uzytkownikId FROM Follower f WHERE f.followerId = :userId) " +
                "ORDER BY w.dataUtworzenia DESC";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("userId", uzytkownikId);
        return query.getResultList();
    }

    @Override
    public List<Wpis> getAllWpisy() {
        String jpql = "SELECT w FROM Wpis w ORDER BY w.dataUtworzenia DESC";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public void addWpis(Wpis wpis) {
        entityManager.persist(wpis);
    }
}