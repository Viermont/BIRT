/*Autor: Javier Montero Martinez   Fecha: 24/09/2021   Módulo: Programación
Unidad: 01   Tarea: Tarea Evaluación 01   Explicación:
1º- He corregido los errores de ejecución del programa hasta
conseguir que se muestre en salida tal y como muestra el enunciado.
2º - He estructurado el método main por estrofas de la canción evitando
las redundancias de código.
3º - A la última estrofa no le he creado un método ya que no se ha repetido
el código, pero de haber continuado la canción, si habría sido necesario.
Enlace autoevaluación:
https://drive.google.com/drive/folders/1fDBAqJXdrcP6Fz9XTnfhrowqDveRWpw5?usp=sharing
*/
public class ALevantarse {
   public static void main(String[] args) {
      System.out.println("Hoy a las siete de la mañana,");
      System.out.println("el sol empieza a entrar por la ventana");
      despertador();
      
      System.out.println("Sigo durmiendo otro ratito,");
      System.out.println("igual no importa porque aún es tempranito.");
      galloCantor();
      
      System.out.println("Doy media vuelta sigo durmiendo,");
      System.out.println("hace un rato que ya está amaneciendo");
      tambor();
      
      System.out.println("Ya mucha luz entra en mi pieza,");
      System.out.println("pero yo tengo un poco de pereza");
      System.out.println("Suena la bocina de un camión");
      tambor();
   }
   public static void despertador() {
      System.out.println("Suena el despertador");
      System.out.println("Cinco minutos más por favor");
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
