package models;

import java.time.LocalDate;

public class Administrador extends Empleado {
    private String departamento;
    private String nivel;

    public Administrador(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                        String sexo, String telefono, String email, String direccion,
                        String departamento, String nivel) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion, Rol.ADMINISTRADOR);
        this.departamento = departamento;
        this.nivel = nivel;
    }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    @Override
    public String toString() {
        return "Administrador - " + getNombreCompleto() + " (" + nivel + ")";
    }
}
