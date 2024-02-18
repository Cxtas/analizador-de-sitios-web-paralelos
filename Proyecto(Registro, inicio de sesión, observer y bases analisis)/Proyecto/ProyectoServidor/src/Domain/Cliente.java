/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Business.AdministradorBusiness;
import Business.ExaminadorBusiness;
import Utility.GestionXML;
import Utility.MyUtil;
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
 * @author Diego
 */
public class Cliente extends Thread {

    private Socket socket;
    private PrintStream enviar;
    private BufferedReader leer;

    private boolean activo;

    public Cliente(Socket socket) throws IOException {

        this.socket = socket;
        this.enviar = new PrintStream(this.socket.getOutputStream());
        this.leer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.activo = true;

    }
    //este hilo se encarga de escuchar lo que pide el cliente y resuelve sus peticiones mediante acciones estabblecidas.

    public void run() {

        try {
            enviarDatos("hola soy el servidor");
            GestionXML gestionXML = new GestionXML();
            while (this.activo) {

                String peticion = leerDatos();
                Element eProtocolo;

                try {
                    eProtocolo = gestionXML.stringToXML(peticion);
                    String accion = eProtocolo.getAttributeValue("accion");

                    //PROTOCOLOSS
                    if (accion.equals("autenticarAdministrador")) {
                        Administrador administrador = gestionXML.xmlAAdministrador(eProtocolo);
                        AdministradorBusiness administradorBusiness=new AdministradorBusiness();
                        Administrador a=administradorBusiness.verificarUsuario(administrador.getUser(),administrador.getContrasenia());
                        if (a!=null) {
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAdministrador("validadoA", a)));
                        }else{
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("denegado", "s")));
                        }
                    }
                    
                    if (accion.equals("autenticarExaminador")) {
                        Examinador examinador = gestionXML.xmlAExaminador(eProtocolo);
                        ExaminadorBusiness examinadorBusiness=new ExaminadorBusiness();
                        Examinador e=examinadorBusiness.buscarExaminador(examinador.getUser(),examinador.getContrasenia());
                        if (e!=null) {
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarExaminador("validadoE", e)));
                        }else{
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("denegado", "s")));
                        }
                    }
                    
                    if (accion.equals("AllExaminadores")) {
                        ExaminadorBusiness examinadorBusiness=new ExaminadorBusiness();
                        ArrayList<Examinador> examinadores= examinadorBusiness.obtenerExaminadores();
                        enviarDatos(gestionXML.xmlToString(gestionXML.agregarExaminadores("Examinadores", examinadores)));
                    }
                    
                     if (accion.equals("AllAdministradores")) {
                        AdministradorBusiness administradorBusiness=new AdministradorBusiness();
                        ArrayList<Administrador> administradores=administradorBusiness.obtenerAdministradores();
                        enviarDatos(gestionXML.xmlToString(gestionXML.agregarAdministradores("Administradores", administradores)));
                    }
                    
                    if (accion.equals("eliminarAdministrador")) {
                        System.out.println(peticion);
                        Administrador administrador = gestionXML.xmlAAdministrador(eProtocolo);
                        AdministradorBusiness administradorBusiness=new AdministradorBusiness();
                       boolean a=administradorBusiness.desactivarAdministrador(administrador);
                       System.out.println(a);
                        if (a) {
                            enviarDatos(gestionXML.xmlToString(GestionXML.agregarAccionSimple("eliminado", "")));
                        }else{
                            enviarDatos(gestionXML.xmlToString(GestionXML.agregarAccionSimple("falloEliminacion", "")));
                        }
                    }
                    
                     if (accion.equals("eliminarExaminador")) {
                         System.out.println(peticion);
                         Examinador examinador = gestionXML.xmlAExaminador(eProtocolo);
                        ExaminadorBusiness examinadorBusiness=new ExaminadorBusiness();
                        boolean e=examinadorBusiness.desactivarExaminador(examinador);
                         System.out.println(e);
                        if (e) {
                             enviarDatos(gestionXML.xmlToString(GestionXML.agregarAccionSimple("eliminado", "")));
                        }else{
                             enviarDatos(gestionXML.xmlToString(GestionXML.agregarAccionSimple("falloEliminacion", "")));
                        }
                    }
                     if (accion.equals("registrarAdministrador")) {
                        Administrador administrador = gestionXML.xmlAAdministrador(eProtocolo);
                        AdministradorBusiness administradorBusiness=new AdministradorBusiness();
                        boolean a=administradorBusiness.insertarAdministrador(administrador);
                        if (a) {
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("RegistroExitoso", "s")));
                        }else{
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("falloRegistro", "s")));
                        }
                    }
                     
                       if (accion.equals("registrarExaminador")) {
                       Examinador examinador=gestionXML.xmlAExaminador(eProtocolo);
                        ExaminadorBusiness examinadorBusiness=new ExaminadorBusiness();
                        boolean a=examinadorBusiness.insertarExaminador(examinador);
                        if (a) {
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("RegistroExitoso", "s")));
                        }else{
                            enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("falloRegistro", "s")));
                        }
                    }
                    
                } catch (JDOMException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }//while

            this.socket.close();
            this.enviar.close();
            this.leer.close();
        } //run //run
        catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //este metodo se encarga de enviar datos al cliente
    public void enviarDatos(String datos) {
        this.enviar.println(datos);
    }

    //se encarga de leer las peticiones del cliente.
    public String leerDatos() throws IOException {
        return this.leer.readLine();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}//fin clase
