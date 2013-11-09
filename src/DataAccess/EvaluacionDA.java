
package DataAccess;

import java.util.Date;
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
        	System.out.println("En EvaluacionDA");
        }
		finally{
		   //DBManager.closeDBConnection();
		}
	   
	}
}
