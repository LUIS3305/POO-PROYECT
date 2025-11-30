package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatearFecha(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.format(FORMATO_FECHA);
    }

    public static String formatearFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) return "";
        return fechaHora.format(FORMATO_FECHA_HORA);
    }

    public static int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public static boolean esFechaValida(LocalDate fecha) {
        if (fecha == null) return false;
        LocalDate hoy = LocalDate.now();
        return !fecha.isAfter(hoy.plusYears(1));
    }

    public static boolean esFechaPasada(LocalDate fecha) {
        if (fecha == null) return false;
        return fecha.isBefore(LocalDate.now());
    }

    public static boolean esFechaFutura(LocalDate fecha) {
        if (fecha == null) return false;
        return fecha.isAfter(LocalDate.now());
    }

    public static String obtenerDiaSemana(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.getDayOfWeek().toString();
    }

    public static boolean estaDentroDeRango(LocalDateTime fechaHora, LocalDateTime inicio, LocalDateTime fin) {
        if (fechaHora == null || inicio == null || fin == null) return false;
        return !fechaHora.isBefore(inicio) && !fechaHora.isAfter(fin);
    }
}
