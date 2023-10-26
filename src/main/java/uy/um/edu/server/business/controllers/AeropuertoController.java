package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.managers.AeropuertoMgr;

import java.util.List;

@RestController
@RequestMapping("/aeropuertos")
public class AeropuertoController {

    @Autowired
    private AeropuertoMgr aeropuertoMgr;

    @GetMapping("/{codigo}/usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorAeropuerto(@PathVariable String codigo) {
        List<Usuario> usuarios = (List<Usuario>) aeropuertoMgr.obtenerUsuariosPorAeropuerto(codigo);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{codigo}/vuelos-pendientes")
    public ResponseEntity<List<Vuelo>> obtenerVuelosPendientesPorAeropuerto(@PathVariable String codigo) {
        List<Vuelo> usuarios = (List<Vuelo>) aeropuertoMgr.obtenerVuelosPendientesPorAeropuerto(codigo);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<String> agregarAeropuerto(@RequestBody Aeropuerto aeropuerto) {
        try {
            aeropuertoMgr.agregarAeropuerto(aeropuerto);
            return new ResponseEntity<String>("Aeropuerto creado", HttpStatus.CREATED);
        } catch (InvalidInformation e) {
            return new ResponseEntity<String>("Información inválida", HttpStatus.BAD_REQUEST);
        } catch (EntidadYaExiste e) {
            return new ResponseEntity<String>("Aeropuerto ya existe", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/{codigo}/asociar-aerolinea/{codigoAerolinea}")
    public ResponseEntity<String> asociarAerolinea(@PathVariable String codigo, @PathVariable String codigoAerolinea) {
        try {
            aeropuertoMgr.asociarAerolinea(codigo, codigoAerolinea);
            return new ResponseEntity<String>("Aerolinea asociada", HttpStatus.OK);
        } catch (InvalidInformation e) {
            return new ResponseEntity<String>("Información inválida", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}/aerolineas-disponibles")
    public ResponseEntity<List<Aerolinea>> obtenerAerolineasDisponibles(@PathVariable String codigo) {
        List<Aerolinea> aerolineas = null;
        try {
            aerolineas = aeropuertoMgr.obtenerAerolineasDisponibles(codigo);
        } catch (InvalidInformation e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(aerolineas);
    }
    @GetMapping("/{codigo}/aerolineas-asociadas")
    public ResponseEntity<List<Aerolinea>> obtenerAerolineasAsociadas(@PathVariable String codigo) {
        List<Aerolinea> aerolineas = null;
        try {
            aerolineas = aeropuertoMgr.obtenerAerolineasAsociadas(codigo);
        } catch (InvalidInformation e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(aerolineas);
    }
}
