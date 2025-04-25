/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.Admin;

import Datos.Conexion;
import Modelo.Productos;
import Modelo.ProductosDAO;
import Vista.Admin.VistaProductoAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private Conexion conectar = new Conexion();


    public ControladorProductoAdmin(Productos modelo, VistaProductoAdmin vista) {
        this.vista = vista;
        this.dao = new ProductosDAO();
        agregarEventos();
        cargarTabla();
    }

    private void agregarEventos() {
        vista.btn_agregar.addActionListener(this);
        vista.btn_modificar.addActionListener(this);
        vista.btn_eliminar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_agregar) {
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
        int fila = vista.tabla_productos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar.");
            return;
        }

        try {
            int id = Integer.parseInt(vista.tabla_productos.getValueAt(fila, 0).toString());
            String nombre = vista.tabla_productos.getValueAt(fila, 1).toString();
            String descripcion = vista.tabla_productos.getValueAt(fila, 2).toString();
            int precio = Integer.parseInt(vista.tabla_productos.getValueAt(fila, 3).toString());
            int stock = Integer.parseInt(vista.tabla_productos.getValueAt(fila, 4).toString());
            String categoria = vista.tabla_productos.getValueAt(fila, 5).toString();
            String proveedor = vista.tabla_productos.getValueAt(fila, 6).toString();

            String sql = "UPDATE Producto SET nombre=?, descripcion=?, precio=?, stock=?, categoria=?, proveedor=? WHERE id_producto=?";
            try (Connection con = conectar.Conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, descripcion);
                stmt.setInt(3, precio);
                stmt.setInt(4, stock);
                stmt.setString(5, categoria);
                stmt.setString(6, proveedor);
                stmt.setInt(7, id);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.getMessage());
        }
    }

    private void eliminar() {
        int fila = vista.tabla_productos.getSelectedRow();
        if (fila >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) vista.tabla_productos.getModel();
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

        vista.tabla_productos.setModel(modelo);
    }
}
