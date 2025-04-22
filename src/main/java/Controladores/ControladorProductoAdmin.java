/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelo.Productos;
import Modelo.ProductosDAO;
import Vista.VistaProductoAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jim
 */
public class ControladorProductoAdmin implements ActionListener {

    private Productos modelo;
    private VistaProductoAdmin vista;
    private ProductosDAO dao;

    public ControladorProductoAdmin(Productos modelo, VistaProductoAdmin vista) {
        this.vista = vista;
        this.dao = new ProductosDAO();
        agregarEventos();
        cargarTabla();
    }

    private void agregarEventos() {
        vista.jButton1.addActionListener(this);
        vista.btn_modificar.addActionListener(this);
        vista.btn_eliminar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton1) {
            insertar();
        } else if (e.getSource() == vista.btn_modificar) {
            modificar();
        } else if (e.getSource() == vista.btn_eliminar) {
            eliminar();
        }
    }

    private void insertar() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        int precio = Integer.parseInt(JOptionPane.showInputDialog("Precio:"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));
        String categoria = JOptionPane.showInputDialog("Categoría:");
        String proveedor = JOptionPane.showInputDialog("Proveedor:");

        Productos producto = new Productos(0, nombre, descripcion, precio, stock, categoria, proveedor);
        dao.insertar(producto);
        JOptionPane.showMessageDialog(vista, "Producto agregado.");
        cargarTabla();
    }

    private void modificar() {
        int fila = vista.jTable1.getSelectedRow();
        if (fila >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) vista.jTable1.getModel();

            int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            String nombre = modelo.getValueAt(fila, 1).toString();
            String descripcion = modelo.getValueAt(fila, 2).toString();
            int precio = Integer.parseInt(modelo.getValueAt(fila, 3).toString());
            int stock = Integer.parseInt(modelo.getValueAt(fila, 4).toString());
            String categoria = modelo.getValueAt(fila, 5).toString();
            String proveedor = modelo.getValueAt(fila, 6).toString();

            Productos producto = new Productos(id, nombre, descripcion, precio, stock, categoria, proveedor);
            dao.actualizar(producto, id);
            JOptionPane.showMessageDialog(vista, "Producto modificado.");
            cargarTabla();
        }
    }

    private void eliminar() {
        int fila = vista.jTable1.getSelectedRow();
        if (fila >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) vista.jTable1.getModel();
            int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Eliminar producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                JOptionPane.showMessageDialog(vista, "Producto eliminado.");
                cargarTabla();
            }
        }
    }

    private void cargarTabla() {
        List<Productos> lista = dao.obtenerTodos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría", "Proveedor"});

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

        vista.jTable1.setModel(modelo);
    }
}
