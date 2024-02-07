/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

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

     public boolean insertarTarea(Tarea tarea) throws IOException {

        Element eTarea = new Element("tarea");
        
        Element eAvance = new Element("porcentajeAvance");
        eAvance.addContent(String.valueOf(tarea.getPorcentajeAvance()));

        Element eEstado = new Element("estado");
        eEstado.addContent(tarea.getEstado());

        Element eTipoAnalisis = new Element("tipoAnalisis");
        eTipoAnalisis.addContent(tarea.getTipoAnalisis());

        Element eURL = new Element("url");
        eURL.addContent(tarea.getUrl());
        
        Element eImgs = new Element("imagenes");
        eImgs.addContent(String.valueOf(tarea.isImagenes()));
        
        Element eEnlaces = new Element("enlaces");
        eEnlaces.addContent(String.valueOf(tarea.isEnlaces()));

        eTarea.addContent(eAvance);
        eTarea.addContent(eEstado);
        eTarea.addContent(eTipoAnalisis);
        eTarea.addContent(eURL);
        eTarea.addContent(eImgs);
        eTarea.addContent(eEnlaces);

        this.root.addContent(eTarea);
        guardarXML();

        return true;
    }

    public ArrayList<Tarea> obtenerTareas() {
        ArrayList<Tarea> tareas = new ArrayList<>();

        List eTareas = this.root.getChildren();
        for (Object objetoActual : eTareas) {
            Element eActual = (Element) objetoActual;
            Tarea tarea = new Tarea(Integer.parseInt(eActual.getChild("porcentajeAvance").getValue()), eActual.getChild("estado").getValue(),
                    eActual.getChild("tipoAnalisis").getValue(), eActual.getChild("url").getValue(),Boolean.parseBoolean(eActual.getChild("imagenes").getValue()),
                    Boolean.parseBoolean(eActual.getChild("enlaces").getValue()));
            tareas.add(tarea);
        }//for
        return tareas;
    }//obtenerAdmins
    
}
