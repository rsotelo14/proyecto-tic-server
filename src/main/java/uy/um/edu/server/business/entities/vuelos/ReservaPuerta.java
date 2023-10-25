package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.*;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;
import uy.um.edu.server.business.entities.aeropuerto.PuertaAeropuerto;

@Entity
@Table(name = "reservas_puerta")
public class ReservaPuerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: ver tipo
    private String fecha;


    @ManyToOne
    private PuertaAeropuerto puerta;

    @OneToOne(mappedBy = "reservaPuerta")
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;
}
