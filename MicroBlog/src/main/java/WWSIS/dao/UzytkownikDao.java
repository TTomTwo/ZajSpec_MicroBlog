// src/main/java/WWSIS/dao/UzytkownikDao.java
package WWSIS.dao;

import WWSIS.model.Uzytkownik;

public interface UzytkownikDao {
    Uzytkownik findByLogin(String login);
    void insert(Uzytkownik uzytkownik);
}
