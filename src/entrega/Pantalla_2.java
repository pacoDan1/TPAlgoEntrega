package entrega;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algoii.tp.db.estudiantes.Estudiante;
import algoii.tp.db.estudiantes.EstudiantesDB;
import algoii.tp.db.estudiantes.Nota;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Pantalla_2 extends JFrame {

	private JPanel contentPane;
	private   JTable table;
	
	public Pantalla_1 pantallaAnterior = new Pantalla_1();//seteado para  probar 
	
	public Object[][] data; 
//	public  List<Nota> notas;
	
	public Pantalla_2 pantallaActual = this;
	
	JScrollPane scrollPane =null;//= new JScrollPane(table); 
	
	List<Nota> notasNuevasModificadas ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla_2 frame = new Pantalla_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pantalla_2() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNotasDe = new JLabel("Notas de : "+alumnoSeleccionado().getNombre());
		lblNotasDe.setBounds(35, 11, 200, 14);
		contentPane.add(lblNotasDe);
		
		table =crearTabla();
//		table.addInputMethodListener(new InputMethodListener() {
//			public void caretPositionChanged(InputMethodEvent event) {
//			}
//			public void inputMethodTextChanged(InputMethodEvent event) {
//			}
//		});
		table.setBounds(197, 62, 145, 120);
		contentPane.add(table);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 62, 330, 120);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table); // 22:36
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // 22:36
	
	
		
		JButton botonAnterior = new JButton("Siguiente");
		botonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarYMostrarTabla();
				pantallaAnterior.toFront();
			}
		});
		botonAnterior.setBounds(68, 204, 145, 23);
		contentPane.add(botonAnterior);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(345, 238, 89, 23);
		contentPane.add(btnSalir);
		
		
		capturarEventoEnTabla();
//		
	
		
	}
	private JTable crearTabla() {
		 data= crearMatrizDeNotas();
	     String[] columnNames = {"Numero de Examen","Nota En Letras","Nota Numerica "};
	    return new JTable(data, columnNames);
	}
	public Object[][] crearMatrizDeNotas(){
//		notas = estudiantes().obtenerNotas(legajoSeleccionado());
		Object[][] matriz = {
				{notas().get(0).getNroExamen(),notas().get(0).getDescrLetras(),notas().get(0).getValor()},
				{notas().get(1).getNroExamen(),notas().get(1).getDescrLetras(),notas().get(1).getValor()},
				{notas().get(2).getNroExamen(),notas().get(2).getDescrLetras(),notas().get(2).getValor()}		
		}; // con for(;;) tira error a morir
		
		return matriz;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Pantalla_1 getPantallaAnterior() {
		return pantallaAnterior;
	}

	public void setPantallaAnterior(Pantalla_1 pantallaAnterior) {
		this.pantallaAnterior = pantallaAnterior;
	}
	private EstudiantesDB estudiantes() {
		return pantallaAnterior.getEstudiantes();
	}
	private Estudiante alumnoSeleccionado() {
		return pantallaAnterior.getAlumno();
	}
	private int legajoSeleccionado() {
		return pantallaAnterior.getLegajo();
	}
	public void setLegajoSeleccionado(int legajo){
		pantallaAnterior.setLegajo(legajo);
	}

	
	void capturarEventoEnTabla() {	
		table.setPreferredScrollableViewportSize(new Dimension(500, 75));
        if (true){
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    actualizarYMostrarTabla();
                }
            });
        }
        //Create the scroll pane and add the table to it. 
//        JScrollPane scrollPane1 = new JScrollPane(table);
        //Add the scroll pane to this window.
//        setContentPane(scrollPane);   si lo descomento superpondra otro scrollPane y no se vera el label y el boton 
        /*addWindowListener(new WindowAdapter() {
          *  public void windowClosing(WindowEvent e) {
           *     System.exit(0);
            *}
       * }); */
	}
//	@SuppressWarnings({ "unused", "null" })
//	@SuppressWarnings("null")
	private void actualizarYMostrarTabla(){
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        
        notasNuevasModificadas=alumnoSeleccionado().getNotas();
        
//        System.out.println("Value of data: ");
//        for (int i=0; i < numRows; i++) {
//            System.out.print("   fila  " + i + ":");
//            for (int j=0; j < numCols; j++) {
//                System.out.print("  " + model.getValueAt(i, j));
//                data[i][j]=model.getValueAt(i,j);
//                if(i==1)letras=model.getValueAt(i, j).toString();
//                if(i==0)nroExamen=Integer.parseInt( model.getValueAt(i, j).toString());
//                if(i==2)valor=Integer.parseInt( model.getValueAt(i, j).toString());            
//            }
//            System.out.println();
//            notasNuevas.add(new Nota((int)nroExamen, letras,(int) valor));
//        }
        int nroExamen =0,valor = 0;String letras =""; 

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
//                if(j==0)nroExamen=Integer.parseInt(model.getValueAt(i, j).toString());
                if(j==0)nroExamen=Integer.valueOf( model.getValueAt(i, j).toString());
                if(j==1)letras= model.getValueAt(i, j).toString();
//                if(j==2)valor=Integer.parseInt(model.getValueAt(i, j).toString());         
                if(j==2)valor=Integer.valueOf( model.getValueAt(i, j).toString());                     
            }
            System.out.println();
            notasNuevasModificadas.set(1,new Nota(nroExamen, letras, valor));
//              notasNuevasModificadas.set(i, new Nota(nroExamen, letras, valor));       
//            mostrar(notasNuevas.get(i).toString());
        }
//        estudiantes().modificarNotasEstudiante(legajoSeleccionado(),notasNuevasModificadas );
    
        alumnoSeleccionado().setNotas(notasNuevasModificadas);
        
        System.out.println("-------------------------------------");
        
	}
	private void mostrar(Object s) {
		System.out.println(s);
	}

	public List<Nota> notas() {
		return alumnoSeleccionado().getNotas();
	}

	public void setNotas(List<Nota> notas) {
		this.alumnoSeleccionado().setNotas(notas);
	}
}
