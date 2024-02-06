/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import Domain.Administrador;
import Domain.Examinador;
import java.io.IOException;
import java.io.StringReader;
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

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);

        return eAdministrador;
    }//crearElAdministrador

    public static Administrador xmlAAdministrador(Element element) {
        Element eAdmin=element.getChild("dato").getChild("administrador");
        Administrador admin = new Administrador(eAdmin.getChild("user").getValue(), eAdmin.getChild("contrasenia").getValue());
        return admin;
    }//crearAdministrador

    public static Element examinadorAXML(Examinador examinador) throws IOException {
        Element eExaminador = new Element("examinador");//<examinador>

        Element eUser = new Element("user");
        eUser.addContent(examinador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(examinador.getContrasenia());

        Element eRol = new Element("rol");
        eRol.addContent(examinador.getRol());

        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);

        return eExaminador;
    }//crearElExaminador

    public static Examinador xmlAExaminador(Element element) {
        Element eExaminador=element.getChild("dato").getChild("examinador");
        Examinador examinador = new Examinador(eExaminador.getChild("user").getValue(), 
                                               eExaminador.getChild("contrasenia").getValue(),
                                               eExaminador.getChild("rol").getValue());
        return examinador;
    }//crearExaminador

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

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
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

        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
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
