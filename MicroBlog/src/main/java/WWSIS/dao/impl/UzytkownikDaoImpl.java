package WWSIS.dao.impl;

import WWSIS.dao.UzytkownikDao;  // Importujemy interfejs UzytkownikDao, który ta klasa implementuje
import WWSIS.model.Uzytkownik;  // Importujemy klasę Uzytkownik, która jest typem danych przechowywanych w bazie
import org.springframework.stereotype.Repository;  // Importujemy adnotację @Repository, aby oznaczyć tę klasę jako komponent Springa

import javax.persistence.EntityManager;  // Importujemy EntityManager, który umożliwia operacje na bazie danych
import javax.persistence.PersistenceContext;  // Importujemy adnotację @PersistenceContext do wstrzykiwania EntityManagera
import javax.persistence.TypedQuery;  // Importujemy TypedQuery, która pozwala na wykonywanie zapytań z określonym typem wyników

@Repository  // Adnotacja @Repository wskazuje, że ta klasa jest odpowiedzialna za interakcję z bazą danych (DAO)
public class UzytkownikDaoImpl implements UzytkownikDao {

    @PersistenceContext  // Adnotacja @PersistenceContext pozwala Springowi na wstrzyknięcie instancji EntityManagera
    private EntityManager entityManager;  // EntityManager jest używany do wykonywania operacji na bazie danych

    // Metoda do wyszukiwania użytkownika na podstawie loginu
    @Override
    public Uzytkownik findByLogin(String login) {
        // Tworzymy zapytanie JPQL, które wybiera użytkownika na podstawie loginu
        TypedQuery<Uzytkownik> q = entityManager.createQuery(
                "select u from Uzytkownik u where u.login = :login", Uzytkownik.class);  // Zapytanie JPQL
        q.setParameter("login", login);  // Ustawiamy parametr zapytania dla loginu
        // Wykonujemy zapytanie i sprawdzamy, czy wynik jest pusty. Jeśli jest pusty, zwracamy null
        return q.getResultList().isEmpty() ? null : q.getResultList().get(0);  // Zwracamy pierwszy wynik lub null, jeśli lista jest pusta
    }

    // Metoda do dodawania nowego użytkownika do bazy danych
    @Override
    public void insert(Uzytkownik uzytkownik) {
        entityManager.persist(uzytkownik);  // Używamy EntityManagera do zapisania obiektu użytkownika w bazie danych
    }
}