package controllers;

import models.*;
import java.util.ArrayList;
import java.util.List;

public class GestionConsultorios {

    public GestionConsultorios() {
    }

    public boolean agregarConsultorio(Consultorio consultorio) {
        if (consultorio == null) return false;
        if (buscarPorCodigo(consultorio.getCodigo()) != null) return false;

        Sistema.consultorios.add(consultorio);
        return true;
    }

    public boolean modificarConsultorio(String codigo, Consultorio consultorioModificado) {
        Consultorio consultorio = buscarPorCodigo(codigo);
        if (consultorio == null) return false;

        for (int i = 0; i < Sistema.consultorios.size(); i++) {
            if (Sistema.consultorios.get(i).equals(consultorio)) {
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
        List<Consultorio> consultoriosDisponibles = new ArrayList<>();
        
        for (int i = 0; i < Sistema.consultorios.size(); i++) {
            Consultorio consultorio = Sistema.consultorios.get(i);
            if (consultorio.estaDisponible()) {
                consultoriosDisponibles.add(consultorio);
            }
        }
        
        return consultoriosDisponibles;
    }

    public List<Consultorio> listarPorEspecialidad(String especialidad) {
        List<Consultorio> consultoriosEspecialidad = new ArrayList<>();
        
        for (int i = 0; i < Sistema.consultorios.size(); i++) {
            Consultorio consultorio = Sistema.consultorios.get(i);
            if (consultorio.getEspecialidad().equalsIgnoreCase(especialidad)) {
                consultoriosEspecialidad.add(consultorio);
            }
        }
        
        return consultoriosEspecialidad;
    }
}
