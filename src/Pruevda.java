import domain.Evaluacion;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Pruevda{
    public void Run() throws ParseException{
        System.out.println("Running...");
            RunTests();
            System.out.println("Test Completed");
    }

    private void RunTests() throws ParseException {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        Evaluacion ev = new Evaluacion(1, sdf.parse("20/05/2008"));

        testEvaluacionId(ev, 1);
        testEvaluacionDate(ev , sdf.parse("20/05/2008"));
    }

    private boolean testEvaluacionId(Evaluacion obj, int output){
        System.out.println("------------------------------");
        System.out.println("Testing Evaluacion.Id...");

        boolean testResult = output == obj.getId();

    
        System.out.println("Finish " + (testResult? "Successful" : "Failure"));
        System.out.println("------------------------------");
        return true;
    }

    private boolean testEvaluacionDate(Evaluacion obj, Date output){
        System.out.println("------------------------------");
        System.out.println("Testing Evaluacion.Date...");

        boolean testResult = output.compareTo(obj.getFechaCreada()) == 0;

    
        System.out.println("Finish " + (testResult? "Successful" : "Failure"));
        System.out.println("------------------------------");
        return true;
    }
} 
