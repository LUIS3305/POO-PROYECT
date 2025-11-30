package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receta {
    private String id;
    private LocalDate fechaEmision;
    private List<Medicamento> medicamentos;
    private String indicacionesGenerales;

    public Receta(String id) {
        this.id = id;
        this.fechaEmision = LocalDate.now();
        this.medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(String nombre, String dosis, String frecuencia, int duracionDias) {
        medicamentos.add(new Medicamento(nombre, dosis, frecuencia, duracionDias));
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public void setMedicamentos(List<Medicamento> medicamentos) { this.medicamentos = medicamentos; }

    public String getIndicacionesGenerales() { return indicacionesGenerales; }
    public void setIndicacionesGenerales(String indicacionesGenerales) {
        this.indicacionesGenerales = indicacionesGenerales;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receta #").append(id).append(" - ").append(fechaEmision).append("\n");
        for (Medicamento med : medicamentos) {
            sb.append("  ").append(med).append("\n");
        }
        return sb.toString();
    }

    public static class Medicamento {
        private String nombre;
        private String dosis;
        private String frecuencia;
        private int duracionDias;

        public Medicamento(String nombre, String dosis, String frecuencia, int duracionDias) {
            this.nombre = nombre;
            this.dosis = dosis;
            this.frecuencia = frecuencia;
            this.duracionDias = duracionDias;
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDosis() { return dosis; }
        public void setDosis(String dosis) { this.dosis = dosis; }

        public String getFrecuencia() { return frecuencia; }
        public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

        public int getDuracionDias() { return duracionDias; }
        public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }

        @Override
        public String toString() {
            return nombre + " - " + dosis + ", " + frecuencia + " por " + duracionDias + " días";
        }
    }
}
