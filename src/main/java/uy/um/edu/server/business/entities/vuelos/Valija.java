package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "valija")
public class Valija {

    @Id
    @GenericGenerator(name="valija_id", strategy = "increment")
    @GeneratedValue(generator="valija_id")
    private Long id;
    @Column(unique = true)
    private String codigoValija;

    @ManyToOne
    @JoinColumn(name = "asiento_id")
    private Asientos asiento;

    public String getCodigoValija() {
        return codigoValija;
    }

    public void setCodigoValija(String codigoValija) {
        this.codigoValija = codigoValija;
    }

    public void setAsiento(Asientos asiento) {
        this.asiento = asiento;
    }

    public Asientos getAsiento() {
        return asiento;
    }
}
