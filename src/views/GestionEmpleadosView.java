package views;

import controllers.GestionEmpleados;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import models.Administrador;
import models.Empleado;
import models.Medico;
import models.Recepcionista;
import models.*;


public class GestionEmpleadosView extends javax.swing.JFrame {
    
    private DefaultTableModel tableModel;
    private GestionEmpleados gestionEmpleados;

    public GestionEmpleadosView() {
        gestionEmpleados = new GestionEmpleados();
        initComponents();
        tableModel = (DefaultTableModel) table.getModel();
        cargarDatos();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setTitle("Gestión de Empleados");

        toolBar.setRollover(true);

        btnAdd.setText("Nuevo Empleado");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        toolBar.add(btnAdd);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Rol", "Nombre", "DNI"
            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        agregarEmpleado();
    }//GEN-LAST:event_btnAddActionPerformed


    private void cargarDatos() {
        tableModel.setRowCount(0);
        for (Usuario u : Sistema.usuarios) {
            tableModel.addRow(new Object[]{
                u.getUsername(),
                u.getRol(),
                u.getEmpleado().getNombres() + " " + u.getEmpleado().getApellidos(),
                u.getEmpleado().getDni()
            });
        }
    }

    private void agregarEmpleado() {
        // Simple dialog to add user (simplified for demo)
        JTextField txtUser = new JTextField();
        JTextField txtPass = new JTextField();
        String[] roles = {"Administrador", "Medico", "Recepcionista"};
        JComboBox<String> cbRol = new JComboBox<>(roles);
        
        Object[] message = {
            "Usuario:", txtUser,
            "Contraseña:", txtPass,
            "Rol:", cbRol
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nuevo Empleado", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String user = txtUser.getText();
            String pass = txtPass.getText();
            String rol = (String) cbRol.getSelectedItem();
            
            // Create dummy employee data for now
            Empleado emp = null;
            java.time.LocalDate dummyDate = java.time.LocalDate.now();
            if (rol.equals("Medico")) {
                emp = new Medico("DNI", "Nom", "Ape", dummyDate, "M", "Tel", "Email", "Dir", "General", "CMP123");
            } else if (rol.equals("Administrador")) {
                emp = new Administrador("DNI", "Nom", "Ape", dummyDate, "M", "Tel", "Email", "Dir", "Sistemas", "Jefe");
            } else {
                emp = new Recepcionista("DNI", "Nom", "Ape", dummyDate, "F", "Tel", "Email", "Dir", "Mañana");
            }
            
            Usuario nuevo = new Usuario(user, pass, rol);
            nuevo.setEmpleado(emp);
            
            Sistema.usuarios.add(nuevo);
            cargarDatos();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
