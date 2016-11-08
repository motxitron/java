/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Empleado;
import java.io.IOException;
import java.util.List;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author usuario
 */
public class EscribirXMLSAX implements XMLReader {

    private ContentHandler handler;
    private final AttributesImpl atts = new AttributesImpl();

    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }

    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {

    }

    @Override
    public void setEntityResolver(EntityResolver resolver) {
    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {
    }

    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override
    public void setContentHandler(ContentHandler handler) {
    this.handler=handler;
    }

    @Override
    public ContentHandler getContentHandler() {
        return this.handler;
    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {
    }

    @Override
    public ErrorHandler getErrorHandler() {
return null;    }

    @Override
    public void parse(InputSource input) throws IOException, SAXException {
        if (!(input instanceof Modelo)) {
            String m = "El único parámetro para el parser es un Alm_datos";
            throw new SAXException(m);
        }
        if (handler == null) {
            throw new SAXException("Sin ContentHandler");
        }
        Modelo source = (Modelo) input;
        List<Empleado> empleados = source.getEmpleados();
        handler.startDocument();
        handler.startElement("", "empleados", "empleados", atts);
        for (Empleado empl : empleados) {//El id, se añade como atributo para ver el uso de atributos, podría ser un elemento más
            atts.addAttribute("", "id", "id", "", String.valueOf(empl.getId()));
            handler.startElement("", "empleado", "empleado", atts);
            atts.clear();

            handler.startElement("", "nombre", "nombre", atts);
            char[] nombre = empl.getNombre().toCharArray();
            handler.characters(nombre, 0, nombre.length);
            handler.endElement("", "nombre", "nombre");

            handler.startElement("", "apell1", "apell1", atts);
            char[] apell1 = empl.getApell1().toCharArray();
            handler.characters(apell1, 0, apell1.length);
            handler.endElement("", "apell1", "apell1");

            handler.startElement("", "apell2", "apell2", atts);
            char[] apell2 = empl.getApell2().toCharArray();
            handler.characters(apell2, 0, apell2.length);
            handler.endElement("", "apell2", "apell2");

            handler.startElement("", "salario", "salario", atts);
            char[] salario = String.valueOf(empl.getSalario()).toCharArray();
            handler.characters(salario, 0, salario.length);
            handler.endElement("", "salario", "salario");

            handler.endElement("", "empleado", "empleado");

        }
        handler.endElement("", "empleados", "empleados");
        handler.endDocument();
    }

    @Override
    public void parse(String systemId) throws IOException, SAXException {
    }

}
