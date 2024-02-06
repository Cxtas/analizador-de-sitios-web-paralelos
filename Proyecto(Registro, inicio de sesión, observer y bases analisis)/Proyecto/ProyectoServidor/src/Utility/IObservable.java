/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

/**
 *
 * @author author
 */
public interface IObservable {
    //observable administra los observers
    
    void addObserver(IObserver o);
    
    void deleteObserver(IObserver o);
    
    void notifyObserver();
    
}//fin clase
