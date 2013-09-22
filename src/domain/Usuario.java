package domain;


public class Usuario {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private int usuarioID;
	private String nombreUsuario;
	private String clave;
	private String nombre;
	private Rol rol;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	public Usuario(){}
	
	public Usuario(String usu, String cla) {

		usuarioID = 0;
		nombreUsuario = usu;
		clave = cla;
		nombre = "N/A";
		rol = null;

	}

	public Usuario(String nom, String cla, Rol usrRol) {

		nombre = nom;
		clave = cla;
		rol = usrRol;

	}

	public Usuario(String nombreUsu,String cla,String nom, Rol ro){
 
       nombreUsuario = nombreUsu;
	   clave = cla;
	   nombre = nom;
	   rol = rol;
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

	public Rol getRol() {
		return rol;
	}
	
	public void setID(int usuarioID2) {
		usuarioID = usuarioID2;
	}
	
	public void setNombre(String nombre2){
	   nombre = nombre2;
	}
	
	public void setRol(Rol rol2){
	   rol = rol2;
	}

}
