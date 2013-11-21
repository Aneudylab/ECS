import domain.Evaluacion;
import domain.Plantilla;
import DataAccess.PlantillaDA;
import java.util.Date;
import java.util.ArrayList;

public class PlantillaEvalDA extends BaseTest {

    @Override
    protected void RunTests(){
        testPlantillaDA();
    }

    private void testPlantilla() {
        Plantilla p = new Plantilla(5);
        p.CrearPuntoEvaluacion(3, "Punto prueba 1");
        p.CrearPuntoEvaluacion(5, "Punto prueba 2");
        p.CrearPuntoEvaluacion(7, "Punto prueba 3");

        Test("001 Test getId in Plantilla", p.getId() == 5);
        Test("002 Test getId in Plantilla", p.getId() != 1);
        Test("003 Test getPuntoEvaluacion in Plantilla", p.getPuntoEvaluacion(3) ==  "Punto prueba 1");
        Test("004 Test getPuntoEvaluacion in Plantilla", p.getPuntoEvaluacion(5) ==  "Punto prueba 2");
        Test("005 Test getPuntoEvaluacion in Plantilla", p.getPuntoEvaluacion(7) ==  "Punto prueba 3");

    }

    private void testPlantillaDA() {
        PlantillaDA pda = new PlantillaDA();
        Plantilla p = pda.leerPlantilla(2);
        Plantilla p2 = pda.leerPlantilla(7);
        
        Test("001 Test getId in Plantilla", p.getId() == 1);
        Test("002 Test getId in Plantilla", p2.getId() == 3);

        Test("003 Test getPuntoEvaluacion in Plantilla", p.getPuntoEvaluacion(1).equals("El representante utiliza protocolo de bienvenida"));
        Test("004 Test getPuntoEvaluacion in Plantilla", 
                p.getPuntoEvaluacion(3).equals("El representante identifica la necesidad del cliente"));
        
        Test("005 Test getPuntoEvaluacion in Plantilla", p2.getPuntoEvaluacion(1).equals("pregunta 1"));
        Test("006 Test getPuntoEvaluacion in Plantilla", p2.getPuntoEvaluacion(2).equals("pregunta 2"));
        Test("007 Test getPuntoEvaluacion in Plantilla", p2.getPuntoEvaluacion(3).equals("pregunta 3"));

        p = pda.leerSoloPlantilla(2);
        p2 = pda.leerSoloPlantilla(7);

        Test("008 Test getId in Plantilla", p.getId() == 1);
        Test("009 Test getId in Plantilla", p2.getId() == 3);
    }
    
}
