package DataAccess;

import domain.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class UsuarioDA {

	public UsuarioDA() {
	}

	public boolean leerUsuario(Usuario usu) {

		Connection con = DBManager.openDBConnection();
		String usuario = usu.getNombreUsuario();
		String clave = usu.getClave();
		// creando mi query de verificacion
		String SQLquery = "SELECT NOMBRE_USUARIO, CLAVE FROM USUARIO WHERE NOMBRE_USUARIO='"
				+ usuario + "' And CLAVE='" + clave + "'";
		try {

			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(SQLquery);

			// verificando si el usuario existe
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		}
		// Manejo de excepcion
		catch (Exception err) {
			System.out.println("Error: " + err.getMessage());
			return false;
		}

	}
}