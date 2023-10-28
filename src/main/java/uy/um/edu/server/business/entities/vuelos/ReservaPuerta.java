package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.*;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;
import uy.um.edu.server.business.entities.aeropuerto.PuertaAeropuerto;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas_puerta")
public class ReservaPuerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private LocalTime horaInicio;
    private LocalTime horaFin;


    @ManyToOne
    private PuertaAeropuerto puerta;

    @OneToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    public PuertaAeropuerto getPuerta() {
        return puerta;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public void setPuerta(PuertaAeropuerto puerta) {
        this.puerta = puerta;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
