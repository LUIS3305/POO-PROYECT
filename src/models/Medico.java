package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Empleado {
    private String especialidad;
    private String numeroColegioMedico;
    private List<String> diasDisponibles;

    public Medico(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                  String sexo, String telefono, String email, String direccion,
                  String especialidad, String numeroColegioMedico) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion, Rol.MEDICO);
        this.especialidad = especialidad;
        this.numeroColegioMedico = numeroColegioMedico;
        this.diasDisponibles = new ArrayList<>();
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getNumeroColegioMedico() { return numeroColegioMedico; }
    public void setNumeroColegioMedico(String numeroColegioMedico) {
        this.numeroColegioMedico = numeroColegioMedico;
    }

    public List<String> getDiasDisponibles() { return diasDisponibles; }
    public void setDiasDisponibles(List<String> diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    @Override
    public String toString() {
        return "Dr. " + getNombreCompleto() + " - " + especialidad;
    }
}
