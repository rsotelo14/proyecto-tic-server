package uy.um.edu.server.business.entities.aeropuerto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import uy.um.edu.server.business.entities.aerolinea.Aerolinea;
import uy.um.edu.server.business.entities.vuelos.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aeropuertos")
public class Aeropuerto {
    @Id
    @GeneratedValue
    @GenericGenerator(name="aeropuerto_ids", strategy = "increment")
    private Long aeropuertoid;
    @Column(unique = true)
    private String nombre;
    private String ciudad;
    private String pais;
    @Column(unique = true)
    private String codigo;
    private String direccion;
    private String telefono;

    public Aeropuerto(String nombreAeropuerto, String cuidad, String pais, String codigoAeropuerto, String direccion, String telefono) {

        this.nombre = nombreAeropuerto;
        this.ciudad = cuidad;
        this.pais = pais;
        this.codigo = codigoAeropuerto;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return Objects.equals(aeropuertoid, that.aeropuertoid) && Objects.equals(nombre, that.nombre) && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aeropuertoid, nombre, codigo);
    }

    @ManyToMany  //Aca en teoria se hace el mapeo de la relacion N a N entre aerolinea y aeropuerto
    @JoinTable(
            name = "aerolinea_aeropuerto",
            joinColumns = @JoinColumn(name = "aeropuerto_id"),
            inverseJoinColumns = @JoinColumn(name = "aerolinea_id")
    )
    @JsonIgnore
    private List<Aerolinea> aerolineas=new ArrayList<>();
    @OneToMany(mappedBy = "aeropuerto")
    @JsonIgnore
    private List<PuertaAeropuerto> puertas=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "aeropuertoOrigen")
    private List<Vuelo> vuelosSalientes =new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "aeropuertoDestino")
    private List<Vuelo> vuelosEntrantes =new ArrayList<>();

    public Aeropuerto() {
    }
    @JsonIgnore
    @OneToMany(mappedBy = "aeropuerto", fetch = FetchType.EAGER)
    private List<UsuarioAeropuerto> usuarios=new ArrayList<>();

    public Long getAeropuertoid() {
        return aeropuertoid;
    }

    public void setAeropuertoid(Long aeropuertoid) {
        this.aeropuertoid = aeropuertoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Aerolinea> getAerolineas() {
        return aerolineas;
    }

    public void setAerolineas(List<Aerolinea> aerolineas) {
        this.aerolineas = aerolineas;
    }

    public List<PuertaAeropuerto> getPuertas() {
        return puertas;
    }

    public void setPuertas(List<PuertaAeropuerto> puertas) {
        this.puertas = puertas;
    }

    public List<Vuelo> getVuelosSalientes() {
        return vuelosSalientes;
    }

    public void setVuelosSalientes(List<Vuelo> vuelosSalientes) {
        this.vuelosSalientes = vuelosSalientes;
    }

    public List<Vuelo> getVuelosEntrantes() {
        return vuelosEntrantes;
    }

    public void setVuelosEntrantes(List<Vuelo> vuelosEntrantes) {
        this.vuelosEntrantes = vuelosEntrantes;
    }

    public List<UsuarioAeropuerto> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioAeropuerto> usuarios) {
        this.usuarios = usuarios;
    }

    public Aeropuerto(Long aeropuertoid, String nombre, String ciudad, String pais, String codigo, String direccion, String telefono) {
        this.aeropuertoid = aeropuertoid;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.codigo = codigo;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}

