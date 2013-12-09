package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import domain.ControladorEvaluacion;



public class RevisionForm extends JFrame implements ActionListener{

	
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
	private JLabel lblRespuesta;
	private JLabel lblComentario;
	private JLabel lblProcede;
	private TextArea textAreaComent[];
	private JScrollPane scrollPane;
	private HashMap<Integer, String> respReclam;
	private int id;
	private ControladorEvaluacion cEvaluacion = new ControladorEvaluacion();

	//-----------------------
	// Constructor
	//-----------------------
	
	public RevisionForm(String evaluacion, int idEvl) {
		
		super("Revisar evaluación");
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
		
		lblTitulo = new JLabel("Evaluación  " + evaluacion);
		panelNorte.add(lblTitulo);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setPreferredSize(new Dimension(877,2544));
		panelCentral.setLayout(null);
		
		lblPreguntaT = new JLabel("Pregunta");
		lblPreguntaT.setBounds(22, 14, 70, 14);
		panelCentral.add(lblPreguntaT);
		
		lblRespuestaT = new JLabel("Respuesta");
		lblRespuestaT.setBounds(434, 14, 70, 14);
		panelCentral.add(lblRespuestaT);
		
		lblProcede = new JLabel("Reclamar");
		lblProcede.setBounds(545, 14, 70, 14);
		panelCentral.add(lblProcede);
		
		lblComentario = new JLabel("Comentario");
		lblComentario.setBounds(741, 14, 70, 14);
		panelCentral.add(lblComentario);
		
		scrollPane = new JScrollPane(panelCentral);
		contentPane.add(scrollPane);
		
		panelSur = new JPanel();
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setToolTipText("Reclamar Evaluación ");
		btnGuardar.addActionListener(this);
		panelSur.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar Reclamación");
		btnCancelar.addActionListener(this);
		panelSur.add(btnCancelar);
		
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
	}
	
	//Muestra los puntos de la evaluacion
	public void mostrarEvaluacion(ArrayList<Map<String,String> > puntos){
		
		int i = 44;
		int suma = i;
		int k = 0;
		lblId = new JLabel[puntos.size()];
		textAreaComent = new TextArea[puntos.size()];
		chk1 = new JCheckBox[puntos.size()];
		
		 for(Map<String, String> ptos:puntos){
			
			// Los id de los puntos
			 lblId[k] = new JLabel( ptos.get("id")); 
			 lblId[k].setBounds(22, suma, 24, 14);
			 panelCentral.add(lblId[k]);
			
			// Los puntos de evaluacion
			 lblPregunta = new JLabel(ptos.get("pto")); 
			 lblPregunta.setBounds(48, suma, 402, 14);
			 panelCentral.add(lblPregunta);
			 
			// Las respuestas
			 lblRespuesta = new JLabel(ptos.get("resp")); 
			 lblRespuesta.setBounds(456, suma, 47, 23);
			 panelCentral.add(lblRespuesta);
			 
			// Las casillas de reclamacion
		         chk1[k] = new JCheckBox();
			 chk1[k].setBackground(Color.WHITE);
			 chk1[k].setBounds(557, suma, 47, 23);
			 chk1[k].addActionListener(this);
			 panelCentral.add(chk1[k]);
			 
			// Los comentarios
			 textAreaComent[k] = new TextArea(); 
		         textAreaComent[k].setFont(new Font("Calibri", Font.BOLD, 11));
		         textAreaComent[k].setBackground(Color.WHITE);
		         textAreaComent[k].setEnabled(false);
		         textAreaComent[k].setBounds(668, suma, 193, 61);
		         panelCentral.add(textAreaComent[k]);
		      	
			 suma = suma + i + 40;
			 k++;
				
		 }
		  	
	}
	
	//--------------------------------------------
	//Envia la evaluacion y los puntos reclamados
	//--------------------------------------------
	public void guardarRevision(int idEval, HashMap<Integer, String> respReclam){
	    cEvaluacion.guardarRevision(idEval, respReclam);
	    JOptionPane.showMessageDialog(null, "Evaluacion " + idEval + " Reclamada!");
	}
	
	public void mostrar(){
		this.setVisible(true);
	}
	
	public void ocultar(){
		this.setVisible(false);
	}
	
	public void habilitarText(int id){
		textAreaComent[id].setEnabled(true);
	}
	
	public void desabilitarText(int id){
		textAreaComent[id].setEnabled(false);
		textAreaComent[id].setText("");
	}
	
	//----------------------------------------------
	// Manejando los eventos
	//----------------------------------------------
	public void actionPerformed(ActionEvent evento){
		respReclam = new HashMap<Integer, String>();
		
		// Se activa la caja de texto si se selecciona un check
		for ( int k = 0; k < chk1.length ; k++ ) { 
			if ( chk1[k].isSelected() ){
				habilitarText(k);
			}else{
				desabilitarText(k);
			}    
		} 
		
		// Se guarda el id del punto y el comentario
		if ( evento.getSource() == btnGuardar ){ 
			for ( int k = 0; k < chk1.length ; k++ ) {
				   if ( chk1[k].isSelected() ){
					   Integer idPunto = Integer.parseInt(lblId[k].getText());
					   String comentario = textAreaComent[k].getText();
					   respReclam.put(idPunto, comentario);
				   }
			}
			
			if( JOptionPane.showConfirmDialog(null,
					"Esta seguro que desea enviar los cambios?",
	                                "Confirmando Reclamacion", 
	                                JOptionPane.YES_NO_OPTION)  == JOptionPane.YES_OPTION)
			    {   
			    	guardarRevision(id,respReclam); // Se envia la Revision
			    	ocultar();
			    	RevisarEvaluacionForm revisar = new RevisarEvaluacionForm();
			        revisar.mostrar();
			    }
		}
		if ( evento.getSource() == btnCancelar ){
			ocultar();
			RevisarEvaluacionForm revisar = new RevisarEvaluacionForm();
	                revisar.mostrar();
		}
		
	}


}
