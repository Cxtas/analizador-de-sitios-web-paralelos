/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Business.AdministradorBusiness;
import Business.ExaminadorBusiness;
import Domain.Administrador;
import Domain.Examinador;
import Utility.MyUtil;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class JIFRegistroUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFRegistroUsuario
     */
    public JIFRegistroUsuario() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgRolUsuario = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfContrasenia = new javax.swing.JPasswordField();
        btnResgistrar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jcbTipoUsuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jrbDigitador = new javax.swing.JRadioButton();
        jrbGestor = new javax.swing.JRadioButton();
        jrbAnalista = new javax.swing.JRadioButton();

        setTitle("Nuevo usuario");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registro de nuevo Usuario");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Usuario:");

        jtfUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfUserActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contraseña:");

        btnResgistrar.setText("Registrar");
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jcbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Examinador" }));
        jcbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoUsuarioActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tipo:");

        bgRolUsuario.add(jrbDigitador);
        jrbDigitador.setForeground(new java.awt.Color(0, 0, 0));
        jrbDigitador.setText("Digitador");

        bgRolUsuario.add(jrbGestor);
        jrbGestor.setForeground(new java.awt.Color(0, 0, 0));
        jrbGestor.setText("Gestor");

        bgRolUsuario.add(jrbAnalista);
        jrbAnalista.setForeground(new java.awt.Color(0, 0, 0));
        jrbAnalista.setText("Analista");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfUser)
                            .addComponent(jtfContrasenia)
                            .addComponent(jcbTipoUsuario, 0, 131, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbGestor)
                            .addComponent(jrbDigitador)
                            .addComponent(jrbAnalista))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnResgistrar)
                .addGap(100, 100, 100)
                .addComponent(btnVolver)
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jrbDigitador))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbGestor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jrbAnalista))
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(btnVolver)
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResgistrar)
                        .addGap(42, 42, 42))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfUserActionPerformed

    private void btnResgistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResgistrarActionPerformed
        try {
            if (this.jcbTipoUsuario.getSelectedIndex() == 0) {//0 es administrador
                AdministradorBusiness administradorBusiness = new AdministradorBusiness();
                boolean registrado = administradorBusiness.insertarAdministrador(new Administrador(this.jtfUser.getText(),
                        MyUtil.obtenerContraseniaCifrada(String.valueOf(this.jtfContrasenia.getPassword()), MyUtil.MD2),
                        "administrador", true));
                if (registrado) {
                    JOptionPane.showMessageDialog(this, "Usuario registrado");
                    borrarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe un usuario con ese nombre");
                    borrarDatos();
                }
            } else {//examinador
                if (getSelectedRol() != null) {
                    ExaminadorBusiness examinadorBusiness = new ExaminadorBusiness();
                    boolean registrado = examinadorBusiness.insertarExaminador(new Examinador(this.jtfUser.getText(),
                            MyUtil.obtenerContraseniaCifrada(String.valueOf(this.jtfContrasenia.getPassword()), MyUtil.MD2),
                            this.getSelectedRol(), "examinador", true));
                    if (registrado) {
                        JOptionPane.showMessageDialog(this, "Usuario registrado");
                        borrarDatos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ya existe un usuario con ese nombre.");
                        borrarDatos();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un rol.");
                }
            }
        } catch (NoSuchAlgorithmException | IOException | JDOMException ex) {
            java.util.logging.Logger.getLogger(JIFRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los espacios");
        }

    }//GEN-LAST:event_btnResgistrarActionPerformed

    private String getSelectedRol() {
        if (jrbAnalista.isSelected()) {
            return "analista";
        } else if (jrbDigitador.isSelected()) {
            return "digitador";
        } else if (jrbGestor.isSelected()) {
            return "gestor";
        }
        return null;
    }//getSelectedRol
    
    private void borrarDatos(){
        this.jtfUser.setText("");
        this.jtfContrasenia.setText("");
        this.jrbAnalista.setSelected(false);
        this.jrbDigitador.setSelected(false);
        this.jrbGestor.setSelected(false);
    }

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void jcbTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoUsuarioActionPerformed
        if (this.jcbTipoUsuario.getSelectedIndex() == 1) {
            this.jrbAnalista.setEnabled(true);
            this.jrbDigitador.setEnabled(true);
            this.jrbGestor.setEnabled(true);
        }
    }//GEN-LAST:event_jcbTipoUsuarioActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgRolUsuario;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jcbTipoUsuario;
    private javax.swing.JRadioButton jrbAnalista;
    private javax.swing.JRadioButton jrbDigitador;
    private javax.swing.JRadioButton jrbGestor;
    private javax.swing.JPasswordField jtfContrasenia;
    private javax.swing.JTextField jtfUser;
    // End of variables declaration//GEN-END:variables

}
