package Controladores;

import Modelo.Carrito;
import Modelo.ProductosDAO;
import Vista.VistaProductos;
import Modelo.Productos;
import Vista.Inicio;
import Vista.Producto;
import Vista.VistaCarrito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProductoIndividual implements ActionListener {
    
    private Inicio vista;
    private ProductosDAO dao;
    private Carrito carrito;
    private Producto productoVista;
    


public ControladorProductoIndividual(Producto productoVista) {
        this.productoVista = productoVista;
        this.dao = new ProductosDAO();
        this.carrito = new Carrito();
        vista.getComboCategoría().addActionListener(this);
        //agregarEventos();
       
    }




private void agregarEventos() {
        productoVista.getBtnAgregarCarrito().addActionListener(this);

}

@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productoVista.getBtnAgregarCarrito()) {
            
            JOptionPane.showMessageDialog(null, "pruebabtn");
            agregarAlCarrito();

        } else if (e.getSource() == productoVista.getBtnAgregarCarrito()) {
            

        }
    }

    public void agregarAlCarrito() {
        
        int fila = productoVista.getTablaProductos().getSelectedRow();

        if (fila >= 0) {
            try {
                DefaultTableModel modelo = (DefaultTableModel) productoVista.getTablaProductos().getModel();

                int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());

                int stockBD = dao.obtenerStockPorId(id);

                if (stockBD <= 0) {
                    JOptionPane.showMessageDialog(productoVista, "No hay stock disponible para este producto.");
                    return;
                }

                String nombre = modelo.getValueAt(fila, 1).toString();
                String descripcion = modelo.getValueAt(fila, 2).toString();
                int precio = Integer.parseInt(modelo.getValueAt(fila, 3).toString());
                String categoria = modelo.getValueAt(fila, 5).toString();
                String proveedor = modelo.getValueAt(fila, 6).toString();

                Productos producto = new Productos(id, nombre, descripcion, precio, stockBD, categoria, proveedor);
                carrito.agregarProducto(producto);

                JOptionPane.showMessageDialog(productoVista, "Producto agregado al carrito.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(productoVista, "Error al agregar al carrito: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(productoVista, "Seleccione un producto para agregar al carrito.");
        }
    }

    /*
    public void mostrarCarrito() {
        VistaCarrito vistaCarrito = new VistaCarrito();
        DefaultTableModel modelo = (DefaultTableModel) vistaCarrito.getTablaCarrito().getModel();
        modelo.setRowCount(0);

        for (Productos p : carrito.getProductos()) {
            modelo.addRow(new Object[]{
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getCategoria()
            });
        }

        vistaCarrito.getBtnCerrar().addActionListener(e -> vistaCarrito.dispose());
        vistaCarrito.setVisible(true);

    }*/
    
    }


