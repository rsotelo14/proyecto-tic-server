package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.*;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;

@Entity
@Table(name = "reservas_pista")
public class ReservaPista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: ver tipo
    private String fecha;


    @ManyToOne
    private PistaAeropuerto pista;

    @OneToOne(mappedBy = "reservaPista")
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;
}
