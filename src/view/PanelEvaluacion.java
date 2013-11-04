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

	private ArrayList<JPanel> panelInteriorEvaluacion = new ArrayList<JPanel>();
	private ArrayList<JLabel> lblPregunta = new ArrayList<JLabel>();
	private ArrayList<JLabel> lblContPregunta = new ArrayList<JLabel>();
	private ArrayList<JRadioButton> rbtnSi = new ArrayList<JRadioButton>();
	private ArrayList<JRadioButton> rbtnNo = new ArrayList<JRadioButton>();
	private ArrayList<ButtonGroup> btnGroup = new ArrayList<ButtonGroup>();
	private ArrayList<JPanel> respuesta = new ArrayList<JPanel>();
	private ArrayList<Integer> IDrespuesta = new ArrayList<Integer>();

	public PanelEvaluacion() {
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
	//-------------------------------------
	//SETTERS AND GETTERS 
	//--------------------------------------
	
	public ArrayList<Integer> getIDrespuesta() {
		return IDrespuesta;
	}

	public void setIDrespuesta(ArrayList<Integer> iDrespuesta) {
		IDrespuesta = iDrespuesta;
	}

	public ArrayList<JPanel> getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(ArrayList<JPanel> respuesta) {
		this.respuesta = respuesta;
	}

	public ArrayList<JRadioButton> getRbtnSi() {
		return rbtnSi;
	}

	public void setRbtnSi(ArrayList<JRadioButton> rbtnSi) {
		this.rbtnSi = rbtnSi;
	}

	public ArrayList<JRadioButton> getRbtnNo() {
		return rbtnNo;
	}

	public void setRbtnNo(ArrayList<JRadioButton> rbtnNo) {
		this.rbtnNo = rbtnNo;
	}

	public ArrayList<ButtonGroup> getBtnGroup() {
		return btnGroup;
	}

	public void setBtnGroup(ArrayList<ButtonGroup> btnGroup) {
		this.btnGroup = btnGroup;
	}

	public ArrayList<JLabel> getLblPregunta() {
		return lblPregunta;
	}

	public void setLblPregunta(ArrayList<JLabel> lblPregunta) {
		this.lblPregunta = lblPregunta;
	}
	
	public ArrayList<JPanel> getPanelInteriorEvaluacion() {
		return panelInteriorEvaluacion;
	}

	public void setPanelInteriorEvaluacion(ArrayList<JPanel> panelInteriorEvaluacion) {
		this.panelInteriorEvaluacion = panelInteriorEvaluacion;
	}
	public int getCantLblPregunta() {
		return lblPregunta.size();
	}
}