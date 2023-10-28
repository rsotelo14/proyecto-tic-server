package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.vuelos.Avion;

import java.util.List;

public interface AvionRepository  extends CrudRepository<Avion, Long> {
    public Avion findOneByCodigo(String codigoAvion);
    public List<Avion> findAllByAerolineaCodigoIATA(String codigoAerolinea);
}
