/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;


import Business.TareaBusiness;
import Domain.ClienteSingleton;
import Domain.Examinador;
import Domain.Tarea;
import Utility.GestionXML;
import Utility.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author catas
 */
public class JIFAsignarTarea extends javax.swing.JInternalFrame implements Observer {

    private ArrayList<Examinador> analistas;
    private Examinador analista;

    private TareaBusiness tareaBusiness;
    private ArrayList<Tarea> tareas;//Tareas Por Analizar
    private Tarea tarea; //tarea seleccionada

    /**
     * Creates new form JIFAsignarTarea
     *
     * @throws java.io.IOException
     * @throws org.jdom.JDOMException
     */
    private ClienteSingleton clienteSingleton;
    public JIFAsignarTarea() throws IOException, JDOMException {

        this.tareaBusiness = new TareaBusiness();
        initComponents();
        this.analista = null;
        this.tarea = null;
        this.analistas = new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.clienteSingleton=ClienteSingleton.getInstance();
        
        GestionXML gestionXML=new GestionXML();
        this.clienteSingleton.enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("AllExaminadores", "")));
        agregarTareas();
    }//constructor

    //Busca entre el arrayList se usuarios solo a los analistas y descarta los otros roles
    private void solicitarAnalista() {
        Iterator<Examinador> iterator = this.analistas.iterator(); //se necesita un iterador para no alterar el índice
        while (iterator.hasNext()) {
            Examinador examinador = iterator.next();
            if (!examinador.getRol().equals("analista") || !examinador.isActivo()) {//si el examinador no es analista o no está activo
                iterator.remove(); // se elimina del arrayList
            }
        }
        for (Examinador examinador : this.analistas) {//recorre el array de analistas
            this.jcbAnalistas.addItem(examinador.getUser());//y llena el combobox con sus usuarios para ser seleccionados
        }
    }//solicitar

    private void agregarTareas() {
        this.tareas = tareaBusiness.obtenerTareas();//obtiene las tareas registradas
        Iterator<Tarea> iterator = this.tareas.iterator();//se necesita un iterador para no alterar el índice
        while (iterator.hasNext()) {
            Tarea tareaTemp = iterator.next();
            if (!tareaTemp.getAnalista().equals("null")) {//Si ya tiene un analista no puede volver a ser asignada 
                iterator.remove(); // y se elimina de la lista
            }
        }
        for (Tarea tareaTempo : this.tareas) {
            this.jcbTareas.addItem(tareaTempo.getUrl());
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
        jLabel2 = new javax.swing.JLabel();
        jbtnAsignar = new javax.swing.JButton();
        jcbAnalistas = new javax.swing.JComboBox<>();
        jcbTareas = new javax.swing.JComboBox<>();
        jbtnVolver = new javax.swing.JButton();

        setTitle("Asignar");

        jLabel1.setText("Seleccione el analista:");

        jLabel2.setText("Ingrese la tarea a asignar");

        jbtnAsignar.setText("Asignar");
        jbtnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAsignarActionPerformed(evt);
            }
        });

        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel2))
                    .addComponent(jcbTareas, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jbtnAsignar)
                        .addGap(67, 67, 67)
                        .addComponent(jbtnVolver))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jcbAnalistas, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jcbAnalistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jcbTareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAsignar)
                    .addComponent(jbtnVolver))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAsignarActionPerformed
        try {
            this.analista = this.analistas.get(this.jcbAnalistas.getSelectedIndex());//guarda el analista seleccionado
            this.tarea = this.tareas.get(this.jcbTareas.getSelectedIndex());//guarda la tarea
            boolean asignado = this.tareaBusiness.asignarAnalista(this.analista.getUser(), this.tarea.getUrl());//asigna el analista seleccionado a la tarea
            if (asignado) {//si se asignó muestra un mensaje
                JOptionPane.showMessageDialog(this, "Se asignó la tarea " + this.tarea.getUrl() + " al analista: " + this.analista.getUser());
                //Elimina de la lista de tareas, tareas que ya son asignadas
                this.tareas.remove(this.jcbTareas.getSelectedIndex());
                this.jcbTareas.removeItemAt(this.jcbTareas.getSelectedIndex());
            } else {//si no se pudo asignar envía un mensaje
                JOptionPane.showMessageDialog(this, "No se pudo asignar");
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo asignar, revise que se encuentren los datos completos para poder asignar");
        } catch (IOException ex) {
            Logger.getLogger(JIFAsignarTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnAsignarActionPerformed

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtnAsignar;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcbAnalistas;
    private javax.swing.JComboBox<String> jcbTareas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(String dato) {
        try {
            GestionXML gestionXML = new GestionXML();
            Element eProtocolo = gestionXML.stringToXML(dato);
            String accion = eProtocolo.getAttributeValue("accion");
            
 
            
            if (accion.equals("Examinadores")) {
                this.analistas=gestionXML.xmlAExaminadores(eProtocolo);
                solicitarAnalista();
               
            }
        } catch (JDOMException ex) {
            Logger.getLogger(JIFAsignarTarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JIFAsignarTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

