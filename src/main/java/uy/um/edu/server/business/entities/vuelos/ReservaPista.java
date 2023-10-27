package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.*;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "reservas_pista")
public class ReservaPista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;


    @ManyToOne
    private PistaAeropuerto pista;

    @OneToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public PistaAeropuerto getPista() {
        return pista;
    }

    public void setPista(PistaAeropuerto pista) {
        this.pista = pista;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}
