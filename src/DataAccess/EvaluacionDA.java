
package DataAccess;

import java.sql.ResultSet;
import java.util.Date;
import java.util.ArrayList;
import domain.Evaluacion;

public class EvaluacionDA{

    public void guardarEvaluacion(int idSuper, int idRepr, Evaluacion unaEva){
	    
		Object[] parametros;
        String query = "INSERT INTO evaluacion " +
                       "(fecha_creada,id_estado_evaluacion,id_plantilla," + 
					   "id_representante,id_supervisor) " +
					   "VALUES(?,?,?,?,?) "; 
        
        parametros = new Object[]{new Date(),1,unaEva.getIdPlantilla(),idRepr,idSuper};
        
        try{
        	
            DBManager.openDBConnection();
			int id = DBManager.executeQueryGeneratedKeys(query, parametros);
            
			unaEva.setID(id);
			
        }catch(Exception ex){
        	System.out.println("Error: " + ex.getMessage());
        }
		finally{
		   DBManager.closeDBConnection();
		}
	}

    public ArrayList<Evaluacion> leerEvaluaciones(int idRepresentante){
        ArrayList<Evaluacion> evals = new ArrayList<Evaluacion>();
        ResultSet result;
        Object[] parametros;

        String query =  "SELECT iD_EVALUACION, FECHA_CREADA " +
                        "FROM EVALUACION " +
                        "WHERE ID_REPRESENTANTE = ?";

        return evals;
    }
    /*{
       
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
   
    } */
}
