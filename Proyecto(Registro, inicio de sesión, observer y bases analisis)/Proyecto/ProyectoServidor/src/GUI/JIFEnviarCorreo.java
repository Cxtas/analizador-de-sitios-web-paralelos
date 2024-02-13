package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *javax.swing.JInternalFrame
 * @author 
 */
public class JIFEnviarCorreo extends javax.swing.JInternalFrame {

    private static String emailFrom = "pprogramacion630@gmail.com";
    private static String passwordFrom = "wjxviujysawgmdtq";
    private ArrayList<String> emailTo = new ArrayList<>();
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    private ArrayList<File> mArchivosAdjuntos = new ArrayList<>();
    private String nombres_archivos;

    public JIFEnviarCorreo() {
        initComponents();
        mProperties = new Properties();
        nombres_archivos = "";
    }

    private void añadirDestinatario(){
        emailTo.add(jtfDestinatario.getText().trim());
    }
    private void creaEmail() {
        
        subject = jtfAsunto.getText().trim();
        content = jtfContenido.getText().trim();

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            MimeMultipart mElementosCorreo = new MimeMultipart();
            // Contenido del correo
            MimeBodyPart mContenido = new MimeBodyPart();
            mContenido.setContent(content, "text/html; charset=utf-8");
            mElementosCorreo.addBodyPart(mContenido);

            // Agregar archivos adjuntos si los hay
            if (!mArchivosAdjuntos.isEmpty()) {
                MimeBodyPart mAdjuntos = null;
                for (int i = 0; i < mArchivosAdjuntos.size(); i++) {
                    File archivo = mArchivosAdjuntos.get(i);
                    mAdjuntos = new MimeBodyPart();
                    mAdjuntos.setDataHandler(new DataHandler(new FileDataSource(archivo.getAbsolutePath())));
                    mAdjuntos.setFileName(archivo.getName());
                    mElementosCorreo.addBodyPart(mAdjuntos);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe adjuntar al menos un archivo.");
                return; // No enviar el correo si no hay archivos adjuntos
            }

            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            InternetAddress[] recipients = new InternetAddress[emailTo.size()];
            for (int i = 0; i < emailTo.size(); i++) {
                recipients[i] = new InternetAddress(emailTo.get(i));
            }
            mCorreo.setRecipients(Message.RecipientType.TO, recipients);
            mCorreo.setSubject(subject);
            mCorreo.setContent(mElementosCorreo);
            emailTo.clear();

        } catch (AddressException ex) {
            Logger.getLogger(JIFEnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JIFEnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarCorreo() {
        try {
            if (mCorreo == null) {
                JOptionPane.showMessageDialog(null, "Error al crear el correo.");
                return;
            }

            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");
            lblAdjuntos.setText("");
            nombres_archivos = "";
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JIFEnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JIFEnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
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
        jbtnSendEmail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfDestinatario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfAsunto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtfContenido = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        lblAdjuntos = new javax.swing.JLabel();
        jbtnAdjuntos = new javax.swing.JButton();
        jbtnAñadir = new javax.swing.JButton();

        jbtnSendEmail.setText("Enviar correo");
        jbtnSendEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSendEmailActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Envio de correo ");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Enviar a:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Asunto:");

        jtfAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfAsuntoActionPerformed(evt);
            }
        });

        jtfContenido.setColumns(20);
        jtfContenido.setRows(5);
        jScrollPane1.setViewportView(jtfContenido);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Contenido:");

        lblAdjuntos.setForeground(new java.awt.Color(255, 255, 255));
        lblAdjuntos.setText("....");

        jbtnAdjuntos.setText("Agregar archivos adjuntos");
        jbtnAdjuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdjuntosActionPerformed(evt);
            }
        });

        jbtnAñadir.setText("Añadir");
        jbtnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAñadirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnSendEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfAsunto)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAdjuntos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnAdjuntos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jtfDestinatario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnAñadir)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jbtnAñadir))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAdjuntos)
                .addGap(18, 18, 18)
                .addComponent(lblAdjuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnSendEmail)
                .addGap(27, 27, 27))
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

    private void jbtnSendEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSendEmailActionPerformed
        creaEmail();
        enviarCorreo();
    }//GEN-LAST:event_jbtnSendEmailActionPerformed

    private void jtfAsuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfAsuntoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfAsuntoActionPerformed

    private void jbtnAdjuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdjuntosActionPerformed
          JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (chooser.showOpenDialog(this) != JFileChooser.CANCEL_OPTION) {
            File[] selectedFiles = chooser.getSelectedFiles();
            for (int i = 0; i < selectedFiles.length; i++) {
                File archivo = selectedFiles[i];
                mArchivosAdjuntos.add(archivo);
                nombres_archivos += archivo.getName() + "<br>";
            }

            lblAdjuntos.setText("<html><p>" + nombres_archivos + "</p></html>");
        }
    }//GEN-LAST:event_jbtnAdjuntosActionPerformed

    private void jbtnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAñadirActionPerformed
        añadirDestinatario();
        jtfDestinatario.setText("");
    }//GEN-LAST:event_jbtnAñadirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAdjuntos;
    private javax.swing.JButton jbtnAñadir;
    private javax.swing.JButton jbtnSendEmail;
    private javax.swing.JTextField jtfAsunto;
    private javax.swing.JTextArea jtfContenido;
    private javax.swing.JTextField jtfDestinatario;
    private javax.swing.JLabel lblAdjuntos;
    // End of variables declaration//GEN-END:variables
}
