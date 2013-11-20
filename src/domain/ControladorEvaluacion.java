
package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import DataAccess.PlantillaDA;


public class ControladorEvaluacion{
     
	private Plantilla unaPlantilla;
	 
    //------------------------------------------------------------
	// 		Constructor 
	//----------------------------------------------------------
	public ControladorEvaluacion(){} 
	
    //------------------------------------------------------------
	// 		Metodos 
	//----------------------------------------------------------
	public static HashMap<Integer,String> obtenerListaRepresentantes(){
	   	   
	   Usuario usuActual = new ControladorSesion().getUsuarioActual();
	   HashMap<Integer,String> listRepresentantes = new HashMap<Integer,String>();
	   
	   Supervisor unSupervisor = new Supervisor();
	   unSupervisor.copiar(usuActual);
	   
	   ArrayList<Representante> listaRepr = 
	                            unSupervisor.obtenerListaRepresentantes();
       
	   for(Representante tmpRep: listaRepr){
		   listRepresentantes.put(tmpRep.getID(),tmpRep.getNombre());
	    }
		
		return listRepresentantes;
	} 
	
    public HashMap<Integer, String> obtenerPlantillaActual(){
	   PlantillaDA unPlanDA = new PlantillaDA();
	   HashMap<Integer, String> listaPuntos = 
	                            new HashMap<Integer, String>();
				
	   unaPlantilla = unPlanDA.leerPlantillaActual();
	    
	   ArrayList<PuntoEvaluacion> tmpPuntos = unaPlantilla.getPuntos();
		
	   int cantPuntos = unaPlantilla.cantidadPuntos();
		
	   for(int i =0 ; i < cantPuntos; i++){	  
		 int idPunto = tmpPuntos.get(i).getId();
		 String descPunto = tmpPuntos.get(i).getDescripcion();
		 
		 listaPuntos.put(idPunto,descPunto);
		}
		
	   return listaPuntos;
	}
     
	public int guardarEvaluacion (int idRepre, HashMap<Integer, Boolean> resp){
	    
	   Usuario usuActual = new ControladorSesion().getUsuarioActual(); 
	   Supervisor unSupervisor = new Supervisor();
	   unSupervisor.copiar(usuActual);
	   
	   ArrayList<Respuesta> tmpResp = new ArrayList<Respuesta>();
	   
       //se mapea el HM a ArrayList<Respuesta>
       for (Map.Entry<Integer, Boolean> entry : resp.entrySet())
       {
           tmpResp.add(new Respuesta(entry.getKey(),entry.getValue()));
       }
	   
	   return unSupervisor.crearEvaluacion(unaPlantilla,idRepre,tmpResp);
	}
}
