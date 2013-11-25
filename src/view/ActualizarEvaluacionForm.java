package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import domain.ControladorEvaluacion;




public class ActualizarEvaluacionForm extends JFrame
                                      implements ActionListener,
                                      ListSelectionListener{

		private JPanel contentPane;
		private JPanel panelNorte;
		private JPanel panelCentral;
		private JPanel panelSur;
		private JLabel lblTitulo;
		private JButton btnCancelar;
		private JButton btnActualizar;
		private JScrollPane scrollPane;
		private JList<String> list;
		private boolean seleccion;
		private HashMap<Integer,String> listaEvs = new HashMap<Integer,String>();
		private ArrayList<HashMap<String,String> > puntosEv;
		private Vector<String> listaEv = new Vector<String>();
		private int idEv;
		private ControladorEvaluacion cEvaluacion = new ControladorEvaluacion();

		// ------------------------------------------
		// Constructor
		// ------------------------------------------
		public ActualizarEvaluacionForm() {
			
			super("Actualizar Evaluacion");
			setResizable(false);
			//setIconImage(Toolkit.getDefaultToolkit().getImage("resources/comm.gif"));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 711, 424);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			panelNorte = new JPanel();
			contentPane.add(panelNorte, BorderLayout.NORTH);
			lblTitulo = new JLabel("Listado De Evaluaciones Reclamadas");
			panelNorte.add(lblTitulo);
			
			panelSur = new JPanel();
			contentPane.add(panelSur, BorderLayout.SOUTH);
			
		    btnActualizar = new JButton("Actualizar");
		    btnActualizar.setToolTipText("Actualizar Evaluacion");
		    btnActualizar.addActionListener(this);
			panelSur.add(btnActualizar);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setToolTipText("Cancelar Actualizacion");
			btnCancelar.addActionListener(this);
			panelSur.add(btnCancelar);
		
			panelCentral = new JPanel();
			panelCentral.setBorder(new LineBorder(new Color(102, 205, 170)));
			panelCentral.setBackground(Color.WHITE);
			contentPane.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(null);
				
			list = new JList<String>(listaEv);
			list.setSelectionBackground(new Color(240, 255, 255));
			list.setBackground(new Color(176, 224, 230));
			list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			list.addListSelectionListener(this );
			
			scrollPane = new JScrollPane(list);
			scrollPane.setToolTipText("Listado De Evaluaciones");
			scrollPane.setBounds(320, 82, 281, 167);
			scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
			scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
			panelCentral.add(scrollPane);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("resources/titulo2.jpg"));
			label.setBounds(44, 11, 205, 277);
			panelCentral.add(label);
		}
		
		
		// Metodo que muestra las evaluaciones reclamadas
		public void mostrar(){
		    //listaEvs = cEvaluacion.ObtenerEvaluacionesReclamadas();
			listaEvs = Stub.darRepresentantes();
		    mostrarListaEvaluaciones(listaEvs);
		    setVisible(true);
		}	
		
		// Metodo que oculta la ventana de evaluaciones
		public void ocultar(){
			this.setVisible(false);
		
		}
        
		// Metodo que carga el Vector para mostrar la lista de evaluaciones
		public void mostrarListaEvaluaciones(HashMap<Integer,String> listaEva){
			
			for (Map.Entry<Integer, String> entry : listaEva.entrySet()) {
				listaEv.add(entry.getKey() + "   " + entry.getValue());
			}
		}
		
		//Obtiene la evaluacion seleccionada y la muestra
		public void obtenerEvaluacion(int idEv){
			//puntosEv = cEvaluacion.ObtenerEvaluacionReclamada(idEv);
			puntosEv = Stub.darEvaluacion6();
			ocultar();
			EvaluacionForm form = new EvaluacionForm(list.getSelectedValue(),idEv);
		    form.mostrarEvaluacion(puntosEv);
		    form.mostrar();
		   
		    
		}
		
		// Manejando el evento cuando se selecciona un representante
		public void valueChanged( ListSelectionEvent evento )
	    {
	        if(list.isSelectionEmpty()){
	        	seleccion = false;
	        }else{
	        	seleccion = true;
	        }
	    }
		
		// Manejando los eventos de los botones
	    public void actionPerformed(ActionEvent evento)
	    {	
			if((evento.getSource() == btnActualizar) && (seleccion == true))
			{   
				String[] nombre = list.getSelectedValue().split("   ");
				idEv = Integer.parseInt(nombre[0].trim());
				obtenerEvaluacion(idEv);
				
			}
			if((evento.getSource() == btnActualizar) && (seleccion != true))
			{
				JOptionPane.showMessageDialog(null, "Debes seleccionar una evaluacion ",
						                            "Actualizar Evaluacion ",
						                            JOptionPane.INFORMATION_MESSAGE);
			}
			if(evento.getSource() == btnCancelar)
			{
			    ocultar();
			    MainWindow m = new MainWindow();
			    m.mostrar();
			}
			
		}
	    
		
		
		
 }	
	
