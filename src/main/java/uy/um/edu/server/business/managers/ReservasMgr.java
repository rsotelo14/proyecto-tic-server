package uy.um.edu.server.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.aeropuerto.PistaAeropuerto;
import uy.um.edu.server.business.entities.aeropuerto.PuertaAeropuerto;
import uy.um.edu.server.business.entities.vuelos.ReservaPista;
import uy.um.edu.server.business.entities.vuelos.ReservaPuerta;
import uy.um.edu.server.business.entities.vuelos.Vuelo;
import uy.um.edu.server.business.exceptions.InvalidInformation;
import uy.um.edu.server.persistence.aeropuerto.PistaAeropuertoRepository;
import uy.um.edu.server.persistence.aeropuerto.PuertaAeropuertoRepository;
import uy.um.edu.server.persistence.vuelos.ReservaPistaRepository;
import uy.um.edu.server.persistence.vuelos.ReservaPuertaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservasMgr {

    private static final int INTERVALO_PISTAS_MINUTOS = 30;

    @Autowired
    private VueloMgr vueloMgr;
    @Autowired
    private ReservaPistaRepository reservaPistaRepository;
    @Autowired
    private ReservaPuertaRepository reservaPuertaRepository;
    @Autowired
    private AeropuertoMgr aeropuertoMgr;
    @Autowired
    private PistaAeropuertoRepository pistaAeropuertoRepository;
    @Autowired
    private PuertaAeropuertoRepository puertaAeropuertoRepository;

    public void reservarPistaYPuerta(ReservaPista reservaPista, ReservaPuerta reservaPuerta) throws InvalidInformation {
        System.out.println("Aeropuerto Pista: " + reservaPista.getPista().getAeropuerto().getCodigo());
        System.out.println("Aeropuerto Puerta: " + reservaPuerta.getPuerta().getAeropuerto().getCodigo());
        if ( reservaPista.getPista().getAeropuerto() == null || reservaPuerta.getPuerta().getAeropuerto() == null||!reservaPista.getPista().getAeropuerto().equals(reservaPuerta.getPuerta().getAeropuerto())) {
            System.out.println(reservaPista.getPista().getAeropuerto() == null);
            System.out.println(reservaPuerta.getPuerta().getAeropuerto() == null);
            System.out.println(reservaPista.getPista().getAeropuerto().equals(reservaPuerta.getPuerta().getAeropuerto()));
            throw new InvalidInformation("La pista y la puerta no pertenecen al mismo aeropuerto");
        }
        if (!reservaPista.getFecha().equals( reservaPuerta.getFecha())) {
            System.out.println(reservaPista.getFecha());
            System.out.println(reservaPuerta.getFecha());
            throw new InvalidInformation("La pista y la puerta no pertenecen a la misma fecha");
        }
        if (reservaPista.getVuelo() == null || reservaPuerta.getVuelo() == null|| !reservaPista.getVuelo().equals( reservaPuerta.getVuelo())  ) {
            System.out.println(reservaPista.getVuelo().getCodigoVuelo());
            System.out.println(reservaPuerta.getVuelo().getCodigoVuelo());
            throw new InvalidInformation("La pista y la puerta no pertenecen al mismo vuelo");
        }
        Aeropuerto aeropuerto = reservaPista.getPista().getAeropuerto();
        LocalTime horaAValidar;
        LocalDate fechaAValidar;
        if (aeropuerto.equals(reservaPuerta.getVuelo().getAeropuertoOrigen())){


            horaAValidar = reservaPuerta.getVuelo().getHoraSalidaEstimada();
        }
        else if (aeropuerto.equals(reservaPuerta.getVuelo().getAeropuertoDestino())){
            horaAValidar = reservaPuerta.getVuelo().getHoraLlegadaEstimada();
        }
        else {
            throw new InvalidInformation("La pista y la puerta no pertenecen al mismo aeropuerto");
        }
        if (!(reservaPista.getHoraInicio().isBefore(horaAValidar) && reservaPista.getHoraInicio().plusMinutes(INTERVALO_PISTAS_MINUTOS).isAfter(horaAValidar))){
            throw new InvalidInformation("Se debe reservar la pista en el horario adecuado del vuelo");
        }
        if (!(reservaPuerta.getHoraInicio().isBefore(horaAValidar) && reservaPuerta.getHoraFin().isAfter(horaAValidar))){
            throw new InvalidInformation("Se debe reservar la puerta en el horario adecuado del vuelo");
        }


        //Validar reserva pista
        List<ReservaPista> reservasFechaPista = reservaPistaRepository.findAllByPistaIdAndFecha(reservaPista.getPista().getId(),reservaPista.getFecha());
        System.out.println("Reservas fecha pista: " + reservasFechaPista.size());
        for (ReservaPista reservaAValidar : reservasFechaPista){
            System.out.println("Reserva A Validar: Inicio=" + reservaAValidar.getHoraInicio() + ", Fin=" + reservaAValidar.getHoraInicio().plusMinutes(INTERVALO_PISTAS_MINUTOS));
            System.out.println("Reserva Pista: Inicio=" + reservaPista.getHoraInicio() + ", Fin=" + reservaPista.getHoraInicio().plusMinutes(INTERVALO_PISTAS_MINUTOS));

            if (!(reservaAValidar.getHoraInicio().plusMinutes(INTERVALO_PISTAS_MINUTOS).isBefore(reservaPista.getHoraInicio())
                    || reservaPista.getHoraInicio().plusMinutes(INTERVALO_PISTAS_MINUTOS).isBefore(reservaAValidar.getHoraInicio()))) {
                throw new InvalidInformation("La pista ya está reservada en ese horario");
            }

        }
        //Validar reserva puerta
        List<ReservaPuerta> reservasFechaPuerta = reservaPuertaRepository.findAllByPuertaIdAndFecha(reservaPuerta.getPuerta().getId(),reservaPuerta.getFecha());
        System.out.println("Reservas fecha puerta: " + reservasFechaPuerta.size());
        for (ReservaPuerta reservaAValidar : reservasFechaPuerta){

            if (!(reservaAValidar.getHoraFin().isBefore(reservaPuerta.getHoraInicio())
                    || reservaPuerta.getHoraFin().isBefore(reservaAValidar.getHoraInicio()))) {
                throw new InvalidInformation("La puerta ya está reservada en ese horario");
            }
        }

        System.out.println("PASO VALIDACIONES");
        Aeropuerto aeropuertoRep = aeropuertoMgr.obtenerAeropuertoPorCodigo(aeropuerto.getCodigo());
        assert aeropuertoRep.equals(aeropuerto);
        Vuelo vuelo = reservaPista.getVuelo();
        Vuelo vueloRep = vueloMgr.obtenerPorCodigoVuelo(vuelo.getCodigoVuelo());
        assert vueloRep.equals(vuelo);

        PistaAeropuerto pistaRep = pistaAeropuertoRepository.findByNumeroPista(reservaPista.getPista().getNumeroPista());
        assert pistaRep.equals(reservaPista.getPista());
        reservaPista.setPista(pistaRep);

        PuertaAeropuerto puertaRep = puertaAeropuertoRepository.findByNumeroPuerta(reservaPuerta.getPuerta().getNumeroPuerta());
        assert puertaRep.equals(reservaPuerta.getPuerta());
        reservaPuerta.setPuerta(puertaRep);

        vueloMgr.validarVuelo(vuelo, aeropuerto);
        reservaPistaRepository.save(reservaPista);
        reservaPistaRepository.save(reservaPista);
        reservaPuertaRepository.save(reservaPuerta);



    }
}
