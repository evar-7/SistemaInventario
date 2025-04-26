package Controladores.Admin;

import Controladores.ControladorProductos;
import Vista.*;
import Modelo.Usuario;
import Datos.Conexion;
import Vista.Admin.MenuAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ControladorLogin {

    private Login vista;
    private Conexion conectar = new Conexion();

    public ControladorLogin(Login vista) {
        this.vista = vista;

        this.vista.btn_login.addActionListener(new ActionListener() {
            @Override
            
            
            
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    public void iniciarSesion() {
        String username = vista.txt_username.getText();
        String contrasenna = vista.txt_contrasenna.getText();

        if (username.isEmpty() || contrasenna.isEmpty() || username.equals("User") || contrasenna.equals("Password")){
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return;
        }

        String sql = "SELECT * FROM Usuario WHERE nombre_usuario = ? AND contrasenna = ?";

        try (PreparedStatement pstmt = conectar.Conectar().prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, contrasenna);
            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                String tipo = resultado.getString("tipo_usuario");
                String nombre = resultado.getString("nombre");
                JOptionPane.showMessageDialog(null, "Bienvenido, " + nombre);
                vista.dispose();
                if (tipo.equalsIgnoreCase("admin")) {
                    MenuAdmin menuAdmin = new MenuAdmin();
                    menuAdmin.setVisible(true);
                } else {
                    Vista.Inicio inicio = new Vista.Inicio();
                    ControladorProductos CP = new ControladorProductos(inicio);
                    inicio.setVisible(true);
                    vista.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
        }

    }

}