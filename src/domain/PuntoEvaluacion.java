package domain;

public class PuntoEvaluacion {
	
	private int puntoEvaluacionId;
	private String descripcion;
	
	public PuntoEvaluacion() {}
	
	public PuntoEvaluacion(int num, String ptoEv){
		this.puntoEvaluacionId = num;
		this.descripcion = ptoEv;
	}
	
	public int getId(){
		return puntoEvaluacionId;
	}
	
	public String getDescripcion(){
		return descripcion;
	}

}
