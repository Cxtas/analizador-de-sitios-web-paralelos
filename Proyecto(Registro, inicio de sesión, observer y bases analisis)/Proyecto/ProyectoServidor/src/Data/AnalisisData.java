package Data;

import Domain.DescargaArchivo;
import Domain.Sitio;
import Utility.Ruta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author author
 */
public class AnalisisData {

    private int imagenCont = 0;
    private int tituloCOnt = 0;
    private int subtituloCont = 0;
    private int enlanceCont = 0;
    private int tablaCont = 0;
    private int videoCOnt = 0;
    private Sitio sitio;
    private org.jdom.Document document;
    private org.jdom.Element root;
    private ArrayList<String> imagenesURL;
    private ArrayList<String> aEnlaces;
    private String titulo;
    private Elements imagenes;
    private Elements enlaces;
    private Elements titulos;

    public AnalisisData() {
        this.imagenesURL = new ArrayList<>();
        this.aEnlaces = new ArrayList<>();
    }

    //Análisis de elementos que conforman un sitio web.
    public boolean cantidadElementos(String url) throws NoSuchAlgorithmException, KeyManagementException {
        try {
            //Crea un sitio para almacenar la siguiente información
            if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
                this.sitio = new Sitio(url);
            }
            // Desactivar la verificación del certificado SSL
            desactivarCertificado();

            // Obtener el documento HTML de una página web
            Document doc = Jsoup.connect(url).get();

            // Extraer imágenes
            this.imagenes = doc.select("img");
            this.sitio.setImagenes(imagenes.size());

            // Extraer títulos
            this.titulos = doc.select("title");
            this.sitio.setTitulos(titulos.size());

            // Extraer subtítulos (h2, h3, etc.)
            Elements subtitulos = doc.select("h2, h3, h4, h5, h6");
            this.sitio.setSubtitulos(subtitulos.size());

            // Extraer enlaces
            this.enlaces = doc.select("a");
            this.sitio.setEnlaces(enlaces.size());

            // Extraer tablas
            Elements tablas = doc.select("table");
            this.sitio.setTablas(tablas.size());

            // Extraer videos
            Elements videos = doc.select("video");
            this.sitio.setVideos(videos.size());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Análisis de elementos y extracción. seleccionan img y/o enlaces
    //Enlaces
    public boolean extraerEnlaces(String url) {
        try {
            if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
                this.sitio = new Sitio(url);
            }
            // Desactivar la verificación del certificado SSL
            desactivarCertificado();
            Document doc = Jsoup.connect(url).get();
            //recoge los enlaces
            this.enlaces = doc.select("a");
            for (Element enlace : this.enlaces) {
                this.aEnlaces.add(enlace.attr("href"));
            }

            this.sitio.setaEnlaces(this.aEnlaces);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (KeyManagementException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NullPointerException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    //Descarga imágenes
    public boolean descargarImagen(String url) throws IOException {

        try {
            //Crea un sitio para almacenar la siguiente información
            if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
                this.sitio = new Sitio(url);
            }
            // Desactivar la verificación del certificado SSL
            desactivarCertificado();

            Document doc = Jsoup.connect(url).get();

            this.titulos = doc.select("title");
            for (Element titulo : this.titulos) {
                this.titulo = titulo.text();
            }

            this.imagenes = doc.select("img");
            for (Element imagen : this.imagenes) {
                this.imagenesURL.add(imagen.attr("src"));
            }

            //Descarga imágenes
            ArrayList<DescargaArchivo> hiloDescarga = new ArrayList<>();
            String tituloSinSignos = this.titulo.replaceAll("\\p{Punct}", "");//quita los signos para evitar que no se cree la carpeta
            File carpeta = new File(tituloSinSignos + "/");
            boolean crear = carpeta.mkdirs();//crea la carpeta cada vez
            if (carpeta.exists()) {
                for (int i = 0; i < this.imagenesURL.size(); i++) {
                    String linea = this.imagenesURL.get(i);
                    //busca el formato que tiene la imagen
                    int format = linea.lastIndexOf(".");
                    String formato = ".jpg";
                    if (format != -1) {//evita que haya error si no se encuentra el .formato
                        formato = linea.substring(format);
                    }
                    hiloDescarga.add(new DescargaArchivo(linea, carpeta.getName() + "/" + i + formato));//Le da a hilo los datos para descargar
                }
                for (int i = 0; i < hiloDescarga.size(); i++) {
                    hiloDescarga.get(i).start();//empiezan las descargas
                }
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (KeyManagementException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NullPointerException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }//método

    public boolean precios(String url) {//, BufferedWriter writer
        try {
            //Crea un sitio para almacenar la siguiente información
            if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
                this.sitio = new Sitio(url);
            }

            Document doc = Jsoup.connect(url).get();

            Elements spansPrice = doc.select("span.price");//selecciona los que contengan span.price
            ArrayList<String> precios = new ArrayList<>();

            for (Element span : spansPrice) {
                String priceText = span.text();//toma el texto de la etiqueta
                if ((!priceText.equals("")) && (esPrecio(priceText))) {//si no está vacio
                    String soloNum = priceText.replaceAll("[^0-9]", "");//para verificar que sea mayor a 0
                    int precioInt = Integer.parseInt(soloNum);
                    if (precioInt > 0) {
                        precios.add(priceText);//se agrega al array
                    }
                }
            }
            
            if (precios.isEmpty()) {//si no funcionó el anterior y el array está vacio
                Elements spans = doc.select("span"); //se prueba con el span solito
                for (Element spanOne : spans) {
                    if (spanOne.hasClass("price") || spanOne.hasClass("price product-price") || spanOne.hasClass("woocommerce-Price-currencySymbol")) {//si span solito tiene la clase price
                        String priceText = spanOne.text();//toma el texto
                        if ((!priceText.equals("")) && (esPrecio(priceText))) { //si no está vacio
                            String soloNum = priceText.replaceAll("[^0-9]", "");//para verificar que sea mayor a 0
                            int precioInt = Integer.parseInt(soloNum);
                            if (precioInt > 0) {
                                precios.add(priceText);//se agrega al array
                            }
                        }
                    }
                }
            }

            if (precios.isEmpty()) {
                for (Element span : spansPrice) {
                    String priceText = span.text();
                    precios.add(priceText);
                }
            }
            
            if (precios.isEmpty()) {//si no funcionó el anterior y el array está vacio
                Elements bdis = doc.select("bdi"); //se prueba con el span solito
                for (Element bdi : bdis) {
                    String priceText = bdi.text();//toma el texto
                    precios.add(priceText);//se agrega al array
                }
            }

            //mostrar precios
            for (int i = 0; i < precios.size(); i++) {
                System.out.println("Precio: " + precios.get(i));
            }

            //nombres
            Elements aTitle = doc.select("a");//los que tengan <a>
            ArrayList<String> productosN = new ArrayList<>();

            String ptitle = "";
            for (Element title : aTitle) {
                if (title.hasClass("product-item-link") || title.hasClass("product-name") || title.hasClass("title")) {//los que tengan algunas estas clases
                    ptitle = title.text();
                }
                //evita que se guarden datos en blanco o repetidos
                if (!ptitle.equals("")) {
                    if (!productosN.contains(ptitle)) {
                        productosN.add(ptitle);
                    }
                }
            }//for

            if (productosN.isEmpty()) {
                Elements productos2 = doc.select("[class~=product-title]");
                // Si hay elementos que tienen la clase "product-title"
                for (Element elemento : productos2) {
                    ptitle = elemento.text();
                    System.out.println(ptitle);
                }
            }

            //se muestran los nombres
            for (int i = 0; i < productosN.size(); i++) {
                System.out.println("Producto: " + productosN.get(i));
            }

            //añade los precios y nombres al array de sitio
            this.sitio.setProductos(productosN);
            this.sitio.setPrecios(precios);

        } catch (IOException e) {
            // Manejo de cualquier excepción de entrada/salida
            System.out.println("Ocurrió un error al procesar la solicitud HTTP: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (NullPointerException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean esPrecio(Object obj) {//verifica que sea un precio
        String text = String.valueOf(obj);
        Pattern pattern = Pattern.compile("[₡€$£¥:]?\\s?\\d{1,3}(\\s?\\d{3})*(\\s?,\\s?\\d+)?");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
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

    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xMLOutputter = new XMLOutputter();
        xMLOutputter.output(this.document, new PrintWriter(Ruta.RUTAANALISIS));
    }

    public Sitio getSitio() {
        return sitio;
    }

}//fin clase
