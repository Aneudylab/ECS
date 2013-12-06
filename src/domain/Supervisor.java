
package domain;

import java.util.ArrayList;
import java.util.HashMap;
import DataAccess.RepresentanteDA;
import DataAccess.EvaluacionDA;


public class Supervisor extends Usuario{

	///////////////////////////
	//     Constructores
	//////////////////////////
	public Supervisor(){}
	
	public Supervisor(int id, String nombre, Rol rol){
	   super (id,nombre,rol);
	}
	
    public Supervisor(int id, String nombre) {
        super(id, nombre);
    }
	/////////////////////////////////
	//		Metodos
	///////////////////////////////
	public ArrayList<Representante> obtenerListaRepresentantes(){
	   RepresentanteDA repre = new RepresentanteDA();
	   return repre.leerRepresentantes(super.getId());
	} 
	
	public int crearEvaluacion(Plantilla plantillaActual,int idRep, 
	                           ArrayList<Respuesta> respuestas){
	  
	  Evaluacion unEva = new Evaluacion (plantillaActual,respuestas);
	  EvaluacionDA tmpEvaDA = new EvaluacionDA();
	  
	  tmpEvaDA.guardarEvaluacion(super.getId(),idRep,unEva);
	  unEva.guardarRespuestas();
	  
	  return unEva.getId();
	}
    
	//@override
	public void copiar(Usuario usr){
	   super.setId(usr.getId());
	   super.setNombre(usr.getNombre());
	   super.setRol(new Rol(usr.getIdRol(),usr.getRol()));
	}
	
	
}
