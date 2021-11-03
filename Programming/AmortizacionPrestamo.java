/*******************************************************************************

   *Autor: Javier Montero Martinez                          *Fecha: 03/11/2021   
   *M�dulo: Programaci�n         *Unidad: 02       *Tarea: Tarea Evaluaci�n 01
   
   *Descripci�n: Realiza un programa en Java.
   Para realizar el programa manejaremos distintos tipos de variables,
   utilizaremos bucles for, m�todos con paso de par�metros y la sentencia
   return. Adem�s utilizaremos funcionalidades de las clases Math y Scanner.
   
   *Enlace autoevaluaci�n:
   https://drive.google.com/drive/folders/1nofS3IoAxOSoMdfk0W3Xs4KUvrn4wVQz?usp=sharing

*******************************************************************************/
import java.util.*;
public class AmortizacionPrestamo {
   /*M�todo principal main, incluye el objeto Scanner y se llama a los m�todos.
   En algunos de ellos se usa el paso de par�metros*/
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
   /*M�todo que nos muestra una introducci�n al programa con una �nica sentencia println
   dividida en varias l�neas.*/
   public static void introduccion() {
      System.out.println("Este es un programa para calcular las cuotas de un pr�stamo\n"
      + "Pedir� el capital del pr�stamo (euros), el inter�s anual a pagar (%) y su duraci�n (a�os)\n"
      + "Calcular� para cada a�o, el capital pendiente y la cuota a pagar, intereses y amortizaci�n\n"
      + "Empezamos ya\n\n"
      + "Introduce la cantidad solicitada para el pr�stamo:");
   }
   //M�todo que calcula y muestra diferentes opciones de cuotas a pagar para distintos intereses y plazos.
   public static void calculoOpciones(double cantidad) {
      System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos");
      for(int plazo = 5; plazo <= 10; plazo++) {
         System.out.print(plazo + " A�os\t");
         for(double interes = 2.0; interes <= 5.0; interes += 0.5) {
            System.out.print(redondeo(calculoCuota(cantidad, plazo, interes)) + "(" + interes + "%)\t");         
         }
         System.out.println();
      }
      System.out.println();
   }
   //M�todo que calcula la cuota anual y retorna su valor.
   public static double calculoCuota(double cantidad, int plazo, double interes) {
      double cuota = (cantidad * (interes / 100)) / (1 - Math.pow((1 + (interes / 100)), -plazo));
      return cuota;
   }
   //M�todo que redondea la cantidad recibida a 2 decimales y retorna su valor.
   public static double redondeo(double cantidadRedondear) {
      cantidadRedondear = (double) Math.round(cantidadRedondear * 100) / 100;
      return cantidadRedondear;
   }
   //M�todo que solicita el porcentaje de interes a calcular mediante la sentencia println.
   public static void solicitudInteres() {
      System.out.println("Introduce el inter�s anual que se aplicar� al pr�stamo en %:");
   }
   //M�todo que solicita el plazo en a�os a calcular mediante la sentencia println.
   public static void solicitudPlazo() {
      System.out.println("Introduce el n�mero de a�os que va a durar el pr�stamo:");
   }
   /*M�todo que calcula y muestra por a�os: capital pendiente, cuota anual, intereses a pagar y amortizaci�n
   Para ello utilizamos una variable est�tica, un bucle for, sentencias println que combinan texto y variables
   modificadas mediante un m�todo de redondeo a 2 decimales y un algoritmo acumulador*/
   public static void calculoPorAnio(double cantidad, int plazo, double interes) {
      final double cuotaAnual = calculoCuota(cantidad, plazo, interes);
      double capitalPendiente = cantidad;
      double interesesPagar = capitalPendiente * interes / 100;
      double amortizacion = cuotaAnual - interesesPagar;
      for(int i = 1; i <= plazo; i++) {
         System.out.println("A�o: " + i);
         System.out.println("\tCapital Pendiente: " + redondeo(capitalPendiente));
         System.out.println("\tCuota Anual: " + redondeo(cuotaAnual));
         System.out.println("\tIntereses a pagar: " + redondeo(interesesPagar));
         System.out.println("\tAmortizaci�n: " + redondeo(amortizacion));
         capitalPendiente -= amortizacion;
         interesesPagar = capitalPendiente * interes / 100;
         amortizacion = cuotaAnual - interesesPagar; 
         }
      }
}