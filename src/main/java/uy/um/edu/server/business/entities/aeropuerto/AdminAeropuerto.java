package uy.um.edu.server.business.entities.aeropuerto;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("adminAeropuerto")
public class AdminAeropuerto extends UsuarioAeropuerto {


//Atributo para probar atributos especificos. A cambiar
    private String codigoAdmin;

    public String getCodigoAdmin() {
        return codigoAdmin;
    }

    public void setCodigoAdmin(String codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }

    public AdminAeropuerto() {
    }
    public AdminAeropuerto(String nombre, String apellido, String email, String password, Aeropuerto aeropuerto, String codigoMaletero) {
        super(nombre, apellido, email, password, aeropuerto);
        this.codigoAdmin = codigoMaletero;
    }

    public AdminAeropuerto(String nombre, String apellido, String email, String password,  Aeropuerto aeropuerto) {
        super(nombre, apellido, email, password, aeropuerto);
    }
}

