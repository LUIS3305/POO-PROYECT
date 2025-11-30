package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorID {

    private static int contadorCitas = 1000;
    private static int contadorConsultas = 1000;
    private static int contadorOrdenes = 1000;
    private static int contadorFacturas = 1000;
    private static int contadorHistorias = 1000;
    private static int contadorRecetas = 1000;

    public static String generarIDCita() {
        contadorCitas++;
        return "CIT-" + contadorCitas;
    }

    public static String generarIDConsulta() {
        contadorConsultas++;
        return "CON-" + contadorConsultas;
    }

    public static String generarIDOrden(String tipo) {
        String prefijo;
        
        if (tipo.equalsIgnoreCase("LABORATORIO") || tipo.equalsIgnoreCase("LAB")) {
            prefijo = "LAB-";
        } else if (tipo.equalsIgnoreCase("IMAGEN") || tipo.equalsIgnoreCase("IMG")) {
            prefijo = "IMG-";
        } else if (tipo.equalsIgnoreCase("PROCEDIMIENTO") || tipo.equalsIgnoreCase("PROC")) {
            prefijo = "PROC-";
        } else {
            prefijo = "ORD-";
        }
        
        contadorOrdenes++;
        return prefijo + contadorOrdenes;
    }

    public static String generarNumeroFactura() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        contadorFacturas++;
        return "F-" + fecha + "-" + contadorFacturas;
    }

    public static String generarNumeroHistoriaClinica() {
        String año = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
        contadorHistorias++;
        String numeroFormateado = String.format("%05d", contadorHistorias);
        return "HC-" + año + "-" + numeroFormateado;
    }

    public static String generarIDReceta() {
        contadorRecetas++;
        return "REC-" + contadorRecetas;
    }

    public static void resetearContadores() {
        contadorCitas = 1000;
        contadorConsultas = 1000;
        contadorOrdenes = 1000;
        contadorFacturas = 1000;
        contadorHistorias = 1000;
        contadorRecetas = 1000;
    }
}
