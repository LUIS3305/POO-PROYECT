package controllers;

import models.*;
import interfaces.Facturable;
import utils.GeneradorID;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionFacturacion {

    public GestionFacturacion() {
        // Constructor vacío - Sistema es estático
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
        // Recorrer todas las facturas una por una
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            // Si el número de factura coincide, devolver esta factura
            if (factura.getNumeroFactura().equals(numeroFactura)) {
                return factura;
            }
        }
        // Si no se encontró, devolver null
        return null;
    }

    public List<Factura> listarPorPaciente(Paciente paciente) {
        // Crear lista vacía para guardar facturas del paciente
        List<Factura> facturasPaciente = new ArrayList<>();
        
        // Recorrer todas las facturas
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            // Si la factura es del paciente, agregarla a la lista
            if (factura.getPaciente().equals(paciente)) {
                facturasPaciente.add(factura);
            }
        }
        
        // Devolver la lista de facturas del paciente
        return facturasPaciente;
    }

    public List<Factura> listarPorFecha(LocalDate fecha) {
        // Crear lista vacía para guardar facturas de la fecha
        List<Factura> facturasFecha = new ArrayList<>();
        
        // Recorrer todas las facturas
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            // Si la fecha de emisión coincide, agregarla a la lista
            if (factura.getFechaEmision().toLocalDate().equals(fecha)) {
                facturasFecha.add(factura);
            }
        }
        
        // Devolver la lista de facturas de esa fecha
        return facturasFecha;
    }

    public List<Factura> listarPendientes() {
        // Crear lista vacía para guardar facturas pendientes de pago
        List<Factura> facturasPendientes = new ArrayList<>();
        
        // Recorrer todas las facturas
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            // Si la factura NO está pagada, agregarla a la lista
            if (!factura.isPagado()) {
                facturasPendientes.add(factura);
            }
        }
        
        // Devolver la lista de facturas pendientes
        return facturasPendientes;
    }

    public double calcularIngresosPorRango(LocalDate fechaInicio, LocalDate fechaFin) {
        // Variable para acumular el total de ingresos
        double totalIngresos = 0.0;
        
        // Recorrer todas las facturas
        for (int i = 0; i < Sistema.facturas.size(); i++) {
            Factura factura = Sistema.facturas.get(i);
            
            // Solo considerar facturas pagadas
            if (factura.isPagado()) {
                LocalDate fechaFactura = factura.getFechaEmision().toLocalDate();
                
                // Verificar si la fecha está dentro del rango
                // (no antes del inicio Y no después del fin)
                if (!fechaFactura.isBefore(fechaInicio) && !fechaFactura.isAfter(fechaFin)) {
                    // Sumar el total de esta factura
                    totalIngresos += factura.getTotal();
                }
            }
        }
        
        // Devolver el total de ingresos calculado
        return totalIngresos;
    }
}
