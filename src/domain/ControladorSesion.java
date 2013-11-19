package domain;

import DataAccess.UsuarioDA;

public class ControladorSesion {

    private static Usuario usuarioActual;

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
	public boolean validarEsAdministrador(){
        return usuarioActual.getRol().equals("Administrador");
	}
    	
	public Usuario getUsuarioActual(){
		return usuarioActual;
	}
	
	public boolean validarEsSupervisor(){
        return usuarioActual.getRol().equals("Supervisor");
	}
	
	public boolean validarEsRepresentante(){
	    return usuarioActual.getRol().equals("Representante");
	}
}
