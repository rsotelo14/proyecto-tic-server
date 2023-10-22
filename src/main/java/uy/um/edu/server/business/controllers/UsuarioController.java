package uy.um.edu.server.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.entities.aerolinea.UsuarioAerolinea;
import uy.um.edu.server.business.entities.aeropuerto.AdminAeropuerto;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.exceptions.UsuarioYaExiste;
import uy.um.edu.server.business.managers.UsuarioAerolineaMgr;
import uy.um.edu.server.business.managers.UsuarioAeropuertoMgr;
import uy.um.edu.server.business.managers.UsuarioMgr;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioMgr usuarioMgr;
    @Autowired
    private UsuarioAeropuertoMgr usuarioAeropuertoMgr;
    @Autowired
    private UsuarioAerolineaMgr usuarioAerolineaMgr;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = (List<Usuario>) usuarioMgr.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{correo}")
    public ResponseEntity<Usuario> obtenerUnoPorCorreo(@PathVariable String correo) {
        Usuario usuario = usuarioMgr.obtenerUnoPorCorreo(correo);
        return ResponseEntity.ok(usuario);

    }

    @PostMapping
    public ResponseEntity<String> agregarUsuario( @RequestBody Usuario usuario) {
        System.out.println(usuario.getNombre());

        try {
            if (usuario instanceof AdminAeropuerto){
                usuarioAeropuertoMgr.agregarUsuarioAeropuerto((AdminAeropuerto) usuario);
            } else if (usuario instanceof UsuarioAerolinea) {
                usuarioAerolineaMgr.agregarUsuarioAerolinea((UsuarioAerolinea) usuario);
            }
            else {
                usuarioMgr.agregarUsuario(usuario);
            }
            return new ResponseEntity<>("Usuario creado",HttpStatus.CREATED);
        } catch (InvalidInformation e) {
            return new ResponseEntity<>("Información inválida",HttpStatus.BAD_REQUEST);
        } catch (UsuarioYaExiste e) {
            return new ResponseEntity<>("Usuario ya existe", HttpStatus.CONFLICT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
