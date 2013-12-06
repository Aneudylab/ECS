
package DataAccess;

import domain.Usuario;
import domain.Rol;
import java.sql.ResultSet;


public class UsuarioDA {
    
	////////////////////////
	//  Costructor
	///////////////////////
	
    public UsuarioDA() {}

    public Usuario leerUsuario(String nombreUsr, String clave) {
        
		Usuario usr = null; //creando el usuario que sera retornado
							//incializandola como nula
		
        try{
            
            ResultSet result;	
            Object[] parametros;			

            String Select = "SELECT id_usuario id,nombre,id_supervisor supervisor,rol.id_rol,descripcion rol " +
                            "FROM usuario INNER JOIN rol ON usuario.id_rol = rol.id_rol " + 
                            "WHERE nombre_usuario = ? And clave= ?";

            parametros = new Object[]{ nombreUsr, clave };

            DBManager.openDBConnection();
            result = DBManager.ejecutarQuery(Select, parametros);

            if(result.next()){			
       			
				int idRol = result.getInt("id_rol"); //id del Rol
				String descRol = result.getString("rol"); //descripcion del Rol 
                
				int idUsuario = result.getInt("id"); //Id del usuario
                String nombre = result.getString("nombre"); //Nombre del usuario
                
				//construyendo los objetos Rol y Usuario
				Rol rol = new Rol(idRol,descRol); 
				usr = new Usuario(idUsuario,nombre,rol);
				
            }
            
            // Manejo de excepcion
            // TODo: Que jairis la maneje bien

        }catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
        }finally{
            return usr;
        }
    }

    /**
     * Guarda un usuario nuevo en la BD
	 * @param unUsr usage...
	 * @param idAdmin usage...
	 * @param idSup usage...
     *  
     **/
    public void guardarUsuario(Usuario unUsr, int idAdmin, int idSup) {
        Object[] parametros;

        String query = "INSERT INTO USUARIO " +
                       "    (NOMBRE, NOMBRE_USUARIO, CLAVE, ID_SUPERVISOR, ID_USUARIO_CREADOR, ID_ROL) " +
                       "VALUES " +
                       "    (?,?,?,?,?,?) ";
        
        parametros = new Object[]{
            unUsr.getNombre(),
            unUsr.getNombreUsuario(),
            unUsr.getClave(),
            unUsr.getIdRol() == 3? idSup : null,
            idAdmin,
            unUsr.getIdRol()
        };
        try{

            DBManager.openDBConnection();
            int id = DBManager.executeQueryGeneratedKeys(query, parametros);
            unUsr.setId(id);

        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
