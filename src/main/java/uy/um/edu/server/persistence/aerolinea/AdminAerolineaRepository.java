package uy.um.edu.server.persistence.aerolinea;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.AdminAeropuerto;

public interface AdminAerolineaRepository extends CrudRepository<AdminAeropuerto, Long> {

}
