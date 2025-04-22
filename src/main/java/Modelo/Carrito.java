
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erick
 */
public class Carrito {
    private List<Productos> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Productos producto) {
        productos.add(producto);
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void vaciar() {
        productos.clear();
    }

    public double calcularTotal() {
        double total = 0;
        for (Productos p : productos) {
            total += p.getPrecio();
        }
        return total;
    }
}