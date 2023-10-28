package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.vuelos.EstadoVuelo;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.persistence.aeropuerto.AeropuertoRepository;
import uy.um.edu.server.persistence.vuelos.VueloRepository;

import java.util.List;

@Service
public class VueloMgr {
    @Autowired
    private VueloRepository vueloRepository;
    @Autowired
    @Lazy
    private AeropuertoMgr aeropuertoMgr;

    @Autowired
    @Lazy
    private AerolineaMgr aerolineaMgr;

    public void agregarVuelo(Vuelo vuelo) throws InvalidInformation, EntidadYaExiste {
        //verifico si la informacion del vuelo es válida
        if (vuelo.getAerolinea()==null||vuelo.getAeropuertoOrigen()==null||
        vuelo.getAeropuertoDestino()==null|| vuelo.getFechaSalida()==null||vuelo.getFechaLlegada()==null||vuelo.getHoraSalidaEstimada()==null
        ||vuelo.getHoraLlegadaEstimada()==null||vuelo.getEstado()==null||vuelo.getCapacidadMaxima()==null){
            throw new InvalidInformation("Los datos ingresados no son correctos");
        }
        //verifico si no esta creado ya el vuelo
        if (vueloRepository.findOneByCodigoVuelo(vuelo.getCodigoVuelo())!=null){
            throw new EntidadYaExiste("Ya existe un vuelo con este codigo");
        }
        Aeropuerto aeropuertoOrigen = aeropuertoMgr.obtenerUnoPorCodigo(vuelo.getAeropuertoOrigen().getCodigo());
        Aeropuerto aeropuertoDestino = aeropuertoMgr.obtenerUnoPorCodigo(vuelo.getAeropuertoDestino().getCodigo());
        Aerolinea aerolinea = aerolineaMgr.obtenerUnoPorCodigoIATA(vuelo.getAerolinea().getCodigoIATA());
        if (aeropuertoOrigen==null||aeropuertoDestino==null){
            throw new InvalidInformation("Alguno de los aeropuertos no existe");
        }
        if (aerolinea==null){
            throw new InvalidInformation("La aerolinea no existe");
        }
        assert aeropuertoOrigen.equals(vuelo.getAeropuertoOrigen());
        assert aeropuertoDestino.equals(vuelo.getAeropuertoDestino());
        assert aerolinea.equals(vuelo.getAerolinea());
        vuelo.setAeropuertoOrigen(aeropuertoOrigen);
        vuelo.setAeropuertoDestino(aeropuertoDestino);
        vuelo.setAerolinea(aerolinea);



        vueloRepository.save(vuelo);
    }

    public List<Vuelo> obtenerVuelosPorAeropuertoOrigen(Aeropuerto aeropuertoOrigen){
        return vueloRepository.findByAeropuertoOrigen(aeropuertoOrigen);
    }

    public List<Vuelo> obtenerVuelosPorAeropuertoDestino(Aeropuerto aeropuertoDestino){
        return vueloRepository.findByAeropuertoDestino(aeropuertoDestino);
    }
    public Iterable<Vuelo> obtenerTodo(){
        return vueloRepository.findAll();
    }

    public void validarVuelo(Vuelo vuelo, Aeropuerto aeropuerto) throws InvalidInformation {
        Aerolinea aerolineaRep = aerolineaMgr.obtenerUnoPorCodigoIATA(vuelo.getAerolinea().getCodigoIATA());
        assert aerolineaRep.equals(vuelo.getAerolinea());
        vuelo.setAerolinea(aerolineaRep);

        if (vuelo.getAeropuertoOrigen().equals(aeropuerto)){
            if(vuelo.getEstado().equals(EstadoVuelo.PENDIENTE)){
                vuelo.setEstado(EstadoVuelo.VALIDADO_ORIGEN);
                vueloRepository.save(vuelo);
            } else if (vuelo.getEstado().equals(EstadoVuelo.VALIDADO_DESTINO)){
                vuelo.setEstado(EstadoVuelo.CONFIRMADO);
                vueloRepository.save(vuelo);
            }
        } else if (vuelo.getAeropuertoDestino().equals(aeropuerto)) {
            if (vuelo.getEstado().equals(EstadoVuelo.PENDIENTE)){
                vuelo.setEstado(EstadoVuelo.VALIDADO_DESTINO);
                vueloRepository.save(vuelo);
            } else if (vuelo.getEstado().equals(EstadoVuelo.VALIDADO_ORIGEN)){
                vuelo.setEstado(EstadoVuelo.CONFIRMADO);
                vueloRepository.save(vuelo);
            }
        }
        else {
            throw new InvalidInformation("El aeropuerto no es ni el de origen ni el de destino");
        }
    }

    public void rechazarVuelo(Vuelo vuelo, Aeropuerto aeropuerto) throws InvalidInformation {
        if (!(vuelo.getAeropuertoOrigen().equals(aeropuerto) || vuelo.getAeropuertoDestino().equals(aeropuerto))){
            throw new InvalidInformation("El aeropuerto no es ni el de origen ni el de destino");
        }
        else {
            vuelo.setEstado(EstadoVuelo.CANCELADO);
            vueloRepository.save(vuelo);
        }

    }

    public void validarVueloPorCodigos(String codigoVuelo, String codigoAeropuerto) throws InvalidInformation {
        Vuelo vuelo = vueloRepository.findOneByCodigoVuelo(codigoVuelo);
        Aeropuerto aeropuerto = aeropuertoMgr.obtenerUnoPorCodigo(codigoAeropuerto);
        validarVuelo(vuelo, aeropuerto);
    }

    public void rechazarVueloPorCodigos(String codigoVuelo, String codigoAeropuerto) throws InvalidInformation {
        Vuelo vuelo = vueloRepository.findOneByCodigoVuelo(codigoVuelo);
        Aeropuerto aeropuerto = aeropuertoMgr.obtenerUnoPorCodigo(codigoAeropuerto);
        rechazarVuelo(vuelo, aeropuerto);
    }

    public Vuelo obtenerPorCodigoVuelo(String codigoVuelo) {
        return vueloRepository.findOneByCodigoVuelo(codigoVuelo);
    }
}


