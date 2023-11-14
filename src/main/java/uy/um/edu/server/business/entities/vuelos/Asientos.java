package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import uy.um.edu.server.business.entities.pasajeros.Pasajero;

@Entity
@Table(name = "asientos")
public class Asientos {
    @Id
    @GeneratedValue
    @GenericGenerator(name="asiento_id", strategy = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;
    private Boolean checkIn;
    private Boolean boarding;
    private Pasajero pasajero;
    public Asientos() {
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Boolean getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getBoarding() {
        return boarding;
    }

    public void setBoarding(Boolean boarding) {
        this.boarding = boarding;
    }
}
