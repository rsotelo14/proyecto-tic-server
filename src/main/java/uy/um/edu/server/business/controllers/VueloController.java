package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.managers.VueloMgr;

@RestController
@RequestMapping("/vuelos")
public class VueloController {


    @Autowired
    private VueloMgr vueloMgr;

    @PostMapping("/{codigoVuelo}/validar/{codigoAeropuerto}")
    public void validarVuelo(@PathVariable String codigoVuelo, @PathVariable String codigoAeropuerto) {
        try {
            vueloMgr.validarVueloPorCodigos(codigoVuelo, codigoAeropuerto);
        } catch (InvalidInformation e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{codigoVuelo}/rechazar/{codigoAeropuerto}")
    public void rechazarVuelo(@PathVariable String codigoVuelo, @PathVariable String codigoAeropuerto) {
        try {
            vueloMgr.rechazarVueloPorCodigos(codigoVuelo, codigoAeropuerto);
        } catch (InvalidInformation e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
