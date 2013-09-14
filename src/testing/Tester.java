package testing;

import DataAccess.UsuarioDA;
import domain.ControladorSesion;
import domain.Usuario;
import testing.testsuite.*;

public class Tester{
    public static void main(String[] args){
        // Instanciando TestSuits
        UnitTestTS001 ts001 = new UnitTestTS001();

        // Instanciando Objetos para precondiciones
        Usuario u = new Usuario("pprimero", "1234");
        UsuarioDA uda = new UsuarioDA();
        ControladorSesion cs = new ControladorSesion();

        // Instanciando objetos para parámetros
        Usuario umal = new Usuario("nousr", "nopwd");
        Usuario ubien = new Usuario("pprimero", "1234");

        // Corriendo pruebas
        ts001.tc1GettingNombreUsuario(u, "pprimero");
        ts001.tc2GettingClave(u, "1234");
        ts001.tc3SettingGettingUsuarioId(u, 2, 2);
        ts001.tc4SettingGettingNombre(u, "Pablo primero", "Pablo primero");
        ts001.tc5SettingGettingRol(u, "Supervisor", "Supervisor");
        ts001.tc6IdUsrLeerUsuarioMalAuth(uda, umal, 0);
        ts001.tc7IdUsrLeerUsuarioBienAuth(uda, u, 2);
        ts001.tc8UsrActualLoginMal(cs, "nousr", "nopwd");
        ts001.tc9UsrActualLoginBien(cs, "pprimero", "1234");
        ts001.tc10IdUsrActualLoginBien(cs, "pprimero", "1234", 2); 
        ts001.tc11nomUsrActualLoginBien(cs, "pprimero", "1234", "Pablo primero"); 
        ts001.tc12rolUsrActualLoginBien(cs, "pprimero", "1234", "Supervisor"); 
        
    }
}
