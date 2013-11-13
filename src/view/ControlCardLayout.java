
package view;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author JairisThomas
 * 
 */
public class ControlCardLayout implements ActionListener {

	private static EvaluarRepresentanteForm ventana;
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
						
				ventana.evaluarRepresentante(darIdRepresentante());
				ventana.getBtnGuardar().setEnabled(true);

			}

		}

	}
	public static int darIdRepresentante(){
		int id;
		String[] idStrin = ventana.getCombo().getSelectedItem().toString().split("-");
		id = Integer.parseInt(idStrin[0].trim());
		return id;
	}
}
