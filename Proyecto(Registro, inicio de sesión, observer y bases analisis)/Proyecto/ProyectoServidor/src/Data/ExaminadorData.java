/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Domain.Examinador;
import Domain.Sitio;
import Domain.Tarea;
import Utility.Ruta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Estephanie
 */
public class ExaminadorData {
    private Document document;
    private Element root;
    
    private Document documentU;
    private Element rootU;
    
    
    public ExaminadorData() throws IOException, JDOMException {
         File f = new File(Ruta.RUTAEXAMINADOR);
        if(f.exists()){
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);//Ignora los espacios en blanco y toma todo el elemento, si no, el espacio lo toma como separaciones de elementos
            this.document = saxBuilder.build(Ruta.RUTAEXAMINADOR);
            this.root = this.document.getRootElement();
        }else{
            this.root = new Element("examinadores");
            this.document = new Document(this.root);
            guardarXML();
        }
    }//constructor
    
    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(Ruta.RUTAEXAMINADOR));
    }//guaradarXML
    
public boolean insertarExaminador(Examinador examinador) throws IOException{
        
        ArrayList<Examinador> examinadores = obtenerExaminadores();//obtiene los examinadores registrados
        
        if (examinadores.size()>0) {//si hay examinadores
            boolean repetido = verificarUsuario(examinador.getUser());//verifica que antes de registrar no se repita el user
            if (repetido) {
                return false;
            }
        }
        
        //se registran en el xml una vez verificados
        Element eExaminador = new Element("examinador");
            Element eUser =  new Element("user");
            eUser.addContent(examinador.getUser());

            Element eContrasenia =  new Element("contrasenia");
            eContrasenia.addContent(examinador.getContrasenia());
            
            Element eRol =  new Element("rol");
            eRol.addContent(examinador.getRol());
            
            Element eTipoUsuario =  new Element("tipoUsuario");
            eTipoUsuario.addContent(examinador.getTipoUsuario());
            
            Element eActivo =  new Element("activo");
            eActivo.addContent(String.valueOf(examinador.isActivo()));

        
        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
        eExaminador.addContent(eTipoUsuario);
        eExaminador.addContent(eActivo);
    
        this.root.addContent(eExaminador);
        guardarXML();
        
        return true;
    }
    
    //se obtienen todos los examinadores del xml
   public ArrayList<Examinador> obtenerExaminadores(){
        ArrayList<Examinador> examinadores = new ArrayList<>();
        
        List eExaminadores = this.root.getChildren();
        for (Object objetoActual : eExaminadores) {
            Element eActual = (Element) objetoActual;
            Examinador examinador = new Examinador(eActual.getChild("user").getValue(), 
                                                   eActual.getChild("contrasenia").getValue(), 
                                                   eActual.getChild("rol").getValue(),
                                                   eActual.getChild("tipoUsuario").getValue(),
                                                   Boolean.parseBoolean(eActual.getChild("activo").getValue()));
            examinadores.add(examinador);
        }//for
        return examinadores;
    }//obtenerExaminador
    
   //verifica que no se registren examinadores con el mismo nombre de usuario
    public boolean verificarUsuario(String nombreUsuario) {
        List elementList = this.root.getChildren();
        
        for (Object object : elementList) {
            Element eElementTemp = (Element) object;
             Examinador examinador = new Examinador(eElementTemp.getChild("user").getValue(), eElementTemp.getChild("contrasenia").getValue(), 
                                                    eElementTemp.getChild("rol").getValue(), eElementTemp.getChild("tipoUsuario").getValue(),
                                                    Boolean.parseBoolean(eElementTemp.getChild("activo").getValue()));
            if (examinador.getUser().equalsIgnoreCase(nombreUsuario) && examinador.isActivo()) {
                return true;
            }
            
        }
        
        return false;
    }
        //se cambia en el xml el atributo activo de true a false
        public boolean desactivarExaminador(Examinador examinador) throws IOException {
        List elementList = this.root.getChildren();
        boolean hecho = false;
        for (Object object : elementList) {
            Element eUsuarioActual = (Element) object;
            if (eUsuarioActual.getChild("user").getValue().equals(examinador.getUser())) {
                eUsuarioActual.getChild("activo").removeContent();
                eUsuarioActual.getChild("activo").addContent(String.valueOf(examinador.isActivo()));
                hecho=true;
            }
        }//for
        if (hecho) {
            this.guardarXML();
            return true;
        }
        return false;
    }//desactivar
    
//    public void asignarTarea(Examinador e, Tarea tarea){//metodo que usan los gestores
//        e.verificarRol();
//        if(e.getRol().equals("analista")){
//            e.getTareas().add(tarea);
//        }else{
//            JOptionPane.showMessageDialog(null, "Solo se pueden asignar tareas a los analistas");
//        }
//    }//asignarTarea - Lo usa el gestor
    
//    public void registrarURL(String url) throws IOException{
//        Element eRuta = new Element("ruta");//<ruta>
//    
//        this.rootU.addContent(eRuta);
//        guardarXML();
//    }//registrarURL - lo usa el digitador
    
    public void verEstadisticas(Sitio sitio){
        
        
        
    }//verEstadisticas - lo usa el digitador
    
    
    
}//fin clase
