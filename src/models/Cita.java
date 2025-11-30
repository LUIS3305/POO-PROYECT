package models;

import models.Paciente;
import models.Medico;
import models.Consultorio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private Consultorio consultorio;
    private LocalDateTime fechaHora;
    private Modalidad modalidad;
    private EstadoCita estado;
    private String motivoConsulta;
    private String observaciones;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaCancelacion;
    private String motivoCancelacion;

    public Cita(String id, Paciente paciente, Medico medico, Consultorio consultorio,
                LocalDateTime fechaHora, Modalidad modalidad, String motivoConsulta) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.consultorio = consultorio;
        this.fechaHora = fechaHora;
        this.modalidad = modalidad;
        this.motivoConsulta = motivoConsulta;
        this.estado = EstadoCita.PROGRAMADA;
        this.fechaRegistro = LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public Consultorio getConsultorio() { return consultorio; }
    public void setConsultorio(Consultorio consultorio) { this.consultorio = consultorio; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Modalidad getModalidad() { return modalidad; }
    public void setModalidad(Modalidad modalidad) { this.modalidad = modalidad; }

    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getFechaCancelacion() { return fechaCancelacion; }
    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getMotivoCancelacion() { return motivoCancelacion; }
    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public void cancelar(String motivo) {
        this.estado = EstadoCita.CANCELADA;
        this.motivoCancelacion = motivo;
        this.fechaCancelacion = LocalDateTime.now();
    }

    public void confirmar() {
        if (estado == EstadoCita.PROGRAMADA) {
            this.estado = EstadoCita.CONFIRMADA;
        }
    }

    public void iniciarAtencion() {
        if (estado == EstadoCita.CONFIRMADA || estado == EstadoCita.PROGRAMADA) {
            this.estado = EstadoCita.EN_SALA;
        }
    }

    public void finalizarAtencion() {
        if (estado == EstadoCita.EN_SALA) {
            this.estado = EstadoCita.ATENDIDA;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Cita #" + id + " - " + paciente.getNombreCompleto() +
               " con " + medico.getNombreCompleto() +
               " el " + fechaHora.format(formatter) + " - Estado: " + estado;
    }
}
