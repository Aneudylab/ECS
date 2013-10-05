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

public class PlantillaForm extends KeyAdapter implements
		ActionListener {

	// paneles
	JFrame ventana;
	JPanel panelSuperior;
	JPanel panelInferior;
	JPanel panelPregunta;
	JPanel panelEstatus;
	JPanel panelOpciones;

	JLabel lbletiquetaSuperior;
	JLabel lbletiquetaInferior;
	JLabel lblContador;
	ArrayList<JLabel> lblPregunta = new ArrayList<JLabel>();
	String pregunta;
	ArrayList<String> listaPreguntas = new ArrayList<String>();//
	JTextField txtPregunta;
	JButton btnAgregar;
	JButton btnGuardar;
	JButton btnCancelar;
	JButton btnX;

	int contador = 1;
	JScrollPane scrollpane;
	JRadioButton si, no;
	ButtonGroup radioGrupo;
	// para dar espacio entre
	// StatusBar statusBar = new StatusBar();
	GridBagConstraints gbc = new GridBagConstraints();
	CardLayout manejador;

	// Metodos definidos en el diagrama de clases

	public void crearNuevaPlantilla() {
	}

	public void crearPuntoEvaluacion() {
	}

	public void guardarPlantilla() {
	}

	// ==================================

	public PlantillaForm() {
		construyePanelSuperior();
		construyePanelInferior();
		construyePanelOpciones();
		// construyePanelEstatus();
		construyeVentana();
	}

	public PlantillaForm(int idUsuario) {
		construyePanelSuperior();
		construyePanelInferior();
		construyePanelOpciones();
		// construyePanelEstatus();
		construyeVentana();
	}

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
		btnAgregar.setActionCommand("ADD");
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
		// panelInferior.setLayout(new FlowLayout(FlowLayout.LEADING));
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setSize(873, 506);

	}

	public void construyeVentana() {
		ventana = new JFrame();
		ventana.setTitle("Crear de Plantilla ");
		ventana.setSize(900, 600);
		ventana.setLayout(new BoxLayout(ventana.getContentPane(),
				BoxLayout.Y_AXIS));
		ventana.add(panelSuperior);
		scrollpane = new JScrollPane(panelInferior);
		ventana.add(scrollpane);
		ventana.add(panelOpciones);
		// ventana.add(panelEstatus);
		ventana.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		ventana.setVisible(true);
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
		// JOptionPane.showConfirmDialog(null, " "+listaPreguntas.size());
        
		lblContador = new JLabel(""+(listaPreguntas.size()));
		//for (int i = 0; i < listaPreguntas.size(); i++) {
		lblPregunta.add(new JLabel(") " + listaPreguntas.get(listaPreguntas.size()-1)));
		panelPregunta.add(lblContador, BorderLayout.WEST);
		panelPregunta.add(lblPregunta.get((lblPregunta.size() - 1)), BorderLayout.CENTER);
		panelPregunta.add(btnX, BorderLayout.EAST);
		//}
		contador++;
		// determinar el size del panel pregunta
		panelPregunta.setPreferredSize(new Dimension(850, 40));
		panelPregunta.setMaximumSize(new Dimension(950, 40));
		panelPregunta.setMinimumSize(new Dimension(850, 40));

		panelInferior.add(panelPregunta);// Decendente FlowLayout.Left

	}

	private void organizarPreguntas() {

		// listaPreguntas.removeAll(lblPregunta);
		lblPregunta.clear();
		//int count= panelPregunta.getComponentCount();
        panelPregunta.removeAll();
		panelInferior.removeAll();
		for (int i = 0; i < listaPreguntas.size(); i++) {
			JLabel tmpContador = new JLabel(""+(i+1));
			
			lblPregunta.add(new JLabel(") "+ listaPreguntas.get(listaPreguntas.size() - 1)));
			panelPregunta.add(tmpContador, BorderLayout.WEST);
			panelPregunta.add(lblPregunta.get((lblPregunta.size() - 1)),BorderLayout.CENTER);
			panelPregunta.add(btnX, BorderLayout.EAST);
		}
		panelPregunta.setPreferredSize(new Dimension(850, 40));
		panelPregunta.setMaximumSize(new Dimension(950, 40));
		panelPregunta.setMinimumSize(new Dimension(850, 40));
		panelInferior.add(panelPregunta);
		
		// ventana.paintAll(ventana.getGraphics());
	}

	/*
	 * public void construyePanelEstatus() { panelEstatus = new JPanel();
	 * panelEstatus.setLayout(new FlowLayout(FlowLayout.LEFT));
	 * panelEstatus.add(statusBar); panelEstatus.setPreferredSize(new
	 * Dimension(900, 25)); panelEstatus.setMaximumSize(new Dimension(900, 25));
	 * panelEstatus.setMinimumSize(new Dimension(900, 25));
	 * 
	 * }
	 */
	public void construyePanelOpciones() {
		panelOpciones = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		panelOpciones.setLayout(flow);
		flow.setHgap(150);
		btnCancelar = new JButton("    Cancelar       ");
		btnGuardar = new JButton("    Guardar       ");
		// agregar escuchas
		btnGuardar.setActionCommand("GUARDAR");
		btnCancelar.setActionCommand("CANCELAR");
		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);
		panelOpciones.add(btnGuardar);
		panelOpciones.add(btnCancelar);
		panelOpciones.setPreferredSize(new Dimension(900, 50));
		panelOpciones.setMaximumSize(new Dimension(900, 50));
		panelOpciones.setMinimumSize(new Dimension(900, 50));
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
			// statusBar.setMessage("Cantidad :" + Integer.toString(contador -
			// 1)
			// + "   ");

			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ADD") {

			escribirPregunta();
		}

		if (e.getActionCommand() == "x") {

			JButton boton = (JButton) e.getSource(); // cast al objeto de la
														// fuente a un JButton
			eliminarPregunta(boton); // invocando el metodo
										// eliminarPregunta tomando como
										// argumento un JButton
										
			organizarPreguntas();
		    ventana.paintAll(ventana.getGraphics());
		}
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {

			escribirPregunta();
			ventana.paintAll(ventana.getGraphics());
		}
	}

	private void eliminarPregunta(JButton boton) {

		int index = 0; // contador para el index

		for (Component c : panelInferior.getComponents()) {

			JPanel panel = (JPanel) c; // cast del componente a un JPanel

			for (Component i : panel.getComponents()) { // Por cada compononente

				if (i instanceof JButton) { // verificar si es una instancia de
											// un JButton
					JButton boton2 = (JButton) i; // cast a un JButton
					if (boton == boton2) { 	// Verificar si los botones son
											// iguales
						listaPreguntas.remove(index);
						panelInferior.remove(index);
						
						
					}
				}
			}
			// se incrementa el contador para la busqueda
			index++;
		}

	
	}
	
	public void mostrar(){
	  this.setVisible();
	}

}