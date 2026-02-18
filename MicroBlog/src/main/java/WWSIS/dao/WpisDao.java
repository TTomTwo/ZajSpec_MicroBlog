package WWSIS.dao;

import WWSIS.model.Wpis;
import java.util.List;

public interface WpisDao {

    /**
     * Pobiera wszystkie wpisy konkretnego użytkownika (timeline użytkownika)
     */
    List<Wpis> getWpisyByUserId(Integer uzytkownikId);

    /**
     * Pobiera moje wpisy + wpisy użytkowników których śledzę (full timeline)
     */
    List<Wpis> getFullTimelineForUser(Integer uzytkownikId);

    /**
     * Pobiera wszystkie wpisy ze wszystkich użytkowników (publiczny timeline)
     */
    List<Wpis> getAllWpisy();

    /**
     * Dodaje nowy wpis
     */
    void addWpis(Wpis wpis);

}