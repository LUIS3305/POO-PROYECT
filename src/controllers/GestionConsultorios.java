package controllers;

import models.*;
import java.util.ArrayList;
import java.util.List;

public class GestionConsultorios {

    public GestionConsultorios() {
        // Constructor vacío - Sistema es estático
    }

    public boolean agregarConsultorio(Consultorio consultorio) {
        if (consultorio == null) return false;
        if (buscarPorCodigo(consultorio.getCodigo()) != null) return false;

        Sistema.consultorios.add(consultorio);
        return true;
    }

    public boolean modificarConsultorio(String codigo, Consultorio consultorioModificado) {
        // Buscar el consultorio por código
        Consultorio consultorio = buscarPorCodigo(codigo);
        if (consultorio == null) return false;

        // Buscar el índice del consultorio en la lista
        for (int i = 0; i < Sistema.consultorios.size(); i++) {
            if (Sistema.consultorios.get(i).equals(consultorio)) {
                // Reemplazar el consultorio en esa posición
                Sistema.consultorios.set(i, consultorioModificado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarConsultorio(String codigo) {
        Consultorio consultorio = buscarPorCodigo(codigo);
        if (consultorio == null) return false;

        return Sistema.consultorios.remove(consultorio);
    }

    public boolean cambiarEstado(String codigo, EstadoConsultorio nuevoEstado) {
        Consultorio consultorio = buscarPorCodigo(codigo);
        if (consultorio == null) return false;

        consultorio.setEstado(nuevoEstado);
        return true;
    }

    public Consultorio buscarPorCodigo(String codigo) {
        return Sistema.buscarConsultorioPorCodigo(codigo);
    }

    public List<Consultorio> listarTodos() {
        return new ArrayList<>(Sistema.consultorios);
    }

    public List<Consultorio> listarDisponibles() {
        // Crear lista vacía para guardar consultorios disponibles
        List<Consultorio> consultoriosDisponibles = new ArrayList<>();
        
        // Recorrer todos los consultorios
        for (int i = 0; i < Sistema.consultorios.size(); i++) {
            Consultorio consultorio = Sistema.consultorios.get(i);
            // Si el consultorio está disponible, agregarlo a la lista
            if (consultorio.estaDisponible()) {
                consultoriosDisponibles.add(consultorio);
            }
        }
        
        // Devolver la lista de consultorios disponibles
        return consultoriosDisponibles;
    }

    public List<Consultorio> listarPorEspecialidad(String especialidad) {
        // Crear lista vacía para guardar consultorios de la especialidad
        List<Consultorio> consultoriosEspecialidad = new ArrayList<>();
        
        // Recorrer todos los consultorios
        for (int i = 0; i < Sistema.consultorios.size(); i++) {
            Consultorio consultorio = Sistema.consultorios.get(i);
            // Si la especialidad coincide (sin importar mayúsculas), agregarlo
            if (consultorio.getEspecialidad().equalsIgnoreCase(especialidad)) {
                consultoriosEspecialidad.add(consultorio);
            }
        }
        
        // Devolver la lista de consultorios de esa especialidad
        return consultoriosEspecialidad;
    }
}
