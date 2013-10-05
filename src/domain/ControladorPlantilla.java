
package domain;


import javax.swing.JOptionPane;
import java.util.ArrayList;
import view.PlantillaForm;

public class ControladorPlantilla{

    
	//metodo que retorna el id de la plantilla creada por el
	//usuario administrador
	public int crearPlantilla (ArrayList<String> puntos){
	 
	  Administrador unAdmin = ControladorSesion.usuarioActual;
	  int id = unAdmin.crearPlantilla(puntos);
	  return id;
	
	} 
	 
    //metodo que verifica si el usuario es administrador
    public boolean validarEsAdmin (Usuario usuActual){
	   return usuActual.getRol().equals("Administrador");
	}	


}
