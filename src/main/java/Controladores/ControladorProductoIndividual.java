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
            agregarAlCarrito();

        } else if (e.getSource() == productoVista.getBtnAgregarCarrito()) {
            

        }
    }

    public void agregarAlCarrito() {

        //Valida de q hay stock
        
        int columna = vista.getTablaProductos().getSelectedColumn();
        int fila = vista.getTablaProductos().getSelectedRow();
        int x = Integer.parseInt(vista.getTablaProductos().getValueAt(fila, columna).toString());

        if (x <= 0) {
            JOptionPane.showMessageDialog(null, "No hay stock disponible");
            return;

            //Agrega carrito
            
        } else {

            if (fila >= 0) {
                try {
                    DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();

                    int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    String nombre = modelo.getValueAt(fila, 1).toString();
                    String descripcion = modelo.getValueAt(fila, 2).toString();
                    int precio = Integer.parseInt(modelo.getValueAt(fila, 3).toString());
                    int stock = Integer.parseInt(modelo.getValueAt(fila, 4).toString());
                    String categoria = modelo.getValueAt(fila, 5).toString();
                    String proveedor = modelo.getValueAt(fila, 6).toString();

                    Productos producto = new Productos(id, nombre, descripcion, precio, stock, categoria, proveedor);
                    carrito.agregarProducto(producto);

                    JOptionPane.showMessageDialog(vista, "Producto agregado al carrito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al agregar al carrito: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un producto para agregar al carrito.");
            }
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


