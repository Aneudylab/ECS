
package domain;

import java.util.HashMap;
import java.util.ArrayList;
import DataAccess.RolDA;
import DataAccess.SupervisorDA;


public class ControladorUsuario{

  public HashMap<Integer,String> obtenerListaSupervisores(){
    
	SupervisorDA unSuperDA = new SupervisorDA();
	ArrayList<Supervisor> tmpSupervisores = unSuperDA.leerSupervisores();
    
	HashMap<Integer,String> listaSuper = new HashMap<Integer,String>();
	
    for (Supervisor tmpSpv : tmpSupervisores){
	   
	    int id = tmpSpv.getId();
		String nombre = tmpSpv.getNombre();
		
		listaSuper.put(id,nombre);
	}	
	
	return listaSuper;
  }
  
  public HashMap<Integer,String> obtenerListaRoles(){
	
	RolDA unRolDA = new RolDA();
	ArrayList<Rol> tmpRoles = unRolDA.leerRoles();
    
	HashMap<Integer,String> listaRoles = new HashMap<Integer,String>();
	
    for (Rol tmpRol : tmpRoles){
	   
	    int id = tmpRol.getId();
		String descripcion = tmpRol.getDescripcion();
		
		listaRoles.put(id,descripcion);
	}	
	
	return listaRoles;  
  }
  
  public int crearUsuario (String nombre, int rol, String clave, 
                           String nomUsr, int idSuper){

      Usuario usuActual = new ControladorSesion().getUsuarioActual();
	  
	  Administrador unAdmin = new Administrador();
	  unAdmin.copiar(usuActual); 
	  int id = unAdmin.crearUsuario(nombre,rol,clave,nomUsr,idSuper);
	  
	  return id;
   }
   
}