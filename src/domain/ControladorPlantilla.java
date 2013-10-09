package domain;

import java.util.ArrayList;

public class ControladorPlantilla{

	//metodo que retorna el id de la plantilla creada por el
	//usuario administrador
    public int crearPlantilla (ArrayList<String> puntos){
        ControladorSesion cSesion = new ControladorSesion();

        //conversion temporar a de usuario a administrador
        Administrador unAdmin = new Administrador();
        unAdmin.copiar(cSesion.getUsuarioActual());

        int id = unAdmin.crearPlantilla(puntos);
        return id;

    } 
	 
    //metodo que verifica si el usuario es administrador
    public boolean validarEsAdmin (Usuario usuActual){
        return usuActual.getRol().equals("Administrador");
	}	
}
