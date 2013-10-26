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

import domain.ControlCardLayout;
import domain.ControladorEvaluacion;

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
	int cartaActual = 1;
	// ------------------------------------------
	// Componentes de la ventana
	// ------------------------------------------

	private JFrame ventana;
	private JPanel panelSuperior, panelInteriorSuperior, PanelInferior;
	public JPanel panelCentral;
	private PanelRepresentante panelRepresentante;
	private PanelEvaluacion panelEvaluacion;
	public CardLayout manejadorFlujo;
	private JLabel lblTituloPanelSuperior;
	public JComboBox<String> Combo;
	private JLabel lblCantPreguntas;
	public int carta = 1;
	private Vector<String> listaRepresentates = new Vector<String>();
	public JButton btnGuardar;
	public JButton btnCancelar;

	// -----------------------s-------------------
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

		mostrarRepresentantes(Stub.darRepresentantes());

		// componentes del panel

		lblTituloPanelSuperior.setText(" 	EVALUACION DE REPRESENTANTE     ");
		ControlCardLayout control = new ControlCardLayout(this);

		Combo.addActionListener(control);

		// agregar los componentes al panel
		panelInteriorSuperior.add(lblTituloPanelSuperior, BorderLayout.WEST);
		panelInteriorSuperior.add(Combo, BorderLayout.EAST);

		panelSuperior.add(panelInteriorSuperior);
	}

	private void construyePanelCentral() {
		panelCentral = new JPanel();
		manejadorFlujo = new CardLayout();
		panelCentral.setLayout(manejadorFlujo);
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setSize(873, 506);
		panelCentral.add(panelRepresentante, "1");
		panelCentral.add(panelEvaluacion, "2");

	}

	private void construyePanelInferior() {
		PanelInferior = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		PanelInferior.setLayout(flow);
		flow.setHgap(150);
		btnGuardar = new JButton("    Guardar       ");
		btnCancelar = new JButton("    Cancelar       ");
		btnGuardar.setActionCommand(GUARDAR);
		btnCancelar.setActionCommand(CANCELAR);
		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);
		PanelInferior.add(btnCancelar);
		PanelInferior.add(btnGuardar);
		PanelInferior.setPreferredSize(new Dimension(900, 50));
		PanelInferior.setMaximumSize(new Dimension(900, 50));
		PanelInferior.setMinimumSize(new Dimension(900, 50));

		if (cartaActual == 1) {
			btnGuardar.setEnabled(false);
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
	}

	private void mostrarRepresentantes(HashMap<Integer, String> listaReps) {
		listaRepresentates.add("Seleccione un Representante...........");
		for (Map.Entry<Integer, String> entry : Stub.darRepresentantes()
				.entrySet()) {
			listaRepresentates.add(entry.getValue());

		}
		Combo = new JComboBox<>(listaRepresentates);
	}

	public void evaluarRepresentante(int idRepresentante) {
		HashMap<Integer, String> puntosEv = Stub.darEvaluacion();
		mostrarPlantilla(puntosEv);

	}

	public void mostrarPlantilla(HashMap<Integer, String> puntosEv) {
		int i = 0;
		for (Map.Entry<Integer, String> entry : puntosEv.entrySet()) {
			lblCantPreguntas = new JLabel(i + " ");
			panelEvaluacion.rbtnSi.add(new JRadioButton(" Sip "));
			panelEvaluacion.rbtnNo.add(new JRadioButton(" Nop "));

			panelEvaluacion.btnGroup.add(new ButtonGroup());

			panelEvaluacion.btnGroup.get(i).add(panelEvaluacion.rbtnSi.get(i));
			panelEvaluacion.btnGroup.get(i).add(panelEvaluacion.rbtnNo.get(i));

			panelEvaluacion.rbtnSi.get(i).setBackground(Color.WHITE);
			panelEvaluacion.rbtnNo.get(i).setBackground(Color.WHITE);

			panelEvaluacion.rbtnSi.get(i).setActionCommand("SI");
			panelEvaluacion.rbtnNo.get(i).setActionCommand("NO");
			panelEvaluacion.rbtnSi.get(i).addActionListener(this);
			panelEvaluacion.rbtnNo.get(i).addActionListener(this);

			panelEvaluacion.respuesta.add(new JPanel(new BorderLayout()));

			panelEvaluacion.respuesta.get(i).add(panelEvaluacion.rbtnSi.get(i),
					BorderLayout.EAST);
			panelEvaluacion.respuesta.get(i).add(panelEvaluacion.rbtnNo.get(i),
					BorderLayout.WEST);
			panelEvaluacion.lblContPregunta.add(new JLabel(i + " ¿"));
			panelEvaluacion.lblPregunta.add(new JLabel(" " + entry.getValue()));
			panelEvaluacion.panelInteriorEvaluacion.add(new JPanel(
					new BorderLayout()));
			// Editar propiedades de los elementos del panel
			panelEvaluacion.panelInteriorEvaluacion.get(i).setBackground(
					Color.white);
			panelEvaluacion.panelInteriorEvaluacion.get(i).add(
					panelEvaluacion.lblContPregunta.get(i), BorderLayout.WEST);
			panelEvaluacion.panelInteriorEvaluacion.get(i).add(
					panelEvaluacion.lblPregunta.get(i), BorderLayout.WEST);
			panelEvaluacion.panelInteriorEvaluacion.get(i).add(
					panelEvaluacion.respuesta.get(i), BorderLayout.EAST);

			panelEvaluacion.add(panelEvaluacion.panelInteriorEvaluacion.get(i));
			i++;

		}

	}

	public void guardarEvaluacion(int idRep, HashMap<String, Boolean> Respuestas) {
		int idEv = Stub.guardarEvaluacion(idRep, Respuestas);
		JOptionPane.showMessageDialog(null, "Se creó la evaluación " + idEv);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == GUARDAR) {
			HashMap<String, Boolean> darResp = new HashMap<String, Boolean>();
			for (int i = 0; i < panelEvaluacion.respuesta.size(); i++) {
				darResp.put(panelEvaluacion.respuesta.get(i).toString(),
						panelEvaluacion.rbtnSi.get(i).isSelected());

			}
			guardarEvaluacion(5, darResp);
			ocurtar();

		}
		if (e.getActionCommand() == CANCELAR) {
			System.exit(0);
		}
	}
}