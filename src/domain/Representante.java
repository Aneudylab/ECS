
package domain;

import java.util.ArrayList;
import DataAccess.EvaluacionDA;
import domain.Evaluacion;


public class Representante extends Usuario {
   
  
   
   public Representante(){}
   
   public Representante(int id, String nombre){
      super(id,nombre);
   }
	

	
	public ArrayList ObtenerListaEvaluaciones (){
	
		ArrayList <Evaluacion> listEvals;
		
		int idRepresentante = this.getID();
		
		listEvals = EvaluacionDA.LeerEvaluaciones(idRepresentante);
		
		
		return listEvals;
	}
}