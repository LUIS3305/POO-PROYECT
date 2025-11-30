package models;

import models.Medico;
import models.Paciente;
import java.time.LocalDateTime;

public class OrdenLaboratorio extends Orden {
    private String tipoAnalisis;
    private boolean requiereAyuno;
    private LocalDateTime fechaTomaMuestra;
    private String numeroMuestra;

    public OrdenLaboratorio(String id, Paciente paciente, Medico medicoSolicitante,
                           String descripcion, String tipoAnalisis, boolean requiereAyuno, double costo) {
        super(id, paciente, medicoSolicitante, descripcion, costo);
        this.tipoAnalisis = tipoAnalisis;
        this.requiereAyuno = requiereAyuno;
    }

    public void registrarTomaMuestra(String numeroMuestra, String ejecutadoPor) {
        this.numeroMuestra = numeroMuestra;
        this.fechaTomaMuestra = LocalDateTime.now();
        iniciarProceso(ejecutadoPor);
    }

    @Override
    public String getTipoOrden() {
        return "Orden de Laboratorio";
    }
    public String getTipoAnalisis() { return tipoAnalisis; }
    public void setTipoAnalisis(String tipoAnalisis) { this.tipoAnalisis = tipoAnalisis; }

    public boolean isRequiereAyuno() { return requiereAyuno; }
    public void setRequiereAyuno(boolean requiereAyuno) { this.requiereAyuno = requiereAyuno; }

    public LocalDateTime getFechaTomaMuestra() { return fechaTomaMuestra; }
    public void setFechaTomaMuestra(LocalDateTime fechaTomaMuestra) {
        this.fechaTomaMuestra = fechaTomaMuestra;
    }

    public String getNumeroMuestra() { return numeroMuestra; }
    public void setNumeroMuestra(String numeroMuestra) { this.numeroMuestra = numeroMuestra; }

    @Override
    public String toString() {
        return super.toString() + " (" + tipoAnalisis + ")" +
               (requiereAyuno ? " - Requiere ayuno" : "");
    }
}
