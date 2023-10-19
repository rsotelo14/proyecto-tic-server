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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "aerolineas")
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

//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
}
