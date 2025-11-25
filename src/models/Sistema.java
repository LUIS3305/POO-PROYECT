package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase estática que centraliza todos los datos del sistema
 */
public class Sistema implements Serializable {
    // ===== LISTAS DE DATOS =====
    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Empleado> empleados = new ArrayList<>();
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Consultorio> consultorios = new ArrayList<>();
    public static List<HorarioAtencion> horariosAtencion = new ArrayList<>();
    public static List<Cita> citas = new ArrayList<>();
    public static List<Consulta> consultas = new ArrayList<>();
    public static List<HistoriaClinica> historiasClinicas = new ArrayList<>();
    public static List<Orden> ordenes = new ArrayList<>();
    public static List<Factura> facturas = new ArrayList<>();

    // ===== SESIÓN =====
    public static Empleado usuarioActual = null;

    // ===== MÉTODOS DE AUTENTICACIÓN =====
    public static Usuario login(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.autenticar(password)) {
                if (u.getEmpleado() != null) {
                    usuarioActual = u.getEmpleado();
                }
                return u;
            }
        }
        return null;
    }

    public static void logout() {
        usuarioActual = null;
    }

    // ===== MÉTODOS DE BÚSQUEDA =====
    public static Paciente buscarPacientePorDNI(String dni) {
        // Recorrer todos los pacientes uno por uno
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente paciente = pacientes.get(i);
            // Si el DNI coincide, devolver este paciente
            if (paciente.getDni().equals(dni)) {
                return paciente;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public static Consultorio buscarConsultorioPorCodigo(String codigo) {
        // Recorrer todos los consultorios uno por uno
        for (int i = 0; i < consultorios.size(); i++) {
            Consultorio consultorio = consultorios.get(i);
            // Si el código coincide, devolver este consultorio
            if (consultorio.getCodigo().equals(codigo)) {
                return consultorio;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public static HistoriaClinica buscarHistoriaClinicaPorPaciente(Paciente paciente) {
        // Recorrer todas las historias clínicas una por una
        for (int i = 0; i < historiasClinicas.size(); i++) {
            HistoriaClinica hc = historiasClinicas.get(i);
            // Si la historia clínica es del paciente, devolverla
            if (hc.getPaciente().equals(paciente)) {
                return hc;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public static Empleado buscarEmpleadoPorDNI(String dni) {
        // Recorrer todos los empleados uno por uno
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            // Si el DNI coincide, devolver este empleado
            if (empleado.getDni().equals(dni)) {
                return empleado;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    // ===== MÉTODOS DE FILTRADO =====
    public static List<Medico> obtenerMedicos() {
        List<Medico> medicos = new ArrayList<>();
        for (Empleado emp : empleados) {
            if (emp instanceof Medico) {
                medicos.add((Medico) emp);
            }
        }
        return medicos;
    }

    public static List<Medico> obtenerMedicosPorEspecialidad(String especialidad) {
        List<Medico> resultado = new ArrayList<>();
        for (Empleado emp : empleados) {
            if (emp instanceof Medico) {
                Medico medico = (Medico) emp;
                if (medico.getEspecialidad().equalsIgnoreCase(especialidad)) {
                    resultado.add(medico);
                }
            }
        }
        return resultado;
    }

    // ===== MÉTODO DE REINICIO =====
    public static void reiniciarSistema() {
        usuarios.clear();
        empleados.clear();
        pacientes.clear();
        consultorios.clear();
        horariosAtencion.clear();
        citas.clear();
        consultas.clear();
        historiasClinicas.clear();
        ordenes.clear();
        facturas.clear();
        usuarioActual = null;
    }
}
