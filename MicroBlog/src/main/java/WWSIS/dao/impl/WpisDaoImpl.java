package WWSIS.dao.impl;

import WWSIS.dao.WpisDao;  // Importujemy interfejs WpisDao, który ta klasa implementuje
import WWSIS.model.Wpis;  // Importujemy klasę Wpis, która jest typem danych przechowywanych w bazie
import org.springframework.stereotype.Repository;  // Importujemy adnotację @Repository, aby oznaczyć tę klasę jako komponent Springa
import org.springframework.transaction.annotation.Transactional;  // Importujemy adnotację @Transactional, która umożliwia zarządzanie transakcjami

import javax.persistence.EntityManager;  // Importujemy EntityManager, który pozwala na operacje na bazie danych
import javax.persistence.PersistenceContext;  // Importujemy adnotację @PersistenceContext do wstrzykiwania EntityManagera
import java.util.List;  // Importujemy List, ponieważ metody zwracają listy wyników

@Repository  // Adnotacja @Repository wskazuje, że ta klasa jest odpowiedzialna za interakcję z bazą danych (DAO)
public class WpisDaoImpl implements WpisDao {

    @PersistenceContext  // Adnotacja @PersistenceContext umożliwia Springowi wstrzyknięcie EntityManagera
    private EntityManager entityManager;  // EntityManager jest używany do wykonywania operacji na bazie danych, takich jak zapytania i zapisywanie danych

    // Pobiera wszystkie wpisy dla konkretnego użytkownika na podstawie jego ID
    @Override
    public List<Wpis> timeline(Integer userId) {  // Integer, bo uzytkownikId jest kluczem obcym w tabeli Wpis
        return entityManager.createQuery(  // Tworzymy zapytanie JPQL (Java Persistence Query Language)
                        "select w from Wpis w " +
                                "where w.uzytkownikId = :userId " +  // Filtrujemy wpisy, aby pobrać tylko te, które są przypisane do konkretnego użytkownika
                                "order by w.dataUtworzenia desc", Wpis.class)  // Sortujemy wyniki po dacie utworzenia w porządku malejącym (najnowsze wpisy na górze)
                .setParameter("userId", userId)  // Ustawiamy parametr zapytania, aby pasował do ID użytkownika
                .getResultList();  // Wykonujemy zapytanie i zwracamy wyniki jako listę obiektów Wpis
    }

    // Pobiera wszystkie publiczne wpisy w systemie
    @Override
    public List<Wpis> publicTimeline() {
        return entityManager.createQuery(  // Tworzymy zapytanie JPQL
                        "select w from Wpis w " +
                                "order by w.dataUtworzenia desc", Wpis.class)  // Pobieramy wszystkie wpisy, posortowane po dacie utworzenia
                .getResultList();  // Wykonujemy zapytanie i zwracamy wyniki jako listę obiektów Wpis
    }

    // Pobiera pełny timeline użytkownika, obejmujący zarówno jego wpisy, jak i wpisy osób, które on obserwuje
    @Override
    public List<Wpis> fullTimeline(Integer userId) {
        // Zapytanie do pobrania pełnego timeline użytkownika:
        // - Wpisy użytkownika
        // - Wpisy osób, które obserwuje
        return entityManager.createQuery(  // Tworzymy zapytanie JPQL
                        "select w from Wpis w " +
                                "where w.uzytkownikId = :userId " +  // Pobieramy wpisy użytkownika
                                "   or w.uzytkownikId in (" +  // Dodatkowo pobieramy wpisy osób, które użytkownik obserwuje
                                "       select f.uzytkownikId from Follower f " +  // Subzapytanie, które wybiera ID użytkowników, których obserwuje
                                "       where f.followerId = :userId" +  // Wybieramy tylko tych użytkowników, którzy są obserwowani przez określonego użytkownika
                                "   ) " +
                                "order by w.dataUtworzenia desc", Wpis.class)  // Sortujemy wyniki po dacie utworzenia w porządku malejącym
                .setParameter("userId", userId)  // Ustawiamy parametr zapytania dla ID użytkownika
                .getResultList();  // Wykonujemy zapytanie i zwracamy wyniki jako listę obiektów Wpis
    }

    // Metoda do dodawania nowego wpisu do bazy danych
    @Override
    @Transactional  // Adnotacja @Transactional zapewnia, że ta operacja będzie wykonywana w ramach transakcji
    public void insert(Wpis wpis) {
        entityManager.persist(wpis);  // Używamy EntityManagera do zapisania nowego wpisu w bazie danych
    }
}