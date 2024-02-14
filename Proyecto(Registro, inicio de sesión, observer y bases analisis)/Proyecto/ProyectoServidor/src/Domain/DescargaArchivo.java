/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author Nelson
 */
public class DescargaArchivo extends Thread {

    private String url;
    private String destino;

    public DescargaArchivo(String url, String destino) {
        this.url = url;
        this.destino = destino;
    }

    @Override
    public void run() {
//        while (true) {
            try {
                // Descargar el archivo desde la URL
                URL archivoUrl = new URL(url);
                try (InputStream inputStream = archivoUrl.openStream(); FileOutputStream fileOutputStream = new FileOutputStream(destino)) {

                    byte[] buffer = new byte[10024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Descarga completada: " + destino);
                }

            } catch (IOException e) {
                System.err.println("Error al descargar " + url + ": " + e.getMessage());
            }
//        }
    }

} // fin clase
