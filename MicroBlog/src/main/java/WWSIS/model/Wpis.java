package WWSIS.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wpis")
public class Wpis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uzytkownik_id", nullable = false)
    private Integer uzytkownikId;

    @Column(name = "tresc", nullable = false, length = 280)
    private String tresc;

    @Column(name = "data_utworzenia")
    private LocalDateTime dataUtworzenia;

    // Konstruktor bezparametrowy
    public Wpis() {
    }

    // Gettery i settery
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUzytkownikId() {
        return uzytkownikId;
    }

    public void setUzytkownikId(Integer uzytkownikId) {
        this.uzytkownikId = uzytkownikId;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(LocalDateTime dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }
}