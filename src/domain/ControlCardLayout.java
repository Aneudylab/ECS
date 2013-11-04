
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

	private EvaluarRepresentanteForm ventana;
	private JPanel panelCentral;

	public ControlCardLayout(EvaluarRepresentanteForm ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Apéndice de método generado automáticamente

		if (evento.getSource() == ventana.getCombo()) {
			int cartaActual = ventana.getCarta();

			if (ventana.getCombo().getSelectedIndex() != 0) {
				++cartaActual;
				ventana.getManejadorFlujo().show(ventana.getPanelCentral(), ""
						+ (cartaActual));
						
				ventana.evaluarRepresentante(1);
				ventana.getBtnGuardar().setEnabled(true);

			}

		}

	}
}
