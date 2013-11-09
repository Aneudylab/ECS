package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import domain.ControladorPlantilla;

public class CrearPlantillaForm extends KeyAdapter implements
		ActionListener {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	private static final String CANCELAR = "cancelar";
	private static final String GUARDAR = "guardar";
	private static final String AGREGAR = "agregar";
	
	// -----------------------------------------------------------------
	// Componentes 
	// -----------------------------------------------------------------
	private JFrame ventana;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelPregunta;
	private JPanel panelEstatus;
	private JPanel panelOpciones;
	private JLabel lbletiquetaSuperior;
	private JLabel lbletiquetaInferior;
	private JLabel lblContador;
	private JTextField txtPregunta;
	private JButton btnAgregar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnX;
	private JScrollPane scrollpane;
	private StatusBar statusBar = new StatusBar();
	private CardLayout manejador;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	private ArrayList<JLabel> lblPregunta = new ArrayList<JLabel>();
	private String pregunta;
	private ArrayList<String> listaPreguntas = new ArrayList<String>();
    private int plantillaId;

	// -----------------------------------------------------------------
	// Controladores
	// -----------------------------------------------------------------
    ControladorPlantilla cPlantilla = new ControladorPlantilla();
	
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	public CrearPlantillaForm() {
        cPlantilla = new ControladorPlantilla();
		construyePanelSuperior();
		construyePanelInferior();
		construyePanelOpciones();
		construyePanelEstatus();
		construyeVentana();
	}

	public CrearPlantillaForm(int idUsuario) {
        cPlantilla = new ControladorPlantilla();
		construyePanelSuperior();
		construyePanelInferior();
		construyePanelOpciones();
		construyePanelEstatus();
		construyeVentana();
	}

	// -----------------------------------------------------------------
	// Metodos del Formulario
	// -----------------------------------------------------------------
	public void construyePanelSuperior() {
		lbletiquetaSuperior = new JLabel("Pregunta	 : ");
		panelSuperior = new JPanel();
		panelSuperior.setBorder(BorderFactory
				.createTitledBorder("Crear Plantilla de Evaluacion "));
		txtPregunta = new JTextField("", 56);
		txtPregunta.addKeyListener(this);
		btnAgregar = new JButton("ADD   ");
		panelSuperior.setLayout(new FlowLayout());
		panelSuperior.add(lbletiquetaSuperior);
		panelSuperior.add(txtPregunta);
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		panelSuperior.add(btnAgregar);
		// Determinar el tamaño del panel
		panelSuperior.setPreferredSize(new Dimension(900, 75));
		panelSuperior.setMaximumSize(new Dimension(900, 75));
		panelSuperior.setMinimumSize(new Dimension(200, 75));
	}

	public void construyePanelInferior() {
		panelInferior = new JPanel();
		panelInferior
				.setBorder(BorderFactory.createTitledBorder("Preguntas  "));
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setSize(873, 506);

	}

	public void construyeVentana() {
		ventana = new JFrame();
		ventana.setTitle("Crear Plantilla ");
		ventana.setSize(900, 600);
		ventana.setLayout(new BoxLayout(ventana.getContentPane(),
				BoxLayout.Y_AXIS));
		ventana.add(panelSuperior);
		scrollpane = new JScrollPane(panelInferior);
		ventana.add(scrollpane);
		ventana.add(panelOpciones);
		ventana.add(panelEstatus);
		ventana.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		//ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
	}

	public void construyePregunta() {
		panelPregunta = new JPanel();
		panelPregunta.setLayout(new BorderLayout());

		btnX = new JButton(" X ");

		btnX.setActionCommand("x");
		btnX.addActionListener(this);

		btnX.setBackground(Color.WHITE);
		btnX.setBorderPainted(false);
		panelPregunta.setBackground(Color.WHITE);
		panelPregunta.setPreferredSize(new Dimension(850, 40));
		panelPregunta.setMaximumSize(new Dimension(950, 40));
		panelPregunta.setMinimumSize(new Dimension(850, 40));
		
		lblContador = new JLabel(" ¿ ");
		lblPregunta.add(new JLabel(listaPreguntas.get(listaPreguntas.size() - 1)+" ?"));
		panelPregunta.add(lblContador, BorderLayout.WEST);
		panelPregunta.add(lblPregunta.get((listaPreguntas.size() - 1)),
				BorderLayout.CENTER);
		panelPregunta.add(btnX, BorderLayout.EAST);
		
		// determinar el size del panel pregunta
		statusBar.setMessage(""+ listaPreguntas.size());
		panelInferior.add(panelPregunta);// Decendente FlowLayout.Left

	}

	public void construyePanelEstatus() {
		panelEstatus = new JPanel();
		panelEstatus.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelEstatus.add(statusBar);
		panelEstatus.setPreferredSize(new Dimension(900, 25));
		panelEstatus.setMaximumSize(new Dimension(900, 25));
		panelEstatus.setMinimumSize(new Dimension(900, 25));

	}

	public void construyePanelOpciones() {
		panelOpciones = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		panelOpciones.setLayout(flow);
		flow.setHgap(150);
		btnCancelar = new JButton("    Cancelar       ");
		btnGuardar = new JButton("    Guardar       ");
		btnGuardar.setActionCommand(GUARDAR);
		btnCancelar.setActionCommand(CANCELAR);
		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);
		panelOpciones.add(btnGuardar);
		panelOpciones.add(btnCancelar);
		panelOpciones.setPreferredSize(new Dimension(900, 50));
		panelOpciones.setMaximumSize(new Dimension(900, 50));
		panelOpciones.setMinimumSize(new Dimension(900, 50));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == AGREGAR) {

			escribirPregunta();
		}

		if (e.getActionCommand() == "x") {

			JButton boton = (JButton) e.getSource(); // cast al objeto de la
														// fuente a un JButton
			eliminarPregunta(boton); // invocando el metodo
										// eliminarPregunta tomando como
										// argumento un JButton
		}
		if(e.getActionCommand() == CANCELAR){
			cancelar();
		}
		if(e.getActionCommand() == GUARDAR){
			crearNuevaPlantilla(listaPreguntas); // en construccion 
		}
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {

			escribirPregunta();
			ventana.paintAll(ventana.getGraphics());
		}
	}
	
	public void escribirPregunta() {
		if (txtPregunta.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Primero escriba.. luego presione, simple eh!", "Mensaje",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			pregunta = txtPregunta.getText();
			listaPreguntas.add(pregunta);
			construyePregunta();
			txtPregunta.setText("");
		}
	}
	
	private void organizarPreguntas() {
		lblPregunta.clear();
		panelPregunta.removeAll();
	
		//lblContador = new JLabel("" + listaPreguntas.size());
		lblContador = new JLabel(" ¿ ");
		
		for (int i = 0; i < listaPreguntas.size(); i++) {
			lblPregunta.add(new JLabel(listaPreguntas.get(listaPreguntas.size() - 1)+" ?"));
			panelPregunta.add(lblContador, BorderLayout.WEST);
			panelPregunta.add(lblPregunta.get((lblPregunta.size() - 1)),
					BorderLayout.CENTER);
			panelPregunta.add(btnX, BorderLayout.EAST);
			
		}
		statusBar.setMessage(""+ listaPreguntas.size());
	}

	private void eliminarPregunta(JButton boton) {

		int index = 0; // contador para el index

		for (Component c : panelInferior.getComponents()) {

			JPanel panel = (JPanel) c; // cast del componente a un JPanel

			for (Component i : panel.getComponents()) { // Por cada compononente

				if (i instanceof JButton) { // verificar si es una instancia de
											// un JButton
					JButton boton2 = (JButton) i; // cast a un JButton
					if (boton == boton2) { // Verificar si los botones son
											// iguales
						panelInferior.remove(index);
						listaPreguntas.remove(index);
						organizarPreguntas();
					}
				}
			}
			// se incrementa el contador para la busqueda
			index++;
		}

		ventana.paintAll(ventana.getGraphics());
	}
	private void cancelar(){
		//listaPreguntas.clear();
        ocultar();
	}
	
	// -----------------------------------------------------------------
	// Metodos definidos en el diagrama de clases
	// -----------------------------------------------------------------
	
	public void crearNuevaPlantilla(ArrayList<String> listaPreguntas) {
        if(listaPreguntas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No puede crear una plantilla vacía",
                "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int plantillaId = cPlantilla.crearPlantilla(listaPreguntas);

            JOptionPane.showMessageDialog(null, "Se creó la plantilla: "+plantillaId,
                "Crear Plantilla", JOptionPane.INFORMATION_MESSAGE);

            ocultar();
        }
	}
	public void mostrar(){
		ventana.setVisible(true);
	}
	
    private void ocultar(){
		ventana.setVisible(false);
		MainWindow main = new MainWindow();
		main.mostrar();
    }
}
