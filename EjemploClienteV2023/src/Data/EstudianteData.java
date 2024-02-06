package Data;

import Domain.Direccion;
import Domain.Estudiante;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author catas
 */
public class EstudianteData {


  
public Element crearEstudiante (Estudiante estudiante) {

        Element eEstudiante = new Element("Estudiante");
        Element eNombre = new Element("Nombre");
        eNombre.addContent(estudiante.getNombre());

        Element eEdad = new Element("Edad");
        eEdad.addContent("" + estudiante.getEdad());

        Element eDireccion = new Element("Direccion");
        Element eCanton = new Element("Canton");
        eCanton.addContent(estudiante.getDireccion().getCanton());
        Element eDistrito = new Element("Distrito");
        eDistrito.addContent(estudiante.getDireccion().getDistrito());
        eDireccion.addContent(eCanton);
        eDireccion.addContent(eDistrito);

        eEstudiante.addContent(eNombre);
        eEstudiante.addContent(eEdad);
        eEstudiante.addContent(eDireccion);

        
        
        return eEstudiante;
    }// crearEstudiante
}
