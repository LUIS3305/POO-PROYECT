package models;

import models.Paciente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {
    private String numeroHistoriaClinica;
    private Paciente paciente;
    private LocalDate fechaApertura;
    private List<Consulta> consultas;
    private String antecedentesPersonales;
    private String antecedentesFamiliares;
    private String alergias;
    private String medicacionHabitual;
    private String cirugiasPrevias;
    private String hospitalizacionesPrevias;

    public HistoriaClinica(String numeroHistoriaClinica, Paciente paciente) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
        this.paciente = paciente;
        this.fechaApertura = LocalDate.now();
        this.consultas = new ArrayList<>();
        paciente.setNumeroHistoriaClinica(numeroHistoriaClinica);
    }

    public void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public List<Consulta> getConsultasPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta consulta : consultas) {
            LocalDate fechaConsulta = consulta.getFechaHoraInicio().toLocalDate();
            if (!fechaConsulta.isBefore(fechaInicio) && !fechaConsulta.isAfter(fechaFin)) {
                resultado.add(consulta);
            }
        }
        return resultado;
    }
    public String getNumeroHistoriaClinica() { return numeroHistoriaClinica; }
    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }

    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }

    public String getAntecedentesPersonales() { return antecedentesPersonales; }
    public void setAntecedentesPersonales(String antecedentesPersonales) {
        this.antecedentesPersonales = antecedentesPersonales;
    }

    public String getAntecedentesFamiliares() { return antecedentesFamiliares; }
    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getMedicacionHabitual() { return medicacionHabitual; }
    public void setMedicacionHabitual(String medicacionHabitual) {
        this.medicacionHabitual = medicacionHabitual;
    }

    public String getCirugiasPrevias() { return cirugiasPrevias; }
    public void setCirugiasPrevias(String cirugiasPrevias) { this.cirugiasPrevias = cirugiasPrevias; }

    public String getHospitalizacionesPrevias() { return hospitalizacionesPrevias; }
    public void setHospitalizacionesPrevias(String hospitalizacionesPrevias) {
        this.hospitalizacionesPrevias = hospitalizacionesPrevias;
    }

    @Override
    public String toString() {
        return "Historia Clínica #" + numeroHistoriaClinica + " - " +
               paciente.getNombreCompleto() + " (" + consultas.size() + " consultas)";
    }
}
