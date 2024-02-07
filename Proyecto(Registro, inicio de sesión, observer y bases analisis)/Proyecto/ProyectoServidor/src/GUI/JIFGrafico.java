/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Domain.Sitio;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
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
    public JIFGrafico() {
        initComponents();
        //this.sitio=new Sitio();
    }

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
        jbtnAnalizar = new javax.swing.JButton();

        setTitle("Estadísticas");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Seleccione el sitio del cual desea ver su análisis");

        jcbSitios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnAnalizar.setText("Analizar");
        jbtnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAnalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jbtnAnalizar)
                            .addGap(172, 172, 172)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jcbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jcbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jbtnAnalizar)
                .addGap(95, 95, 95))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAnalizarActionPerformed
        Sitio sitio= (Sitio)this.jcbSitios.getSelectedItem();

        DefaultPieDataset pieDataset= new DefaultPieDataset();

        pieDataset.setValue("Enlaces", sitio.getEnlaces());
        pieDataset.setValue("Imagenes", sitio.getImagenes());
        pieDataset.setValue("Videos", sitio.getVideos());
        pieDataset.setValue("Titulos", sitio.getTitulos());
        pieDataset.setValue("Subtitulos", sitio.getSubtitulos());
        pieDataset.setValue("Tablas", sitio.getTablas());

        JFreeChart chart= ChartFactory.createPieChart(
            "Elecciones generales",
            pieDataset,
            true,
            true,
            false);

        ChartFrame frame= new ChartFrame("Grafico circular", chart);
        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_jbtnAnalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbtnAnalizar;
    private javax.swing.JComboBox<String> jcbSitios;
    // End of variables declaration//GEN-END:variables
}
