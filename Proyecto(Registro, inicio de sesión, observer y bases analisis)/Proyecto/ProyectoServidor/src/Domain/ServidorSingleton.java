/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.GestionXML;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ServidorSingleton extends Thread {

    private static ServidorSingleton servidor;
    private ArrayList<Cliente> clientes;
    private int numeroPuerto;

    private ServidorSingleton() {

        this.clientes = new ArrayList<>();
        this.numeroPuerto = 8080;
    }//constructor

    public static ServidorSingleton getInstance() {
        if (servidor == null) {
            servidor = new ServidorSingleton();
            servidor.start();
        }

        return servidor;
    }

    //el hilo se usa para crear los clientes del servidor y guardarlos con su respectivo socket cuando establecen conexion.
    @Override
    public void run() {
        try {
            ServerSocket serverSocket
                    = new ServerSocket(this.numeroPuerto);

            while (true) {

                Socket socket = serverSocket.accept();
                Cliente cliente = new Cliente(socket);
                cliente.start();
                this.clientes.add(cliente);

                if (this.clientes.size() > 0) {
                    for (int i = 0; i < clientes.size(); i++) {
                        if (this.clientes.get(i).getSocket().isClosed()) {
                            this.clientes.remove(this.clientes.get(i));
                        }

                    }
                }

            }
        } //run
        catch (IOException ex) {
            Logger.getLogger(ServidorSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//run

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

 
  //envia la solicitud de amistad al cliente que se desea.
    

}//fin clase
