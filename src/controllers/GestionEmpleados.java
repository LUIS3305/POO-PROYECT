package controllers;

import models.*;
import utils.Validador;
import java.util.ArrayList;
import java.util.List;

public class GestionEmpleados {
    
    public GestionEmpleados() {
    }

    public boolean agregarEmpleado(Empleado empleado) {
        if (empleado == null) return false;
        if (!Validador.validarDNI(empleado.getDni())) return false;
        if (buscarPorDNI(empleado.getDni()) != null) return false;

        Sistema.empleados.add(empleado);
        return true;
    }
    
    public boolean agregarEmpleado(Empleado empleado, String username, String password) {
        if (empleado == null) return false;
        if (!Validador.validarDNI(empleado.getDni())) return false;
        if (buscarPorDNI(empleado.getDni()) != null) return false;

        Sistema.empleados.add(empleado);
        
        Usuario usuario = new Usuario(username, password, empleado.getRol().toString());
        usuario.setEmpleado(empleado);
        Sistema.usuarios.add(usuario);
        
        return true;
    }

    public boolean modificarEmpleado(String dni, Empleado empleadoModificado) {
        Empleado empleado = buscarPorDNI(dni);
        if (empleado == null) return false;

        for (int i = 0; i < Sistema.empleados.size(); i++) {
            if (Sistema.empleados.get(i).equals(empleado)) {
                Sistema.empleados.set(i, empleadoModificado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarEmpleado(String dni) {
        Empleado empleado = buscarPorDNI(dni);
        if (empleado == null) return false;

        empleado.setActivo(false);
        return true;
    }

    public Empleado buscarPorDNI(String dni) {
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado empleado = Sistema.empleados.get(i);
            if (empleado.getDni().equals(dni)) {
                return empleado;
            }
        }
        return null;
    }

    public List<Empleado> listarTodos() {
        return new ArrayList<>(Sistema.empleados);
    }

    public List<Empleado> listarActivos() {
        List<Empleado> empleadosActivos = new ArrayList<>();
        
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado empleado = Sistema.empleados.get(i);
            if (empleado.isActivo()) {
                empleadosActivos.add(empleado);
            }
        }
        
        return empleadosActivos;
    }

    public List<Empleado> listarPorRol(Rol rol) {
        List<Empleado> empleadosPorRol = new ArrayList<>();
        
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado empleado = Sistema.empleados.get(i);
            if (empleado.getRol() == rol) {
                empleadosPorRol.add(empleado);
            }
        }
        
        return empleadosPorRol;
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = new ArrayList<>();
        
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado emp = Sistema.empleados.get(i);
            if (emp instanceof Medico && emp.isActivo()) {
                medicos.add((Medico) emp);
            }
        }
        
        return medicos;
    }

    public List<Medico> listarMedicosPorEspecialidad(String especialidad) {
        List<Medico> todosMedicos = Sistema.obtenerMedicosPorEspecialidad(especialidad);
        List<Medico> medicosActivos = new ArrayList<>();
        
        for (int i = 0; i < todosMedicos.size(); i++) {
            Medico medico = todosMedicos.get(i);
            if (medico.isActivo()) {
                medicosActivos.add(medico);
            }
        }
        
        return medicosActivos;
    }
}
