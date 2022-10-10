/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 30/09/2022      *Módulo: Programación      *Unidad: 01       
  
   *Tarea: Tarea Evaluación 01. Realiza un programa en Java (30%) que visualice por consola una canción
   acumulativa
   
   *Descripción: El programa utiliza 10 métodos, desde el main llama a los 5 métodos de cada estrofa,
   cada uno de ellos escribe la estrofa correspondiente mediante println y seguido llama al método de
   estribillo correspondiente, que a su vez, escribe el estribillo y el salto de línea de utilizando
   de nuevo println.
   
   *Autoevaluación: https://drive.google.com/drive/folders/1Ud-KyRBlnZonz89crt5pPQKTG_G-icK6?usp=sharing

*********************************************************************************************************/
public class MoscaMora {

   // Método principal: llama a los 5 métodos de estrofa.
	public static void main(String[] args) {
      estroUno();
      estroDos();
      estroTres();
      estroCuatro();
      estroCinco();
   }
   
   // Método estrofa 1: escribe su estrofa y llama al método de estribillo que corresponde.
   public static void estroUno () {
	      System.out.println("Estaba la mora en su lugar,");
	      System.out.println("Vino la mosca y le hizo mal,");
         estribCinco();
   }
   
   // Método estrofa 2: escribe su estrofa y llama al método de estribillo que corresponde.     
   public static void estroDos () {
	      System.out.println("Estaba la mosca en su lugar,");
	      System.out.println("Vino la araña y le hizo mal,");
         estribCuatro();
   }

   // Método estrofa 3: escribe su estrofa y llama al método de estribillo que corresponde.  
   public static void estroTres () {         
	      System.out.println("Estaba la araña en su lugar,");
	      System.out.println("Vino el ratón y le hizo mal,");
         estribTres();
   }

   // Método estrofa 4: escribe su estrofa y llama al método de estribillo que corresponde.      
   public static void estroCuatro () {         
	      System.out.println("Estaba el ratón en su lugar,");
	      System.out.println("Vino el gato y le hizo mal,");
         estribDos();
   }

   // Método estrofa 5: escribe su estrofa y llama al método de estribillo que corresponde.     
   public static void estroCinco () {   
	      System.out.println("Estaba el gato en su lugar,");
	      System.out.println("Vino el perro y le hizo mal,");
         estribUno();     
   }
                 
   // Método estribillo 5: escribe el último estribillo que se repite siempre y el salto de línea.
   public static void estribCinco () {         
	      System.out.println("La mosca a la mora,");
	      System.out.println("Y la mora en su moralito sola.");
	      System.out.println();
   }

   // Método estribillo 4: escribe su estribillo y llama al siguiente.
   public static void estribCuatro () {  
	      System.out.println("La araña a la mosca,");
         estribCinco();
   }         

   // Método estribillo 3: escribe su estribillo y llama al siguiente.
   public static void estribTres () { 
	      System.out.println("El ratón a la araña,");
         estribCuatro();
   }
        
   // Método estribillo 2: escribe su estribillo y llama al siguiente.
   public static void estribDos () {  
	      System.out.println("El gato al ratón,");
         estribTres();
   }
         
   // Método estribillo 1: escribe su estribillo y llama al siguiente.
   public static void estribUno () { 
	      System.out.println("El perro al gato,");
         estribDos();
   }	
}
