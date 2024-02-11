
package Domain;

import java.util.ArrayList;
import org.jdom.Element;

/**
 *
 * @author author
 */
public class Analisis {
    ArrayList<Element> data;

    public Analisis() {
        data= new ArrayList<>();
    }

    public ArrayList<Element> getElementos() {
        return data;
    }

    public void setElementos(ArrayList<Element> elementos) {
        this.data = elementos;
    }
    
}//fin clase
