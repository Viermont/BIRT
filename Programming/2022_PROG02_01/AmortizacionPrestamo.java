/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 4/11/2022      *Módulo: Programación      *Unidad: 02       
  
   *Tarea: Tarea Evaluación 02. Realiza un programa en Java (100%)
   
   *Descripción: El objetivo es practicar en nuestros programas el manejo de variables, bucles FOR y
   métodos con paso de parámetros y sentencia return. Utilizaremos funcionalidades de las clases Math
   y Scanner. El programa proporcionará diferentes cálculos para depósitos a plazo fijo.
   
   *Autoevaluación: https://drive.google.com/drive/folders/1rKRxLBSkV99C26-PVkfLdkkT29I6gpVu?usp=sharing

*********************************************************************************************************/
import java.util.*; // Importo libreria de Java para poder usar la clase Scanner.

public class AmortizacionPrestamo { // Clase principal que da nombre al programa.
    // Variables constantes para los plazos del depósito.
    public static final int PLAZO_INI = 5;
    public static final int PLAZO_FIN = 8;

    // Método principal: llama a 3 métodos para la ejecución del programa, a 2 de ellos les paso parámetros.
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Declaro variable de tipo Scanner.
        teclado.useLocale(Locale.ENGLISH); // Pongo teclado en inglés para evitar errores con decimales.
        intro();
        double deposito = intereses(teclado); // Guardo en la variable la devolución de la sentencia return.
        incremento(teclado, deposito);
    }
    
    // Método que nos introduce al programa y nos solicita información.
    public static void intro() {
        System.out.print("Este programa calcula los intereses obtenidos con un depósito a plazo fijo\n" +
            "Pedirá la cantidad a invertir, la tasa de interés, la duración y la aportación anual\n" +
            "Calculará los intereses totales para diferentes tasas de interés y plazos\n" +
            "Calculará los intereses anuales y el nuevo capital durante la vida del depósito\n" +
            "Empezamos ya\nIntroduce la cantidad del deposito inicial: ");
    }
    
    // Método que calcula los intereses totales para distintas tasas e intereses y devuelve un valor.
    public static double intereses(Scanner teclado) {
        double deposito = teclado.nextDouble();
        for(int i = PLAZO_INI; i <= PLAZO_FIN; i++) {
            System.out.print(i + " años    ");
            for(double t = 1.0; t <= 2.5; t += 0.5) {
                // Intereses totales = inversión x tasa /100 x años
                System.out.print((deposito * t / 100 * i) + "(" + t + "%)    ");
            }
            System.out.println();
        } 
        return deposito; // Retorno valor a la llamada al método.
    }
    
    // Método que solicita nuevos datos para el cálculo anual de años posteriores y llama a un método.
    public static void incremento(Scanner teclado, double deposito) {
        int acumulador = 0;     
        System.out.print("Introduce la aportación anual a partir del segundo año: ");
        double aportacion = teclado.nextDouble();
        System.out.print("Introduce la tasa de interés (%): ");
        double tasaInteres = teclado.nextDouble();
        System.out.print("Introduce la duración del depósito (años): ");
        int duracion = teclado.nextInt();
        for (int i = 1; i <= duracion; i++) { // Utilizo bucle for
            System.out.println("Año " + i);
            deposito = deposito += acumulador; // Utilizo algoritmo acumulador
            System.out.println("\tC.Inicial: " + redondeo(deposito));
            // Intereses anuales = capital x tasa /100
            double intereses = (deposito * tasaInteres / 100);
            System.out.println("\tIntereses: " + redondeo(intereses));
            deposito = deposito + aportacion + intereses;
        }
    }
   
     // Método que redondea la variable double recibida a 2 decimales y la devuelve. 
    public static double redondeo(double dato) {
        dato = (double) Math.round(dato * 100) / 100;
        return dato;
    }
    
}