package uy.um.edu.server.business.entities.pasajeros;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import uy.um.edu.server.business.entities.Usuario;
import uy.um.edu.server.business.entities.vuelos.Asientos;

import java.util.List;


@Entity
@Table(name = "pasajero")

public class Pasajero extends Usuario {

    public Pasajero(){

    }

    @OneToMany(mappedBy = "pasajero")
    private List<Asientos> asientos;

    public Pasajero(String nombre, String apellido, String email, String password) {
        super(nombre, apellido, email, password);
    }
}
