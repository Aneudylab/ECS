package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import domain.ControladorEvaluacion;




public class EvaluacionForm extends JFrame implements ActionListener {
	
	//---------------------------
	// Variables Miembros
	//---------------------------
	
	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelCentral;
	private JCheckBox chk1[];
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblId[];
	private JLabel lblPreguntaT;
	private JLabel lblPregunta;
	private JLabel lblTitulo;
	private JLabel lblRespuestaT;
	private JLabel lblRespuesta[];
	private JLabel lblComentario;
	private JLabel lblProcede;
	private TextArea textAreaComent;
	private JScrollPane scrollPane;
	private HashMap<Integer, Boolean> recProcedentes;
	private int id;
	private ControladorEvaluacion cEvaluacion = new ControladorEvaluacion();

	//-----------------------
	// Constructor
	//-----------------------
	
	public EvaluacionForm(String nombre, int idEvl) {
		
		super("Actualizar evaluación");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,922, 487);
		id = idEvl;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNorte = new JPanel();
		panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelNorte.setBackground(new Color(176, 224, 230));
		
		lblTitulo = new JLabel("Evaluación  " + nombre);
		panelNorte.add(lblTitulo);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setPreferredSize(new Dimension(877,2544));
		panelCentral.setLayout(null);
		
		lblPreguntaT = new JLabel("Pregunta");
		lblPreguntaT.setBounds(22, 11, 70, 14);
		panelCentral.add(lblPreguntaT);
		
		lblRespuestaT = new JLabel("Respuesta");
		lblRespuestaT.setBounds(434, 14, 70, 14);
		panelCentral.add(lblRespuestaT);
		
		lblComentario = new JLabel("Comentario");
		lblComentario.setBounds(611, 14, 70, 14);
		panelCentral.add(lblComentario);
		
		lblProcede = new JLabel("Procede");
		lblProcede.setBounds(792, 11, 85, 14);
		panelCentral.add(lblProcede);
		
		scrollPane = new JScrollPane(panelCentral);
		contentPane.add(scrollPane);
		
		panelSur = new JPanel();
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setToolTipText("Actualizar Evaluación ");
		btnGuardar.addActionListener(this);
		panelSur.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar Actualización");
		btnCancelar.addActionListener(this);
		panelSur.add(btnCancelar);
		
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
	}
	
	//Muestra los puntos reclamados de la evaluacion
	public void mostrarEvaluacion(ArrayList<HashMap<String,String> > puntos){
		
		int i = 44;
		int suma = i;
		int k = 0;
		lblId = new JLabel[puntos.size()];
		lblRespuesta =  new JLabel[puntos.size()];
		chk1 = new JCheckBox[puntos.size()];
		
		 for(HashMap<String, String> ptos:puntos){
			
			// Los id de los puntos
			 lblId[k] = new JLabel( ptos.get("id")); 
			 lblId[k].setBounds(22, suma, 24, 14);
			 panelCentral.add(lblId[k]);
			
			// Los puntos de evaluacion
			 lblPregunta = new JLabel(ptos.get("pto")); 
			 lblPregunta.setBounds(48, suma, 402, 14);
			 panelCentral.add(lblPregunta);
			 
			 // Las respuestas
			 lblRespuesta[k] = new JLabel(ptos.get("resp")); 
			 lblRespuesta[k].setBounds(456, suma, 47, 23);
			 panelCentral.add(lblRespuesta[k]);
			 
			 // Los comentarios
			 textAreaComent = new TextArea(ptos.get("coment")); 
		         textAreaComent.setFont(new Font("Calibri", Font.BOLD, 11));
		         textAreaComent.setBackground(Color.WHITE);
		         textAreaComent.setEditable(false);
		         textAreaComent.setBounds(543, suma, 193, 42);
		         panelCentral.add(textAreaComent);
		     
		         // Las casillas de verificacion
		         chk1[k] = new JCheckBox("Si");
			 chk1[k].setBackground(Color.WHITE);
			 chk1[k].setBounds(805, suma, 47, 23);
			 chk1[k].addActionListener(this);
			 panelCentral.add(chk1[k]);
			 	
			 suma = suma + i + 40;
			 k++;
				
		 }
		  	
	}
	
	//envia a la clase controladora el id de evaluacion y los puntos actualizados
	public void actualizarEvaluacion(int idEval, HashMap<Integer, Boolean> recProcedentes){
	    cEvaluacion.actualizarEvaluacion(idEval, recProcedentes);
		JOptionPane.showMessageDialog(null, "Evaluacion " + idEval + " actualizada!");
	}
	
	public void mostrar(){
		this.setVisible(true);
	}
	
	public void ocultar(){
		this.setVisible(false);
	}
	
	
	
	// Manejando los eventos
	public void actionPerformed(ActionEvent evento){
		
	    recProcedentes = new HashMap<Integer, Boolean>();
		
		if ( evento.getSource() == btnGuardar ){ // Se guardan las respuestas
		
		    for ( int k = 0; k < chk1.length ; k++ ) {
			   if ( chk1[k].isSelected() ){
				   Integer idPunto = Integer.parseInt(lblId[k].getText());
				   Boolean respuesta = lblRespuesta[k].getText().equalsIgnoreCase("No")? false:true; 
			           recProcedentes.put(idPunto, respuesta);
			   }
		    }
		    
		    	if( JOptionPane.showConfirmDialog(null,
						"Esta seguro que desea guardar los cambios?",
		                                "Confirmando Actualizacion", 
		                                 JOptionPane.YES_NO_OPTION)  == JOptionPane.YES_OPTION)
				    {   
				    	actualizarEvaluacion(id,recProcedentes);
				    	ocultar();
				    	ActualizarEvaluacionForm actualizar = new ActualizarEvaluacionForm();
				        actualizar.mostrar();
				    }
				    
		}	
		if ( evento.getSource() == btnCancelar ){
			ocultar();
			ActualizarEvaluacionForm actualizar = new ActualizarEvaluacionForm();
	                actualizar.mostrar();
		}
		
	}


}

