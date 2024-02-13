package Data;

import Utility.Ruta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
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
<<<<<<< HEAD

    private int imagenCont = 0;
    private int tituloCOnt = 0;
    private int subtituloCont = 0;
    private int enlanceCont = 0;
    private int tablaCont = 0;
    private int videoCOnt = 0;
    private org.jdom.Document document;
    private org.jdom.Element root;

    public AnalisisData() throws JDOMException, IOException {
        File f = new File(Ruta.RUTAANALISIS);
        if (f.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            this.document = saxBuilder.build(Ruta.RUTAANALISIS);
            this.root = this.document.getRootElement();
        } else {
            this.root = new org.jdom.Element("estudiantes");
            this.document = new org.jdom.Document(this.root);
            guardarXML();
        }
=======
    private String et;
    private GestionXML manejo;
    private Analisis a;
    private String url;
    private Sitio sitio;
    private int cont;
    private ArrayList<Sitio> sitios;
    
    public AnalisisData(String url) throws JDOMException, IOException {
        a= new Analisis();
        this.url=url;
        this.manejo= new GestionXML();
        this.sitio= new Sitio(url);
        this.et="";
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
        
        for (int i = 1; i < 7; i++) {
            this.et="h"+i;
            BuscarElementos("subtitulo");
            this.sitio.setSubtitulos(this.cont);
        }
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
            
            
            
            if(tipo.equalsIgnoreCase("enlace")){
                this.et="a";
                tipo="abs:href";
            }
            
            if(tipo.equalsIgnoreCase("imagen")){
                this.et="img";
                tipo="abs:src";
            }
            
            if(tipo.equalsIgnoreCase("tabla")){
                this.et="table";
                tipo="abs:summary";
            }
            
            if(tipo.equalsIgnoreCase("titulo")){
                this.et="title";
                tipo="null";
            }
            
            if(tipo.equalsIgnoreCase("subtitulo")){
                
                tipo="class";
            }
            
            if(tipo.equalsIgnoreCase("video")){
                this.et="video";
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
            Elements links = document.select(this.et);//IMPORTANTE selecciona la etiqueta a bucar
            
            for (Element link : links) {//busca links en una coleccion de links hasta que no haya mas
                System.out.println(link.attr(tipo));// IMPORTANTE abs= absolute (el atributo absoluto)
                this.cont++;
            }//for each
                    
        } catch (IOException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
    }//try - catch//try - catch
    }// buscar elemento

    
    
       public void ExtraerElementos(){
        //yo ya tengo el sitio, solo pido la url y empiezo a buscar las cosas
        ArrayList<ProductoServicio> productos= new ArrayList<>();
        ArrayList<String> nombres= new ArrayList<>();
        ArrayList<String> precios= new ArrayList<>();
        String precioP="";
        String nombreP="";
        String temp="";
        int p=0;
        int IPunto=0;
        int IComa=0;
        try {
            //esta url es this.url porque es la que se le pasa por parámetro
//            String url = "https://extremetechcr.com/tienda/32-combos-gaming";
            
            try {
                desactivarCertificado();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
            }//try - catch interno
            
            Document document = Jsoup.connect(this.sitio.getUrl()).get();
            
            System.out.println(document.outerHtml());
            Elements links = document.select("a");//selecciona la etiqueta a bucar //a
            
            System.out.println("PRODUCTOS");
            for (Element link : links) {//busca links en una coleccion de links hasta que no haya mas
                if(temp.equals(link.attr("abs:href")))
                    System.out.println("");
                else if(link.attr("abs:href").contains(".html")){
                    System.out.println(link.attr("abs:href"));//abs= absolute (el atributo absoluto) //href
                    temp=link.attr("abs:href");
                    nombres.add(temp);
                    }
            }//for each
            
            temp="";
            Elements spans = document.select("span");//para buscar precios
            System.out.println("PRECIOS");
            for (Element span : spans) {//busca links en una coleccion de links hasta que no haya mas
                if(span.text().contains(",")){
                    System.out.println(span.text());//abs= absolute (el atributo absoluto) //href
                    precios.add(span.text());
                }//if interno
            }//for each
            
            p=45;
            for (int i = 0; i < nombres.size(); i++) {
                IPunto=nombres.get(i).indexOf('.', p);
                IComa=precios.get(i).indexOf(',');
                if(IPunto>-1){
                    nombreP=nombres.get(i).substring(p, IPunto);
                    precioP=precios.get(i).replace(',', precios.get(i).charAt(IComa-1));
                }
                productos.add(new ProductoServicio(nombreP, Integer.parseInt(precioP.substring(3))));
            }
            
            for (int i = 0; i < productos.size(); i++) {
                System.out.println(productos.get(i).toString());
            }
            
           
                    
        } catch (IOException ex) {
            Logger.getLogger(AnalisisData.class.getName()).log(Level.SEVERE, null, ex);
        }//try - catch
>>>>>>> 27ea812f906a5af4a776404fa4918788fa8f4926
    }

    public void ExtraerElementos(String url) throws NoSuchAlgorithmException, KeyManagementException {
        try {
            // Desactivar la verificación del certificado SSL
            desactivarCertificado();

            // Obtener el documento HTML de una página web
            Document doc = Jsoup.connect(url).get();

            // Extraer imágenes
            Elements imagenes = doc.select("img");
            this.imagenCont = imagenes.size();

            // Extraer títulos
            Elements titulos = doc.select("title");
            this.tituloCOnt = titulos.size();

            // Extraer subtítulos (h2, h3, etc.)
            Elements subtitulos = doc.select("h2, h3, h4, h5, h6");
            this.subtituloCont = subtitulos.size();

            // Extraer enlaces
            Elements enlaces = doc.select("a");
            this.enlanceCont = enlaces.size();

            // Extraer tablas
            Elements tablas = doc.select("table");
            this.tablaCont = tablas.size();

            // Extraer videos
            Elements videos = doc.select("video");
            this.videoCOnt = videos.size();

            // Almacenar los datos en un archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos_extraidos.xml"))) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<datos>\n");
                writer.write("  <num_imagenes>" + imagenCont + "</num_imagenes>\n");
                writer.write("  <num_titulos>" + tituloCOnt + "</num_titulos>\n");
                writer.write("  <num_subtitulos>" + subtituloCont + "</num_subtitulos>\n");
                writer.write("  <num_enlaces>" + enlanceCont + "</num_enlaces>\n");
                writer.write("  <num_tablas>" + tablaCont + "</num_tablas>\n");
                writer.write("  <num_videos>" + videoCOnt + "</num_videos>\n\n");

                writer.write("  <imagenes>\n");
                for (Element imagen : imagenes) {
                    writer.write("    <imagen src=\"" + imagen.attr("src") + "\" />\n");
                }
                writer.write("  </imagenes>\n");

                writer.write("  <titulos>\n");
                for (Element titulo : titulos) {
                    writer.write("    <titulo>" + titulo.text() + "</titulo>\n");
                }
                writer.write("  </titulos>\n");

                writer.write("  <subtitulos>\n");
                for (Element subtitulo : subtitulos) {
                    writer.write("    <subtitulo>" + subtitulo.text() + "</subtitulo>\n");
                }
                writer.write("  </subtitulos>\n");

                writer.write("  <enlaces>\n");
                for (Element enlace : enlaces) {
                    writer.write("    <enlace href=\"" + enlace.attr("href") + "\" />\n");
                }
                writer.write("  </enlaces>\n");

                writer.write("  <tablas>\n");
                for (Element tabla : tablas) {
                    writer.write("    <tabla>" + tabla.outerHtml() + "</tabla>\n");
                }
                writer.write("  </tablas>\n");

                writer.write("  <videos>\n");
                for (Element video : videos) {
                    writer.write("    <video src=\"" + video.attr("src") + "\" />\n");
                }
                writer.write("  </videos>\n");

                // Llamar al método precios para guardar los precios
                precios(url, writer);

                writer.write("</datos>");
            }

            System.out.println("Datos extraídos y almacenados en 'datos_extraidos.xml'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void precios(String url, BufferedWriter writer) {
        try {
            // Obtener el documento HTML de una página web
            Document doc = Jsoup.connect(url).get();

            // Buscar elementos que contengan precios
            Elements allElements = doc.getAllElements();
            Elements priceElements = new Elements();
            for (Element element : allElements) {
                String text = element.text();
                if (containsPrice(text)) {
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
                    writer.write("  <precio>" + price + "</precio>\n");
                }
            }

            System.out.println("Precios extraídos y almacenados en 'datos_extraidos.xml'");
        } catch (IOException e) {
            // Manejo de cualquier excepción de entrada/salida
            System.out.println("Ocurrió un error al procesar la solicitud HTTP: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para verificar si una cadena contiene un precio
    private static boolean containsPrice(String text) {
        return text.matches(".*[€$£¥:]\\s*\\d+(\\.\\d+)?.*");
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

<<<<<<< HEAD
    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xMLOutputter = new XMLOutputter();
        xMLOutputter.output(this.document, new PrintWriter(Ruta.RUTAANALISIS));
    }
=======
    public Sitio getSitio() {
        return sitio;
    }
    
    
>>>>>>> 27ea812f906a5af4a776404fa4918788fa8f4926
}//fin clase
