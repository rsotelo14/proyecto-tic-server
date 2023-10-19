package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.exceptions.UsuarioYaExiste;
import uy.um.edu.server.persistence.UsuarioRepository;

@Service
public class UsuarioMgr {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void agregarUsuario(Usuario usuario)
            throws InvalidInformation, UsuarioYaExiste {

        if (usuario.getNombre() == null || "".equals(usuario.getNombre())
            || usuario.getApellido() == null || "".equals(usuario.getApellido())
            || usuario.getCorreo() == null || "".equals(usuario.getCorreo())
                //Validar longitud y formato
            || usuario.getContrasena() == null || "".equals(usuario.getContrasena())
        ) {
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");
        }

        // Verifico si el cliente no existe
        if (usuarioRepository.findOneByPasaporte(usuario.getPasaporte()) != null) {
            throw new UsuarioYaExiste("Ya existe usuario con ese pasaporte");
        }
        if (usuarioRepository.findOneByCorreo(usuario.getCorreo()) != null) {
            throw new UsuarioYaExiste("Ya existe usuario con ese correo");
        }

        usuarioRepository.save(usuario);

    }

    public Iterable<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }


    public Usuario obtenerUnoPorCorreo(String correoAeropuerto) {
        return usuarioRepository.findOneByCorreo(correoAeropuerto);
    }
}
