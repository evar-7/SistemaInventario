
package Modelo;

import Datos.Conexion;
import Modelo.Pedido;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Erick
 *  private int id_pedido;
    private int id_usuario;
    private java.util.Date fecha_pedido;
    private String estado;
    private double total;
 */
public class PedidoDAO {
    public boolean crearPedido(Pedido pedido) {
        String sql = "INSERT INTO pedido (id_usuario, fecha_pedido, estado) VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().Conectar(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId_usuario());
            stmt.setDate(2, new java.sql.Date(pedido.getFecha_pedido().getTime()));
            stmt.setString(3, pedido.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pedido> obtenerPedidosPorCliente(int id_Usuario) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE id_usuario=?";
        try (Connection conn = new Conexion().Conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_Usuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getInt("id_usuario"),
                        rs.getDate("fecha_pedido"),
                        rs.getString("estado")
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public boolean actualizarEstado(int idPedido, String nuevoEstado) {
        String sql = "UPDATE pedido SET estado=? WHERE id_pedido=?";
        try (Connection conn = new Conexion().Conectar(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idPedido);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
