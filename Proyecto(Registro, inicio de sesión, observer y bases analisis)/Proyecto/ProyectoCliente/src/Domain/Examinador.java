/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Business.AnalisisBusiness;
import java.util.ArrayList;

/**
 *
 * @author Estephanie
 */
public class Examinador extends Usuario{
    private ArrayList<Sitio> sitios;
    private ArrayList<Tarea> tareas;
    private String rol;
    
    public Examinador(String user, String contrasenia, String rol) {//para el resgistro
        super(user, contrasenia);
        this.rol = rol;
    }
    
    public Examinador(String user, String contrasenia) {//Para el inicio de sesión
        super(user, contrasenia);
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public ArrayList<Sitio> getSitios() {
        return sitios;
    }

    public void setSitios(ArrayList<Sitio> sitios) {
        this.sitios = sitios;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    public void verificarRol(){
        if(this.rol.equals("analista"))
            this.tareas= new ArrayList<>();
            new AnalisisBusiness();
        if(this.rol.equals("gestor"))
            this.tareas= new ArrayList<>();
        if(this.rol.equals("digitador"))    
            this.sitios=new ArrayList<>();
    }//rol
    
}
