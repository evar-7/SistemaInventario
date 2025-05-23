/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista.Admin;

import Controladores.Admin.ControladorPedidoAdmin;
import Controladores.Admin.ControladorProductoAdmin;
//import Controladores.ControladorProductos;
import Controladores.Admin.ControladorUsuarioAdmin;
import Modelo.Pedido;
import Modelo.Productos;
import Modelo.Usuario;
import Vista.VistaProductos;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author jim
 */
public class MenuAdmin extends JFrame {

    /**
     * Creates new form MenuAdmin
     */
    public MenuAdmin() {
        this.setLocationRelativeTo(this);
        setTitle("Menú de Administración");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(new Color(230, 233, 240));

        JLabel titulo = new JLabel("Menú de Administración", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contenedor.add(titulo, BorderLayout.NORTH);

        JPanel panelMenu = new JPanel(new GridLayout(2, 2, 20, 20));
        panelMenu.setBackground(new Color(230, 233, 240));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        ImageIcon iconoUsuarios = escalarIcono("/imagenes/ic1.png", 64, 64);
        ImageIcon iconoProductos = escalarIcono("/imagenes/ic3.png", 64, 64);
        ImageIcon iconoPedidos = escalarIcono("/imagenes/ic4.png", 64, 64);
        ImageIcon iconoReportes = escalarIcono("/imagenes/ic2.png", 64, 64);

        JButton btnUsuarios = new JButton("Gestionar Usuarios", iconoUsuarios);
        JButton btnProductos = new JButton("Gestionar Productos", iconoProductos);
        JButton btnPedidos = new JButton("Ver Pedidos", iconoPedidos);
        JButton btnReportes = new JButton("Reportes", iconoReportes);

        JButton[] botones = {btnUsuarios, btnProductos, btnPedidos, btnReportes};
        for (JButton btn : botones) {
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panelMenu.add(btn);
        }

        contenedor.add(panelMenu, BorderLayout.CENTER);
        setContentPane(contenedor);

        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VistaProductoAdmin vista = new VistaProductoAdmin();
                Productos modelo = new Productos();
                ControladorProductoAdmin controlador = new ControladorProductoAdmin(modelo, vista);
                vista.setVisible(true);
            }
        });

        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VistaUsuarioAdmin vista = new VistaUsuarioAdmin();
                Usuario modelo = new Usuario();
                ControladorUsuarioAdmin controlador = new ControladorUsuarioAdmin(modelo, vista);
                vista.setVisible(true);
            }
        });
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VistaPedidoAdmin vista = new VistaPedidoAdmin();
                Pedido modelo = new Pedido();
                ControladorPedidoAdmin controlador = new ControladorPedidoAdmin(modelo, vista);
                vista.setVisible(true);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReportes vista = new MenuReportes();;
                vista.setVisible(true);
            }
        });

    }

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(ruta));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuAdmin().setVisible(true);
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Menú de administración");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenu.setForeground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
