
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
	   super (id,nombre);
        
    }
	/////////////////////////////////
	//		Metodos
	///////////////////////////////
	public ArrayList<Representante> obtenerListaRepresentantes(){
	   RepresentanteDA repre = new RepresentanteDA();
	   return repre.leerRepresentantes(super.getID());
	} 
	
	public int crearEvaluacion(Plantilla plantillaActual,int idRep, 
	                           ArrayList<Respuesta> respuestas){
	  
	  Evaluacion unEva = new Evaluacion (plantillaActual,respuestas);
	  EvaluacionDA tmpEvaDA = new EvaluacionDA();
	  
	  tmpEvaDA.guardarEvaluacion(super.getID(),idRep,unEva);
	  unEva.guardarRespuestas();
	  
	  return unEva.getId();
	}
    
    /**
     * Retorna una lista de las evaluaciones reclamadas hecha por los
     * representantes que se reportan a este supervisor
     *  
	 * @return object
     **/
    public ArrayList<Evaluacion> obtenerEvaluacionesReclamadas() {
        EvaluacionDA eda = new EvaluacionDA();
        return eda.leerEvaluacionesReclamadas(getID());
    }

	//@override
	public void copiar(Usuario usr){
	   super.setID(usr.getID());
	   super.setNombre(usr.getNombre());
	   super.setRol(new Rol(usr.getRolID(),usr.getRol()));
	}
	
	
}
