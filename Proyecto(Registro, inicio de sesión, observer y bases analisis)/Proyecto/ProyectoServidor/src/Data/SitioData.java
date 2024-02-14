/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Domain.ProductoServicio;
import Domain.Sitio;
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
public class SitioData {
    private Document document;
    private Element root;

    public SitioData() throws IOException, JDOMException {
        File f = new File(Ruta.RUTASITIOS);
        if (f.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);//Ignora los espacios en blanco y toma todo el elemento, si no, el espacio lo toma como separaciones de elementos
            this.document = saxBuilder.build(Ruta.RUTASITIOS);
            this.root = this.document.getRootElement();
        } else {
            this.root = new Element("sitios");
            this.document = new Document(this.root);
            guardarXML();
        }
    }//constructor
    
    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(Ruta.RUTASITIOS));
    }//guaradarXML
    
    //guarda los datos analizados de un sitio
     public boolean guardarSitio(Sitio sitio) throws IOException {
        Element eSitio = new Element("sitio");
        
        Element eURL = new Element("url");
        eURL.addContent(sitio.getUrl());

        Element eImagenes = new Element("imagenes");
        eImagenes.addContent(String.valueOf(sitio.getImagenes()));

        Element eEnlaces = new Element("enlaces");
        eEnlaces.addContent(String.valueOf(sitio.getEnlaces()));
        
        Element eVideos = new Element("videos");
        eVideos.addContent(String.valueOf(sitio.getVideos()));
        
        Element eTitulos = new Element("titulos");
        eTitulos.addContent(String.valueOf(sitio.getTitulos()));
        
        Element eSubtitulos = new Element("subtitulos");
        eSubtitulos.addContent(String.valueOf(sitio.getSubtitulos()));
        
        Element eTablas = new Element("tablas");
        eTablas.addContent(String.valueOf(sitio.getTablas()));
        
        Element eProductos = new Element("productos");

        //for para añadir productos al archivo
        for (int i = 0; i < sitio.getProductos().size(); i++) {
            Element eProducto = new Element("producto");

            Element eNombre = new Element("nombre");
            eNombre.addContent(sitio.getProductos().get(i).getNombre());

            Element ePrecio = new Element("precio");
            ePrecio.addContent(sitio.getProductos().get(i).getPrecio());

            eProducto.addContent(eNombre);
            eProducto.addContent(ePrecio);
            eProductos.addContent(eProducto);
        }
        
        Element eaEnlaces = new Element("eanlaces");

        //for para añadir el array de enlaces al archivo
        for (int i = 0; i < sitio.getaEnlaces().size(); i++) {
            Element eaEnlace = new Element("enlace");
            eaEnlace.addContent(sitio.getaEnlaces().get(i));

            eaEnlace.addContent(eaEnlace);
        }
        
        eSitio.addContent(eURL);
        eSitio.addContent(eImagenes);
        eSitio.addContent(eEnlaces);
        eSitio.addContent(eVideos);
        eSitio.addContent(eTitulos);
        eSitio.addContent(eSubtitulos);
        eSitio.addContent(eTablas);
        eSitio.addContent(eProductos);
        eSitio.addContent(eaEnlaces);

        this.root.addContent(eSitio);
        guardarXML();

        return true;
     }//guardarSitio
    
      //se obtienen todas los sitios
    public ArrayList<Sitio> obtenerSitios() {
        ArrayList<Sitio> sitios = new ArrayList<>();
        ArrayList<ProductoServicio> productos = new ArrayList<>();

        List eSitios = this.root.getChildren();
        
        for (Object objetoActual : eSitios) {
            Element eActual = (Element) objetoActual;
            //buscar los productos del sitio
             List listaProductos = eActual.getChild("productos").getChildren();
                for (Object object1 : listaProductos) {
                    Element productoActual = (Element) object1;

                    ProductoServicio producto = new ProductoServicio(productoActual.getChild("nombre").getValue(), productoActual.getChild("precio").getValue());
                    productos.add(producto);
                }
                
                //buscar los enlaces del sitio
                ArrayList<String> aEnlaces = new ArrayList<>();
                List listaEnlaces = eActual.getChild("enlaces").getChildren();
                for (Object object1 : listaEnlaces) {
                    Element enlaceActu = (Element) object1;

                    aEnlaces.add(enlaceActu.getChild("enlace").getValue());
                }
                
            Sitio sitio = new Sitio(eActual.getChild("url").getValue(), Integer.parseInt(eActual.getChild("imagenes").getValue()),
                    Integer.parseInt(eActual.getChild("enlaces").getValue()),Integer.parseInt(eActual.getChild("videos").getValue()),
                    Integer.parseInt(eActual.getChild("titulos").getValue()),Integer.parseInt(eActual.getChild("subtitulos").getValue()),
                    Integer.parseInt(eActual.getChild("tablas").getValue()), productos, aEnlaces);
            sitios.add(sitio);
        }//for
        return sitios;
    }//obtenerAdmins
 
     public Sitio obtenerSitio(String url) {
        ArrayList<ProductoServicio> productos = new ArrayList<>();
        List elementList = this.root.getChildren();
        
        for (Object object : elementList) {
            Element eSitioTemp = (Element) object;
            
            //busca productos
            List listaProductos = eSitioTemp.getChild("productos").getChildren();
                for (Object object1 : listaProductos) {
                    Element productoActual = (Element) object1;

                    ProductoServicio producto = new ProductoServicio(productoActual.getChild("nombre").getValue(), productoActual.getChild("precio").getValue());
                    productos.add(producto);
                }
                
                //buscar los enlaces del sitio
                ArrayList<String> aEnlaces = new ArrayList<>();
                List listaEnlaces = eSitioTemp.getChild("enlaces").getChildren();
                for (Object object1 : listaEnlaces) {
                    Element enlaceActu = (Element) object1;

                    aEnlaces.add(enlaceActu.getChild("enlace").getValue());
                }
                
            Sitio sitio = new Sitio(eSitioTemp.getChild("url").getValue(), Integer.parseInt(eSitioTemp.getChild("imagenes").getValue()),
                    Integer.parseInt(eSitioTemp.getChild("enlaces").getValue()),Integer.parseInt(eSitioTemp.getChild("videos").getValue()),
                    Integer.parseInt(eSitioTemp.getChild("titulos").getValue()),Integer.parseInt(eSitioTemp.getChild("subtitulos").getValue()),
                    Integer.parseInt(eSitioTemp.getChild("tablas").getValue()), productos, aEnlaces);
            if (sitio.getUrl().equals(url)) {
                return sitio;
            }
            
        }//for 

        return null;
        
    }//obtenerSitio
    
}
