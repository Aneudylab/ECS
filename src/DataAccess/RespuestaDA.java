
package DataAccess;
import domain.Evaluacion;
import java.sql.ResultSet;

public class RespuestaDA{

   public void guardarRespuesta(int idEval,int idPlant, 
                                 int idPtoEva,boolean cumplePunto ){
	
		Object[] parametros;
        String query = "INSERT INTO respuesta " +
                       "(id_evaluacion,id_plantilla,id_punto_evaluacion," + 
					   "cumple_punto) " +
					   "VALUES(?,?,?,?) "; 
        

		int cumple = cumplePunto ? 1 : 0;
		
        parametros = new Object[]{idEval,idPlant,idPtoEva,cumple};
        
        try{
        	
            DBManager.openDBConnection();
			DBManager.ejecutarUpdate(query, parametros);
			
        }catch(Exception ex){
        	System.out.println("Error: " + ex.getMessage());
        }
	}

   public void cargarRespuestas(Evaluacion unaEv){
        ResultSet result;
        Object[] parametros;

        String query = "SELECT id_punto_evaluacion, cumple_punto " +
                       "FROM respuesta " +
                       "WHERE id_evaluacion = ? " +
                       "  AND id_plantilla = ?";

        parametros = new Object[]{unaEv.getId(), unaEv.getIdPlantilla()};

        try{
            DBManager.openDBConnection();
			result = DBManager.ejecutarQuery(query, parametros);

            while(result.next()){			                
				int id = result.getInt("id_punto_evaluacion"); 
                boolean cumple = result.getInt("cumple_punto") == 1;

                unaEv.crearRespuesta(id, cumple);
			}
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
