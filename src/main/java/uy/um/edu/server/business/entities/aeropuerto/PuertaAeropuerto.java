package uy.um.edu.server.business.entities.aeropuerto;



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

    @OneToMany(mappedBy = "puertaOrigen")
    private List<Vuelo> vuelosSalientes =new ArrayList<>();
    @OneToMany(mappedBy = "puertaDestino")
    private List<Vuelo> vuelosEntrantes =new ArrayList<>();

}
