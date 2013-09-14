package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Description: Super class for script helper
 * 
 * @author jrosario
 * 
 * @since July 03, 2013
 **/

public class DBManager {

    private static Connection connection = null;
    private static DBManager dbManager;
    private static PreparedStatement stm;
    private static ResultSet resultado;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/ecs";
    private String user = "ecsusr";
    private String pwd = "ecspwd";

    private DBManager() {
        if (connection == null) {

            try {

                Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(url, user, pwd);

                if (connection != null) {
                    // Comentado. Esto muestra un mensaje que solamente es necesario para testing
                    //System.out.println("Conexión establecida... ");
                }

            } catch (IllegalAccessException e) {
                System.out
                    .println("Exception de Acceso Ilegal a la Base de Datos : "
                            + e.getMessage());
                e.printStackTrace();

            } catch (InstantiationException e) {
                System.out.println("Error Instanciando : " + e.getMessage());
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                System.out.println("Clase No encontrada : " + e.getMessage());

                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Exception de SQL : " + e.getMessage());

                e.printStackTrace();
            }
        }
    }


    /**
     * 
     * @param consulta Query
     * @param parametros de la consulta, si los hay
     * @return los resultados 
     * @throws Exception
     */

    public static ResultSet ejecutarQuery(String consulta, Object[] parametros)throws Exception{
        stm = connection.prepareStatement(consulta);

        if(parametros != null){
            for(int k = 0; k < parametros.length; k++){
                stm.setObject(k+1, parametros[k]);
            }
        }
        resultado = stm.executeQuery();

        return resultado;

    }



    /**
     * Metodo para ejecutar un insert o actualizacion
     * @param consulta es el Query
     * @param parametros Los parametros que tiene el Query, si los hay
     * @throws Exception
     */

    public static void ejecutarUpdate(String consulta, Object[] parametros) throws Exception{
        stm = connection.prepareStatement(consulta);

        if(parametros != null){
            for(int k = 0; k < parametros.length; k++){
                stm.setObject(k+1, parametros[k]);
            }
        }

        stm.executeUpdate();

    }

    /**
     * Metodo para abrir la conexion con la base de datos
     * @return la conexion
     */
    public static Connection openDBConnection() {
        try {
            dbManager = new DBManager();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return connection;
    }

    /**
     * Metodo para cerrar la conexion con la Base De Datos
     */
    public static void closeDBConnection (){

        try {
            if(!connection.isClosed()){
                connection.close();
                System.out.println("Conexión cerrada... ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
