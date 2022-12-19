/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 02/12/2022      *M�dulo: Programaci�n      *Unidad: 03       
  
   *Tarea: Tarea Evaluaci�n 02. Realiza un programa en Java (60%)
   
   *Descripci�n: el objetivo de esta tarea es realizar un programa que genere un n�mero al azar del 1 al
   100 (entero) y que d� pistas para que el usuario o usuaria lo adivine.
   
   *Autoevaluaci�n: https://drive.google.com/drive/folders/1Y6ay5DZtU_Jb-Z72TCIZ0W4nqLfCzz4C?usp=sharing
   
*********************************************************************************************************/
import java.util.*;

public class Adivina {

    // Variable miembro global solicitada en el enunciado.
    static int contPartGanadas;
    // Constante para el n�mero m�ximo que puede generar el programa.
    public static final int NUM_MAX = 100;

    /* M�todo principal: crea objeto de tipo escaner, llama al m�todo intro y jugarPartida, al que le pasa 
     * el objeto teclado como par�metro.
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        intro();
        jugarPartida(teclado);
        teclado.close();
    }
    
    // M�todo Intro: muestra por consola una peque�a introducci�n al programa.
    public static void intro() {
        System.out.print("El programa genera un n�mero aleatorio del 1 al 100\n"
        + "El usuario o usuaria debe adivinarlo en m�ximo 10 intentos\n"
        + "El programa le dar� pistas cada vez que falle\n");
    }
 
    /* M�todo JugarPartida: recibe como par�metro un objeto de tipo esc�ner pero no devuelve nada.
     *De inicio se ejecuta siempre la primera vez, solicita un n�mero de 0 a 100, comprueba que sea un
     *entero entre 1 y 100 y da pistas al usuario para que lo adivine mediante el m�todo compruebaNum.
     *Finalmente, si se adivina el n�mero o se llega al m�ximo de 10 intentos, mediante el m�todo
     *jugarOtra se averigua si el usuario quiere seguir jugando, en cuyo caso se repetir� el m�todo.
     *Una vez que el usuario no quiera seguir jugando, se llama al m�todo mostrarEstad�sticas, al que
     *le pasaremos los datos necesarios para que muestre por consola las estad�sticas de las partidas.
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
            System.out.print("\nAdivina un n�mero del 1 al 100");
            do {
                intentos++;
                System.out.print("\nAdivina el n�mero: ");
                while (!teclado.hasNextInt()) {
                    System.out.println("\"" + teclado.next() + "\" no es un n�mero.\nAdivina el n�mero: ");
                }
                int num = teclado.nextInt();
                while (num < 1 || num > 100) {
                    System.out.println("\"" + num + "\" no es un n�mero entre 1 y 100.\nAdivina el n�mero: ");
                    num = teclado.nextInt();
                }
                adivinar = compruebaNum(num, numAdivinar, intentos);
            } while (intentos < 10 && adivinar == false);
            if (!adivinar) {
                System.out.println("No has adivinado el n�mero secreto: " + numAdivinar + ".");
            }
            jugar = jugarOtra(teclado);
            intentosTotales +=intentos;
            mejorPartida = Math.min(intentos, mejorPartida);
        }
        mostrarEstadisticas(numPartidas, intentosTotales, mejorPartida);
    }
    
    /* M�todo compruebaNum: recibe como par�metros el n�mero insertado por teclado por el usuario, el
     *n�mero aleatorio a adivinar y el n�mero de intentos que llevamos. El m�todo comprueba si se ha
     *adivinado el n�mero, si es mayor o si es menor y da pistas si no se ha adivinado. Finalmente
     *devuelve una variable booleana que indicar� si se ha acertado o no.
     */  
    public static boolean compruebaNum(int num, int numAdivinar, int intentos) {
        if (num > numAdivinar) {
            System.out.print("\nEl n�mero es menor.");
            return false;
        } else if (num < numAdivinar) {
            System.out.print("\nEl n�mero es mayor.");
            return false;
        } else {
            System.out.println("Has adivinado en " + intentos + " intentos.");
            contPartGanadas++;
            return true;
        }
    }
    
    /* M�todo jugarOtra: recibe como par�metro un objeto de tipo esc�ner, consulta al usuario si quiere
     *o no seguir jugando y devuelve una variable booleana indicando el deseo del usuario al respecto.
     */
    public static boolean jugarOtra(Scanner teclado) {
        System.out.print("�Quieres jugar otra vez (S/N)? ");
        String otraPartida = teclado.next();
        if (otraPartida.charAt(0) == 's' || otraPartida.charAt(0) == 'S') {
            return true;
        }
        return false;
    }
    
    /* M�todo mostrarEstadisticas: recibe como par�metros las estad�sticas de las partidas jugadas y
     *las muestra seg�n el formato exigido. No devuelve nada.
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