/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Empleado;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import jdk.internal.org.xml.sax.SAXException;

import modelo.Modelo;

/**
 *
 * @author Jc
 */
public class Vista {

    Modelo m;

    public Vista(Modelo m) {
        this.m = m;
    }

    public void cargar_aleatorios() {
        System.out.println("¿Cuántos empleados vas a generar?: ");
        Scanner sc = new Scanner(System.in);
        int ent = sc.nextInt();
        m.generarAleatorios(ent);
    }

    public void altaEmpleado() {
        Empleado empl = new Empleado();
        empl.emp_teclado();
        m.getEmpleados().add(empl);
    }

    public void escribirDelimitado() throws IOException {
        m.exportDelTo(m.getEmpleadosDelimitado(), "#");
    }

    public void leerDelimitado() throws IOException {
        m.importDelFrom(m.getEmpleadosDelimitado(), "#");
    }

    public void escribirEncolumnado() throws IOException {
        int longis[] = {4, 20, 20, 20, 12};
        m.exportEncTo(m.getEmpleadosEncolumnado(), longis);
    }

    public void leerEncolumnado() throws IOException {
        int longis[] = {4, 20, 20, 20, 12};
        m.importarEncFrom(m.getEmpleadosEncolumnado(), longis);
    }

    public void exportarBinario() {
        m.saveEmpleados(m.getEmpleadosBinario());
    }

    public void importarBinario() {
        m.readEmpleados(m.getEmpleadosBinario());
    }

    public void exportarObjeto() {
        m.saveEmpleadosAsObject(m.getEmpleadosObjeto());
    }

    public void importarObjeto() {
        m.readAEmpleadosAsObject(m.getEmpleadosObjeto());
    }

    public void exportarXML() {
        m.expEmpXML(m.getEmpleadosXML());
    }

    public void importarXML() {
        m.impEmpXML(m.getEmpleadosXML());
    }
    public void ImportarXmlSax() throws SAXException, org.xml.sax.SAXException, IOException{
        m.impEmpXmlSax(m.getEmpleadosSAX());
    }

    public void ExportarXmlSax() throws TransformerException {
         m.expEmpXmlSax(m.getEmpleadosSAX());
    }

    public void SerializarXmlXStream() throws FileNotFoundException {
        m.SerializarXMLconXStream(m.getEmpleadosXStream());
    }

    public void DeserializarXmlXStream() throws FileNotFoundException {
         m.DeserializarXMLconXStream(m.getEmpleadosXStream());
    }

    public void DarEstilo() throws IOException, TransformerException {
        m.Estilo();
    }
     public void expXMLJaxb() throws IOException, TransformerException, JAXBException {
        m.expXMLJaxb(m.getEmpeadosJAXB());
    }
      public void impXMLJaxb() throws IOException, TransformerException, JAXBException {
        m.impXMLJaxb(m.getEmpeadosJAXB());
    }

} // fin class Vista

