

package Test;

import DataAccess.UsuarioDA;
import domain.ControladorSesion;
import domain.Usuario;

public class UsuarioTesterTS001 {
	
	/**
	 * Testeando si el ID es setteado correctamente
	 * @param u, un objeto Usuario
	 * @param input, El ID a cambiar
	 * @param expected, El ID esperado cuando se realice el setteo. 
	 */
	
	public void tc1SettingGettingID(Usuario u, int input, int expected){
		
		System.out.println("\nTesting setting/getting id!");
		u.setID(input);
		int output = u.getID();
		
		if(output == expected){
			System.out.println("Test passed.");
		}else{
			System.out.println("nTest failed.");
		}
		
		
	}
	
	
	/**
	 * Testea que el usuario y la clave sean correctos
	 * 
	 * @param u, un objeto de la clase  Usuario
	 * @param nameExpected, el nombre esperado una vez se cree el usuario
	 * @param passExpected, la clave esperada una vez se cree el usuario
	 */
	
	public void tc2TestingUsuarioAndPassword(Usuario u, String nameExpected, String passExpected){
		
		System.out.println("\nTesting user and password!");
		String output1 = u.getNombreUsuario();
		String output2 = u.getClave();
		
		if(nameExpected.equals(output1) && passExpected.equals(output2)){
			System.out.println("Test Passed");
		}else{
			System.out.println("Test Failed");
		}
		
	}
	
	/**
	 * Testing setting/getting Rol
	 * @param u, un objeto Usuario
	 * @param inputRol, Nuevo rol a cambiar
	 * @param rolExpected, rol Esperado una vez sea cambiado
	 */
	
	public void tc3SettingGettingRol(Usuario u, String inputRol, String rolExpected){
		
		System.out.println("\nTesting setting/getting a rol!");
		u.setRol(inputRol);
		String output = u.getRol();
		
		if(rolExpected.equals(output)){
			System.out.println("Test Passed\n");
		}else{
			System.out.println("Test Failed\n");
		}	
		
	}
	
	
	public void tc4GettingExistentName(Usuario u, String nameExpected){
		
		System.out.println("\nTesting getting a existent name!");
		String output = u.getNombre();
		
		if(nameExpected.equals(output)){
			System.out.println("Test Passed\n");
		}else{
			System.out.println("Test Failed\n");
		}
		
		
	}
	
	public void tc5SettingUserName(Usuario u, String inputName, String nameExpected){
		
		System.out.println("\nTesting setting a user name!");
		u.setNombre(inputName);
		String output = u.getNombre();
		
		if(nameExpected.equals(output)){
			System.out.println("Test Passed\n");
		}else{
			System.out.println("Test Failed\n");
		}
		
		
	}
	
	
	public void tc1LeerUnUsuario(UsuarioDA uda, Usuario u, int idExpected, String nombreExpected, String rolExpected){
		   uda.leerUsuario(u);
		   System.out.print("\nTesting la creacion de un nuevo usuario!\n");
		   
		   if((u.getID()== idExpected) && (u.getNombre().equals(nombreExpected)) && (u.getRol().equals(rolExpected))){
			   System.out.print("Test Passed\n");
		   }else{
			   System.out.print("Test Failed\n");
		   }
			
		}
		
		
		public void tc2IniciarSesion(ControladorSesion c, String user, String pass,String nomExpected, String rolExpected){
			System.out.print("\nTesting el Inicio De Sesion!\n");
			c.iniciarSesion(user, pass);
			if(nomExpected.equals(c.usuarioActual.getNombre()) && rolExpected.equals(c.usuarioActual.getRol())){
				   System.out.print("Test Passed\n");
			   }else{
				   System.out.print("Test Failed\n");
			   }
		}
		
		public void tc3CrearUsuario(String nomUsuario, String clave, String nomExpected, String claveExpected){
			System.out.print("\nTesting la creacion de un Usuario!\n");
			Usuario nuevo = new Usuario(nomUsuario, clave);
			
			if(nomExpected.equals(nuevo.getNombreUsuario()) && claveExpected.equals(nuevo.getClave())){
				   System.out.print("Test Passed\n");
			   }else{
				   System.out.print("Test Failed\n");
			   }
		}
		
		public void tc4SettingGettingNombreUsuario(Usuario user, String nombre, String nombreExpected){
			System.out.print("\nTesting Setting/Getting  nombre de un Usuario!\n");
			user.setNombre(nombre);
			
			if(nombreExpected.equals(user.getNombre())){
				   System.out.print("Test Passed\n");
			   }else{
				   System.out.print("Test Failed\n");
			   }
			
		}
		
		public void tc5SettingGettingRolUsuario(Usuario user, String rol, String rolExpected){
			System.out.print("\nTesting Setting/Getting  rol de un Usuario!\n");
			user.setRol(rol);
			
			if(rolExpected.equals(user.getRol())){
				   System.out.print("Test Passed\n");
			   }else{
				   System.out.print("Test Failed\n");
			   }
			
		}
	
	
	public static void main(String[]args){
		
		UsuarioTesterTS001 test = new  UsuarioTesterTS001();
		Usuario usuario = new Usuario("Monaliza","12456");
		test.tc1SettingGettingID(usuario,100,100);
		test.tc2TestingUsuarioAndPassword(usuario, "Monaliza", "12456");
		test.tc3SettingGettingRol(usuario, "Supervisor", "Supervisor");
		test.tc4GettingExistentName(usuario, "N/A");
		test.tc5SettingUserName(usuario, "Molina Morillo", "Molina Morillo");
		
		
		Usuario u = new Usuario("pprimero", "1234");
		ControladorSesion con = new ControladorSesion();
		UsuarioDA uda = new UsuarioDA();
		test.tc1LeerUnUsuario(uda, u, 2, "Pablo primero", "Supervisor");
		test.tc2IniciarSesion(con, "pprimero", "1234", "Pablo primero", "Supervisor");
		test.tc3CrearUsuario("alabour", "1234", "alabour", "1234");
		test.tc4SettingGettingNombreUsuario(u, "Pedro", "Pedro");
		test.tc5SettingGettingRolUsuario(u, "Administrador", "Administrador");
	}
	
	
	

}
