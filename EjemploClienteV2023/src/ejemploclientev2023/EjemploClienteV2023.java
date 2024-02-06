/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploclientev2023;

import Business.EstudianteBusinness;
import Domain.Direccion;
import Domain.Estudiante;
import Utility.GestionXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author catas
 */
public class EjemploClienteV2023 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JDOMException {
        try {
            Socket cliente= new Socket("10.54.237.168", 5025); //mismo puerto para cliente y servidor sino no funciona
            System.out.println("Cliente iniciado");
           
            BufferedReader br= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter pw= new PrintWriter(cliente.getOutputStream(), true);
            
//            pw.println("crear");
//            Estudiante e= new Estudiante("Catalina", 19, new Direccion("Paraiso", "Santiago"));
//           
//            Element eEstudiante= EstudianteBusinness.crearEstudiante(e);
//            pw.println(GestionXML.xmlToString(eEstudiante));
//                    pw.println(e.toString() );
//            System.out.println(br.readLine());
            
            pw.println("recuperar");
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine()); 
            
            
        } catch (IOException ex) {
            Logger.getLogger(EjemploClienteV2023.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// main 
    
} //fin class
