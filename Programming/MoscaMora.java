/********************************************************************************************************
   *Autor: Javier Montero Martinez     *Fecha: 30/09/2022      *M�dulo: Programaci�n      *Unidad: 01       
  
   *Tarea: Tarea Evaluaci�n 01. Realiza un programa en Java (30%) que visualice por consola una canci�n
   acumulativa
   
   *Descripci�n: El programa utiliza 10 m�todos, desde el main llama a los 5 m�todos de cada estrofa,
   cada uno de ellos escribe la estrofa correspondiente mediante println y seguido llama al m�todo de
   estribillo correspondiente, que a su vez, escribe el estribillo y el salto de l�nea de utilizando
   de nuevo println.
   
   *Autoevaluaci�n: https://drive.google.com/drive/folders/1Ud-KyRBlnZonz89crt5pPQKTG_G-icK6?usp=sharing

*********************************************************************************************************/
public class MoscaMora {

   // M�todo principal: llama a los 5 m�todos de estrofa.
	public static void main(String[] args) {
      estroUno();
      estroDos();
      estroTres();
      estroCuatro();
      estroCinco();
   }
   
   // M�todo estrofa 1: escribe su estrofa y llama al m�todo de estribillo que corresponde.
   public static void estroUno () {
	      System.out.println("Estaba la mora en su lugar,");
	      System.out.println("Vino la mosca y le hizo mal,");
         estribCinco();
   }
   
   // M�todo estrofa 2: escribe su estrofa y llama al m�todo de estribillo que corresponde.     
   public static void estroDos () {
	      System.out.println("Estaba la mosca en su lugar,");
	      System.out.println("Vino la ara�a y le hizo mal,");
         estribCuatro();
   }

   // M�todo estrofa 3: escribe su estrofa y llama al m�todo de estribillo que corresponde.  
   public static void estroTres () {         
	      System.out.println("Estaba la ara�a en su lugar,");
	      System.out.println("Vino el rat�n y le hizo mal,");
         estribTres();
   }

   // M�todo estrofa 4: escribe su estrofa y llama al m�todo de estribillo que corresponde.      
   public static void estroCuatro () {         
	      System.out.println("Estaba el rat�n en su lugar,");
	      System.out.println("Vino el gato y le hizo mal,");
         estribDos();
   }

   // M�todo estrofa 5: escribe su estrofa y llama al m�todo de estribillo que corresponde.     
   public static void estroCinco () {   
	      System.out.println("Estaba el gato en su lugar,");
	      System.out.println("Vino el perro y le hizo mal,");
         estribUno();     
   }
                 
   // M�todo estribillo 5: escribe el �ltimo estribillo que se repite siempre y el salto de l�nea.
   public static void estribCinco () {         
	      System.out.println("La mosca a la mora,");
	      System.out.println("Y la mora en su moralito sola.");
	      System.out.println();
   }

   // M�todo estribillo 4: escribe su estribillo y llama al siguiente.
   public static void estribCuatro () {  
	      System.out.println("La ara�a a la mosca,");
         estribCinco();
   }         

   // M�todo estribillo 3: escribe su estribillo y llama al siguiente.
   public static void estribTres () { 
	      System.out.println("El rat�n a la ara�a,");
         estribCuatro();
   }
        
   // M�todo estribillo 2: escribe su estribillo y llama al siguiente.
   public static void estribDos () {  
	      System.out.println("El gato al rat�n,");
         estribTres();
   }
         
   // M�todo estribillo 1: escribe su estribillo y llama al siguiente.
   public static void estribUno () { 
	      System.out.println("El perro al gato,");
         estribDos();
   }	
}
