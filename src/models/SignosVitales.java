package models;

import java.time.LocalDateTime;

public class SignosVitales {
    private double temperatura;
    private int presionArterialSistolica;
    private int presionArterialDiastolica;
    private int frecuenciaCardiaca;
    private int frecuenciaRespiratoria;
    private double saturacionOxigeno;
    private double peso;
    private double talla;
    private double imc;
    private LocalDateTime fechaRegistro;
    private String registradoPor;

    public SignosVitales() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public int getPresionArterialSistolica() { return presionArterialSistolica; }
    public void setPresionArterialSistolica(int presionArterialSistolica) {
        this.presionArterialSistolica = presionArterialSistolica;
    }

    public int getPresionArterialDiastolica() { return presionArterialDiastolica; }
    public void setPresionArterialDiastolica(int presionArterialDiastolica) {
        this.presionArterialDiastolica = presionArterialDiastolica;
    }

    public String getPresionArterial() {
        return presionArterialSistolica + "/" + presionArterialDiastolica;
    }

    public int getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getFrecuenciaRespiratoria() { return frecuenciaRespiratoria; }
    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public double getSaturacionOxigeno() { return saturacionOxigeno; }
    public void setSaturacionOxigeno(double saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public double getPeso() { return peso; }
    public void setPeso(double peso) {
        this.peso = peso;
        calcularIMC();
    }

    public double getTalla() { return talla; }
    public void setTalla(double talla) {
        this.talla = talla;
        calcularIMC();
    }

    public double getImc() { return imc; }

    private void calcularIMC() {
        if (peso > 0 && talla > 0) {
            double tallaMts = talla / 100.0;
            this.imc = peso / (tallaMts * tallaMts);
        }
    }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(String registradoPor) { this.registradoPor = registradoPor; }

    @Override
    public String toString() {
        return String.format("T: %.1f°C | PA: %s mmHg | FC: %d lpm | FR: %d rpm | SpO2: %.1f%% | IMC: %.2f",
            temperatura, getPresionArterial(), frecuenciaCardiaca, frecuenciaRespiratoria,
            saturacionOxigeno, imc);
    }
}
