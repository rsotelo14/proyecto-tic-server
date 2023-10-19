package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.vuelos.Avion;
import uy.um.edu.server.persistence.vuelos.AvionRepository;

@Service
public class AvionMgr {
    @Autowired
    private AvionRepository avionRepository;
    public Avion obtenerUnoPorCodigo(String codigoAvion) {
        return avionRepository.findOneByCodigo(codigoAvion);
    }
}
