
package Main;

import Controladores.Admin.ControladorLogin;
import Controladores.Admin.ControladorUsuarioAdmin;
import Datos.Conexion;
import Modelo.Usuario;
import Vista.Admin.VistaUsuarioAdmin;
import Vista.VistaLogin;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        if (hayUsuariosRegistrados()) {
            VistaLogin vistaLogin = new VistaLogin();
            ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);
            vistaLogin.setVisible(true);
        } else {
            VistaUsuarioAdmin vistaRegistro = new VistaUsuarioAdmin();
            Usuario nuevoUsuario = new Usuario();
            ControladorUsuarioAdmin controladorRegistro = new ControladorUsuarioAdmin(nuevoUsuario, vistaRegistro);
            vistaRegistro.setVisible(true);
        }
    }

    public static boolean hayUsuariosRegistrados() {
        String sql = "SELECT COUNT(*) AS total FROM Usuario";
        try (Connection conn = new Conexion().Conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
