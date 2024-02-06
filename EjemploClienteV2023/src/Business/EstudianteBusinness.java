/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.EstudianteData;
import Domain.Estudiante;
import java.io.IOException;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author catas
 */
public class EstudianteBusinness {

 
 public static Element crearEstudiante(Estudiante estudiante) {
     EstudianteData ed= new EstudianteData();
return ed.crearEstudiante(estudiante);
 }
}
