package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.pasajeros.Pasajero;
import uy.um.edu.server.business.entities.vuelos.Asientos;
import uy.um.edu.server.business.entities.vuelos.EstadoVuelo;
import uy.um.edu.server.business.entities.vuelos.Valija;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
import uy.um.edu.server.business.exceptions.EntidadNoExiste;
import uy.um.edu.server.business.exceptions.EntidadYaExiste;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.persistence.PasajeroRepository;
import uy.um.edu.server.persistence.aeropuerto.AeropuertoRepository;
import uy.um.edu.server.persistence.vuelos.AsientoRepository;
import uy.um.edu.server.persistence.vuelos.ValijaRepository;
import uy.um.edu.server.persistence.vuelos.VueloRepository;
import uy.um.edu.server.business.entities.*;

import java.util.ArrayList;
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
    @Autowired
    @Lazy
    private PasajeroRepository pasajeroRepository;
    @Autowired
    private AsientoRepository asientoRepository;
    @Autowired
    private ValijaRepository valijaRepository;

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
        vuelo.setAsientos(new ArrayList<Asientos>());
        vueloRepository.save(vuelo);

        for (int i = 0; i < vuelo.getCapacidadMaxima(); i++) {
            Asientos asiento = new Asientos();
            asiento.setCodigoAsiento(vuelo.getCodigoVuelo()+ "-" + i);
            asiento.setVuelo(vuelo);
            asientoRepository.save(asiento);
            vuelo.getAsientos().add(asiento);
        }


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
    public void agregarPasajero(String codigoVuelo,String pasaporte) throws EntidadNoExiste {
        Pasajero pasajero =pasajeroRepository.findOneByPasaporte(pasaporte);
        if (pasajero==null){
            throw new EntidadNoExiste("Pasajero no existe");
        }
        Vuelo vuelo = vueloRepository.findOneByCodigoVuelo(codigoVuelo);
        if (vuelo==null){
            throw new EntidadNoExiste("Vuelo no existe");
        }
        Asientos asiento = vuelo.getAsientos().stream().filter(asientos -> asientos.getPasajero()==null).findFirst().get();
        asiento.setPasajero(pasajero);
        asientoRepository.save(asiento);

    }

    public Vuelo obtenerPorCodigoVuelo(String codigoVuelo) {
        return vueloRepository.findOneByCodigoVuelo(codigoVuelo);
    }

    public void checkIn(String codigoVuelo, String pasaporte, Integer cantidadValijas) throws EntidadNoExiste {
        Vuelo vuelo = vueloRepository.findOneByCodigoVuelo(codigoVuelo);
        if (vuelo==null){
            throw new EntidadNoExiste("Vuelo no existe");
        }
        Pasajero pasajero = pasajeroRepository.findOneByPasaporte(pasaporte);
        if (pasajero==null){
            throw new EntidadNoExiste("Pasajero no existe");
        }
        Asientos asiento = vuelo.getAsientos().stream().filter(asientos -> pasajero.equals(asientos.getPasajero())).findFirst().get();
        if (asiento==null){
            throw new EntidadNoExiste("El pasajero no tiene asiento asignado");
        }
        asiento.setCheckIn(true);
        asientoRepository.save(asiento);

        for (int i = 0; i < cantidadValijas; i++) {
            Valija valija = new Valija();
            valija.setAsiento(asiento);
            valija.setCodigoValija(asiento.getCodigoAsiento() + "-" + i);
            asiento.getValijas().add(valija);
            valijaRepository.save(valija);
        }


    }
}


