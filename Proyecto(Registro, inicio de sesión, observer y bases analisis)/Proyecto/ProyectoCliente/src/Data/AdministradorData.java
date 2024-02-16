package Data;

import Domain.Administrador;
import Utility.Ruta;
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
 * @author Estephanie
 */
public class AdministradorData {

    private Document document;
    private Element root;

    public AdministradorData() throws IOException, JDOMException {
        File f = new File(Ruta.RUTAADMINISTRADOR);
        if (f.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);//Ignora los espacios en blanco y toma todo el elemento, si no, el espacio lo toma como separaciones de elementos
            this.document = saxBuilder.build(Ruta.RUTAADMINISTRADOR);
            this.root = this.document.getRootElement();
        } else {
            this.root = new Element("administradores");
            this.document = new Document(this.root);
            guardarXML();
        }
    }//constructor

    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(Ruta.RUTAADMINISTRADOR));
    }//guaradarXML

    //se registran administradores
    public boolean insertarAdministrador(Administrador administrador) throws IOException {

        ArrayList<Administrador> administradores = obtenerAdministradores();//se obtinen los administradores registrados

        if (administradores.size() > 0) {//si hay administradores
            boolean repetido = verificarUsuario(administrador.getUser());//se verifica que no tenga el mismo user el que se va a registrar
            if (repetido) {//si ya está registrado el user se devulve false
                return false;
            }
        }

        //de lo contrario se registra el nuevo administrador
        Element eAdministrador = new Element("administrador");//<administrador>
        Element eUser = new Element("user");
        eUser.addContent(administrador.getUser());

        Element eContrasenia = new Element("contrasenia");
        eContrasenia.addContent(administrador.getContrasenia());

        Element eTipoUsuario = new Element("tipoUsuario");
        eTipoUsuario.addContent(administrador.getTipoUsuario());

        Element eActivo = new Element("activo");
        eActivo.addContent(String.valueOf(administrador.isActivo()));

        eAdministrador.addContent(eUser);
        eAdministrador.addContent(eContrasenia);
        eAdministrador.addContent(eTipoUsuario);
        eAdministrador.addContent(eActivo);

        this.root.addContent(eAdministrador);
        guardarXML();

        return true;
    }

    public ArrayList<Administrador> obtenerAdministradores() {//se obtienen todos los administradores registrados
        ArrayList<Administrador> administradores = new ArrayList<>();

        List eAdministradores = this.root.getChildren();
        for (Object objetoActual : eAdministradores) {
            Element eActual = (Element) objetoActual;
            Administrador admin = new Administrador(eActual.getChild("user").getValue(), eActual.getChild("contrasenia").getValue(),
                    eActual.getChild("tipoUsuario").getValue(), Boolean.parseBoolean(eActual.getChild("activo").getValue()));
            administradores.add(admin);
        }//for
        return administradores;
    }//obtenerAdmins

    //se verifica que no tengan el mismo user
    public boolean verificarUsuario(String nombreUsuario) {
        List elementList = this.root.getChildren();

        for (Object object : elementList) {
            Element eAdminTemp = (Element) object;
            Administrador admin = new Administrador(eAdminTemp.getChild("user").getValue(), eAdminTemp.getChild("contrasenia").getValue(),
                    eAdminTemp.getChild("tipoUsuario").getValue(), Boolean.parseBoolean(eAdminTemp.getChild("activo").getValue()));
            if (admin.getUser().equals(nombreUsuario) && admin.isActivo()) {
                return true;
            }

        }

        return false;
    }//verificar usuario

    //se desactiva un administrador cambiando el atributo activo de false a true
    public boolean desactivarAdministrador(Administrador admin) throws IOException {
        List elementList = this.root.getChildren();
        boolean hecho = false;
        for (Object object : elementList) {
            Element eUsuarioActual = (Element) object;
            if (eUsuarioActual.getChild("user").getValue().equals(admin.getUser())) {
                eUsuarioActual.getChild("activo").removeContent();
                eUsuarioActual.getChild("activo").addContent(String.valueOf(admin.isActivo()));
                hecho=true;
            }
        }//for
        if (hecho) {
            this.guardarXML();
            return true;
        }
        return false;
    }//desactivar
}//clase