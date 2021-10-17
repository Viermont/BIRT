/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 13/10/2021   
   *M�dulo: Programaci�n         *Unidad: 01       *Tarea: Tarea Evaluaci�n 02
   
   *Descripci�n: Realiza un programa en Java. Programa que visualiza por
   consola una figura sim�trica compleja realizada mediante bucles for.
   
   *Enlace autoevaluaci�n:
   https://drive.google.com/drive/folders/16Z0sSfm1nIqq5Y7e4lgQbj9tES6xOLpr?usp=sharing

*******************************************************************************/
public class Cohete {
/*El m�todo main realiza las siguientes acciones:
   1- Escribe el texto solicitado seguido de un salto de l�nea
   2- Escribe una l�nea en blanco seguido de salto de l�nea
   3- Llama al m�todo dibujo para que se ejecute*/
   public static void main (String[] args) {
      System.out.println("DIBUJA ESTA FIGURA:");
      System.out.println();
      dibujo();
   }
/*El m�todo dibujo realiza varias acciones:
   1- Llama en bucle 4 veces al m�todo ejeCentral
   2- Llama al m�todo CuerpoSuperior
   3- Llama al m�todo CuerpoInferior
   4- Llama en bucle 12 veces al m�todo ejeCentral
   5- Llama al m�todo CuerpoSuperior
*/
   public static void dibujo() {
      for(int linea = 1; linea <= 4; linea++) {
         ejeCentral();
      }
      cuerpoSuperior();
      cuerpoInferior();
      for(int linea = 1; linea <= 12; linea++) {
         ejeCentral();
      }
      cuerpoSuperior();
   }
//El m�todo barraVertical dibuja una doble barra vertical
   public static void barraVertical() {
      System.out.print("||");
   }
//El m�todo saltoLinea imprime un salto de l�nea
   public static void saltoLinea() {
      System.out.println();
   }
/*El m�todo ejeCentral imprime con un bucle 12 espacios en blanco, despu�s
llama al m�todo barraVertical y al m�todo saltoLinea*/
   public static void ejeCentral() {
      for(int espacio = 1; espacio <= 12; espacio++) {
         System.out.print(" ");
      }
      barraVertical();
      saltoLinea();
   }
/*El m�todo cuerpoSuperior realiza en bucle 4 veces lo siguiente:
   1- Dibuja (-3 * linea + 12) espacios en blanco
   2- Dibuja __/
   3- Dibuja (3 * linea - 3) :
   4- Llama al m�todo barraVertical
   5- Dibuja (3 * linea - 3) :
   6- Dibuja \__ seguido de salto de l�nea
Una vez terminado el bucle llama al m�todo cuerpoMedio*/
   public static void cuerpoSuperior() {
      for(int linea = 1; linea <= 4; linea++) {
         for(int i = 1; i <= -3 * linea + 12; i++) {
            System.out.print(" ");
         }
         System.out.print("__/");
         for(int i = 1; i <= 3 * linea -3; i++) {
            System.out.print(":");
         }
         barraVertical();
         for(int i = 1; i <= 3 * linea -3; i++) {
            System.out.print(":");
         }
         System.out.println("\\__");
      }
      cuerpoMedio();
   }
/*El m�todo cuerpoMedio realiza las siguientes acciones:
   1- Dibuja |
   2- Realiza un bucle que dibuja 24 veces "
   3- Dibuja | seguida de salto de l�nea*/
   public static void cuerpoMedio() {
      System.out.print("|");
      for(int i = 1; i <= 24; i++){
      System.out.print("\"");
      }
      System.out.println("|");
   }
/*El m�todo cuerpoInferior realiza en bucle 4 veces lo siguiente:
   1- Dibuja (2 * linea - 2) espacios en blanco
   2- Dibuja \_
   3- Dibuja (-2 * linea + 13) /\
   4- Dibuja _/ seguido de salto de l�nea*/
   public static void cuerpoInferior() {
      for(int linea = 1; linea <= 4; linea++) {
         for(int i = 1; i <= 2 * linea - 2; i++) {
            System.out.print(" ");
         }
         System.out.print("\\_");
         for(int i = 1; i <= -2 * linea + 13; i++) {
            System.out.print("/\\");
         }
         System.out.println("_/");
      }    
   }
}