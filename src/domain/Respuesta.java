
package domain;


public class Respuesta{
   
	private int num;
    private boolean cumple;

    
    public Respuesta(){}

	public Respuesta(int tmpNum,boolean tmpCumple){
	   num = tmpNum;
	   cumple = tmpCumple;
	}
	
	public int getID(){
	  return num;
	}
    
	public boolean getCumplePunto(){
	  return cumple;
	}

    // Stub Aneudy
    // oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
    // Atributos
    private String comentario;

    // Constructores
	public Respuesta(int tmpNum, String tmpComentario){
	   num = tmpNum;
	   comentario = tmpComentario;
	}

    //Métodos
    public String getComentario(){
        return comentario;
    }

    // oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
    // Fin Stub Aneudy
}
