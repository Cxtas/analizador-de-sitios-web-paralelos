
package Domain;

import java.util.ArrayList;

/**
 *
 * @author author
 */
public class Sitio {
    
    private String url;
    private int imagenes;
    private int enlaces;
    private int videos;
    private int titulos;
    private int subtitulos;
    private int tablas;
    ArrayList <ProductoServicio> productos;

    public Sitio(String url) {
        this.imagenes = 0;
        this.enlaces = 0;
        this.videos = 0;
        this.titulos = 0;
        this.subtitulos = 0;
        this.tablas = 0;
        this.productos=new ArrayList<>();
        this.url = url;
    }

    public Sitio(String url,int imagenes, int enlaces, int videos, int titulos, int subtitulos, int tablas ) {//ArrayList<ProductoServicio> productos
        this.url = url;
        this.imagenes = imagenes;
        this.enlaces = enlaces;
        this.videos = videos;
        this.titulos = titulos;
        this.subtitulos = subtitulos;
        this.tablas = tablas;
        //this.productos = productos;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public ArrayList<ProductoServicio> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoServicio> productos) {
        this.productos = productos;
    }
    
    public int getImagenes() {
        return imagenes;
    }

    public void setImagenes(int imagenes) {
        this.imagenes = imagenes;
    }

    public int getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(int enlaces) {
        this.enlaces = enlaces;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public int getTitulos() {
        return titulos;
    }

    public void setTitulos(int titulos) {
        this.titulos = titulos;
    }

    public int getSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(int subtitulos) {
        this.subtitulos = subtitulos;
    }

    public int getTablas() {
        return tablas;
    }

    public void setTablas(int tablas) {
        this.tablas = tablas;
    }
    
}//fin clase
