package Modelo;

/**
 *
 * @author Erick
 */

import Modelo.Productos;
import java.sql.*;
import java.util.*;

public class ProductosDAO {
    
    private final String URL = "jdbc:mysql://localhost:3306/tiendavirtual";
    private final String USER = "root";
    private final String PASS = "12345";
    
    public List<Productos> obtenerTodos() {
        List<Productos> producto = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Productos a = new Productos();
                a.setId(rs.getInt("id_producto"));
                a.setNombre(rs.getString("nombre"));
                a.setDescripcion(rs.getString("descripcion"));
                a.setPrecio(rs.getInt("precio"));
                a.setStockDisponible(rs.getInt("stock"));
                a.setCategoria(rs.getString("categoria"));
                a.setProveedor(rs.getString("proveedor"));
                producto.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }
    
     public List<Productos> obtenerTodosFiltrados(int x) {
        List<Productos> producto = new ArrayList<>();
        String y;
         switch (x) {
             case 1:
                 y = "SELECT * FROM producto where categoria='Cocina'";
                 break;
             case 2:
                 y = "SELECT * FROM producto where categoria='Limpieza'";
                 break;
             case 3:
                 y = "SELECT * FROM producto where categoria='Entretenimiento'";
                 break;
             case 4:
                 y = "SELECT * FROM producto where categoria='Muebles'";
                 break;
             case 5:
                 y = "SELECT * FROM producto";
                 break;
             default:
                 throw new AssertionError("Ingrese un valor");
         }


        

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(y)) {
            while (rs.next()) {
                Productos a = new Productos();
                a.setId(rs.getInt("id_producto"));
                a.setNombre(rs.getString("nombre"));
                a.setDescripcion(rs.getString("descripcion"));
                a.setPrecio(rs.getInt("precio"));
                a.setStockDisponible(rs.getInt("stock"));
                a.setCategoria(rs.getString("categoria"));
                a.setProveedor(rs.getString("proveedor"));
                producto.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }
    
    
    
    public void insertar(Productos producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, categoria, proveedor) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, (int) producto.getPrecio());
            ps.setInt(4, (int) producto.getStockDisponible());
            ps.setString(5, producto.getCategoria());
            ps.setString(6, producto.getProveedor());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizar(Productos producto, int id) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, categoria=?, proveedor=? WHERE id_producto=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3,(int) producto.getPrecio());
            ps.setInt(4,(int) producto.getStockDisponible());
            ps.setString(5, producto.getCategoria());
            ps.setString(6, producto.getProveedor());
            ps.setInt(7, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarPorId(Productos producto, int id) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, categoria=?, proveedor=? WHERE id_producto=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3,(int) producto.getPrecio());
            ps.setInt(4,(int) producto.getStockDisponible());
            ps.setString(5, producto.getCategoria());
            ps.setString(6, producto.getProveedor());
            ps.setInt(7, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
