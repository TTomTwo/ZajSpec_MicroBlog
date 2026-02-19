package WWSIS.dao.impl;

// Importowanie niezbędnych klas do testów i operacji na danych.
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
@Rollback(true)  // Po zakończeniu testu wszystkie zmiany w bazie danych będą wycofane (żadne dane testowe nie pozostaną w bazie).
public class UzytkownikDaoTest {

    @Autowired  // Spring wstrzykuje odpowiednią instancję UzytkownikDao.
    private UzytkownikDao uzytkownikDao;

    private Uzytkownik testUser;  // Obiekt użytkownika, który będzie wykorzystywany w testach.

    @Before  // Metoda przygotowująca dane przed każdym testem.
    public void setUp() {
        String unique = String.valueOf(System.currentTimeMillis());  // Tworzymy unikalny ciąg znaków na podstawie czasu.

        // Tworzymy testowego użytkownika z unikalnymi danymi.
        testUser = new Uzytkownik();
        testUser.setLogin("ZonaMnieBije_" + unique);  // Login będzie zawierał unikalną część.
        testUser.setHaslo("hash_bol_duzy");  // Przykładowe hasło.
        testUser.setEmail("konto@hotmail_" + unique + ".pl");  // Unikalny email.

        // Zapisujemy użytkownika w bazie danych.
        uzytkownikDao.insert(testUser);
    }

    @Test  // Adnotacja do oznaczenia testu.
    public void testInsertAndFindByLogin() {
        // Szukamy użytkownika po loginie.
        Uzytkownik found = uzytkownikDao.findByLogin(testUser.getLogin());

        // Sprawdzamy, czy użytkownik został znaleziony w bazie.
        assertNotNull("Użytkownik powinien istnieć po insert", found);
        // Sprawdzamy, czy login użytkownika jest taki sam jak ten, który zapisaliśmy.
        assertEquals("Login powinien być taki sam", testUser.getLogin(), found.getLogin());
        // Sprawdzamy, czy email jest taki sam.
        assertEquals("Email powinien być taki sam", testUser.getEmail(), found.getEmail());
        // Sprawdzamy, czy ID użytkownika zostało wygenerowane przez bazę danych (powinno być nie-null).
        assertNotNull("ID powinno być wygenerowane przez bazę", found.getId());
    }

    @Test  // Drugi test, sprawdzający sytuację, gdy użytkownik nie istnieje w bazie.
    public void testFindByLoginNotFound() {
        // Szukamy użytkownika o loginie, który nie istnieje w bazie.
        Uzytkownik notFound = uzytkownikDao.findByLogin("nieistniejacy_typ");

        // Sprawdzamy, czy nie znaleziono użytkownika, który nie istnieje.
        assertNull("Nie powinno znaleźć tego, którego nie ma", notFound);
    }
}