
package domain;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.EvaluarRepresentanteForm;
import view.PanelRepresentante;


/**
 * @author JairisThomas
 * 
 */
public class ControlCardLayout implements ActionListener {

	EvaluarRepresentanteForm ventana;
	JPanel panelCentral;

	public ControlCardLayout(EvaluarRepresentanteForm ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Apéndice de método generado automáticamente

		if (evento.getSource() == ventana.Combo) {
			int cartaActual = ventana.carta;

			if (ventana.Combo.getSelectedIndex() != 0) {
				++cartaActual;
				ventana.manejadorFlujo.show(ventana.panelCentral, ""
						+ (cartaActual));
						
				ventana.evaluarRepresentante(5);
				ventana.btnGuardar.setEnabled(true);

			}

		}

	}
}
