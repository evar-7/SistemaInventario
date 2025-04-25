package Controladores;

import Modelo.Carrito;
import Modelo.Productos;
import Modelo.ProductosDAO;
import Vista.Inicio;
import Vista.Producto;
import Vista.VistaCarrito;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class ControladorProductoIndividual implements ActionListener {

    private Producto productoVista;
    private ProductosDAO dao;
    private Carrito carrito;
    private Inicio inicioVista;

    public ControladorProductoIndividual(Producto productoVista, Inicio inicioVista) {
        this.productoVista = productoVista;
        this.dao = new ProductosDAO();
        this.carrito = new Carrito();
        agregarEventos();
        
        inicioVista.getBtnVerCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Carrito antes de pasar: " + carrito);

                VistaCarrito vistaCarrito = new VistaCarrito();
                ControladorCarrito controladorCarrito = new ControladorCarrito(vistaCarrito, carrito);
                vistaCarrito.setVisible(true);
            }
        });
    }

    private void agregarEventos() {
        productoVista.getBtnAgregarCarrito().addActionListener(this);
        productoVista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productoVista.getBtnAgregarCarrito()) {
            agregarAlCarritoDesdeDetalle();
        }else if (e.getSource() == productoVista.getBtnVolver()){
            Vista.Inicio inicio = new Vista.Inicio();
            ControladorProductos CP = new ControladorProductos(inicio);
            inicio.setVisible(true);
            productoVista.dispose();
        }
    }

    public void agregarAlCarritoDesdeDetalle() {
        
        String nombre = productoVista.getjLabel4().getText().substring("Nombre Producto: ".length());
        String descripcion = productoVista.getjLabel3().getText().substring("Descripción: ".length());
        String precioTexto = productoVista.getjLabel5().getText().substring("Precio: ₡".length());
        String stockTexto = productoVista.getjLabel1().getText().substring("Stock Disponible: ".length());

        try {
            int precio = Integer.parseInt(precioTexto);
            int stockDisponible = Integer.parseInt(stockTexto);

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
}


