package uy.um.edu.server.business.entities.aerolinea;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("empleadoAerolinea")
public class EmpleadoAerolinea extends UsuarioAerolinea{
    private String codigoEmpleado;

    public EmpleadoAerolinea(String nombre, String apellido, String email, String password, Aerolinea aerolinea, String codigoEmpleado) {
        super(nombre, apellido, email, password, aerolinea);
        this.codigoEmpleado = codigoEmpleado;
    }

    public EmpleadoAerolinea() {
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }
}
