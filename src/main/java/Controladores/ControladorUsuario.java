package Controladores;

import Modelo.Usuario;
import Vista.Registro;
import Datos.Conexion;
import Vista.Login;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ControladorUsuario {

    private Usuario modelo;
    private Registro registro;
    private Conexion conectar = new Conexion();

    public ControladorUsuario(Usuario modelo, Registro vista) {
        this.modelo = modelo;
        this.registro = vista;

        this.registro.btn_registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        if (modelo.getNombre().isEmpty() || modelo.getApellido().isEmpty() || modelo.getCorreo().isEmpty() || modelo.getNombre_usuario().isEmpty() || modelo.getContrasenna().isEmpty() || registro.txt_confcontrasenna.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos, incluyendo la confirmación de contraseña");
            return;
        }

        if (!modelo.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un correo valido");
            return;
        }

        if (!validacionNombreUsuario(modelo.getNombre_usuario())) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya esta en uso");
            return;
        }

        if (modelo.getContrasenna().length() < 12) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener 12 caracteres como minimo");
            return;
        }

        // **VALIDACIÓN DE CONFIRMACIÓN DE CONTRASEÑA AQUÍ**
        if (!registro.txt_contrasenna.getText().equals(registro.txt_confcontrasenna.getText())) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
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
            Login login = new Login();
            login.setVisible(true);
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