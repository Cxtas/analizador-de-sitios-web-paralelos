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
import java.util.List;
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

    public static Element administradorAXML(String accion,Administrador administrador) throws IOException {
        Element eDato=new Element("dato");
        Element eAdministrador = new Element("administrador");//<administrador>

        Element eUser = new Element("user");
        eUser.addContent(administrador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(administrador.getContrasenia());
        
        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(administrador.getTipoUsuario());
        
        Element eActivo=new Element("activo");
        eActivo.addContent(String.valueOf(administrador.isActivo()));

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
        eAdministrador.addContent(eTipoUsuario);
        eAdministrador.addContent(eActivo);

        eDato.addContent(eAdministrador);
        
         Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(eDato);
        return eProtocolo;
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
        
        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(administradores.get(i).getTipoUsuario());
        
        Element eActivo=new Element("activo");
        eActivo.addContent(String.valueOf(administradores.get(i).isActivo()));

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
         eAdministrador.addContent(eTipoUsuario);
        eAdministrador.addContent(eActivo);
         edato.addContent(eAdministrador);

        }

        Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarAdministradores
    
       public static ArrayList<Examinador> xmlAExaminadores(Element element){
         ArrayList<Examinador> examinadores=new ArrayList<>();
          List<Element> examinadorElements = element.getChildren("examinador");

            for (Element examinadorElement : examinadorElements) {
                String user = examinadorElement.getChildText("user");
                String contrasenia = examinadorElement.getChildText("contrasenia");
                String rol = examinadorElement.getChildText("rol");
                String tipoUsuario=examinadorElement.getChildText("tipoUsuario");
                boolean activo= Boolean.parseBoolean(String.valueOf(examinadorElement.getChild("activo")));

                System.out.println(activo);
                Examinador examinador = new Examinador(user, contrasenia, rol,tipoUsuario ,activo);
                examinadores.add(examinador);
            }
        

        return examinadores;
    }
      
       public static ArrayList<Administrador> xmlAAdministradores(Element element){
        ArrayList<Administrador> administradores=new ArrayList<>();
        List EAdminList=element.getChildren();
        
        for (Object object : EAdminList) {
            Element adminActual=(Element) object;
            
            Administrador administrador=new Administrador(adminActual.getChild("user").getValue(),
                    adminActual.getChild("contrasenia").getValue(), 
                    adminActual.getChild("tipoUsuario").getValue(), 
                    Boolean.parseBoolean(String.valueOf(adminActual.getChild("activo").getValue())));
            
            administradores.add(administrador);
            
        }
        return administradores;
    }
    
     public static Element examinadorAXML(String accion,Examinador examinador) throws IOException {
        Element eDato=new Element("dato");
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

         eDato.addContent(eExaminador);
        
         Element eProtocolo = crearMensajeProtocolo(accion);
        eProtocolo.addContent(eDato);
        return eProtocolo;
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
        
         Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(examinadores.get(i).getTipoUsuario());
        
        Element eActivo = new Element("activo");
        eActivo.addContent(String.valueOf(examinadores.get(i).isActivo()));

        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
        eExaminador.addContent(eActivo);
        eExaminador.addContent(eTipoUsuario);
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
