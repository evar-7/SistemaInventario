

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

public class ControladorProductos implements ActionListener {

    private Inicio vista;
    private ProductosDAO dao;
    private Carrito carrito;
    private Producto productoVista;

    public ControladorProductos(Inicio vista) {
        this.vista = vista;
        this.dao = new ProductosDAO();
        this.carrito = new Carrito(); 
        //agregarEventos();
        cargarTabla();
    }
    public ControladorProductos(Producto vista) {
        this.productoVista = vista;
    }
    
/*private void agregarEventos() {
        vista.getBtnVerProducto().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnAgregarCarrito().addActionListener(this);
        vista.getBtnVerCarrito().addActionListener(e -> mostrarCarrito());
    }*/
    
    
    private void agregarEventos() {
        vista.getBtnVerProducto().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
    }
/*
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregar()) {
            insertar();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            actualizar();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminar();
        } else if (e.getSource() == vista.getBtnAgregarCarrito()) {
            agregarAlCarrito();
}*/

 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnVerProducto()) {
            insertar();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            actualizar();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            agregarAlCarrito();
}
    }

    public void insertar() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String descripcion = JOptionPane.showInputDialog("Descripción:");
            int precio = Integer.parseInt(JOptionPane.showInputDialog("Precio:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock disponible:"));
            String categoria = JOptionPane.showInputDialog("Categoría:");
            String proveedor = JOptionPane.showInputDialog("Proveedor:");

            Productos producto = new Productos(0, nombre, descripcion, precio, stock, categoria, proveedor);
            dao.insertar(producto);
            JOptionPane.showMessageDialog(vista, "Producto agregado.");
            cargarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al agregar: " + ex.getMessage());
        }
    }

    public void actualizar() {
        int fila = vista.getTablaProductos().getSelectedRow();
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
                dao.actualizar(producto, id);

                JOptionPane.showMessageDialog(vista, "Producto actualizado.");
                cargarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para actualizar.");
        }
    }

    public void eliminar() {
        int fila = vista.getTablaProductos().getSelectedRow();
        if (fila >= 0) {
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();
                    int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    dao.eliminar(id);
                    JOptionPane.showMessageDialog(vista, "Producto eliminado.");
                    cargarTabla();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para eliminar.");
        }
    }

    public void cargarTabla() {
        List<Productos> lista = dao.obtenerTodos();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();
        modelo.setRowCount(0);

        for (Productos p : lista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStockDisponible(),
                p.getCategoria(),
                p.getProveedor()
            });
        }
    }
    public void agregarAlCarrito() {
        int fila = vista.getTablaProductos().getSelectedRow();
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
    
        
        
}}