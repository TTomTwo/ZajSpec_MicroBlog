package WWSIS.dao.impl;

import WWSIS.dao.FollowerDao;
import WWSIS.model.Follower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FollowerDaoImpl implements FollowerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void follow(Integer followerId, Integer followeeId) {
        if (followerId.equals(followeeId)) return;
        if (isFollowing(followerId, followeeId)) return;

        Follower f = new Follower();
        f.setFollowerId(followerId);  // Integer
        f.setUzytkownikId(followeeId);  // Integer

        entityManager.persist(f);
    }

    @Override
    @Transactional
    public void unfollow(Integer followerId, Integer followeeId) {
        entityManager.createQuery(
                        "delete from Follower f where f.followerId = :fid and f.uzytkownikId = :uid")
                .setParameter("fid", followerId)
                .setParameter("uid", followeeId)
                .executeUpdate();
    }

    @Override
    public boolean isFollowing(Integer followerId, Integer followeeId) {
        Long cnt = entityManager.createQuery(
                        "select count(f) from Follower f where f.followerId = :fid and f.uzytkownikId = :uid",
                        Long.class)
                .setParameter("fid", followerId)
                .setParameter("uid", followeeId)
                .getSingleResult();

        return cnt != null && cnt > 0;
    }
}