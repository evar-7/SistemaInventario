package Modelo;

public class Pago {
    private int idPago;
    private int idPedido;
    private int monto;
    private String metodoPago;
    private String estadoPago;

    public Pago() {}

    public Pago(int idPago, int idPedido, int monto, String metodoPago, String estadoPago) {
        this.idPago = idPago;
        this.idPedido = idPedido;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
