package WWSIS.dao.impl;

import WWSIS.dao.FollowerDao;
import WWSIS.model.Follower;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public class FollowerDaoImpl implements FollowerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void followUser(Integer followerId, Integer uzytkownikId) {
        // Sprawdź czy już nie śledzi
        if (!isFollowing(followerId, uzytkownikId)) {
            Follower follower = new Follower();
            follower.setFollowerId(followerId);
            follower.setUzytkownikId(uzytkownikId);
            follower.setDataDodania(LocalDateTime.now());
            entityManager.persist(follower);
        }
    }

    @Override
    public void unfollowUser(Integer followerId, Integer uzytkownikId) {
        String jpql = "DELETE FROM Follower f WHERE f.followerId = :followerId AND f.uzytkownikId = :uzytkownikId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("followerId", followerId);
        query.setParameter("uzytkownikId", uzytkownikId);
        query.executeUpdate();
    }

    @Override
    public boolean isFollowing(Integer followerId, Integer uzytkownikId) {
        String jpql = "SELECT f FROM Follower f WHERE f.followerId = :followerId AND f.uzytkownikId = :uzytkownikId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("followerId", followerId);
        query.setParameter("uzytkownikId", uzytkownikId);

        List<Follower> results = query.getResultList();
        return !results.isEmpty();
    }
}