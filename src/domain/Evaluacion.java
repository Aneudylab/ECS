package domain;

import java.util.ArrayList;
import java.util.Date;
import DataAccess.RespuestaDA;

public class Evaluacion{

    private int evaluacionid;
    private Plantilla unaPlantilla;
    private Date fechaCreada;
    private ArrayList<Respuesta> listaRespuestas;
    private Representante representante;

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

    public Evaluacion(int id, Date fechaCreada, Representante representante){
        this.evaluacionid = id;
        this.fechaCreada = fechaCreada;
        this.representante = representante;
        listaRespuestas = new ArrayList<Respuesta>();
    }
    //---------------------------------------------------
    //	Metodos
    //------------------------------------------------
    public int getId(){
        return evaluacionid;
    } 

    public void setID (int tmpID){
        evaluacionid = tmpID;
    }

    public int getIdPlantilla(){
        return unaPlantilla.getId();
    }

    public void crearRespuesta(int tmpNum, boolean tmpCumple){
        listaRespuestas.add(new Respuesta(tmpNum,tmpCumple));
    }

    public void crearRespuesta(int tmpNum, String tmpComent){
        listaRespuestas.add(new Respuesta(tmpNum,tmpComent));
    }

    public void crearRespuesta(int tmpNum, boolean cumple, String tmpComent){
        listaRespuestas.add(new Respuesta(tmpNum, cumple, tmpComent));
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

    public String getComentario(int id){
        for(Respuesta resp: listaRespuestas){
            if(resp.getID() == id) return resp.getComentario();
        }

        return "";
    }

    public String getPuntoEvaluacion(int id) {
        return unaPlantilla.getPuntoEvaluacion(id);
    }

    public int contarRespuestas(){
        return listaRespuestas.size();
    }

    public Representante getRepresentante() {
        return representante;
    }
}
