package uy.um.edu.server.persistence.aerolinea;


import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aerolinea.UsuarioAerolinea;

public interface UsuarioAerolineaRepository extends CrudRepository<UsuarioAerolinea, Long> {
}
