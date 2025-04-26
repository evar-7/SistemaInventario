package Modelo;

public class ItemCarrito {
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;

    public ItemCarrito(int idProducto, String nombreProducto, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
