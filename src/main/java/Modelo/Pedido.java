/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @Erick
 */
public class Pedido {
    private int id_pedido;
    private int id_usuario;
    private java.util.Date fecha_pedido;
    private String estado;
    
    public Pedido () {
    }
    
    public Pedido(int id_pedido, int id_usuario, Date fecha_pedido, String estado) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.fecha_pedido = fecha_pedido;
        this.estado = estado;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
