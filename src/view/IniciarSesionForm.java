package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.ControladorSesion;
import domain.Usuario;

public class IniciarSesionForm extends JFrame implements ActionListener {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	private static final String INICIAR_SESION = "iniciarsesion";
	private static final String CANCELAR = "cancelarsesion";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private Usuario usuario;

	// -----------------------------------------------------------------
	// Atributos de la Interfaz
	// -----------------------------------------------------------------

	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JLabel lblTitulo;
	private JLabel lblEspacio;
	private JLabel lblEspacio2;
	private JButton btnIniciarSesion;
	private JButton btnCancelar;

	// -----------------------------------------------------------------
	// Controladores
	// -----------------------------------------------------------------
    ControladorSesion cSesion;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el panel e inicializa sus componentes
	 */
	public IniciarSesionForm() {
		super("Inicar Sesion");

        cSesion = new ControladorSesion();

		setSize(400, 150);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblUsuario = new JLabel("Nombre: ");
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		add(lblUsuario, gbc);

		txtUsuario = new JTextField("", 12);
		gbc.gridx = 1;
		add(txtUsuario, gbc);

		lblClave = new JLabel("Clave: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblClave, gbc);

		txtClave = new JPasswordField("", 12);
		txtClave.addKeyListener(new PresionarTecla());
		gbc.gridx = 1;
		add(txtClave, gbc);

		lblEspacio = new JLabel("      ");
		gbc.gridx = 3;
		gbc.gridy = 0;
		add(lblEspacio, gbc);

		lblEspacio2 = new JLabel("      ");
		gbc.gridx = 3;
		gbc.gridy = 2;
		add(lblEspacio2, gbc);

		btnIniciarSesion = new JButton("Iniciar ");
		gbc.gridx = 4;
		gbc.gridy = 0;
		btnIniciarSesion.setActionCommand(INICIAR_SESION);
		btnIniciarSesion.addActionListener(this);
		add(btnIniciarSesion, gbc);

		btnCancelar = new JButton("Cancelar ");
		gbc.gridx = 4;
		gbc.gridy = 2;
		btnCancelar.setActionCommand(CANCELAR);
		btnCancelar.addActionListener(this);
		add(btnCancelar, gbc);

		setResizable(false);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent evento) {

        if (evento.getActionCommand() == INICIAR_SESION){
            String usr = txtUsuario.getText();
            String pwd = new String(txtClave.getPassword());

            IniciarSesion(usr, pwd);
        }

		if (evento.getActionCommand() == CANCELAR) {
			System.exit(0);
		}

	}

	// Inner clase para poder iniciar sesion precionando la tecla enter
    protected class PresionarTecla extends KeyAdapter {

        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER){
                String usr = txtUsuario.getText();
                String pwd = new String(txtClave.getPassword());

                IniciarSesion(usr, pwd);
            }
        }
    }

    private void IniciarSesion(String usuario, String clave)
    {
        try {
            cSesion.iniciarSesion(usuario, clave);
            
            if ( usuario.equals("") && clave.equals("") ){	
	         mostrarErrorLogin("Los campos estan vacios");
	    }else if ( usuario.equals("") ){
                 mostrarErrorLogin("El campo nombre esta vacio");
            }else if ( clave.equals("") ){
		 mostrarErrorLogin("El campo clave esta vacio");
            }else if ( cSesion.getUsuarioActual() == null ) {
	         mostrarErrorLogin("Usuario o Password incorrecto");
            }else {
		 MainWindow m = new MainWindow();
	         m.mostrar();
		 this.ocultar();
				
	     } 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    //metodo que muestra el MessageDialog cuando existe un error
	public void mostrarErrorLogin(){
	    JOptionPane.showMessageDialog(null,
						              "Usuario o Password incorrecto,",
						               "Error de acceso", 
									   JOptionPane.ERROR_MESSAGE);
         
        txtUsuario.setText("");
		txtClave.setText(""); 
		txtUsuario.requestFocusInWindow();
    }	

    /**
     * @Deprecated Use la de eric
     * */
    @Deprecated
	public void mostrarErrorLogin( String mensaje){
	    JOptionPane.showMessageDialog(null, mensaje,"Error de acceso", 
					 JOptionPane.ERROR_MESSAGE);	
	}
	
	
	// metodo para ocultar la ventana activa
	public void ocultar(){
		this.setVisible(false);
	}
}
