/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.AdministradorData;
import Domain.Administrador;
import Domain.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class AdministradorBusiness {
     private AdministradorData administradorData;
    
    public AdministradorBusiness() throws IOException, JDOMException{
        this.administradorData = new AdministradorData();
    }//constructor
    
    public boolean insertarAdministrador(Administrador administrador) throws IOException{
        return this.administradorData.insertarAdministrador(administrador);
    }//registrarAdministrador
    
    public ArrayList<Administrador> obtenerAdministradores(){
        return this.administradorData.obtenerAdministradores();
    }//obtenerAdministradores
    
    public Administrador buscarAdministrador(String user){
        ArrayList<Administrador> administradores = obtenerAdministradores();
        for (int i = 0; i < administradores.size(); i++) {
            if(administradores.get(i).getUser().equals(user)){
                return administradores.get(i);
            }
        }
        return null;
    }//buscar
    
    public boolean verificarUsuario(String nombreUsuario) {
        return this.administradorData.verificarUsuario(nombreUsuario);
    }//verificar que los usuarios no se registren repetidos
    
    public boolean desactivarAdministrador(Administrador administrador) throws IOException {
        return this.administradorData.desactivarAdministrador(administrador);
    }
}
