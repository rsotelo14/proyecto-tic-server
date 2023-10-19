package uy.um.edu.server.persistence;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.pasajeros.Pasajero;

public interface PasajeroRepository extends CrudRepository<Pasajero, Long> {
}
