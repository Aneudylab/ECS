package Domain;
		
public class Evaluacion{
	
    private int num;
    private String coment;
    private String idResp;


		public Evaluacion(int num, String coment, String idResp){
	
	    voidCrearRespuesta(num, coment){
    resp = Respuesta(num, coment);

    respuestas.add(resp);
        }
    }

        public int getIdRespuesta(){
     intgetIdRespuesta(index){
return this.respuestas.at(index).getId();
         }
    }

	    public String getComentario(){
String getComentario(idResp){
foreach(resp in this.respuestas){
if(resp.getId){
coment = resp.getComentario();
                }
            }
        }
    }
}
