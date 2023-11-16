package uy.um.edu.server.business.entities.aerolinea;

public class PasaporteCodigoVuelo {
    private String pasaporte;
    private String codigoVuelo;
    private Integer cantidadValijas;
    public PasaporteCodigoVuelo() {
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public Integer getCantidadValijas() {
        return cantidadValijas;
    }

    public void setCantidadValijas(Integer cantidadValijas) {
        this.cantidadValijas = cantidadValijas;
    }
}
