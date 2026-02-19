package WWSIS.service.impl;

import WWSIS.model.Uzytkownik;
import WWSIS.model.Wpis;
import WWSIS.service.MicroblogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@Rollback(true)
public class MicroblogServiceImplTest {

    @Autowired
    private MicroblogService microblogService;

    private Uzytkownik testUser1;
    private Uzytkownik testUser2;
    private Wpis testWpis1;
    private Wpis testWpis2;

    @Before
    public void setUp() {
        String unique = String.valueOf(System.currentTimeMillis());

        testUser1 = new Uzytkownik();
        testUser1.setLogin("user1_" + unique);
        testUser1.setHaslo("hash1");
        testUser1.setEmail("user1_" + unique + "@wp.pl");
        microblogService.registerUzytkownik(testUser1);

        testUser2 = new Uzytkownik();
        testUser2.setLogin("user2_" + unique);
        testUser2.setHaslo("hash2");
        testUser2.setEmail("user2_" + unique + "@wp.pl");
        microblogService.registerUzytkownik(testUser2);

        testWpis1 = new Wpis();
        testWpis1.setTresc("Wpis user1 – Mój kot jest miękki");
        testWpis1.setDataUtworzenia(new Date());
        testWpis1.setUzytkownikId(testUser1.getId());
        microblogService.addWpis(testWpis1);

        testWpis2 = new Wpis();
        testWpis2.setTresc("Wpis user2 – haha, maven robi brrrrr");
        testWpis2.setDataUtworzenia(new Date());
        testWpis2.setUzytkownikId(testUser2.getId());
        microblogService.addWpis(testWpis2);
    }

    @Test
    public void testRegisterAndFindByLogin() {
        Uzytkownik found = microblogService.findUzytkownikByLogin(testUser1.getLogin());

        assertNotNull("Użytkownik powinien istnieć po rejestracji", found);
        assertEquals("Login powinien się zgadzać", testUser1.getLogin(), found.getLogin());
    }

    @Test
    public void testGetTimeline() {
        List<Wpis> timeline = microblogService.getTimeline(testUser1.getId());

        assertEquals("Powinien zwrócić 1 wpis user1", 1, timeline.size());
        assertEquals("Tresc powinna się zgadzać", "Wpis user1 – Mój kot jest miękki", timeline.get(0).getTresc());
    }

    @Test
    public void testGetPublicTimeline() {
        List<Wpis> publicTimeline = microblogService.getPublicTimeline();

        assertTrue("Public timeline powinno mieć co najmniej 2 wpisy", publicTimeline.size() >= 2);
    }

    @Test
    public void testFollowAndGetFullTimeline() {
        microblogService.follow(testUser1.getId(), testUser2.getId());

        List<Wpis> fullTimeline = microblogService.getFullTimeline(testUser1.getId());

        assertTrue("Full timeline powinno mieć wpis user2 po follow", fullTimeline.size() >= 2);
        assertTrue("Powinno zawierać wpis user2", fullTimeline.stream().anyMatch(w -> w.getTresc().equals("Wpis user2 – haha, maven robi brrrrr")));
    }

    @Test
    public void testUnfollow() {
        microblogService.follow(testUser1.getId(), testUser2.getId());

        microblogService.unfollow(testUser1.getId(), testUser2.getId());

        assertFalse("Nie powinien followować po unfollow", microblogService.isFollowing(testUser1.getId(), testUser2.getId()));
    }
}