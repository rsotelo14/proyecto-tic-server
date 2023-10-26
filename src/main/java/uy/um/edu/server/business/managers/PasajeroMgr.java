package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.pasajeros.Pasajero;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.persistence.PasajeroRepository;

@Service
public class PasajeroMgr {
       @Autowired
        private PasajeroRepository pasajeroRepository;
        public Pasajero obtenerUnoPorPasaporte(String pasaporte){
            return pasajeroRepository.findOneByPasaporte(pasaporte);
        }
        public Pasajero obtenerUnoPorCorreo(String correo){
            return pasajeroRepository.findOneByCorreo(correo);
        }
        public void agregarPasajero(Pasajero pasajero) throws EntidadYaExiste, InvalidInformation {
            if (pasajeroRepository.findOneByPasaporte(pasajero.getPasaporte()) != null)
                throw new EntidadYaExiste("Ya existe un pasajero con ese pasaporte");
            if (pasajeroRepository.findOneByCorreo(pasajero.getCorreo()) != null)
                throw new EntidadYaExiste("Ya existe un pasajero con ese correo");
            pasajeroRepository.save(pasajero);
            if (pasajero.getNombre() == null || "".equals(pasajero.getNombre())
                    || pasajero.getApellido() == null || "".equals(pasajero.getApellido())
                    || pasajero.getCorreo() == null || "".equals(pasajero.getCorreo())
                    || pasajero.getContrasena() == null || "".equals(pasajero.getContrasena())
            ) {
                throw new InvalidInformation("Alguno de los datos ingresados no es correcto");
            }
            pasajeroRepository.save(pasajero);
        }


}
