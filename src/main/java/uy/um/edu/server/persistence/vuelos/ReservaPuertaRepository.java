package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.vuelos.ReservaPuerta;

public interface ReservaPuertaRepository extends CrudRepository<ReservaPuerta,Long> {
}
