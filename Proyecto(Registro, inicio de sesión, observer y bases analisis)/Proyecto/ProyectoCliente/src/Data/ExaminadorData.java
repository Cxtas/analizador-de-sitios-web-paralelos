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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

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
        File f1 = new File(Ruta.RUTAURLS);
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
        
        if(f1.exists()){
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);//Ignora los espacios en blanco y toma todo el elemento, si no, el espacio lo toma como separaciones de elementos
            this.documentU = saxBuilder.build(Ruta.RUTAURLS);
            this.rootU = this.documentU.getRootElement();
        }else{
            this.rootU = new Element("examinadores");
            this.documentU = new Document(this.rootU);
            guardarXML();
        }
        
    }//constructor
    
    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(Ruta.RUTAEXAMINADOR));
    }//guaradarXML
    
    public boolean insertarExaminador(Examinador examinador) throws IOException{
        
        ArrayList<Examinador> examinadores = obtenerExaminadores();
        
        if (examinadores.size()>0) {
            boolean repetido = verificarUsuario(examinador.getUser());
            if (repetido) {
                return false;
            }
        }
        
        Element eExaminador = new Element("examinador");//<administrador>
            Element eUser =  new Element("user");
            eUser.addContent(examinador.getUser());

            Element eContrasenia =  new Element("contrasenia");
            eContrasenia.addContent(examinador.getContrasenia());
            
            Element eRol =  new Element("rol");
            eContrasenia.addContent(examinador.getRol());

        
        eExaminador.addContent(eUser);
        eExaminador.addContent(eContrasenia);
        eExaminador.addContent(eRol);
    
        this.root.addContent(eExaminador);
        guardarXML();
        
        return true;
    }
    
    public ArrayList<Examinador> obtenerExaminadores(){
        ArrayList<Examinador> examinadores = new ArrayList<>();
        
        List eExaminadores = this.root.getChildren();
        for (Object objetoActual : eExaminadores) {
            Element eActual = (Element) objetoActual;
            Examinador examinador = new Examinador(eActual.getChild("user").getValue(), 
                                                   eActual.getChild("contrasenia").getValue(), 
                                                   eActual.getChild("rol").getValue());
            examinadores.add(examinador);
        }//for
        return examinadores;
    }//obtenerExaminador
    
    public boolean insertarURL(String url) throws IOException{
        
        ArrayList<String> urls = obtenerURLS();

        Element eLURLs = new Element("urls");//<urls>
        Element eURL = new Element("url");
 
        eLURLs.addContent(eURL);

        this.rootU.addContent(eLURLs);
        guardarXML();

        return true;
    }
    
    public ArrayList<String> obtenerURLS(){
        ArrayList<String> urls = new ArrayList<>();

        List eURLs = this.rootU.getChildren();
        for (String urlActual : urls) {
            //Sitio sitio = new Sitio(urlActual); //crear sitios(?)
            urls.add(urlActual);
        }//for
        return urls;


    }//obtenerExaminador
    
    public boolean verificarUsuario(String nombreUsuario) {
        List elementList = this.root.getChildren();
        
        for (Object object : elementList) {
            Element eElementTemp = (Element) object;
             Examinador examinador = new Examinador(eElementTemp.getChild("user").getValue(), eElementTemp.getChild("contrasenia").getValue(), eElementTemp.getChild("rol").getValue());
            if (examinador.getUser().equals(nombreUsuario)) {
                return true;
            }
            
        }
        
        return false;
    }//verificarUsuario
    
    public void asignarTarea(Examinador e, Tarea tarea){//metodo que usan los gestores
        e.verificarRol();
        if(e.getRol().equals("analista")){
            e.getTareas().add(tarea);
        }else{
            JOptionPane.showMessageDialog(null, "Solo se pueden asignar tareas a los analistas");
        }
    }//asignarTarea - Lo usa el gestor
    
    public void registrarURL(String url) throws IOException{
        Element eRuta = new Element("ruta");//<ruta>
    
        this.rootU.addContent(eRuta);
        guardarXML();
    }//registrarURL - lo usa el digitador
    
}//fin clase
