/********************************************************************************************************
 *Autor: Javier Montero Martinez      *Fecha: 18/01/2023       *Módulo: Programación        *Unidad: 05
  
 *Tarea: Tarea Evaluación 01. Realiza un programa en Java (100%)
   
 *Descripción: Esta tarea se centra en el manejo de arrays, ficheros y cadenas de caracteres y necesitará
 2 ficheros de texto, dna.txt y ecoli.txt.
 
 *Autoevaluación: https://drive.google.com/drive/folders/1KvxW0XEjjZk0n412A_ZLkwds-L9mSGUp?usp=share_link
   
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
    * Método principal: crea un objeto de tipo Scanner (teclado), crea un Array (arrayNombreFicheros) para
    * guardar el nombre de los ficheros, llama al método Intro y al método nombreFichero, al que le pasa el
    * valor del array por referencia y el objeto de tipo Scanner. LLama al método leerFichero, al que le
    * pasa el array por referencia y del que recibe un objeto (leer) de tipo Scanner con el fichero de
    * entrada. Llama al método escribirFichero al que le pasa un array por referencia, el objeto de tipo
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
    * Método intro: explica lo que hace el programa.
    * No tiene parámetros y no devuelve ningún valor.
    */
    public static void intro() {
        System.out.print("\nEste programa genera información sobre\n"
        + "secuencias de nucleótidos de ADN contenidas en un fichero\n"
        + "También indicará si pueden codificar proteinas o no\n"
        + "Todos los resultados se guardarán en un fichero");
    }
    
   /*
    * Método nombreFichero: pide por teclado el nombre del fichero de entrada y salida y los guarda en un array.
    * Parámetros:     Scanner teclado: objeto de tipo Scanner para leer por teclado el nombre del fichero.
    *                 String[] arrayNombreficheros: array de tipo string donde guardar los nombres de archivo.
    * No devuelve ningún valor.
    */
    public static void nombreFichero(Scanner teclado, String[] arrayNombreFicheros) {
        for (int i = 0; i <2; i++) {
            System.out.print("\nIntroduce el nombre del fichero: ");
            arrayNombreFicheros[i]= teclado.nextLine();
        }
    }

   /*
    * Método leerFichero: conecta con el fichero a leer y lo guarda en el objeto de tipo scanner leer.
    * Contempla excepciones en caso de error de lectura.
    * Parámetros:     String[] arrayNombreficheros: array de tipo string donde están guardados los nombres de
    *                 archivo.
    * Return:         Devuelve el objeto de tipo Scanner leer.
    */
    public static Scanner leerFichero(String[] arrayNombreFicheros) {
        File ficheroEntrada = new File(arrayNombreFicheros[0]);
        Scanner leer = null;
        try {
            leer = new Scanner(ficheroEntrada);
        } catch (FileNotFoundException excepcion) {
            System.out.println("Error con la conexión al fichero");
        } catch (Exception excepcion) {
            System.out.println("Otra excepción" + excepcion.getMessage());
        }
        return leer;
    }

   /*
    * Método escribirFichero: llama a el el método crearFicheroSalida y dependiendo de lo que devuelva
    * escribirá por teclado o por consola. Recorre cada línea del fichero de entrada y en cada una de ellas
    * ellas escribe 1º la descripción, 2º llama al método cuentaNucleotidos y muestra la secuencia de
    * nucleótidos, 3º llama a calculaMasa y mostrará el porcentaje de masa de cada nucleótido respecto al
    * total, 4º llama a clasificaCodones y mostrará los codones de cada secuencia, y 5º llamará a
    * codificaProteina y mostrará si la secuencia codifica proteína o no.
    * Parámetros:     String[] arrayNombreficheros: array de tipo string donde están guardados los nombres de
    *                 archivo.
    *                 Scanner leerFichero: objeto de tipo scanner con el fichero de entrada.
    *                 String salida: objeto de tipo estring para saber si tiene que escribir en un fichero o
    *                 por consola.
    * No devuelve ningún valor.
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
            escribir.println("Descripción: " + linea);
            linea = leerFichero.nextLine(); 
            escribir.println("Nucleótidos: " + linea.toUpperCase());
            cuentaNucleotidos(contNucleotidos, contBasura, linea.toUpperCase());
            escribir.println("Contadores: " + Arrays.toString(contNucleotidos));           
            double[] masaTotal = calculaMasa(contNucleotidos, contBasura, porcentajes);  
            escribir.println("Masa (%): " + Arrays.toString(porcentajes) + " de " + masaTotal[0]);
            String[] tresCodones = clasificaCodones(contBasura, linea.toUpperCase());
            escribir.println("Lista Codones: " + Arrays.toString(tresCodones));
            String esProteina = codificaProteina(tresCodones, porcentajes);
            escribir.print("Es proteína: " + esProteina);
            contVueltas++;
        }
    }

   /*
    * Método leerFichero: dependiendo de la salida que se le pida seleccionará si la salida es por
    * fichero o por consola.
    * Parámetros:     String[] arrayNombreficheros: array de tipo string donde están guardados los nombres de
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
    * Método cuentaNucleotidos: recibe la línea a analizar y mientras la recorre cuenta el número de cada
    * nucleótido y la cantidad de basura.
    * Parámetros:     Int[] contNucleotidos: array de tipo int donde guardar la cantidad de cada nucleótido.
    *                 Int[] contBasura: array de tipo int donde guardar la cantidad de basura.
    *                 String linea: objeto de tipo String con el contenido de la línea a analizar.
    * No devuelve ningún valor.
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
    * Método calculaMasa: calcula los porcentajes de masa de cada nucleótido y la masa total.
    * Parámetros:     Int[] contNucleotidos: array de tipo int con la cantidad de cada nucleótido.
    *                 Int[] contBasura: array de tipo int con la cantidad de basura.
    *                 Double[] porcentajes: array de tipo double donde almacenará los porcentajes de masa.
    * No devuelve ningún valor.

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
    * Método clasificaCodones: método que clasifica los codones de 3 en 3 quitando la basura de en medio.
    * Parámetros:     Int[] contBasura: array de tipo int con la cantidad de basura.
    *                 String linea: linea con los nucleótidos a analizar.
    * Return:         Devuelve un objeto de tipob String con los nucleótidos clasificados porcodones.
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
     * Método codificaProteina: analiza la cadena de codones para saber si codifica proteína o no.
     * Parámetros:     String[] tresCodones: array de tipo string con la cadena de codones.
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