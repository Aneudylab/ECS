
package DataAccess;

import domain.Usuario;
import domain.Rol;
import java.sql.ResultSet;


public class UsuarioDA {

    public UsuarioDA() {
    }

    public void leerUsuario(Usuario usu) {

        try{
            Rol rol;
            ResultSet result;								
            Object[] parametros;			

            String Select = "SELECT id_usuario id,nombre,id_supervisor supervisor,rol.id_rol,descripcion rol " +
                            "FROM usuario INNER JOIN rol ON usuario.id_rol = rol.id_rol " + 
                            "WHERE nombre_usuario = ? And clave= ?";

            parametros = new Object[]{ usu.getNombreUsuario(), usu.getClave() };

            DBManager.openDBConnection();
            result = DBManager.ejecutarQuery(Select, parametros);

            if(result.next()){			
       			rol = new Rol(result.getInt("id_rol"),result.getString("rol"));
                usu.setID(result.getInt("id"));
                usu.setNombre(result.getString("nombre"));
                usu.setRol(rol);
            }

            // Manejo de excepcion
            // TODo: Que jairis la maneje bien

        }catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
