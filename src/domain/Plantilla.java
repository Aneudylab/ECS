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
	
	public Plantilla(int tmpID){
	   plantillaid = tmpID;
	   fechaCreada = null;
	   listaPtoEva = new ArrayList<PuntoEvaluacion>();
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
	
	public ArrayList<PuntoEvaluacion> getPuntos(){
	    return listaPtoEva;
	}
	
	public int cantidadPuntos(){
	    return listaPtoEva.size();
	}

    public void setId (int tmpID){
        plantillaid = tmpID;
    }

    public int getIdPunto(int index){
        if(index < 0 || index > listaPtoEva.size()) return -1;
        return listaPtoEva.get(index).getId();
    }

    public String getPuntoEvaluacion(int id) {
        for (PuntoEvaluacion pto : listaPtoEva) {
            if (pto.getId() == id) return pto.getDescripcion();
        }

        return "";
    }

    /////////////////////////////////
    //Otros metodos
    //////////////////////////////////

    public void CrearPuntoEvaluacion(int num, String punto){
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
