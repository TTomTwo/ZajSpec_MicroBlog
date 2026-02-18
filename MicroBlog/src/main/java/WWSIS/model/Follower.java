package WWSIS.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follower")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uzytkownik_id", nullable = false)
    private Integer uzytkownikId;

    @Column(name = "follower_id", nullable = false)
    private Integer followerId;

    @Column(name = "data_dodania")
    private LocalDateTime dataDodania;

    // Konstruktor bezparametrowy
    public Follower() {
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

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public LocalDateTime getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(LocalDateTime dataDodania) {
        this.dataDodania = dataDodania;
    }
}