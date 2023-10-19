package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.persistence.aeropuerto.MaleteroAeropuertoRepository;

@Service
public class MaleteroAeropuertoMgr {
    @Autowired
    private MaleteroAeropuertoRepository maleteroRepository;
}
