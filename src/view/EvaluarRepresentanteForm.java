package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import domain.ControladorEvaluacion;
import domain.ControladorSesion;

/**
 * Description: Clase vista para evaluar representante.
 * 
 * @author Jairis Rosario
 * 
 * @since Oct 08, 2013
 **/

public class EvaluarRepresentanteForm implements ActionListener {

	// ------------------------------------------
	// Constantes
	// ------------------------------------------

	private static final String GUARDAR = "GUARDAR";
	private static final String CANCELAR = "CANCELAR";
	private int cartaActual = 1;
	// ------------------------------------------
	// Componentes de la ventana
	// ------------------------------------------

	private JFrame ventana;
	private JPanel panelSuperior, panelInteriorSuperior, PanelInferior;
	private JPanel panelCentral;
	private PanelRepresentante panelRepresentante;
	private PanelEvaluacion panelEvaluacion;
	private CardLayout manejadorFlujo;
	private JLabel lblTituloPanelSuperior;
	private JComboBox<String> Combo;
	private JLabel lblCantPreguntas;
	private int carta = 1;
	private Vector<String> listaRepresentates = new Vector<String>();
	private JButton btnGuardar;
	private JButton btnCancelar;
	

	// ------------------------------------------
	// CONTROLADORES
	// ------------------------------------------
		private ControladorEvaluacion cEvaluacion = new ControladorEvaluacion();
		private ControladorSesion cSesion = new ControladorSesion();
		
	// ------------------------------------------
	// CONTRUCTOR
	// ------------------------------------------
	
	public EvaluarRepresentanteForm() {
		construyePanelSuperior();
		construyePanelRepresentante();
		construyePanelEvaluacion();
		construyePanelCentral();
		construyePanelInferior();
		construyeVentana();
	}

	// ------------------------------------------
	// Metodos Constructores de la ventana
	// ------------------------------------------

	private void construyeVentana() {
		ventana = new JFrame();
		ventana.setTitle("  EVALUAR REPRESENTANTE  ");
		ventana.setSize(900, 600);
		ventana.setLayout(new BoxLayout(ventana.getContentPane(),
				BoxLayout.Y_AXIS));
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		// Agregar paneles a la ventana
		JScrollPane p = new JScrollPane(panelCentral);
		ventana.add(panelSuperior);
		ventana.add(p);
		ventana.add(PanelInferior);
	}

	private void construyePanelSuperior() {
		// caracteristicas del panel
		panelSuperior = new JPanel();
		panelInteriorSuperior = new JPanel();
		lblTituloPanelSuperior = new JLabel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		panelSuperior.setLayout(flow);
		panelSuperior.setPreferredSize(new Dimension(900, 70));
		panelSuperior.setMaximumSize(new Dimension(900, 70));
		panelSuperior.setMinimumSize(new Dimension(200, 70));

		panelInteriorSuperior.setPreferredSize(new Dimension(890, 60));
		panelInteriorSuperior.setLayout(new BoxLayout(panelInteriorSuperior,
				BoxLayout.Y_AXIS));
		panelInteriorSuperior.setBorder(BorderFactory
				.createTitledBorder("  Representante     "));

		mostrarRepresentantes(cEvaluacion.obtenerListaRepresentantes());

		// componentes del panel

		lblTituloPanelSuperior.setText(" 	EVALUACION DE REPRESENTANTE     ");
		ControlCardLayout control = new ControlCardLayout(this);

		Combo.addActionListener(control);

		// agregar los componentes al panel
		panelInteriorSuperior.add(lblTituloPanelSuperior, BorderLayout.WEST);
		panelInteriorSuperior.add(getCombo(), BorderLayout.EAST);

		panelSuperior.add(panelInteriorSuperior);
	}

	private void construyePanelCentral() {
		setPanelCentral(new JPanel());
		setManejadorFlujo(new CardLayout());
		getPanelCentral().setLayout(getManejadorFlujo());
		getPanelCentral().setBackground(Color.WHITE);
		getPanelCentral().setSize(873, 506);
		getPanelCentral().add(panelRepresentante, "1");
		getPanelCentral().add(panelEvaluacion, "2");

	}

	private void construyePanelInferior() {
		PanelInferior = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		PanelInferior.setLayout(flow);
		flow.setHgap(150);
		setBtnGuardar(new JButton("    Guardar       "));
		setBtnCancelar(new JButton("    Cancelar       "));
		getBtnGuardar().setActionCommand(GUARDAR);
		getBtnCancelar().setActionCommand(CANCELAR);
		getBtnGuardar().addActionListener(this);
		getBtnCancelar().addActionListener(this);
		PanelInferior.add(getBtnCancelar());
		PanelInferior.add(getBtnGuardar());
		PanelInferior.setPreferredSize(new Dimension(900, 50));
		PanelInferior.setMaximumSize(new Dimension(900, 50));
		PanelInferior.setMinimumSize(new Dimension(900, 50));

		if (cartaActual == 1) {
			getBtnGuardar().setEnabled(false);
		}

	}

	private void construyePanelRepresentante() {
		panelRepresentante = new PanelRepresentante();

	}

	private void construyePanelEvaluacion() {
		panelEvaluacion = new PanelEvaluacion();

	}

	public void mostrar() {
		
		ventana.setVisible(true);
	}
	public void ocurtar(){
		ventana.setVisible(false);
		MainWindow main = new MainWindow();
		main.mostrar();
	}

	private void mostrarRepresentantes(HashMap<Integer, String> listaReps) {
		listaRepresentates.add("Seleccione un Representante...........");
		for (Map.Entry<Integer, String> entry : listaReps
				.entrySet()) {
			listaRepresentates.add(entry.getKey()+" - "+entry.getValue());

		}
		Combo = new JComboBox<>(listaRepresentates);
	}

	public void evaluarRepresentante(int idRepresentante) {
		HashMap<Integer, String> puntosEv = cEvaluacion.obtenerPlantillaActual();
		mostrarPlantilla(puntosEv);

	}

	public void mostrarPlantilla(HashMap<Integer, String> puntosEv) {
		int i = 0;
		
		for (Map.Entry<Integer, String> entry : puntosEv.entrySet()) {
			
			panelEvaluacion.getIDrespuesta().add(entry.getKey());
			panelEvaluacion.getRbtnSi().add(new JRadioButton(" Si ",true));
			panelEvaluacion.getRbtnNo().add(new JRadioButton(" No "));

			panelEvaluacion.getBtnGroup().add(new ButtonGroup());

			panelEvaluacion.getBtnGroup().get(i).add(panelEvaluacion.getRbtnSi().get(i));
			panelEvaluacion.getBtnGroup().get(i).add(panelEvaluacion.getRbtnNo().get(i));

			panelEvaluacion.getRbtnSi().get(i).setBackground(Color.WHITE);
			panelEvaluacion.getRbtnNo().get(i).setBackground(Color.WHITE);

			panelEvaluacion.getRbtnSi().get(i).setActionCommand("SI");
			panelEvaluacion.getRbtnNo().get(i).setActionCommand("NO");
			panelEvaluacion.getRbtnSi().get(i).addActionListener(this);
			panelEvaluacion.getRbtnNo().get(i).addActionListener(this);

			panelEvaluacion.getRespuesta().add(new JPanel(new BorderLayout()));

			panelEvaluacion.getRespuesta().get(i).add(panelEvaluacion.getRbtnSi().get(i),
					BorderLayout.EAST);
			panelEvaluacion.getRespuesta().get(i).add(panelEvaluacion.getRbtnNo().get(i),
					BorderLayout.WEST);
			
			panelEvaluacion.getLblPregunta().add(new JLabel(" " + entry.getValue()));
			panelEvaluacion.getPanelInteriorEvaluacion().add(new JPanel(
					new BorderLayout()));
			// Editar propiedades de los elementos del panel
			panelEvaluacion.getPanelInteriorEvaluacion().get(i).setBackground(
					Color.white);
			panelEvaluacion.getPanelInteriorEvaluacion().get(i).add(
					panelEvaluacion.getLblPregunta().get(i), BorderLayout.WEST);
			panelEvaluacion.getPanelInteriorEvaluacion().get(i).add(
					panelEvaluacion.getRespuesta().get(i), BorderLayout.EAST);

			panelEvaluacion.add(panelEvaluacion.getPanelInteriorEvaluacion().get(i));
			i++;

		}

	}

	public void guardarEvaluacion(HashMap<Integer, Boolean> Respuestas) {
		int idRep = ControlCardLayout.darIdRepresentante();
		int idEv = cEvaluacion.guardarEvaluacion(idRep, Respuestas); 
		JOptionPane.showMessageDialog(null, "Se creó la evaluación " + idEv);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == GUARDAR) {
			HashMap<Integer, Boolean> darResp = new HashMap<Integer, Boolean>();
			for (int i = 0; i < panelEvaluacion.getComponentCount(); i++) {
				darResp.put(panelEvaluacion.getIDrespuesta().get(i).intValue(),
						panelEvaluacion.getRbtnSi().get(i).isSelected());

			}
			guardarEvaluacion(darResp);
			ocurtar();

		}
		if (e.getActionCommand() == CANCELAR) {
			ocurtar();
		}
	}
	//-------------------------------------
	//SETTERS Y GETTERS
	//-------------------------------------
	public JComboBox<String> getCombo() {
		return Combo;
	}

	public void setCombo(JComboBox<String> combo) {
		Combo = combo;
	}

	public int getCarta() {
		return carta;
	}

	public void setCarta(int carta) {
		this.carta = carta;
	}

	public CardLayout getManejadorFlujo() {
		return manejadorFlujo;
	}

	public void setManejadorFlujo(CardLayout manejadorFlujo) {
		this.manejadorFlujo = manejadorFlujo;
	}

	public JPanel getPanelCentral() {
		return panelCentral;
	}

	public void setPanelCentral(JPanel panelCentral) {
		this.panelCentral = panelCentral;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	public JButton getBtnCancelar(){
		return btnCancelar;
	}
	public void setBtnCancelar(JButton btnCancelar){
		this.btnCancelar = btnCancelar;
	}
	
}
