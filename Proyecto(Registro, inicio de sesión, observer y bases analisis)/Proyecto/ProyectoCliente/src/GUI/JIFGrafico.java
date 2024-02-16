/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Business.TareaBusiness;
import Domain.Sitio;
import Domain.Tarea;
import Domain.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Estephanie
 */
public class JIFGrafico extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFGrafico
     */
    private TareaBusiness tareaBusiness;
    private ArrayList<Tarea> tareas;
    private Usuario usuario;
    
    public JIFGrafico(Usuario usuario) {
        this.tareaBusiness = new TareaBusiness();
        this.tareas = new ArrayList<>();
        this.usuario = usuario;
        this.tareas = this.tareaBusiness.obtenerTareas();//obtiene todas las tareas registradas
        initComponents();
        agregarTareas();
        //this.sitio=new Sitio();
    }//constructor
    
 //Agrega las tareas que pertenecen al analista que inició sesión al combobox
    private void agregarTareas() {
        Iterator<Tarea> iterator = this.tareas.iterator();
        while (iterator.hasNext()) {
            Tarea tarea = iterator.next();
            if ((!tarea.getAnalista().equals(this.usuario.getUser())) || tarea.getEstado().equals("en proceso")) {//Si la tarea no tiene el analista que inició sesión y si no está en proceso ya está asignada
                iterator.remove(); // se elimina la tarea del arrayList
            }
        }
        for (Tarea tarea : this.tareas) {
            this.jcbSitios.addItem(tarea.getUrl());
        }

    }//asignarTareas

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcbSitios = new javax.swing.JComboBox<>();
        jbtnGraficarPastel = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();
        jbtnBarras = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Estadísticas");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Seleccione el sitio del cual desea ver su análisis");

        jbtnGraficarPastel.setText("Graficar");
        jbtnGraficarPastel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficarPastelActionPerformed(evt);
            }
        });

        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });

        jbtnBarras.setText("Graficar");

        jLabel2.setText("Pastel");

        jLabel3.setText("De barras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jbtnGraficarPastel)
                .addGap(51, 51, 51)
                .addComponent(jbtnBarras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnVolver)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jcbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel3)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jcbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnGraficarPastel)
                    .addComponent(jbtnVolver)
                    .addComponent(jbtnBarras))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGraficarPastelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficarPastelActionPerformed
//        Sitio sitio= (Sitio)this.jcbSitios.getSelectedItem();
//
//        DefaultPieDataset pieDataset= new DefaultPieDataset();
//
//        pieDataset.setValue("Enlaces", sitio.getEnlaces());
//        pieDataset.setValue("Imagenes", sitio.getImagenes());
//        pieDataset.setValue("Videos", sitio.getVideos());
//        pieDataset.setValue("Titulos", sitio.getTitulos());
//        pieDataset.setValue("Subtitulos", sitio.getSubtitulos());
//        pieDataset.setValue("Tablas", sitio.getTablas());
//
//        JFreeChart chart= ChartFactory.createPieChart(
//            "Elecciones generales",
//            pieDataset,
//            true,
//            true,
//            false);
//
//        ChartFrame frame= new ChartFrame("Grafico circular", chart);
//        frame.pack();
//        frame.setVisible(true);
    }//GEN-LAST:event_jbtnGraficarPastelActionPerformed

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnBarras;
    private javax.swing.JButton jbtnGraficarPastel;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcbSitios;
    // End of variables declaration//GEN-END:variables
}
