/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.ExaminadorData;
import Domain.Administrador;
import Domain.Examinador;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Estephanie
 */
public class ExaminadorBusiness {
    
    private ExaminadorData examinadorData;
    
    public ExaminadorBusiness() throws IOException, JDOMException{
        this.examinadorData = new ExaminadorData();
    }//constructor
    
    public boolean insertarExaminador(Examinador examinador) throws IOException{
        return this.examinadorData.insertarExaminador(examinador);
    }//registrarExaminador
    
    public ArrayList<Examinador> obtenerExaminadores(){
        return this.examinadorData.obtenerExaminadores();
    }//obtenerExaminadores
    
    public Examinador buscarExaminador(String user){
        ArrayList<Examinador> examinadores = obtenerExaminadores();
        for (int i = 0; i < examinadores.size(); i++) {
            if(examinadores.get(i).getUser().equals(user)){
                return examinadores.get(i);
            }
        }
        return null;
    }//obtenerAdministradores
    
    public boolean verificarUsuario(String nombreUsuario) {
        return this.examinadorData.verificarUsuario(nombreUsuario);
    }//verificar que los usuarios no se registren repetidos
    
    public boolean desactivarExaminador(Examinador examinador) throws IOException {
        return this.examinadorData.desactivarExaminador(examinador);
    }
    
}//clase
