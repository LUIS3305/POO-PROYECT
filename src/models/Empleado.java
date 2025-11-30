package models;

import java.time.LocalDate;

public abstract class Empleado extends Persona {
    protected Rol rol;
    protected boolean activo;

    public Empleado(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                    String sexo, String telefono, String email, String direccion, Rol rol) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion);
        this.rol = rol;
        this.activo = true;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return rol + " - " + getNombreCompleto();
    }
}
