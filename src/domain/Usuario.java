
package domain;


public class Usuario {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private int idUsuario;
	private String nombreUsuario;
	private String clave;
	private String nombre;
	private Rol rol;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	public Usuario(){}
	
	public Usuario(String nombreUsu, String cla) {

		idUsuario = 0;
		nombreUsuario = nombreUsu;
		clave = cla;
		nombre = "N/A";
		rol = null;

	}

	public Usuario(int usrID,String nom){
	    idUsuario = usrID;
	    nombre = nom;
	}
	
	public Usuario(int id, String usrNom, Rol usrRol){
		idUsuario = id;
		nombre = usrNom;
		rol = usrRol;	
	}
	
    public Usuario(String nombre, Rol rol, String clave, String nombreUsuario) { 
		this.nombre = nombre;
		this.rol = rol;
		this.clave = clave;
		this.nombreUsuario = nombreUsuario;
    }

	// -----------------------------------------------------------------
	// Setters y Getters
	// -----------------------------------------------------------------

	public int getId() {
		return idUsuario;
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
		 return rol.getDescripcion();
	}
	
	public int getIdRol(){
	   return rol.getId();
	}
	
	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setNombre(String nombre2){
	   nombre = nombre2;
	}
	
	public void setRol(Rol rol2){
	   rol = rol2;
	}

   /////////////////////////////////
   //Otros metodos
   //////////////////////////////////
    public void copiar(Usuario usr){
        idUsuario = usr.getId();
        nombre = usr.getNombre();
    }
}
