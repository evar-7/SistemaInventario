package Controladores;

import Modelo.Carrito;
import Modelo.ItemCarrito;
import Vista.Producto;
import Vista.Inicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorProductoIndividual implements ActionListener {

    private Producto vistaProducto;
    private Inicio vistaInicio;

    public ControladorProductoIndividual(Producto vistaProducto, Inicio vistaInicio) {
        this.vistaProducto = vistaProducto;
        this.vistaInicio = vistaInicio;
        agregarEventos();
    }

    private void agregarEventos() {
        vistaProducto.getBtnAgregarCarrito().addActionListener(this);
        vistaProducto.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaProducto.getBtnAgregarCarrito()) {
            agregarProductoAlCarrito();
        } else if (e.getSource() == vistaProducto.getBtnVolver()) {
            vistaProducto.dispose();
            vistaInicio.setVisible(true);
        }
    }

    private void agregarProductoAlCarrito() {
        try {
            String nombre = vistaProducto.getjLabel4().getText().replace("Nombre Producto: ", "").trim();
            String descripcion = vistaProducto.getjLabel3().getText().replace("Descripción: ", "").trim();
            String stockTexto = vistaProducto.getjLabel1().getText().replace("Stock Disponible: ", "").trim();
            String precioTexto = vistaProducto.getjLabel5().getText().replace("Precio: ₡", "").trim();

            int idGenerico = nombre.hashCode(); // TEMPORAL hasta tener ID real
            int cantidad = 1;
            int stock = Integer.parseInt(stockTexto);
            double precio = Double.parseDouble(precioTexto);

            if (stock <= 0) {
                JOptionPane.showMessageDialog(vistaProducto, "No hay stock disponible.");
                return;
            }

            ItemCarrito item = new ItemCarrito(idGenerico, nombre, cantidad, precio);
            Carrito.agregarProducto(item);
            JOptionPane.showMessageDialog(vistaProducto, nombre + " agregado al carrito.");
            JOptionPane.showMessageDialog(vistaProducto, nombre + " agregado al carrito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaProducto, "Error al agregar al carrito: " + ex.getMessage());
        }
    }
}
