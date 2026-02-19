package WWSIS.dao.impl;

import WWSIS.dao.FollowerDao;  // Importujemy interfejs FollowerDao, który ta klasa implementuje
import WWSIS.model.Follower;  // Importujemy klasę Follower, która reprezentuje relację obserwowania w systemie
import org.springframework.stereotype.Repository;  // Importujemy adnotację @Repository, aby oznaczyć tę klasę jako komponent Springa
import org.springframework.transaction.annotation.Transactional;  // Importujemy adnotację @Transactional, aby zarządzać transakcjami

import javax.persistence.EntityManager;  // Importujemy EntityManager do operacji na bazie danych
import javax.persistence.PersistenceContext;  // Importujemy adnotację @PersistenceContext, aby wstrzyknąć EntityManager

@Repository  // Adnotacja @Repository wskazuje, że ta klasa jest odpowiedzialna za interakcję z bazą danych (DAO)
public class FollowerDaoImpl implements FollowerDao {

    @PersistenceContext  // Adnotacja @PersistenceContext umożliwia Springowi wstrzyknięcie instancji EntityManagera
    private EntityManager entityManager;  // EntityManager jest używany do wykonywania operacji na bazie danych

    // Metoda do rozpoczęcia obserwowania przez użytkownika (follower) innego użytkownika (followee)
    @Override
    @Transactional  // Adnotacja @Transactional zapewnia, że operacja jest wykonywana w ramach transakcji
    public void follow(Integer followerId, Integer followeeId) {
        // Jeśli użytkownik próbuje obserwować samego siebie, nie robimy nic
        if (followerId.equals(followeeId)) return;

        // Jeśli użytkownik już obserwuje innego użytkownika, nie robimy nic
        if (isFollowing(followerId, followeeId)) return;

        // Tworzymy nowy obiekt Follower, który reprezentuje relację obserwowania
        Follower f = new Follower();
        f.setFollowerId(followerId);  // Ustawiamy ID obserwującego użytkownika
        f.setUzytkownikId(followeeId);  // Ustawiamy ID użytkownika, który jest obserwowany

        // Zapisujemy obiekt Follower w bazie danych
        entityManager.persist(f);  // Zapisujemy relację w bazie
    }

    // Metoda do przestania obserwowania przez użytkownika (follower) innego użytkownika (followee)
    @Override
    @Transactional  // Adnotacja @Transactional zapewnia, że operacja jest wykonywana w ramach transakcji
    public void unfollow(Integer followerId, Integer followeeId) {
        // Tworzymy zapytanie do usunięcia relacji obserwowania między dwoma użytkownikami
        entityManager.createQuery(
                        "delete from Follower f where f.followerId = :fid and f.uzytkownikId = :uid")  // Zapytanie JPQL do usunięcia relacji
                .setParameter("fid", followerId)  // Ustawiamy parametr dla ID obserwującego
                .setParameter("uid", followeeId)  // Ustawiamy parametr dla ID obserwowanego
                .executeUpdate();  // Wykonujemy zapytanie
    }

    // Metoda do sprawdzenia, czy użytkownik (follower) obserwuje danego użytkownika (followee)
    @Override
    public boolean isFollowing(Integer followerId, Integer followeeId) {
        // Tworzymy zapytanie do zliczenia liczby relacji obserwowania między dwoma użytkownikami
        Long cnt = entityManager.createQuery(
                        "select count(f) from Follower f where f.followerId = :fid and f.uzytkownikId = :uid",  // Zapytanie JPQL do liczenia wyników
                        Long.class)  // Zwracamy wynik typu Long
                .setParameter("fid", followerId)  // Ustawiamy parametr dla ID obserwującego
                .setParameter("uid", followeeId)  // Ustawiamy parametr dla ID obserwowanego
                .getSingleResult();  // Pobieramy pojedynczy wynik (liczbę relacji)

        // Zwracamy true, jeśli liczba relacji jest większa niż 0 (czyli użytkownik obserwuje innego użytkownika)
        return cnt != null && cnt > 0;
    }
}