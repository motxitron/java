/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestempl;

import java.io.IOException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import jdk.internal.org.xml.sax.SAXException;
import modelo.Modelo;
import vista.Vista;

/**
 *
 * @author Jc
 */
public class GestEmpl {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, SAXException, org.xml.sax.SAXException, TransformerException, JAXBException {
        Modelo m = new Modelo();
        Vista v = new Vista(m);
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        String menu = "\nInicio: 01 Mostrar empleados, 02 Generar aleatorios, 03 Alta de empleado\n"
                + "Ficheros texto: 11 Escribir delimitado, 12 Escribir encolumnado, 13 Leer delimitado, 14 Leer encolumnado\n"
                + "Ficheros binarios: 15 Exportar binario, 16 Importar binario 17 Exportar objeto 18 Importar objeto\n"
                +"Fichero XML: 20 Exportar XML, 21 Importar XML, 22 Exportar XML con SAX, 23 Importar XML con SAX\n"
                +"XStream: 24 Serializar XML con XStream, 25 Deserializar XML con XStream\n"
                +"Estilo: 26 Dar estilo a un XML\n"
                +"JAXB: 27 Exportar, 28 Importar\n"
                + "Salir\n\n"
                + "Escriba una opción: ";
        String opcion;
        do {
            System.out.print(menu);
            opcion = sc.nextLine();
            switch (opcion.toLowerCase()) {
                case "01":
                case "mostrar empleados":
                case "mostrar":
                    m.mostrarEmpleados();
                    break;
                case "02":
                case "generar aleatorios":
                case "generar":
                case "aleatorios":
                    v.cargar_aleatorios();
                    break;
                case "03":
                case "alta de empleado":
                case "alta":
                    v.altaEmpleado();
                    break;
                case "11":
                case "escribir delimitado":
                    v.escribirDelimitado();
                    break;
                case "12":
                case "escribir encolumnado":
                    v.escribirEncolumnado();
                    break;
                case "13":
                case "leer delimitado":
                    v.leerDelimitado();
                    break;
                case "14":
                case "leer encolumnado":
                    v.leerEncolumnado();
                    break;
                case "15":
                case "exportar binario":
                    v.exportarBinario();
                    break;
                case "16":
                case "importar binario":
                    v.importarBinario();
                    break;
                case "17":
                case "exportar objeto":
                    v.exportarObjeto();
                    break;
                case "18":
                case "importar objeto":
                    v.importarObjeto();
                    break;
                case "20":
                case "exportar XML con DOM":                    
                    v.exportarXML();
                break;
                case "21":
                case "Importar XML con DOM":
                    v.importarXML();
                break;
                 case "22":
                case "Exportar XML con SAX":
                     v.ExportarXmlSax();
                break;
                 case "23":
                case "Importar XML con SAX":
                    v.ImportarXmlSax();
                break;
                 case "24":
                case "Serializar XML con XStream":
                    v.SerializarXmlXStream();
                break;
                 case "25":
                case "Deserializar XML con XStream":
                    v.DeserializarXmlXStream();
                break;
                case "26":
                    case"Crear pagina con estilo":
                    v.DarEstilo();
                    break;
                    case "27":
                    case"Exportar JAXB":
                    v.expXMLJaxb();
                    break;
                    case "28":
                    case"Importar JAXB":
                    v.impXMLJaxb();
                    break;
                    
                case "salir":
                case "q":
                    salir = true;
                    break;
                default:
                    System.err.printf("%nOpciÃ³n incorrecta%n%n");
                    break;
            } // fin de switch
        } while (!salir);
    }

}
