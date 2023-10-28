package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;
import uy.um.edu.server.business.entities.vuelos.ReservaPista;

import java.time.LocalDate;
import java.util.List;

public interface ReservaPistaRepository extends CrudRepository<ReservaPista,Long> {
    List<ReservaPista> findAllByFecha(LocalDate fecha);

    List<ReservaPista> findAllByPistaIdAndFecha(Long pistaID, LocalDate fecha);
}
