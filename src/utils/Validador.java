package utils;

import java.util.regex.Pattern;

public class Validador {

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern TELEFONO_PATTERN =
        Pattern.compile("^\\d{7,15}$");

    private static final Pattern DNI_PATTERN =
        Pattern.compile("^\\d{8}$");

    public static boolean validarEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && TELEFONO_PATTERN.matcher(telefono).matches();
    }

    public static boolean validarDNI(String dni) {
        return dni != null && DNI_PATTERN.matcher(dni).matches();
    }

    public static boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarNumeroPositivo(double numero) {
        return numero > 0;
    }

    public static boolean validarRangoEdad(int edad) {
        return edad >= 0 && edad <= 150;
    }

    public static String limpiarTexto(String texto) {
        if (texto == null) return "";
        return texto.trim();
    }
}
