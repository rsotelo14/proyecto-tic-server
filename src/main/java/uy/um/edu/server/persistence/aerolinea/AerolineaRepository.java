package uy.um.edu.server.persistence.aerolinea;


import org.springframework.data.repository.CrudRepository;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;

public interface AerolineaRepository extends CrudRepository<Aerolinea, Long> {
    public Aerolinea findOneByNombre(String nombre);
}
