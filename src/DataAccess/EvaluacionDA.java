
package DataAccess;

import java.sql.ResultSet;
import java.util.Date;
import java.util.ArrayList;
import domain.Evaluacion;
import domain.Plantilla;

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

    // Retorna una lista de evaluaciones de un representante
    public ArrayList<Evaluacion> leerEvaluaciones(int idRepresentante){
        ArrayList<Evaluacion> evals = new ArrayList<Evaluacion>();
        ResultSet result;
        Object[] parametros;

        String query =  "SELECT id_evaluacion, fecha_creada " +
                        "FROM evaluacion " +
                        "WHERE id_representante = ?";

        parametros = new Object[]{idRepresentante};

        try{
            DBManager.openDBConnection();
			result = DBManager.ejecutarQuery(query, parametros);

            while(result.next()){			                
				int id = result.getInt("id_evaluacion"); //Id de la evaluación
                Date fecha = result.getDate("fecha_creada"); //fecha en que se creó
                
                Evaluacion unaEv = new Evaluacion(id, fecha);

                evals.add(unaEv);
			}
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            return evals;
        }
    }

    // Retorna una evaluación según un Id de Evaluación
    public Evaluacion leerEvaluacion(int idEvaluacion){
        PlantillaDA pda = new PlantillaDA();
        RespuestaDA rda = new RespuestaDA();

        Plantilla unaPlant = pda.leerPlantilla(idEvaluacion);

        Evaluacion unaEv = new Evaluacion(idEvaluacion, unaPlant);

        rda.cargarRespuestas(unaEv);

        return unaEv;
    }
}
