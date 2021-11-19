/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 19/11/2021   
   *Módulo: Programación         *Unidad: 03       *Tarea: Tarea Evaluación 01
   
   *Descripción: Realiza un programa en Java.
   Para realizar el programa manejaremos distintos tipos de variables,
   estructuras condicionales IF-ELSE, bucles FOR y métodos con paso de
   parámetros y sentencia return así como, las funcionalidades de las clases
   de las librerías de java.
   
   *Enlace autoevaluación:
https://drive.google.com/file/d/1aS0Uf2HJITOAnKPIWsoWHdEswZ4o4Yyl/view?usp=sharing

*******************************************************************************/
import java.util.*;
public class CalculaNota {
/*Método principal en el que se crea la variable de tipo Scanner y se llama al
resto de métodos. Se les pasa y se recibe de ellos los parámetros necesarios*/
   public static void main (String[] args) {
      Scanner teclado = new Scanner(System.in);
      intro();
      double notaParcial = notaExamen(teclado, "Parcial");
      double notaFinal = notaExamen(teclado, "Final");
      double notaTareas = notaTareas(teclado);
      double notaDefinitiva = notaParcial + notaFinal + notaTareas;
      mensajeFinal(notaDefinitiva);
   }
//Método que mediante un println nos introduce al programa.
   public static void intro() {
      System.out.println("Este programa lee las calificaciones de exámenes y "
      + "tareas\ny calcula la nota final del módulo para un estudiante.\n"
      + "También podría hacerlo para 2 estudiantes y comparar sus notas. "
      + "Funcionaría sin problemas");
   }
/*Método que recibe como parámetros un objeto de tipo Scanner y una String con
el tipo de examen, solicita por teclado el peso del examen, la calificación y
los puntos extra y los muestra mediante print, junto con la nota final y la nota
final ponderada. Devuelve la nota final ponderada.*/
   public static double notaExamen(Scanner teclado, String tipoExamen) {
      System.out.print(tipoExamen + ":\nIntroduce el peso del examen (0-100): ");
      int peso = teclado.nextInt();
      System.out.print("Introduce la calificación del examen (0-100): ");
      int calif = teclado.nextInt();
      System.out.print("¿Has obtenido puntos extra (1=Si, 2=No)? ");
/*Iba a utilizar una variable de tipo booleano, pero hubiara ocupado más código para
transformarla en las opciones 1 y 2, que es lo que se solicita*/
      int sino = teclado.nextInt();
      int puntosExtra;
      if (sino == 1) {
         System.out.print("Introduce el total de puntos extra: ");
         puntosExtra = teclado.nextInt();
      } else {
/*Si elige la opción 1, solicita la nota, en caso contrario, aunque no sea un 2, no la
solicita y da por hecho que no hay puntos extra*/
         puntosExtra = 0;
      }
      int notaFinal = calif + puntosExtra;
      if (notaFinal > 100) {
         notaFinal = 100;
      }
      System.out.println("Nota final = " + notaFinal + " / 100");
      double notaPonderada = calculoPonderada(notaFinal, peso, 100);
//Utilizo 2 sentencias print ya que el redondeo se hace mediante una de ellas, con %.1f. tal como se pide.
      System.out.printf("Nota final ponderada = %.1f / ", notaPonderada);
      System.out.println(peso);
      return notaPonderada;
   }
/*Método que recibe como parámetros un objeto de tipo Scanner, solicita por teclado el peso y nº
de tareas y su puntuación obtenida y máxima, además solicita por teclado el nº de presenciales a
las que se ha atendido, nos calcula y muestra la nota de las presenciales, la nota final de las
tareas y nota final ponderada. Devuelve la nota final ponderada.*/
   public static double notaTareas(Scanner teclado) {
      System.out.print("Tareas:\nIntroduce el peso de las tareas (0-100): ");
      int peso = teclado.nextInt();
      System.out.print("Introduce el número de tareas: ");
      int numTareas = teclado.nextInt();
/*En caso que tenga tareas, mediante un buble for solicitará la puntuación obtenida y la máxima
para cada tarea y las guardará mediante un algoritmo acumulador.*/
      int sumaNotas = 0;
      int sumaPMax = 0;
      if (numTareas > 0) {
         for(int i = 1; i <= numTareas; i++) {
            System.out.print("Tarea " + i + ": Introduce la puntuación obtenida y la puntuación máxima: ");
            sumaNotas += teclado.nextInt();
            sumaPMax += teclado.nextInt();
         }
      }
      System.out.print("Introduce el número de presenciales a las que has atendido: ");
      int numPresen = teclado.nextInt();
      int notaPresen = numPresen * 5;
      final int maxPresen = 30;
      if (notaPresen > maxPresen) {
         notaPresen = maxPresen;
      }
      System.out.println("Nota de las presenciales = " + notaPresen + " / " + maxPresen);
      sumaNotas += notaPresen;
      sumaPMax += maxPresen;
      System.out.println("Nota final = " + sumaNotas + " / " + sumaPMax);
      double notaPonderada = calculoPonderada(sumaNotas, peso, sumaPMax);   
      System.out.printf("Nota final ponderada = %.1f / ", notaPonderada);
      System.out.println(peso);     
      return notaPonderada;
   }
   public static void mensajeFinal(double notaDefinitiva) {
   System.out.printf("La calificación total obtenida es: %.1f\n", notaDefinitiva);
   double valor;
   String mensaje;
   if (notaDefinitiva >= 85) {
      valor = 3.0;
      mensaje = "Excelente trabajo";
   } else if (notaDefinitiva >= 75 && notaDefinitiva <= 84.99) {
      valor = 2.0;
      mensaje = "Gran trabajo";
   } else if (notaDefinitiva >= 60 && notaDefinitiva <= 74.99) {
      valor = 0.7;
      mensaje = "Todavía tienes trabajo por hacer";
   } else {
      valor = 0.0;
      mensaje = "Otra vez será";
   }
   System.out.println("La nota final en una escala del 0 al 4 es al menos: " + valor + "\n" + mensaje);
   }
//Método que recibe la nota final, el peso que tiene, la puntuación máxima y calcula la nota final ponderada.
   public static double calculoPonderada(int notaFinal, int peso, int puntMax) {
      double notaPonderada = (double)notaFinal * peso / puntMax;
      return notaPonderada;
   }
}