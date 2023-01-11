/********************************************************************************************************
 *Autor: Javier Montero Martinez      *Fecha: 23/12/2022       *Módulo: Programación        *Unidad: 04       
  
 *Tarea: Tarea Evaluación 01. Realiza un programa en Java (100%)
   
 *Descripción: El objetivo de este programa es trabajar la lectura y escritura de ficheros de texto y
 el manejo de cadenas de caracteres.

 *Autoevaluación: https://drive.google.com/drive/folders/1LCq9RRBdC7E5HacaDeW94u_lioP3nQPt?usp=share_link
   
*********************************************************************************************************/
import java.util.*;
import java.io.*;

public class MadLib {

    /*
     * Método principal: crea un objeto de tipo Scanner, llama al método intro, después al método menu,
     * al que le pasa el objeto de tipo Scanner teclado como parámetro, para finalizar muestra un mensaje
     * de despedida y cierra el objeto Scanner teclado.
     */
    public static void main (String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        intro();
        menu(teclado);
        System.out.println("\n\nAgur");
        teclado.close();
    }

    /*
     * Método intro: explica lo que hace el programa.
     * No tiene parámetros y no devuelve ningún valor.
     */
    public static void intro() {
        System.out.println("\nBienvenidos y bienvenidas al juego de los cuentos locos.\n"
        + "El programa te pedirá que introduzcas una serie de palabras\n"
        + "que se utilizarán para completar una historia.\n"
        + "El resultado se guardará en un fichero.\n"
        + "Puedes leer esas historias siempre que quieras.");
    }

    /*
     * Método menu: menú principal del programa, pide elegir por teclado una de las 3 opciones, llama
     * al método compruebaMenu, dependiendo del valor recibido de vuelta decide c = crear MadLib (llama
     * al método crear), v = ver MadLib (llama al método ver), s = salir (cambia el valor booleano para
     * que no cumpla la condición y salga) o r = repetir menú (repite el bucle). En caso contrario
     * vuelve al main.
     * Parámetros:    Scanner teclado: objeto de tipo Scanner que se usará para recibir por teclado la
     *                    elección del usuario.
     * No devuelve ningún valor.
     */
    public static void menu(Scanner teclado) throws FileNotFoundException {
        boolean volverMenu = true;
        while (volverMenu) {
            String opcion = "";
            char eleccion;
            do {
                System.out.print("\n******* MENU *******\n"
                + "(C)rear un \"Mad Lib\"\n"
                + "(V)er un \"Mad Lib\"\n"
                + "(S)alir\n"
                + "Elija su opción: ");
                opcion = teclado.next();
                eleccion = compruebaMenu(opcion);
            } while (eleccion == 'r');
            if (eleccion == 'c') {
                crear(teclado);
            } else if (eleccion == 'v') {
                ver(teclado);
            } else {
                volverMenu = false;
            }
        }
    }

    /* Método compruebaMenu: comprueba que la primera letra de la string introducida por el usuario
     * sea c = crear, v = ver o s = salir. Si no es ninguna de ellas devuelve r = repetir menú.
     * Parámetros:    String opcion: opción elegida por el usuario del programa en el menú.
     * Return:        valor de tipo char con la primera letra de la opción elegida.
     */
    public static char compruebaMenu(String opcion) {
        opcion = opcion.toLowerCase();
        if (opcion.charAt(0) == 'c' || opcion.charAt(0) == 'v' || opcion.charAt(0) == 's') {
            return opcion.charAt(0);
        } else {
            return 'r';
        }
    }

    /* Método crear: solicita por teclado el fichero a leer a partir del cual crear el cuento, llama
     * al método compruebaNombre para que vea si el fichero existe, solicita por teclado el nombre del
     * nuevo fichero, lo crea y llama al método leerFichero para que cuente su número de líneas.
     * Comprueba que el fichero se pueda leer y contempla y muestra las posibles excepciones al leer el
     * fichero si se produjeran. Vuelve a conectar con el fichero a leer, mientras lo recorre línea por
     * línea y mediante un bucle, saca cada palabra de cada línea, comprueba si cumple las condiciones
     * para ser sustituida por otra, en cuyo caso llama al método cambiaPalabras, que devolverá la nueva
     * palabra. Finalmente y antes de pasar a la nueva palabra, la escribira en el nuevo fichero creado
     * anteriormente.
     * Parámetros:    Scanner teclado: objeto de tipo Scanner que se usará para recibir por teclado las
     *                    elecciones del usuario.
     * No devuelve ningún valor.
     */
    public static void crear(Scanner teclado) throws FileNotFoundException {
        //Guarda nombre fichero entrada
        System.out.print("\n\nCrear un cuento:");
        String nombreFicheroEntrada = compruebaNombre(teclado);

        //Guarda nombre fichero salida
        System.out.print("\nNombre del fichero de salida: ");
        String nombreFicheroSalida = teclado.next();

        //Conecta y crea el fichero de salida
        File ficheroSalida = new File(nombreFicheroSalida);
        PrintStream escribirFichero = new PrintStream(ficheroSalida);
        int contlineas = leerFichero(nombreFicheroEntrada, teclado, true);
        File ficheroEntrada = new File (nombreFicheroEntrada);
        
        Scanner leerFichero = null;
        try {
            leerFichero = new Scanner(ficheroEntrada);
            for(int i = 1; i <= contlineas; i++) {
                String linea = leerFichero.nextLine();
                Scanner leerLinea = new Scanner(linea);
                while (leerLinea.hasNext()) {
                    String palabra = leerLinea.next();
                    if (palabra.charAt(0) == '<' && palabra.charAt(palabra.length() - 1) == '>') {
                        palabra = palabra.substring(1,palabra.length() - 1);
                        palabra = cambiaPalabras(palabra, teclado);
                    }
                    escribirFichero.print(palabra + " ");
                }
                escribirFichero.println();
            }
            System.out.print("\nEl cuento loco ha sido creado");
        } catch (FileNotFoundException excepcionNoEncontrado) {
            System.out.println("se ha producido una excepción: " + excepcionNoEncontrado.getMessage() + "\n");
            crear(teclado);
        } catch (Exception excepcion) {
            System.out.println("se ha producido una excepción: " + excepcion.getMessage() + "\n");
            crear(teclado);
        }
        if (leerFichero != null) {
            leerFichero.close();
        }
    }

    /* Método ver: muestra por consola un texto y llama al método compruebaNombre para que vea si existe,
     * en cuyo caso llama al método leerFichero.
     * Parámetros:    Scanner teclado: objeto de tipo Scanner que no se usará en el método pero
     *                    si se pasará como parámetro a otros métodos.
     * No devuelve nada.
     */
    public static void ver(Scanner teclado) {
        System.out.print("\n\nVer un cuento:");
        String ficheroEntrada = compruebaNombre(teclado);
        int contlineas = leerFichero(ficheroEntrada, teclado, false);
    }

    /* Método compruebaNombre: solicita el nombre del fichero a leer, conecta con el y si existe, devuelve
     * su nombre. En caso que no exista solicita de nuevo el nombre del fichero.
     * Parámetros:    Scanner teclado: objeto de tipo Scanner para leer por teclado el nombre del fichero.
     * Return:        devuelve una String con el nombre del fichero a leer.
     */
    public static String compruebaNombre(Scanner teclado) {
        System.out.print("\nNombre del fichero que quieres leer: ");
        String ficheroEntrada = teclado.next();
        File fichero = new File(ficheroEntrada);
        while (!fichero.exists()) {
            System.out.print("\nFichero no encontrado. Inténtalo otra vez\n"
            + "Nombre del fichero: ");
            ficheroEntrada = teclado.next();
            fichero = new File(ficheroEntrada);
            System.out.println();
        }
        return ficheroEntrada;
    }

    /* Método leerFichero:  sirve tanto para recorrer el fichero contando sus líneas como para mostrar
     * por consola el contenido de dichas líneas. Comprueba que el fichero se pueda leer y contempla y
     * muestra las posibles excepciones al leer el fichero si se produjeran. Llama al método
     * cambiaPalabras para que compruebe cada palabra del fichero por si alguna cumple las condiciones
     * para ser ser cambiada por la que el usuario elija.
     * Parámetros:    String ficheroEntrada: String con el nombre del fichero a leer.
     *                Scanner teclado: objeto de tipo Scanner que no se usará en el método pero
     *                    si se pasará como parámetro a otro método.
     *                boolean contar: valor booleano que indica si sólo se cuantan las líneas del
     *                    fichero o si también se mustran por consola dichas líneas.
     */
    public static int leerFichero(String ficheroEntrada, Scanner teclado, boolean contar) {
        int contLineas = 0;
        File fichero = new File(ficheroEntrada);
        while (!fichero.canRead()) {
            System.out.print("No se puede leer este fichero. Inténtalo otra vez\n");
            ficheroEntrada = compruebaNombre(teclado);
        }
        Scanner leerFichero = null;
        try {
            leerFichero = new Scanner(fichero);
            while (leerFichero.hasNextLine()) {
                String linea = leerFichero.nextLine();
                if (!contar) {
                    System.out.println(linea);
                }
                contLineas++;
            }        
        } catch (FileNotFoundException excepcionNoEncontrado) {
            System.out.println("se ha producido una excepción: " + excepcionNoEncontrado.getMessage() + "\n");
            ver(teclado);
        } catch (Exception excepcion) {
            System.out.println("se ha producido una excepción: " + excepcion.getMessage() + "\n");
            ver(teclado);
        }
        if (leerFichero != null) {
            leerFichero.close();
        }
        return contLineas;
    }

    /* Método cambiaPalabras: método que recibe la palabra a ser modificada, la cambia por la que
     * el usuario introduzca por teclado y la devuelve.
     * Parámetros:    String palabra: String con la palabra a cambiar.
     *                Scanner teclado: objeto de tipo Scanner para que el usuario pueda teclear la
     *                    nueva palabra.
     * Return:        Devuelve una String con la palabra modificada.
     */
    public static String cambiaPalabras(String palabra, Scanner teclado) {
        palabra = palabra.replaceAll("-"," ");
        System.out.print("\n" + palabra + ": ");
        palabra = teclado.next();
        return palabra;
    }
}