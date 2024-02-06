/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;
import org.jdom.Element;

/**
 *
 * @author author
 */
public class Analisis {
    ArrayList<Element> data;
    ArrayList<Tarea> TareasAn;

    public Analisis() {
        data= new ArrayList<>();
        TareasAn=new ArrayList<>();
    }

    public ArrayList<Element> getElementos() {
        return data;
    }

    public void setElementos(ArrayList<Element> elementos) {
        this.data = elementos;
    }

    public ArrayList<Element> getData() {
        return data;
    }

    public void setData(ArrayList<Element> data) {
        this.data = data;
    }

    public ArrayList<Tarea> getTareasAn() {
        return TareasAn;
    }

    public void setTareasAn(ArrayList<Tarea> TareasAn) {
        this.TareasAn = TareasAn;
    }
    
}//fin clase
