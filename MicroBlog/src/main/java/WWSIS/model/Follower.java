package WWSIS.model;

import javax.persistence.*;  // Importujemy wszystkie klasy związane z JPA (Java Persistence API)
import java.util.Date;  // Importujemy klasę Date do przechowywania dat i godzin

// Adnotacja @Entity oznacza, że ta klasa jest encją JPA i będzie mapowana na tabelę w bazie danych
@Entity
@Table(name = "follower",  // Określamy nazwę tabeli, która będzie mapowana na tę klasę
        uniqueConstraints = @UniqueConstraint(columnNames = {"uzytkownik_id", "follower_id"}))  // Określamy, że połączenie użytkownika i obserwatora musi być unikalne
public class Follower {

    @Id  // Adnotacja @Id oznacza, że pole "id" jest kluczem głównym tabeli
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Adnotacja @GeneratedValue oznacza, że wartość tego pola będzie generowana przez bazę danych (autoinkrement)
    private Integer id;  // Pole przechowujące unikalny identyfikator obserwowania

    @Column(name = "uzytkownik_id", nullable = false)  // Adnotacja @Column mapuje pole na kolumnę "uzytkownik_id" w tabeli
    private Integer uzytkownikId;  // ID użytkownika, który jest obserwowany

    @Column(name = "follower_id", nullable = false)  // Adnotacja @Column mapuje pole na kolumnę "follower_id" w tabeli
    private Integer followerId;  // ID użytkownika, który obserwuje

    @Temporal(TemporalType.TIMESTAMP)  // Określamy, że pole "dataDodania" będzie przechowywane jako timestamp (data + godzina)
    @Column(name = "data_dodania")  // Określamy nazwę kolumny w tabeli, która przechowuje datę dodania obserwacji
    private Date dataDodania;  // Data, kiedy użytkownik zaczął obserwować innego użytkownika

    // KONSTRUKTOR pusty (Hibernate wymaga tego konstruktora do tworzenia obiektów encji)
    public Follower() {
    }

    // Gettery i settery dla każdego pola w klasie:

    public Integer getId() {  // Getter dla pola "id"
        return id;
    }

    public void setId(Integer id) {  // Setter dla pola "id"
        this.id = id;
    }

    public Integer getUzytkownikId() {  // Getter dla pola "uzytkownikId"
        return uzytkownikId;
    }

    public void setUzytkownikId(Integer uzytkownikId) {  // Setter dla pola "uzytkownikId"
        this.uzytkownikId = uzytkownikId;
    }

    public Integer getFollowerId() {  // Getter dla pola "followerId"
        return followerId;
    }

    public void setFollowerId(Integer followerId) {  // Setter dla pola "followerId"
        this.followerId = followerId;
    }

    public Date getDataDodania() {  // Getter dla pola "dataDodania"
        return dataDodania;
    }

    public void setDataDodania(Date dataDodania) {  // Setter dla pola "dataDodania"
        this.dataDodania = dataDodania;
    }
}