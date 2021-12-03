/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 03/12/2021   
   *M�dulo: Programaci�n         *Unidad: 03       *Tarea: Tarea Evaluaci�n 02
   
   *Descripci�n: Realiza un programa en Java. Para realizar el programa usaremos
   distintos tipos de variables, estructuras condicionales IF-ELSE, bucles WHILE
   y DO WHILE y m�todos con paso de par�metros, sentencia return, print, println,
   printf, variables de tipo Scanner y Random, as� como las funcionalidades de
   las clases de las librer�as de java.
   
   *Enlace autoevaluaci�n:
   https://drive.google.com/file/d/1kGd4VjIXlrm6C4XvNL63ZRp5ZZiDjscU/view?usp=sharing

*******************************************************************************/
import java.util.*;
public class Adivina {
   
   /*M�todo principal en el que se crea la variable de tipo Scanner y se llama a
   los m�todos intro y jugarPartida. A este se le pasa la variable tipo Scanner*/
   public static void main (String [] args) {
      Scanner teclado = new Scanner(System.in);   
      intro();
      jugarPartida(teclado); 
   }
   
   //M�todo que mediante un println nos introduce al programa.
   public static void intro() {
      System.out.println("El programa genera un n�mero aleatorio del 1 al 100\n"
      + "El usuario o usuaria debe adivinarlo\nEl programa le dar� pistas cada vez que falle");
   }
   
   /*M�todo que recibe una vatriable tipo scanner, crea las variables necesarias para acumular
   datos mediante algoritmo acumulador*/
   public static void jugarPartida(Scanner teclado) {
      int numPartidas = 0;
      int numIntentosTotales = 0;
      int mejorPartida = 1000000;   //se inicia la variable en el m�ximo de intentos posibles.
      boolean jugar = true;   //se inicia variable booleana con valor true.
      do {  //se utiliza do while para repetir el juego las veces que sea necesario.
         Random rand = new Random(20); //se crea variable tipo random utilizando la semilla 20.
         numPartidas++;
         int numAdivinar = rand.nextInt(100) + 1;  //generamos numero aleatorio de 1 a 100.
         int numElegido;
         int numIntentosPartida = 0;
         String otraPartida;
         System.out.println("\nAdivina un n�mero del 1 al 100");
         
         while (jugar) {   //mediante while repetimos el codigo hasta que se adivine el numero.
            System.out.print("Adivina el n�mero: ");
            numElegido = teclado.nextInt();  //Solicitamos un int mediante teclado y guardamos.
            numIntentosPartida++;
            if(numAdivinar < numElegido) {   //Utilizamos condicionales para las diferentes opciones.
               System.out.println("\nEl n�mero es menor.");
            } else if (numAdivinar > numElegido) {
               System.out.println("\nEl n�mero es mayor.");
            } else {
               System.out.print("\nHas adivinado en " + numIntentosPartida + " intentos.\n"
               + "�Quieres jugar otra vez (S/N)? ");  //consultamos si se quiere volver a jugar.
               jugar = false; //booleano pasa a false para que una vez adivinado el numero salga del bucle.
            }
         }
         otraPartida = teclado.next(); //Solicitamos por teclado entrada de datos y guardamos en string.
         jugar = juegaMas(otraPartida);   //llamamos al m�todo juagaMas y le pasamos la variable string.
         numIntentosTotales += numIntentosPartida;
         mejorPartida = Math.min(numIntentosPartida, mejorPartida);  //almacenamos variable de menor valor.
      } while(jugar);
      mostrarEstadisticas(numPartidas, numIntentosTotales, mejorPartida);  /*llamamos al m�todo
      mostrarEstad�sticas y le pasamos las variables acumulativas como par�metro*/
   }
   
   /*M�todo que recibe una variable tipo string con la respuesta a si se sigue jugando, devuelve variable
   booleana true o false si cumple o no la condici�n*/
   public static boolean juegaMas(String otraPartida) {
      return otraPartida.charAt(0) == 's' || otraPartida.charAt(0) == 'S';
   }
   
   /*M�todo que recibe las variables acumulativas y muestra las estad�sticas de las partidas jugadas*/
   public static void mostrarEstadisticas(int numPartidas, int numIntentosTotales, int mejorPartida) {
      double intentosPartida = (double)numIntentosTotales / (double)numPartidas; //Casteamos int a double
      System.out.println("\nResultados del juego:\nPartidas jugadas: " + numPartidas
      + "\nIntentos realizados: " + numIntentosTotales);
      System.out.printf("Intentos por partida: %.1f", intentosPartida); /*usamos printf para mostrar variable
      de tipo double con 1 decimal*/
      System.out.print("\nMejor partida: " + mejorPartida);
   }
}