package controllers;

import models.*;
import utils.Validador;
import java.util.ArrayList;
import java.util.List;

public class GestionEmpleados {
    
    public GestionEmpleados() {
        // Constructor vacío - Sistema es estático
    }

    public boolean agregarEmpleado(Empleado empleado) {
        if (empleado == null) return false;
        if (!Validador.validarDNI(empleado.getDni())) return false;
        if (buscarPorDNI(empleado.getDni()) != null) return false; // DNI duplicado

        Sistema.empleados.add(empleado);
        return true;
    }
    
    public boolean agregarEmpleado(Empleado empleado, String username, String password) {
        if (empleado == null) return false;
        if (!Validador.validarDNI(empleado.getDni())) return false;
        if (buscarPorDNI(empleado.getDni()) != null) return false; // DNI duplicado

        Sistema.empleados.add(empleado);
        
        Usuario usuario = new Usuario(username, password, empleado.getRol().toString());
        usuario.setEmpleado(empleado);
        Sistema.usuarios.add(usuario);
        
        return true;
    }

    public boolean modificarEmpleado(String dni, Empleado empleadoModificado) {
        // Buscar el empleado por DNI
        Empleado empleado = buscarPorDNI(dni);
        if (empleado == null) return false;

        // Buscar el índice del empleado en la lista
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            if (Sistema.empleados.get(i).equals(empleado)) {
                // Reemplazar el empleado en esa posición
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
        // Recorrer todos los empleados uno por uno
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado empleado = Sistema.empleados.get(i);
            // Si el DNI coincide, devolver este empleado
            if (empleado.getDni().equals(dni)) {
                return empleado;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public List<Empleado> listarTodos() {
        return new ArrayList<>(Sistema.empleados);
    }

    public List<Empleado> listarActivos() {
        // Crear lista vacía para guardar empleados activos
        List<Empleado> empleadosActivos = new ArrayList<>();
        
        // Recorrer todos los empleados
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado empleado = Sistema.empleados.get(i);
            // Si el empleado está activo, agregarlo a la lista
            if (empleado.isActivo()) {
                empleadosActivos.add(empleado);
            }
        }
        
        // Devolver la lista de empleados activos
        return empleadosActivos;
    }

    public List<Empleado> listarPorRol(Rol rol) {
        // Crear lista vacía para guardar empleados con el rol especificado
        List<Empleado> empleadosPorRol = new ArrayList<>();
        
        // Recorrer todos los empleados
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado empleado = Sistema.empleados.get(i);
            // Si el rol coincide, agregarlo a la lista
            if (empleado.getRol() == rol) {
                empleadosPorRol.add(empleado);
            }
        }
        
        // Devolver la lista de empleados con ese rol
        return empleadosPorRol;
    }

    public List<Medico> listarMedicos() {
        // Crear lista vacía para guardar médicos
        List<Medico> medicos = new ArrayList<>();
        
        // Recorrer todos los empleados
        for (int i = 0; i < Sistema.empleados.size(); i++) {
            Empleado emp = Sistema.empleados.get(i);
            // Si el empleado es un Médico y está activo, agregarlo
            if (emp instanceof Medico && emp.isActivo()) {
                medicos.add((Medico) emp);
            }
        }
        
        // Devolver la lista de médicos
        return medicos;
    }

    public List<Medico> listarMedicosPorEspecialidad(String especialidad) {
        // Obtener todos los médicos con esa especialidad
        List<Medico> todosMedicos = Sistema.obtenerMedicosPorEspecialidad(especialidad);
        
        // Crear lista vacía para guardar solo los médicos activos
        List<Medico> medicosActivos = new ArrayList<>();
        
        // Recorrer los médicos y filtrar solo los activos
        for (int i = 0; i < todosMedicos.size(); i++) {
            Medico medico = todosMedicos.get(i);
            if (medico.isActivo()) {
                medicosActivos.add(medico);
            }
        }
        
        // Devolver la lista de médicos activos con esa especialidad
        return medicosActivos;
    }
}
