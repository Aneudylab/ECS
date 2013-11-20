package DataAccess;

import java.sql.ResultSet;
import domain.Plantilla;


public class PuntoEvaluacionDA {
	
	//--------------------------
	// Constructor 
	//--------------------------	
	
	public PuntoEvaluacionDA(){ }
	
	//------------------------------------------------------------------
	// Metodo que guarda los puntos de evaluacion en la DB
	//------------------------------------------------------------------
	
	public void guardarPunto(int idPlantilla, int idPunto, String descripcion){
								
        Object[] parametros;
        String query = "INSERT INTO punto_evaluacion( " +
                       "ID_PUNTO_EVALUACION,ID_PLANTILLA, DESCRIPCION )" +
                       "VALUES(?,?,?)"; 
        
        parametros = new Object[]{idPunto, idPlantilla, descripcion };
        
        try{
        	
            DBManager.openDBConnection();
            DBManager.ejecutarUpdate(query, parametros);
           
        }catch(Exception ex){
        	System.out.println("Error: " + ex.getMessage());
        	System.out.println("En puntoevaluacionDA");
        }
        
	}
	
	public void leerPuntosEvaluacion(Plantilla actualPlantilla){
        
		ResultSet result;
	    String query = "SELECT id_punto_evaluacion id, descripcion " + 
 		               "FROM punto_evaluacion where id_plantilla = ? ";
		
		Object[] parametros = new Object[]{actualPlantilla.getId()};
		
		try{
		   DBManager.openDBConnection();
           result = DBManager.ejecutarQuery(query, parametros);
		   
		   while(result.next()){
		     int idPunto = result.getInt("id");
			 String descPunto = result.getString("descripcion");
			 
			 actualPlantilla.CrearPuntoEvaluacion(idPunto,descPunto);
		    }
		   
		}catch(Exception err) {
           System.out.println("Error: " + err.getMessage());
		}finally{
		   //DBManager.closeDBConnection();
		}
	}


}
