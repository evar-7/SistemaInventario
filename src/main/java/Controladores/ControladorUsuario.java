package Controladores;

import Modelo.Usuario;
import Vista.Registro;
import Datos.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ControladorUsuario {

    private Usuario modelo;
    private Registro registro;
    private Conexion conectar = new Conexion();

    public ControladorUsuario(Usuario modelo, Registro vista) {
        //boton para registrarse
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
        modelo.setNombre_usuario(registro.txt_username.getText().trim().toLowerCase());
        modelo.setContrasenna(registro.txt_contrasenna.getText());
        modelo.setTipo_usuario("cliente");

        //validaciones del registro
        //revisar que todos los campos esten llenos
        if (modelo.getNombre().isEmpty() || modelo.getApellido().isEmpty() || modelo.getCorreo().isEmpty() || modelo.getNombre_usuario().isEmpty() || modelo.getContrasenna().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            return;
        }

        //validr el correo electronico
        if (!modelo.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un correo valido");
            return;
        }

        //validar que el nombre de usuario sea unico
        if (!validacionNombreUsuario(modelo.getNombre_usuario())) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya esta en uso");
            return;
        }

        if (modelo.getContrasenna().length() < 12) {
            JOptionPane.showInternalMessageDialog(null, "La contraseña debe tener 12 caracteres como minimo");
            return;
        }

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

    private boolean validacionNombreUsuario(String nombreusuario) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE LOWER(TRIM(nombre_usuario)) = ?";

        try (PreparedStatement stmt = conectar.Conectar().prepareStatement(sql)) {
            stmt.setString(1, nombreusuario.trim().toLowerCase());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
