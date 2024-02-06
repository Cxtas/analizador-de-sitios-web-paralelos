/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author catas
 */
public class Direccion {
    private String canton;
    private String distrito;
    private 

    public Direccion(String canton, String distrito) {
        this.canton = canton;
        this.distrito = distrito;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return "Direccion{" + "canton=" + canton + ", distrito=" + distrito + '}';
    }
    
    
}// fin class
