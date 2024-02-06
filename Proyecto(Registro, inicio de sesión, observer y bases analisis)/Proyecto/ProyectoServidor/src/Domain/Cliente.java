/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Business.AdministradorBusiness;
import Business.ExaminadorBusiness;
import Utility.EnumProtocolo;
import Utility.GestionXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class Cliente extends Thread{
    
    private Socket socket;
    private BufferedReader leer;
    private PrintStream enviar;
    private boolean activo;

    public Cliente(Socket socket) throws IOException {
        this.socket = socket;
        this.leer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.enviar = new PrintStream(this.socket.getOutputStream());
        this.activo = true;
    }//constructor

    public void enviarDatos(String datos) {//Enviar datos al cliente
        this.enviar.println(datos);
    }
    
    public String leerDatos() throws IOException {//Leer las acciones enviadas por el cliente
        return this.leer.readLine();
    }
    
    @Override
    public void run(){
        try {
            enviarDatos("Este es el servidor");
            //Realizar acciones del cliente
            while (this.activo) {
                try {
                    
                    String accionC = leerDatos();
                    System.out.println(accionC);
                    Element eProtocolo;
                    try {
                        eProtocolo = GestionXML.stringToXML(accionC);
                        String accion = eProtocolo.getAttributeValue("accion");
                        
                        //Protocolos
                        if (accion.equals("autenticarA")) {
                            AdministradorBusiness administradorBusiness = new AdministradorBusiness();
                            Administrador administrador = GestionXML.xmlAAdministrador(eProtocolo);
                            System.out.println(administrador.toString());
                            String mensaje = "";
                            Administrador verificado = administradorBusiness.buscarAdministrador(administrador.getUser());
                            if (verificado != null) {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAdministrador("validado", verificado));
                            } else {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAccionSimple("validado", "null"));
                            }
                            System.out.println(mensaje);
                            enviarDatos(mensaje);
                        }
                        
                        if (accion.equals("autenticarE")) {
                            ExaminadorBusiness examinadorBusiness = new ExaminadorBusiness();
                            Examinador examinador = GestionXML.xmlAExaminador(eProtocolo);
                            System.out.println(examinador.toString());
                            String mensaje = "";
                            Examinador verificado = examinadorBusiness.buscarExaminador(examinador.getUser());
                            if (verificado != null) {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarExaminador("validado", verificado));
                            } else {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAccionSimple("validado", "null"));
                            }
                            System.out.println(mensaje);
                            enviarDatos(mensaje);
                        }
                   
                        if (accion.equals("guardarA")) {
                            
                            AdministradorBusiness administradorBusiness=  new AdministradorBusiness();
                            System.out.println(GestionXML.xmlAAdministrador(eProtocolo).toString());
                            boolean registrado = administradorBusiness.insertarAdministrador(GestionXML.xmlAAdministrador(eProtocolo));
                            System.out.println(registrado);
                            String mensaje = "";
                            if (registrado) {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAccionSimple("registrado", "si"));
                            } else {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAccionSimple("registrado", "no"));
                            }
                            this.enviarDatos(mensaje);
                        }
                        
                        if (accion.equals("guardarE")) {
                            
                            ExaminadorBusiness examinadorBusiness = new ExaminadorBusiness();
                            System.out.println(GestionXML.xmlAExaminador(eProtocolo).toString());
                            boolean registrado = examinadorBusiness.insertarExaminador(GestionXML.xmlAExaminador(eProtocolo));
                            String mensaje = "";
                            if (registrado) {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAccionSimple("registrado", "si"));
                            } else {
                                mensaje = GestionXML.xmlToString(GestionXML.agregarAccionSimple("registrado", "no"));
                            }
                            this.enviarDatos(mensaje);
                        }
                        
//                         if (accion.equals("chatPrivado")) {
//                            String personaEnviar=eProtocolo.getChild("dato").getAttributeValue("nombre");
//                            String mensaje = eProtocolo.getChild("dato").getValue();
//                             System.out.println(personaEnviar + mensaje);
//                            this.partida.notificadorEspecifico( personaEnviar, mensaje);
//                            
//                        }
//                        if (accion.equals("cerrar")) {
//                            this.activo=false;
//                            System.out.println("cliente cerrado");
//                            this.enviarDatos(GestionXML.xmlToString(GestionXML.crearMensajeProtocolo("cerrar")));
//                        }
                        
                    } catch (JDOMException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }//while
            
            this.socket.close();
            this.enviar.close();
            this.leer.close();
        }
        catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
}//clase
