package WWSIS.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wpis")
public class Wpis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="uzytkownik_id", nullable=false)
    private Integer uzytkownikId;

    @Column(name="tresc", nullable=false, length=280)
    private String tresc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_utworzenia")
    private Date dataUtworzenia;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUzytkownikId() { return uzytkownikId; }
    public void setUzytkownikId(Integer uzytkownikId) { this.uzytkownikId = uzytkownikId; }

    public String getTresc() { return tresc; }
    public void setTresc(String tresc) { this.tresc = tresc; }

    public Date getDataUtworzenia() { return dataUtworzenia; }
    public void setDataUtworzenia(Date dataUtworzenia) { this.dataUtworzenia = dataUtworzenia; }
}
