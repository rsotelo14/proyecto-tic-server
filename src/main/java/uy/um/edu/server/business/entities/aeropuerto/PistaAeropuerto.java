package uy.um.edu.server.business.entities.aeropuerto;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pistas_aeropuerto")
public class PistaAeropuerto {

    @Id
    @GeneratedValue(generator = "pista_aeropuerto_ids")
    @GenericGenerator(name = "pista_aeropuerto_ids", strategy = "increment")
    private Long id;
    private Long tarifaHora;
    @Column(unique = true, nullable = false)
    private Long numeroPista;
    @ManyToOne
    @JoinColumn(name = "aeropuerto_id")
    private Aeropuerto aeropuerto;

    public PistaAeropuerto() {
    }

    public PistaAeropuerto(Long tarifaHora, Long numeroPista, Aeropuerto aeropuerto) {
        this.tarifaHora = tarifaHora;
        this.numeroPista = numeroPista;
        this.aeropuerto = aeropuerto;
    }

    public Long getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Long tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Long getNumeroPista() {
        return numeroPista;
    }

    public void setNumeroPista(Long numeroPista) {
        this.numeroPista = numeroPista;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}
