package domain;

import javax.swing.JOptionPane;

import DataAccess.UsuarioDA;
import view.MainWindow;

public class ControladorSesion {

    public static Usuario usuarioActual;
	private String nombre = "";
	private String clave = "";

	public void iniciarSesion(String nombreUsuario, String Clave) {

        Usuario usu = new Usuario(nombreUsuario, Clave);
		UsuarioDA usuarioDA = new UsuarioDA();
        usuarioDA.leerUsuario(usu);

        /* 
         * Antes de llamar esta función el usuario actual se vuelve null
         * Esto asegura que si la autenticación falla no habrá usuario actual.
         */
        ControladorSesion.usuarioActual = null;

        if(usu.getID() != 0){
            ControladorSesion.usuarioActual = usu;
		}
	}

}
