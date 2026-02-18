package WWSIS.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uzytkownik")
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "haslo", nullable = false, length = 100)
    private String haslo;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "data_rejestracji")
    private LocalDateTime dataRejestracji;

    // Konstruktor bezparametrowy (wymagany przez JPA)
    public Uzytkownik() {
    }

    // Gettery i settery
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataRejestracji() {
        return dataRejestracji;
    }

    public void setDataRejestracji(LocalDateTime dataRejestracji) {
        this.dataRejestracji = dataRejestracji;
    }
}