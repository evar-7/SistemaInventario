
package com.mycompany.sistemainventario;
import Controladores.ControladorLogin;
import Controladores.ControladorProductos;
import Vista.VistaLogin;
import Vista.VistaProductos;


public class SistemaInventario {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            VistaProductos vista = new VistaProductos();
            new ControladorProductos(vista);
            vista.setVisible(true);
        });
    }
}
