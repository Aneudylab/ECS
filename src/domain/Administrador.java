/**
 *  Administrador.java
 *
 *  Copyright (c) 2013 - Luisa I. Soriano
 */
package domain;

import java.util.ArrayList;

import DataAccess.PlantillaDA;
import DataAccess.UsuarioDA;

public class Administrador extends Usuario{
	
	//----------------------
	// Constructores
	//----------------------
	
	public Administrador(){}
	
	public Administrador(int id, String nombre, Rol rol){
		super(id, nombre, rol);
	}
	
	//--------------------------------------------------------------------
	// Metodo que crea una nueva plantilla
	//--------------------------------------------------------------------
	
	public int crearPlantilla( ArrayList<String> listaPtosEv){
		
		Plantilla unaPlant = new Plantilla(listaPtosEv);

	    new PlantillaDA().guardarPlantilla(unaPlant, this.getId());
	    unaPlant.guardarPuntosEvaluacion();

	    int plantillaId = unaPlant.getId();

	    return plantillaId;

	}
	
	
	//--------------------------------------------------------------------
	// Metodo que crea un nuevo Usario 
	//--------------------------------------------------------------------
	
    /**
     * Guarda un Usuario en la BD
	 * @param nombre Nombre del usuario...
	 * @param rol Rol del usuario...
	 * @param clave Clave...
	 * @param nombreUsuario Nombre Inicio de Sesion...
	 * @param idSupervisor id del supervisor...
     *  
	 * @return object
     **/
    public int crearUsuario(String nombre, int idRol, String clave, String nombreUsuario, int idSupervisor) {
        UsuarioDA uda = new UsuarioDA();
		Usuario unUsr = new Usuario(nombre, new Rol(idRol, ""), clave, nombreUsuario); 

        uda.guardarUsuario(unUsr, getId(), idSupervisor);

        return unUsr.getId();

    }
}
