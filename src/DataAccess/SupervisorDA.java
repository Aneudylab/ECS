package DataAccess;
import domain.Supervisor;
import java.util.ArrayList;
import java.sql.ResultSet;

public class SupervisorDA {
    /**
     * Retorna la lista de supervisores en el sistema
     *  
	 * @return object
     **/
    public ArrayList<Supervisor> leerSupervisores() {
        ArrayList<Supervisor> sups = new ArrayList<Supervisor>();
        ResultSet result;
        Object[] parametros;

        String query =  "SELECT id_usuario, " +
                        "       nombre" +

                        "  FROM usuario" +
                        " WHERE id_rol = 2";

        parametros = new Object[]{};

        try{
            DBManager.openDBConnection();
            result = DBManager.ejecutarQuery(query, parametros);

            while(result.next()){			                
                int id = result.getInt("id_usuario"); 
                String nombre = result.getString("nombre");

                Supervisor sup = new Supervisor(id, nombre);
                sups.add(sup);
            }
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            return sups;
        } 
    }
}
