package controllers;

import models.*;
import utils.GeneradorID;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionConsultas {

    public GestionConsultas() {
    }

    public Consulta iniciarConsulta(Cita cita) {
        if (cita == null) return null;

        String id = GeneradorID.generarIDConsulta();
        Consulta consulta = new Consulta(id, cita);
        Sistema.consultas.add(consulta);

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
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            if (consulta.getId().equals(id)) {
                return consulta;
            }
        }
        return null;
    }

    public List<Consulta> listarPorPaciente(Paciente paciente) {
        List<Consulta> consultasPaciente = new ArrayList<>();
        
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            if (consulta.getCita().getPaciente().equals(paciente)) {
                consultasPaciente.add(consulta);
            }
        }
        
        return consultasPaciente;
    }

    public List<Consulta> listarPorFecha(LocalDate fecha) {
        List<Consulta> consultasFecha = new ArrayList<>();
        
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            if (consulta.getFechaHoraInicio().toLocalDate().equals(fecha)) {
                consultasFecha.add(consulta);
            }
        }
        
        return consultasFecha;
    }

    public List<Consulta> listarAbiertas() {
        List<Consulta> consultasAbiertas = new ArrayList<>();
        
        for (int i = 0; i < Sistema.consultas.size(); i++) {
            Consulta consulta = Sistema.consultas.get(i);
            if (!consulta.isCerrada()) {
                consultasAbiertas.add(consulta);
            }
        }
        
        return consultasAbiertas;
    }
}
