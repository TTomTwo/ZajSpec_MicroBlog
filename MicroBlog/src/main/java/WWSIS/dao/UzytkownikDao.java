package WWSIS.dao;

import WWSIS.model.Uzytkownik;

public interface UzytkownikDao {

    /**
     * Pobiera użytkownika po loginie
     */
    Uzytkownik getUserByLogin(String login);

    /**
     * Rejestruje/dodaje nowego użytkownika
     */
    void registerUser(Uzytkownik uzytkownik);

}