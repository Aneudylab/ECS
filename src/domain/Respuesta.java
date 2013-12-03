
package domain;


public class Respuesta{

    private int num;
    private boolean cumple;
    private String comentario ;


    public Respuesta(){}

    public Respuesta(int tmpNum, boolean tmpCumple){
        num = tmpNum;
        cumple = tmpCumple;
    }

    public Respuesta (int tmpNum, String comment){
        num = tmpNum;
        comentario = comment;
    }

    public Respuesta(int tmpNum, boolean tmpCumple, String comment){
        num = tmpNum;
        cumple = tmpCumple;
        comentario = comment;
    }

    public int getID(){
        return num;
    }

    public boolean getCumplePunto(){
        return cumple;
    }

    public String getComentario(){
        return this.comentario;
    }

}
