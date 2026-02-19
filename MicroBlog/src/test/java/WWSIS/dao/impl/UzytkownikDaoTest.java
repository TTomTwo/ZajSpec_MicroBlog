package WWSIS.dao.impl;

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
public class UzytkownikDaoTest {

    @Autowired
    private UzytkownikDao uzytkownikDao;

    private Uzytkownik testUser;

    @Before
    public void setUp() {
        String unique = String.valueOf(System.currentTimeMillis());

        testUser = new Uzytkownik();
        testUser.setLogin("testMasochista_" + unique);
        testUser.setHaslo("hash_bol_duzy");
        testUser.setEmail("bol@wdupie_" + unique + ".pl");

        uzytkownikDao.insert(testUser);
    }

    @Test
    public void testInsertAndFindByLogin() {
        Uzytkownik found = uzytkownikDao.findByLogin(testUser.getLogin());

        assertNotNull("Użytkownik powinien istnieć po insert", found);
        assertEquals("Login powinien być taki sam", testUser.getLogin(), found.getLogin());
        assertEquals("Email powinien być taki sam", testUser.getEmail(), found.getEmail());
        assertNotNull("ID powinno być wygenerowane przez bazę", found.getId());
    }

    @Test
    public void testFindByLoginNotFound() {
        Uzytkownik notFound = uzytkownikDao.findByLogin("nieistniejacy_maso_frajer_666");

        assertNull("Nie powinno znaleźć gówna, którego nie ma", notFound);
    }
}