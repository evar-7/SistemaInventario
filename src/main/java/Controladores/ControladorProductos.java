
/**
 *
 * @author Erick
 */

package Controladores;

import Modelo.Productos;
import Modelo.ProductosDAO;
import Vista.VistaProductos;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import Modelo.Productos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProductos implements ActionListener {

    private VistaProductos vista;
    private ProductosDAO dao;

    public ControladorProductos(VistaProductos vista) {
        this.vista = vista;
        this.dao = new ProductosDAO();
        agregarEventos();
        cargarTabla();
    }

    private void agregarEventos() {
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregar()) {
            insertar();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            actualizar();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminar();
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
                int id = Integer.parseInt(vista.getTablaProductos().getValueAt(fila, 0).toString());

                String nombre = vista.getTablaProductos().getValueAt(fila, 1).toString();
                String descripcion = vista.getTablaProductos().getValueAt(fila, 2).toString();
                int precio = Integer.parseInt(vista.getTablaProductos().getValueAt(fila, 3).toString());
                int stock = Integer.parseInt(vista.getTablaProductos().getValueAt(fila, 4).toString());
                String categoria = vista.getTablaProductos().getValueAt(fila, 5).toString();
                String proveedor = vista.getTablaProductos().getValueAt(fila, 6).toString();

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
                int id = Integer.parseInt(vista.getTablaProductos().getValueAt(fila, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(vista, "Producto eliminado.");
                cargarTabla();
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
}