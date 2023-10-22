package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
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
}
