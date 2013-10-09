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

	    new PlantillaDA().guardarPlantilla(unaPlant, this.getID());
	    unaPlant.guardarPuntosEvaluacion();

	    int plantillaId = unaPlant.getId();

	    return plantillaId;

	}
	
	
	//--------------------------------------------------------------------
	// Metodo que crea un nuevo Usario 
	//--------------------------------------------------------------------
	
/*	public int crearUsuario(String nombre, 
			                 Rol rol,
			                 String clave, 
			                 String nombreUsuario,
			                 int idSupervisor){
		
		Usuario usuario = new Usuario(nombre, rol, clave, nombreUsuario); 
		new UsuarioDA().guardar(usuario, this.getID(), idSupervisor); 
	   	
	    return usuario.getID();	
	}*/
}
