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

   
    public void ExtraerElementos(String url) throws NoSuchAlgorithmException, KeyManagementException{
        ad.ExtraerElementos(url);
    }
    
}//fin clase
