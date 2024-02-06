/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

/**
 *
 * @author author
 */
public class Observer implements IObserver{

    @Override
    public void update() {
        System.out.println("Se llamo al observer");
    }
    
}
