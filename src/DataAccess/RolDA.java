package DataAccess;
import domain.Rol;
import java.util.ArrayList;
import java.sql.ResultSet;

public class RolDA {
    /**
     * Retorna la lista de roles en el sistema
     *  
	 * @return object
     **/
    public ArrayList<Rol> leerRoles() {
        ArrayList<Rol> roles = new ArrayList<Rol>();
        ResultSet result;
        Object[] parametros;

        String query = "SELECT id_rol, " +
                       "       descripcion " +
                       "  FROM rol ";

        parametros = new Object[]{};

        try{
            DBManager.openDBConnection();
            result = DBManager.ejecutarQuery(query, parametros);

            while(result.next()){			                
                int id = result.getInt("id_rol"); 
                String descripcion = result.getString("descripcion");

                Rol rol = new Rol(id, descripcion);
                roles.add(rol);
            }
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return roles;
    }
}
