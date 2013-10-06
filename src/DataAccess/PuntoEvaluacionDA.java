package DataAccess;


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
        }
        
        
	}


}
