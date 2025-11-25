package controllers;

import models.*;
import utils.GeneradorID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionCitas {

    public GestionCitas() {
        // Constructor vacío - Sistema es estático
    }

    public Cita crearCita(Paciente paciente, Medico medico, Consultorio consultorio,
                         LocalDateTime fechaHora, Modalidad modalidad, String motivoConsulta) {
        // Validar disponibilidad
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

        // Validar nueva disponibilidad
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
        // Recorrer todas las citas para verificar disponibilidad del médico
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si el médico tiene una cita a la misma hora y no está cancelada
            if (cita.getMedico().equals(medico) &&
                cita.getFechaHora().equals(fechaHora) &&
                cita.getEstado() != EstadoCita.CANCELADA) {
                return false; // No está disponible
            }
        }
        return true; // Está disponible
    }

    private boolean validarDisponibilidadConsultorio(Consultorio consultorio, LocalDateTime fechaHora) {
        if (consultorio == null) return false;
        if (!consultorio.estaDisponible()) return false;

        // Recorrer todas las citas para verificar disponibilidad del consultorio
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si el consultorio tiene una cita a la misma hora y no está cancelada
            if (cita.getConsultorio() != null &&
                cita.getConsultorio().equals(consultorio) &&
                cita.getFechaHora().equals(fechaHora) &&
                cita.getEstado() != EstadoCita.CANCELADA) {
                return false; // No está disponible
            }
        }
        return true; // Está disponible
    }

    public Cita buscarPorID(String id) {
        // Recorrer todas las citas una por una
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si el ID coincide, devolver esta cita
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public List<Cita> listarPorPaciente(Paciente paciente) {
        // Crear lista vacía para guardar citas del paciente
        List<Cita> citasPaciente = new ArrayList<>();
        
        // Recorrer todas las citas
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si la cita es del paciente, agregarla a la lista
            if (cita.getPaciente().equals(paciente)) {
                citasPaciente.add(cita);
            }
        }
        
        // Devolver la lista de citas del paciente
        return citasPaciente;
    }

    public List<Cita> listarPorMedico(Medico medico) {
        // Crear lista vacía para guardar citas del médico
        List<Cita> citasMedico = new ArrayList<>();
        
        // Recorrer todas las citas
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si la cita es del médico, agregarla a la lista
            if (cita.getMedico().equals(medico)) {
                citasMedico.add(cita);
            }
        }
        
        // Devolver la lista de citas del médico
        return citasMedico;
    }

    public List<Cita> listarPorFecha(LocalDate fecha) {
        // Crear lista vacía para guardar citas de la fecha
        List<Cita> citasFecha = new ArrayList<>();
        
        // Recorrer todas las citas
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si la fecha de la cita coincide, agregarla a la lista
            if (cita.getFechaHora().toLocalDate().equals(fecha)) {
                citasFecha.add(cita);
            }
        }
        
        // Devolver la lista de citas de esa fecha
        return citasFecha;
    }

    public List<Cita> listarPorMedicoYFecha(Medico medico, LocalDate fecha) {
        // Crear lista vacía para guardar citas del médico en esa fecha
        List<Cita> citasMedicoFecha = new ArrayList<>();
        
        // Recorrer todas las citas
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si la cita es del médico Y en la fecha especificada, agregarla
            if (cita.getMedico().equals(medico) && cita.getFechaHora().toLocalDate().equals(fecha)) {
                citasMedicoFecha.add(cita);
            }
        }
        
        // Devolver la lista de citas del médico en esa fecha
        return citasMedicoFecha;
    }

    public List<Cita> listarPorEstado(EstadoCita estado) {
        // Crear lista vacía para guardar citas con ese estado
        List<Cita> citasEstado = new ArrayList<>();
        
        // Recorrer todas las citas
        for (int i = 0; i < Sistema.citas.size(); i++) {
            Cita cita = Sistema.citas.get(i);
            // Si el estado de la cita coincide, agregarla a la lista
            if (cita.getEstado() == estado) {
                citasEstado.add(cita);
            }
        }
        
        // Devolver la lista de citas con ese estado
        return citasEstado;
    }

    public List<Cita> listarCitasDelDia() {
        return listarPorFecha(LocalDate.now());
    }
}
