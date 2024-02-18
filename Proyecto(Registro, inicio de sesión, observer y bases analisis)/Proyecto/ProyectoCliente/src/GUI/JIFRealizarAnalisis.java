package GUI;

import Business.AnalisisBusiness;
import Business.SitioBusiness;
import Business.TareaBusiness;
import Domain.Sitio;
import Domain.Tarea;
import Domain.Usuario;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class JIFRealizarAnalisis extends javax.swing.JInternalFrame implements Runnable {

    /**
     * Creates new form JIFRealizarAnalisis
     */
    private AnalisisBusiness analisisBusiness;
    private TareaBusiness tareaBusiness;
    private SitioBusiness sitioBusiness;
    private ArrayList<Tarea> tareas;
    private Usuario usuario;
    private Tarea tareaSelected;
    private Sitio sitio;

    public JIFRealizarAnalisis(Usuario usuario) throws IOException, JDOMException {//permite saber cuál es el usuario que inició sesión
        this.usuario = usuario;//se guarda el usuario
        this.tareaBusiness = new TareaBusiness();
        this.sitioBusiness = new SitioBusiness();
        this.tareas = new ArrayList<>();
        this.sitio = null;
        initComponents();
        this.tareas = this.tareaBusiness.obtenerTareas();//se obtienen las tareas registradas
        this.tareaSelected = this.tareas.get(0);//inicia en la primera tarea
        agregarTareas();//se agregan las tareas al combobox
        Thread thread = new Thread(this);
        thread.start();//se inicia el hilo para cambiar de tarea

    }

    //Al ser un hilo permite darse cuenta al momento que se selecciona otra tarea permitiendo cambiar el detalle en tiempo real
    @Override
  public void run() {
    while (true) {
        Object selectedItem = jcbTareasPendientes.getSelectedItem();
        if (selectedItem != null) {
            this.tareaSelected = this.tareaBusiness.buscarTarea(selectedItem.toString());
            actualizarDetalle();
        }
    }
    }

    //Agrega las tareas que pertenecen al analista que inició sesión al combobox
    private void agregarTareas() {
        Iterator<Tarea> iterator = this.tareas.iterator();
        while (iterator.hasNext()) {
            Tarea tarea = iterator.next();
            if ((!tarea.getAnalista().equals(this.usuario.getUser())) || !tarea.getEstado().equals("en proceso")) {//Si la tarea no tiene el analista que inició sesión y si no está en proceso ya está asignada
                iterator.remove(); // se elimina la tarea del arrayList
            }
        }
        for (Tarea tarea : this.tareas) {
            this.jcbTareasPendientes.addItem(tarea.getUrl());
        }

    }//asignarTareas

    //Se cambia el el textArea la información de cada tarea
    private void actualizarDetalle() {
        this.jtaDetalleTarea.setText("URL: " + tareaSelected.getUrl() + "\n"
                + "Análisis de elementos que conforman un sitio web: " + tareaSelected.isAnalisis0() + "\n"
                + "Análisis de elementos y extracción: " + tareaSelected.isAnalisis1() + "\n"
                + "Imágenes: " + tareaSelected.isImagenes() + "\n"
                + "Enlaces: " + tareaSelected.isEnlaces() + "\n"
                + "Análisis de extracción y comparación: " + tareaSelected.isAnalisis2() + "\n");
    }

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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaResultado = new javax.swing.JTextArea();

        setTitle("Análisis");

        jLabel1.setText("Elegir tarea:");

        jcbTareasPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTareasPendientesActionPerformed(evt);
            }
        });

        jbtnAnalizar.setText("Analizar");
        jbtnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAnalizarActionPerformed(evt);
            }
        });

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

        jLabel4.setText("Elementos encontrados:");

        jtaResultado.setColumns(20);
        jtaResultado.setRows(5);
        jScrollPane2.setViewportView(jtaResultado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jcbTareasPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnAnalizar)
                .addGap(98, 98, 98)
                .addComponent(jbtnVolver)
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbTareasPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAnalizar)
                    .addComponent(jbtnVolver))
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtnVolverActionPerformed

    private void jbtnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAnalizarActionPerformed
        try {
            boolean hecho1 = true, hecho2 = true, hecho3 = true, hecho4 = true;

            jtaResultado.setText("");
            this.analisisBusiness = new AnalisisBusiness();

            if (this.tareaSelected.isAnalisis0()) { //Análisis de elementos que conforman un sitio web.
              
                
                hecho1 = this.analisisBusiness.cantidadElementos(this.tareaSelected.getUrl());//Crea un sitio y le asigna la cantidad de los elementos de este tipo de analisis
//                System.out.println(hecho1);
                if (hecho1) {
                    this.sitio = this.analisisBusiness.getSitio();
                    System.out.println(this.sitio);
                    this.jtaResultado.append("Enlaces: " + this.sitio.getEnlaces() + "\n"
                            + "Imagenes: " + this.sitio.getImagenes() + "\n"
                            + "Titulos: " + this.sitio.getTitulos() + "\n"
                            + "Subtitulos: " + this.sitio.getSubtitulos() + "\n"
                            + "Tablas: " + this.sitio.getTablas() + "\n"
                            + "Videos: " + this.sitio.getVideos() + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo contar los elementos");
                }
            }//analisis1
            
            if (this.tareaSelected.isAnalisis1()) {//Análisis de elementos y extracción. seleccionan img y/o enlaces
                if (this.tareaSelected.isImagenes()) {//si se pidió imagenes
                    hecho2 = this.analisisBusiness.descargarImagen(this.tareaSelected.getUrl());
                    if (hecho2) {
                        JOptionPane.showMessageDialog(this, "Se descargaron las imagenes, revise la carpeta con el nombre de la pagina.");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo descargar las imagenes o no las hay");
                    }
                }//imagenes

                if (this.tareaSelected.isEnlaces()) {//si se pidieron enlaces
                    hecho3 = this.analisisBusiness.extraerEnlaces(this.tareaSelected.getUrl());//Crea un sitio y le asigna la cantidad de los elementos de este tipo de analisis
                    if (hecho3) {
                        this.sitio = this.analisisBusiness.getSitio();
                        this.jtaResultado.append("Enlaces: " + this.sitio.getaEnlaces() + "\n");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo extraer los enlaces o no se encontraron.");
                    }
                }//enlaces

            }//analisis2

            if (this.tareaSelected.isAnalisis2()) {//Análisis de extracción y comparación
                hecho4 = this.analisisBusiness.precios(this.tareaSelected.getUrl());
                if (hecho4) {
                    this.sitio = this.analisisBusiness.getSitio();
                    this.jtaResultado.append("Productos: " + this.sitio.getProductos() + "\n");
                    this.jtaResultado.append("Precios: " + this.sitio.getPrecios()+ "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo extraer los productos o la pagina no contiene");
                }
            }//analisis3

            if (hecho1 && hecho2 && hecho3 && hecho4) {
            if (!this.tareas.isEmpty() && jcbTareasPendientes.getSelectedIndex() >= 0 && 
                    jcbTareasPendientes.getSelectedIndex() < this.tareas.size()) {
                this.tareas.remove(jcbTareasPendientes.getSelectedIndex());
                jcbTareasPendientes.removeItemAt(jcbTareasPendientes.getSelectedIndex());
                jtaResultado.setText("");
            }
            this.tareaBusiness.cambiarEstado(this.tareaSelected.getUrl());
            this.sitioBusiness.guardarSitio(this.sitio);//guarda en xml los datos del sitio
        }
        } catch (NullPointerException ex) {
            System.out.println("Error " + ex);
        } catch (IOException ex) {
            Logger.getLogger(JIFRealizarAnalisis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JIFRealizarAnalisis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(JIFRealizarAnalisis.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jbtnAnalizarActionPerformed

    private void jcbTareasPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTareasPendientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTareasPendientesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnAnalizar;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcbTareasPendientes;
    private javax.swing.JTextArea jtaDetalleTarea;
    private javax.swing.JTextArea jtaResultado;
    // End of variables declaration//GEN-END:variables
}
