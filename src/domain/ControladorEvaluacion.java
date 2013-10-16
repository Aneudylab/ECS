
package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import DataAccess.PlantillaDA;


public class ControladorEvaluacion{
     
	private int representanteid;
	private Plantilla unaPlantilla;
	 
    //------------------------------------------------------------
	// 		Contructor 
	//----------------------------------------------------------
	public ControladorEvaluacion(){} 
	
    //------------------------------------------------------------
	// 		Metodos 
	//----------------------------------------------------------
	public ArrayList<HashMap<Integer,String> > obtenerListaRepresentantes(){
	   	   
	   Usuario usuActual = new ControladorSesion().getUsuarioActual();
	   ArrayList<HashMap<Integer,String> > listRepresentantes = new ArrayList<HashMap<Integer,String> >();
	   
	   Supervisor unSupervisor = new Supervisor();
	   unSupervisor.copiar(usuActual);
	   
	   ArrayList<Representante> listaRepr = 
	                            unSupervisor.obtenerListaRepresentantes();
       
	   for(Representante tmpRep: listaRepr){
		   
		   HashMap<Integer,String> tmpRepresentate = 
		                           new HashMap<Integer,String>();
		   
		   tmpRepresentate.put(tmpRep.getID(),tmpRep.getNombre()); 
		   listRepresentantes.add(tmpRepresentate);
	    }
		
		return listRepresentantes;
	} 
	
    public ArrayList<HashMap<Integer, String> > obtenerPlantillaActual(){
	   PlantillaDA unPlanDA = new PlantillaDA();
	   ArrayList<HashMap<Integer, String> > listaPuntos = 
	                            new ArrayList<HashMap<Integer, String> >();
				
	   unaPlantilla = unPlanDA.leerPlantillaActual();
	    
	   ArrayList<PuntoEvaluacion> tmpPuntos = unaPlantilla.getPuntos();
		
	   int cantPuntos = unaPlantilla.cantidadPuntos();
		
	   for(int i =0 ; i < cantPuntos; i++){
		 HashMap <Integer, String> tmpPair = new HashMap <Integer, String>();
		  
		 int idPunto = tmpPuntos.get(i).getId();
		 String descPunto = tmpPuntos.get(i).getDescripcion();
		
		 tmpPair.put(idPunto, descPunto);
		  
		 listaPuntos.add(tmpPair);
		}
		
	   return listaPuntos;
	}
     
	public int guardarEvaluacion (int idRepre, ArrayList<HashMap<Integer, Boolean> > resp){
	    
	   Usuario usuActual = new ControladorSesion().getUsuarioActual(); 
	   Supervisor unSupervisor = new Supervisor();
	   unSupervisor.copiar(usuActual);
	   
	   ArrayList<Respuesta> tmpResp = new ArrayList<Respuesta>();
	   
	   //se crea un bucle para recorrer el HashMap
	   for(HashMap<Integer,Boolean> tmpRepre:resp){ 
		    //se visualiza el par que contiene el HashMap
			for (Map.Entry<Integer, Boolean> entry : tmpRepre.entrySet())
			{
				tmpResp.add(new Respuesta(entry.getKey(),entry.getValue()));
			}
	    }
	   
	   return unSupervisor.crearEvaluacion(unaPlantilla,idRepre,tmpResp);
	}
}
