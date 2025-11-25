package controllers;

import models.Sistema;
import models.Usuario;

public class AuthService {
    private static Usuario usuarioAutenticado;

    public static Usuario login(String username, String password) {
        Usuario u = Sistema.login(username, password);
        if (u != null) {
            usuarioAutenticado = u;
        }
        return u;
    }

    public static void logout() {
        usuarioAutenticado = null;
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}

