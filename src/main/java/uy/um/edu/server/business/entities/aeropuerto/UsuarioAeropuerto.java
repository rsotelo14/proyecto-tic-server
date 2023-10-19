package uy.um.edu.server.business.entities.aeropuerto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import uy.um.edu.server.business.entities.Usuario;

@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminAeropuerto.class, name = "adminAeropuerto"),
        @JsonSubTypes.Type(value = MaleteroAeropuerto.class, name = "maleteroAeropuerto")
})
@Entity
@DiscriminatorValue("usuarioAeropuerto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class UsuarioAeropuerto extends Usuario {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aeropuerto_id")
    @JsonManagedReference
    private Aeropuerto aeropuerto;

    public UsuarioAeropuerto(String nombre, String apellido, String email, String password, Aeropuerto aeropuerto) {
        super(nombre, apellido, email, password);
        this.aeropuerto = aeropuerto;
    }

    public UsuarioAeropuerto() {
    }
    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}
