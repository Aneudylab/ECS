
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import domain.ControladorUsuario;

public class CrearUsuarioForm extends JFrame{

	private JPanel panelExterior;
	private JPanel panelInterior;
		
	private JLabel lbNombre;
	private JLabel lbRol;
	private JLabel lbNombreUsuario;
	private JLabel lbContrasena;
	private JLabel lbConContrasena;
	private JLabel lbSupervisor;
		
	private JLabel validacion;
	private JLabel etiqueta ;
	private JTextField nombre;
	private JComboBox rol;
	private JPasswordField contrasena;
	private JPasswordField conContrasena;
	private JTextField nombreUsuario;
	private JComboBox supervisor;
	private JButton aceptar;
	private JButton cancelar;
	
    private ControladorUsuario cUsuario;
	HashMap<Integer,String> listaRoles;
	HashMap<Integer,String> listaSups;

	
	public CrearUsuarioForm(){
			
		super("Registro de Usuario");
		this.setLocationRelativeTo(null);
		Container c= this.getContentPane();
		c.setLayout(new BorderLayout());
			
		JPanel panelSuperior = crearPanelSuperior();
		this.add(panelSuperior, BorderLayout.NORTH);
		
		JPanel panelFormulario = crearPanelFormulario();
		JPanel panelContenedorForm = new JPanel(new FlowLayout());
		panelContenedorForm.add(panelFormulario);
		this.add(panelContenedorForm, BorderLayout.CENTER);
		
		JPanel panelBotones = crearPanelBotones();
		this.add(panelBotones, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setResizable(false);
		
        cUsuario = new ControladorUsuario();
	}
	
	private JPanel crearPanelSuperior(){
		
		JPanel panelSuperior = new JPanel(new GridLayout(2,1));
		Font f = new Font("San Serif",Font.PLAIN,20);
		
		etiqueta = new JLabel("Datos Personales", JLabel.CENTER);
		validacion = new JLabel("                ", JLabel.CENTER);
		etiqueta.setFont(f);
		panelSuperior.add(etiqueta);
		panelSuperior.add( validacion, "Center");
		
		return panelSuperior;
	}
	
	private JPanel crearPanelFormulario(){
		
		lbNombre = new JLabel("Nombre",JLabel.LEFT);
		lbNombre.setBounds(50,50,100,200);
		lbRol = new JLabel("Rol",JLabel.LEFT);
		lbNombreUsuario = new JLabel("Usuario", JLabel.LEFT);
		lbContrasena = new JLabel("Clave",JLabel.LEFT);
		lbConContrasena = new JLabel("Confirmar clave",JLabel.LEFT);
		lbSupervisor = new JLabel("Supervisor",JLabel.LEFT);
			
		nombre = new JTextField(15);
		llenarComboBox();
		//String[] roles = {"","Representante","Supervisor","Administrador"};
		//rol= new JComboBox(roles);
		//rol.setMaximumRowCount(3);
		rol.setSelectedItem(-1);
		rol.addItemListener(new RolListener ());
		nombreUsuario = new JTextField(15);
		contrasena = new JPasswordField(15);
		conContrasena = new JPasswordField(15);
		//String[] listSup = {"","Juan","Antonio","Luis"};
		//supervisor = new JComboBox(listSup);
		//supervisor.setName("SupervisorID");
	
		
					
		panelExterior = new JPanel();
		panelExterior.setLayout(new GridLayout(6,2,30,10));
		panelExterior.setSize(250,300);
		
		panelExterior.add(lbNombre);
		panelExterior.add(nombre);
		panelExterior.add(lbRol);
		panelExterior.add(rol);
		panelExterior.add(lbNombreUsuario);
		panelExterior.add(nombreUsuario);
		panelExterior.add(lbContrasena);
		panelExterior.add(contrasena);
		panelExterior.add(lbConContrasena);
		panelExterior.add(conContrasena);
		panelExterior.add(lbSupervisor);
		panelExterior.add(supervisor);
		
		return panelExterior;
	
	}
	
	public JPanel crearPanelBotones(){
	
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		
		aceptar= new JButton ("Guardar");
		aceptar.addActionListener(new SubmitListener());
		panelBotones.add(aceptar);
		
		cancelar= new JButton ("Cancelar");
		cancelar.addActionListener(new Cancelar());
		panelBotones.add(cancelar);
		
		return panelBotones;
	
	}
	
	public class RolListener implements ItemListener{
	
		public void itemStateChanged(ItemEvent ae){
				
			JComboBox jc = (JComboBox) ae.getSource();
			String sOpcion = jc.getSelectedItem().toString();
			JPanel panel = (JPanel)jc.getParent();

			if (sOpcion.equals("Representante"))
			{
		  
	  		   for(Component tmpComp : panel.getComponents()){
			      if (tmpComp instanceof JComboBox)
				  {	
                     JComboBox jc2 = (JComboBox) tmpComp;
				     if (jc2!=jc)
					   jc2.setEnabled(true);
					 
				  }
			   }   
			}
			else{
				for(Component tmpComp : panel.getComponents()){
			      if (tmpComp instanceof JComboBox)
				  {
                     JComboBox jc2 = (JComboBox) tmpComp;
				     if (jc2!=jc)
					 {
					   jc2.setSelectedIndex(0);
					   jc2.setEnabled(false);
					 }
				   }
			   }   
			}

		}
	
	}
	
	public class SubmitListener implements ActionListener{
	
		public void actionPerformed(ActionEvent a){
		
		 if ( validarFormulario()){
			int id = registrarUsuario();
			JOptionPane.showMessageDialog(null,
			                              "El Usuario ID [" + id + "] fue registrado",
										  "Usuario Registrado Exitosamente",
										  JOptionPane.INFORMATION_MESSAGE
										  );
		 }
		
		}
	}

	public class Cancelar implements ActionListener{
	
		public void actionPerformed(ActionEvent a){
		    ocultar();		
		}
	}
    
	private int registrarUsuario(){
	
     	String nom = nombre.getText();
		int rolID = rol.getSelectedIndex();
		String clave = contrasena.getPassword().toString();
		String nomUsr = nombreUsuario.getText();
		int idSuper = supervisor.getSelectedIndex();
		
        int id = crearUsuario(nom,rolID,clave,nomUsr,idSuper);		 

		return id;
	}
	
	private boolean validarFormulario(){
		
		boolean valido = true;
		char [] con = contrasena.getPassword();
		char [] con2 = conContrasena.getPassword();
		
		if(nombre.getText().equals("") || nombreUsuario.getText().equals("") || 
		   contrasena.getText().equals("")||conContrasena.getText().equals("")){
			
			darError(1);
			valido = false;
		}
		else if( !Arrays.equals(con,con2)){
			darError(2);
			valido = false;
		}
		else if(rol.getSelectedItem()== null || rol.getSelectedItem().equals("")){
			darError(3);
			valido = false;
		}
		else if(rol.getSelectedItem()=="Representante" && supervisor.getSelectedItem()==""){
			darError(4);
			valido = false;
		}	
		else {
			valido = true;
		}
		
		return valido;
	 
	}
	
	private void darError(int num){
	
	   switch (num)
	   {
	      case 1: 
	            JOptionPane.showMessageDialog(null,
						              "Todos los campos deben estar completos",
						              "Error de campos incompletos", 
									  JOptionPane.ERROR_MESSAGE);
				break;
		  case 2:
	            JOptionPane.showMessageDialog(null,
						              "Las contrasenas no coinciden",
						              "Error de contrasenas", 
									  JOptionPane.ERROR_MESSAGE);
				break;

		  case 3:
	            JOptionPane.showMessageDialog(null,
						              "Tiene que asignar un rol",
						              "Error usuario sin rol", 
									  JOptionPane.ERROR_MESSAGE);
				break;
				
		  case 4:
	            JOptionPane.showMessageDialog(null,
						              "Debe selecionar un supervisor para este representante",
						              "Error de representante sin supervisor", 
									  JOptionPane.ERROR_MESSAGE);
				break;
				
		  default:
	            JOptionPane.showMessageDialog(null,
						              "Consulte con su administrador",
						              "Error", 
									  JOptionPane.ERROR_MESSAGE);
				break;

		}
		
	}
	
	private void ocultar(){
		MainWindow main = new MainWindow();
		main.mostrar();
		this.dispose();
    }
	
	  //Metodo para cargar las lista de los combox
   public void llenarComboBox(){
  
      listaSups = new HashMap<Integer,String> ();
	  listaSups.put(0,"");
      listaSups.put(1,"Jose");	  
	  listaSups.put(2,"Antonio");
	  listaSups.put(3,"Vargas");
	  // new ControladorUsuario.obtenerListaSupervisores();
	   
	  listaRoles = new HashMap<Integer,String>(); 
	  listaRoles.put(0,"");
      listaRoles.put(1,"Representante");	  
	  listaRoles.put(2,"Supervisor");
	  listaRoles.put(3,"Administrador");
	  //   new ControladorUsuario.obtenerListaRoles();
      
	  ArrayList<String> tmpSuper = new ArrayList<String>(listaSups.values());
	  ArrayList<String> tmpRol = new ArrayList<String>(listaRoles.values());
	  
	  supervisor = new JComboBox(tmpSuper.toArray());
	  supervisor.setEnabled(false);
	  rol = new JComboBox(tmpRol.toArray());
	  
   }
   
   //Metodo que se ejecutara a la hora de crear el usuario
   public int crearUsuario(String usr,int rol, String clave,
                             String nomUsr, int idSuper){
	
	  ControladorUsuario unUsrCont = new ControladorUsuario();
	
	  int idUsr = unUsrCont.crearUsuario(usr,rol,clave,nomUsr,idSuper);
	
	  return idUsr;
	}
	
	public void mostrar(){
	   this.setVisible(true);
	}
/*	
 	
	public static void main(String [] args){

		CrearUsuarioForm tm = new CrearUsuarioForm ();
		tm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tm.setSize(400,400);
		tm.setVisible(true);

    }\
*/
}
