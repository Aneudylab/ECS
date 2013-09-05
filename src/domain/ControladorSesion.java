package domain;

import javax.swing.JOptionPane;

import DataAccess.UsuarioDA;
import view.MainWindow;

public class ControladorSesion {

	private String nombre = "";
	private String clave = "";

	public void iniciarSesion(String nombreUsuario, String Clave) {

		Usuario usuario = new Usuario(nombreUsuario, Clave);
		UsuarioDA usuarioDA = new UsuarioDA();

		if (usuarioDA.leerUsuario(usuario) == true) {
			MainWindow m = new MainWindow(usuario.getNombreUsuario());
			m.setVisible(true);
		} else {

			JOptionPane.showMessageDialog(null, "Clave o Password incorrecto,",
					"Error de acceso", JOptionPane.ERROR_MESSAGE);
		}
	}

	// -----------------------------------------------------------------
	// Setters y Getters
	// -----------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

}
