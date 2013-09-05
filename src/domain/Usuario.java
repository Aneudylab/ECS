package domain;

public class Usuario {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private int usuarioID;
	private String nombreUsuario;
	private String clave;
	private String nombre;
	private String rol;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	public Usuario(){}
	
	public Usuario(String usu, String cla) {

		usuarioID = 0;
		nombreUsuario = usu;
		clave = cla;
		nombre = "N/A";
		rol = "N/A";

	}

	
	// -----------------------------------------------------------------
	// Setters y Getters
	// -----------------------------------------------------------------

	public int getID() {
		return usuarioID;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRol() {
		return rol;
	}

	
	public void setID(int usuarioID) {
		this.usuarioID = usuarioID;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    // el rol a establecer
	 
	public void setRol(String rol) {
		this.rol = rol;
	}

}
