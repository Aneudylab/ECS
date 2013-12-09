package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.ControladorEvaluacion;


public class RevisarEvaluacionForm extends JFrame
                                   implements ActionListener,
                                   ListSelectionListener{
	
	// -------------------------
	// Variables Miembros
	// -------------------------
	
	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelSur;
	private JLabel lblTitulo;
	private JButton btnCancelar;
	private JButton btnRevisar;
	private JScrollPane scrollPane;
	private JList<String> list;
	private boolean seleccion;
	private HashMap<Integer,String> listaEvaluaciones = new HashMap<Integer,String>();
	private ArrayList<Map<String,String> > puntosEv;
	private Vector<String> listaEv = new Vector<String>();
	private int idEv;
	private ControladorEvaluacion cEvaluacion = new ControladorEvaluacion();

	// ------------------------------------------
	// Constructor
	// ------------------------------------------
	public RevisarEvaluacionForm() {
		
		super("Revisar Evaluacion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 284);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNorte = new JPanel();
		panelNorte.setBackground(new Color(176, 224, 230));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblTitulo = new JLabel("Listado De Evaluaciones por Revisar");
		panelNorte.add(lblTitulo);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
	    btnRevisar = new JButton("Revisar");
	    btnRevisar.setToolTipText("Revisar Evaluacion");
	    btnRevisar.addActionListener(this);
		panelSur.add(btnRevisar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar Revision");
		btnCancelar.addActionListener(this);
		panelSur.add(btnCancelar);
	
		panelCentral = new JPanel();
		panelCentral.setBackground(SystemColor.menu);
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
			
		list = new JList<String>(listaEv);
		list.setSelectionBackground(new Color(240, 255, 255));
		list.setBackground(SystemColor.window);
		list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		list.addListSelectionListener(this );
		
		scrollPane = new JScrollPane(list);
		scrollPane.setToolTipText("Listado De Evaluaciones");
		scrollPane.setBounds(260, 0, 285, 170);
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		panelCentral.add(scrollPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("resources/titulo3.jpg"));
		label.setBounds(0, 0, 259, 165);
		panelCentral.add(label);
	}
	
	
	//--------------------------------------------------------
	// Metodo que muestra las evaluaciones del representante
	//--------------------------------------------------------
	public void mostrar(){
	    listaEvaluaciones = cEvaluacion.obtenerListaEvaluaciones();
	    mostrarListaEvaluaciones(listaEvaluaciones);
	    setVisible(true);
	}	
	
	//----------------------------------------------
	// Metodo que oculta la ventana de evaluaciones
	//----------------------------------------------
	public void ocultar(){
		this.setVisible(false);
	
	}
    
	//------------------------------------
	// Carga las evaluaciones por revisar
	//------------------------------------
	public void mostrarListaEvaluaciones(HashMap<Integer,String> listaEva){
		
		for (Map.Entry<Integer, String> entry : listaEva.entrySet()) {
			listaEv.add(entry.getKey() + "   " + entry.getValue());
		}
	}
	
	//------------------------------------------------
	//Obtiene la evaluacion seleccionada y la muestra
	//------------------------------------------------
	public void obtenerEvaluacion(int idEv){
		puntosEv = cEvaluacion.obtenerEvaluacion(idEv);
		ocultar();
		RevisionForm form = new RevisionForm(list.getSelectedValue(),idEv);
	    form.mostrarEvaluacion(puntosEv);
	    form.mostrar();
	   
	    
	}
	
	//---------------------------------------------------------
	// Manejando el evento cuando se selecciona una evaluacion
	//---------------------------------------------------------
	public void valueChanged( ListSelectionEvent evento )
    {
        if(list.isSelectionEmpty()){
        	seleccion = false;
        }else{
        	seleccion = true;
        }
    }
	
	//---------------------------------------------
	// Manejando los eventos de los botones
	//---------------------------------------------
    public void actionPerformed(ActionEvent evento)
    {	
		if((evento.getSource() == btnRevisar) && (seleccion == true))
		{   
			String[] nombre = list.getSelectedValue().split("   ");
			idEv = Integer.parseInt(nombre[0].trim());
			obtenerEvaluacion(idEv);
			
		}
		if((evento.getSource() == btnRevisar) && (seleccion != true))
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
