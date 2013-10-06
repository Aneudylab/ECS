

package domain;


import java.util.Date;
import java.util.ArrayList;


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
	 for (String tmpPunto; listaPtosEv){
	    CrearPuntoEvaluacion(num++, tmpPunto);
	 }
    
   }
   
   ////////////////////////////
   // Metodos de la clase
   ////////////////////////////
   
   private void CrearPuntoEvaluacion(int num, String punto){
      PuntoEvaluacion puntoEva = new PuntoEvaluacion(num,punto);
	  listaPtoEva.add(puntoEva);
   
   }
   
   public void setId (int tmpID){
      plantillaid = tmpID;
   }
   
   void GuardarPuntosEvaluacion(){
     
   }
   
}