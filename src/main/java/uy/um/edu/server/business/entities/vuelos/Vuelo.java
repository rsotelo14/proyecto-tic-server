package uy.um.edu.server.business.entities.vuelos;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.cglib.core.Local;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.aeropuerto.PuertaAeropuerto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vuelos")
public class Vuelo {
    @Id  //Capaz podriamos hacer que el id no sea autogenerado, sino que sea el numero de vuelo
    @GeneratedValue(generator="vuelos_ids")
    @GenericGenerator(name="vuelos_ids", strategy = "increment")
    private Long id;
    @Column(unique = true , nullable = false)
    private String codigoVuelo;
    @ManyToOne
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;

    @ManyToOne
    @JoinColumn(name = "aeropuerto_origen_id")
    private Aeropuerto aeropuertoOrigen;
    @ManyToOne
    @JoinColumn(name = "aeropuerto_destino_id")
    private Aeropuerto aeropuertoDestino;
    @OneToMany (mappedBy = "vuelo")
    private List<Asientos> asientos;



    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private LocalTime horaSalidaEstimada;
    private LocalTime horaSalidaReal;
    private LocalTime horaLlegadaEstimada;
    private LocalTime horaLlegadaReal;
    private Long capacidadMaxima;
    private Long pasajerosConfirmados;

    private EstadoVuelo estado;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Avion avion;


    @OneToOne
    @JoinColumn(name = "reserva_puerta_origen_id")
    private ReservaPuerta reservaPuertaOrigen;

    @OneToOne
    @JoinColumn(name = "reserva_puerta_destino_id")
    private ReservaPuerta reservaPuertaDestino;


    @OneToOne
    @JoinColumn(name = "reserva_pista_origen_id")
    private ReservaPista reservaPistaOrigen;
    @OneToOne
    @JoinColumn(name = "reserva_pista_destino_id")
    private ReservaPista reservaPistaDestino;

    public Vuelo() {
    }


    public Vuelo(String codigoVuelo , Aerolinea aerolinea, Aeropuerto aeropuerto_origen,

                 Aeropuerto aeropuerto_destino,
                 LocalDate fecha_salida, LocalDate fecha_llegada, LocalTime hora_salida_estimada, LocalTime hora_salida_real,
                 LocalTime hora_llegada_estimada, LocalTime hora_llegada_real, Long capacidad_maxima,
                 Long pasajeros_confirmados, Avion avion, EstadoVuelo estado) {
        this.codigoVuelo=codigoVuelo;
        this.aerolinea = aerolinea;
        this.aeropuertoOrigen = aeropuerto_origen;
        this.aeropuertoDestino = aeropuerto_destino;
        this.fechaSalida = fecha_salida;
        this.fechaLlegada = fecha_llegada;
        this.horaSalidaEstimada = hora_salida_estimada;
        this.horaSalidaReal = hora_salida_real;
        this.horaLlegadaEstimada = hora_llegada_estimada;
        this.horaLlegadaReal = hora_llegada_real;

        this.capacidadMaxima = capacidad_maxima;
        this.pasajerosConfirmados = pasajeros_confirmados;
        this.avion = avion;
        this.estado= estado;
    }

    public List<Asientos> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asientos> asientos) {
        this.asientos = asientos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(codigoVuelo, vuelo.codigoVuelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoVuelo);
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalTime getHoraSalidaEstimada() {
        return horaSalidaEstimada;
    }

    public void setHoraSalidaEstimada(LocalTime horaSalidaEstimada) {
        this.horaSalidaEstimada = horaSalidaEstimada;
    }

    public LocalTime getHoraSalidaReal() {
        return horaSalidaReal;
    }

    public void setHoraSalidaReal(LocalTime horaSalidaReal) {
        this.horaSalidaReal = horaSalidaReal;
    }

    public LocalTime getHoraLlegadaEstimada() {
        return horaLlegadaEstimada;
    }

    public void setHoraLlegadaEstimada(LocalTime horaLlegadaEstimada) {
        this.horaLlegadaEstimada = horaLlegadaEstimada;
    }

    public LocalTime getHoraLlegadaReal() {
        return horaLlegadaReal;
    }

    public void setHoraLlegadaReal(LocalTime horaLlegadaReal) {
        this.horaLlegadaReal = horaLlegadaReal;
    }


    public Long getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Long capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Long getPasajerosConfirmados() {
        return pasajerosConfirmados;
    }

    public void setPasajerosConfirmados(Long pasajerosConfirmados) {
        this.pasajerosConfirmados = pasajerosConfirmados;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVuelo estado) {
        this.estado = estado;
    }
}
