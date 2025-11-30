package models;

import models.Medico;
import java.time.LocalTime;

public class HorarioAtencion {
    private Medico medico;
    private Consultorio consultorio;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int duracionConsultaMinutos;

    public HorarioAtencion(Medico medico, Consultorio consultorio, String diaSemana,
                          LocalTime horaInicio, LocalTime horaFin, int duracionConsultaMinutos) {
        this.medico = medico;
        this.consultorio = consultorio;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.duracionConsultaMinutos = duracionConsultaMinutos;
    }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public Consultorio getConsultorio() { return consultorio; }
    public void setConsultorio(Consultorio consultorio) { this.consultorio = consultorio; }

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public int getDuracionConsultaMinutos() { return duracionConsultaMinutos; }
    public void setDuracionConsultaMinutos(int duracionConsultaMinutos) {
        this.duracionConsultaMinutos = duracionConsultaMinutos;
    }

    public int getCantidadCitasDisponibles() {
        long minutosTotales = java.time.Duration.between(horaInicio, horaFin).toMinutes();
        return (int) (minutosTotales / duracionConsultaMinutos);
    }

    @Override
    public String toString() {
        return diaSemana + " " + horaInicio + "-" + horaFin + " - " +
               medico.getNombreCompleto() + " en " + consultorio.getCodigo();
    }
}
