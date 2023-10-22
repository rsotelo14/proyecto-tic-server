package uy.um.edu.server.business.entities.aerolinea;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import uy.um.edu.server.business.entities.aeropuerto.Aeropuerto;
import uy.um.edu.server.business.entities.vuelos.Avion;
import uy.um.edu.server.business.entities.vuelos.Vuelo;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aerolineas")


public class Aerolinea {
    @Id
    @GeneratedValue
    @GenericGenerator(name="aerolinea_id", strategy = "increment")
    private Long id;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
    }
    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String codigoIATA;

    @Column(unique = true)
    private String codigoICAO;

    @Column
    private String paisDeOrigen;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "aerolineas", fetch = FetchType.EAGER)
    private List<Aeropuerto> aeropuertos=new ArrayList<>();
    @OneToMany(mappedBy = "aerolinea")
    @JsonIgnore
    private List<Vuelo> vuelos=new ArrayList<>();
    @OneToMany(mappedBy = "aerolinea")
    @JsonIgnore
    private List<Avion> aviones=new ArrayList<>();

    public Aerolinea() {
    }
    @JsonIgnore
    @OneToMany(mappedBy = "aerolinea")
    private List<UsuarioAerolinea> usuarios=new ArrayList<>();

    public String getCodigoICAO() {
        return codigoICAO;
    }

    public void setCodigoICAO(String codigoICAO) {
        this.codigoICAO = codigoICAO;
    }

    public String getCodigoIATA() {
        return codigoIATA;
    }

    public void setCodigoIATA(String codigoIATA) {
        this.codigoIATA = codigoIATA;
    }

    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

    public List<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aerolinea) {
            Aerolinea aerolinea = (Aerolinea) obj;
            return aerolinea.getCodigoIATA().equals(this.getCodigoIATA());
        }
        return false;
    }

    //    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
}
