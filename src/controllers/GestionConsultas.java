package controllers;

import models.*;
import utils.GeneradorID;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionConsultas {

    public GestionConsultas() {
        // Constructor vacío - Sistema es estático
    }

    public Consulta iniciarConsulta(Cita cita) {
        if (cita == null) return null;

        String id = GeneradorID.generarIDConsulta();
        Consulta consulta = new Consulta(id, cita);
        Sistema.consultas.add(consulta);

        // Agregar a historia clínica
        HistoriaClinica historia = Sistema.buscarHistoriaClinicaPorPaciente(cita.getPaciente());
        if (historia != null) {
            historia.agregarConsulta(consulta);
        }

        return consulta;
    }

    public boolean cerrarConsulta(String idConsulta, String resumenClinico) {
        Consulta consulta = buscarPorID(idConsulta);
        if (consulta == null || consulta.isCerrada()) return false;

        consulta.cerrarConsulta(resumenClinico);
        return true;
    }

    public boolean agregarDiagnostico(String idConsulta, Diagnostico diagnostico) {
        Consulta consulta = buscarPorID(idConsulta);
        if (consulta == null || consulta.isCerrada()) return false;

        consulta.agregarDiagnostico(diagnostico);
        return true;
    }

    public Receta crearReceta(String idConsulta) {
        Consulta consulta = buscarPorID(idConsulta);
        if (consulta == null || consulta.isCerrada()) return null;

        String idReceta = GeneradorID.generarIDReceta();
        Receta receta = new Receta(idReceta);
        consulta.setReceta(receta);
        return receta;
    }

    public Consulta buscarPorID(String id) {
        // Recorrer todas las consultas una por una
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            // Si el ID coincide, devolver esta consulta
            if (consulta.getId().equals(id)) {
                return consulta;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public List<Consulta> listarPorPaciente(Paciente paciente) {
        // Crear lista vacía para guardar consultas del paciente
        List<Consulta> consultasPaciente = new ArrayList<>();
        
        // Recorrer todas las consultas
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            // Si la consulta es del paciente, agregarla a la lista
            if (consulta.getCita().getPaciente().equals(paciente)) {
                consultasPaciente.add(consulta);
            }
        }
        
        // Devolver la lista de consultas del paciente
        return consultasPaciente;
    }

    public List<Consulta> listarPorFecha(LocalDate fecha) {
        // Crear lista vacía para guardar consultas de la fecha
        List<Consulta> consultasFecha = new ArrayList<>();
        
        // Recorrer todas las consultas
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            // Si la fecha de la consulta coincide, agregarla a la lista
            if (consulta.getFechaHoraInicio().toLocalDate().equals(fecha)) {
                consultasFecha.add(consulta);
            }
        }
        
        // Devolver la lista de consultas de esa fecha
        return consultasFecha;
    }

    public List<Consulta> listarAbiertas() {
        // Crear lista vacía para guardar consultas abiertas
        List<Consulta> consultasAbiertas = new ArrayList<>();
        
        // Recorrer todas las consultas
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            // Si la consulta NO está cerrada, agregarla a la lista
            if (!consulta.isCerrada()) {
                consultasAbiertas.add(consulta);
            }
        }
        
        // Devolver la lista de consultas abiertas
        return consultasAbiertas;
    }
}
