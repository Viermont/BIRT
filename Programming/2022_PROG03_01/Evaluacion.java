/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 18/11/2022      *Módulo: Programación      *Unidad: 03       
  
   *Tarea: Tarea Evaluación 01. Realiza un programa en Java (40%)
   
   *Descripción: programa en el que tendremos que utilizar estructuras condicionales IF-ELSE, uso de
   variables, bucles FOR, métodos con paso de parámetros y sentencia return así como, las funcionalidades
   de las clases de las librerías de java. El programa permitirá calcular la nota media de un estudiante
   a partir de la calificación del examen parcial, el examen final y las Unidades Didácticas realizadas.
   
   *Autoevaluación: https://drive.google.com/drive/folders/1F9vEjfDqz8xv6hvD1scjGIYETnWcRxE_?usp=sharing
   
*********************************************************************************************************/
import java.util.*;

// Clase principal, que da nombre al programa y en la que declaro 3 variables constantes.
public class Evaluacion {
    public static final int NOTA_MAX = 100;
    public static final int PESO_PARCIAL = 40;
    public static final int PESO_FINAL = 60;

    // Método principal, en el creo la clase Scanner y llamo a los métodos intro y calculaNota
    public static void main(String[] args) {    
        Scanner teclado = new Scanner(System.in);
        intro();
        double notaAlumnoA = calculaNota(teclado);
        /*Las 2 siguientes líneas sólo se utilizan para la funcionalidad de comparar las notas de 2 alumnos
        pero hay que dejarla comentada y sin uso efectivo.
        double notaAlumnoB = calculaNota(teclado);
        comparaAlumnos(notaAlumnoA, notaAlumnoB);*/
        teclado.close();                                    
    }
    
    // Método intro, escribe una introducción al programa.
    public static void intro() {
        System.out.println("\nEste programa lee las calificaciones de exámenes (parcial y final) y Unidades"
        + " Didácticas\npara calcular la nota final del módulo.\nTambién podría hacerlo para 2 estudiantes"
        + " y comparar sus notas. Funcionaría sin problemas.");
    }
    
    /* Método calculaNota, recive como parámetro un objeto de tipo Scanner, que a su vez envía a los
    diferentes métodos que he utilizado para estructurar el programa y evitar redundancias. Devuelve
    al main una variable de tipo double con la nota final del alumno para poder tener la funcionalidad
    que compare la nota de 2 alumnos, sino no sería necesario el return*/
    public static double calculaNota(Scanner teclado) {
        double notaParcial = notaExamenes(teclado, "PARCIAL");
        double notaFinal = notaExamenes(teclado, "FINAL");
        totalExamenes(notaParcial, notaFinal);
        double notaUnid = notaUnidades(teclado);
        double notaAlumno = notaFin(notaParcial, notaFinal, notaUnid);
        escalaNotas(notaAlumno);
        return notaAlumno;
    }
    
    /* Método notaExamenes, recibe como parámetros un objeto de tipo Scanner y una variable de tipo String
    que define el tipo de examen. Solicita la nota de dicho examen y los puntos extra, calcula la nota del
    examen y llama al método sumaExtra que calcula la nota del examen con puntos extra y llama al método
    calculaPonderada que realice los cálculos. Devuelve la nota final ponderada al método CalculaNota*/
    public static double notaExamenes(Scanner teclado, String tipoNota) {
        System.out.print("\n" + tipoNota + ":\nIntroduce la calificación del examen (0-100): ");
        int nota = teclado.nextInt();
        System.out.print("\n¿Has obtenido puntos extra (1=Si, 2=No)? ");
            if (teclado.nextInt() == 1) {
                nota = sumaExtra(teclado, nota);
            }
        System.out.println("\nNota final = " + nota + " / " + NOTA_MAX);
        return calculaPonderada(tipoNota, nota);
    }
    
    /* Método sumaExtra, recibe como parámetros el objeto de tipo Scanner y la nota del examen, solicita los
    puntos extra y los suma a la nota con un límite máximo mediante. Devuelve la suma al método NotaExamenes*/
    public static int sumaExtra(Scanner teclado, int nota) {
        System.out.print("\nIntroduce el total de puntos extra (0-10): ");
        nota += teclado.nextInt();
        if (nota < NOTA_MAX) {
            return nota;
        } else {
            return NOTA_MAX;
        }       
    }
    
    /* Método calculaPonderada, recibe un objeto de tipo String con el tipo de nota de examen y la nota de
    dicho examen, calcula la nota ponderada teniendo el cuenta el peso de dicho examen, la muestra con 1
    decimal y la devuelve*/
    public static double calculaPonderada(String tipoNota, int nota) {
        int peso;
        double notaPonderada;
        if (tipoNota == "PARCIAL") { 
            notaPonderada = (double)nota * PESO_PARCIAL / (PESO_PARCIAL + PESO_FINAL);
            System.out.printf("Nota final ponderada = %.1f", notaPonderada);
            System.out.println(" / " + PESO_PARCIAL);
        } else {
            notaPonderada = (double)nota * PESO_FINAL / (PESO_PARCIAL + PESO_FINAL);
            System.out.printf("Nota final ponderada = %.1f", notaPonderada);
            System.out.println(" / " + PESO_FINAL);
        }
        return notaPonderada;
    }
    
    /* Método totalExamenes, recive 2 variables de tipo double como parámetros con las notas del examen
    parcial y final y muestra la nota media ponderada con 1 decimal*/
    public static void totalExamenes(double notaParcial, double notaFinal) {
        System.out.printf("\nTOTAL EXAMENES\nNota final ponderada de los exámenes (sobre "
        + (PESO_PARCIAL + PESO_FINAL) + "): %.1f\n", (notaParcial + notaFinal));
    }
    
    /* Método notaUnidades, recibe un objeto tipo Scanner como parámetro, solicita el nº de unidades,
    y para cada unidad solicita el peso de dicha unidad y su nota, calcula la nota final de las unidades
    ponderada y con 1 decimal, y devuelve dicha nota.*/
    public static double notaUnidades(Scanner teclado) {
        System.out.print("\nUNIDADES\nIntroduce el número de unidades: ");
        int numUnid = teclado.nextInt();
        double notaUnid = 0;
        for (int i = 1; i <= numUnid; i++) {
            System.out.print("\nUD " + i + ":\nIntroduce el peso de la UD (0-100): ");
            int peso = teclado.nextInt();
            System.out.print("\nIntroduce la puntuación obtenida (0-100):");
            int puntuacion = teclado.nextInt();
            notaUnid += ((double)puntuacion * peso / 100);
        }
        System.out.printf("\nTOTAL UDs\nNota final ponderada de las UDs (sobre 100): %.1f\n", notaUnid);
        return notaUnid;
    }
    
    /* Método notaFin, recive como parámetros 3 variables tipo double con la nota de los 2 exámenes y de
    las unidades y muestra por pantalla la nota final teniendo en cuenta el peso de cada nota.*/
    public static double notaFin(double notaParcial, double notaFinal, double notaUnid) {
        double notaAlumno = (notaParcial + notaFinal + notaUnid) / 2;
        System.out.printf("\n\nTOTAL FINAL: %.1f\n", notaAlumno);
        return notaAlumno;
    }
    
    /* Método EscalaNotas, convierte el valor de la numérico de la nota final del alumno en una escala de
    0 a 4 y muestra un mensaje.*/
    public static void escalaNotas(double notaAlumno) {
        if(notaAlumno == 100) {
            System.out.println("La nota final en una escala del 0 al 4 es al menos: 4.0\n"
            + "Excelente trabajo");
        } else if (notaAlumno >= 85 && notaAlumno < 100) {
            System.out.println("La nota final en una escala del 0 al 4 es al menos: 3.0\n"
            + "Muy buen trabajo");
        } else if (notaAlumno >= 75 && notaAlumno < 85) {
            System.out.println("La nota final en una escala del 0 al 4 es al menos: 2.0\n"
            + "Buen trabajo");
        } else if (notaAlumno >= 60 && notaAlumno < 75) {
            System.out.println("La nota final en una escala del 0 al 4 es al menos: 1.5\n"
            + "Todavía tienes trabajo por hacer");
        } else {
            System.out.println("La nota final en una escala del 0 al 4 es al menos: 0.0\n"
            + "Otra vez será");
        }
    }
    
    /* Método comparaAlumnos, recibe como parámetros la nota de 2 alumnos, compara si son iguales o una de
    ellas es mayor y comenta el resultado. Esto es una funcionalidad aparte que no corrige la plataforma por
    lo que la dejo comentada para que pase los test*/
    /*public static void comparaAlumnos(double notaAlumnoA, double notaAlumnoB) {
        if (notaAlumnoA == notaAlumnoB) {
            System.out.println("Ambos alumnos tienen la misma nota: " + notaAlumnoA);
        } else if (notaAlumnoA > notaAlumnoB) {
            System.out.println("El alumno A, con una nota media de " + notaAlumnoA + ", tiene mejor nota "
            + "que el alumno B, que tiene una media de " + notaAlumnoB);
        } else {
            System.out.println("El alumno B, con una nota media de " + notaAlumnoB + ", tiene mejor nota "
            + "que el alumno A, que tiene una media de " + notaAlumnoA);
        }
    }*/
}