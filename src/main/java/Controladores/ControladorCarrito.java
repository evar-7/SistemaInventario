
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
import javax.swing.table.DefaultTableModel;

public class ControladorCarrito implements ActionListener {
    private VistaCarrito vistaCarrito;
    private Carrito carrito;

    public ControladorCarrito(VistaCarrito vistaCarrito, Carrito carrito) {
        this.vistaCarrito = vistaCarrito;
        this.carrito = carrito;
        
        mostrarProductosEnCarrito();

        // Cargar productos en la tabla
        vistaCarrito.cargarDatosCarrito(carrito.getProductos());

        // Agregar eventos
        vistaCarrito.getBtnCerrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCarrito.getBtnCerrar()) {
            vistaCarrito.dispose();
        }
    }
    
    private void mostrarProductosEnCarrito() {
        if (carrito != null && carrito.getProductos() != null) {
            DefaultTableModel modelo = (DefaultTableModel) vistaCarrito.getTablaCarrito().getModel();
            modelo.setRowCount(0);

            for (Productos producto : carrito.getProductos()) {
                Object[] fila = {
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStockDisponible()
                };
                modelo.addRow(fila);
            }
        }
    }
}