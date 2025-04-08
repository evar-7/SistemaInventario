package Controladores;

import Modelo.Usuario;
import Vista.Registro;
import Datos.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorUsuario {

    private Usuario modelo;
    private Registro registro;
    private Conexion conectar = new Conexion();

    public ControladorUsuario(Usuario modelo, Registro vista) {
        this.modelo = modelo;
        this.registro = vista;

        this.registro.btn_registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    public void registrarUsuario() {
        System.out.println("metodo registrar");
        modelo = new Usuario();
        modelo.setNombre(registro.txt_nombre.getText());
        modelo.setApellido(registro.txt_apellido.getText());
        modelo.setCorreo(registro.txt_correo.getText());
        modelo.setNombre_usuario(registro.txt_username.getText());
        modelo.setContrasenna(registro.txt_contrasenna.getText());
        modelo.setTipo_usuario("cliente");

        String sql = "INSERT INTO Usuario (nombre, apellidos, correo, nombre_usuario, contrasenna, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conectar.Conectar().prepareStatement(sql)) {
            pstmt.setString(1, modelo.getNombre());
            pstmt.setString(2, modelo.getApellido());
            pstmt.setString(3, modelo.getCorreo());
            pstmt.setString(4, modelo.getNombre_usuario());
            pstmt.setString(5, modelo.getContrasenna());
            pstmt.setString(6, modelo.getTipo_usuario());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario registrado");
            registro.dispose();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }

}


