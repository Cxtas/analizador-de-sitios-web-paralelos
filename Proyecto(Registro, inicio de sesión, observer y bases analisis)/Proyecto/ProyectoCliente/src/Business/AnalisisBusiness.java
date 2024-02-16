
package Business;

import Data.AnalisisData;
import Domain.Sitio;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author author
 */
public class AnalisisBusiness {
    private AnalisisData ad;

    public AnalisisBusiness() {
        this.ad = new AnalisisData();
    }
    
    //Análisis de elementos que conforman un sitio web.
     public boolean cantidadElementos(String url) throws NoSuchAlgorithmException, KeyManagementException {
        return ad.cantidadElementos(url);
    }
    
    //Análisis de elementos y extracción. seleccionan img y/o enlaces
    //Buscar y devolver enlaces
    public boolean extraerEnlaces(String url){
        return ad.extraerEnlaces(url);
     }
    
    //descargar imágenes
    public boolean descargarImagen(String url) throws IOException {
       return ad.descargarImagen(url);
    }
    
    //Buscar precios
    public boolean precios(String url) throws NoSuchAlgorithmException, KeyManagementException {
       return ad.precios(url);
    }
    
    public Sitio getSitio() {
        return ad.getSitio();
    }
    
}//fin clase
