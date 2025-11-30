package models;

import java.time.LocalDate;

public class Recepcionista extends Empleado {
    private String turno;

    public Recepcionista(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                        String sexo, String telefono, String email, String direccion,
                        String turno) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion, Rol.RECEPCIONISTA);
        this.turno = turno;
    }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return "Recepcionista - " + getNombreCompleto() + " (Turno: " + turno + ")";
    }
}
