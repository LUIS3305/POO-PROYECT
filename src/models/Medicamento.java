package models;

import java.io.Serializable;

public class Medicamento implements Serializable {
    private String nombre;
    private String marca;
    private double dosis;
    private String unidad;
    private int frecuencia;
    private int duracion;

    public Medicamento(String nombre, String marca, double dosis, String unidad, int frecuencia, int duracion) {
        this.nombre = nombre;
        this.marca = marca;
        this.dosis = dosis;
        this.unidad = unidad;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
    }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public double getDosis() { return dosis; }
    public String getUnidad() { return unidad; }
    public int getFrecuencia() { return frecuencia; }
    public int getDuracion() { return duracion; }
    
    @Override
    public String toString() {
        return nombre + " " + dosis + unidad + " (" + marca + ")";
    }
}
