package view;

import java.util.HashMap;

public class Stub {

	public static HashMap<Integer, String> darRepresentantes() {

		HashMap<Integer, String> testMap = new HashMap<Integer, String>();
		
		int i =0;
		while(i <= 10){
			testMap.put(i, "Jairis Roario                       ");
			i++;
		}

		return testMap;

	}
	
	public static HashMap<Integer, String> darEvaluacion() {

		HashMap<Integer, String> testMap = new HashMap<Integer, String>();
		
		int i = 0;
		while(i <= 10){
			testMap.put(i, "Fue Exelente su servicio con el cliente 	?");
			i++;
		}
		return testMap;
	}
	
	public static int guardarEvaluacion(int idReps, HashMap<Integer, Boolean> Respuestas){
		return 1;
	}

}
