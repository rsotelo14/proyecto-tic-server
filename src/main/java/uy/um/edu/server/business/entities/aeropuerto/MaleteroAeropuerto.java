package uy.um.edu.server.business.entities.aeropuerto;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("maleteroAeropuerto")
public class MaleteroAeropuerto extends UsuarioAeropuerto{

//Atributo para probar atributos especificos. A cambiar
    private String codigoMaletero;
    public MaleteroAeropuerto() {
    }

    public MaleteroAeropuerto(String nombre, String apellido, String email, String password, Aeropuerto aeropuerto, String codigoMaletero) {
        super(nombre, apellido, email, password, aeropuerto);
        this.codigoMaletero = codigoMaletero;
    }
    public MaleteroAeropuerto(String nombre, String apellido, String email, String password, Aeropuerto aeropuerto) {
        super(nombre, apellido, email, password, aeropuerto);
    }

    public String getCodigoMaletero() {
        return codigoMaletero;
    }

    public void setCodigoMaletero(String codigoMaletero) {
        this.codigoMaletero = codigoMaletero;
    }
}
