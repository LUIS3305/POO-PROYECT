package models;

import models.Paciente;
import models.Consulta;
import interfaces.Facturable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String numeroFactura;
    private Paciente paciente;
    private Consulta consulta;
    private LocalDateTime fechaEmision;
    private List<ItemFactura> items;
    private double subtotal;
    private double igv;
    private double total;
    private MetodoPago metodoPago;
    private boolean pagado;
    private String cajero;

    public Factura(String numeroFactura, Paciente paciente, Consulta consulta) {
        this.numeroFactura = numeroFactura;
        this.paciente = paciente;
        this.consulta = consulta;
        this.fechaEmision = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.pagado = false;
    }

    public void agregarItem(Facturable item, int cantidad) {
        items.add(new ItemFactura(item, cantidad));
        calcularTotales();
    }

    public void agregarItem(Facturable item) {
        agregarItem(item, 1);
    }

    private void calcularTotales() {
        subtotal = 0;
        for (ItemFactura item : items) {
            subtotal += item.getSubtotal();
        }
        igv = subtotal * 0.18;
        total = subtotal + igv;
    }

    public void procesarPago(MetodoPago metodoPago, String cajero) {
        this.metodoPago = metodoPago;
        this.cajero = cajero;
        this.pagado = true;
    }

    public String generarComprobante() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("          CLÍNICA AMBULATORIA\n");
        sb.append("═══════════════════════════════════════════\n");
        sb.append("FACTURA: ").append(numeroFactura).append("\n");
        sb.append("Fecha: ").append(fechaEmision.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        sb.append("───────────────────────────────────────────\n");
        sb.append("Paciente: ").append(paciente.getNombreCompleto()).append("\n");
        sb.append("DNI: ").append(paciente.getDni()).append("\n");
        sb.append("───────────────────────────────────────────\n");
        sb.append("ITEMS:\n");
        for (ItemFactura item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("───────────────────────────────────────────\n");
        sb.append(String.format("Subtotal:     S/. %.2f\n", subtotal));
        sb.append(String.format("IGV (18%%):    S/. %.2f\n", igv));
        sb.append(String.format("TOTAL:        S/. %.2f\n", total));
        sb.append("───────────────────────────────────────────\n");
        if (pagado) {
            sb.append("Estado: PAGADO\n");
            sb.append("Método de Pago: ").append(metodoPago).append("\n");
            sb.append("Atendido por: ").append(cajero).append("\n");
        } else {
            sb.append("Estado: PENDIENTE DE PAGO\n");
        }
        sb.append("═══════════════════════════════════════════\n");
        return sb.toString();
    }
    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public List<ItemFactura> getItems() { return items; }
    public void setItems(List<ItemFactura> items) { this.items = items; }

    public double getSubtotal() { return subtotal; }
    public double getIgv() { return igv; }
    public double getTotal() { return total; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }

    public String getCajero() { return cajero; }
    public void setCajero(String cajero) { this.cajero = cajero; }

    @Override
    public String toString() {
        return "Factura #" + numeroFactura + " - " + paciente.getNombreCompleto() +
               " - Total: S/. " + String.format("%.2f", total) +
               (pagado ? " (PAGADO)" : " (PENDIENTE)");
    }
}
