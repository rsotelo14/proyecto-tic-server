package uy.um.edu.server.persistence.aerolinea;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;

import java.util.List;

public interface AerolineaRepository extends CrudRepository<Aerolinea, Long> {
    public Aerolinea findOneByNombre(String nombre);

    Aerolinea findOneByCodigoIATA(String codigoIATA);
    @Query("SELECT a FROM Aeropuerto a WHERE :aerolinea MEMBER OF a.aerolineas")
    List<Aeropuerto> findAssociatedAeropuertosForAirline(Aerolinea aerolinea);
}
