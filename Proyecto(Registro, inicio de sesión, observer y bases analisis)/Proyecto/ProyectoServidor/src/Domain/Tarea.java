/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author author
 */
public class Tarea {
    private int porcentajeAvance;
    private String estado;

    public Tarea(int porcentajeAvance, String estado) {
        this.porcentajeAvance = porcentajeAvance;
        this.estado = estado;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea{" + "porcentajeAvance=" + porcentajeAvance + ", estado=" + estado + '}';
    }
    
}//fin clase
