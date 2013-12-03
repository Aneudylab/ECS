
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

    public void reclamarRespuesta(int idEv, int idPlant, int idResp, String coment){
        Object[] parametros;

        String query = "UPDATE respuesta " // Respuesta
            + "SET es_reclamada = 1, " // 1 = true
            + "    comentario=  ? "
            + "WHERE id_evaluacion = ? "
            + "  AND id_plantilla = ? "
            + "  AND id_punto_evaluacion = ?";

        parametros = new Object[]{coment, idEv, idPlant, idResp};

        try {
            DBManager.openDBConnection();
            DBManager.ejecutarUpdate(query, parametros);
        }
        catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void cargarRespuestasReclamadas(Evaluacion unaEv){
        ResultSet result;
        Object[] parametros;

        String query = "SELECT id_punto_evaluacion, cumple_punto, comentario " +
            "FROM respuesta " +
            "WHERE id_evaluacion = ? " +
            "  AND id_plantilla = ? " +
            "  AND es_reclamada = 1 ";

        parametros = new Object[]{unaEv.getId(), unaEv.getIdPlantilla()};

        try{
            DBManager.openDBConnection();
            result = DBManager.ejecutarQuery(query, parametros);

            while(result.next()){                     
                int id = result.getInt("id_punto_evaluacion"); 
                boolean cumple = result.getInt("cumple_punto") == 1;
                String comentario = result.getString("comentario");

                unaEv.crearRespuesta(id, cumple, comentario);
            }
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void cambiarRespuesta(int idEv, int idPlant, int idResp, boolean cumple){
        Object[] parametros;

        String query = "UPDATE respuesta " // Respuesta
            + "SET es_reclamada = 0, " // 1 = false
            + "    cumple_punto =  ? "
            + "WHERE id_evaluacion = ? "
            + "  AND id_plantilla = ? "
            + "  AND id_punto_evaluacion = ?";

        // cpunto tomará el valor opuesto al actual en la respuesta
        int cpunto = cumple? 0 : 1; // Si es true, cambia a false = 0
                                    // Si es false cambia a true = 1
        parametros = new Object[]{cpunto, idEv, idPlant, idResp};

        try {
            DBManager.openDBConnection();
            DBManager.ejecutarUpdate(query, parametros);
        }
        catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
