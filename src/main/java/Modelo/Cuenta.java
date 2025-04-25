package Modelo;



public class Cuenta {
    private int id_cuenta;
    private int id_orden; 
    private Usuario usuario; 
    private String metodo_pago; 
    private double monto;
    private String estado_pago; 

    
    public Cuenta() {
    }

    
    public Cuenta(int id_orden, Usuario usuario, String metodo_pago, double monto, String estado_pago) {
        this.id_orden = id_orden;
        this.usuario = usuario;
        this.metodo_pago = metodo_pago;
        this.monto = monto;
        this.estado_pago = estado_pago;
    }

   
    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }
}