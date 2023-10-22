package uy.um.edu.server.persistence;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findOneByPasaporte(String pasaporte);
    Usuario findOneByCorreo(String correo);

}
