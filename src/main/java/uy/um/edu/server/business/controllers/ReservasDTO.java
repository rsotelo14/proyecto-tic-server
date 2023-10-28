package uy.um.edu.server.business.controllers;

import uy.um.edu.server.business.entities.vuelos.ReservaPista;
import uy.um.edu.server.business.entities.vuelos.ReservaPuerta;

public class ReservasDTO {
    private ReservaPuerta reservaPuerta;
    private ReservaPista reservaPista;

    public ReservasDTO() {
    }

    public ReservaPista getReservaPista() {
        return reservaPista;
    }

    public void setReservaPista(ReservaPista reservaPista) {
        this.reservaPista = reservaPista;
    }

    public ReservaPuerta getReservaPuerta() {
        return reservaPuerta;
    }

    public void setReservaPuerta(ReservaPuerta reservaPuerta) {
        this.reservaPuerta = reservaPuerta;
    }
}
