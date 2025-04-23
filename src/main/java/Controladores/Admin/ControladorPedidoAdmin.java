/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.Admin;

import Datos.Conexion;
import Modelo.Pedido;
import Vista.Admin.VistaPedidoAdmin;
import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jim
 */
public class ControladorPedidoAdmin {

    private Pedido modelo;
    private VistaPedidoAdmin vista;
    private Conexion conectar = new Conexion();

    public ControladorPedidoAdmin(Pedido modelo, VistaPedidoAdmin vista) {
        this.modelo = modelo;
        this.vista = vista;

        mostrarPedidos();

        vista.btn_actualizar_estado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstadoPedido();
                mostrarPedidos();
            }
        });

        vista.tabla_pedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla_pedidos.getSelectedRow();

                if (fila >= 0) {
                    String idPedido = vista.tabla_pedidos.getValueAt(fila, 0).toString();
                    vista.txt_Id_pedido.setText(idPedido);
                    vista.id_cliente.setText(vista.tabla_pedidos.getValueAt(fila, 1).toString());
                    vista.txt_fecha.setText(vista.tabla_pedidos.getValueAt(fila, 2).toString());
                    vista.cb_estado.setSelectedItem(vista.tabla_pedidos.getValueAt(fila, 3).toString());

                    mostrarDetallePedido(Integer.parseInt(idPedido));
                }
            }
        });
    }

    public void mostrarPedidos() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Id");
        modelo.addColumn("Id Usuario");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        String sql = "SELECT * FROM Pedido";
        try (Connection con = conectar.Conectar(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            int contador = 0;
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_pedido"),
                    rs.getInt("id_usuario"),
                    rs.getString("fecha_pedido"),
                    rs.getString("estado")
                };
                modelo.addRow(fila);
            }
            vista.tabla_pedidos.setModel(modelo);
            vista.tabla_pedidos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    String estado = value.toString().toLowerCase();
                    if (!isSelected) {
                        switch (estado) {
                            case "pendiente":
                                c.setBackground(Color.decode("#FFF3CD"));
                                break;
                            case "enviado":
                                c.setBackground(Color.decode("#D1ECF1"));
                                break;
                            case "entregado":
                                c.setBackground(Color.decode("#D4EDDA"));
                                break;
                            case "cancelado":
                                c.setBackground(Color.decode("#F8D7DA"));
                                break;
                            default:
                                c.setBackground(Color.WHITE);
                                break;
                        }
                    } else {
                        switch (estado) {
                            case "pendiente":
                                c.setBackground(Color.decode("#FFF3CD"));
                                break;
                            case "enviado":
                                c.setBackground(Color.decode("#D1ECF1"));
                                break;
                            case "entregado":
                                c.setBackground(Color.decode("#D4EDDA"));
                                break;
                            case "cancelado":
                                c.setBackground(Color.decode("#F8D7DA"));
                                break;
                            default:
                                c.setBackground(Color.WHITE);
                                break;
                        }
                    }
                    c.setForeground(Color.BLACK);
                    return c;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarDetallePedido(int idPedido) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id Producto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");

        String sql = "SELECT dp.id_producto, p.nombre, dp.cantidad, p.precio "
                + "FROM Detalle_Pedido dp "
                + "JOIN Producto p ON dp.id_producto = p.id_producto "
                + "WHERE dp.id_pedido = ?";

        try (Connection con = conectar.Conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getInt("cantidad"),
                    rs.getInt("precio")
                };
                modelo.addRow(fila);
            }

            vista.tabla_detalles.setModel(modelo);
            vista.tabla_detalles.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, false, false, row, column);
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                    return c;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEstadoPedido() {
        try {
            String sql = "UPDATE Pedido SET estado=? WHERE id_pedido=?";
            PreparedStatement stmt = conectar.Conectar().prepareStatement(sql);
            stmt.setString(1, vista.cb_estado.getSelectedItem().toString());
            stmt.setInt(2, Integer.parseInt(vista.txt_Id_pedido.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado: " + e.getMessage());
        }
    }
}
