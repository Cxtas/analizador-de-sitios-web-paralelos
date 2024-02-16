
package servidor;

import Data.AnalisisData;
import Domain.Sitio;
import GUI.JFLogging;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.jdom.JDOMException;


/**
 *
 * @author Estephanie
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JDOMException, IOException, NoSuchAlgorithmException, KeyManagementException {
        JFLogging logging = new JFLogging();
         logging.setVisible(true);
    }
    
}
