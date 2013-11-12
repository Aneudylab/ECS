import java.util.Date;
import java.text.SimpleDateFormat;
public abstract class BaseTest{
    protected int passed;
    protected int failed;

    public BaseTest(){
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

    protected void Test(String name, boolean daTest){
        System.out.println("---------------------------------------------------");
        System.out.println("Testing "+ name);
        System.out.println("Finish " + (daTest? "Successful" : "Failure"));
        System.out.println("---------------------------------------------------");

        int joker = daTest ? ++passed : ++failed;

    }

    protected Date newFecha(String f) {
        Date d = null;
        try{
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");

            d = sdf.parse(f);
        }
        catch(Exception e){
        }

        return d;
    }

    abstract protected void RunTests();
} 
