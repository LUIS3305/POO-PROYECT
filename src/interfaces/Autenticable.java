package interfaces;

import models.Rol;

public interface Autenticable {
    boolean autenticar(String usuario, String password);
    Rol getRol();
}
