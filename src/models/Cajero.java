package models;

import java.time.LocalDate;

public class Cajero extends Empleado {
    private String numeroCaja;
    private String turno;

    public Cajero(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                  String sexo, String telefono, String email, String direccion,
                  String numeroCaja, String turno) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion, Rol.CAJERO);
        this.numeroCaja = numeroCaja;
        this.turno = turno;
    }

    public String getNumeroCaja() { return numeroCaja; }
    public void setNumeroCaja(String numeroCaja) { this.numeroCaja = numeroCaja; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return "Cajero(a) - " + getNombreCompleto() + " (Caja: " + numeroCaja + ")";
    }
}
