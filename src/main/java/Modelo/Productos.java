
package Modelo;

/**
 *
 * @author Erick
 */
public class Productos {
    
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int stockDisponible;
    private String categoria;
    private String proveedor;
    
    public Productos() {
    }

    public Productos(int id, String nombre, String descripcion, int precio, int stockDisponible, String categoria, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
