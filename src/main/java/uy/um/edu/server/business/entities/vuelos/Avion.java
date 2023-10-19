
package uy.um.edu.server.business.entities.vuelos;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;

import java.util.List;

@Entity
@Table(name = "aviones")
public class Avion {
    @Id
    @GeneratedValue
    @GenericGenerator(name="avion_id", strategy = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;
    @Column(unique = true)
    private String codigo;
    private String nombre;
    private Long capacidad;
    private String tipoAvion;
    @OneToMany(mappedBy = "avion")
    private List<Vuelo> vuelos;

    public Avion(Long id, Aerolinea aerolinea, String nombre, Long capacidad, String tipoAvion, List<Vuelo> vuelos) {
        this.id = id;
        this.aerolinea = aerolinea;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoAvion = tipoAvion;
        this.vuelos = vuelos;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Long capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(String tipoAvion) {
        this.tipoAvion = tipoAvion;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public Avion() {
    }
}
