/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.SitioData;
import Domain.Sitio;
import java.io.IOException;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class SitioBusiness {
    private SitioData sitioData;

    public SitioBusiness() throws IOException, JDOMException {
        this.sitioData = new SitioData();
    }
    
    //guarda los datos analizados de un sitio
     public boolean guardarSitio(Sitio sitio) throws IOException {
         return this.sitioData.guardarSitio(sitio);
     }//guardar

     public Sitio obtenerSitio(String url) {
         return this.sitioData.obtenerSitio(url);
     }
}
