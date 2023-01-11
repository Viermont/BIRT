/********************************************************************************************************
 *Autor: Javier Montero Martinez      *Fecha: 23/12/2022       *M�dulo: Programaci�n        *Unidad: 04       
  
 *Tarea: Tarea Evaluaci�n 01. Realiza un programa en Java (100%)
   
 *Descripci�n: El objetivo de este programa es trabajar la lectura y escritura de ficheros de texto y
 el manejo de cadenas de caracteres.

 *Autoevaluaci�n: https://drive.google.com/drive/folders/1LCq9RRBdC7E5HacaDeW94u_lioP3nQPt?usp=share_link
   
*********************************************************************************************************/
import java.util.*;
import java.io.*;

public class MadLib {

    /*
     * M�todo principal: crea un objeto de tipo Scanner, llama al m�todo intro, despu�s al m�todo menu,
     * al que le pasa el objeto de tipo Scanner teclado como par�metro, para finalizar muestra un mensaje
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
     * M�todo intro: explica lo que hace el programa.
     * No tiene par�metros y no devuelve ning�n valor.
     */
    public static void intro() {
        System.out.println("\nBienvenidos y bienvenidas al juego de los cuentos locos.\n"
        + "El programa te pedir� que introduzcas una serie de palabras\n"
        + "que se utilizar�n para completar una historia.\n"
        + "El resultado se guardar� en un fichero.\n"
        + "Puedes leer esas historias siempre que quieras.");
    }

    /*
     * M�todo menu: men� principal del programa, pide elegir por teclado una de las 3 opciones, llama
     * al m�todo compruebaMenu, dependiendo del valor recibido de vuelta decide c = crear MadLib (llama
     * al m�todo crear), v = ver MadLib (llama al m�todo ver), s = salir (cambia el valor booleano para
     * que no cumpla la condici�n y salga) o r = repetir men� (repite el bucle). En caso contrario
     * vuelve al main.
     * Par�metros:    Scanner teclado: objeto de tipo Scanner que se usar� para recibir por teclado la
     *                    elecci�n del usuario.
     * No devuelve ning�n valor.
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
                + "Elija su opci�n: ");
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

    /* M�todo compruebaMenu: comprueba que la primera letra de la string introducida por el usuario
     * sea c = crear, v = ver o s = salir. Si no es ninguna de ellas devuelve r = repetir men�.
     * Par�metros:    String opcion: opci�n elegida por el usuario del programa en el men�.
     * Return:        valor de tipo char con la primera letra de la opci�n elegida.
     */
    public static char compruebaMenu(String opcion) {
        opcion = opcion.toLowerCase();
        if (opcion.charAt(0) == 'c' || opcion.charAt(0) == 'v' || opcion.charAt(0) == 's') {
            return opcion.charAt(0);
        } else {
            return 'r';
        }
    }

    /* M�todo crear: solicita por teclado el fichero a leer a partir del cual crear el cuento, llama
     * al m�todo compruebaNombre para que vea si el fichero existe, solicita por teclado el nombre del
     * nuevo fichero, lo crea y llama al m�todo leerFichero para que cuente su n�mero de l�neas.
     * Comprueba que el fichero se pueda leer y contempla y muestra las posibles excepciones al leer el
     * fichero si se produjeran. Vuelve a conectar con el fichero a leer, mientras lo recorre l�nea por
     * l�nea y mediante un bucle, saca cada palabra de cada l�nea, comprueba si cumple las condiciones
     * para ser sustituida por otra, en cuyo caso llama al m�todo cambiaPalabras, que devolver� la nueva
     * palabra. Finalmente y antes de pasar a la nueva palabra, la escribira en el nuevo fichero creado
     * anteriormente.
     * Par�metros:    Scanner teclado: objeto de tipo Scanner que se usar� para recibir por teclado las
     *                    elecciones del usuario.
     * No devuelve ning�n valor.
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
            System.out.println("se ha producido una excepci�n: " + excepcionNoEncontrado.getMessage() + "\n");
            crear(teclado);
        } catch (Exception excepcion) {
            System.out.println("se ha producido una excepci�n: " + excepcion.getMessage() + "\n");
            crear(teclado);
        }
        if (leerFichero != null) {
            leerFichero.close();
        }
    }

    /* M�todo ver: muestra por consola un texto y llama al m�todo compruebaNombre para que vea si existe,
     * en cuyo caso llama al m�todo leerFichero.
     * Par�metros:    Scanner teclado: objeto de tipo Scanner que no se usar� en el m�todo pero
     *                    si se pasar� como par�metro a otros m�todos.
     * No devuelve nada.
     */
    public static void ver(Scanner teclado) {
        System.out.print("\n\nVer un cuento:");
        String ficheroEntrada = compruebaNombre(teclado);
        int contlineas = leerFichero(ficheroEntrada, teclado, false);
    }

    /* M�todo compruebaNombre: solicita el nombre del fichero a leer, conecta con el y si existe, devuelve
     * su nombre. En caso que no exista solicita de nuevo el nombre del fichero.
     * Par�metros:    Scanner teclado: objeto de tipo Scanner para leer por teclado el nombre del fichero.
     * Return:        devuelve una String con el nombre del fichero a leer.
     */
    public static String compruebaNombre(Scanner teclado) {
        System.out.print("\nNombre del fichero que quieres leer: ");
        String ficheroEntrada = teclado.next();
        File fichero = new File(ficheroEntrada);
        while (!fichero.exists()) {
            System.out.print("\nFichero no encontrado. Int�ntalo otra vez\n"
            + "Nombre del fichero: ");
            ficheroEntrada = teclado.next();
            fichero = new File(ficheroEntrada);
            System.out.println();
        }
        return ficheroEntrada;
    }

    /* M�todo leerFichero:  sirve tanto para recorrer el fichero contando sus l�neas como para mostrar
     * por consola el contenido de dichas l�neas. Comprueba que el fichero se pueda leer y contempla y
     * muestra las posibles excepciones al leer el fichero si se produjeran. Llama al m�todo
     * cambiaPalabras para que compruebe cada palabra del fichero por si alguna cumple las condiciones
     * para ser ser cambiada por la que el usuario elija.
     * Par�metros:    String ficheroEntrada: String con el nombre del fichero a leer.
     *                Scanner teclado: objeto de tipo Scanner que no se usar� en el m�todo pero
     *                    si se pasar� como par�metro a otro m�todo.
     *                boolean contar: valor booleano que indica si s�lo se cuantan las l�neas del
     *                    fichero o si tambi�n se mustran por consola dichas l�neas.
     */
    public static int leerFichero(String ficheroEntrada, Scanner teclado, boolean contar) {
        int contLineas = 0;
        File fichero = new File(ficheroEntrada);
        while (!fichero.canRead()) {
            System.out.print("No se puede leer este fichero. Int�ntalo otra vez\n");
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
            System.out.println("se ha producido una excepci�n: " + excepcionNoEncontrado.getMessage() + "\n");
            ver(teclado);
        } catch (Exception excepcion) {
            System.out.println("se ha producido una excepci�n: " + excepcion.getMessage() + "\n");
            ver(teclado);
        }
        if (leerFichero != null) {
            leerFichero.close();
        }
        return contLineas;
    }

    /* M�todo cambiaPalabras: m�todo que recibe la palabra a ser modificada, la cambia por la que
     * el usuario introduzca por teclado y la devuelve.
     * Par�metros:    String palabra: String con la palabra a cambiar.
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