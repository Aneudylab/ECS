import DataAccess.RespuestaDA;
public class TestRespDA extends BaseTest{
    @Override
    protected void RunTests(){
        RespuestaDA rda = new RespuestaDA();

        rda.reclamarRespuesta(1, 1, 1, "Prueba");
        rda.reclamarRespuesta(1, 1, 3, "Otra Prueba");

    }

} 
