package uy.um.edu.server.persistence.aeropuerto;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;

public interface PistaAeropuertoRepository extends CrudRepository<PistaAeropuerto, Long> {

    public PistaAeropuerto findByNumeroPista(Long numeroPista);
}
