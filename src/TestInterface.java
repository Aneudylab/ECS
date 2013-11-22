import domain.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class TestInterface extends BaseTest{
    @Override
    protected void RunTests() {
        //TestLogin();
        TestControlEv();
    }

    private void TestLogin() {
        ControladorSesion cSesion = new ControladorSesion();
        Usuario usr = new Usuario();

        Test("000 Before Login Usr is Null", cSesion.getUsuarioActual() == null);

        // Bad login
        cSesion.iniciarSesion("abcd", "edfg");
        Test("001 BadLogin Usr is Null", cSesion.getUsuarioActual() == null);

        // Good login [Admin]
        cSesion.iniciarSesion("alabour", "1234");
        Test("002 GoodLogin Usr is not Null", cSesion.getUsuarioActual() != null);
        Test("003 isAdmin for an Admin", cSesion.validarEsAdministrador() );
        Test("004 not isSup for an Admin", !cSesion.validarEsSupervisor() );
        Test("005 not isRep for an Admin", !cSesion.validarEsRepresentante() );

        // Good login [Sup]
        cSesion.iniciarSesion("pprimero", "1234");
        Test("002 GoodLogin Usr is not Null", cSesion.getUsuarioActual() != null);
        Test("003 not isAdmin for an Admin", !cSesion.validarEsAdministrador() );
        Test("004 isSup for an Admin", cSesion.validarEsSupervisor() );
        Test("005 not isRep for an Admin", !cSesion.validarEsRepresentante() );

        // Good login [Rep]
        cSesion.iniciarSesion("ptercero", "1234");
        Test("002 GoodLogin Usr is not Null", cSesion.getUsuarioActual() != null);
        Test("003 not isAdmin for an Admin", !cSesion.validarEsAdministrador() );
        Test("004 not isSup for an Admin", !cSesion.validarEsSupervisor() );
        Test("005 isRep for an Admin", cSesion.validarEsRepresentante() );
    }

    private void TestControlEv() {
        Map<Integer, String> listaEv = new HashMap<Integer, String>();
        List<Map<String , String>> ev ;
        ControladorEvaluacion cEv = new ControladorEvaluacion();
        ControladorSesion cSec = new ControladorSesion();
        HashMap<Integer, String> rec = new HashMap<Integer, String>();

        cSec.iniciarSesion("alabour", "1234");
        listaEv = cEv.obtenerListaEvaluaciones();
        Test("001 No Evs for Admins", listaEv.size() == 0);

        cSec.iniciarSesion("pcuarto", "1234");
        listaEv = cEv.obtenerListaEvaluaciones();
        Test("002 1 Evs for pcuarto", listaEv.size() == 1);

        cSec.iniciarSesion("ptercero", "1234");
        listaEv = cEv.obtenerListaEvaluaciones();
        Test("003 2 Evs for ptercero", listaEv.size() == 2);

        for (Map.Entry entry : listaEv.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }

        ev = cEv.obtenerEvaluacion(7);

        Test("004 Ev has 3 ptos", ev.size() == 3);
        
        for (Map<String , String> pto : ev) {
            System.out.print("| " + pto.get("id") + "  ");
            System.out.print("|  " + pto.get("pto") + "  ");
            System.out.print("|  " + pto.get("resp") + " |");
            System.out.println();
        }

        ev = cEv.obtenerEvaluacion(1);

        for (Map<String , String> pto : ev) {
            System.out.print("| " + pto.get("id") + "  ");
            System.out.print("|  " + pto.get("pto") + "  ");
            System.out.print("|  " + pto.get("resp") + " |");
            System.out.println();
        }

        rec.put(2, "Entiendo que está mal evaluada");

        cEv.guardarRevision(7, rec);
    }
}
