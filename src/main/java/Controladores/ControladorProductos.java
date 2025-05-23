package Controladores;

import Modelo.Carrito;
import Modelo.ProductosDAO;
import Vista.VistaProductos;
import Modelo.Productos;
import Modelo.ItemCarrito;
import Vista.Inicio;
import Vista.Producto;
import Vista.VistaCarrito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProductos implements ActionListener {

    private Inicio vista;
    private ProductosDAO dao;
    private Producto productoVista;

    public ControladorProductos(Inicio vista) {
        this.vista = vista;
        this.dao = new ProductosDAO();
        vista.getComboCategoría().addActionListener(this);
        agregarEventos();
        cargarTabla();
    }

    public ControladorProductos(Producto vista) {
        this.productoVista = vista;
    }

    private void agregarEventos() {
        vista.getBtnVerProducto().addActionListener(this);
        vista.getBtnVerCarrito().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getComboCategoría()) {
            cargarTabla();
        } else if (e.getSource() == vista.getBtnVerCarrito()) {
            mostrarCarrito();
        }
    }

    public void insertar() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String descripcion = JOptionPane.showInputDialog("Descripción:");
            int precio = Integer.parseInt(JOptionPane.showInputDialog("Precio:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock disponible:"));
            String categoria = JOptionPane.showInputDialog("Categoría:");
            String proveedor = JOptionPane.showInputDialog("Proveedor:");

            Productos producto = new Productos(0, nombre, descripcion, precio, stock, categoria, proveedor);
            dao.insertar(producto);
            JOptionPane.showMessageDialog(vista, "Producto agregado.");
            cargarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al agregar: " + ex.getMessage());
        }
    }

    public void eliminar() {
        int fila = vista.getTablaProductos().getSelectedRow();
        if (fila >= 0) {
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();
                    int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    dao.eliminar(id);
                    JOptionPane.showMessageDialog(vista, "Producto eliminado.");
                    cargarTabla();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para eliminar.");
        }
    }

    public void cargarTabla() {
        int x = 5;
        String categoria = vista.getComboCategoría().getSelectedItem().toString();
        if (categoria.equals("Cocina")) {
            x = 1;
        } else if (categoria.equals("Limpieza")) {
            x = 2;
        } else if (categoria.equals("Entretenimiento")) {
            x = 3;
        } else if (categoria.equals("Muebles")) {
            x = 4;
        } else if (categoria.equals("Todas")) {
            x = 5;
        }
        List<Productos> lista = dao.obtenerTodosFiltrados(x);
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();
        modelo.setRowCount(0);

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
    }

    public void mostrarCarrito() {
        VistaCarrito vistaCarrito = new VistaCarrito();
        DefaultTableModel modelo = (DefaultTableModel) vistaCarrito.getTablaCarrito().getModel();
        modelo.setRowCount(0);

        for (ItemCarrito item : Carrito.getProductos()) {
            modelo.addRow(new Object[]{
                item.getNombreProducto(),
                "-", // descripción opcional si la deseas
                item.getPrecioUnitario(),
                item.getCantidad()
            });
        }

        vistaCarrito.getBtnCerrar().addActionListener(e -> vistaCarrito.dispose());
        vistaCarrito.setVisible(true);
    }
}
