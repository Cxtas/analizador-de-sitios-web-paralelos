/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.TareaData;
import Domain.Tarea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class TareaBusiness {
    private TareaData tareaData;

    public TareaBusiness() {
        try {
            this.tareaData = new TareaData();
        } catch (JDOMException ex) {
            Logger.getLogger(TareaBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TareaBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public boolean insertarTareas(Tarea tarea) throws IOException {
         return this.tareaData.insertarTarea(tarea);
     }
     
      public ArrayList<Tarea> obtenerTareas() {
          return this.tareaData.obtenerTareas();
      }
}

