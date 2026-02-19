package WWSIS.model;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "follower",
        uniqueConstraints = @UniqueConstraint(columnNames = {"uzytkownik_id", "follower_id"}))
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   // <-- zmienione na Integer, bo to standard i lepiej niż Integer

    @Column(name = "uzytkownik_id", nullable = false)
    private Integer uzytkownikId;

    @Column(name = "follower_id", nullable = false)
    private Integer followerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_dodania")
    private Date dataDodania;

    // KONSTRUKTOR pusty (Hibernate lubi)
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

    public Date getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(Date dataDodania) {
        this.dataDodania = dataDodania;
    }
}