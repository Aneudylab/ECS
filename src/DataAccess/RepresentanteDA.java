
package DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import domain.Representante;


public class RepresentanteDA{
   
   public RepresentanteDA(){}
   
   public ArrayList<Representante> leerRepresentantes (int idSupervisor){
       
	    ResultSet result;
	    ArrayList<Representante> tmpListaRepr = null;
	    Object[] parametros;

        String query = "SELECT id_usuario id,nombre " + 
		               "FROM usuario " +
		               "WHERE id_supervisor=?";

        parametros = new Object[]{idSupervisor};
		
        try{
			tmpListaRepr = new ArrayList<Representante>();
			result = DBManager.ejecutarQuery(query, parametros);
			
            while(result.next()){			                
				int idUsuario = result.getInt("id"); //Id del usuario
                String nombre = result.getString("nombre"); //Nombre del usuario
                
				tmpListaRepr.add(new Representante(idUsuario,nombre));
			}
			
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
		}finally{
			//DBManager.closeDBConnection();
			return tmpListaRepr;		
		}
   
    }

}