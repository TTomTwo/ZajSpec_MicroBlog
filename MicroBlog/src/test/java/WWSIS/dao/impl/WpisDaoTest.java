package WWSIS.dao.impl;

// Importowanie niezbędnych klas do testów i operacji na danych.
import WWSIS.dao.UzytkownikDao;
import WWSIS.dao.WpisDao;
import WWSIS.model.Uzytkownik;
import WWSIS.model.Wpis;
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

// Adnotacja, która informuje JUnit o użyciu Springa do uruchamiania testów.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")  // Wskazujemy Springowi, gdzie znajduje się konfiguracja aplikacji.
@Transactional  // Testy będą wykonywane w ramach transakcji, która zostanie wycofana po zakończeniu testu.
@Rollback(true)  // Po zakończeniu testu wszystkie zmiany w bazie danych będą wycofane (tak, by nie pozostawić testowych danych).
public class WpisDaoTest {

    @Autowired  // Spring wstrzykuje odpowiednią instancję WpisDao.
    private WpisDao wpisDao;

    @Autowired  // Spring wstrzykuje odpowiednią instancję UzytkownikDao.
    private UzytkownikDao uzytkownikDao;

    // Zmienne pomocnicze do przechowywania testowych użytkowników i wpisów.
    private Uzytkownik author;
    private Wpis wpis1;
    private Wpis wpis2;

    @Before  // Metoda, która jest wywoływana przed każdym testem, by przygotować dane.
    public void setUp() {
        String unique = String.valueOf(System.currentTimeMillis());  // Tworzymy unikalny ciąg znaków na podstawie aktualnego czasu.

        // Tworzymy użytkownika autora.
        author = new Uzytkownik();
        author.setLogin("autor_" + unique);  // Nadanie unikalnego loginu.
        author.setHaslo("hash");  // Ustawiamy hasło.
        author.setEmail("autor_" + unique + "@wp.pl");  // Tworzymy unikalny adres email.
        uzytkownikDao.insert(author);  // Zapisujemy użytkownika do bazy danych.

        // Tworzymy pierwszy wpis testowy.
        wpis1 = new Wpis();
        wpis1.setTresc("Pierwszy wpis – Mój kot jest miękki");  // Treść wpisu.
        wpis1.setDataUtworzenia(new Date());  // Ustawiamy datę utworzenia wpisu.
        wpis1.setUzytkownikId(author.getId());  // Ustalamy ID użytkownika jako klucz obcy (FK).
        wpisDao.insert(wpis1);  // Zapisujemy wpis w bazie danych.

        // Czekamy 100 ms, żeby wpisać drugi wpis po pierwszym (zachowanie kolejności).
        try { Thread.sleep(100); } catch (Exception ignored) {}

        // Tworzymy drugi wpis testowy.
        wpis2 = new Wpis();
        wpis2.setTresc("Drugi wpis – haha, maven robi brrrrr");
        wpis2.setDataUtworzenia(new Date());
        wpis2.setUzytkownikId(author.getId());
        wpisDao.insert(wpis2);  // Zapisujemy drugi wpis do bazy danych.
    }

    @Test  // Adnotacja do oznaczenia testu.
    public void testInsertAndTimeline() {
        List<Wpis> timeline = wpisDao.timeline(author.getId());  // Pobieramy "timeline" dla użytkownika autora.

        // Sprawdzamy, czy użytkownik ma dokładnie dwa wpisy na swoim timeline.
        assertEquals("Powinno zwrócić 2 wpisy autora", 2, timeline.size());
        // Sprawdzamy, czy najnowszy wpis znajduje się na górze.
        assertEquals("Najnowszy na górze", wpis2.getTresc(), timeline.get(0).getTresc());
        // Sprawdzamy, czy starszy wpis znajduje się na dole.
        assertEquals("Starszy na dole", wpis1.getTresc(), timeline.get(1).getTresc());
    }

    @Test
    public void testPublicTimeline() {
        List<Wpis> publicTimeline = wpisDao.publicTimeline();  // Pobieramy publiczny "timeline" (wszystkie wpisy).

        // Sprawdzamy, czy publiczny "timeline" ma co najmniej 2 wpisy.
        assertTrue("Public timeline powinno mieć co najmniej 2 wpisy", publicTimeline.size() >= 2);
        // Sprawdzamy, czy najnowszy wpis znajduje się na górze.
        assertEquals("Najnowszy na górze", wpis2.getTresc(), publicTimeline.get(0).getTresc());
    }

    @Test
    public void testFullTimelineNoFollow() {
        List<Wpis> fullTimeline = wpisDao.fullTimeline(author.getId());  // Pobieramy pełny "timeline" dla autora (nie uwzględnia follow).

        // Sprawdzamy, czy pełny "timeline" bez żadnych obserwacji (follow) to po prostu timeline autora.
        assertEquals("Bez follow full timeline = timeline", 2, fullTimeline.size());
        // Sprawdzamy, czy najnowszy wpis znajduje się na górze.
        assertEquals("Najnowszy na górze", wpis2.getTresc(), fullTimeline.get(0).getTresc());
    }
}