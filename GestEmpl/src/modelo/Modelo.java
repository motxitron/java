package modelo;

import com.thoughtworks.xstream.XStream;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import entidades.Empleado;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import javax.xml.bind.annotation.*;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Jc
 */
@XmlRootElement(name = "Empleados")
public class Modelo extends InputSource {

    private ArrayList<Empleado> empleados;
    private File empleadosDelimitado;
    private File empleadosEncolumnado;
    private File empleadosBinario;
    private File empleadosObjeto;
    private File empleadosXML;
    private File empleadosSAX;
    private File empleadosXStream;
    private File empeadosJAXB;

    public Modelo() {
        this.empleadosDelimitado = new File("empleados.del");
        this.empleadosEncolumnado = new File("empleados.encol");
        this.empleadosBinario = new File("empleados.bin");
        this.empleadosObjeto = new File("empleados.obj");
        this.empleadosXML = new File("empleados.xml");
        this.empleadosSAX = new File("empleadossax.xml");
        this.empleadosXStream = new File("empleadosxstream.xml");
        this.empeadosJAXB = new File("empeadosJAXB.xml");
        empleados = new ArrayList<>();
    }

    public void mostrarEmpleados() {

        // Fácil usando el método de Empleado toEncFormat()
        for (int i = 0; i < empleados.size(); i++) {

            System.out.println(empleados.get(i).toEncFormat());
        }
        /*
        public String toEncFormat() {
        String temp = String.format("%4d  %-20s%-20s%-20s%10.2f", id, nombre, apell1, apell2, salario);
        return temp;
         */

    }

    public void generarAleatorios(int n) {

        for (int i = 0; i < n; i++) {
            entidades.Empleado empl = new Empleado();
            empl.emp_aleatorio();
            empleados.add(empl);

        }
        // Fácil usando el método de Empleado emp_aleatorio()
    }

    @XmlElement(name = "empleado")
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    @XmlTransient
    public File getEmpleadosXStream() {
        return empleadosXStream;
    }

    public void setEmpleadosXStream(File empleadosXStream) {
        this.empleadosXStream = empleadosXStream;
    }

    @XmlTransient
    public File getEmpleadosDelimitado() {
        return empleadosDelimitado;
    }

    @XmlTransient
    public File getEmpleadosEncolumnado() {
        return empleadosEncolumnado;
    }

    @XmlTransient
    public File getEmpeadosJAXB() {
        return empeadosJAXB;
    }

    public void setEmpeadosJAXB(File empeadosJAXB) {
        this.empeadosJAXB = empeadosJAXB;
    }

    @XmlTransient
    public File getEmpleadosSAX() {
        return empleadosSAX;
    }

    public void setEmpleadosSAX(File empleadosSAX) {
        this.empleadosSAX = empleadosSAX;
    }

    @XmlTransient
    public File getEmpleadosObjeto() {
        return empleadosObjeto;
    }

    public void setEmpleadosObjeto(File empleadosObjeto) {
        this.empleadosObjeto = empleadosObjeto;
    }

    public void setEmpleadosEncolumnado(File empleadosEncolumnado) {
        this.empleadosEncolumnado = empleadosEncolumnado;
    }

    @XmlTransient
    public File getEmpleadosBinario() {
        return empleadosBinario;
    }

    public void setEmpleadosBinario(File empleadosBinario) {
        this.empleadosBinario = empleadosBinario;
    }

    public void setEmpleadosDelimitado(File empleadosDelimitado) {
        this.empleadosDelimitado = empleadosDelimitado;
    }

    @XmlTransient
    public File getEmpleadosXML() {
        return empleadosXML;
    }

    public void setEmpleadosXML(File empleadosXML) {
        this.empleadosXML = empleadosXML;
    }

    @Override
    public String toString() {
        return "Modelo{" + "empleados=" + empleados + '}';
    }

    public void exportDelTo(File f, String delim) throws IOException {
        List<String> lista = new ArrayList<>();

        for (int i = 0; i < empleados.size(); i++) {
            lista.add(empleados.get(i).toDelimitedString(delim));
        }
        Files.write(f.toPath(), lista);
        // System.out.println(Files.readAllLines(f.toPath()));
        //Completar usando toDelimitedString() de Empleado
        // y Files.write()
    }

    public void importDelFrom(File f, String delim) throws IOException {
        List<String> empleadosEnDelimitado = null;
        empleadosEnDelimitado = Files.readAllLines(f.toPath());
        for (int i = 0; i < empleadosEnDelimitado.size(); i++) {
            entidades.Empleado emp = new Empleado(empleadosEnDelimitado.get(i), delim);
            empleados.add(emp);
            // System.out.println(emp.toEncFormat());
        }
        //se utiliza contructor f y delim
        // y Files.readAllLines()
    }

    public void exportEncTo(File f, int longis[]) throws IOException {
        List<String> lista = new ArrayList<>();

        for (int i = 0; i < empleados.size(); i++) {
            lista.add(empleados.get(i).toColumnadoString(longis));
        }
        Files.write(f.toPath(), lista);
        //System.out.println(Files.readAllLines(f.toPath())); //

        //Completar usando toColumnadodString() de Empleado
        // y Files.write()
    }

    public void importarEncFrom(File f, int longis[]) throws IOException {
        List<String> lista = null;

        lista = Files.readAllLines(f.getAbsoluteFile().toPath());
        for (int i = 0; i < lista.size(); i++) {
            entidades.Empleado empleado = new entidades.Empleado(lista.get(i), longis);
            empleados.add(empleado);
            //System.out.println(empleado.toColumnadoString(longis));
        }

        //Completar usando un constructor de Empleado
        // y Files.readAllLines()
    }

    public void saveEmpleados(File f) {
        try {
            DataOutputStream dos;
            BufferedOutputStream bos;
            FileOutputStream fos;
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            dos.writeInt(empleados.size());
            for (Empleado empl : empleados) {
                dos.writeInt(empl.getId());
                dos.writeUTF(empl.getNombre());
                dos.writeUTF(empl.getApell1());
                dos.writeUTF(empl.getApell2());
                dos.writeFloat(empl.getSalario());
            }
            dos.close();
        } catch (Exception ex) {
            System.err.println("No fue posible crear " + f.getName());
        }
    }

    public void readEmpleados(File f) {
        try {
            DataInputStream dis;
            BufferedInputStream bis;
            FileInputStream fis;
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            int numEmpleados = dis.readInt();
            for (int i = 0; i < numEmpleados; i++) {
                Empleado empl = new Empleado();
                empl.setId(dis.readInt());
                empl.setNombre(dis.readUTF());
                empl.setApell1(dis.readUTF());
                empl.setApell2(dis.readUTF());
                empl.setSalario(dis.readFloat());
                this.getEmpleados().add(empl);
            }
            dis.close();
        } catch (Exception ex) {
            System.err.println("No fue posible leer " + f.getName());
        }
    }

    public void saveEmpleadosAsObject(File f) {
        try {
            ObjectOutputStream oos;
            BufferedOutputStream bos;
            FileOutputStream fos;
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this.empleados);
            oos.close();
        } catch (Exception ex) {
            System.err.println("No fue posible escribir el archivo " + f.getName());
            System.err.println(ex.toString());
        }
    }

    public void readAEmpleadosAsObject(File f) {
        ObjectInputStream ois;
        BufferedInputStream bis;
        FileInputStream fis;
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);
                ois = new ObjectInputStream(bis);
                this.empleados = (ArrayList<Empleado>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("No fue posible leer " + f.getName() + ", saliendo.");
                System.err.println(ex.toString());
                System.exit(1);
            }
        }
    }

    public void expEmpXML(File empleadosXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            for (Empleado empleado : empleados) {
                Element emp = document.createElement("empleado");
                document.getDocumentElement().appendChild(emp);

                Element id = document.createElement("id");
                Text idValue = document.createTextNode(String.valueOf(empleado.getId()));
                emp.appendChild(id);
                id.appendChild(idValue);

                Element nombre = document.createElement("nombre");
                Text nombreValue = document.createTextNode(String.valueOf(empleado.getNombre()));
                emp.appendChild(nombre);
                nombre.appendChild(nombreValue);

                Element apell1 = document.createElement("apell1");
                Text apell1Value = document.createTextNode(String.valueOf(empleado.getApell1()));
                emp.appendChild(apell1);
                apell1.appendChild(apell1Value);

                Element apell2 = document.createElement("apell2");
                Text apell2Value = document.createTextNode(String.valueOf(empleado.getApell2()));
                emp.appendChild(apell2);
                apell2.appendChild(apell2Value);

                Element salario = document.createElement("salario");
                Text salarioValue = document.createTextNode(String.valueOf(empleado.getSalario()));
                emp.appendChild(salario);
                salario.appendChild(salarioValue);
            }
            //se crea la fuente XML partir del documento
            Source source = new DOMSource(document);
            //se crea el fichero de texto Empleados.xml
            Result result = new StreamResult(this.empleadosXML);
            // se crea un TransformerFactory
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            //se transforma el árbol al fichero
            transformer.transform(source, result);

            //se visualiza el documento por pantalla
//            Result console = new StreamResult(System.out);
//            transformer.transform(source, console);
        } catch (Exception e) {
            System.out.println("No se puede crear el archivo xml");
        }
    }

    public void impEmpXML(File empleadosXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            //Pasamos un fichero XML a un árbol DOM usando el DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Se crea un Java DOM XML parser(analizador sintáctico)
            Document document = builder.parse(empleadosXML);
            document.getDocumentElement().normalize();
            //Se visualiza el nombre del elemento raíz
            //1System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
            NodeList listaempleados = document.getElementsByTagName("empleado");
            for (int i = 0; i < listaempleados.getLength(); i++) {
                Node empleado = listaempleados.item(i); //obtiene un nodo
                if (empleado.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) empleado;
//                    System.out.println("Id: " + getNodo("id", elemento));
//                    System.out.println("Nombre: " + getNodo("nombre", elemento));
//                    System.out.println("Apell1: " + getNodo("apell1", elemento));
//                    System.out.println("Apell2: " + getNodo("apell2", elemento));
//                    System.out.println("Salario: " + getNodo("salario", elemento));
                    Empleado empl = new Empleado(Integer.parseInt(getNodo("id", elemento)), getNodo("nombre", elemento), getNodo("apell1", elemento), getNodo("apell2", elemento), Float.parseFloat(getNodo("salario", elemento)));
                    this.empleados.add(empl);
                }
            }
        } catch (Exception e) {
            System.out.println("No se puede importar el archivo xml");
        }
    }

    private static String getNodo(String tag, Element elem) {
        NodeList nodo = elem.getElementsByTagName(tag).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();
    }

    public void impEmpXmlSax(File fichero) throws SAXException, IOException, SAXException {
        try {
            // Creamos la factoria de parseadores por defecto  
            XMLReader reader = XMLReaderFactory.createXMLReader();
            // Añadimos nuestro manejador al reader  
            LectorXML miLector = new LectorXML();
            reader.setContentHandler(miLector);
            // Procesamos el xml de ejemplo  
            reader.parse(new InputSource(new FileInputStream("empleados.xml")));
            setEmpleados(miLector.getLista());
        } catch (SAXException | IOException e) {
        }
    }

    public void expEmpXmlSax(File empleadosSAX) throws TransformerException {
        XMLReader datosReader = (XMLReader) new EscribirXMLSAX();
        InputSource datosSource = this;
        Source source = new SAXSource(datosReader, datosSource);
        Result resultado = new StreamResult(empleadosSAX);
        Transformer transformer
                = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(source, resultado);
    }

    public void DeserializarXMLconXStream(File empleadosXStream) throws FileNotFoundException {
        XStream xstream = new XStream();
        xstream.alias("Empleados", List.class);
        xstream.alias("Empleado", entidades.Empleado.class);
        empleados = (ArrayList<Empleado>) xstream.fromXML(new FileInputStream(empleadosXStream));
    }

    public void SerializarXMLconXStream(File empleadosXStream) throws FileNotFoundException {
        XStream xstream = new XStream();
        xstream.alias("Empleados", List.class);
        xstream.alias("Empleado", entidades.Empleado.class);
        xstream.toXML(empleados, new FileOutputStream(empleadosXStream));
    }

    public void Estilo() throws IOException, TransformerException {
        FileOutputStream fos = new FileOutputStream("empleado.html");
        Source estilos = new StreamSource("empleadosplantilla.xsl");
        Source datos = new StreamSource("empleados.xml");
        Result result = new StreamResult(fos);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
        transformer.transform(datos, result);
        fos.close();
    }

    public void expXMLJaxb(File empleadosJAXB) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance(Modelo.class);
        Marshaller marshaller = contexto.createMarshaller();
        marshaller.marshal(this, empleadosJAXB);
    }

    public void impXMLJaxb(File empeadosJAXB) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance(Modelo.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        Modelo lista = (Modelo) unmarshaller.unmarshal(this.empeadosJAXB);
        this.setEmpleados(lista.getEmpleados());
    }
}
