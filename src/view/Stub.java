package view;

import java.util.ArrayList;
import java.util.HashMap;

public class Stub {

	public static HashMap<Integer, String> darRepresentantes() {

		HashMap<Integer, String> testMap = new HashMap<Integer, String>();
		
		int i = 1;
		while(i <= 18){
			testMap.put(i, "  05-11-2013     Jairis Rosario                       ");
			i++;
		}

		return testMap;

	}
	
	public static ArrayList<HashMap<String, String> > darEvaluacion() {

		HashMap<String, String> testMap = new HashMap<String, String>();
		ArrayList<HashMap<String, String> > testMapa = new ArrayList<HashMap<String, String> >();
		
		int i = 0;
		while(i < 9){
			testMap.put(""+i+" ", "Fue Exelente su servicio con el cliente 	?     si      pienso que merezco mas puntos");
			testMapa.add(testMap);
			i++;
		}
		return testMapa;
	}
	
	public static int guardarEvaluacion(int idReps, HashMap<Integer, Boolean> Respuestas){
		return 1;
	}
	
	
	public static ArrayList<HashMap<String, String> > darEvaluacion6() {

		
		ArrayList<HashMap<String, String> > testMapa = new ArrayList<HashMap<String, String> >();
		
		int i = 0;
		while(i < 12 ){
			HashMap<String, String> testMap = new HashMap<String, String>();
			testMap.put("id",""+i);
			testMap.put("pto","Fue Exelente su servicio con el cliente mientras coversaba?");
			testMap.put("resp","No");
			testMap.put("coment","Pienso que merezco mas puntos dfdhgfdhgd fjfdhhsjkg gfdhgjk jshgjdhg");
			testMapa.add(testMap);
			i++;
		}
		return testMapa;
	}

}
