/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
     public void cantidadElementos(String url) throws NoSuchAlgorithmException, KeyManagementException {
        ad.cantidadElementos(url);
    }
    
    //Análisis de elementos y extracción. seleccionan img y/o enlaces
    //Buscar y devolver enlaces
    public void extraerEnlaces(String url){
         ad.extraerEnlaces(url);
     }
    
    //descargar imágenes
    public void descargarImagen(String url) throws IOException {
        ad.descargarImagen(url);
    }
    
    //Buscar precios
    public void precios(String url) {
        ad.precios(url);
    }
    
    public Sitio getSitio() {
        return ad.getSitio();
    }
    
}//fin clase
