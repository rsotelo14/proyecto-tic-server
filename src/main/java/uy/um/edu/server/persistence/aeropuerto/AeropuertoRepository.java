package uy.um.edu.server.persistence.aeropuerto;


import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;

public interface AeropuertoRepository  extends CrudRepository<Aeropuerto, Long> {
Aeropuerto findOneByNombre(String nombre);
Aeropuerto findOneByCodigo(String codigo);
}
