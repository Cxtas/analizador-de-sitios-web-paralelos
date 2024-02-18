package Domain;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author catas
 */
public class GenerarInformePDF {
    private int contador;
    

    public GenerarInformePDF (int contador) {//Sitio sitio
       this.contador= contador;
    }


    public void generarInforme(Sitio sitio) {
        Document document = new Document();
        try {
            
            String nombreArchivo = "InformeAnalisis_" +contador + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
                document.add(new Paragraph("Análisis del Sitio Web: " + sitio.getUrl()));
                document.add(new Paragraph("Cantidad de Imágenes: " + sitio.getImagenes()));
                document.add(new Paragraph("Cantidad de Enlaces: " + sitio.getEnlaces()));
                document.add(new Paragraph("Cantidad de Videos: " + sitio.getVideos()));
                document.add(new Paragraph("Cantidad de Titulos: " + sitio.getTitulos()));
                document.add(new Paragraph("Cantidad de Tablas: " + sitio.getTablas()));
                document.add(new Paragraph("-----------------------------------------------"));
                document.add(new Paragraph("Enlaces"+ sitio.getaEnlaces()));
                
            document.close();
            JOptionPane.showMessageDialog(null, "Informe PDF generado exitosamente: " + nombreArchivo);
            contador++; // Incrementar el contador para el próximo informe
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
