package DataAccess;

import domain.Usuario;
import java.sql.ResultSet;


public class UsuarioDA {

	public UsuarioDA() {
	}

	public void leerUsuario(Usuario usu) {

		try{
		
		     ResultSet result;								
                     Object[] parametros;			
			
		     String Select = "SELECT id_usuario, nombre, r.descripcion rol "+
                                     "FROM usuario u, rol r " + 
                                     "WHERE u.id_rol = r.id_rol AND nombre_usuario= ? And CLAVE= ?";
						
	             parametros = new Object[]{ usu.getNombreUsuario(), usu.getClave() };
				
		     DBManager.openDBConnection();
                     result = DBManager.ejecutarQuery(Select, parametros);
		
	 	   if(result.next()){				
			 usu.setID(result.getInt("id_usuario"));
                         usu.setNombre(result.getString("nombre"));
                         usu.setRol(result.getString("rol"));
	           }
		
		// Manejo de excepcion
        // TODo: Que jairis la maneje bien
		
		}catch (Exception err) {
			System.out.println("Error: " + err.getMessage());
		}
	}
}
