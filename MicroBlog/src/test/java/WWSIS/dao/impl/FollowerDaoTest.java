package WWSIS.dao.impl;

import WWSIS.dao.FollowerDao;
import WWSIS.dao.UzytkownikDao;
import WWSIS.model.Uzytkownik;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@Rollback(true)
public class FollowerDaoTest {

    @Autowired
    private FollowerDao followerDao;

    @Autowired
    private UzytkownikDao uzytkownikDao;

    private Uzytkownik user1;
    private Uzytkownik user2;

    @Before
    public void setUp() {
        user1 = new Uzytkownik();
        user1.setLogin("follower" + System.currentTimeMillis());
        user1.setHaslo("hash");
        user1.setEmail("f@wp.pl");
        uzytkownikDao.insert(user1);

        user2 = new Uzytkownik();
        user2.setLogin("followee" + System.currentTimeMillis());
        user2.setHaslo("hash");
        user2.setEmail("e@wp.pl");
        uzytkownikDao.insert(user2);
    }

    @Test
    public void testFollowAndIsFollowing() {
        followerDao.follow(user1.getId(), user2.getId());
        assertTrue("powinien followować", followerDao.isFollowing(user1.getId(), user2.getId()));
    }

    @Test
    public void testUnfollow() {
        followerDao.follow(user1.getId(), user2.getId());
        followerDao.unfollow(user1.getId(), user2.getId());
        assertFalse("nie powinien followować po unfollow", followerDao.isFollowing(user1.getId(), user2.getId()));
    }

    @Test
    public void testFollowSelf() {
        followerDao.follow(user1.getId(), user1.getId());
        assertFalse("nie powinien followować samego siebie", followerDao.isFollowing(user1.getId(), user1.getId()));
    }
}