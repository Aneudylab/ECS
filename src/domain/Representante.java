
package domain;

import java.util.ArrayList;
import DataAccess.EvaluacionDA;
import domain.Evaluacion;


public class Representante extends Usuario {

    public Representante(){}

    public Representante(int id, String nombre){
        super(id,nombre);
    }

    public ArrayList<Evaluacion> obtenerListaEvaluaciones (){
        ArrayList<Evaluacion> listEvals = new ArrayList<Evaluacion>();
        EvaluacionDA unEvda = new EvaluacionDA();

        int idRepresentante = this.getId();

        listEvals = unEvda.leerEvaluaciones(idRepresentante);

        return listEvals;
    }
}
