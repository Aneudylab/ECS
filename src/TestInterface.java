import domain.*;
import java.util.Map;
import java.util.HashMap;

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
        ControladorEvaluacion cEv = new ControladorEvaluacion();
        ControladorSesion cSec = new ControladorSesion();

        cSec.iniciarSesion("alabour", "1234");
        listaEv = cEv.obtenerListaEvaluaciones();
        Test("001 No Evs for Admins", listaEv.size() == 0);

        cSec.iniciarSesion("pcuarto", "1234");
        listaEv = cEv.obtenerListaEvaluaciones();
        Test("002 1 Evs for pcuarto", listaEv.size() == 2);
    }
}
