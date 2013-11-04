package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

public class PanelEvaluacion extends JPanel {

	public ArrayList<JPanel> panelInteriorEvaluacion = new ArrayList<JPanel>();
	public ArrayList<JLabel> lblPregunta = new ArrayList<JLabel>();
	public ArrayList<JLabel> lblContPregunta = new ArrayList<JLabel>();
	public ArrayList<JRadioButton> rbtnSi = new ArrayList<JRadioButton>();
	public ArrayList<JRadioButton> rbtnNo = new ArrayList<JRadioButton>();
	public ArrayList<ButtonGroup> btnGroup = new ArrayList<ButtonGroup>();
	public ArrayList<JPanel> respuesta = new ArrayList<JPanel>();
	public ArrayList<Integer> IDrespuesta = new ArrayList<Integer>();

	// JFrame v = new JFrame();

	public PanelEvaluacion() {
		// TODO Apéndice de constructor generado automáticamente
		construyePanelEvaluacion();
	}

	private void construyePanelEvaluacion() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory
				.createTitledBorder("Preguntas de evaluación    "));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(850, 40));
		setMaximumSize(new Dimension(950, 40));
		setMinimumSize(new Dimension(850, 40));
	}

}