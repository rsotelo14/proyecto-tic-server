package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.exceptions.UsuarioYaExiste;
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

    @PostMapping
    public ResponseEntity<?> agregarUsuario( @RequestBody Usuario usuario) {
        try {
            usuarioMgr.agregarUsuario(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidInformation e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UsuarioYaExiste e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
