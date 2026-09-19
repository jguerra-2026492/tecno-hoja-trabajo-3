package main.java.com.javatesting.kinalproyect.model;

/**
 * Modelo simple que representa un producto de la tabla "productos".
 */
public class Producto {

    private String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return codigo + "  |  " + nombre + "  |  Q" + precio;
    }
}
