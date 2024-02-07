/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Domain.Analisis;
import Utility.GestionXML;
import Domain.ProductoServicio;
import Domain.Sitio;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jdom.JDOMException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author author
 */
public class AnalisisData {
    private GestionXML manejo;
    private Analisis a;
    //private String url;
    private Sitio sitio;
    private int cont;
    private ArrayList<Sitio> sitios;
    
    public AnalisisData(String url) throws JDOMException, IOException {
        a= new Analisis();
        //this.url=url;
        this.manejo= new GestionXML();
        this.sitio= new Sitio(url);
    }
    
    public void CantElementos(){ //Análisis de elementos que conforman un sitio web.
        BuscarElementos("imagen");
        this.sitio.setImagenes(this.cont);
        this.cont=0;
        
        BuscarElementos("enlace");
        this.sitio.setEnlaces(this.cont);
        this.cont=0;
        
        BuscarElementos("video");
        this.sitio.setVideos(this.cont);
        this.cont=0;
        
        BuscarElementos("titulo");
        this.sitio.setTitulos(this.cont);
        this.cont=0;
        
        BuscarElementos("subtitulo");
        this.sitio.setSubtitulos(this.cont);
        this.cont=0;
        
        BuscarElementos("tabla");
        this.sitio.setTablas(this.cont);
        this.cont=0;
        
        System.out.println("Imagenes: "+ this.sitio.getImagenes());
        System.out.println("Enlaces: "+ this.sitio.getEnlaces());
        System.out.println("Titulos: "+ this.sitio.getTitulos());
        System.out.println("Subtitulos: "+ this.sitio.getSubtitulos());
        System.out.println("Videos: "+ this.sitio.getVideos());
        System.out.println("Tablas: "+ this.sitio.getTablas());
        
    }
    
    public void BuscarElementos(String tipo){ //busca imagenes o enlaces
        try {
            //String url = "https://www.facebook.com/?locale=es_LA";
            String et="";
            if(tipo.equalsIgnoreCase("enlace")){
                et="a";
                tipo="abs:href";
            }
            
            if(tipo.equalsIgnoreCase("imagen")){
                et="img";
                tipo="abs:src";
            }
            
            if(tipo.equalsIgnoreCase("tabla")){
                et="table";
                tipo="abs:summary";
            }
            
            if(tipo.equalsIgnoreCase("titulo")){
                et="title";
                tipo="null";
            }
            
            if(tipo.equalsIgnoreCase("video")){
                et="video";
                tipo="abs:width";
            }
            //MEJORAR CON ENUM
            
            try {
                desactivarCertificado();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            }//try - catch interno//try - catch interno
            
            Document document = Jsoup.connect(this.sitio.getUrl()).get();
            Elements links = document.select(et);//IMPORTANTE selecciona la etiqueta a bucar
            
            for (Element link : links) {//busca links en una coleccion de links hasta que no haya mas
                System.out.println(link.attr(tipo));// IMPORTANTE abs= absolute (el atributo absoluto)
                this.cont++;
            }//for each
                    
        } catch (IOException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        }//try - catch//try - catch
    }
    
    public void ExtraerElementos(Sitio sitio){
        //yo ya tengo el sitio, solo pido la url y empiezo a buscar las cosas
        try {
            //String url = "https://www.facebook.com/?locale=es_LA";
            String et1="h1";
            String et2="h3";
            String abs="abs:src";
            ArrayList<String> Lproductos= new ArrayList<>();
            ArrayList<Integer> Lprecios= new ArrayList<>();
            try {
                desactivarCertificado();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            }//try - catch interno//try - catch interno
            
            Document document = Jsoup.connect(sitio.getUrl()).get();
            Elements productos = document.select(et1);//IMPORTANTE selecciona la etiqueta a bucar
            Elements precios = document.select(et1);//IMPORTANTE selecciona la etiqueta a bucar
            
            for (Element producto : productos) {//busca links en una coleccion de links hasta que no haya mas
                System.out.println(producto.attr(abs));// IMPORTANTE abs= absolute (el atributo absoluto)
                Lproductos.add(producto.attr(abs));
            }//for each
            
            for (Element precio : precios) {//busca links en una coleccion de links hasta que no haya mas
                System.out.println(precio.attr(abs));// IMPORTANTE abs= absolute (el atributo absoluto)
                Lprecios.add(Integer.parseInt(precio.attr(abs)));
            }//for each
            
            for (int i = 0; i < precios.size(); i++) {
                sitio.getProductos().add(new ProductoServicio(Lproductos.get(i), Lprecios.get(i)));
            }
                    
        } catch (IOException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        }//try - catch//try - catch
        
    }
    
    private static void desactivarCertificado() throws NoSuchAlgorithmException, KeyManagementException {
        // Crear un administrador de confianza que no realice ninguna validación del certificado
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        // Configurar la conexión SSL para desactivar la validación del certificado
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Desactivar la verificación del host
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
    
}//fin clase
