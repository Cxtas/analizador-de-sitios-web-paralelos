/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author Estephanie
 */
public class Usuario {
    
    private String user;
    private String contrasenia;
    private String tipoUsuario;
    private boolean activo;

    public Usuario(String user, String contrasenia, String tipoUsuario, boolean activo) {
        this.user = user;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
        this.activo = activo;
    }

    public String getUser() {
        return user;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "user=" + user + ", contrasenia=" + contrasenia + ", tipoUsuario=" + tipoUsuario + ", activo=" + activo + '}';
    }

}
