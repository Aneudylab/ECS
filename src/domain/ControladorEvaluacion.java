
package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import DataAccess.PlantillaDA;
import DataAccess.EvaluacionDA;


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
	
	public HashMap<Integer,String> obtenerListaEvaluaciones(){
		
		Usuario usuarioActual = new ControladorSesion().getUsuarioActual();
		HashMap<Integer,String> tmpEvaluaciones = new HashMap<Integer,String>();
		
		Representante unRep = new Representante();
		unRep.copiar(usuarioActual);
		
		ArrayList<Evaluacion> listaEva = unRep.obtenerListaEvaluaciones();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		
		for(Evaluacion tmpEva : listaEva){
			int tmpID = tmpEva.getId();
			String tmpDate = dateFormat.format(tmpEva.getFechaCreada());
			
			tmpEvaluaciones.put(tmpID,tmpDate);
			
		}
		
		return tmpEvaluaciones;
	}
	
	public ArrayList<Map<String,String>> obtenerEvaluacion(int idEva){
		
		//Usuario usrActual = new ControladorSesion().getUsuarioActual();
		ArrayList<Map<String,String>> retEva = 
		                          new ArrayList<Map<String,String>> ();
		
		Evaluacion evalActual = 
		         new EvaluacionDA().leerEvaluacion(idEva);
		
		int cantReps = evalActual.getContarRespuestas();
		
		for(int i = 0 ; i < cantReps ; i++){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		    Map<String,String> tmpPair = new HashMap<String,String>();
			
			int id = evalActual.getIdRespuesta(i);
			String resp = (evalActual.getRespuesta(id)) ? "Si" : "No";
			String pto = evalActual.getPuntoEvaluacion(id);
			
			
			tmpPair.put("id",String.valueOf(id));
			tmpPair.put("resp",resp);
			tmpPair.put("pto",pto);
			
			retEva.add(tmpPair);
			
		}
		
		return retEva;
	}
	
	public String guardarRevision(int idEvaluacion, 
	                        HashMap<Integer,String> respReclamadas){
	    
		PlantillaDA unaPlant = new PlantillaDA();
		
		unaPlantilla = unaPlant.leerSoloPlantilla(idEvaluacion);	
	    Evaluacion unaEva = new Evaluacion (idEvaluacion,unaPlantilla);
		
		for(Map.Entry<Integer,String> tmpResp : respReclamadas.entrySet()){
			int num = tmpResp.getKey();
			String coment = tmpResp.getValue();
			
			unaEva.crearRespuesta(num,coment);
		}
		
		String nuevoEstado = new EvaluacionDA().actualizarRevision(unaEva);
		
		return nuevoEstado;
	}
	
	
}