package controllers;

import models.Paciente;
import models.HistoriaClinica;
import models.Sistema;
import utils.Validador;
import utils.GeneradorID;
import java.util.ArrayList;
import java.util.List;

public class GestionPacientes {

    public GestionPacientes() {
    }

    public boolean registrarPaciente(Paciente paciente) {
        if (paciente == null) return false;
        if (!Validador.validarDNI(paciente.getDni())) return false;
        if (buscarPorDNI(paciente.getDni()) != null) return false;

        Sistema.pacientes.add(paciente);

        String numeroHC = GeneradorID.generarNumeroHistoriaClinica();
        HistoriaClinica historiaClinica = new HistoriaClinica(numeroHC, paciente);
        Sistema.historiasClinicas.add(historiaClinica);

        return true;
    }

    public boolean modificarPaciente(String dni, Paciente pacienteModificado) {
        Paciente paciente = buscarPorDNI(dni);
        if (paciente == null) return false;

        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            if (Sistema.pacientes.get(i).equals(paciente)) {
                Sistema.pacientes.set(i, pacienteModificado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPaciente(String dni) {
        Paciente paciente = buscarPorDNI(dni);
        if (paciente == null) return false;

        paciente.setActivo(false);
        return true;
    }

    public Paciente buscarPorDNI(String dni) {
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            if (paciente.getDni().equals(dni)) {
                return paciente;
            }
        }
        return null;
    }

    public Paciente buscarPorHistoriaClinica(String numeroHC) {
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            if (numeroHC.equals(paciente.getNumeroHistoriaClinica())) {
                return paciente;
            }
        }
        return null;
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(Sistema.pacientes);
    }

    public List<Paciente> listarActivos() {
        List<Paciente> pacientesActivos = new ArrayList<>();
        
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            if (paciente.isActivo()) {
                pacientesActivos.add(paciente);
            }
        }
        
        return pacientesActivos;
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        String nombreBusqueda = nombre.toLowerCase();
        List<Paciente> pacientesEncontrados = new ArrayList<>();
        
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            if (paciente.getNombreCompleto().toLowerCase().contains(nombreBusqueda)) {
                pacientesEncontrados.add(paciente);
            }
        }
        
        return pacientesEncontrados;
    }

    public HistoriaClinica obtenerHistoriaClinica(Paciente paciente) {
        return Sistema.buscarHistoriaClinicaPorPaciente(paciente);
    }
}
