/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.EnumProtocolo;
import Utility.GestionXML;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class ServidorSingleton extends Thread {

    private static ServidorSingleton servidorS;
    private ArrayList<Cliente> clientes;

    private ServidorSingleton() {
        this.clientes = new ArrayList<>();
    }//constructor

    public static ServidorSingleton getInstance() {
        if (servidorS == null) {
            servidorS = new ServidorSingleton();
            servidorS.start();
        }
        return servidorS;
    }

    @Override
    public void run() {
        try {
            //conectarCliente()
            ServerSocket server = new ServerSocket(5025);
            while (true) {
                try {
                    Socket socket = server.accept();
                    Cliente cliente = new Cliente(socket);
                    System.out.println("Cliente conectado?");
                    cliente.start();
                    this.clientes.add(cliente);
                    
                    if (this.clientes.size() > 0) {
                        for (int i = 0; i < clientes.size(); i++) {
                            if (this.clientes.get(i).getSocket().isClosed()) {
                                this.clientes.remove(this.clientes.get(i));
                            }//if
                        }//for
                    }//if
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServidorSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//run

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

}//clase
