/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.util.ArrayList;

/**
 *
 * @author author
 */
public class Observable implements IObservable{
    private ArrayList<IObserver> observadores=new ArrayList<>();
    
    public void addObserver(IObserver o) {
        this.observadores.add(o);
    }//anade un observer

    @Override
    public void deleteObserver(IObserver o) {
        this.observadores.remove(o);
    }//elimina un observer

    @Override
    public void notifyObserver() {
        for(IObserver observer: observadores){
            observer.update();
        }//notifica todos los observers que tiene el cliente
    }
    
    
}
