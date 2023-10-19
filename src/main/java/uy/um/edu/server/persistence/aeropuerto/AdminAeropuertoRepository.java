package uy.um.edu.server.persistence.aeropuerto;


import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.AdminAeropuerto;

public interface AdminAeropuertoRepository extends CrudRepository<AdminAeropuerto, Long> {

}
