/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public interface Observable {
     

    void addObserver (Observer observer);

     void removeObserver(Observer observer);
     
     void notifyObservers(String data);
}
