/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Domain.Examinador;
import Domain.Usuario;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class JFPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form JFPrincipal
     */
    private Usuario usuario;

    public JFPrincipal(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        setLocationRelativeTo(null);
        permisos();
    }
    
    //permite que el internal se muestre en el centro del desktop
    private void centrar(JInternalFrame frame){
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frame.show();
    }

    //Este método abre los menús depende del tipo y rol de cada usuario
    private void permisos() {
        if (this.usuario.getTipoUsuario().equals("examinador")) {
            this.jmRegistrar.setEnabled(false);//Los examinadores no pueden registrar usuarios
            Examinador examinador = (Examinador) this.usuario;//establecer el usuario como examinador
            switch (examinador.getRol()) { //depende del rol tiene acceso a distintos menús
                case "digitador" -> {
                    this.jmDatos.setEnabled(true);//ingresa urls
                    this.jmTareas.setEnabled(false);
                }
                case "gestor" -> {
                    this.jmResultados.setEnabled(false);//asigna tareas
                    this.jmiGSolicitudes.setEnabled(true);
                }
                case "analista" -> {
                    this.jmAnalisis.setEnabled(true);//Solo el analista puede analizar las páginas
                }

            }
        }
    }//permisos

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmRegistrar = new javax.swing.JMenu();
        jmiNuevoUsuario = new javax.swing.JMenuItem();
        jmiEliminarUsuarios = new javax.swing.JMenuItem();
        jmDatos = new javax.swing.JMenu();
        jmiIngresarDatos = new javax.swing.JMenuItem();
        jmTareas = new javax.swing.JMenu();
        jmiGSolicitudes = new javax.swing.JMenuItem();
        jmiEstadoTareas = new javax.swing.JMenuItem();
        jmAnalisis = new javax.swing.JMenu();
        jmResultados = new javax.swing.JMenu();
        jmiGrafico = new javax.swing.JMenuItem();
        jmiPDFenviar = new javax.swing.JMenuItem();
        jmSesion = new javax.swing.JMenu();
        jmiCambioUsuario = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jmRegistrar.setText("Registrar");

        jmiNuevoUsuario.setText("Nuevo usuario");
        jmiNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNuevoUsuarioActionPerformed(evt);
            }
        });
        jmRegistrar.add(jmiNuevoUsuario);

        jmiEliminarUsuarios.setText("Eliminar usuarios");
        jmiEliminarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEliminarUsuariosActionPerformed(evt);
            }
        });
        jmRegistrar.add(jmiEliminarUsuarios);

        jMenuBar1.add(jmRegistrar);

        jmDatos.setText("Datos");
        jmDatos.setEnabled(false);

        jmiIngresarDatos.setText("Establecer datos para el análisis");
        jmiIngresarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIngresarDatosActionPerformed(evt);
            }
        });
        jmDatos.add(jmiIngresarDatos);

        jMenuBar1.add(jmDatos);

        jmTareas.setText("Tareas");

        jmiGSolicitudes.setText("Gestionar solicitudes");
        jmiGSolicitudes.setEnabled(false);
        jmiGSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGSolicitudesActionPerformed(evt);
            }
        });
        jmTareas.add(jmiGSolicitudes);

        jmiEstadoTareas.setText("Estado de tareas");
        jmiEstadoTareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEstadoTareasActionPerformed(evt);
            }
        });
        jmTareas.add(jmiEstadoTareas);

        jMenuBar1.add(jmTareas);

        jmAnalisis.setText("Analisis");
        jmAnalisis.setEnabled(false);
        jmAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAnalisisActionPerformed(evt);
            }
        });
        jMenuBar1.add(jmAnalisis);

        jmResultados.setText("Resultados");

        jmiGrafico.setText("Gráfico");
        jmiGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGraficoActionPerformed(evt);
            }
        });
        jmResultados.add(jmiGrafico);

        jmiPDFenviar.setText("Generar PDF y enviar");
        jmiPDFenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPDFenviarActionPerformed(evt);
            }
        });
        jmResultados.add(jmiPDFenviar);

        jMenuBar1.add(jmResultados);

        jmSesion.setText("Sesión");

        jmiCambioUsuario.setText("Cambiar de usuario");
        jmiCambioUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCambioUsuarioActionPerformed(evt);
            }
        });
        jmSesion.add(jmiCambioUsuario);

        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jmSesion.add(jmiSalir);

        jMenuBar1.add(jmSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuevoUsuarioActionPerformed
        JIFRegistroUsuario ventanaR = new JIFRegistroUsuario();
        this.jDesktopPane1.add(ventanaR);
        centrar(ventanaR);
//        ventanaR.setVisible(true);
    }//GEN-LAST:event_jmiNuevoUsuarioActionPerformed

    private void jmiIngresarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIngresarDatosActionPerformed
        JIFDatosAnalisis ventana = new JIFDatosAnalisis();
        this.jDesktopPane1.add(ventana);
        centrar(ventana);
    }//GEN-LAST:event_jmiIngresarDatosActionPerformed

    private void jmiEliminarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEliminarUsuariosActionPerformed
        JIFEliminarUsuarios ventana = new JIFEliminarUsuarios();
        this.jDesktopPane1.add(ventana);
        centrar(ventana);
    }//GEN-LAST:event_jmiEliminarUsuariosActionPerformed

    private void jmiGSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGSolicitudesActionPerformed
        try {
            JIFAsignarTarea tarea = new JIFAsignarTarea();
            this.jDesktopPane1.add(tarea);
            centrar(tarea);
        } catch (IOException ex) {
            Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiGSolicitudesActionPerformed

    private void jmiEstadoTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEstadoTareasActionPerformed
        JIFEstadoTareas tarea = new JIFEstadoTareas();
        this.jDesktopPane1.add(tarea);
        centrar(tarea);

    }//GEN-LAST:event_jmiEstadoTareasActionPerformed

    private void jmAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAnalisisActionPerformed
        JIFRealizarAnalisis analisis = new JIFRealizarAnalisis();
        this.jDesktopPane1.add(analisis);
        centrar(analisis);
    }//GEN-LAST:event_jmAnalisisActionPerformed

    private void jmiGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGraficoActionPerformed
        JIFGrafico grafico = new JIFGrafico();
        this.jDesktopPane1.add(grafico);
        centrar(grafico);
    }//GEN-LAST:event_jmiGraficoActionPerformed

    private void jmiPDFenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPDFenviarActionPerformed
        JIFGenerarPDFenviar generar = new JIFGenerarPDFenviar();
        this.jDesktopPane1.add(generar);
        centrar(generar);
    }//GEN-LAST:event_jmiPDFenviarActionPerformed

    private void jmiCambioUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCambioUsuarioActionPerformed
        this.setVisible(false);
        JFLogging logging = new JFLogging();
        logging.setVisible(true);
    }//GEN-LAST:event_jmiCambioUsuarioActionPerformed

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jmiSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrincipal(null).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jmAnalisis;
    private javax.swing.JMenu jmDatos;
    private javax.swing.JMenu jmRegistrar;
    private javax.swing.JMenu jmResultados;
    private javax.swing.JMenu jmSesion;
    private javax.swing.JMenu jmTareas;
    private javax.swing.JMenuItem jmiCambioUsuario;
    private javax.swing.JMenuItem jmiEliminarUsuarios;
    private javax.swing.JMenuItem jmiEstadoTareas;
    private javax.swing.JMenuItem jmiGSolicitudes;
    private javax.swing.JMenuItem jmiGrafico;
    private javax.swing.JMenuItem jmiIngresarDatos;
    private javax.swing.JMenuItem jmiNuevoUsuario;
    private javax.swing.JMenuItem jmiPDFenviar;
    private javax.swing.JMenuItem jmiSalir;
    // End of variables declaration//GEN-END:variables
}
