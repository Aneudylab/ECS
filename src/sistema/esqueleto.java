package sistema;

import view.IniciarSesionForm;

public class Esqueleto {
	
	public static void main(String[] args) {
		 try
	        {
			 IniciarSesionForm interfaz = new IniciarSesionForm();
	            interfaz.setVisible( true );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
		
	}

}
