package Domain;

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
        this.tareas = new ArrayList<>();
    }

    public String getRol() {
        return rol;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }
    
    public void addTareas(Tarea tarea){
        this.tareas.add(tarea);
    }
    
    
}//fin clase
