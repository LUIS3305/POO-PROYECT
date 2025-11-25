package prueba;

import controllers.*;
import models.*;
import views.LoginFrame;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Clase principal del Sistema de Gestión de Clínica
 * Inicializa datos de prueba y lanza la aplicación
 */
public class Main {

    public static void main(String[] args) {
        inicializarDatosPrueba();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }

    private static void inicializarDatosPrueba() {
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        GestionConsultorios gestionConsultorios = new GestionConsultorios();
        GestionPacientes gestionPacientes = new GestionPacientes();


        // CREAR EMPLEADOS 
    

        // Administrador
        Administrador admin = new Administrador(
            "12345678", "Carlos", "García Pérez",
            LocalDate.of(1980, 5, 15), "M",
            "987654321", "admin@clinica.com", "Av. Principal 123",
            "Administración General", "Administrador General"
        );
        gestionEmpleados.agregarEmpleado(admin, "admin", "admin123");

        // Recepcionista
        Recepcionista recep = new Recepcionista(
            "23456789", "María", "López Sánchez",
            LocalDate.of(1992, 8, 20), "F",
            "987654322", "recepcion@clinica.com", "Calle Lima 456",
            "Mañana"
        );
        gestionEmpleados.agregarEmpleado(recep, "recepcion", "recep123");

        // Médicos
        Medico medico1 = new Medico(
            "34567890", "Juan", "Rodríguez Torres",
            LocalDate.of(1975, 3, 10), "M",
            "987654323", "jrodriguez@clinica.com", "Av. Arequipa 789",
            "Medicina General", "CMP-12345"
        );
        gestionEmpleados.agregarEmpleado(medico1, "drjuan", "medico123");

        Medico medico2 = new Medico(
            "45678901", "Ana", "Martínez Flores",
            LocalDate.of(1982, 11, 25), "F",
            "987654324", "amartinez@clinica.com", "Jr. Cusco 321",
            "Pediatría", "CMP-23456"
        );
        gestionEmpleados.agregarEmpleado(medico2, "draana", "medico123");

        Medico medico3 = new Medico(
            "56789012", "Pedro", "Fernández Ruiz",
            LocalDate.of(1978, 7, 18), "M",
            "987654325", "pfernandez@clinica.com", "Av. Brasil 654",
            "Cardiología", "CMP-34567"
        );
        gestionEmpleados.agregarEmpleado(medico3, "drpedro", "medico123");

        // Enfermero
        Enfermero enfermero = new Enfermero(
            "67890123", "Rosa", "Vega Morales",
            LocalDate.of(1988, 4, 12), "F",
            "987654326", "rvega@clinica.com", "Calle Sol 987",
            "Consulta Externa", "Mañana"
        );
        gestionEmpleados.agregarEmpleado(enfermero, "enfermera", "enf123");

        // Cajero
        Cajero cajero = new Cajero(
            "78901234", "Luis", "Campos Díaz",
            LocalDate.of(1990, 9, 30), "M",
            "987654327", "lcampos@clinica.com", "Jr. Puno 147",
            "1", "Mañana"
        );
        gestionEmpleados.agregarEmpleado(cajero, "cajero", "caj123");

        // CREAR CONSULTORIOS

        Consultorio cons1 = new Consultorio(
            "C-101", "Consultorio 1", "Medicina General",
            "Piso 1", "Ala A"
        );
        gestionConsultorios.agregarConsultorio(cons1);

        Consultorio cons2 = new Consultorio(
            "C-102", "Consultorio 2", "Pediatría",
            "Piso 1", "Ala A"
        );
        gestionConsultorios.agregarConsultorio(cons2);

        Consultorio cons3 = new Consultorio(
            "C-201", "Consultorio 3", "Cardiología",
            "Piso 2", "Ala B"
        );
        gestionConsultorios.agregarConsultorio(cons3);

        Consultorio cons4 = new Consultorio(
            "C-202", "Consultorio 4", "Medicina General",
            "Piso 2", "Ala B"
        );
        gestionConsultorios.agregarConsultorio(cons4);

        //  CREAR PACIENTES DE PRUEBA 

        Paciente pac1 = new Paciente(
            "40123456", "Roberto", "Sánchez Vega",
            LocalDate.of(1985, 6, 15), "M",
            "912345678", "rsanchez@email.com", "Av. Venezuela 234",
            "DNI", "Carmen Vega", "912345679", "Esposa"
        );
        gestionPacientes.registrarPaciente(pac1);

        Paciente pac2 = new Paciente(
            "40234567", "Laura", "Mendoza Castro",
            LocalDate.of(1990, 3, 22), "F",
            "923456789", "lmendoza@email.com", "Jr. Tacna 567",
            "DNI", "José Mendoza", "923456780", "Padre"
        );
        gestionPacientes.registrarPaciente(pac2);

        Paciente pac3 = new Paciente(
            "40345678", "Miguel", "Torres Ríos",
            LocalDate.of(1978, 12, 8), "M",
            "934567890", "mtorres@email.com", "Calle Luna 890",
            "DNI", "Patricia Ríos", "934567891", "Esposa"
        );
        gestionPacientes.registrarPaciente(pac3);

        Paciente pac4 = new Paciente(
            "40456789", "Sofía", "Ramírez Gómez",
            LocalDate.of(2015, 5, 10), "F",
            "945678901", "sramirez@email.com", "Av. Colonial 123",
            "DNI", "Andrea Gómez", "945678902", "Madre"
        );
        gestionPacientes.registrarPaciente(pac4);


        System.out.println("\nUSUARIOS DISPONIBLES:");
        System.out.println("  Administrador  : admin      / admin123");
        System.out.println("  Recepcionista  : recepcion  / recep123");
        System.out.println("  Médico General : drjuan     / medico123");
        System.out.println("  Pediatra       : draana     / medico123");
        System.out.println("  Cardiólogo     : drpedro    / medico123");
        System.out.println("  Enfermera      : enfermera  / enf123");
        System.out.println("  Cajero         : cajero     / caj123");
    }
}
