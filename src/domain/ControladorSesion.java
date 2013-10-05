package domain;

import javax.swing.JOptionPane;

import DataAccess.UsuarioDA;
import view.MainWindow;

public class ControladorSesion {

    public static Usuario usuarioActual;

	public void iniciarSesion(String nombreUsr, String clave) {

		setUsuarioActual(null);
		UsuarioDA usuarioDA = new UsuarioDA();
        Usuario unUsuario = usuarioDA.leerUsuario(nombreUsr,clave);

        if(unUsuario != null){
           setUsuarioActual(unUsuario);
		}
	}
	
	//metodo de establecer el usuario
	public void setUsuarioActual(Usuario unUsr){
	    usuarioActual = unUsr;
	}
	
	// Metodo booleano que retorna True si el idRol es 1
	public static boolean ValidarEsAdministrador(){
        return usuarioActual.getRol().equals("Administrador") ;
	}
    
	
	public Usuario getUsuarioActual(){
		return usuarioActual;
	}
}
