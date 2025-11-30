package controllers;

import models.Empleado;
import models.Rol;
import models.Sistema;

public class GestionAutenticacion {

    public GestionAutenticacion() {
    }

    public Empleado autenticar(String usuario, String password) {
        models.Usuario usr = Sistema.login(usuario, password);
        if (usr != null && usr.getEmpleado() != null) {
            return usr.getEmpleado();
        }
        return null;
    }

    public void cerrarSesion() {
        Sistema.logout();
    }

    public Empleado getUsuarioActual() {
        return Sistema.usuarioActual;
    }

    public boolean tienePermiso(Rol rolRequerido) {
        Empleado usuario = Sistema.usuarioActual;
        if (usuario == null) return false;
        return usuario.getRol() == rolRequerido;
    }

    public boolean tienePermisos(Rol... rolesPermitidos) {
        Empleado usuario = Sistema.usuarioActual;
        if (usuario == null) return false;

        for (Rol rol : rolesPermitidos) {
            if (usuario.getRol() == rol) {
                return true;
            }
        }
        return false;
    }

    public boolean esAdministrador() {
        return tienePermiso(Rol.ADMINISTRADOR);
    }

    public boolean esMedico() {
        return tienePermiso(Rol.MEDICO);
    }

    public boolean esRecepcionista() {
        return tienePermiso(Rol.RECEPCIONISTA);
    }

    public boolean esEnfermero() {
        return tienePermiso(Rol.ENFERMERO);
    }

    public boolean esCajero() {
        return tienePermiso(Rol.CAJERO);
    }
}
