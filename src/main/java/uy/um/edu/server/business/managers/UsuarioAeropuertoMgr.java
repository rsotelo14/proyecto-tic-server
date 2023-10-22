package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.server.business.exceptions.EntidadNoExiste;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.business.exceptions.UsuarioYaExiste;
import uy.um.edu.server.persistence.UsuarioRepository;

@Service

public class UsuarioAeropuertoMgr {

    @Autowired
    private UsuarioMgr usuarioMgr;
    @Autowired
    private AeropuertoMgr aeropuertoMgr;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void agregarUsuarioAeropuerto(UsuarioAeropuerto usuarioAeropuerto) throws InvalidInformation, EntidadYaExiste, UsuarioYaExiste, EntidadNoExiste {
        if (usuarioAeropuerto.getNombre() == null || "".equals(usuarioAeropuerto.getNombre())
                || usuarioAeropuerto.getApellido() == null || "".equals(usuarioAeropuerto.getApellido())
                || usuarioAeropuerto.getCorreo() == null || "".equals(usuarioAeropuerto.getCorreo())
                //Validar longitud y formato
                || usuarioAeropuerto.getContrasena() == null || "".equals(usuarioAeropuerto.getContrasena())
        ) {
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");
        }

        // Verifico si el cliente no existe
        if (usuarioRepository.findOneByPasaporte(usuarioAeropuerto.getPasaporte()) != null) {
            throw new EntidadYaExiste("Ya existe usuario con ese pasaporte");
        }
        if (usuarioRepository.findOneByCorreo(usuarioAeropuerto.getCorreo()) != null) {
            throw new EntidadYaExiste("Ya existe usuario con ese correo");
        }
        Aeropuerto aeropuerto = aeropuertoMgr.obtenerUnoPorCodigo(usuarioAeropuerto.getAeropuerto().getCodigo());
        if (aeropuerto == null) {
            throw new EntidadNoExiste("No existe la aerolinea");
        }
        assert aeropuerto.equals(usuarioAeropuerto.getAeropuerto());
        usuarioAeropuerto.setAeropuerto(aeropuerto);
        usuarioRepository.save(usuarioAeropuerto);
    }


}
