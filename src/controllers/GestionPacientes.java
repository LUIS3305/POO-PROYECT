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
        // Constructor vacío - Sistema es estático
    }

    public boolean registrarPaciente(Paciente paciente) {
        if (paciente == null) return false;
        if (!Validador.validarDNI(paciente.getDni())) return false;
        if (buscarPorDNI(paciente.getDni()) != null) return false; // DNI duplicado

        Sistema.pacientes.add(paciente);

        // Crear historia clínica automáticamente
        String numeroHC = GeneradorID.generarNumeroHistoriaClinica();
        HistoriaClinica historiaClinica = new HistoriaClinica(numeroHC, paciente);
        Sistema.historiasClinicas.add(historiaClinica);

        return true;
    }

    public boolean modificarPaciente(String dni, Paciente pacienteModificado) {
        // Buscar el paciente por DNI
        Paciente paciente = buscarPorDNI(dni);
        if (paciente == null) return false;

        // Buscar el índice del paciente en la lista
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            if (Sistema.pacientes.get(i).equals(paciente)) {
                // Reemplazar el paciente en esa posición
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
        // Recorrer todos los pacientes uno por uno
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            // Si el DNI coincide, devolver este paciente
            if (paciente.getDni().equals(dni)) {
                return paciente;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public Paciente buscarPorHistoriaClinica(String numeroHC) {
        // Recorrer todos los pacientes
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            // Si el número de historia clínica coincide, devolver este paciente
            if (numeroHC.equals(paciente.getNumeroHistoriaClinica())) {
                return paciente;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(Sistema.pacientes);
    }

    public List<Paciente> listarActivos() {
        // Crear lista vacía para guardar pacientes activos
        List<Paciente> pacientesActivos = new ArrayList<>();
        
        // Recorrer todos los pacientes
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            // Si el paciente está activo, agregarlo a la lista
            if (paciente.isActivo()) {
                pacientesActivos.add(paciente);
            }
        }
        
        // Devolver la lista de pacientes activos
        return pacientesActivos;
    }

    public List<Paciente> buscarPorNombre(String nombre) {
        // Convertir el nombre a minúsculas para búsqueda sin importar mayúsculas
        String nombreBusqueda = nombre.toLowerCase();
        
        // Crear lista vacía para guardar pacientes encontrados
        List<Paciente> pacientesEncontrados = new ArrayList<>();
        
        // Recorrer todos los pacientes
        for (int i = 0; i < Sistema.pacientes.size(); i++) {
            Paciente paciente = Sistema.pacientes.get(i);
            // Convertir nombre del paciente a minúsculas y verificar si contiene el texto buscado
            if (paciente.getNombreCompleto().toLowerCase().contains(nombreBusqueda)) {
                pacientesEncontrados.add(paciente);
            }
        }
        
        // Devolver la lista de pacientes encontrados
        return pacientesEncontrados;
    }

    public HistoriaClinica obtenerHistoriaClinica(Paciente paciente) {
        return Sistema.buscarHistoriaClinicaPorPaciente(paciente);
    }
}
