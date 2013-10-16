package DataAccess;

import java.sql.ResultSet;
import java.util.Date;
import domain.Plantilla;

/**
 * Data Access para plantilla
 * @author Aneudy Labour
 * @since 2013-10-04
 * */
public class PlantillaDA{

    /**
     * Método que guarda un objeto Plantilla en la BD y lo actualiza con el Id generado 
     * @param unaPlant Plantilla a guardar
     * @param idAdministrador id del usuario que creó la plantilla
     * */
    public void guardarPlantilla(Plantilla unaPlant, int idAdministrador){
        Object[] parametros;

        String query = "INSERT INTO PLANTILLA (FECHA_CREADA, ID_USUARIO_CREADOR) VALUES (?, ?)";

        parametros = new Object[]{new Date(), idAdministrador};
        try{

            DBManager.openDBConnection();
            int id = DBManager.executeQueryGeneratedKeys(query, parametros);
            unaPlant.setId(id);

        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
	
	public Plantilla leerPlantillaActual(){
	    
		String query = "SELECT max(id_plantilla) id FROM Plantilla";
	    Object[] parametros = null;
		Plantilla plantilla = new Plantilla();;
		PuntoEvaluacionDA puntoEvaDA = new PuntoEvaluacionDA();
		
		try{
		   DBManager.openDBConnection();
		   ResultSet result = DBManager.ejecutarQuery(query,parametros);
		   
		   if(result.next()){
		      int idPlan = result.getInt("id");
			  plantilla.setId(idPlan);
			  puntoEvaDA.leerPuntosEvaluacion(plantilla);
		    }
		
		}catch(Exception err){
		    System.out.println("Error: " + err.getMessage());
		}finally{
            //DBManager.closeDBConnection();
		    return plantilla;
		}
	}
} 
