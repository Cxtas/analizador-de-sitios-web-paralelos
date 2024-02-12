package Domain;

import java.util.ArrayList;
import org.jdom.Element;

/**
 *
 * @author author
 */
public class Analisis {

    ArrayList<Element> data;
    private String url;

    public Analisis() {
        data = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Element> getElementos() {
        return data;
    }

    public void setElementos(ArrayList<Element> elementos) {
        this.data = elementos;
    }

}//fin clase
