package models;

import interfaces.Facturable;

public class ItemFactura {
    private Facturable item;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemFactura(Facturable item, int cantidad) {
        this.item = item;
        this.cantidad = cantidad;
        this.precioUnitario = item.obtenerMonto();
        this.subtotal = precioUnitario * cantidad;
    }
    public Facturable getItem() { return item; }
    public void setItem(Facturable item) { this.item = item; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = precioUnitario * cantidad;
    }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = precioUnitario * cantidad;
    }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return item.obtenerDescripcion() + " x" + cantidad + " = S/. " +
               String.format("%.2f", subtotal);
    }
}
