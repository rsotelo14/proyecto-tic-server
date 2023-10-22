package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.managers.AerolineaMgr;

import java.util.List;

@RestController
@RequestMapping("/aerolineas")
public class AerolineaController {

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @GetMapping("/{codigoIATA}/aeropuertos")
    public ResponseEntity<List<Aeropuerto>> obtenerAeropuertosPorAerolinea(@PathVariable String codigoIATA) {

        List<Aeropuerto> aeropuertos = (List<Aeropuerto>) aerolineaMgr.obtenerAeropuertosPorAerolinea(codigoIATA);
        return ResponseEntity.ok(aeropuertos);
    }

    @PostMapping
    public ResponseEntity<Aerolinea> agregarAerolinea(@RequestBody Aerolinea aerolinea) {
        try {
            aerolineaMgr.agregarAerolinea(aerolinea);
            return ResponseEntity.ok().build();
        } catch (EntidadYaExiste e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
