package Vista.Admin;

import javax.swing.*;
import java.awt.*;

public class MenuReportes extends JFrame {

    private JLabel jLabel1;
    private JPanel panelMenu;

    public MenuReportes() {
        setTitle("Menú de Reportes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(new Color(230, 233, 240));

        jLabel1 = new JLabel("Menú de Reportes", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        jLabel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contenedor.add(jLabel1, BorderLayout.NORTH);

        panelMenu = new JPanel(new GridLayout(2, 2, 20, 20));
        panelMenu.setBackground(new Color(230, 233, 240));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        ImageIcon iconoVentas = escalarIcono("/imagenes/profit.png", 64, 64);
        ImageIcon iconoInventario = escalarIcono("/imagenes/inventory.png", 64, 64);
        ImageIcon iconoPedidos = escalarIcono("/imagenes/ic4.png", 64, 64);

        JButton btnVentas = new JButton("Reporte de Ventas", iconoVentas);
        JButton btnInventario = new JButton("Reporte de Inventario", iconoInventario);
        JButton btnPedidos = new JButton("Reporte de Pedidos", iconoPedidos);

        JButton[] botones = {btnVentas, btnInventario, btnPedidos};
        for (JButton btn : botones) {
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panelMenu.add(btn);
        }

        panelMenu.add(new JLabel());
        contenedor.add(panelMenu, BorderLayout.CENTER);
        setContentPane(contenedor);

        btnVentas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir reporte de ventas..."));
        btnInventario.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir reporte de inventario..."));
        btnPedidos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir reporte de pedidos..."));
    }

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        java.net.URL location = getClass().getResource(ruta);
        if (location == null) {
            System.err.println("No se encontró la imagen: " + ruta);
            return new ImageIcon();
        }
        ImageIcon iconoOriginal = new ImageIcon(location);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuReportes().setVisible(true);
        });
    }
}
