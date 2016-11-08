/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Empleado;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class LectorXML extends DefaultHandler {

    ArrayList<Empleado> lista = new ArrayList<>();
    ArrayList<String> todo = new ArrayList<>();

    public ArrayList<Empleado> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Empleado> lista) {
        this.lista = lista;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("\nPrincipio del documento XML");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nFin del documento XML");

    }

    @Override
    public void startElement(String uri, String localName, String name,
            Attributes attributes) throws SAXException {
        String[] atri = null;
        System.out.print("\nEtiqueta -> " + localName);
        //Recorremos los atributos
        System.out.println("\t" + attributes.getLength() + " atributos: ");
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.print("\t" + attributes.getQName(i) + ": " + attributes.getValue(i));

        }

    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.println("\tTexto: " + String.valueOf(ch, start, length));
         todo.add(String.valueOf(ch, start, length));
         if (todo.size() ==5) {
            Empleado empleado = new Empleado(Integer.parseInt(todo.get(0)), todo.get(1), todo.get(2), todo.get(3), Float.parseFloat(todo.get(4)));
            lista.add(empleado);
            todo.removeAll(todo);
        }
       

    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        System.out.println("fin " + localName);

    }
}
