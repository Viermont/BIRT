// Clase Producto.
public class Producto {
// Atributos
    private String nombre;
    private double precio;
    private int cantidad;

// Constructor
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
   
    public Producto(String nombre, double precio) {   // Supondrá cantidad = 0
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 0;
    }
    
    public Producto(Producto unProducto) {            // Supondrá cantidad = 0
        this(unProducto.getNombre(), unProducto.getPrecio(), 0);
    }
     
    // Métodos  
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }


    // Calcula y devuelve el precio total de la compra
    public double getPrecioTotal() {
        return precio * cantidad;
    }

    // Formatea la compra de un producto de la siguiente manera
    // Nombre: hueco de 30 caracteres, alineado a la izquierda
    // Precio: hueco de 8 caracteres, precio alineado a la derecha, 2 decimales
    // Cantidad: hueco de 8 caracteres, alineado a la derecha
    // Precio total: hueco de 8 caracteres, precio alineado a la derecha, 2 decimales
    // Naranjas                          1,40       5    7,00
    // Te puede ayudar usar el método format de la clase String
    public String toString() {
        String cadenaImprimir = "";
        cadenaImprimir += String.format("%-30s %8.2f %8d %8.2f", nombre, precio, cantidad, getPrecioTotal());
        return cadenaImprimir;
    }
}