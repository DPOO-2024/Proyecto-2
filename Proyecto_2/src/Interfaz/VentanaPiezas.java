package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Piezas.Pieza;

public class VentanaPiezas extends JDialog implements ActionListener {
	//private interfazAdministrador interfaz;
	private ArrayList<Pieza> piezas;
	private JList<String> lista;
	private String t;
	private Galeria mundo;
	
	//public VentanaPiezas(interfazAdministrador i, ArrayList<Pieza> p, String titulo) {
	public VentanaPiezas( ArrayList<Pieza> p, String titulo, String ext, Galeria m) throws MensajedeErrorException {
		//interfaz=i;
		mundo=m;
		t=titulo;
		piezas=p;
		
		setTitle(titulo);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel superior = new JPanel(new BorderLayout());
		JLabel t= new JLabel(titulo,JLabel.CENTER);
		t.setFont(new Font("Nirmala UI",Font.BOLD,30));
		t.setForeground(new Color(0, 144, 41 ));
		superior.add(t,BorderLayout.CENTER);
		
		JLabel extra= new JLabel(ext,JLabel.CENTER);
		extra.setFont(new Font("Nirmala UI",Font.PLAIN,10));
		extra.setForeground(new Color(0, 144, 41 ));
		superior.add(extra,BorderLayout.SOUTH);
		
		
		add(superior,BorderLayout.NORTH);
		
		JPanel ordenar= new JPanel(new GridLayout(3,1,0,10));
		JLabel relleno1= new JLabel(" ");
		ordenar.add(relleno1);
		JPanel panelP = panelPiezas();
		ordenar.add(panelP);
		JLabel relleno2= new JLabel(" ");
		ordenar.add(relleno2);
		add(ordenar, BorderLayout.CENTER);
		
		JButton continuar = new JButton("Ver información Pieza");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("Info");
		continuar.addActionListener(this);
		
		add(continuar,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	//Crear panel con Piezas
	public JPanel panelPiezas() throws MensajedeErrorException {
		
		JPanel panel = new JPanel(new BorderLayout());
		JLabel t = new JLabel("Tipo de Pieza -> Titulo de la Pieza",JLabel.CENTER);
		t.setFont(new Font("Nirmala UI",Font.BOLD,25));
		t.setForeground(new Color(0, 144, 41 ));
		panel.add(t,BorderLayout.NORTH);

		DefaultListModel<String> titulos = new DefaultListModel<String>();
		int i=1;
		if (piezas.isEmpty()) {
			throw new MensajedeErrorException("No hay piezas disponibles");
		}
		else {
			for (Pieza p:piezas) {
				String fila = Integer.toString(i)+ ". "+p.getTipoPieza()+" -> "+p.getTitulo();
				titulos.addElement(fila);
				i++;
			}
		}
		
		lista = new JList<String>(titulos);
		lista.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lista.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		JScrollPane scroll = new JScrollPane(lista);
		panel.add(scroll,BorderLayout.CENTER);
		
		return panel;
	}
	
	
	//Mostrar info de Pieza seleccionada
	public void mostrarInfo() {
		int i = lista.getSelectedIndex();
		String val = (String) lista.getSelectedValue();
		
		JDialog info = new JDialog();
		info.setTitle("Información Pieza");
		info.setSize(700, 500);
		info.setResizable(false);
		info.setLocationRelativeTo(null);
		info.setLayout(new BorderLayout());
		
		Pieza p = piezas.get(i);
		String infoPieza="";
		
		if (t.equals("Piezas Disponibles")||t.equals("Historial Piezas (No disponibles)")
				||t.equals("Piezas Propias Disponibles")||t.equals("Piezas Propias No Disponibles")) {
			infoPieza=Pieza.imprimirPieza(p);
		}else if (t.equals("Historial de una Pieza")) {
			infoPieza=Pieza.mostrarHistorialPieza(mundo,p);
		}
		
	
		JTextPane m1 = new JTextPane();
		m1.setText(infoPieza);
		m1.setEditable(false);
		m1.setFont(new Font("Nirmala UI",Font.PLAIN,20));
        m1.setForeground(new Color(0,59,20));
        StyledDocument doc = m1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        JScrollPane texto = new JScrollPane(m1);
        info.add(texto,BorderLayout.CENTER);
        
		info.setVisible(true);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		 if (comando.equals("Info")) {
			 mostrarInfo();
		 }
			
			
	}

}
