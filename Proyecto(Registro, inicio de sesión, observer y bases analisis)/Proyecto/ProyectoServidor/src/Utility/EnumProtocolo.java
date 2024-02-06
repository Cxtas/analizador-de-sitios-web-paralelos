/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Utility;

import Domain.Cliente;
import org.jdom.Element;

/**
 *
 * @author Estephanie
 */
public enum EnumProtocolo {

    REGISTRAR {
        public void accion(Cliente cliente, Element eDatos) {
            
            
            cliente.enviarDatos("CREAR");

        }//acccion
    },
    VERIFICAR {
        public void accion(Cliente cliente, Element eDatos) {
            cliente.enviarDatos("CREAR");

        }//acccion
    },
    RECUPERAR {
        public void accion(Cliente cliente, Element eDatos) {
//            cliente.getPs().println(GestionXML.xmlToString(eEstudiante));
        }//accion
    };

    public abstract void accion(Cliente cliente, Element eDatos);

}//fin clase
