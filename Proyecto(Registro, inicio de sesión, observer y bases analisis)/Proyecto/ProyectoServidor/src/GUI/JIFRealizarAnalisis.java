/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Business.TareaBusiness;
import Domain.Tarea;
import Domain.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estephanie
 */
public class JIFRealizarAnalisis extends javax.swing.JInternalFrame implements Runnable{

    /**
     * Creates new form JIFRealizarAnalisis
     */
    private TareaBusiness tareaBusiness;
    private ArrayList<Tarea> tareas;
    private Usuario usuario;
    private Tarea tareaSelected;

    public JIFRealizarAnalisis(Usuario usuario) {//permite saber cuál es el usuario que inició sesión
        this.usuario = usuario;//se guarda el usuario
        this.tareaBusiness = new TareaBusiness();
        this.tareas = new ArrayList<>();
        initComponents();
        this.tareas = this.tareaBusiness.obtenerTareas();//se obtienen las tareas registradas
        this.tareaSelected = this.tareas.get(0);//inicia en la primera tarea
        agregarTareas();//se agregan las tareas al combobox
        Thread thread = new Thread(this);
        thread.start();//se inicia el hilo para cambiar de tarea
        
    }
    
    //Al ser un hilo permite darse cuenta al momento que se selecciona otra tarea permitiendo cambiar el detalle en tiempo real
    @Override
    public void run(){
        while(true){
            this.tareaSelected = this.tareaBusiness.buscarTarea(jcbTareasPendientes.getSelectedItem().toString());
            actualizarDetalle();
        }
    }
    
    //Agrega las tareas que pertenecen al analista que inició sesión al combobox
    private void agregarTareas() {
        Iterator<Tarea> iterator = this.tareas.iterator();
        while (iterator.hasNext()) {
            Tarea tarea = iterator.next();
            if (!tarea.getAnalista().equals(this.usuario.getUser())) {//Si la tarea no tiene el analista que inició sesión 
                iterator.remove(); // se elimina la tarea del arrayList
            }
        }
        for (Tarea tarea : this.tareas) {
            this.jcbTareasPendientes.addItem(tarea.getUrl());
        }
        

    }//asignarTareas
    
    //Se cambia el el textArea la información de cada tarea
    private void actualizarDetalle(){
        this.jtaDetalleTarea.setText("URL: "+tareaSelected.getUrl()+"\n"+
                                        "Análisis de elementos que conforman un sitio web: "+tareaSelected.isAnalisis0()+"\n"+
                                        "Análisis de elementos y extracción: "+tareaSelected.isAnalisis1()+"\n"+
                                        "Imágenes: "+tareaSelected.isImagenes()+"\n"+
                                        "Enlaces: "+tareaSelected.isEnlaces()+"\n"+
                                        "Análisis de extracción y comparación: "+tareaSelected.isAnalisis2()+"\n");
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
        jLabel1 = new javax.swing.JLabel();
        jcbTareasPendientes = new javax.swing.JComboBox<>();
        jbtnAnalizar = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDetalleTarea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Análisis");

        jLabel1.setText("Elegir tarea:");

        jbtnAnalizar.setText("Analizar");

        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });

        jLabel2.setText("Detalle:");

        jtaDetalleTarea.setColumns(20);
        jtaDetalleTarea.setRows(5);
        jScrollPane1.setViewportView(jtaDetalleTarea);

        jLabel3.setText("(Tareas pendientes)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnAnalizar)
                .addGap(176, 176, 176)
                .addComponent(jbtnVolver)
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbTareasPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbTareasPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAnalizar)
                    .addComponent(jbtnVolver))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAnalizar;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcbTareasPendientes;
    private javax.swing.JTextArea jtaDetalleTarea;
    // End of variables declaration//GEN-END:variables
}
