/********************************************************************************************************
 *Autor: Javier Montero Martinez      *Fecha: 18/01/2023       *M�dulo: Programaci�n        *Unidad: 05
  
 *Tarea: Tarea Evaluaci�n 01. Realiza un programa en Java (100%)
   
 *Descripci�n: Esta tarea se centra en el manejo de arrays, ficheros y cadenas de caracteres y necesitar�
 2 ficheros de texto, dna.txt y ecoli.txt.
 
 *Autoevaluaci�n: https://drive.google.com/drive/folders/1KvxW0XEjjZk0n412A_ZLkwds-L9mSGUp?usp=share_link
   
*********************************************************************************************************/
import java.util.*;
import java.io.*;

public class MuestrasADN {
    // Constantes
    public static final int numMinCodones = 5;
    public static final int porcenMasaCitosGuanin = 30;
    public static final int numNucleotidos = 4;
    public static final int nucleotidosCodon = 3;
    public static final double[] masasUnitarias = {135.128, 111.103, 151.128, 125.107, 100.000};

   /*
    * M�todo principal: crea un objeto de tipo Scanner (teclado), crea un Array (arrayNombreFicheros) para
    * guardar el nombre de los ficheros, llama al m�todo Intro y al m�todo nombreFichero, al que le pasa el
    * valor del array por referencia y el objeto de tipo Scanner. LLama al m�todo leerFichero, al que le
    * pasa el array por referencia y del que recibe un objeto (leer) de tipo Scanner con el fichero de
    * entrada. Llama al m�todo escribirFichero al que le pasa un array por referencia, el objeto de tipo
    * Scanner con el fichero de entrada y un String con el tipo de salida del fichero. Finalmente cierra
    * los objetos de tipo Scanner.
    */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        String[] arrayNombreFicheros = new String[2];
        intro();
        nombreFichero(teclado, arrayNombreFicheros);
        Scanner leer = leerFichero(arrayNombreFicheros);
        escribirFichero(arrayNombreFicheros, leer, "fichero");
        leer = leerFichero(arrayNombreFicheros);
        escribirFichero(arrayNombreFicheros, leer, "consola");
        leer.close();
        teclado.close();
    }
    
   /*
    * M�todo intro: explica lo que hace el programa.
    * No tiene par�metros y no devuelve ning�n valor.
    */
    public static void intro() {
        System.out.print("\nEste programa genera informaci�n sobre\n"
        + "secuencias de nucle�tidos de ADN contenidas en un fichero\n"
        + "Tambi�n indicar� si pueden codificar proteinas o no\n"
        + "Todos los resultados se guardar�n en un fichero");
    }
    
   /*
    * M�todo nombreFichero: pide por teclado el nombre del fichero de entrada y salida y los guarda en un array.
    * Par�metros:     Scanner teclado: objeto de tipo Scanner para leer por teclado el nombre del fichero.
    *                 String[] arrayNombreficheros: array de tipo string donde guardar los nombres de archivo.
    * No devuelve ning�n valor.
    */
    public static void nombreFichero(Scanner teclado, String[] arrayNombreFicheros) {
        for (int i = 0; i <2; i++) {
            System.out.print("\nIntroduce el nombre del fichero: ");
            arrayNombreFicheros[i]= teclado.nextLine();
        }
    }

   /*
    * M�todo leerFichero: conecta con el fichero a leer y lo guarda en el objeto de tipo scanner leer.
    * Contempla excepciones en caso de error de lectura.
    * Par�metros:     String[] arrayNombreficheros: array de tipo string donde est�n guardados los nombres de
    *                 archivo.
    * Return:         Devuelve el objeto de tipo Scanner leer.
    */
    public static Scanner leerFichero(String[] arrayNombreFicheros) {
        File ficheroEntrada = new File(arrayNombreFicheros[0]);
        Scanner leer = null;
        try {
            leer = new Scanner(ficheroEntrada);
        } catch (FileNotFoundException excepcion) {
            System.out.println("Error con la conexi�n al fichero");
        } catch (Exception excepcion) {
            System.out.println("Otra excepci�n" + excepcion.getMessage());
        }
        return leer;
    }

   /*
    * M�todo escribirFichero: llama a el el m�todo crearFicheroSalida y dependiendo de lo que devuelva
    * escribir� por teclado o por consola. Recorre cada l�nea del fichero de entrada y en cada una de ellas
    * ellas escribe 1� la descripci�n, 2� llama al m�todo cuentaNucleotidos y muestra la secuencia de
    * nucle�tidos, 3� llama a calculaMasa y mostrar� el porcentaje de masa de cada nucle�tido respecto al
    * total, 4� llama a clasificaCodones y mostrar� los codones de cada secuencia, y 5� llamar� a
    * codificaProteina y mostrar� si la secuencia codifica prote�na o no.
    * Par�metros:     String[] arrayNombreficheros: array de tipo string donde est�n guardados los nombres de
    *                 archivo.
    *                 Scanner leerFichero: objeto de tipo scanner con el fichero de entrada.
    *                 String salida: objeto de tipo estring para saber si tiene que escribir en un fichero o
    *                 por consola.
    * No devuelve ning�n valor.
    */
    public static void escribirFichero(String[] arrayNombreFicheros, Scanner leerFichero, String salida)
    throws FileNotFoundException {
        PrintStream escribir = null;
        escribir = crearFicheroSalida(arrayNombreFicheros, salida);
        System.out.println();
        int contVueltas = 0;
        while (leerFichero.hasNextLine()) {
            int[] contNucleotidos = new int[numNucleotidos];
            int[] contBasura = new int[1];
            double[] porcentajes = new double[4];
            String linea = leerFichero.nextLine();
            if (contVueltas != 0) {
                escribir.println("\n");
                }
            escribir.println("Descripci�n: " + linea);
            linea = leerFichero.nextLine(); 
            escribir.println("Nucle�tidos: " + linea.toUpperCase());
            cuentaNucleotidos(contNucleotidos, contBasura, linea.toUpperCase());
            escribir.println("Contadores: " + Arrays.toString(contNucleotidos));           
            double[] masaTotal = calculaMasa(contNucleotidos, contBasura, porcentajes);  
            escribir.println("Masa (%): " + Arrays.toString(porcentajes) + " de " + masaTotal[0]);
            String[] tresCodones = clasificaCodones(contBasura, linea.toUpperCase());
            escribir.println("Lista Codones: " + Arrays.toString(tresCodones));
            String esProteina = codificaProteina(tresCodones, porcentajes);
            escribir.print("Es prote�na: " + esProteina);
            contVueltas++;
        }
    }

   /*
    * M�todo leerFichero: dependiendo de la salida que se le pida seleccionar� si la salida es por
    * fichero o por consola.
    * Par�metros:     String[] arrayNombreficheros: array de tipo string donde est�n guardados los nombres de
    *                 archivo.
    *                 String salida: objeto de tipo estring para saber si tiene que escribir en un fichero o
    *                 por consola.
    * Return:         Devuelve un objeto de tipo PrintStream con el tipo de salida.
    */
    public static PrintStream crearFicheroSalida (String[] arrayNombreFicheros, String salida) 
    throws FileNotFoundException {
        File fichero = new File(arrayNombreFicheros[1]);
        PrintStream escribir = null;
        if (salida == "fichero") {
            escribir = new PrintStream(fichero);
        } else {
            escribir = System.out;
        }
        return escribir;
    }

   /*
    * M�todo cuentaNucleotidos: recibe la l�nea a analizar y mientras la recorre cuenta el n�mero de cada
    * nucle�tido y la cantidad de basura.
    * Par�metros:     Int[] contNucleotidos: array de tipo int donde guardar la cantidad de cada nucle�tido.
    *                 Int[] contBasura: array de tipo int donde guardar la cantidad de basura.
    *                 String linea: objeto de tipo String con el contenido de la l�nea a analizar.
    * No devuelve ning�n valor.
    */
    public static void cuentaNucleotidos(int[] contNucleotidos, int[] contBasura, String linea) {
        for (int i = 0; i < linea.length(); i++) { 
            if (linea.charAt(i) == 'A') {
                contNucleotidos[0]++;
            } else if (linea.charAt(i) == 'C') {
                contNucleotidos[1]++;
            } else if (linea.charAt(i) == 'G') {
                contNucleotidos[2]++;
            } else if (linea.charAt(i) == 'T') {
                contNucleotidos[3]++;
            } else {
                contBasura[0]++;
            }
        } 
    }
    
   /*
    * M�todo calculaMasa: calcula los porcentajes de masa de cada nucle�tido y la masa total.
    * Par�metros:     Int[] contNucleotidos: array de tipo int con la cantidad de cada nucle�tido.
    *                 Int[] contBasura: array de tipo int con la cantidad de basura.
    *                 Double[] porcentajes: array de tipo double donde almacenar� los porcentajes de masa.
    * No devuelve ning�n valor.

    */
    public static double[] calculaMasa(int[] contNucleotidos, int[] contBasura, double[] porcentajes) {
        double[] masaTotal = new double[1];
        for (int i = 0; i < contNucleotidos.length; i++) {
            masaTotal[0] += (contNucleotidos[i] * masasUnitarias[i]);
        }
        masaTotal[0] += contBasura[0] * masasUnitarias[4];
        for (int i = 0; i < contNucleotidos.length; i++) {
            porcentajes[i] = (double) Math.round((contNucleotidos[i] * masasUnitarias[i] * 100 / masaTotal[0]) * 10) / 10;
        }
        masaTotal[0] = (double) Math.round(masaTotal[0] * 10) / 10;
        return masaTotal;
    }
   
   /*
    * M�todo clasificaCodones: m�todo que clasifica los codones de 3 en 3 quitando la basura de en medio.
    * Par�metros:     Int[] contBasura: array de tipo int con la cantidad de basura.
    *                 String linea: linea con los nucle�tidos a analizar.
    * Return:         Devuelve un objeto de tipob String con los nucle�tidos clasificados porcodones.
    */
    public static String[] clasificaCodones(int[] contBasura, String linea) {
        char[] cadenaCodones = new char[linea.length() - contBasura[0]];
        
        int cont = 0;
        for (int i = 0; i < linea.length(); i++) {
            if (linea.charAt(i) != '-') {
                cadenaCodones[cont] += linea.charAt(i);
                cont++;
            }
        }
        String[] tresCodones = new String[cadenaCodones.length/nucleotidosCodon];
            for (int i = 0; i < tresCodones.length; i++) {
                tresCodones[i] = new String(cadenaCodones, i * nucleotidosCodon, nucleotidosCodon);
            }
        return tresCodones;
     }
     
    /*
     * M�todo codificaProteina: analiza la cadena de codones para saber si codifica prote�na o no.
     * Par�metros:     String[] tresCodones: array de tipo string con la cadena de codones.
     *                 Double[] porcentajes: array de tipo double con los porcentajes de masa.
     * Return:         Devuelve un objeto de tipo string.
     */
     public static String codificaProteina(String[] tresCodones, double[] porcentajes) {
         if ((tresCodones[0].equals("ATG")) && ((tresCodones[tresCodones.length - 1].equals("TAA")) ||
         (tresCodones[tresCodones.length - 1].equals("TAG")) || (tresCodones[tresCodones.length - 1].equals("TGA")))
         && (tresCodones.length >= numMinCodones) && ((porcentajes[1] + porcentajes[2]) >= porcenMasaCitosGuanin)) {
             return "SI";
         } else {
             return "NO";
         }
     }
}