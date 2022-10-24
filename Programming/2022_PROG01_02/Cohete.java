/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 14/10/2022      *Módulo: Programación      *Unidad: 01       
  
   *Tarea: Tarea Evaluación 02. Realiza un programa en Java (70%) que visualice por consola una figura
   simétrica
   
   *Descripción: El objetivo es realizar un programa que visualice por consola una figura simétrica.
   Tendremos que utilizar métodos, variables, bucles FOR y la sentencias print y println. Usaremos
   métodos para evitar redundancias y definir la estructura de la figura. Además, utilizaremos bucles
   for para generar los patrones repetitivos.
   
   *Autoevaluación: https://drive.google.com/drive/folders/1oRID2WrWqUh5DwMvyXtw4wVkLFRhjr_6?usp=sharing

*********************************************************************************************************/
public class Cohete {

    // Método principal: llama a los 5 métodos que dibujarán la figura las veces que son necesarias.
    public static void main(String[] args) {
        escribeIntro();
        dibujaTriangulo();
        dibujaLinea();
        dibujaTriangulosPeques();
        dibujaTriangulosInvertidos();
        dibujaLinea();
        dibujaTriangulosInvertidos();
        dibujaTriangulosPeques();
        dibujaLinea();
        dibujaTriangulo();
    }
    
    // Método que escribe una primera introducción antes de dibujar la figura.
    public static void escribeIntro() {
        System.out.println("FIGURA:");
    }
    
    /* Método que dibuja un triángulo grande que se utiliza tanto en la parte superior del cohete
    como en la parte inferior. Al ser el primero, lo describo a mas detalle que el resto, ya que
    serán similares. Utilizamos la fórmula para averiguar patrones de líneas, en este caso:
        
                       LINEA  ESPACIO BARRA ** BARRA INVERTIDA    de la que sacamos las fórmulas:
             /**\        1       5      1            1                  ESPACIO = 6 - LINEA 
            //**\\       2       4      2            2                    BARRA = LINEA
           ///**\\\     
          ////**\\\\
         /////**\\\\\                                                                           */
    public static void dibujaTriangulo() {         
        // En cada una de las 5 líneas del triángulo
        for (int linea = 1; linea <= 5; linea++) {
            // Dibujamos los espacios
            for (int i = 0; i < (6 - linea); i++) {
                System.out.print(" ");
            }
            // Dibujamos las barras
            for (int i = 0; i < linea; i++) {
                System.out.print("/");
            }
            // Dibujamos los asteriscos
            System.out.print("**");
            // Dibujamos las barras invertidas
            for (int i = 0; i < linea; i++) {
                System.out.print("\\");
            }
            System.out.println();
        }
    }
    
    // Método que dibuja una línea horizontal formada por diferentes signos y que se utiliza 3 veces.
    public static void dibujaLinea() {
        System.out.println("+=*=*=*=*=*=*+");
    }
    
    /* Método que dibuja 2 triángulos pequeños que dan cuerpo al cohete y que se utiliza 2 veces.
    No explico cada paso ya que es similar al método anterior */
    public static void dibujaTriangulosPeques() {
        for (int linea = 1; linea <= 3; linea++) {
            System.out.print("|");
            for (int i = 0; i < (3 - linea); i++) {
               System.out.print(".");
            }
            for (int i = 0; i < linea; i++) {
                System.out.print("/\\");
            }
            for (int i = 0; i < (-2 * linea + 6); i++) {
                System.out.print(".");
            }
            for (int i = 0; i < linea; i++) {
                System.out.print("/\\");
            }
            for (int i = 0; i < (3 - linea); i++) {
               System.out.print(".");
            }
            System.out.println("|");
        }
    }
    
    // Método que dibuja 2 triángulos pequeños invertidos, dan cuerpo al cohete y se utiliza 2 veces.
    public static void dibujaTriangulosInvertidos() {
        for (int linea = 1; linea <= 3; linea++) {
            System.out.print("|");
            for (int i = 0; i < (linea - 1); i++) {
               System.out.print(".");
            }
            for (int i = 0; i < (-linea + 4); i++) {
                System.out.print("\\/");
            }
            for (int i = 0; i < (2 * linea - 2); i++) {
                System.out.print(".");
            }
            for (int i = 0; i < (-linea + 4); i++) {
                System.out.print("\\/");
            }
            for (int i = 0; i < (linea - 1); i++) {
               System.out.print(".");
            }
            System.out.println("|");
        }
    }
}