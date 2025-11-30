package models;

import models.Medico;
import models.Paciente;
import java.time.LocalDateTime;

public class OrdenImagen extends Orden {
    private String tipoImagen;
    private String areaAnatomica;
    private boolean requiereContraste;
    private LocalDateTime fechaRealizacion;

    public OrdenImagen(String id, Paciente paciente, Medico medicoSolicitante,
                      String descripcion, String tipoImagen, String areaAnatomica,
                      boolean requiereContraste, double costo) {
        super(id, paciente, medicoSolicitante, descripcion, costo);
        this.tipoImagen = tipoImagen;
        this.areaAnatomica = areaAnatomica;
        this.requiereContraste = requiereContraste;
    }

    public void registrarRealizacion(String ejecutadoPor) {
        this.fechaRealizacion = LocalDateTime.now();
        iniciarProceso(ejecutadoPor);
    }

    @Override
    public String getTipoOrden() {
        return "Orden de Imagen";
    }
    public String getTipoImagen() { return tipoImagen; }
    public void setTipoImagen(String tipoImagen) { this.tipoImagen = tipoImagen; }

    public String getAreaAnatomica() { return areaAnatomica; }
    public void setAreaAnatomica(String areaAnatomica) { this.areaAnatomica = areaAnatomica; }

    public boolean isRequiereContraste() { return requiereContraste; }
    public void setRequiereContraste(boolean requiereContraste) {
        this.requiereContraste = requiereContraste;
    }

    public LocalDateTime getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + tipoImagen + " de " + areaAnatomica +
               (requiereContraste ? " (con contraste)" : "");
    }
}
