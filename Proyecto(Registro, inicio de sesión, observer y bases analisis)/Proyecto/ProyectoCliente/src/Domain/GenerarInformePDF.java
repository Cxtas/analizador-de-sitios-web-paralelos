/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

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
    
    private ArrayList<Sitio> sitios;

    public GenerarInformePDF (ArrayList<Sitio> sitios) {
        this.sitios = sitios;
    }

    @Override
    public void run() {
        generarInforme();
    }

    public void generarInforme() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Informe_Analisis.pdf"));
            document.open();
            for (Sitio sitio : sitios) {
                document.add(new Paragraph("Análisis del Sitio Web: " + sitio.getUrl()));
                document.add(new Paragraph("Cantidad de Imágenes: " + sitio.getImagenes()));
                document.add(new Paragraph("Cantidad de Enlaces: " + sitio.getEnlaces()));
                document.add(new Paragraph("Cantidad de Videos: " + sitio.getVideos()));
                document.add(new Paragraph("Cantidad de Titulos: " + sitio.getTitulos()));
                document.add(new Paragraph("Cantidad de Tablas: " + sitio.getTablas()));
                document.add(new Paragraph("-----------------------------------------------"));
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
