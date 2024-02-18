
package Business;

import Data.ExaminadorData;
import Domain.Examinador;
import java.io.IOException;
import java.util.ArrayList;
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
    
    public Examinador buscarExaminador(String user, String contrasenia){
        ArrayList<Examinador> examinadores = obtenerExaminadores();
        for (int i = 0; i < examinadores.size(); i++) {
            if(examinadores.get(i).getUser().equals(user) && examinadores.get(i).getContrasenia().equals(contrasenia)){
                return examinadores.get(i);
            }
        }
        return null;
    }//buscar examinador
    
    public boolean verificarUsuario(String nombreUsuario) {
        return this.examinadorData.verificarUsuario(nombreUsuario);
    }//verificar que los usuarios no se registren repetidos
    
    public boolean desactivarExaminador(Examinador examinador) throws IOException {
        return this.examinadorData.desactivarExaminador(examinador);
    }//desactivar administrador
    
}//clase
