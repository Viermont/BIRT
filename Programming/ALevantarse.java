/*Autor: Javier Montero Martinez   Fecha: 24/09/2021   M�dulo: Programaci�n
Unidad: 01   Tarea: Tarea Evaluaci�n 01   Explicaci�n:
1�- He corregido los errores de ejecuci�n del programa hasta
conseguir que se muestre en salida tal y como muestra el enunciado.
2� - He estructurado el m�todo main por estrofas de la canci�n evitando
las redundancias de c�digo.
3� - A la �ltima estrofa no le he creado un m�todo ya que no se ha repetido
el c�digo, pero de haber continuado la canci�n, si habr�a sido necesario.
Enlace autoevaluaci�n:
https://drive.google.com/drive/folders/1fDBAqJXdrcP6Fz9XTnfhrowqDveRWpw5?usp=sharing
*/
public class ALevantarse {
   public static void main(String[] args) {
      System.out.println("Hoy a las siete de la ma�ana,");
      System.out.println("el sol empieza a entrar por la ventana");
      despertador();
      
      System.out.println("Sigo durmiendo otro ratito,");
      System.out.println("igual no importa porque a�n es tempranito.");
      galloCantor();
      
      System.out.println("Doy media vuelta sigo durmiendo,");
      System.out.println("hace un rato que ya est� amaneciendo");
      tambor();
      
      System.out.println("Ya mucha luz entra en mi pieza,");
      System.out.println("pero yo tengo un poco de pereza");
      System.out.println("Suena la bocina de un cami�n");
      tambor();
   }
   public static void despertador() {
      System.out.println("Suena el despertador");
      System.out.println("Cinco minutos m�s por favor");
      System.out.println();      
   }
   public static void galloCantor() {
      System.out.println("Canta el gallo cantor");
      despertador();
   }
   public static void tambor() {
      System.out.println("Mi hermano toca el tambor");
      galloCantor();
   }
}