/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import Domain.Administrador;
import Domain.Examinador;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Estephanie
 */
public class GestionXML {

    public static Element stringToXML(String stringMensaje) throws JDOMException, IOException {

        SAXBuilder saxBuilder = new SAXBuilder();

        StringReader stringReader = new StringReader(stringMensaje);

        Document doc = saxBuilder.build(stringReader);

        return doc.getRootElement();

    } // stringToXML   

    public static String xmlToString(Element element) {

        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());

        String xmlStringElement = outputter.outputString(element);

        xmlStringElement = xmlStringElement.replace("\n", "");

        return xmlStringElement;

    } // xmlToString

    public static Element administradorAXML(Administrador administrador) throws IOException {
        Element eAdministrador = new Element("administrador");//<administrador>

        Element eUser = new Element("user");
        eUser.addContent(administrador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(administrador.getContrasenia());
        
        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(administrador.getTipoUsuario());

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
        eAdministrador.addContent(eTipoUsuario);

        return eAdministrador;
    }//crearElAdministrador

    public static Administrador xmlAAdministrador(Element element) {
        Element eAdmin=element.getChild("dato").getChild("administrador");
        Administrador admin = new Administrador(eAdmin.getChild("user").getValue(), eAdmin.getChild("contrasenia").getValue(),
                                                eAdmin.getChild("tipoUsuario").getValue(), Boolean.parseBoolean(eAdmin.getChild("activo").getValue()));
        return admin;
    }//crearAdministrador

     //agrega una lista de administradores al protocolo.
    public static Element agregarAdministradores(String accion, ArrayList<Administrador> administradores) {
        Element edato = new Element("dato");

        for (int i = 0; i < administradores.size(); i++) {
            Element eAdministrador = new Element("administrador");

        Element eUser = new Element("user");
        eUser.addContent(administradores.get(i).getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(administradores.get(i).getContrasenia());

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
        edato.addContent(eAdministrador);

        }

        Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarAdministradores
    
    public static Element examinadorAXML(Examinador examinador) throws IOException {
        Element eExaminador = new Element("examinador");//<examinador>

        Element eUser = new Element("user");
        eUser.addContent(examinador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(examinador.getContrasenia());

        Element eRol = new Element("rol");
        eRol.addContent(examinador.getRol());
        
        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(examinador.getTipoUsuario());
        
        Element eActivo = new Element("activo");
        eActivo.addContent(String.valueOf(examinador.isActivo()));

        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
        eExaminador.addContent(eTipoUsuario);
        eExaminador.addContent(eActivo);

        return eExaminador;
    }//crearElExaminador

    public static Examinador xmlAExaminador(Element element) {
        Element eExaminador=element.getChild("dato").getChild("examinador");
        Examinador examinador = new Examinador(eExaminador.getChild("user").getValue(), 
                                               eExaminador.getChild("contrasenia").getValue(),
                                               eExaminador.getChild("rol").getValue(),
                                               eExaminador.getChild("tipoUsuario").getValue(),
                                               Boolean.parseBoolean(eExaminador.getChild("activo").getValue()));
        return examinador;
    }//crearExaminador
    
        //agrega una lista de examinadores al protocolo.
    public static Element agregarExaminadores(String accion, ArrayList<Examinador> examinadores) {
        Element edato = new Element("dato");

        for (int i = 0; i < examinadores.size(); i++) {
        Element eExaminador = new Element("examinador");//<examinador>

        Element eUser = new Element("user");
        eUser.addContent(examinadores.get(i).getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(examinadores.get(i).getContrasenia());

        Element eRol = new Element("rol");
        eRol.addContent(examinadores.get(i).getRol());

        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
        edato.addContent(eExaminador);

        }

        Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarExaminadores

    //crea el mensaje principal de los protocolos
    public static Element crearMensajeProtocolo(String accion) {

        Element eProtocolo = new Element("Protocolo");
        eProtocolo.setAttribute("accion", accion);

        return eProtocolo;
    }//crearMensajeProtocolo

    public static Element agregarAdministrador(String accion, Administrador administrador) {
        Element edato = new Element("dato");

        Element eAdministrador = new Element("administrador");//<administrador>

        Element eUser = new Element("user");
        eUser.addContent(administrador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(administrador.getContrasenia());
        
        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(administrador.getTipoUsuario());
        
        Element eActivo = new Element("activo");
        eActivo.addContent(String.valueOf(administrador.isActivo()));

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
        eAdministrador.addContent(eTipoUsuario);
        eAdministrador.addContent(eActivo);
        edato.addContent(eAdministrador);

        Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarAdministrador

    public static Element agregarExaminador(String accion, Examinador examinador) {
        Element edato = new Element("dato");

         Element eExaminador = new Element("examinador");//<examinador>

        Element eUser = new Element("user");
        eUser.addContent(examinador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(examinador.getContrasenia());

        Element eRol = new Element("rol");
        eRol.addContent(examinador.getRol());
        
        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(examinador.getTipoUsuario());
        
        Element eActivo = new Element("activo");
        eActivo.addContent(String.valueOf(examinador.isActivo()));

        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
        eExaminador.addContent(eTipoUsuario);
        eExaminador.addContent(eActivo);
        edato.addContent(eExaminador);

        Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarExaminador
    
       //agrega una accion al protocolo
        public static Element agregarAccionSimple(String accion,String dato){
        Element edato= new Element("dato");
        edato.addContent(dato);
      
        Element eProtocolo=crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarAccionSimple

}
