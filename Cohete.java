/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 13/10/2021   
   *Módulo: Programación         *Unidad: 01       *Tarea: Tarea Evaluación 02
   
   *Descripción: Realiza un programa en Java. Programa que visualiza por
   consola una figura simétrica compleja realizada mediante bucles for.
   
   *Enlace autoevaluación:
   https://drive.google.com/drive/folders/16Z0sSfm1nIqq5Y7e4lgQbj9tES6xOLpr?usp=sharing

*******************************************************************************/
public class Cohete {
/*El método main realiza las siguientes acciones:
   1- Escribe el texto solicitado seguido de un salto de línea
   2- Escribe una línea en blanco seguido de salto de línea
   3- Llama al método dibujo para que se ejecute*/
   public static void main (String[] args) {
      System.out.println("DIBUJA ESTA FIGURA:");
      System.out.println();
      dibujo();
   }
/*El método dibujo realiza varias acciones:
   1- Llama en bucle 4 veces al método ejeCentral
   2- Llama al método CuerpoSuperior
   3- Llama al método CuerpoInferior
   4- Llama en bucle 12 veces al método ejeCentral
   5- Llama al método CuerpoSuperior
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
//El método barraVertical dibuja una doble barra vertical
   public static void barraVertical() {
      System.out.print("||");
   }
//El método saltoLinea imprime un salto de línea
   public static void saltoLinea() {
      System.out.println();
   }
/*El método ejeCentral imprime con un bucle 12 espacios en blanco, después
llama al método barraVertical y al método saltoLinea*/
   public static void ejeCentral() {
      for(int espacio = 1; espacio <= 12; espacio++) {
         System.out.print(" ");
      }
      barraVertical();
      saltoLinea();
   }
/*El método cuerpoSuperior realiza en bucle 4 veces lo siguiente:
   1- Dibuja (-3 * linea + 12) espacios en blanco
   2- Dibuja __/
   3- Dibuja (3 * linea - 3) :
   4- Llama al método barraVertical
   5- Dibuja (3 * linea - 3) :
   6- Dibuja \__ seguido de salto de línea
Una vez terminado el bucle llama al método cuerpoMedio*/
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
/*El método cuerpoMedio realiza las siguientes acciones:
   1- Dibuja |
   2- Realiza un bucle que dibuja 24 veces "
   3- Dibuja | seguida de salto de línea*/
   public static void cuerpoMedio() {
      System.out.print("|");
      for(int i = 1; i <= 24; i++){
      System.out.print("\"");
      }
      System.out.println("|");
   }
/*El método cuerpoInferior realiza en bucle 4 veces lo siguiente:
   1- Dibuja (2 * linea - 2) espacios en blanco
   2- Dibuja \_
   3- Dibuja (-2 * linea + 13) /\
   4- Dibuja _/ seguido de salto de línea*/
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