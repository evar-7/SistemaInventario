/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelo.Pedido;
import Modelo.PedidoDAO;

/**
 *
 * @Erick
 *  private int id_pedido;
    private int id_usuario;
    private java.util.Date fecha_pedido;
    private String estado;
    private double total;
 */
public class ControladorPedido {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public boolean crearPedido(int id_usuario) {
        Pedido nuevoPedido = new Pedido(0, id_usuario, new java.util.Date(), "pendiente");
        return pedidoDAO.crearPedido(nuevoPedido);
    }

    public boolean actualizarEstado(int idPedido, String nuevoEstado) {
        return pedidoDAO.actualizarEstado(idPedido, nuevoEstado);
    }
}
