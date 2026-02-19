package WWSIS.service.impl;

// Importowanie klas potrzebnych do testowania i obsługi projektu.
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

// Adnotacja uruchamiająca testy w kontekście Springa.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")  // Wskazuje Springowi, gdzie znajduje się konfiguracja aplikacji.
@Transactional  // Cały test zostanie wykonany w transakcji – po zakończeniu testu, wszystkie zmiany w bazie danych zostaną wycofane.
@Rollback(true)  // Po zakończeniu testu transakcja zostanie cofnięta, więc dane nie zostaną zapisane na stałe w bazie danych.
public class MicroblogServiceImplTest {

    @Autowired  // Spring automatycznie wstrzykuje odpowiednią instancję MicroblogService.
    private MicroblogService microblogService;

    // Obiekty użytkowników i wpisów, które będą używane w testach.
    private Uzytkownik testUser1;
    private Uzytkownik testUser2;
    private Wpis testWpis1;
    private Wpis testWpis2;

    @Before  // Metoda, która jest wywoływana przed każdym testem, by przygotować dane testowe.
    public void setUp() {
        String unique = String.valueOf(System.currentTimeMillis());  // Tworzymy unikalny ciąg znaków na podstawie aktualnego czasu.

        // Tworzymy użytkownika testowego 1.
        testUser1 = new Uzytkownik();
        testUser1.setLogin("user1_" + unique);  // Nadawanie unikalnego loginu.
        testUser1.setHaslo("hash1");  // Ustawiamy hasło.
        testUser1.setEmail("user1_" + unique + "@wp.pl");  // Ustawiamy unikalny email.
        microblogService.registerUzytkownik(testUser1);  // Rejestrujemy użytkownika w systemie.

        // Tworzymy użytkownika testowego 2.
        testUser2 = new Uzytkownik();
        testUser2.setLogin("user2_" + unique);
        testUser2.setHaslo("hash2");
        testUser2.setEmail("user2_" + unique + "@wp.pl");
        microblogService.registerUzytkownik(testUser2);

        // Tworzymy pierwszy wpis testowy.
        testWpis1 = new Wpis();
        testWpis1.setTresc("Wpis user1 – Mój kot jest miękki");  // Treść wpisu.
        testWpis1.setDataUtworzenia(new Date());  // Data utworzenia wpisu.
        testWpis1.setUzytkownikId(testUser1.getId());  // Powiązanie z użytkownikiem.
        microblogService.addWpis(testWpis1);  // Dodanie wpisu do systemu.

        // Tworzymy drugi wpis testowy.
        testWpis2 = new Wpis();
        testWpis2.setTresc("Wpis user2 – haha, maven robi brrrrr");
        testWpis2.setDataUtworzenia(new Date());
        testWpis2.setUzytkownikId(testUser2.getId());
        microblogService.addWpis(testWpis2);
    }

    @Test  // Adnotacja oznaczająca metodę jako test.
    public void testRegisterAndFindByLogin() {
        Uzytkownik found = microblogService.findUzytkownikByLogin(testUser1.getLogin());  // Szukamy użytkownika po loginie.

        // Sprawdzamy, czy użytkownik został znaleziony.
        assertNotNull("Użytkownik powinien istnieć po rejestracji", found);
        assertEquals("Login powinien się zgadzać", testUser1.getLogin(), found.getLogin());  // Sprawdzamy, czy login jest taki sam jak wprowadzony.
    }

    @Test
    public void testGetTimeline() {
        List<Wpis> timeline = microblogService.getTimeline(testUser1.getId());  // Pobieramy "timeline" (czasową listę wpisów) dla użytkownika.

        // Sprawdzamy, czy na "timeline" znajduje się jeden wpis dla testUser1.
        assertEquals("Powinien zwrócić 1 wpis user1", 1, timeline.size());
        assertEquals("Tresc powinna się zgadzać", "Wpis user1 – Mój kot jest miękki", timeline.get(0).getTresc());
    }

    @Test
    public void testGetPublicTimeline() {
        List<Wpis> publicTimeline = microblogService.getPublicTimeline();  // Pobieramy publiczny "timeline" – wszystkie wpisy użytkowników.

        // Sprawdzamy, czy publiczny "timeline" ma co najmniej 2 wpisy.
        assertTrue("Public timeline powinno mieć co najmniej 2 wpisy", publicTimeline.size() >= 2);
    }

    @Test
    public void testFollowAndGetFullTimeline() {
        microblogService.follow(testUser1.getId(), testUser2.getId());  // Testujemy funkcję "follow" – testUser1 zaczyna śledzić testUser2.

        List<Wpis> fullTimeline = microblogService.getFullTimeline(testUser1.getId());  // Pobieramy pełny "timeline" użytkownika testUser1 (zawiera wpisy zarówno jego, jak i jego obserwowanych).

        // Sprawdzamy, czy w pełnym "timeline" znajduje się wpis testUser2.
        assertTrue("Full timeline powinno mieć wpis user2 po follow", fullTimeline.size() >= 2);
        assertTrue("Powinno zawierać wpis user2", fullTimeline.stream().anyMatch(w -> w.getTresc().equals("Wpis user2 – haha, maven robi brrrrr")));
    }

    @Test
    public void testUnfollow() {
        microblogService.follow(testUser1.getId(), testUser2.getId());  // Testujemy funkcję "follow" – testUser1 zaczyna śledzić testUser2.

        microblogService.unfollow(testUser1.getId(), testUser2.getId());  // Testujemy funkcję "unfollow" – testUser1 przestaje śledzić testUser2.

        // Sprawdzamy, czy testUser1 przestał śledzić testUser2.
        assertFalse("Nie powinien followować po unfollow", microblogService.isFollowing(testUser1.getId(), testUser2.getId()));
    }
}