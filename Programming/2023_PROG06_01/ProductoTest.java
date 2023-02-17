/********************************************************************************************************
 *Autor: Javier Montero Martinez      *Fecha: 10/02/2023       *Módulo: Programación        *Unidad: 06
  
 *Tarea: Tarea Evaluación 01. Realiza un programa en Java (40%)
   
 *Descripción: Esta tarea se centra en el proceso de creación y testeo de la clase Producto. Para ello se
  crearán y utilizarán 2 ficheros .java, Producto.java y ProductoTest.java.
 
 *Autoevaluación: https://drive.google.com/drive/folders/1D7j8RQqE-5TuGaCQ9hqbE6J4zpUo4gNI?usp=sharing
   
*********************************************************************************************************/
/*
   Este programa testea la clase Producto
*/
import java.util.*;
import java.io.*;

public class ProductoTest {
 
    // Método principal
    public static void main(String[] args) {
   
        // Creamos un producto con el primer constructor
        Producto productoUno = new Producto("Naranjas", 1.6, 5);
      
        // Lo mostramos formateado con el método toString
        System.out.println(productoUno.toString() + "\n");
      
      
        // Creamos un producto con el segundo constructor y lo mostramos
        // Producto: "Chocolate", Precio: 2.35
        Producto productoDos = new Producto("Chocolate", 2.35);
        System.out.println(productoDos.toString());

      
        // Actualizamos la cantidad del producto anterior a 2 y lo mostramos
        productoDos.setCantidad(2);
        System.out.println(productoDos.toString());
      
        // Creamos un producto con el tercer constructor a partir del producto 1 y lo mostramos
        Producto productoTres = new Producto(productoUno);
        System.out.println(productoTres.toString());
      
        // Actualiza la cantidad del producto anterior a 10 y lo mostramos
        productoTres.setCantidad(10);
        System.out.println(productoTres.toString() + "\n");

      
        // Mostramos la siguiente información del producto 2
        // Nombre
        // Precio
        // Cantidad
        // Precio total       
        String nombreProducto = productoDos.getNombre();
        System.out.println("NOMBRE: " + nombreProducto);
        double precioProducto = productoDos.getPrecio();
        System.out.println("PRECIO: " + precioProducto);
        int cantidadProducto = productoDos.getCantidad();
        System.out.println("CANTIDAD: " + cantidadProducto);
        double precioTotal = productoDos.getPrecioTotal();
        System.out.println("PRECIO TOTAL: " + precioTotal);

   
        // Pedimos datos por teclado, creamos un producto y lo mostramos
        Scanner leerDatos = new Scanner(System.in);
        Producto otroProducto = pedirProducto(leerDatos);
        System.out.println("\n" + otroProducto.toString());
        
     
        // Creamos un array de 3 Productos, pedimos los datos y los mostramos
        Producto[] arrayProductos = new Producto[3];
        for (int i = 0; i < arrayProductos.length; i++) {
            arrayProductos[i] = pedirProducto(leerDatos);
        }
        System.out.println("\n" + Arrays.toString(arrayProductos) + "\n");
      
     
        // Mostramos el precio del elemento 1 de arrayProductos
        System.out.println("Precio del elemento 1: " + arrayProductos[1].getPrecio());
      
        // Mostramos el nombre del elemento 0 de arrayProductos
        System.out.println("Nombre del elemento 0: " + arrayProductos[0].getNombre());
        
        // Mostramos la cantidad del elemento 2 de arrayProductos
        System.out.println("Cantidad del elemento 2: " + arrayProductos[2].getCantidad());
      
        // Mostramos el precio total del elemento 1 de arrayProductos
        System.out.println("Precio total del elemento 1: " + arrayProductos[1].getPrecioTotal() + "\n");
      
      
        // Muestra la suma del precio total de todos los productos del arrayProductos
        double suma = precioTotal(arrayProductos);
        System.out.println("Suma total: " + suma);
    }
    
    // Pedimos datos por teclado y creamos un producto
    public static Producto pedirProducto(Scanner leerDatos) {
        Producto otroProducto = new Producto("", 0.0, 0);
        System.out.print("\nNombre: ");
        otroProducto.setNombre(leerDatos.next());
        System.out.print("\nPrecio: ");
        otroProducto.setPrecio(leerDatos.nextDouble());
        System.out.print("\nCantidad: ");
        otroProducto.setCantidad(leerDatos.nextInt());
        return otroProducto;    
    }
        
    // Mostramos el precio total de cada uno de los productos del array de Productos
    // y devolvemos la suma de todos ellos
    public static double precioTotal(Producto[] arrayProductos) {
        double suma = 0;
        for (int i = 0; i < arrayProductos.length; i++) {
            System.out.print("Precio total del producto " + (i + 1) + ": ");
            System.out.printf("%.2f %n", arrayProductos[i].getPrecioTotal());
            suma += arrayProductos[i].getPrecioTotal();
        }
        return (double)Math.round(suma * 100) / 100;
    }
}