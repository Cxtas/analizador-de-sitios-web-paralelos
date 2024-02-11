package Data;

import Domain.Analisis;
import Utility.GestionXML;
import Domain.Sitio;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
  
    
    public AnalisisData() throws JDOMException, IOException {
       
    }
    

       public void ExtraerElementos(String url) {
        try {
            // Obtener el documento HTML de una página web
            Document doc = Jsoup.connect(url).get();

            // Contadores para imágenes, títulos, subtítulos, enlaces, tablas y videos
            int imageCount = 0;
            int titleCount = 0;
            int subtitleCount = 0;
            int linkCount = 0;
            int tableCount = 0;
            int videoCount = 0;

            // Extraer imágenes
            Elements images = doc.select("img");
            imageCount = images.size();

            // Extraer títulos
            Elements titles = doc.select("title");
            titleCount = titles.size();

            // Extraer subtítulos (h2, h3, etc.)
            Elements subtitles = doc.select("h2, h3, h4, h5, h6");
            subtitleCount = subtitles.size();

            // Extraer enlaces
            Elements links = doc.select("a");
            linkCount = links.size();

            // Extraer tablas
            Elements tables = doc.select("table");
            tableCount = tables.size();

            // Extraer videos
            Elements videos = doc.select("video");
            videoCount = videos.size();

            // Almacenar los datos en un archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos_extraidos.txt"))) {
                writer.write("Número de imágenes: " + imageCount + "\n");
                writer.write("Número de títulos: " + titleCount + "\n");
                writer.write("Número de subtítulos: " + subtitleCount + "\n");
                writer.write("Número de enlaces: " + linkCount + "\n");
                writer.write("Número de tablas: " + tableCount + "\n");
                writer.write("Número de videos: " + videoCount + "\n\n");

                writer.write("Imágenes:\n");
                for (Element image : images) {
                    writer.write(image.attr("src") + "\n");
                }

                writer.write("\nTítulos:\n");
                for (Element title : titles) {
                    writer.write(title.text() + "\n");
                }

                writer.write("\nSubtítulos:\n");
                for (Element subtitle : subtitles) {
                    writer.write(subtitle.text() + "\n");
                }

                writer.write("\nEnlaces:\n");
                for (Element link : links) {
                    writer.write(link.attr("href") + "\n");
                }

                writer.write("\nTablas:\n");
                for (Element table : tables) {
                    writer.write(table.outerHtml() + "\n");
                }

                writer.write("\nVideos:\n");
                for (Element video : videos) {
                    writer.write(video.attr("src") + "\n");
                }
            }

            precios("https://www.apple.com//");

            System.out.println("Datos extraídos y almacenados en 'datos_extraidos.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
       }  

    public static void precios(String url) {
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

        // Extraer y almacenar los precios en un archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("precios_extraidos.txt"))) {
            for (Element element : priceElements) {
                // Extraer el texto del elemento
                String text = element.text();
                // Utilizar expresiones regulares para encontrar el precio
                Pattern pattern = Pattern.compile("[€$£¥]\\s*\\d+(\\.\\d+)?"); // Patrón para encontrar precios (por ejemplo, €12.34)
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    String price = matcher.group();
                    writer.write(price + "\n");
                }
            }
        }

        System.out.println("Precios extraídos y almacenados en 'precios_extraidos.txt'");
    } catch (IOException e) {
        // Manejo de cualquier excepción de entrada/salida
        System.out.println("Ocurrió un error al procesar la solicitud HTTP: " + e.getMessage());
        e.printStackTrace();
    }
}

    // Método para verificar si una cadena contiene un precio
    private static boolean containsPrice(String text) {
        return text.matches(".*[€$£¥]\\s*\\d+(\\.\\d+)?.*");
    }
    
//    private static void desactivarCertificado() throws NoSuchAlgorithmException, KeyManagementException {
//        // Crear un administrador de confianza que no realice ninguna validación del certificado
//        TrustManager[] trustAllCerts = new TrustManager[]{
//            new X509TrustManager() {
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//        };
//
//        // Configurar la conexión SSL para desactivar la validación del certificado
//        SSLContext sslContext = SSLContext.getInstance("SSL");
//        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//
//        // Desactivar la verificación del host
//        HostnameVerifier allHostsValid = (hostname, session) -> true;
//        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//    }
    
}//fin clase
