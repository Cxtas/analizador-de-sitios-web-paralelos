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
    private boolean analisis0;//0 -> Análisis de elementos que conforman un sitio web.
    private boolean analisis1;//1 -> Análisis de elementos y extracción. seleccionan img y/o enlaces
    private boolean analisis2;//2 -> Análisis de extracción y comparación
                             //se pueden seleccionar una,dos o todas
    private String url;
    private boolean imagenes;
    private boolean enlaces;

    public Tarea(int porcentajeAvance, String estado, String url) {
        this.porcentajeAvance = porcentajeAvance;
        this.estado = estado;
        this.analisis0 = false;
        this.analisis1 = false;
        this.analisis2 = false;
        this.url = url;
        this.imagenes = false;
        this.enlaces = false;
    }
    
    public Tarea(int porcentajeAvance, String estado, boolean analisis0,boolean analisis1,boolean analisis2, String url, boolean imagenes, boolean enlaces) {
        this.porcentajeAvance = porcentajeAvance;
        this.estado = estado;
        this.analisis0 = analisis0;
        this.analisis1 = analisis1;
        this.analisis2 = analisis2;
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

    public boolean isAnalisis0() {
        return analisis0;
    }

    public boolean isAnalisis1() {
        return analisis1;
    }

    public boolean isAnalisis2() {
        return analisis2;
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

    public void setAnalisis0(boolean analisis0) {
        this.analisis0 = analisis0;
    }

    public void setAnalisis1(boolean analisis1) {
        this.analisis1 = analisis1;
    }

    public void setAnalisis2(boolean analisis2) {
        this.analisis2 = analisis2;
    }
    
    public void setImagenes(boolean imagenes) {
        this.imagenes = imagenes;
    }

    public void setEnlaces(boolean enlaces) {
        this.enlaces = enlaces;
    }

    @Override
    public String toString() {
        return "Tarea{ Sitio: " +this.url+ "\nPorcentaje de avance = " + porcentajeAvance + "%, estado = " + estado + '}';
    }
    
}//fin clase
