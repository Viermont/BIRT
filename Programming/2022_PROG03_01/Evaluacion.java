/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 18/11/2022      *M�dulo: Programaci�n      *Unidad: 03       
  
   *Tarea: Tarea Evaluaci�n 01. Realiza un programa en Java (40%)
   
   *Descripci�n: programa en el que tendremos que utilizar estructuras condicionales IF-ELSE, uso de
   variables, bucles FOR, m�todos con paso de par�metros y sentencia return as� como, las funcionalidades
   de las clases de las librer�as de java. El programa permitir� calcular la nota media de un estudiante
   a partir de la calificaci�n del examen parcial, el examen final y las Unidades Did�cticas realizadas.
   
   *Autoevaluaci�n: https://drive.google.com/drive/folders/1F9vEjfDqz8xv6hvD1scjGIYETnWcRxE_?usp=sharing
   
*********************************************************************************************************/
import java.util.*;

// Clase principal, que da nombre al programa y en la que declaro 3 variables constantes.
public class Evaluacion {
    public static final int NOTA_MAX = 100;
    public static final int PESO_PARCIAL = 40;
    public static final int PESO_FINAL = 60;

    // M�todo principal, en el creo la clase Scanner y llamo a los m�todos intro y calculaNota
    public static void main(String[] args) {    
        Scanner teclado = new Scanner(System.in);
        intro();
        double notaAlumnoA = calculaNota(teclado);
        /*Las 2 siguientes l�neas s�lo se utilizan para la funcionalidad de comparar las notas de 2 alumnos
        pero hay que dejarla comentada y sin uso efectivo.
        double notaAlumnoB = calculaNota(teclado);
        comparaAlumnos(notaAlumnoA, notaAlumnoB);*/
        teclado.close();                                    
    }
    
    // M�todo intro, escribe una introducci�n al programa.
    public static void intro() {
        System.out.println("\nEste programa lee las calificaciones de ex�menes (parcial y final) y Unidades"
        + " Did�cticas\npara calcular la nota final del m�dulo.\nTambi�n podr�a hacerlo para 2 estudiantes"
        + " y comparar sus notas. Funcionar�a sin problemas.");
    }
    
    /* M�todo calculaNota, recive como par�metro un objeto de tipo Scanner, que a su vez env�a a los
    diferentes m�todos que he utilizado para estructurar el programa y evitar redundancias. Devuelve
    al main una variable de tipo double con la nota final del alumno para poder tener la funcionalidad
    que compare la nota de 2 alumnos, sino no ser�a necesario el return*/
    public static double calculaNota(Scanner teclado) {
        double notaParcial = notaExamenes(teclado, "PARCIAL");
        double notaFinal = notaExamenes(teclado, "FINAL");
        totalExamenes(notaParcial, notaFinal);
        double notaUnid = notaUnidades(teclado);
        double notaAlumno = notaFin(notaParcial, notaFinal, notaUnid);
        escalaNotas(notaAlumno);
        return notaAlumno;
    }
    
    /* M�todo notaExamenes, recibe como par�metros un objeto de tipo Scanner y una variable de tipo String
    que define el tipo de examen. Solicita la nota de dicho examen y los puntos extra, calcula la nota del
    examen y llama al m�todo sumaExtra que calcula la nota del examen con puntos extra y llama al m�todo
    calculaPonderada que realice los c�lculos. Devuelve la nota final ponderada al m�todo CalculaNota*/
    public static double notaExamenes(Scanner teclado, String tipoNota) {
        System.out.print("\n" + tipoNota + ":\nIntroduce la calificaci�n del examen (0-100): ");
        int nota = teclado.nextInt();
        System.out.print("\n�Has obtenido puntos extra (1=Si, 2=No)? ");
            if (teclado.nextInt() == 1) {
                nota = sumaExtra(teclado, nota);
            }
        System.out.println("\nNota final = " + nota + " / " + NOTA_MAX);
        return calculaPonderada(tipoNota, nota);
    }
    
    /* M�todo sumaExtra, recibe como par�metros el objeto de tipo Scanner y la nota del examen, solicita los
    puntos extra y los suma a la nota con un l�mite m�ximo mediante. Devuelve la suma al m�todo NotaExamenes*/
    public static int sumaExtra(Scanner teclado, int nota) {
        System.out.print("\nIntroduce el total de puntos extra (0-10): ");
        nota += teclado.nextInt();
        if (nota < NOTA_MAX) {
            return nota;
        } else {
            return NOTA_MAX;
        }       
    }
    
    /* M�todo calculaPonderada, recibe un objeto de tipo String con el tipo de nota de examen y la nota de
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
    
    /* M�todo totalExamenes, recive 2 variables de tipo double como par�metros con las notas del examen
    parcial y final y muestra la nota media ponderada con 1 decimal*/
    public static void totalExamenes(double notaParcial, double notaFinal) {
        System.out.printf("\nTOTAL EXAMENES\nNota final ponderada de los ex�menes (sobre "
        + (PESO_PARCIAL + PESO_FINAL) + "): %.1f\n", (notaParcial + notaFinal));
    }
    
    /* M�todo notaUnidades, recibe un objeto tipo Scanner como par�metro, solicita el n� de unidades,
    y para cada unidad solicita el peso de dicha unidad y su nota, calcula la nota final de las unidades
    ponderada y con 1 decimal, y devuelve dicha nota.*/
    public static double notaUnidades(Scanner teclado) {
        System.out.print("\nUNIDADES\nIntroduce el n�mero de unidades: ");
        int numUnid = teclado.nextInt();
        double notaUnid = 0;
        for (int i = 1; i <= numUnid; i++) {
            System.out.print("\nUD " + i + ":\nIntroduce el peso de la UD (0-100): ");
            int peso = teclado.nextInt();
            System.out.print("\nIntroduce la puntuaci�n obtenida (0-100):");
            int puntuacion = teclado.nextInt();
            notaUnid += ((double)puntuacion * peso / 100);
        }
        System.out.printf("\nTOTAL UDs\nNota final ponderada de las UDs (sobre 100): %.1f\n", notaUnid);
        return notaUnid;
    }
    
    /* M�todo notaFin, recive como par�metros 3 variables tipo double con la nota de los 2 ex�menes y de
    las unidades y muestra por pantalla la nota final teniendo en cuenta el peso de cada nota.*/
    public static double notaFin(double notaParcial, double notaFinal, double notaUnid) {
        double notaAlumno = (notaParcial + notaFinal + notaUnid) / 2;
        System.out.printf("\n\nTOTAL FINAL: %.1f\n", notaAlumno);
        return notaAlumno;
    }
    
    /* M�todo EscalaNotas, convierte el valor de la num�rico de la nota final del alumno en una escala de
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
            + "Todav�a tienes trabajo por hacer");
        } else {
            System.out.println("La nota final en una escala del 0 al 4 es al menos: 0.0\n"
            + "Otra vez ser�");
        }
    }
    
    /* M�todo comparaAlumnos, recibe como par�metros la nota de 2 alumnos, compara si son iguales o una de
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