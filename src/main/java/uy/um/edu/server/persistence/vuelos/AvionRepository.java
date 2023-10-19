package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.vuelos.Avion;

public interface AvionRepository  extends CrudRepository<Avion, Long> {
    public Avion findOneByCodigo(String codigoAvion);
}
