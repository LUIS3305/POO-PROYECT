package models;

public class Consultorio {
    private String codigo;
    private String nombre;
    private String especialidad;
    private EstadoConsultorio estado;
    private String piso;
    private String ubicacion;

    public Consultorio(String codigo, String nombre, String especialidad, String piso, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.piso = piso;
        this.ubicacion = ubicacion;
        this.estado = EstadoConsultorio.DISPONIBLE;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public EstadoConsultorio getEstado() { return estado; }
    public void setEstado(EstadoConsultorio estado) { this.estado = estado; }

    public String getPiso() { return piso; }
    public void setPiso(String piso) { this.piso = piso; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public boolean estaDisponible() {
        return estado == EstadoConsultorio.DISPONIBLE;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + especialidad + ") - " + estado;
    }
}
