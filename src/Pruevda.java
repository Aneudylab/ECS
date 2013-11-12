import domain.Evaluacion;
import DataAccess.EvaluacionDA;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Pruevda{
    private int passed;
    private int failed;

    public Pruevda(){
        this.passed = 0;
        this.failed = 0;
    }

    public void Run() {
        System.out.println("Running...");
        RunTests();
        System.out.println("Test Completed");
        System.out.println("\n===================================================");
        System.out.println("Summary: " + passed + " passed " + failed + " failed");
    }
    private Date newFecha(String f) {
        Date d = null;
        try{
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");

            d = sdf.parse(f);
        }
        catch(Exception e){
        }

        return d;
    }
    private void TestSuiteProbarEvaluacion(){
        Evaluacion ev = new Evaluacion(1, newFecha("20/05/2008"));
        Evaluacion ev2 = new Evaluacion(1, newFecha("20/05/2008"));
        Evaluacion ev3 = new Evaluacion(2, newFecha("20/05/2008"));
        Evaluacion ev4 = new Evaluacion(1, newFecha("20/05/2007"));

        ArrayList<Evaluacion> evList = new ArrayList<Evaluacion>();

        evList.add(ev);
        evList.add(ev3);
        evList.add(ev4);

        Test("Ev and the correct Id", testEvaluacionId(ev, 1, true));
        Test("Ev and the correct Id", testEvaluacionId(ev3, 2, true));
        Test("Ev and the incorrect Id", testEvaluacionId(ev, 2, false));
        Test("Ev and the incorrect Id", testEvaluacionId(ev3, 1, false));

        Test("Ev and the correct date", testEvaluacionDate(ev, newFecha("20/05/2008"), true));
        Test("Ev and the correct date", testEvaluacionDate(ev4, newFecha("20/05/2007"), true));
        Test("Ev and the incorrect date", testEvaluacionDate(ev, newFecha("20/05/2007"), false));
        Test("Ev and the incorrect date", testEvaluacionDate(ev4, newFecha("20/05/2008"), false));

        Test("EvList contains an Ev", testEvListContainsEv(evList, ev2, true));
        Test("EvList contains an Ev", testEvListContainsEv(evList, new Evaluacion(2, newFecha("20/05/2008")), true));
        Test("EvList doesn't contains an Ev", testEvListContainsEv(evList, new Evaluacion(1, newFecha("20/05/2004")), false));
        Test("EvList doesn't contains an Ev", testEvListContainsEv(evList, new Evaluacion(2, newFecha("20/05/2007")), false));

        Test("Two Equals Evs", testEvaluacionEquals(ev, ev2, true));
        Test("Two Equals With Diff Ids", testEvaluacionEquals(ev, ev3, false));
        Test("Two Equals With Diff Dates", testEvaluacionEquals(ev, ev4, false));
    }

    private void TestSuiteProbarEvaluacionDA(){
        EvaluacionDA eda = new EvaluacionDA();
        Evaluacion ev = null;
        ArrayList<Evaluacion> evList = null;

        evList = eda.leerEvaluaciones(4);

        Test("Count Objs in EvList", evList.size() == 3);
        Test("EvList contains an Ev", testEvListContainsEv(evList, new Evaluacion(1, newFecha("07/04/2013")), true));
        Test("EvList contains an Ev", testEvListContainsEv(evList, new Evaluacion(2, newFecha("12/11/2013")), true));
        Test("EvList contains an Ev", testEvListContainsEv(evList, new Evaluacion(3, newFecha("12/11/2013")), true));

        evList = eda.leerEvaluaciones(5);
        Test("Count Objs in EvList", evList.size() == 2);
        Test("EvList contains an Ev", testEvListContainsEv(evList, new Evaluacion(4, newFecha("12/11/2013")), true));
        Test("EvList contains an Ev", testEvListContainsEv(evList, new Evaluacion(5, newFecha("12/11/2013")), true));

        evList = eda.leerEvaluaciones(6);
        Test("Count Objs in EvList", evList.size() == 0);
       
        ev = eda.leerEvaluacion(1);
        Test("Ev's PlantillaId", testEvIdPlantilla(ev, 1, true));
        Test("Ev's incorrect PlantillaId", testEvIdPlantilla(ev, 2, false));

    }

    private void RunTests(){
        TestSuiteProbarEvaluacion();
        TestSuiteProbarEvaluacionDA();
    }

    private boolean testEvaluacionId(Evaluacion input1, int input2, boolean expected){
        return (input1.getId() == input2) == expected;
    }

    private boolean testEvaluacionDate(Evaluacion input1, Date input2, boolean expected){
        return (input2.compareTo(input1.getFechaCreada()) == 0) == expected;
    }

    private boolean testEvaluacionEquals(Evaluacion input1, Evaluacion input2, boolean expected){
        return input1.equals(input2) == expected;
    }

    private boolean testEvListContainsEv(ArrayList<Evaluacion> input1, Evaluacion input2, boolean expected){
        return input1.contains(input2) == expected;
    }

    private boolean testEvIdPlantilla(Evaluacion input1, int input2, boolean expected){
        return (input1.getIdPlantilla() == input2) == expected;
    }
    private boolean testEvaluacionDALeerEvaluacion(){
        return true;
    }
    private boolean testEvaluacionDAActualizarRevision(){
        return true;
    }

    private void Test(String name, boolean daTest){
        System.out.println("---------------------------------------------------");
        System.out.println("Testing "+ name);
        System.out.println("Finish " + (daTest? "Successful" : "Failure"));
        System.out.println("---------------------------------------------------");

        int joker = daTest ? ++passed : ++failed;

    }
} 
