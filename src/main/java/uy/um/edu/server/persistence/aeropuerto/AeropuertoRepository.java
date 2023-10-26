package uy.um.edu.server.persistence.aeropuerto;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;

import java.util.List;

public interface AeropuertoRepository extends CrudRepository<Aeropuerto, Long> {
    Aeropuerto findOneByNombre(String nombre);

    Aeropuerto findOneByCodigo(String codigo);

    @Query("SELECT a FROM Aerolinea a WHERE :aeropuerto NOT MEMBER OF a.aeropuertos")
    List<Aerolinea> findAvailableAirlinesForAirport(Aeropuerto aeropuerto);

    @Query("SELECT a FROM Aerolinea a WHERE :aeropuerto MEMBER OF a.aeropuertos")
    List<Aerolinea> findAssociatedAirlinesForAirport(Aeropuerto aeropuerto);
}
