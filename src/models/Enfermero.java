package models;

import java.time.LocalDate;

public class Enfermero extends Empleado {
    private String areaTrabajo;
    private String turno;

    public Enfermero(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                    String sexo, String telefono, String email, String direccion,
                    String areaTrabajo, String turno) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion, Rol.ENFERMERO);
        this.areaTrabajo = areaTrabajo;
        this.turno = turno;
    }

    public String getAreaTrabajo() { return areaTrabajo; }
    public void setAreaTrabajo(String areaTrabajo) { this.areaTrabajo = areaTrabajo; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return "Enfermero(a) - " + getNombreCompleto() + " (Área: " + areaTrabajo + ")";
    }
}
