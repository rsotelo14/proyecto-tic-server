package uy.um.edu.server.business.entities.aeropuerto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import uy.um.edu.server.business.entities.vuelos.Vuelo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "puertas_aeropuerto")
public class PuertaAeropuerto {
    @Id
    @GeneratedValue
    @GenericGenerator(name="puerta_aeropuerto_ids", strategy = "increment")
    private Long id;
    private Long tarifaHora;
    @Column(unique = true, nullable = false)
    private Long numeroPuerta;
    @ManyToOne
    @JoinColumn(name = "aeropuerto_id")
    private Aeropuerto aeropuerto;

    public PuertaAeropuerto() {
    }


    public Long getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Long tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Long getNumeroPuerta() {
        return numeroPuerta;
    }

    public void setNumeroPuerta(Long numeroPuerta) {
        this.numeroPuerta = numeroPuerta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }
}
