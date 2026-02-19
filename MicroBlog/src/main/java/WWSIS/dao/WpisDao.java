package WWSIS.dao;

import WWSIS.model.Wpis;  // Importujemy klasę Wpis, ponieważ jest to typ, który będziemy manipulować w metodach DAO
import java.util.List;  // Importujemy Listę, ponieważ metody będą zwracały listy obiektów Wpis

// Interfejs WpisDao zawierający metody do operacji na wpisach w bazie danych
public interface WpisDao {

    // Metoda do pobrania wpisów konkretnego użytkownika
    List<Wpis> timeline(Integer userId);  // Parametr userId oznacza ID użytkownika, dla którego chcemy pobrać wpisy

    // Metoda do pobrania pełnego timeline użytkownika, czyli wpisów jego + wpisów osób, które śledzi
    List<Wpis> fullTimeline(Integer userId);  // Parametr userId oznacza ID użytkownika, dla którego chcemy pobrać pełny timeline

    // Metoda do pobrania publicznych wpisów wszystkich użytkowników
    List<Wpis> publicTimeline();  // Ta metoda zwróci wszystkie publiczne wpisy w systemie

    // Metoda do dodawania nowego wpisu do bazy danych
    void insert(Wpis wpis);  // Parametr wpis to obiekt, który chcemy dodać do bazy danych
}