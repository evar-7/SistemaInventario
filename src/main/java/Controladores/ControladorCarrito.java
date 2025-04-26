
package Controladores;

/**
 *
 * @author Erick
 */

import Modelo.Carrito;
import Modelo.Productos;
import Vista.Producto;
import Vista.VistaCarrito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorCarrito {
    private static List<Carrito> carrito = new ArrayList<>();

    public static void agregarProducto(Carrito producto) {
        carrito.add(producto);
        
    }

    public static List<Carrito> obtenerProductos() {
        return carrito;
    }

//    public static void eliminarProducto(int idProducto) {
//        carrito.removeIf(c -> c.getIdProducto() == idProducto);
//    }

    public static void vaciarCarrito() {
        carrito.clear();
    }
    
}