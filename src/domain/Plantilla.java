

package domain;

import java.util.Date;
import java.util.ArrayList;
import DataAccess.PuntoEvaluacionDA;



public class Plantilla{

   private int plantillaid;
   private Date fechaCreada;
   private ArrayList<PuntoEvaluacion> listaPtoEva;
   
   /////////////////////
   // Contructores
   /////////////////////
   
   public Plantilla(){
      plantillaid = 0;
	  listaPtoEva = new ArrayList<PuntoEvaluacion>();
	  fechaCreada = null;
   }
   
   public Plantilla(ArrayList<String> listaPtosEv){
	 int num = 1;
	 listaPtoEva = new ArrayList<PuntoEvaluacion>();
	 for (String tmpPunto: listaPtosEv){
	    CrearPuntoEvaluacion(num++, tmpPunto);
	 }
    
   }
   
   ///////////////////////////////////
   // Metodos getters y setters
  ////////////////////////////////////
  
   public int getId(){
    return plantillaid;
   }
   
   public void setId (int tmpID){
      plantillaid = tmpID;
   }
   
   /////////////////////////////////
   //Otros metodos
   //////////////////////////////////
   
   private void CrearPuntoEvaluacion(int num, String punto){
      PuntoEvaluacion puntoEva = new PuntoEvaluacion(num,punto);
	  listaPtoEva.add(puntoEva);
   
   }

   public void guardarPuntosEvaluacion(){

       PuntoEvaluacionDA guardarPuntoEva = new PuntoEvaluacionDA();

       for (PuntoEvaluacion tmpPuntoEva: listaPtoEva){

           String desc = tmpPuntoEva.getDescripcion();
           int idPto = tmpPuntoEva.getId();


           guardarPuntoEva.guardarPunto(plantillaid, idPto, desc);

       }

   }
   
}
