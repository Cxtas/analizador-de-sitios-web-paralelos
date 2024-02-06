/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente;

import Domain.ClienteSingleton;
import GUI.JFLogging;

/**
 *
 * @author Estephanie
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteSingleton cliente = ClienteSingleton.getInstance();
        if (cliente != null) {
            JFLogging ventana = new JFLogging();
            ventana.setLocation(250, 70);
            ventana.setVisible(true);
        }
    }

}
