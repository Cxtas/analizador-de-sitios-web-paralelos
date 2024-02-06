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
    
    
}
