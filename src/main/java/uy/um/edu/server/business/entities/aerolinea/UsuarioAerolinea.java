package uy.um.edu.server.business.entities.aerolinea;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import uy.um.edu.server.business.entities.Usuario;


@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminAerolinea.class),
        @JsonSubTypes.Type(value = EmpleadoAerolinea.class)
})
@Entity
@DiscriminatorValue("usuarioAerolinea")
@Inheritance(strategy = InheritanceType.JOINED )
public class UsuarioAerolinea extends Usuario {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;

    public UsuarioAerolinea(String nombre, String apellido, String email, String password, Aerolinea aerolinea) {
        super(nombre, apellido, email, password);
        this.aerolinea = aerolinea;
    }

    public UsuarioAerolinea() {
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
}
