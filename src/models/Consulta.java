package models;

import models.Cita;
import models.Orden;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Consulta {
    private String id;
    private Cita cita;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String motivoConsulta;
    private String anamnesis;
    private String antecedentes;
    private SignosVitales signosVitales;
    private String examenFisico;
    private List<Diagnostico> diagnosticos;
    private String plan;
    private Receta receta;
    private List<Orden> ordenes;
    private String resumenClinico;
    private boolean cerrada;

    public Consulta(String id, Cita cita) {
        this.id = id;
        this.cita = cita;
        this.fechaHoraInicio = LocalDateTime.now();
        this.diagnosticos = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.cerrada = false;
    }

    public void cerrarConsulta(String resumenClinico) {
        this.fechaHoraFin = LocalDateTime.now();
        this.resumenClinico = resumenClinico;
        this.cerrada = true;
        if (cita != null) {
            cita.finalizarAtencion();
        }
    }

    public void agregarDiagnostico(Diagnostico diagnostico) {
        diagnosticos.add(diagnostico);
    }

    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }

    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getAnamnesis() { return anamnesis; }
    public void setAnamnesis(String anamnesis) { this.anamnesis = anamnesis; }

    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }

    public SignosVitales getSignosVitales() { return signosVitales; }
    public void setSignosVitales(SignosVitales signosVitales) { this.signosVitales = signosVitales; }

    public String getExamenFisico() { return examenFisico; }
    public void setExamenFisico(String examenFisico) { this.examenFisico = examenFisico; }

    public List<Diagnostico> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<Diagnostico> diagnosticos) { this.diagnosticos = diagnosticos; }

    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }

    public List<Orden> getOrdenes() { return ordenes; }
    public void setOrdenes(List<Orden> ordenes) { this.ordenes = ordenes; }

    public String getResumenClinico() { return resumenClinico; }
    public void setResumenClinico(String resumenClinico) { this.resumenClinico = resumenClinico; }

    public boolean isCerrada() { return cerrada; }
    public void setCerrada(boolean cerrada) { this.cerrada = cerrada; }

    @Override
    public String toString() {
        return "Consulta #" + id + " - " + cita.getPaciente().getNombreCompleto() +
               " - " + fechaHoraInicio.toLocalDate() + (cerrada ? " (Cerrada)" : " (Abierta)");
    }
}
