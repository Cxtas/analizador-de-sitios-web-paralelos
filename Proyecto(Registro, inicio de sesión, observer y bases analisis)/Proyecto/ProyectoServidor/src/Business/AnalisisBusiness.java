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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;

/**
 *
 * @author author
 */
public class AnalisisBusiness {
    private AnalisisData ad;

//    public AnalisisBusiness(String url) {
//        try {
//            this.ad = new AnalisisData(url);
//        } catch (JDOMException ex) {
//            Logger.getLogger(AnalisisBusiness.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(AnalisisBusiness.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    public void CantElementos(){
        ad.CantElementos();
    }
    
    public void BuscarElementos(String tipo){ //busca imagenes o enlaces
        ad.BuscarElementos(tipo);
    }
    
    public void ExtraerElementos(Sitio sitio){
        ad.ExtraerElementos(sitio);
    }
    
}//fin clase
