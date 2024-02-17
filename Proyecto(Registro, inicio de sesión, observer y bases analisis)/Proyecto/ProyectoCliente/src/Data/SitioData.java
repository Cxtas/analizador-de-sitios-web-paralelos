package Data;

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

        Element eaEnlaces = new Element("eanlaces");

        //for para añadir el array de enlaces al archivo
        for (int i = 0; i < sitio.getaEnlaces().size(); i++) {
            Element eaEnlace = new Element("enlace");
            eaEnlace.addContent(sitio.getaEnlaces().get(i));

            eaEnlaces.addContent(eaEnlace);
        }

        Element eProductos = new Element("productos");

        //for para añadir productos al archivo
        for (int i = 0; i < sitio.getProductos().size(); i++) {
            Element eProducto = new Element("producto");
            eProducto.addContent(sitio.getProductos().get(i));

            eProductos.addContent(eProducto);
        }

        Element ePrecios = new Element("precios");
        //for para añadir precios al archivo
        for (int i = 0; i < sitio.getPrecios().size(); i++) {
            Element ePrecio = new Element("precio");
            ePrecio.addContent(sitio.getPrecios().get(i));

            ePrecios.addContent(ePrecio);
        }

        eSitio.addContent(eURL);
        eSitio.addContent(eImagenes);
        eSitio.addContent(eEnlaces);
        eSitio.addContent(eVideos);
        eSitio.addContent(eTitulos);
        eSitio.addContent(eSubtitulos);
        eSitio.addContent(eTablas);
        eSitio.addContent(eaEnlaces);
        eSitio.addContent(eProductos);
        eSitio.addContent(ePrecios);

        this.root.addContent(eSitio);
        guardarXML();

        return true;
    }//guardarSitio

    //se obtienen todas los sitios
    public ArrayList<Sitio> obtenerSitios() {
         ArrayList<Sitio> sitios = new ArrayList<>();
        for (Object sitioa : this.root.getChildren("sitio")) {
            Element sitio=(Element) sitioa;
            Sitio nuevoSitio = new Sitio(
                sitio.getChildText("url"),
                Integer.parseInt(sitio.getChildText("imagenes")),
                Integer.parseInt(sitio.getChildText("enlaces")),
                Integer.parseInt(sitio.getChildText("videos")),
                Integer.parseInt(sitio.getChildText("titulos")),
                Integer.parseInt(sitio.getChildText("subtitulos")),
                Integer.parseInt(sitio.getChildText("tablas")),
                extraerArrayListDeElement(sitio.getChild("eanlaces").getChildren("enlace")),
                extraerArrayListDeElement(sitio.getChild("productos").getChildren("producto")),
                extraerArrayListDeElement(sitio.getChild("precios").getChildren("precio"))
            );
            sitios.add(nuevoSitio);
        }
        return sitios;
    }//obtenerSitios
    
    

    private ArrayList<String> extraerArrayListDeElement(List<Element> elementos) {
        ArrayList<String> lista = new ArrayList<>();
        for (Element elemento : elementos) {
            lista.add(elemento.getText());
        }
        return lista;
    }

    public Sitio obtenerSitio(String url) {

        ArrayList<Sitio> sitios= obtenerSitios();
        for (int i = 0; i < sitios.size(); i++) {
            if (sitios.get(i).getUrl().equals(url)) {
                return sitios.get(i);
            }
 
                
            
            
        }
        return null;
        
//
    }//obtenerSitio

}
