/*
pack/age Controladores;

import Modelo.Cuenta;
import Modelo.Usuario; // Asegúrate de importar la clase Usuario si la necesitas directamente
// Importa la clase DAO que utilices para la persistencia (ejemplo: CuentaDAO)
import Modelo.CuentaDAO;

public class ControladorCuenta {

    private CuentaDAO cuentaDAO;

    public ControladorCuenta() {
        this.cuentaDAO = new CuentaDAO(); // Inicializa tu DAO
    }

    public void agregarCuenta(int idOrden, Usuario usuario, String metodoPago, double monto, String estadoPago) {
        // 1. Crear una nueva instancia de la clase Cuenta con los datos proporcionados
        Cuenta nuevaCuenta = new Cuenta(idOrden, usuario, metodoPago, monto, estadoPago);

        // 2. Llamar al método del DAO para guardar la nueva cuenta en la base de datos
        cuentaDAO.agregarCuenta(nuevaCuenta);

        // 3. (Opcional) Realizar alguna acción adicional después de agregar la cuenta,
        //    como mostrar un mensaje de éxito o actualizar la interfaz de usuario.
        System.out.println("Cuenta agregada exitosamente para la orden ID: " + idOrden);
    }

    // Otros métodos del controlador (listar, buscar, actualizar, eliminar cuentas, etc.) podrían ir aquí
}*/