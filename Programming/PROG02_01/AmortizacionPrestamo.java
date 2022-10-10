/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 03/11/2021   
   *Módulo: Programación         *Unidad: 02       *Tarea: Tarea Evaluación 01
   
   *Descripción: Realiza un programa en Java.
   Para realizar el programa manejaremos distintos tipos de variables,
   utilizaremos bucles for, métodos con paso de parámetros y la sentencia
   return. Además utilizaremos funcionalidades de las clases Math y Scanner.
   
   *Enlace autoevaluación:
   https://drive.google.com/drive/folders/1nofS3IoAxOSoMdfk0W3Xs4KUvrn4wVQz?usp=sharing

*******************************************************************************/
import java.util.*;
public class AmortizacionPrestamo {
   /*Método principal main, incluye el objeto Scanner y se llama a los métodos.
   En algunos de ellos se usa el paso de parámetros*/
      public static void main (String[] args) {
      introduccion();
      Scanner teclado = new Scanner(System.in);
      double cantidad = teclado.nextDouble();
      calculoOpciones(cantidad);
      solicitudInteres();
      double interes = teclado.nextDouble();
      solicitudPlazo();
      int plazo = teclado.nextInt();
      calculoPorAnio(cantidad, plazo, interes);
   }
   /*Método que nos muestra una introducción al programa con una única sentencia println
   dividida en varias líneas.*/
   public static void introduccion() {
      System.out.println("Este es un programa para calcular las cuotas de un préstamo\n"
      + "Pedirá el capital del préstamo (euros), el interés anual a pagar (%) y su duración (años)\n"
      + "Calculará para cada año, el capital pendiente y la cuota a pagar, intereses y amortización\n"
      + "Empezamos ya\n\n"
      + "Introduce la cantidad solicitada para el préstamo:");
   }
   //Método que calcula y muestra diferentes opciones de cuotas a pagar para distintos intereses y plazos.
   public static void calculoOpciones(double cantidad) {
      System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos");
      for(int plazo = 5; plazo <= 10; plazo++) {
         System.out.print(plazo + " Años\t");
         for(double interes = 2.0; interes <= 5.0; interes += 0.5) {
            System.out.print(redondeo(calculoCuota(cantidad, plazo, interes)) + "(" + interes + "%)\t");         
         }
         System.out.println();
      }
      System.out.println();
   }
   //Método que calcula la cuota anual y retorna su valor.
   public static double calculoCuota(double cantidad, int plazo, double interes) {
      double cuota = (cantidad * (interes / 100)) / (1 - Math.pow((1 + (interes / 100)), -plazo));
      return cuota;
   }
   //Método que redondea la cantidad recibida a 2 decimales y retorna su valor.
   public static double redondeo(double cantidadRedondear) {
      cantidadRedondear = (double) Math.round(cantidadRedondear * 100) / 100;
      return cantidadRedondear;
   }
   //Método que solicita el porcentaje de interes a calcular mediante la sentencia println.
   public static void solicitudInteres() {
      System.out.println("Introduce el interés anual que se aplicará al préstamo en %:");
   }
   //Método que solicita el plazo en años a calcular mediante la sentencia println.
   public static void solicitudPlazo() {
      System.out.println("Introduce el número de años que va a durar el préstamo:");
   }
   /*Método que calcula y muestra por años: capital pendiente, cuota anual, intereses a pagar y amortización
   Para ello utilizamos una variable estática, un bucle for, sentencias println que combinan texto y variables
   modificadas mediante un método de redondeo a 2 decimales y un algoritmo acumulador*/
   public static void calculoPorAnio(double cantidad, int plazo, double interes) {
      final double cuotaAnual = calculoCuota(cantidad, plazo, interes);
      double capitalPendiente = cantidad;
      double interesesPagar = capitalPendiente * interes / 100;
      double amortizacion = cuotaAnual - interesesPagar;
      for(int i = 1; i <= plazo; i++) {
         System.out.println("Año: " + i);
         System.out.println("\tCapital Pendiente: " + redondeo(capitalPendiente));
         System.out.println("\tCuota Anual: " + redondeo(cuotaAnual));
         System.out.println("\tIntereses a pagar: " + redondeo(interesesPagar));
         System.out.println("\tAmortización: " + redondeo(amortizacion));
         capitalPendiente -= amortizacion;
         interesesPagar = capitalPendiente * interes / 100;
         amortizacion = cuotaAnual - interesesPagar; 
         }
      }
}
