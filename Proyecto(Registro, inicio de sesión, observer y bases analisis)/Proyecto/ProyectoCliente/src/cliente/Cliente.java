/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente;

import Domain.ClienteSingleton;
import GUI.JFLogging;
import java.io.IOException;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class Cliente {

    public static void main(String[] args) throws IOException, JDOMException {
        ClienteSingleton cliente = ClienteSingleton.getInstance();
        if (cliente != null) {
            JFLogging ventana = new JFLogging();
            cliente.addObserver(ventana);
            ventana.setVisible(true);
        }

    }
}
