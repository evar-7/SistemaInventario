/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.Admin;

import Modelo.Usuario;
import Vista.Admin.VistaUsuarioAdmin;
import Datos.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jim
 */
public class ControladorUsuarioAdmin {
    private Usuario modelo;
    private VistaUsuarioAdmin vista;
    private Conexion conectar = new Conexion();

    public ControladorUsuarioAdmin(Usuario modelo, VistaUsuarioAdmin vista) {
        this.modelo = modelo;
        this.vista = vista;

        mostrarUsuarios();

        this.vista.btn_agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
                mostrarUsuarios();
            }
        });

        this.vista.btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarUsuario();
                mostrarUsuarios();
            }
        });

        this.vista.btn_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
                mostrarUsuarios();
            }
        });

        this.vista.tabla_usuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = vista.tabla_usuarios.getSelectedRow();
                vista.txt_id.setText(vista.tabla_usuarios.getValueAt(fila, 0).toString());
                vista.txt_nombre.setText(vista.tabla_usuarios.getValueAt(fila, 1).toString());
                vista.txt_apellido.setText(vista.tabla_usuarios.getValueAt(fila, 2).toString());
                vista.txt_correo.setText(vista.tabla_usuarios.getValueAt(fila, 3).toString());
                vista.txt_usuario.setText(vista.tabla_usuarios.getValueAt(fila, 4).toString());
                vista.txt_tipo.setText(vista.tabla_usuarios.getValueAt(fila, 6).toString());
            }
        });
    }

    public void mostrarUsuarios() {
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Id");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Correo");
        tabla.addColumn("Usuario");
        tabla.addColumn("Contraseña");
        tabla.addColumn("Tipo");

        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement stmt = conectar.Conectar().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("correo"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasenna"),
                    rs.getString("tipo_usuario")
                };
                tabla.addRow(fila);
            }

            vista.tabla_usuarios.setModel(tabla);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarUsuario() {
        try {
            String sql = "INSERT INTO Usuario (nombre, apellidos, correo, nombre_usuario, contrasenna, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conectar.Conectar().prepareStatement(sql);
            stmt.setString(1, vista.txt_nombre.getText());
            stmt.setString(2, vista.txt_apellido.getText());
            stmt.setString(3, vista.txt_correo.getText());
            stmt.setString(4, vista.txt_usuario.getText());
            stmt.setString(5, vista.txt_contrasenna.getText());
            stmt.setString(6, vista.txt_tipo.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario agregado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
        }
    }

    public void modificarUsuario() {
        try {
            String sql = "UPDATE Usuario SET nombre=?, apellidos=?, correo=?, nombre_usuario=?, contrasenna=?, tipo_usuario=? WHERE id_usuario=?";
            PreparedStatement stmt = conectar.Conectar().prepareStatement(sql);
            stmt.setString(1, vista.txt_nombre.getText());
            stmt.setString(2, vista.txt_apellido.getText());
            stmt.setString(3, vista.txt_correo.getText());
            stmt.setString(4, vista.txt_usuario.getText());
            stmt.setString(5, vista.txt_contrasenna.getText());
            stmt.setString(6, vista.txt_tipo.getText());
            stmt.setInt(7, Integer.parseInt(vista.txt_id.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
        }
    }

    public void eliminarUsuario() {
        try {
            String sql = "DELETE FROM Usuario WHERE id_usuario=?";
            PreparedStatement stmt = conectar.Conectar().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(vista.txt_id.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }
}
