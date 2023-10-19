package uy.um.edu.server.business.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import uy.um.edu.server.business.entities.aerolinea.UsuarioAerolinea;
import uy.um.edu.server.business.entities.aeropuerto.UsuarioAeropuerto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UsuarioAerolinea.class, name = "usuarioAerolinea"),
        @JsonSubTypes.Type(value = UsuarioAeropuerto.class, name = "usuarioAeropuerto"),
        @JsonSubTypes.Type(value = SuperUser.class, name = "superUser")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(generator="usuarios_ids")
    @GenericGenerator(name="usuarios_ids", strategy = "increment")
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true, nullable = false)
    private String correo;

    private String contrasena;
    @Column(unique = true, nullable = false)
    private String pasaporte;

    public Usuario() {
    }
    public Usuario(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = email;
        this.contrasena = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Getters y setters

}