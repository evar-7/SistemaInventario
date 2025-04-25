package Controladores;

import Modelo.Carrito;
import Modelo.Productos;
import Modelo.ProductosDAO;
import Vista.Producto;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorProductoIndividual implements ActionListener {

    private Producto productoVista;
    private ProductosDAO dao;
    private Carrito carrito;

    public ControladorProductoIndividual(Producto productoVista) {
        this.productoVista = productoVista;
        this.dao = new ProductosDAO();
        this.carrito = new Carrito();
        agregarEventos();
    }

    private void agregarEventos() {
        productoVista.getBtnAgregarCarrito().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productoVista.getBtnAgregarCarrito()) {
            agregarAlCarritoDesdeDetalle();
        }
    }

    public void agregarAlCarritoDesdeDetalle() {
        JOptionPane.showMessageDialog(null, "Prueba");
        String nombre = productoVista.getjLabel4().getText().substring("Nombre Producto: ".length());
        String descripcion = productoVista.getjLabel3().getText().substring("Descripción: ".length());
        String precioTexto = productoVista.getjLabel5().getText().substring("Precio: ₡".length());
        String stockTexto = productoVista.getjLabel1().getText().substring("Stock Disponible: ".length());

        try {
            int precio = Integer.parseInt(precioTexto);
            int stockDisponible = Integer.parseInt(stockTexto);

            // Necesitas obtener el ID del producto de alguna manera. Podrías pasarlo al formulario Producto
            // o recuperarlo de la base de datos basado en el nombre (si es único).
            // Por ahora, asumimos que tienes una forma de obtener el ID.
            // int idProducto = ... ;

            // Simulamos obtener el ID (deberías tener tu propia lógica)
            // Esto es solo un ejemplo, ¡no uses esto en producción si el nombre no es único!
            Productos productoBD = dao.obtenerProductoPorNombre(nombre);
            if (productoBD != null) {
                int idProducto = productoBD.getId();
                Productos productoParaCarrito = new Productos(idProducto, nombre, descripcion, precio, 1, productoBD.getCategoria(), productoBD.getProveedor()); // Stock en 1 por ahora

                if (stockDisponible > 0) {
                    carrito.agregarProducto(productoParaCarrito);
                    JOptionPane.showMessageDialog(productoVista, nombre + " agregado al carrito.");
                    // Opcional: Podrías decrementar el stock en la base de datos aquí
                } else {
                    JOptionPane.showMessageDialog(productoVista, "No hay stock disponible para " + nombre + ".");
                }
            } else {
                JOptionPane.showMessageDialog(productoVista, "No se pudo encontrar el producto para agregar al carrito.");
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(productoVista, "Error al procesar el precio o el stock.");
        }
    }

    /*
    public void mostrarCarrito() {
        VistaCarrito vistaCarrito = new VistaCarrito();
        DefaultTableModel modelo = (DefaultTableModel) vistaCarrito.getTablaCarrito().getModel();
        modelo.setRowCount(0);

        for (Productos p : carrito.getProductos()) {
            modelo.addRow(new Object[]{
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getCategoria()
            });
        }

        vistaCarrito.getBtnCerrar().addActionListener(e -> vistaCarrito.dispose());
        vistaCarrito.setVisible(true);

    }*/
    
    }


