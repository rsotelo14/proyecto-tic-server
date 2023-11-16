package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.entities.aerolinea.PasaporteCodigoVuelo;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
import uy.um.edu.server.business.exceptions.EntidadNoExiste;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.managers.VueloMgr;

@RestController
@RequestMapping("/vuelos")
public class VueloController {


    @Autowired
    private VueloMgr vueloMgr;

    @PostMapping("/{codigoVuelo}/validar/{codigoAeropuerto}")
    public ResponseEntity<String> validarVuelo(@PathVariable String codigoVuelo, @PathVariable String codigoAeropuerto) {
        try {
            vueloMgr.validarVueloPorCodigos(codigoVuelo, codigoAeropuerto);
            return new ResponseEntity<>("Vuelo validado", HttpStatus.OK);
        } catch (InvalidInformation e) {
            return new ResponseEntity<>("Información inválida", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{codigoVuelo}/rechazar/{codigoAeropuerto}")
    public ResponseEntity<String> rechazarVuelo(@PathVariable String codigoVuelo, @PathVariable String codigoAeropuerto) {
        try {
            vueloMgr.rechazarVueloPorCodigos(codigoVuelo, codigoAeropuerto);
            return new ResponseEntity<String>("Vuelo rechazado", HttpStatus.OK);
        } catch (InvalidInformation e) {
            return new ResponseEntity<>("Información inválida", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    public ResponseEntity<String> agregarVuelo(@RequestBody Vuelo vuelo) {
        try {
            vueloMgr.agregarVuelo(vuelo);
            return new ResponseEntity<>("Vuelo creado", HttpStatus.CREATED);
        } catch (InvalidInformation e) {
            return new ResponseEntity<>("Información inválida", HttpStatus.BAD_REQUEST);
        } catch (EntidadYaExiste e) {
            return new ResponseEntity<>("Vuelo ya existe", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/asignar-pasajero")
    public ResponseEntity<String> recibirPasaporteCodigoVuelo(@RequestBody PasaporteCodigoVuelo pasaporteCodigoVuelo) {
        try {
            vueloMgr.agregarPasajero(pasaporteCodigoVuelo.getCodigoVuelo(), pasaporteCodigoVuelo.getPasaporte());
            return new ResponseEntity<>("Pasajero agregado", HttpStatus.CREATED);
        } catch (EntidadNoExiste e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/check-in")
    public ResponseEntity<String> checkIn(@RequestBody PasaporteCodigoVuelo pasaporteCodigoVuelo) {
        try {
            vueloMgr.checkIn(pasaporteCodigoVuelo.getCodigoVuelo(), pasaporteCodigoVuelo.getPasaporte(), pasaporteCodigoVuelo.getCantidadValijas());
            return new ResponseEntity<>("Check-in realizado", HttpStatus.OK);

        } catch (EntidadNoExiste e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
