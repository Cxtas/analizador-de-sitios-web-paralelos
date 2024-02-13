/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Domain.Examinador;
import Domain.Tarea;
import Utility.Ruta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Estephanie
 */
public class TareaData {
    private Document document;
    private Element root;

    public TareaData() throws JDOMException, IOException {
         File f = new File(Ruta.RUTATAREAS);
        if (f.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);//Ignora los espacios en blanco y toma todo el elemento, si no, el espacio lo toma como separaciones de elementos
            this.document = saxBuilder.build(Ruta.RUTATAREAS);
            this.root = this.document.getRootElement();
        } else {
            this.root = new Element("tareas");
            this.document = new Document(this.root);
            guardarXML();
        }
    }//constructor
    
    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(Ruta.RUTATAREAS));
    }//guaradarXML

    //se registran tareas
     public boolean insertarTarea(Tarea tarea) throws IOException {

        Element eTarea = new Element("tarea");
        
        Element eAvance = new Element("porcentajeAvance");
        eAvance.addContent(String.valueOf(tarea.getPorcentajeAvance()));

        Element eEstado = new Element("estado");
        eEstado.addContent(tarea.getEstado());

        Element eAnalisis0 = new Element("analisis0");
        eAnalisis0.addContent(String.valueOf(tarea.isAnalisis0()));
        
        Element eAnalisis1 = new Element("analisis1");
        eAnalisis1.addContent(String.valueOf(tarea.isAnalisis1()));
        
        Element eAnalisis2 = new Element("analisis2");
        eAnalisis2.addContent(String.valueOf(tarea.isAnalisis2()));

        Element eURL = new Element("url");
        eURL.addContent(tarea.getUrl());
        
        Element eImgs = new Element("imagenes");
        eImgs.addContent(String.valueOf(tarea.isImagenes()));
        
        Element eEnlaces = new Element("enlaces");
        eEnlaces.addContent(String.valueOf(tarea.isEnlaces()));
        
        Element eAnalista = new Element("analista");
        eAnalista.addContent(tarea.getAnalista());

        eTarea.addContent(eAvance);
        eTarea.addContent(eEstado);
        eTarea.addContent(eAnalisis0);
        eTarea.addContent(eAnalisis1);
        eTarea.addContent(eAnalisis2);
        eTarea.addContent(eURL);
        eTarea.addContent(eImgs);
        eTarea.addContent(eEnlaces);
        eTarea.addContent(eAnalista);

        this.root.addContent(eTarea);
        guardarXML();

        return true;
    }

     //se obtienen todas las tareas
    public ArrayList<Tarea> obtenerTareas() {
        ArrayList<Tarea> tareas = new ArrayList<>();

        List eTareas = this.root.getChildren();
        for (Object objetoActual : eTareas) {
            Element eActual = (Element) objetoActual;
            Tarea tarea = new Tarea(Integer.parseInt(eActual.getChild("porcentajeAvance").getValue()), eActual.getChild("estado").getValue(),
                    Boolean.parseBoolean(eActual.getChild("analisis0").getValue()),Boolean.parseBoolean(eActual.getChild("analisis1").getValue()),
                    Boolean.parseBoolean(eActual.getChild("analisis2").getValue()),eActual.getChild("url").getValue(),
                    Boolean.parseBoolean(eActual.getChild("imagenes").getValue()),Boolean.parseBoolean(eActual.getChild("enlaces").getValue()),
                    eActual.getChild("analista").getValue());
            tareas.add(tarea);
        }//for
        return tareas;
    }//obtenerAdmins
    
    //se les asigna un analista cambiando el atributo analista de null al user del analista asignado
     public boolean asignarAnalista(String userAnalista, String url) throws IOException {
        List elementList = this.root.getChildren();
        boolean hecho = false;
        for (Object object : elementList) {
            Element eTareaActual = (Element) object;
            if (eTareaActual.getChild("url").getValue().equals(url)) {
                eTareaActual.getChild("analista").removeContent();
                eTareaActual.getChild("analista").addContent(userAnalista);
                hecho=true;
            }
        }//for
        if (hecho) {
            this.guardarXML();
            return true;
        }
        return false;
    }//desactivar
}
