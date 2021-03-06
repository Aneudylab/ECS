package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import domain.ControladorSesion;



public class MainWindow extends JFrame implements ActionListener {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	private static final String CREAR_PLANTILLA = "crearPantillaEvaluacion";
	private static final String EVALUAR_REPRESENTANTE = "evaluarRepresentante";
	private static final String ACTUALIZAR_EVALUACION = "actualizarEvaluacion";
	private static final String REVISAR_EVALUACION = "revisarEvaluacion";
	private static final String CREAR_USUARIO = "crearUsuario";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	private PanelImagen panelImagen;
	// -----------------------------------------------------------------
	// Atributos de la Interfaz
	// -----------------------------------------------------------------

	private JButton btnCrearPlantillaEvaluacion;
	private JButton btnEvaluarReprecentante;
	private JButton btnActualizarEvaluacion;
	private JButton btnRevisarEvaluacion;
	private JButton btnCrearUsuarios;
	private JLabel 	lblBienvenido;

	// -----------------------------------------------------------------
	// Controladores
	// -----------------------------------------------------------------
    private ControladorSesion cSesion;

	public MainWindow() {
        // Controlador
        cSesion = new ControladorSesion();

		setSize(790, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setTitle("Sistema de Evaluacion  ");
		setResizable(true);
		setLocationRelativeTo(null);

		panelImagen = new PanelImagen();
		GridBagConstraints gbc = new GridBagConstraints();
		// orientacion del Layout
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		gbc.gridx = 0;
		// Definir espacio entre componentes
		gbc.insets = new Insets(5, 18, 5, 18);
		add(panelImagen, gbc);
		
		btnCrearPlantillaEvaluacion = new JButton("Crear Plantilla    ", null);
		btnCrearPlantillaEvaluacion.setBounds(30, 100, 90, 30);
		gbc.gridy = 2;
		gbc.insets = new Insets(5, 18, 5, 18);
		btnCrearPlantillaEvaluacion.setActionCommand(CREAR_PLANTILLA);
		btnCrearPlantillaEvaluacion.addActionListener(this);
		add(btnCrearPlantillaEvaluacion, gbc);

		
		btnEvaluarReprecentante = new JButton("Evaluar Representante ");
		btnEvaluarReprecentante.setBounds(100, 100, 100, 100);
		gbc.gridy = 4;
		btnEvaluarReprecentante.setActionCommand(EVALUAR_REPRESENTANTE);
		btnEvaluarReprecentante.addActionListener(this);
		add(btnEvaluarReprecentante, gbc);

		btnActualizarEvaluacion = new JButton("Actualizar Evaluacion ");
		btnActualizarEvaluacion.setBounds(100, 100, 100, 100);
		gbc.gridy = 5;
		btnActualizarEvaluacion.setActionCommand(ACTUALIZAR_EVALUACION);
		btnActualizarEvaluacion.addActionListener(this);
		add(btnActualizarEvaluacion, gbc);

		btnRevisarEvaluacion = new JButton("Revisar Evaluacion ");
		btnRevisarEvaluacion.setBounds(100, 100, 100, 100);
		gbc.gridy = 6;
		btnRevisarEvaluacion.setActionCommand(REVISAR_EVALUACION);
		btnRevisarEvaluacion.addActionListener(this);
		add(btnRevisarEvaluacion, gbc);

		btnCrearUsuarios = new JButton("Crear Usuario");
		btnCrearUsuarios.setBounds(100, 100, 100, 100);
		gbc.gridy = 7;
		btnCrearUsuarios.setActionCommand(CREAR_USUARIO);
		btnCrearUsuarios.addActionListener(this);
		add(btnCrearUsuarios, gbc);
		
		lblBienvenido = new JLabel("Usuario	: "+ cSesion.getUsuarioActual().getNombre() + " " + cSesion.getUsuarioActual().getRol());
		gbc.gridy = 8;
		add(lblBienvenido, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Apendice de metodo generado automaticamente
		String comando = evento.getActionCommand();

		if (CREAR_PLANTILLA.equals(comando)) {
			CrearNuevaPlantilla();
		}
		if (EVALUAR_REPRESENTANTE.equals(comando)) {
			evaluarRepresentante();
		}
		if (ACTUALIZAR_EVALUACION.equals(comando)) {
			actualizarEvaluacion(); 
		}
		if (REVISAR_EVALUACION.equals(comando)) {
			revisarEvaluacion();
		}
		if (CREAR_USUARIO.equals(comando)) {
            crearNuevoUsuario();
		}

	}

	private void crearNuevoUsuario (){
	   
	   boolean esAdmin = cSesion.validarEsAdministrador();
	   
	   if(esAdmin){
	      CrearUsuarioForm usuarioForm = new CrearUsuarioForm();
		  usuarioForm.mostrar();
		  ocultar();
		  
	   }
	   else{
	      JOptionPane.showMessageDialog(null,
						              "Usted no tiene privilegios para crear nuevos usuarios",
						               "Usuario sin privilegio", 
									   JOptionPane.ERROR_MESSAGE);	
	   }
	}
	
	private void CrearNuevaPlantilla (){
	   
	   boolean esAdmin = cSesion.validarEsAdministrador();
	   
	   if(esAdmin){
	      CrearPlantillaForm plantilla = new CrearPlantillaForm(cSesion.getUsuarioActual().getId());
		  plantilla.mostrar();
		  ocultar();
	   }
	   else{
	      JOptionPane.showMessageDialog(null, "Usted no tiene privilegios para crear plantilla",
	                                          "Usuario sin privilegio", 
	                                          JOptionPane.ERROR_MESSAGE);	
	   }
	}
	private void evaluarRepresentante(){
		boolean esSup  = cSesion.validarEsSupervisor();
		
		if(esSup){
			EvaluarRepresentanteForm evaluar = new EvaluarRepresentanteForm();
			evaluar.mostrar();
			ocultar();
			
		}else{
			JOptionPane.showMessageDialog(null, 
			                              "Usted no tiene privilegios para evaluar representantes",
			                              "Usuario sin privilegio", 
					              JOptionPane.ERROR_MESSAGE);	
		}
	
	}
	
	// Invoca la ventana de actualizacion
	private void actualizarEvaluacion(){
	    boolean esSup = cSesion.validarEsSupervisor();

	    if(esSup){
	    	ActualizarEvaluacionForm actualizar = new ActualizarEvaluacionForm();
	        actualizar.mostrar();
	        this.ocultar();
	    }
	    else{
	    	JOptionPane.showMessageDialog(null,
		                               "Usted no tiene privilegios para actualizar evaluaciones",
		                               "Usuario sin privilegio", 
					       JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	// Invoca la ventana de Revision
	private void revisarEvaluacion(){
	    boolean esRep = cSesion.validarEsRepresentante();

	    if(esRep){
	    	RevisarEvaluacionForm revisar = new RevisarEvaluacionForm();
	        revisar.mostrar();
	        this.ocultar();
	    }
	    else{
	    	JOptionPane.showMessageDialog(null,
		                               "Usted no tiene privilegios para revisar evaluaciones",
		                               "Usuario sin privilegio", 
					       JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	private void ocultar(){
	   this.setVisible(false);
    }
	
	
	// Metodo que hace visible la ventana principal
	public void mostrar(){
	   this.setVisible(true);
	}
}

