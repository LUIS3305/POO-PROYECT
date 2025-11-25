package models;

import java.time.LocalDate;

public class Paciente extends Persona {
    private String numeroHistoriaClinica;
    private String tipoDocumento;
    private String contactoEmergenciaNombre;
    private String contactoEmergenciaTelefono;
    private String contactoEmergenciaRelacion;
    private String grupoSanguineo;
    private String alergias;
    private boolean activo;

    public Paciente(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                   String sexo, String telefono, String email, String direccion,
                   String tipoDocumento, String contactoEmergenciaNombre,
                   String contactoEmergenciaTelefono, String contactoEmergenciaRelacion) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, telefono, email, direccion);
        this.tipoDocumento = tipoDocumento;
        this.contactoEmergenciaNombre = contactoEmergenciaNombre;
        this.contactoEmergenciaTelefono = contactoEmergenciaTelefono;
        this.contactoEmergenciaRelacion = contactoEmergenciaRelacion;
        this.activo = true;
        this.alergias = "";
    }

    public String getNumeroHistoriaClinica() { return numeroHistoriaClinica; }
    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getContactoEmergenciaNombre() { return contactoEmergenciaNombre; }
    public void setContactoEmergenciaNombre(String contactoEmergenciaNombre) {
        this.contactoEmergenciaNombre = contactoEmergenciaNombre;
    }

    public String getContactoEmergenciaTelefono() { return contactoEmergenciaTelefono; }
    public void setContactoEmergenciaTelefono(String contactoEmergenciaTelefono) {
        this.contactoEmergenciaTelefono = contactoEmergenciaTelefono;
    }

    public String getContactoEmergenciaRelacion() { return contactoEmergenciaRelacion; }
    public void setContactoEmergenciaRelacion(String contactoEmergenciaRelacion) {
        this.contactoEmergenciaRelacion = contactoEmergenciaRelacion;
    }

    public String getGrupoSanguineo() { return grupoSanguineo; }
    public void setGrupoSanguineo(String grupoSanguineo) { this.grupoSanguineo = grupoSanguineo; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Paciente: " + getNombreCompleto() + " - HC: " +
               (numeroHistoriaClinica != null ? numeroHistoriaClinica : "Sin asignar");
    }
}
