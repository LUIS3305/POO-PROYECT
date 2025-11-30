package models;

import models.Medico;
import models.Paciente;
import java.time.LocalDateTime;

public class OrdenProcedimiento extends Orden {
    private String tipoProcedimiento;
    private String frecuencia;
    private int cantidadSesiones;
    private int sesionesRealizadas;
    private LocalDateTime proximaSesion;

    public OrdenProcedimiento(String id, Paciente paciente, Medico medicoSolicitante,
                             String descripcion, String tipoProcedimiento,
                             String frecuencia, int cantidadSesiones, double costo) {
        super(id, paciente, medicoSolicitante, descripcion, costo);
        this.tipoProcedimiento = tipoProcedimiento;
        this.frecuencia = frecuencia;
        this.cantidadSesiones = cantidadSesiones;
        this.sesionesRealizadas = 0;
    }

    public void registrarSesion(String ejecutadoPor, String observaciones) {
        if (sesionesRealizadas == 0) {
            iniciarProceso(ejecutadoPor);
        }
        sesionesRealizadas++;

        if (resultado == null) {
            resultado = "Sesión " + sesionesRealizadas + ": " + observaciones + "\n";
        } else {
            resultado += "Sesión " + sesionesRealizadas + ": " + observaciones + "\n";
        }

        if (sesionesRealizadas >= cantidadSesiones) {
            estado = EstadoOrden.CON_RESULTADO;
        }
    }

    @Override
    public String getTipoOrden() {
        return "Orden de Procedimiento";
    }
    public String getTipoProcedimiento() { return tipoProcedimiento; }
    public void setTipoProcedimiento(String tipoProcedimiento) {
        this.tipoProcedimiento = tipoProcedimiento;
    }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public int getCantidadSesiones() { return cantidadSesiones; }
    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public int getSesionesRealizadas() { return sesionesRealizadas; }
    public void setSesionesRealizadas(int sesionesRealizadas) {
        this.sesionesRealizadas = sesionesRealizadas;
    }

    public LocalDateTime getProximaSesion() { return proximaSesion; }
    public void setProximaSesion(LocalDateTime proximaSesion) {
        this.proximaSesion = proximaSesion;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + tipoProcedimiento +
               " (" + sesionesRealizadas + "/" + cantidadSesiones + " sesiones)";
    }
}
