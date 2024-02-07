/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Business.AdministradorBusiness;
import Business.ExaminadorBusiness;
import Domain.Administrador;
import Domain.Examinador;
import Domain.Usuario;
import Utility.GestionXML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class JIFEliminarUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFEliminarUsuarios
     */
    private ArrayList<Usuario> usuarios;
    private AdministradorBusiness administradorBusiness;
    private ExaminadorBusiness examinadorBusiness;

    public JIFEliminarUsuarios() {
        try {
            this.administradorBusiness = new AdministradorBusiness();
            this.examinadorBusiness = new ExaminadorBusiness();
            initComponents();
            this.usuarios = new ArrayList<>();
            solicitarUsuarios();
        } catch (IOException ex) {
            Logger.getLogger(JIFEliminarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(JIFEliminarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void solicitarUsuarios() {
        this.usuarios.addAll(this.examinadorBusiness.obtenerExaminadores());
        this.usuarios.addAll(this.administradorBusiness.obtenerAdministradores());
        for (int i = 0; i < this.usuarios.size(); i++) {
            this.jcbUsuarios.addItem(this.usuarios.get(i).getUser());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jcbUsuarios = new javax.swing.JComboBox<>();
        jbtnEliminar = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();

        setTitle("Eliminar");

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jcbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jbtnEliminar)))
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnVolver)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jcbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jbtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jbtnVolver)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        try {
            int pos = jcbUsuarios.getSelectedIndex();
            this.usuarios.get(pos).setActivo(false);
            if (this.usuarios.get(pos).getTipoUsuario().equals("administrador")) {
                if(this.administradorBusiness.desactivarAdministrador((Administrador)this.usuarios.get(pos))){
                    JOptionPane.showMessageDialog(this, "Se eliminó correctamente");
                }else{
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar");
                }
            } else {
                 if(this.examinadorBusiness.desactivarExaminador((Examinador)this.usuarios.get(pos))){
                     JOptionPane.showMessageDialog(this, "Se eliminó correctamente");
                 }else{
                      JOptionPane.showMessageDialog(this, "No se pudo eliminar");
                 }
            }
        } catch (IOException ex) {
            Logger.getLogger(JIFEliminarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcbUsuarios;
    // End of variables declaration//GEN-END:variables
}
