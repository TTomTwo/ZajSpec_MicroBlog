package WWSIS.dao.impl;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@Rollback(true)
public class WpisDaoTest {

    @Autowired
    private WpisDao wpisDao;

    @Autowired
    private UzytkownikDao uzytkownikDao;

    private Uzytkownik author;
    private Wpis wpis1;
    private Wpis wpis2;

    @Before
    public void setUp() {
        String unique = String.valueOf(System.currentTimeMillis());

        author = new Uzytkownik();
        author.setLogin("autor_" + unique);
        author.setHaslo("hash");
        author.setEmail("autor_" + unique + "@wp.pl");
        uzytkownikDao.insert(author);

        wpis1 = new Wpis();
        wpis1.setTresc("Pierwszy wpis – boli jak cholera");
        wpis1.setDataUtworzenia(new Date());
        wpis1.setUzytkownikId(author.getId());  // FK Integer, nie relacja
        wpisDao.insert(wpis1);

        try { Thread.sleep(100); } catch (Exception ignored) {}
        wpis2 = new Wpis();
        wpis2.setTresc("Drugi wpis – jeszcze bardziej boli");
        wpis2.setDataUtworzenia(new Date());
        wpis2.setUzytkownikId(author.getId());
        wpisDao.insert(wpis2);
    }

    @Test
    public void testInsertAndTimeline() {
        List<Wpis> timeline = wpisDao.timeline(author.getId());

        assertEquals("Powinno zwrócić 2 wpisy autora", 2, timeline.size());
        assertEquals("Najnowszy na górze", wpis2.getTresc(), timeline.get(0).getTresc());
        assertEquals("Starszy na dole", wpis1.getTresc(), timeline.get(1).getTresc());
    }

    @Test
    public void testPublicTimeline() {
        List<Wpis> publicTimeline = wpisDao.publicTimeline();

        assertTrue("Public timeline powinno mieć co najmniej 2 wpisy", publicTimeline.size() >= 2);
        assertEquals("Najnowszy na górze", wpis2.getTresc(), publicTimeline.get(0).getTresc());
    }

    @Test
    public void testFullTimelineNoFollow() {
        List<Wpis> fullTimeline = wpisDao.fullTimeline(author.getId());

        assertEquals("Bez follow full timeline = timeline", 2, fullTimeline.size());
        assertEquals("Najnowszy na górze", wpis2.getTresc(), fullTimeline.get(0).getTresc());
    }
}