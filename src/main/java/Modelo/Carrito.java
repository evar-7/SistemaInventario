package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private static List<ItemCarrito> productos = new ArrayList<>();

    public static void agregarProducto(ItemCarrito nuevoItem) {
        for (ItemCarrito item : productos) {
            if (item.getIdProducto() == nuevoItem.getIdProducto()) {
                item.setCantidad(item.getCantidad() + nuevoItem.getCantidad());
                return;
            }
        }
        productos.add(nuevoItem);

        System.out.println("Producto agregado al carrito:");
        for (ItemCarrito i : Carrito.getProductos()) {
            System.out.println("- " + i.getNombreProducto() + " | Cantidad: " + i.getCantidad() + " | Precio unitario: " + i.getPrecioUnitario());
        }
        System.out.println("===============================");

    }

    public static List<ItemCarrito> getProductos() {
        return productos;
    }

    public static void eliminarProducto(int idProducto) {
        productos.removeIf(p -> p.getIdProducto() == idProducto);
    }

    public static void vaciar() {
        productos.clear();
    }

    public static double calcularTotal() {
        double total = 0;
        for (ItemCarrito p : productos) {
            total += p.getSubtotal();
        }
        return total;
    }
}
