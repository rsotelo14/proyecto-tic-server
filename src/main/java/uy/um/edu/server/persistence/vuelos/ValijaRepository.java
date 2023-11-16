package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.vuelos.Valija;

public interface ValijaRepository  extends CrudRepository<Valija, Long> {
}
