
package domain;

import java.util.ArrayList;
import java.util.Date;
import DataAccess.RespuestaDA;


public class Evaluacion{
  
   private int evaluacionid;
   private Date fechaCreada;
   private Plantilla unaPlantilla;
   private ArrayList<Respuesta> listaRespuestas;
  
   //---------------------------------------------------
   //	Constructores
   //------------------------------------------------
   public Evaluacion(){}

   public Evaluacion(Plantilla tmpPlantilla){
		unaPlantilla = tmpPlantilla;
		listaRespuestas = new ArrayList<Respuesta>();
    }
	
	public Evaluacion(Plantilla tmpPlantilla, ArrayList<Respuesta> resp){
	    unaPlantilla = tmpPlantilla;
		listaRespuestas = resp;	    
	}
	
   //---------------------------------------------------
   //	Metodos
   //------------------------------------------------
   public int getId(){
      return evaluacionid;
   } 
   
   public int getIdPlantilla(){
      return unaPlantilla.getId();
   }
	
   public void crearRespuesta(int tmpNum, boolean tmpCumple){
      listaRespuestas.add(new Respuesta(tmpNum,tmpCumple));
   }
   
   public void setID (int tmpID){
      evaluacionid = tmpID;
   }
   
   public void guardarRespuestas(){
     RespuestaDA unaResDA = new RespuestaDA();
	 
     for(Respuesta tmpRes:listaRespuestas){
	    int idPunto = tmpRes.getID();
		boolean cumple = tmpRes.getCumplePunto();
		int idPlantilla = unaPlantilla.getId();
		
		unaResDA.guardarRespuesta(evaluacionid,unaPlantilla.getId(),
		                          idPunto,cumple);
	 }
   }
  // Stub Aneudy
   // oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
   //Constructores
   public Evaluacion(int id, Date fechaCreada){
       this.evaluacionid = id;
       this.fechaCreada = fechaCreada;
       listaRespuestas = new ArrayList<Respuesta>();
   }

   public Evaluacion(int id, Plantilla unaPlant){
       this.evaluacionid = id;
       this.unaPlantilla = unaPlant;
       listaRespuestas = new ArrayList<Respuesta>();
   }
   // Métodos
   public Date getFechaCreada(){
       return this.fechaCreada;
   }

   public int getIdRespuesta(int index){
       if(index < 0 || index > listaRespuestas.size()) return -1;
       return listaRespuestas.get(index).getID();
   }

   public boolean getRespuesta(int id){
       for(Respuesta resp: listaRespuestas){
           if(resp.getID() == id) return resp.getCumplePunto();
       }

       return false;
   }

   public int contarRespuestas(){
       return listaRespuestas.size();
   }

   @Override
   public boolean equals(Object object){
       boolean isSame = false;

       if(object != null && object instanceof Evaluacion){
           Evaluacion daEv = (Evaluacion) object;

           isSame = fechaCreada.compareTo(daEv.getFechaCreada()) == 0;
           isSame = isSame && evaluacionid == daEv.getId();
       }

       return isSame;
   }
   // oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
   // Fin Stub Aneudy
}
