/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;

/**
 *
 * @author author
 */
public class Tarea {
    
    private int porcentajeAvance;
    private String estado;
    private String tipoAnalisis;//0 -> Análisis de elementos que conforman un sitio web.
                            //1 -> Análisis de elementos y extracción. seleccionan img y/o enlaces
                            //2 -> Análisis de extracción y comparación
    private String url;
    private boolean imagenes;
    private boolean enlaces;

    public Tarea(int porcentajeAvance, String estado, String tipoAnalisis, String url) {
        this.porcentajeAvance = porcentajeAvance;
        this.estado = estado;
        this.tipoAnalisis = tipoAnalisis;
        this.url = url;
        this.imagenes = false;
        this.enlaces = false;
    }
    
    public Tarea(int porcentajeAvance, String estado, String tipoAnalisis, String url, boolean imagenes, boolean enlaces) {
        this.porcentajeAvance = porcentajeAvance;
        this.estado = estado;
        this.tipoAnalisis = tipoAnalisis;
        this.url = url;
        this.imagenes = imagenes;
        this.enlaces = enlaces;
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

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public String getUrl() {
        return url;
    }

    public boolean isImagenes() {
        return imagenes;
    }

    public boolean isEnlaces() {
        return enlaces;
    }

    public void setImagenes(boolean imagenes) {
        this.imagenes = imagenes;
    }

    public void setEnlaces(boolean enlaces) {
        this.enlaces = enlaces;
    }

    @Override
    public String toString() {
        return "Tarea{" + "porcentajeAvance=" + porcentajeAvance + ", estado=" + estado + '}';
    }
    
}//fin clase
