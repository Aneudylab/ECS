
package DataAccess;

public class RespuestaDA{

   public void guardarRespuesta(int idEval,int idPlant, 
                                 int idPtoEva,boolean cumplePunto ){
	
		Object[] parametros;
        String query = "INSERT INTO repuesta " +
                       "(id_evaluacion,id_plantilla,id_punto_evaluacion," + 
					   "cumple_punto) " +
					   "VALUES(?,?,?,?) "; 
        
		int cumple = 0;
		if(cumplePunto){
		   cumple = 1;
		}
		
        parametros = new Object[]{idEval,idPlant,idPtoEva,cumple};
        
        try{
        	
            DBManager.openDBConnection();
			DBManager.ejecutarUpdate(query, parametros);
			
        }catch(Exception ex){
        	System.out.println("Error: " + ex.getMessage());
        }
		finally{
		   //DBManager.closeDBConnection();
		}	    
	}

}