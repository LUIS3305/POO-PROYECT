package models;

import models.Medico;
import models.Paciente;
import interfaces.Facturable;
import java.time.LocalDateTime;

public abstract class Orden implements Facturable {
    protected String id;
    protected Paciente paciente;
    protected Medico medicoSolicitante;
    protected LocalDateTime fechaSolicitud;
    protected EstadoOrden estado;
    protected String descripcion;
    protected String indicacionesClinicas;
    protected String resultado;
    protected String archivoResultado;
    protected LocalDateTime fechaResultado;
    protected String ejecutadoPor;
    protected double costo;

    public Orden(String id, Paciente paciente, Medico medicoSolicitante,
                String descripcion, double costo) {
        this.id = id;
        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.descripcion = descripcion;
        this.costo = costo;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoOrden.PENDIENTE;
    }

    public void iniciarProceso(String ejecutadoPor) {
        this.estado = EstadoOrden.EN_PROCESO;
        this.ejecutadoPor = ejecutadoPor;
    }

    public void registrarResultado(String resultado, String archivoResultado) {
        this.resultado = resultado;
        this.archivoResultado = archivoResultado;
        this.fechaResultado = LocalDateTime.now();
        this.estado = EstadoOrden.CON_RESULTADO;
    }

    public void marcarComoEntregado() {
        if (estado == EstadoOrden.CON_RESULTADO) {
            this.estado = EstadoOrden.ENTREGADO;
        }
    }

    @Override
    public double obtenerMonto() {
        return costo;
    }

    @Override
    public String obtenerDescripcion() {
        return descripcion;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedicoSolicitante() { return medicoSolicitante; }
    public void setMedicoSolicitante(Medico medicoSolicitante) {
        this.medicoSolicitante = medicoSolicitante;
    }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoOrden getEstado() { return estado; }
    public void setEstado(EstadoOrden estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getIndicacionesClinicas() { return indicacionesClinicas; }
    public void setIndicacionesClinicas(String indicacionesClinicas) {
        this.indicacionesClinicas = indicacionesClinicas;
    }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getArchivoResultado() { return archivoResultado; }
    public void setArchivoResultado(String archivoResultado) {
        this.archivoResultado = archivoResultado;
    }

    public LocalDateTime getFechaResultado() { return fechaResultado; }
    public void setFechaResultado(LocalDateTime fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public String getEjecutadoPor() { return ejecutadoPor; }
    public void setEjecutadoPor(String ejecutadoPor) { this.ejecutadoPor = ejecutadoPor; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public abstract String getTipoOrden();

    @Override
    public String toString() {
        return getTipoOrden() + " #" + id + " - " + descripcion + " - Estado: " + estado;
    }
}
