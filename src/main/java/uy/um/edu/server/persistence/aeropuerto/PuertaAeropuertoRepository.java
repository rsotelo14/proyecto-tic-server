package uy.um.edu.server.persistence.aeropuerto;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.PuertaAeropuerto;

public interface PuertaAeropuertoRepository  extends CrudRepository<PuertaAeropuerto, Long> {
}
