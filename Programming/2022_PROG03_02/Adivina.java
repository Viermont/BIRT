/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 02/12/2022      *Módulo: Programación      *Unidad: 03       
  
   *Tarea: Tarea Evaluación 02. Realiza un programa en Java (60%)
   
   *Descripción: el objetivo de esta tarea es realizar un programa que genere un número al azar del 1 al
   100 (entero) y que dé pistas para que el usuario o usuaria lo adivine.
   
   *Autoevaluación: https://drive.google.com/drive/folders/1Y6ay5DZtU_Jb-Z72TCIZ0W4nqLfCzz4C?usp=sharing
   
*********************************************************************************************************/
import java.util.*;

public class Adivina {

    // Variable miembro global solicitada en el enunciado.
    static int contPartGanadas;
    // Constante para el número máximo que puede generar el programa.
    public static final int NUM_MAX = 100;

    /* Método principal: crea objeto de tipo escaner, llama al método intro y jugarPartida, al que le pasa 
     * el objeto teclado como parámetro.
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        intro();
        jugarPartida(teclado);
        teclado.close();
    }
    
    // Método Intro: muestra por consola una pequeña introducción al programa.
    public static void intro() {
        System.out.print("El programa genera un número aleatorio del 1 al 100\n"
        + "El usuario o usuaria debe adivinarlo en máximo 10 intentos\n"
        + "El programa le dará pistas cada vez que falle\n");
    }
 
    /* Método JugarPartida: recibe como parámetro un objeto de tipo escáner pero no devuelve nada.
     *De inicio se ejecuta siempre la primera vez, solicita un número de 0 a 100, comprueba que sea un
     *entero entre 1 y 100 y da pistas al usuario para que lo adivine mediante el método compruebaNum.
     *Finalmente, si se adivina el número o se llega al máximo de 10 intentos, mediante el método
     *jugarOtra se averigua si el usuario quiere seguir jugando, en cuyo caso se repetirá el método.
     *Una vez que el usuario no quiera seguir jugando, se llama al método mostrarEstadísticas, al que
     *le pasaremos los datos necesarios para que muestre por consola las estadísticas de las partidas.
     */ 
    public static void jugarPartida(Scanner teclado) {
        int numPartidas = 0;
        int intentosTotales = 0;
        int mejorPartida = 999999;      
        boolean jugar = true; 
        while (jugar) {
            Random ran = new Random(20);
            int numAdivinar = ran.nextInt(NUM_MAX) + 1;
            numPartidas++;
            int intentos = 0;
            boolean adivinar = false;
            System.out.print("\nAdivina un número del 1 al 100");
            do {
                intentos++;
                System.out.print("\nAdivina el número: ");
                while (!teclado.hasNextInt()) {
                    System.out.println("\"" + teclado.next() + "\" no es un número.\nAdivina el número: ");
                }
                int num = teclado.nextInt();
                while (num < 1 || num > 100) {
                    System.out.println("\"" + num + "\" no es un número entre 1 y 100.\nAdivina el número: ");
                    num = teclado.nextInt();
                }
                adivinar = compruebaNum(num, numAdivinar, intentos);
            } while (intentos < 10 && adivinar == false);
            if (!adivinar) {
                System.out.println("No has adivinado el número secreto: " + numAdivinar + ".");
            }
            jugar = jugarOtra(teclado);
            intentosTotales +=intentos;
            mejorPartida = Math.min(intentos, mejorPartida);
        }
        mostrarEstadisticas(numPartidas, intentosTotales, mejorPartida);
    }
    
    /* Método compruebaNum: recibe como parámetros el número insertado por teclado por el usuario, el
     *número aleatorio a adivinar y el número de intentos que llevamos. El método comprueba si se ha
     *adivinado el número, si es mayor o si es menor y da pistas si no se ha adivinado. Finalmente
     *devuelve una variable booleana que indicará si se ha acertado o no.
     */  
    public static boolean compruebaNum(int num, int numAdivinar, int intentos) {
        if (num > numAdivinar) {
            System.out.print("\nEl número es menor.");
            return false;
        } else if (num < numAdivinar) {
            System.out.print("\nEl número es mayor.");
            return false;
        } else {
            System.out.println("Has adivinado en " + intentos + " intentos.");
            contPartGanadas++;
            return true;
        }
    }
    
    /* Método jugarOtra: recibe como parámetro un objeto de tipo escáner, consulta al usuario si quiere
     *o no seguir jugando y devuelve una variable booleana indicando el deseo del usuario al respecto.
     */
    public static boolean jugarOtra(Scanner teclado) {
        System.out.print("¿Quieres jugar otra vez (S/N)? ");
        String otraPartida = teclado.next();
        if (otraPartida.charAt(0) == 's' || otraPartida.charAt(0) == 'S') {
            return true;
        }
        return false;
    }
    
    /* Método mostrarEstadisticas: recibe como parámetros las estadísticas de las partidas jugadas y
     *las muestra según el formato exigido. No devuelve nada.
     */
    public static void mostrarEstadisticas(int numPartidas, int intentosTotales, int mejorPartida) {
       double intentosPorPartida = (double)intentosTotales / numPartidas;
        System.out.println("\nResultados del juego:\n"
        + "Partidas jugadas: " + numPartidas
        + "\nIntentos realizados: " + intentosTotales
        + "\nPartidas ganadas: " + contPartGanadas);
        System.out.printf("Intentos por partida: %.1f", intentosPorPartida);
        System.out.println("\nMejor partida: " + mejorPartida);
    }
}