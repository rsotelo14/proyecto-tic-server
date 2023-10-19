package uy.um.edu.server.business.entities.pasajeros;


import jakarta.persistence.Entity;
import uy.um.edu.server.business.entities.Usuario;


@Entity

public class Pasajero extends Usuario {
    public Pasajero(){

    }
    public Pasajero(String nombre, String apellido, String email, String password) {
        super(nombre, apellido, email, password);
    }

}
