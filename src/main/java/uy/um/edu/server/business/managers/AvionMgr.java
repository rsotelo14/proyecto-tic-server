package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.vuelos.Avion;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.persistence.vuelos.AvionRepository;

import java.util.List;

@Service
public class AvionMgr {
    @Autowired
    private AvionRepository avionRepository;
    public Avion obtenerUnoPorCodigo(String codigoAvion) {
        return avionRepository.findOneByCodigo(codigoAvion);
    }
    public void agregarAvion(Avion avion) throws EntidadYaExiste {
        if (avionRepository.findOneByCodigo(avion.getCodigo()) != null)
            throw new EntidadYaExiste("Avion ya existe");
        avionRepository.save(avion);

    }
    public List<Avion> obtenerAvionesAerolinea(String codigoAerolinea) {
        return avionRepository.findAllByAerolineaCodigoIATA(codigoAerolinea);
    }
}
