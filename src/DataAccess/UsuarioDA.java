package DataAccess;

import domain.Usuario;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class UsuarioDA {

	public UsuarioDA() {
	}

	public void leerUsuario(Usuario usu) {

		Connection con = DBManager.openDBConnection();
		String usuario = usu.getNombreUsuario();
		String clave = usu.getClave();
		
		// creando mi query de verificacion
		
        String SQLquery = "SELECT id_usuario, nombre, r.descripcion rol "+
                          " FROM usuario u, rol r " + 
                          "WHERE u.id_rol = r.id_rol AND nombre_usuario= ? And CLAVE= ?";
				
		try {

			PreparedStatement stmt = con.prepareStatement(SQLquery);
			int i = 0;
			stmt.setString(++i,usuario);
			stmt.setString(++i,clave);
			ResultSet result = stmt.executeQuery();

		// verificando si el usuario existe, Si no el id se mantienen en 0
			
			if (result.next()) {
                usu.setID(result.getInt("id_usuario"));
                usu.setNombre(result.getString("nombre"));
                usu.setRol(result.getString("rol"));
			}
		}
		
		// Manejo de excepcion
        // TODo: Que jairis la maneje bien
		
		catch (Exception err) {
			System.out.println("Error: " + err.getMessage());
		}
	}
}
