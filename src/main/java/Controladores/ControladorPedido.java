package Controladores;

import Modelo.Pedido;
import Modelo.PedidoDAO;

public class ControladorPedido {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    // Modificamos el método crearPedido para recibir el id_producto
    public boolean crearPedido(int id_usuario, int id_producto) {
        Pedido nuevoPedido = new Pedido(0, id_usuario, new java.util.Date(), "pendiente", id_producto);
        return pedidoDAO.crearPedido(nuevoPedido);
    }

    public boolean actualizarEstado(int idPedido, String nuevoEstado) {
        return pedidoDAO.actualizarEstado(idPedido, nuevoEstado);
    }
}