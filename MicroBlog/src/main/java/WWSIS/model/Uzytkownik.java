package WWSIS.model;

import javax.persistence.*;  // Importujemy wszystkie klasy związane z JPA (Java Persistence API)
import java.util.Date;  // Importujemy klasę Date do przechowywania dat i godzin

// Adnotacja @Entity wskazuje, że ta klasa jest encją JPA, co oznacza, że jest mapowana na tabelę w bazie danych
@Entity
@Table(name = "uzytkownik")  // Adnotacja @Table wskazuje, że ta encja jest mapowana na tabelę "uzytkownik"
public class Uzytkownik {

    @Id  // Adnotacja @Id wskazuje, że to pole jest kluczem głównym w tabeli
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Adnotacja @GeneratedValue mówi, że wartość tego pola będzie generowana przez bazę danych
    private Integer id;  // Pole "id", które jest kluczem głównym i identyfikuje unikalnie każdego użytkownika

    @Column(name="login", nullable=false, unique=true, length=50)  // Adnotacja @Column mapuje pole "login" na kolumnę w tabeli
    private String login;  // Pole "login", które jest unikalne i nie może być puste (nullable=false)

    @Column(name="haslo", nullable=false, length=100)  // Adnotacja @Column mapuje pole "haslo" na kolumnę w tabeli
    private String haslo;  // Pole "haslo" użytkownika

    @Column(name="email", nullable=false, unique=true, length=100)  // Adnotacja @Column mapuje pole "email" na kolumnę w tabeli
    private String email;  // Pole "email", które jest unikalne i nie może być puste

    @Temporal(TemporalType.TIMESTAMP)  // Adnotacja @Temporal określa, jak przechowywana będzie data
    @Column(name="data_rejestracji")  // Adnotacja @Column wskazuje na nazwę kolumny w tabeli, która przechowuje datę rejestracji
    private Date dataRejestracji;  // Pole przechowujące datę rejestracji użytkownika

    // Gettery i settery dla każdego pola w klasie:

    public Integer getId() { return id; }  // Getter dla pola "id"
    public void setId(Integer id) { this.id = id; }  // Setter dla pola "id"

    public String getLogin() { return login; }  // Getter dla pola "login"
    public void setLogin(String login) { this.login = login; }  // Setter dla pola "login"

    public String getHaslo() { return haslo; }  // Getter dla pola "haslo"
    public void setHaslo(String haslo) { this.haslo = haslo; }  // Setter dla pola "haslo"

    public String getEmail() { return email; }  // Getter dla pola "email"
    public void setEmail(String email) { this.email = email; }  // Setter dla pola "email"

    public Date getDataRejestracji() { return dataRejestracji; }  // Getter dla pola "dataRejestracji"
    public void setDataRejestracji(Date dataRejestracji) { this.dataRejestracji = dataRejestracji; }  // Setter dla pola "dataRejestracji"
}