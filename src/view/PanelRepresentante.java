package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelRepresentante extends JPanel {

	public PanelRepresentante() {
		construyePanelRepresentante();
		// construyeVentanaPrueba();
	}

	private void construyePanelRepresentante() {
		// control = new ControlCardLayout();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory
				.createTitledBorder("Seleccionar Representantes "));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(850, 40));
		setMaximumSize(new Dimension(950, 40));
		setMinimumSize(new Dimension(850, 40));
		JScrollPane p = new JScrollPane(this);
	}

}