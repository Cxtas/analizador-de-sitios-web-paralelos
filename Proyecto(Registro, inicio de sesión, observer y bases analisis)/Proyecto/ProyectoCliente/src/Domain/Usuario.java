
package Domain;

/**
 *
 * @author Estephanie
 */
public class Usuario {
    
    private String user;
    private String contrasenia;
    private String tipo;

    public Usuario(String user, String contrasenia) {
        this.user = user;
        this.contrasenia = contrasenia;
    }

    public String getUser() {
        return user;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
