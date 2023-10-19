package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.managers.UsuarioMgr;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioMgr usuarioMgr;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = (List<Usuario>) usuarioMgr.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }
}
