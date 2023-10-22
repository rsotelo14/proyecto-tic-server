package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aerolinea.UsuarioAerolinea;
import uy.um.edu.server.business.exceptions.EntidadNoExiste;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.persistence.UsuarioRepository;
import uy.um.edu.server.persistence.aerolinea.UsuarioAerolineaRepository;

@Service
public class UsuarioAerolineaMgr {
    @Autowired
    private UsuarioAerolineaRepository usuarioAerolineaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AerolineaMgr aerolinaMgr;

    public UsuarioAerolineaMgr() {
    }

    public void agregarUsuarioAerolinea(UsuarioAerolinea usuarioAerolinea) throws InvalidInformation, EntidadYaExiste, EntidadNoExiste {
        if (usuarioAerolinea.getNombre() == null || "".equals(usuarioAerolinea.getNombre())
                || usuarioAerolinea.getApellido() == null || "".equals(usuarioAerolinea.getApellido())
                || usuarioAerolinea.getCorreo() == null || "".equals(usuarioAerolinea.getCorreo())
                //Validar longitud y formato
                || usuarioAerolinea.getContrasena() == null || "".equals(usuarioAerolinea.getContrasena())
        ) {
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");
        }

        // Verifico si el cliente no existe
        if (usuarioRepository.findOneByPasaporte(usuarioAerolinea.getPasaporte()) != null) {
            throw new EntidadYaExiste("Ya existe usuario con ese pasaporte");
        }
        if (usuarioRepository.findOneByCorreo(usuarioAerolinea.getCorreo()) != null) {
            throw new EntidadYaExiste("Ya existe usuario con ese correo");
        }
        Aerolinea aerolinea = aerolinaMgr.obtenerUnoPorCodigoIATA(usuarioAerolinea.getAerolinea().getCodigoIATA());
        if (aerolinea == null) {
            throw new EntidadNoExiste("No existe la aerolinea");
        }
        assert aerolinea.equals(usuarioAerolinea.getAerolinea());
        usuarioAerolinea.setAerolinea(aerolinea);
        usuarioAerolineaRepository.save(usuarioAerolinea);
    }

}
