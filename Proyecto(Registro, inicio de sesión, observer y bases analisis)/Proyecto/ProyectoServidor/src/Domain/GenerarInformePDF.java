/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.Ruta;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author catas
 */
public class GenerarInformePDF extends Thread {
    
    private Sitio sitio;

    public GenerarInformePDF () {//Sitio sitio
        this.sitio = sitio;
    }

//    @Override
//    public void run() {
//        generarInforme();
//    }

    public void generarInforme(Sitio sitio) {
        Document document = new Document();
        try {
            int contador = 1;
            String nombreArchivo = "InformeAnalisis_" + contador + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
//            for (Sitio sitio : sitios) {
                document.add(new Paragraph("Análisis del Sitio Web: " + sitio.getUrl()));
                document.add(new Paragraph("Cantidad de Imágenes: " + sitio.getImagenes()));
                document.add(new Paragraph("Cantidad de Enlaces: " + sitio.getEnlaces()));
                document.add(new Paragraph("Cantidad de Videos: " + sitio.getVideos()));
                document.add(new Paragraph("Cantidad de Titulos: " + sitio.getTitulos()));
                document.add(new Paragraph("Cantidad de Tablas: " + sitio.getTablas()));
                document.add(new Paragraph("-----------------------------------------------"));
//            }
            document.close();
            System.out.println("Informe PDF generado exitosamente: " + nombreArchivo);
            contador++; // Incrementar el contador para el próximo informe
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
