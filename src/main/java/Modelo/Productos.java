
package Modelo;


public class Productos {
    
    String nombre, descripción, categoría, proveedor;    
    int stock;
    float precio;

    public Productos() {
    }

    public Productos(String nombre, String descripción, String categoría, String proveedor, int stock, float precio) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.categoría = categoría;
        this.proveedor = proveedor;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getCategoría() {
        return categoría;
    }

    public void setCategoría(String categoría) {
        this.categoría = categoría;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    


}
