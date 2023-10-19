package uy.um.edu.server.business.exceptions;

public class UsuarioYaExiste extends Exception {
    public UsuarioYaExiste(String message) {
        super(message);
    }

    public UsuarioYaExiste() {
    }
}
