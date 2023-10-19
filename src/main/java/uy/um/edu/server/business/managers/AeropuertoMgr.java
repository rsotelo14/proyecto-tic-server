package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.vuelos.EstadoVuelo;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.persistence.aeropuerto.AeropuertoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AeropuertoMgr {

    @Autowired
    private AeropuertoRepository aeropuertoRepository;
    @Autowired
    private VueloMgr vueloMgr;

    public void agregarAeropuerto(Aeropuerto aeropuerto) throws InvalidInformation, EntidadYaExiste {
        //Verifica si la informacions es correcto
        if (aeropuerto.getNombre()==null || aeropuerto.getNombre().equals("")||aeropuerto.getCiudad()==null
            ||aeropuerto.getCiudad()==null||aeropuerto.getCiudad().equals("")||
                aeropuerto.getPais()==null||aeropuerto.getPais().equals("")||aeropuerto.getCodigo()==null||
                aeropuerto.getCodigo().equals("")|| aeropuerto.getDireccion()==null||aeropuerto.getDireccion()
                .equals("")||aeropuerto.getTelefono()==null||aeropuerto.getTelefono().equals("")){
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto)");
        }
        //Verifica si el aeropuerto no existe
        if (aeropuertoRepository.findOneByNombre(aeropuerto.getNombre())!=null){
            throw new EntidadYaExiste("El nombre del aeropuerto creado ya existe");
        }
        if (aeropuertoRepository.findOneByCodigo(aeropuerto.getCodigo())!=null){
            throw  new EntidadYaExiste("El codigo del aeropuerto creado ya existe");
        }
        aeropuertoRepository.save(aeropuerto);
    }
    public Iterable<Aeropuerto> findAll(){
        return aeropuertoRepository.findAll();
    }

    public Aeropuerto obtenerUnoPorCodigo(String codigo){
        return aeropuertoRepository.findOneByCodigo(codigo);
    }


    public List<Vuelo> obtenerVuelosPendientes(Aeropuerto aeropuerto){
        List<Vuelo> vuelosOrigen =  vueloMgr.obtenerVuelosPorAeropuertoOrigen(aeropuerto);
        List<Vuelo> vuelosDestino = vueloMgr.obtenerVuelosPorAeropuertoDestino(aeropuerto);
        List<Vuelo> vuelosPendientes = new ArrayList<>();
        for (Vuelo vuelo: vuelosOrigen){
            if (vuelo.getEstado().equals(EstadoVuelo.PENDIENTE) || vuelo.getEstado().equals(EstadoVuelo.VALIDADO_DESTINO)){
                vuelosPendientes.add(vuelo);
            }
        }
        for (Vuelo vuelo: vuelosDestino){
            if (vuelo.getEstado().equals(EstadoVuelo.PENDIENTE) || vuelo.getEstado().equals(EstadoVuelo.VALIDADO_ORIGEN)){
                vuelosPendientes.add(vuelo);
            }
        }
        return vuelosPendientes;
    }
}
