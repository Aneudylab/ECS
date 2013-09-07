package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/ecs";
	private String user = "root";
	private String pwd = "aneudylab";

	private DBManager() {
		if (connection == null) {

			try {

				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url, user, pwd);

				if (connection != null) {
					System.out.println("Conexión establecida... ");
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

	public static Connection openDBConnection() {
		try {
			dbManager = new DBManager();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return connection;

	}
	
	public static void closeDBConnection (){
	   
	   	try {
			connection.close();
			System.out.println("Conexión cerrada... ");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	   
	}

}
