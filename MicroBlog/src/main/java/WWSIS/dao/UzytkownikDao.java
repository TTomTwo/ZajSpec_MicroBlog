// src/main/java/WWSIS/dao/UzytkownikDao.java
package WWSIS.dao;

import WWSIS.model.Uzytkownik;  // Importujemy klasę Uzytkownik, która jest typem obiektów używanych w metodach DAO

// Interfejs UzytkownikDao zawiera metody operujące na danych użytkownika w bazie danych
public interface UzytkownikDao {

    // Metoda do pobrania użytkownika z bazy danych na podstawie loginu
    Uzytkownik findByLogin(String login);  // Parametr login to identyfikator użytkownika, po którym będziemy szukać w bazie danych

    // Metoda do dodawania nowego użytkownika do bazy danych
    void insert(Uzytkownik uzytkownik);  // Parametr uzytkownik to obiekt klasy Uzytkownik, który chcemy dodać do bazy
}