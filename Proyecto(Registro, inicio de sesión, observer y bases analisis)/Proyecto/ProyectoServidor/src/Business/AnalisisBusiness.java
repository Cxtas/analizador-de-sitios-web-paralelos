/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.AnalisisData;
import Domain.Sitio;

/**
 *
 * @author author
 */
public class AnalisisBusiness {
    private AnalisisData ad;
    
    
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
