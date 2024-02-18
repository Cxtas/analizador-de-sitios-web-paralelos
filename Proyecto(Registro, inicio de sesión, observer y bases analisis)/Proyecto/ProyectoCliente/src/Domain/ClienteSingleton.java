/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.GestionXML;
import Utility.Observable;
import Utility.Observer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

public class ClienteSingleton implements Runnable,Observable{

    private static ClienteSingleton clienteSingleton;
    private ArrayList<Observer> observers=new ArrayList<>();
    
    private Socket socket;
    private InetAddress direccion;
    private PrintStream enviar;
    private BufferedReader leer;

    private boolean abierto;
    

    private ClienteSingleton() throws IOException {
        this.direccion = InetAddress.getByName("localhost");
        this.socket = new Socket(this.direccion, 8080);
        this.enviar = new PrintStream(this.socket.getOutputStream());
        this.leer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
       
        this.abierto = true;
       

    }//constructor
    
    @Override
     public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
     public static ClienteSingleton getInstance() {
        if (clienteSingleton == null) {
            try {
                clienteSingleton = new ClienteSingleton();
            } catch (IOException ex) {
                Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);

            }
            Thread thread = new Thread(clienteSingleton);
            thread.start();
        }
        return clienteSingleton;
    }
    
     @Override
    public void run() {
       
        try {
            GestionXML gestionXML = new GestionXML();
            while (this.abierto) {
                
               
                try {
                    String Peticion = this.leerDatos();
                    System.out.println(Peticion);
                    
                    if (Peticion.startsWith("<Protocolo")) {
                        notifyObservers(Peticion);
                        
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.socket.close();
            this.enviar.close();
            this.leer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      public void enviarDatos(String datos) {
        this.enviar.println(datos);
    }

    public String leerDatos() throws IOException {
        return this.leer.readLine();
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}
