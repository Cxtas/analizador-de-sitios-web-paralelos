
package Domain;

import Utility.GestionXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class ClienteSingleton extends Observable implements Runnable {
    
    private static ClienteSingleton clienteSingleton;
    private Socket socket;
    private BufferedReader leer;
    private PrintStream enviar;
    private String registrado;
    private boolean abierto;
    private boolean verificado;

    private ClienteSingleton() throws IOException {
        this.socket = new Socket("127.0.0.1", 5025);
        this.leer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.enviar = new PrintStream(this.socket.getOutputStream());
        this.registrado = "no";
        this.abierto = true;
        this.verificado = false;
    }
    
    public static ClienteSingleton getInstance() {
        if (clienteSingleton == null) {
            try {
                clienteSingleton = new ClienteSingleton();
            } catch (IOException ex) {
                Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);

            }
            Thread thread = new Thread(clienteSingleton);
            thread.start();
        }
        return clienteSingleton;
    }
    
     public void enviarDatos(String datos) {
        this.enviar.println(datos);
    }

    public String leerDatos() throws IOException {
        return this.leer.readLine();
    }
    
    @Override
    public void run(){
        try {
            while (this.abierto) {
                try {
                    String accionS = this.leerDatos();
                    System.out.println(accionS);
                    try {
                        if (accionS.startsWith("<Protocolo")) {
                            
                            
                            Element eProtocolo = GestionXML.stringToXML(accionS);
                            String accion = eProtocolo.getAttributeValue("accion");
                            
                            System.out.println(eProtocolo);
                            if (accion.equals("validado")) {
                                this.setChanged();
                                System.out.println(eProtocolo);
                                this.notifyObservers(eProtocolo);
                                this.clearChanged();
                            }
                            if (accion.equals("registrado")) {
                                this.registrado=eProtocolo.getChild("dato").getValue();
                                this.verificado=true;
                                
                            }
                            
//                            if (accion.equals("chat")) {
//                                this.chat=eProtocolo.getChild("dato").getValue();
//                                this.prueba=true;
//                                
//                            }
//                            if (accion.equals("chatPrivado")) {
//                                this.chatPrivado=eProtocolo.getChild("dato").getValue();
//                                this.mensajePrivado=true;
//                                
//                            }
                            
//                            if (accion.equals("confirmacionEnvio")) {
//                                this.registrado=eProtocolo.getChild("dato").getValue();
//                                this.verificado=true;
//                                
//                            }
                            
                            if (accion.equals("cerrar")) {
                                this.abierto=false;
                            }
                        }
                        
                    } catch (JDOMException ex) {
                        Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            this.socket.close();
            this.enviar.close();
            this.leer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//run

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getLeer() {
        return leer;
    }

    public PrintStream getEnviar() {
        return enviar;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public String getRegistrado() {
        return registrado;
    }

    public void setRegistrado(String registrado) {
        this.registrado = registrado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
    
    
    
}//clase
