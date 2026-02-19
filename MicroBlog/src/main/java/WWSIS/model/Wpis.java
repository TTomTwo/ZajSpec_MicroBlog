package WWSIS.model;

import javax.persistence.*;  // Importujemy wszystkie klasy związane z JPA (Java Persistence API)
import java.util.Date;  // Importujemy klasę Date do przechowywania dat i godzin

// Adnotacja @Entity wskazuje, że ta klasa jest encją JPA, co oznacza, że jest mapowana na tabelę w bazie danych
@Entity
@Table(name = "wpis")  // Adnotacja @Table wskazuje, że ta encja jest mapowana na tabelę "wpis"
public class Wpis {

    @Id  // Adnotacja @Id wskazuje, że to pole jest kluczem głównym w tabeli
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Adnotacja @GeneratedValue wskazuje, że wartość tego pola będzie generowana przez bazę danych
    private Integer id;  // Pole "id", które jest kluczem głównym i identyfikuje unikalnie każdy wpis

    @Column(name="uzytkownik_id", nullable=false)  // Adnotacja @Column wskazuje na kolumnę w tabeli bazy danych, która odpowiada temu polu
    private Integer uzytkownikId;  // Pole przechowujące ID użytkownika, który dodał wpis

    @Column(name="tresc", nullable=false, length=280)  // Adnotacja @Column wskazuje na kolumnę w tabeli dla "tresc" (z ograniczeniem długości 280 znaków)
    private String tresc;  // Pole "tresc", zawierające tekst wpisu, długość ograniczona do 280 znaków (jak w przypadku Twittera)

    @Temporal(TemporalType.TIMESTAMP)  // Adnotacja @Temporal określa, jak przechowywana będzie data
    @Column(name="data_utworzenia")  // Adnotacja @Column wskazuje na nazwę kolumny w tabeli, która przechowuje datę utworzenia wpisu
    private Date dataUtworzenia;  // Pole przechowujące datę utworzenia wpisu

    // Gettery i settery dla każdego pola w klasie:

    public Integer getId() { return id; }  // Getter dla pola "id"
    public void setId(Integer id) { this.id = id; }  // Setter dla pola "id"

    public Integer getUzytkownikId() { return uzytkownikId; }  // Getter dla pola "uzytkownikId"
    public void setUzytkownikId(Integer uzytkownikId) { this.uzytkownikId = uzytkownikId; }  // Setter dla pola "uzytkownikId"

    public String getTresc() { return tresc; }  // Getter dla pola "tresc"
    public void setTresc(String tresc) { this.tresc = tresc; }  // Setter dla pola "tresc"

    public Date getDataUtworzenia() { return dataUtworzenia; }  // Getter dla pola "dataUtworzenia"
    public void setDataUtworzenia(Date dataUtworzenia) { this.dataUtworzenia = dataUtworzenia; }  // Setter dla pola "dataUtworzenia"
}