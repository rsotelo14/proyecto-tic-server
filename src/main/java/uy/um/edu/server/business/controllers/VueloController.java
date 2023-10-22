package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
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
            return new ResponseEntity<String>("Vuelo rechazado",HttpStatus.OK);
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

}
