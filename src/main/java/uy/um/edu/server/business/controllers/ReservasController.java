package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.um.edu.server.business.entities.vuelos.ReservaPista;
import uy.um.edu.server.business.entities.vuelos.ReservaPuerta;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.managers.ReservasMgr;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasMgr reservasMgr;

    @PostMapping
    public ResponseEntity<String> reservarPistaYPuerta(@RequestBody ReservasDTO reservasDTO) {
        ReservaPista reservaPista = reservasDTO.getReservaPista();
        ReservaPuerta reservaPuerta = reservasDTO.getReservaPuerta();

        try {
            reservasMgr.reservarPistaYPuerta(reservaPista, reservaPuerta);
            return ResponseEntity.ok().body("Reserva realizada con exito");
        } catch (InvalidInformation e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
