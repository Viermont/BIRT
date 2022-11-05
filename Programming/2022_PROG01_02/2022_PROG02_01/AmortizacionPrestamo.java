/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 4/11/2022      *M�dulo: Programaci�n      *Unidad: 02       
  
   *Tarea: Tarea Evaluaci�n 02. Realiza un programa en Java (100%)
   
   *Descripci�n: El objetivo es practicar en nuestros programas el manejo de variables, bucles FOR y
   m�todos con paso de par�metros y sentencia return. Utilizaremos funcionalidades de las clases Math
   y Scanner. El programa proporcionar� diferentes c�lculos para dep�sitos a plazo fijo.
   
   *Autoevaluaci�n: https://drive.google.com/drive/folders/1rKRxLBSkV99C26-PVkfLdkkT29I6gpVu?usp=sharing

*********************************************************************************************************/
import java.util.*; // Importo libreria de Java para poder usar la clase Scanner.

public class AmortizacionPrestamo { // Clase principal que da nombre al programa.
    // Variables constantes para los plazos del dep�sito.
    public static final int PLAZO_INI = 5;
    public static final int PLAZO_FIN = 8;

    // M�todo principal: llama a 3 m�todos para la ejecuci�n del programa, a 2 de ellos les paso par�metros.
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Declaro variable de tipo Scanner.
        teclado.useLocale(Locale.ENGLISH); // Pongo teclado en ingl�s para evitar errores con decimales.
        intro();
        double deposito = intereses(teclado); // Guardo en la variable la devoluci�n de la sentencia return.
        incremento(teclado, deposito);
    }
    
    // M�todo que nos introduce al programa y nos solicita informaci�n.
    public static void intro() {
        System.out.print("Este programa calcula los intereses obtenidos con un dep�sito a plazo fijo\n" +
            "Pedir� la cantidad a invertir, la tasa de inter�s, la duraci�n y la aportaci�n anual\n" +
            "Calcular� los intereses totales para diferentes tasas de inter�s y plazos\n" +
            "Calcular� los intereses anuales y el nuevo capital durante la vida del dep�sito\n" +
            "Empezamos ya\nIntroduce la cantidad del deposito inicial: ");
    }
    
    // M�todo que calcula los intereses totales para distintas tasas e intereses y devuelve un valor.
    public static double intereses(Scanner teclado) {
        double deposito = teclado.nextDouble();
        for(int i = PLAZO_INI; i <= PLAZO_FIN; i++) {
            System.out.print(i + " a�os    ");
            for(double t = 1.0; t <= 2.5; t += 0.5) {
                // Intereses totales = inversi�n x tasa /100 x a�os
                System.out.print((deposito * t / 100 * i) + "(" + t + "%)    ");
            }
            System.out.println();
        } 
        return deposito; // Retorno valor a la llamada al m�todo.
    }
    
    // M�todo que solicita nuevos datos para el c�lculo anual de a�os posteriores y llama a un m�todo.
    public static void incremento(Scanner teclado, double deposito) {
        int acumulador = 0;     
        System.out.print("Introduce la aportaci�n anual a partir del segundo a�o: ");
        double aportacion = teclado.nextDouble();
        System.out.print("Introduce la tasa de inter�s (%): ");
        double tasaInteres = teclado.nextDouble();
        System.out.print("Introduce la duraci�n del dep�sito (a�os): ");
        int duracion = teclado.nextInt();
        for (int i = 1; i <= duracion; i++) { // Utilizo bucle for
            System.out.println("A�o " + i);
            deposito = deposito += acumulador; // Utilizo algoritmo acumulador
            System.out.println("\tC.Inicial: " + redondeo(deposito));
            // Intereses anuales = capital x tasa /100
            double intereses = (deposito * tasaInteres / 100);
            System.out.println("\tIntereses: " + redondeo(intereses));
            deposito = deposito + aportacion + intereses;
        }
    }
   
     // M�todo que redondea la variable double recibida a 2 decimales y la devuelve. 
    public static double redondeo(double dato) {
        dato = (double) Math.round(dato * 100) / 100;
        return dato;
    }
    
}