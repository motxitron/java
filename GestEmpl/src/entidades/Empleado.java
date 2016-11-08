/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jc
 */
@XmlRootElement(name = "empleado")
@XmlType(propOrder = {"id", "nombre", "apell1", "apell2", "salario"})
public class Empleado implements Serializable {

    private int id;
    private String nombre;
    private String apell1;
    private String apell2;
    private float salario;
    private static int identif = 0;
    private static final String[] nombres = {"Juan", "Ricardo", "Rosa", "Ana", "Sandra", "Laura", "Mario", "José", "Gael", "Clara", "Carlos", "Paz"};
    private static final String[] apellidos = {"Wayne", "García", "Castro", "Lane", "Cano", "Pérez", "Martín", "Gil", "Iglesias", "Sánchez"};
    private static final float[] salarios = {900, 1200, 1600, 1800, 2000, 2200, 3000};

    public Empleado() {
        this.id = identif++;
    }

    public Empleado(int id, String nombre, String apell1, String apell2, float salario) {
        this.id = id;
        this.nombre = nombre;
        this.apell1 = apell1;
        this.apell2 = apell2;
        this.salario = salario;
    }

    public void emp_aleatorio() {
        Random r = new Random();
        setNombre(nombres[r.nextInt(nombres.length)]);
        setApell1(apellidos[r.nextInt(apellidos.length)]);
        setApell2(apellidos[r.nextInt(apellidos.length)]);
        setSalario(salarios[r.nextInt(salarios.length)]);
    }

    public void emp_teclado() {

        Scanner sc = new Scanner(System.in, System.getProperty("os.name").contains("Windows") ? "iso-8859-1" : "UTF-8");
        System.out.println("Nombre: ");
        setNombre(sc.nextLine());
        System.out.println("Apellido 1: ");
        setApell1(sc.nextLine());
        System.out.println("Apellido 2: ");
        setApell2(sc.nextLine());
        setSalario(readFloat("Salario: "));
    }

    public Empleado(String linea, String delim) {
        // Se fragmente la línea en campos con split. El primero es la idProveedor
        String[] datosEmpleado = linea.split(delim);
        this.id = Integer.parseInt(datosEmpleado[0]);
        this.nombre = datosEmpleado[1];
        this.apell1 = datosEmpleado[2];
        this.apell2 = datosEmpleado[3];
        this.salario = Float.parseFloat(datosEmpleado[4]);

    }

    public Empleado(String s, int longis[]) {
        // Completar. Se recomienda subString

        this.id = Integer.parseInt(s.substring(0, longis[0]).trim());
        this.nombre = s.substring(longis[0], (longis[1] + longis[0]));
        this.apell1 = s.substring((longis[1] + longis[0]), ((longis[1] + longis[0]) + longis[2]));
        this.apell2 = s.substring(((longis[1] + longis[0]) + longis[2]), (((longis[1] + longis[0]) + longis[2]) + longis[3]));
        this.salario = Float.parseFloat(s.substring((((longis[1] + longis[0]) + longis[2]) + longis[3]), (((longis[1] + longis[0]) + longis[2]) + longis[3] + longis[4])).trim().replace(",", "."));
    }

    /**
     * @return the id
     */
    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apell1
     */
    @XmlElement(name = "apell1")
    public String getApell1() {
        return apell1;
    }

    /**
     * @param apell1 the apell1 to set
     */
    public void setApell1(String apell1) {
        this.apell1 = apell1;
    }

    /**
     * @return the apell2
     */
    @XmlElement(name = "apell2")
    public String getApell2() {
        return apell2;
    }

    /**
     * @param apell2 the apell2 to set
     */
    public void setApell2(String apell2) {
        this.apell2 = apell2;
    }

    /**
     * @return the salario
     */
    @XmlElement(name = "salario")
    public float getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", apell1=" + apell1 + ", apell2=" + apell2 + ", salario=" + salario + '}';
    }

    public String toEncFormat() {
        String temp = String.format("%-4d%-20s%-20s%-20s%10.2f", id, nombre, apell1, apell2, salario);
        return temp;
    }

    static public float readFloat(String prompt) {
        Scanner sc = new Scanner(System.in,
                System.getProperty("os.name").contains("Windows") ? "iso-8859-1" : "UTF-8");
        float tempNumber = 0;
        String temp;
        boolean numberOkay;
        do {
            numberOkay = false;
            do {
                System.out.printf("%s ", prompt);
                temp = sc.nextLine();
            } while (temp.isEmpty());
            try {
                tempNumber = Float.parseFloat(temp);
                numberOkay = true;
            } catch (Exception e) {
                System.out.printf("%nEse número no es correcto. Pruebe de nuevo.%n");
                numberOkay = false;
            }
        } while (!numberOkay);
        return tempNumber;
    }

    public String toDelimitedString(String delim) {
        StringBuilder s = new StringBuilder();
        s.append(this.id + delim + this.nombre + delim + this.apell1 + delim + this.apell2 + delim + this.salario);
        //completar
        return s.toString();
    }

    public String toColumnadoString(int longis[]) {

        String s = "%" + longis[0] + "d%-" + longis[1] + "s%-" + longis[2] + "s%-" + longis[3] + "s%" + longis[4] + "f";
        //completar
        //"%4d  %-20s%-20s%-20s%10.2f"
        return String.format(s, this.id, this.nombre, this.apell1, this.apell2, this.salario);
    }
}
