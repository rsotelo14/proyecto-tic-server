package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.PuertaAeropuerto;
import uy.um.edu.server.business.entities.vuelos.ReservaPuerta;

import java.time.LocalDate;
import java.util.List;

public interface ReservaPuertaRepository extends CrudRepository<ReservaPuerta,Long> {
    List<ReservaPuerta> findAllByPuertaIdAndFecha(Long puertaID, LocalDate fecha);
}
