/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 03/12/2021   
   *Módulo: Programación         *Unidad: 03       *Tarea: Tarea Evaluación 02
   
   *Descripción: Realiza un programa en Java. Para realizar el programa usaremos
   distintos tipos de variables, estructuras condicionales IF-ELSE, bucles WHILE
   y DO WHILE y métodos con paso de parámetros, sentencia return, print, println,
   printf, variables de tipo Scanner y Random, así como las funcionalidades de
   las clases de las librerías de java.
   
   *Enlace autoevaluación:
   https://drive.google.com/file/d/1kGd4VjIXlrm6C4XvNL63ZRp5ZZiDjscU/view?usp=sharing

*******************************************************************************/
import java.util.*;
public class Adivina {
   
   /*Método principal en el que se crea la variable de tipo Scanner y se llama a
   los métodos intro y jugarPartida. A este se le pasa la variable tipo Scanner*/
   public static void main (String [] args) {
      Scanner teclado = new Scanner(System.in);   
      intro();
      jugarPartida(teclado); 
   }
   
   //Método que mediante un println nos introduce al programa.
   public static void intro() {
      System.out.println("El programa genera un número aleatorio del 1 al 100\n"
      + "El usuario o usuaria debe adivinarlo\nEl programa le dará pistas cada vez que falle");
   }
   
   /*Método que recibe una vatriable tipo scanner, crea las variables necesarias para acumular
   datos mediante algoritmo acumulador*/
   public static void jugarPartida(Scanner teclado) {
      int numPartidas = 0;
      int numIntentosTotales = 0;
      int mejorPartida = 1000000;   //se inicia la variable en el máximo de intentos posibles.
      boolean jugar = true;   //se inicia variable booleana con valor true.
      do {  //se utiliza do while para repetir el juego las veces que sea necesario.
         Random rand = new Random(20); //se crea variable tipo random utilizando la semilla 20.
         numPartidas++;
         int numAdivinar = rand.nextInt(100) + 1;  //generamos numero aleatorio de 1 a 100.
         int numElegido;
         int numIntentosPartida = 0;
         String otraPartida;
         System.out.println("\nAdivina un número del 1 al 100");
         
         while (jugar) {   //mediante while repetimos el codigo hasta que se adivine el numero.
            System.out.print("Adivina el número: ");
            numElegido = teclado.nextInt();  //Solicitamos un int mediante teclado y guardamos.
            numIntentosPartida++;
            if(numAdivinar < numElegido) {   //Utilizamos condicionales para las diferentes opciones.
               System.out.println("\nEl número es menor.");
            } else if (numAdivinar > numElegido) {
               System.out.println("\nEl número es mayor.");
            } else {
               System.out.print("\nHas adivinado en " + numIntentosPartida + " intentos.\n"
               + "¿Quieres jugar otra vez (S/N)? ");  //consultamos si se quiere volver a jugar.
               jugar = false; //booleano pasa a false para que una vez adivinado el numero salga del bucle.
            }
         }
         otraPartida = teclado.next(); //Solicitamos por teclado entrada de datos y guardamos en string.
         jugar = juegaMas(otraPartida);   //llamamos al método juagaMas y le pasamos la variable string.
         numIntentosTotales += numIntentosPartida;
         mejorPartida = Math.min(numIntentosPartida, mejorPartida);  //almacenamos variable de menor valor.
      } while(jugar);
      mostrarEstadisticas(numPartidas, numIntentosTotales, mejorPartida);  /*llamamos al método
      mostrarEstadísticas y le pasamos las variables acumulativas como parámetro*/
   }
   
   /*Método que recibe una variable tipo string con la respuesta a si se sigue jugando, devuelve variable
   booleana true o false si cumple o no la condición*/
   public static boolean juegaMas(String otraPartida) {
      return otraPartida.charAt(0) == 's' || otraPartida.charAt(0) == 'S';
   }
   
   /*Método que recibe las variables acumulativas y muestra las estadísticas de las partidas jugadas*/
   public static void mostrarEstadisticas(int numPartidas, int numIntentosTotales, int mejorPartida) {
      double intentosPartida = (double)numIntentosTotales / (double)numPartidas; //Casteamos int a double
      System.out.println("\nResultados del juego:\nPartidas jugadas: " + numPartidas
      + "\nIntentos realizados: " + numIntentosTotales);
      System.out.printf("Intentos por partida: %.1f", intentosPartida); /*usamos printf para mostrar variable
      de tipo double con 1 decimal*/
      System.out.print("\nMejor partida: " + mejorPartida);
   }
}