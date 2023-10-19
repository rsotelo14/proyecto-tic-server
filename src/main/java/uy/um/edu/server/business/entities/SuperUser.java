package uy.um.edu.server.business.entities;


import jakarta.persistence.Entity;

@Entity

public class SuperUser extends Usuario{

    public SuperUser() {
    }

    public SuperUser(String nombre, String apellido, String correo, String contrasena) {
        super(nombre, apellido, correo, contrasena);
    }
}
