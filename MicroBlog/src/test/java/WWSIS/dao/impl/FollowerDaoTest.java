package WWSIS.dao.impl;

// Importowanie niezbędnych klas do testów i operacji na danych.
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

// Adnotacja uruchamiająca testy w kontekście Springa.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")  // Wskazujemy Springowi, gdzie znajduje się konfiguracja aplikacji.
@Transactional  // Testy będą wykonywane w ramach transakcji, która zostanie wycofana po zakończeniu testu.
@Rollback(true)  // Po zakończeniu testu wszystkie zmiany w bazie danych będą wycofane, aby nie pozostawić danych testowych.
public class FollowerDaoTest {

    @Autowired  // Spring wstrzykuje odpowiednią instancję FollowerDao.
    private FollowerDao followerDao;

    @Autowired  // Spring wstrzykuje odpowiednią instancję UzytkownikDao.
    private UzytkownikDao uzytkownikDao;

    private Uzytkownik user1;
    private Uzytkownik user2;

    @Before  // Metoda przygotowująca dane przed każdym testem.
    public void setUp() {
        // Tworzymy dwóch użytkowników do testów.
        user1 = new Uzytkownik();
        user1.setLogin("follower" + System.currentTimeMillis());  // Tworzymy unikalny login.
        user1.setHaslo("hash");  // Ustawiamy hasło.
        user1.setEmail("f@wp.pl");  // Ustawiamy email.
        uzytkownikDao.insert(user1);  // Zapisujemy użytkownika 1 w bazie.

        user2 = new Uzytkownik();
        user2.setLogin("followee" + System.currentTimeMillis());  // Tworzymy unikalny login dla drugiego użytkownika.
        user2.setHaslo("hash");  // Ustawiamy hasło.
        user2.setEmail("e@wp.pl");  // Ustawiamy email.
        uzytkownikDao.insert(user2);  // Zapisujemy użytkownika 2 w bazie.
    }

    @Test  // Adnotacja oznaczająca test.
    public void testFollowAndIsFollowing() {
        // Użytkownik user1 zaczyna obserwować użytkownika user2.
        followerDao.follow(user1.getId(), user2.getId());

        // Sprawdzamy, czy user1 rzeczywiście zaczyna obserwować user2.
        assertTrue("powinien followować", followerDao.isFollowing(user1.getId(), user2.getId()));
    }

    @Test
    public void testUnfollow() {
        // Najpierw użytkownik user1 zaczyna obserwować user2.
        followerDao.follow(user1.getId(), user2.getId());

        // Następnie użytkownik user1 przestaje obserwować user2.
        followerDao.unfollow(user1.getId(), user2.getId());

        // Sprawdzamy, czy user1 przestał obserwować user2.
        assertFalse("nie powinien followować po unfollow", followerDao.isFollowing(user1.getId(), user2.getId()));
    }

    @Test
    public void testFollowSelf() {
        // Użytkownik user1 próbuje obserwować samego siebie.
        followerDao.follow(user1.getId(), user1.getId());

        // Sprawdzamy, czy użytkownik nie może obserwować samego siebie.
        assertFalse("nie powinien followować samego siebie", followerDao.isFollowing(user1.getId(), user1.getId()));
    }
}