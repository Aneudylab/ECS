package testing.testsuite;

import domain.Usuario;
import DataAccess.UsuarioDA;
import domain.ControladorSesion;

public class UnitTestTS001{

    public void tc1GettingNombreUsuario(Usuario u, String nombreUsuarioEsperado){
        String tc = "Getter para el atributo nombreUsuario de la clase Usuario";
        System.out.print("\nProbando: \n" + tc + "...\n");

        String outputValue = u.getNombreUsuario();

        if(nombreUsuarioEsperado.equals(outputValue)){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + nombreUsuarioEsperado);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc2GettingClave(Usuario u, String claveEsperada){
        String tc = "Getter para el atributo clave de la clase Usuario";
        System.out.print("\nProbando: \n" + tc + "...\n");

        String outputValue = u.getClave();

        if(claveEsperada.equals(outputValue)){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + claveEsperada);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc3SettingGettingUsuarioId(Usuario u, int usuarioIdEntrado, int usuarioIdEsperado){
        String tc = "Setter / Getter para el atributo usuarioID de la clase Usuario";
        System.out.print("\nProbando: \n" + tc + "...\n");

        u.setID(usuarioIdEntrado);

        int outputValue = u.getID();

        if(usuarioIdEsperado == outputValue){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + usuarioIdEsperado);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc4SettingGettingNombre(Usuario u, String nombreEntrado, String nombreEsperado){
        String tc = "Setter / Getter para el atributo nombre de la clase Usuario";
        System.out.print("\nProbando: \n" + tc + "...\n");

        u.setNombre(nombreEntrado);

        String outputValue = u.getNombre();

        if(nombreEsperado.equals(outputValue)){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + nombreEsperado);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc5SettingGettingRol(Usuario u, String rolEntrado, String rolEsperado){
        String tc = "Setter / Getter para el atributo rol de la clase Usuario";
        System.out.print("\nProbando: \n" + tc + "...\n");

        u.setRol(rolEntrado);

        String outputValue = u.getRol();

        if(rolEsperado.equals(outputValue)){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + rolEsperado);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc6IdUsrLeerUsuarioMalAuth(UsuarioDA uda, Usuario usuarioEntrado, int idEsperado){
        String tc = "Propiedad usuarioID al leer Usuario con credenciales inválidos.";
        System.out.print("\nProbando: \n" + tc + "...\n");

        uda.leerUsuario(usuarioEntrado);

        int outputValue = usuarioEntrado.getID();

        if(idEsperado == outputValue){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + idEsperado);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc7IdUsrLeerUsuarioBienAuth(UsuarioDA uda, Usuario usuarioEntrado, int idEsperado){
        String tc = "Propiedad usuarioID al leer Usuario con credenciales válidos.";
        System.out.print("\nProbando: \n" + tc + "...\n");

        uda.leerUsuario(usuarioEntrado);

        int outputValue = usuarioEntrado.getID();

        if(idEsperado == outputValue){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " + outputValue + " no coincide con " + idEsperado);
        }
        System.out.print("\n==========================================\n");
    }

    public void tc8UsrActualLoginMal(ControladorSesion cs, String nomUsrEntrado, String claveEntrada){
        String tc = "ControladorSesion.usuarioActual al iniciar sesión credenciales inválidos";
        System.out.print("\nProbando: \n" + tc + "...\n");

        cs.iniciarSesion(nomUsrEntrado, claveEntrada);
        Usuario outputValue = cs.usuarioActual;

        if(null == outputValue){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " +nomUsrEntrado + " y " + claveEntrada+ " son credenciales válidas");
        }
        System.out.print("\n==========================================\n");
    }

    public void tc9UsrActualLoginBien(ControladorSesion cs, String nomUsrEntrado, String claveEntrada){
        String tc = "ControladorSesion.usuarioActual al iniciar sesión credenciales válidos";
        System.out.print("\nProbando: \n" + tc + "...\n");

        cs.iniciarSesion(nomUsrEntrado, claveEntrada);
        Usuario outputValue = cs.usuarioActual;

        if(null != outputValue){
            System.out.print("Pasó prueba");
        }
        else{
            System.out.print("Falló prueba: " +nomUsrEntrado + " y " + claveEntrada+ " son credenciales inválidas");
        }
        System.out.print("\n==========================================\n");
    }

    public void tc10IdUsrActualLoginBien(ControladorSesion cs, String nomUsrEntrado, String claveEntrada, int idEsperado){
        String tc = "IdUsuario de usuarioActual al iniciar sesión credenciales válidos";
        System.out.print("\nProbando: \n" + tc + "...\n");

        cs.iniciarSesion(nomUsrEntrado, claveEntrada);
        try{
            int outputValue = cs.usuarioActual.getID();

            if(idEsperado == outputValue){
                System.out.print("Pasó prueba");
            }
            else{
                System.out.print("Falló prueba: " + outputValue + " no coincide con " + idEsperado);
            }
        }
        catch(Exception e){
            System.out.println("Ocurrió un error al intentar hacer la prueba");
            System.out.println(e.getMessage());
        }

        System.out.print("\n==========================================\n");
    }

    public void tc11nomUsrActualLoginBien(ControladorSesion cs, String nomUsrEntrado, String claveEntrada, String nombreEsperado){
        String tc = "nombre de usuarioActual al iniciar sesión credenciales válidos";
        System.out.print("\nProbando: \n" + tc + "...\n");

        cs.iniciarSesion(nomUsrEntrado, claveEntrada);

        try{
            String outputValue = cs.usuarioActual.getNombre();

            if(nombreEsperado.equals(outputValue)){
                System.out.print("Pasó prueba");
            }
            else{
                System.out.print("Falló prueba: " + outputValue + " no coincide con " + nombreEsperado);
            }
        }
        catch(Exception e){
            System.out.println("Ocurrió un error al intentar hacer la prueba");
            System.out.println(e.getMessage());
        }

        System.out.print("\n==========================================\n");
    }
    public void tc12rolUsrActualLoginBien(ControladorSesion cs, String nomUsrEntrado, String claveEntrada, String rolEsperado){
        String tc = "rol de usuarioActual al iniciar sesión credenciales válidos";
        System.out.print("\nProbando: \n" + tc + "...\n");

        cs.iniciarSesion(nomUsrEntrado, claveEntrada);
        try{
            String outputValue = cs.usuarioActual.getRol();

            if(rolEsperado.equals(outputValue)){
                System.out.print("Pasó prueba");
            }
            else{
                System.out.print("Falló prueba: " + outputValue + " no coincide con " + rolEsperado);
            }
        }
        catch(Exception e){
            System.out.println("Ocurrió un error al intentar hacer la prueba");
            System.out.println(e.getMessage());
        }

        System.out.print("\n==========================================\n");
    }
}
