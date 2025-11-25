package models;

import interfaces.Facturable;

public class ServicioConsulta implements Facturable {
    private String especialidad;
    private String nombreMedico;
    private double tarifa;

    public ServicioConsulta(String especialidad, String nombreMedico, double tarifa) {
        this.especialidad = especialidad;
        this.nombreMedico = nombreMedico;
        this.tarifa = tarifa;
    }

    @Override
    public double obtenerMonto() {
        return tarifa;
    }

    @Override
    public String obtenerDescripcion() {
        return "Consulta " + especialidad + " - " + nombreMedico;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getNombreMedico() { return nombreMedico; }
    public void setNombreMedico(String nombreMedico) { this.nombreMedico = nombreMedico; }

    public double getTarifa() { return tarifa; }
    public void setTarifa(double tarifa) { this.tarifa = tarifa; }
}
