package controllers;

import models.*;
import utils.GeneradorID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionCitas {

    public GestionCitas() {
    }

    public Cita crearCita(Paciente paciente, Medico medico, Consultorio consultorio,
                         LocalDateTime fechaHora, Modalidad modalidad, String motivoConsulta) {
        if (!validarDisponibilidadMedico(medico, fechaHora)) {
            return null;
        }
        if (modalidad == Modalidad.PRESENCIAL && !validarDisponibilidadConsultorio(consultorio, fechaHora)) {
            return null;
        }

        String id = GeneradorID.generarIDCita();
        Cita cita = new Cita(id, paciente, medico, consultorio, fechaHora, modalidad, motivoConsulta);
        Sistema.citas.add(cita);
        return cita;
    }

    public boolean modificarCita(String idCita, LocalDateTime nuevaFechaHora,
                                Consultorio nuevoConsultorio) {
        Cita cita = buscarPorID(idCita);
        if (cita == null || cita.getEstado() == EstadoCita.CANCELADA) return false;

        if (!validarDisponibilidadMedico(cita.getMedico(), nuevaFechaHora)) {
            return false;
        }
        if (cita.getModalidad() == Modalidad.PRESENCIAL &&
            !validarDisponibilidadConsultorio(nuevoConsultorio, nuevaFechaHora)) {
            return false;
        }

        cita.setFechaHora(nuevaFechaHora);
        cita.setConsultorio(nuevoConsultorio);
        return true;
    }

    public boolean cancelarCita(String idCita, String motivo) {
        Cita cita = buscarPorID(idCita);
        if (cita == null) return false;

        cita.cancelar(motivo);
        return true;
    }

    public boolean confirmarCita(String idCita) {
        Cita cita = buscarPorID(idCita);
        if (cita == null) return false;

        cita.confirmar();
        return true;
    }

    public boolean iniciarAtencion(String idCita) {
        Cita cita = buscarPorID(idCita);
        if (cita == null) return false;

        cita.iniciarAtencion();
        return true;
    }

    private boolean validarDisponibilidadMedico(Medico medico, LocalDateTime fechaHora) {
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getMedico().equals(medico) &&
                cita.getFechaHora().equals(fechaHora) &&
                cita.getEstado() != EstadoCita.CANCELADA) {
                return false;
            }
        }
        return true;
    }

    private boolean validarDisponibilidadConsultorio(Consultorio consultorio, LocalDateTime fechaHora) {
        if (consultorio == null) return false;
        if (!consultorio.estaDisponible()) return false;

        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getConsultorio() != null &&
                cita.getConsultorio().equals(consultorio) &&
                cita.getFechaHora().equals(fechaHora) &&
                cita.getEstado() != EstadoCita.CANCELADA) {
                return false;
            }
        }
        return true;
    }

    public Cita buscarPorID(String id) {
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    public List<Cita> listarPorPaciente(Paciente paciente) {
        List<Cita> citasPaciente = new ArrayList<>();
        
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getPaciente().equals(paciente)) {
                citasPaciente.add(cita);
            }
        }
        
        return citasPaciente;
    }

    public List<Cita> listarPorMedico(Medico medico) {
        List<Cita> citasMedico = new ArrayList<>();
        
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getMedico().equals(medico)) {
                citasMedico.add(cita);
            }
        }
        
        return citasMedico;
    }

    public List<Cita> listarPorFecha(LocalDate fecha) {
        List<Cita> citasFecha = new ArrayList<>();
        
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getFechaHora().toLocalDate().equals(fecha)) {
                citasFecha.add(cita);
            }
        }
        
        return citasFecha;
    }

    public List<Cita> listarPorMedicoYFecha(Medico medico, LocalDate fecha) {
        List<Cita> citasMedicoFecha = new ArrayList<>();
        
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getMedico().equals(medico) && cita.getFechaHora().toLocalDate().equals(fecha)) {
                citasMedicoFecha.add(cita);
            }
        }
        
        return citasMedicoFecha;
    }

    public List<Cita> listarPorEstado(EstadoCita estado) {
        List<Cita> citasEstado = new ArrayList<>();
        
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            if (cita.getEstado() == estado) {
                citasEstado.add(cita);
            }
        }
        
        return citasEstado;
    }

    public List<Cita> listarCitasDelDia() {
        return listarPorFecha(LocalDate.now());
    }
}
