package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Generador de IDs únicos para diferentes entidades del sistema
 * Versión simplificada para principiantes
 */
public class GeneradorID {

    // Contadores simples - solo números enteros
    private static int contadorCitas = 1000;
    private static int contadorConsultas = 1000;
    private static int contadorOrdenes = 1000;
    private static int contadorFacturas = 1000;
    private static int contadorHistorias = 1000;
    private static int contadorRecetas = 1000;

    /**
     * Genera un ID único para una cita
     * Ejemplo: "CIT-1001", "CIT-1002", etc.
     */
    public static String generarIDCita() {
        contadorCitas++;  // Incrementar el contador en 1
        return "CIT-" + contadorCitas;
    }

    /**
     * Genera un ID único para una consulta
     * Ejemplo: "CON-1001", "CON-1002", etc.
     */
    public static String generarIDConsulta() {
        contadorConsultas++;  // Incrementar el contador en 1
        return "CON-" + contadorConsultas;
    }

    /**
     * Genera un ID único para una orden médica
     * El prefijo cambia según el tipo de orden
     * Ejemplo: "LAB-1001", "IMG-1002", "PROC-1003"
     */
    public static String generarIDOrden(String tipo) {
        String prefijo;
        
        // Decidir el prefijo según el tipo de orden
        if (tipo.equalsIgnoreCase("LABORATORIO") || tipo.equalsIgnoreCase("LAB")) {
            prefijo = "LAB-";
        } else if (tipo.equalsIgnoreCase("IMAGEN") || tipo.equalsIgnoreCase("IMG")) {
            prefijo = "IMG-";
        } else if (tipo.equalsIgnoreCase("PROCEDIMIENTO") || tipo.equalsIgnoreCase("PROC")) {
            prefijo = "PROC-";
        } else {
            prefijo = "ORD-";
        }
        
        contadorOrdenes++;  // Incrementar el contador en 1
        return prefijo + contadorOrdenes;
    }

    /**
     * Genera un número único para una factura
     * Incluye la fecha actual
     * Ejemplo: "F-20251124-1001"
     */
    public static String generarNumeroFactura() {
        // Obtener fecha actual en formato yyyyMMdd (ejemplo: 20251124)
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        contadorFacturas++;  // Incrementar el contador en 1
        return "F-" + fecha + "-" + contadorFacturas;
    }

    /**
     * Genera un número único para una historia clínica
     * Incluye el año actual y un número de 5 dígitos
     * Ejemplo: "HC-2025-01001", "HC-2025-01002"
     */
    public static String generarNumeroHistoriaClinica() {
        // Obtener año actual (ejemplo: 2025)
        String año = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
        
        contadorHistorias++;  // Incrementar el contador en 1
        
        // Formatear el número con 5 dígitos (01001, 01002, etc.)
        String numeroFormateado = String.format("%05d", contadorHistorias);
        
        return "HC-" + año + "-" + numeroFormateado;
    }

    /**
     * Genera un ID único para una receta médica
     * Ejemplo: "REC-1001", "REC-1002", etc.
     */
    public static String generarIDReceta() {
        contadorRecetas++;  // Incrementar el contador en 1
        return "REC-" + contadorRecetas;
    }

    /**
     * Reinicia todos los contadores a su valor inicial (1000)
     * Útil para pruebas o para reiniciar el sistema
     */
    public static void resetearContadores() {
        contadorCitas = 1000;
        contadorConsultas = 1000;
        contadorOrdenes = 1000;
        contadorFacturas = 1000;
        contadorHistorias = 1000;
        contadorRecetas = 1000;
    }
}
