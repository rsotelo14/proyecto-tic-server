package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.persistence.aerolinea.AerolineaRepository;

import java.util.List;

@Service
public class AerolineaMgr {
    @Autowired
    private AerolineaRepository aerolineaRepository;

    public AerolineaMgr() {
    }
    public void agregarAerolinea(Aerolinea aerolinea) throws EntidadYaExiste {

        if (aerolineaRepository.findOneByNombre(aerolinea.getNombre()) != null)
            throw new EntidadYaExiste("Aerolinea ya existe");
        aerolineaRepository.save(aerolinea);
    }

    public Iterable<Aerolinea> obtenerTodos(){
        return aerolineaRepository.findAll();
    }

    public Aerolinea obtenerUnoPorNombre(String nombre) {
        return aerolineaRepository.findOneByNombre(nombre);
    }

    public Aerolinea obtenerUnoPorId(Long id) {
        return aerolineaRepository.findById(id).get();
    }

    public List<Aeropuerto> obtenerAeropuertosPorAerolinea(String codigoIATA) {
        return aerolineaRepository.findOneByCodigoIATA(codigoIATA).getAeropuertos();
    }


    public Aerolinea obtenerUnoPorCodigoIATA(String codigoIATA) {
        return aerolineaRepository.findOneByCodigoIATA(codigoIATA);
    }
}
