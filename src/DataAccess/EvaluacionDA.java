
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
        	System.out.println("En EvaluacionDA");
        }
		finally{
		   //DBManager.closeDBConnection();
		}
	}

    // Retorna una lista de evaluaciones de un representante
    public ArrayList<Evaluacion> leerEvaluaciones(int idRepresentante){
        ArrayList<Evaluacion> evals = new ArrayList<Evaluacion>();
        ResultSet result;
        Object[] parametros;

        String query =  "SELECT id_evaluacion, fecha_creada " +
                        "FROM evaluacion " +
                        "WHERE id_estado_evaluacion = 1 and id_representante = ?";

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

    // Actualiza en BD la revisión hecha a una Evaluación.
    public String actualizarRevision(Evaluacion unaEv){
        RespuestaDA unaRda = new RespuestaDA();
        Object[] parametros;

        int cantResp = unaEv.contarRespuestas();
        int idEv = unaEv.getId();
        int idPlant = unaEv.getIdPlantilla();

        for(int i = 0; i < cantResp; ++i){
            int idResp = unaEv.getIdRespuesta(i);
            String coment = unaEv.getComentario(idResp);

            unaRda.reclamarRespuesta(idEv, idPlant, idResp, coment);
        }

        int newEstado = cantResp > 0 ? 2 : 3; // 2 = Reclamada, 3 = Finalizada

        String query =  "UPDATE evaluacion " + // Evaluacion
                        "SET id_estado_evaluacion = ? " +
                        "Where id_evaluacion = ?";

        parametros = new Object[]{newEstado, idEv};

        try {
            DBManager.openDBConnection();
            DBManager.ejecutarUpdate(query, parametros);
        }
        catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return newEstado == 2 ? "Reclamada" : "Finalizada";
    }
}
