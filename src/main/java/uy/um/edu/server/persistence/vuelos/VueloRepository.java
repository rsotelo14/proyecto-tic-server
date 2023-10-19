package uy.um.edu.server.persistence.vuelos;

import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.vuelos.Vuelo;

import java.util.List;

public interface VueloRepository  extends CrudRepository<Vuelo, Long> {
    Vuelo findOneByCodigoVuelo(String codigoVuelo);

    List<Vuelo> findByAeropuertoOrigen(Aeropuerto aeropuertoOrigen);

    List<Vuelo> findByAeropuertoDestino(Aeropuerto aeropuertoDestino);
}
