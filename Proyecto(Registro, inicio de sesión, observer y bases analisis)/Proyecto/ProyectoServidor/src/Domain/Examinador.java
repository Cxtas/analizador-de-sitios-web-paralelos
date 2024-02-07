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
    private ArrayList<Tarea> tareas;
    private ArrayList<Sitio> sitios;
    private String rol;
    
    public Examinador(String user, String contrasenia, String rol, String tipoUsuario, boolean activo) {
        super(user, contrasenia, tipoUsuario, activo);
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
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
    
}//fin clase
