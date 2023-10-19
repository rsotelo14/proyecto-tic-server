package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.exceptions.UsuarioYaExiste;

@Service

public class UsuarioAeropuertoMgr {

    @Autowired
    private UsuarioMgr usuarioMgr;

    public void agregarUsuarioAeropuerto(UsuarioAeropuerto usuarioAeropuerto) throws InvalidInformation, EntidadYaExiste, UsuarioYaExiste {
        if (usuarioAeropuerto.getAeropuerto() == null) throw new InvalidInformation("Aeropuerto no puede ser nulo");
        usuarioMgr.agregarUsuario(usuarioAeropuerto);
    }


}
