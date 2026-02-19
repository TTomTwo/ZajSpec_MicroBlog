package WWSIS.service;

import WWSIS.model.Uzytkownik;
import WWSIS.model.Wpis;
import java.util.List;

public interface MicroblogService {

    /**
     * Pobierz timeline usera (jego wpisy)
     * @param userId ID usera
     * @return Lista wpisów usera
     */
    List<Wpis> getTimeline(Integer userId);

    /**
     * Pobierz full timeline (moje + obserwowanych)
     * @param userId ID usera
     * @return Lista wpisów
     */
    List<Wpis> getFullTimeline(Integer userId);

    /**
     * Pobierz public timeline (wszystkie wpisy)
     * @return Lista wpisów
     */
    List<Wpis> getPublicTimeline();

    /**
     * Insert nowego wpisu
     * @param wpis Wpis do dodania
     */
    void addWpis(Wpis wpis);

    /**
     * Rejestracja nowego użytkownika
     * @param uzytkownik Uzytkownik do insert
     */
    void registerUzytkownik(Uzytkownik uzytkownik);

    /**
     * Find user by login
     * @param login Login usera
     * @return Uzytkownik lub null
     */
    Uzytkownik findUzytkownikByLogin(String login);

    /**
     * Follow / unfollow
     * @param followerId ID followera
     * @param followeeId ID obserwowanego
     */
    void follow(Integer followerId, Integer followeeId);
    void unfollow(Integer followerId, Integer followeeId);
    boolean isFollowing(Integer followerId, Integer followeeId);
}