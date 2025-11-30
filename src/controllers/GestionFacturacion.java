package controllers;

import models.*;
import interfaces.Facturable;
import utils.GeneradorID;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionFacturacion {

    public GestionFacturacion() {
    }

    public Factura crearFactura(Paciente paciente, Consulta consulta) {
        String numeroFactura = GeneradorID.generarNumeroFactura();
        Factura factura = new Factura(numeroFactura, paciente, consulta);
        Sistema.facturas.add(factura);
        return factura;
    }

    public boolean agregarItemAFactura(String numeroFactura, Facturable item, int cantidad) {
        Factura factura = buscarPorNumero(numeroFactura);
        if (factura == null || factura.isPagado()) return false;

        factura.agregarItem(item, cantidad);
        return true;
    }

    public boolean procesarPago(String numeroFactura, MetodoPago metodoPago, String cajero) {
        Factura factura = buscarPorNumero(numeroFactura);
        if (factura == null || factura.isPagado()) return false;

        factura.procesarPago(metodoPago, cajero);
        return true;
    }

    public Factura buscarPorNumero(String numeroFactura) {
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            if (factura.getNumeroFactura().equals(numeroFactura)) {
                return factura;
            }
        }
        return null;
    }

    public List<Factura> listarPorPaciente(Paciente paciente) {
        List<Factura> facturasPaciente = new ArrayList<>();
        
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            if (factura.getPaciente().equals(paciente)) {
                facturasPaciente.add(factura);
            }
        }
        
        return facturasPaciente;
    }

    public List<Factura> listarPorFecha(LocalDate fecha) {
        List<Factura> facturasFecha = new ArrayList<>();
        
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            if (factura.getFechaEmision().toLocalDate().equals(fecha)) {
                facturasFecha.add(factura);
            }
        }
        
        return facturasFecha;
    }

    public List<Factura> listarPendientes() {
        List<Factura> facturasPendientes = new ArrayList<>();
        
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            if (!factura.isPagado()) {
                facturasPendientes.add(factura);
            }
        }
        
        return facturasPendientes;
    }

    public double calcularIngresosPorRango(LocalDate fechaInicio, LocalDate fechaFin) {
        double totalIngresos = 0.0;
        
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            
            if (factura.isPagado()) {
                LocalDate fechaFactura = factura.getFechaEmision().toLocalDate();
                
                if (!fechaFactura.isBefore(fechaInicio) && !fechaFactura.isAfter(fechaFin)) {
                    totalIngresos += factura.getTotal();
                }
            }
        }
        
        return totalIngresos;
    }
}
