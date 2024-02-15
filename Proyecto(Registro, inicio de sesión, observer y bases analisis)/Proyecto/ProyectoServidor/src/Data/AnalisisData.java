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
    public void cantidadElementos(String url) throws NoSuchAlgorithmException, KeyManagementException {
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

//            // Almacenar los datos en un archivo
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos_extraidos.xml"))) {
//                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//                writer.write("<datos>\n");
//                writer.write("  <num_imagenes>" + imagenCont + "</num_imagenes>\n");
//                writer.write("  <num_titulos>" + tituloCOnt + "</num_titulos>\n");
//                writer.write("  <num_subtitulos>" + subtituloCont + "</num_subtitulos>\n");
//                writer.write("  <num_enlaces>" + enlanceCont + "</num_enlaces>\n");
//                writer.write("  <num_tablas>" + tablaCont + "</num_tablas>\n");
//                writer.write("  <num_videos>" + videoCOnt + "</num_videos>\n\n");
//
//                writer.write("  <imagenes>\n");
//                for (Element imagen : imagenes) {
////                    writer.write("    <imagen src=\"" + imagen.attr("src") + "\" />\n");
//                    this.imagenesURL.add( imagen.attr("src"));
//                }
////                writer.write("  </imagenes>\n");
////
////                writer.write("  <titulos>\n");
//                for (Element titulo : titulos) {
////                    writer.write("    <titulo>" + titulo.text() + "</titulo>\n");
//                   this.titulo= titulo.text();
//                }
////                writer.write("  </titulos>\n");
////
////                writer.write("  <subtitulos>\n");
////                for (Element subtitulo : subtitulos) {
////                    writer.write("    <subtitulo>" + subtitulo.text() + "</subtitulo>\n");
////                }
////                writer.write("  </subtitulos>\n");
////
////                writer.write("  <enlaces>\n");
//                for (Element enlace : enlaces) {
////                    writer.write("    <enlace href=\"" + enlace.attr("href") + "\" />\n");
//                    writer.write("    <enlace href=\"" + enlace.attr("href") + "\" />\n");
//                }
//                writer.write("  </enlaces>\n");
//
//                writer.write("  <tablas>\n");
//                for (Element tabla : tablas) {
//                    writer.write("    <tabla>" + tabla.outerHtml() + "</tabla>\n");
//                }
//                writer.write("  </tablas>\n");
//
//                writer.write("  <videos>\n");
//                for (Element video : videos) {
//                    writer.write("    <video src=\"" + video.attr("src") + "\" />\n");
//                }
//                writer.write("  </videos>\n");
//
//                // Llamar al método precios para guardar los precios
//                precios(url, writer);
//
//                writer.write("</datos>");
//            }
//
//            System.out.println("Datos extraídos y almacenados en 'datos_extraidos.xml'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Análisis de elementos y extracción. seleccionan img y/o enlaces
    //Enlaces
    public void extraerEnlaces(String url) {
        if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
            this.sitio = new Sitio(url);
        }

        try {
            // Desactivar la verificación del certificado SSL
            desactivarCertificado();
            Document doc = Jsoup.connect(url).get();
            //recoge los enlaces
            this.enlaces = doc.select("a");
            int it = 0;//ponerle número al enlace
            for (Element enlace : this.enlaces) {
                this.aEnlaces.add(enlace.attr("href"));
                it++;
            }
            this.sitio.setaEnlaces(this.aEnlaces);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Descarga imágenes
    public void descargarImagen(String url) throws IOException {
        //Crea un sitio para almacenar la siguiente información
        if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
            this.sitio = new Sitio(url);
        }
        try {
            // Desactivar la verificación del certificado SSL
            desactivarCertificado();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        File carpeta = new File(this.titulo + "/");
        System.out.println(this.titulo + "/");
        boolean crear = carpeta.mkdirs();//crea la carpeta cada vez
        System.out.println(crear);//si se crea o no la carpeta
        if (carpeta.exists()) {//valida que haya una carpeta para guardar las imágenes
            for (int i = 0; i < this.imagenesURL.size(); i++) {
                String linea = this.imagenesURL.get(i);
                //busca el formato que tiene la imagen
                int format = linea.lastIndexOf(".");
                String formato = linea.substring(format);
                System.out.println(linea);//links de imagenes
                hiloDescarga.add(new DescargaArchivo(linea, carpeta.getName()+"/"  + i + formato));//Le da a hilo los datos para descargar
            }
            for (int i = 0; i < hiloDescarga.size(); i++) {
                hiloDescarga.get(i).start();//empiezan las descargas
            }

        } else {
            System.out.println("La carpeta no se creó");
        }
    }//método

    public void precios(String url) {//, BufferedWriter writer
        try {
            //Crea un sitio para almacenar la siguiente información
            if (this.sitio == null) {//si se escogen todos los enlaces permite que se guarden en un mismo objeto sitio
                this.sitio = new Sitio(url);
            }
            // Obtener el documento HTML de una página web
            Document doc = Jsoup.connect(url).get();
            // Buscar elementos que contengan precios
            Elements allElements = doc.getAllElements();
            Elements priceElements = new Elements();
            for (Element element : allElements) {
                String text = element.text();
                if (containsPrice(text)) {
                    System.out.println(element);
                    priceElements.add(element);
                }
            }

            // Extraer y almacenar los precios en el archivo XML
            for (Element element : priceElements) {
                // Extraer el texto del elemento
                String text = element.text();
                // Utilizar expresiones regulares para encontrar el precio
                Pattern pattern = Pattern.compile("[€$£¥:]\\s*\\d+(\\.\\d+)?");
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    String price = matcher.group();
//                    System.out.println(price);
                    //writer.write("  <precio>" + price + "</precio>\n");
                    this.sitio.addProductos("nombre", price);//guarda cada producto
                }
            }

//            System.out.println("Precios extraídos y almacenados en 'datos_extraidos.xml'");
        } catch (IOException e) {
            // Manejo de cualquier excepción de entrada/salida
            System.out.println("Ocurrió un error al procesar la solicitud HTTP: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para verificar si una cadena contiene un precio
    private static boolean containsPrice(String text) {
        return text.matches(".[€$£¥:]\\s\\d+(\\.\\d+)?.*");
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
