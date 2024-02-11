
package servidor;

import Data.AnalisisData;
import Domain.Sitio;
import GUI.JFLogging;
import java.io.IOException;
import org.jdom.JDOMException;


/**
 *
 * @author Estephanie
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JDOMException, IOException {
//        JFLogging logging = new JFLogging();
//         logging.setVisible(true);
         AnalisisData ad= new AnalisisData();
         ad.ExtraerElementos("https://www.apple.com/");
    }
    
}
