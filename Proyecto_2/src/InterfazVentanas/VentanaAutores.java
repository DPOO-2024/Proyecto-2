package InterfazVentanas;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Modelo.Galeria;
import Piezas.Autor;

public class VentanaAutores extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<Autor> autores;
	private JList<String> lista;
	private Galeria mundo;
	

	public VentanaAutores( ArrayList<Autor> a, Galeria m) {
		mundo=m;
		autores=a;
		
		setTitle("Historial Autores");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel superior = new JPanel(new BorderLayout());
		JLabel t= new JLabel("Historial Autores",JLabel.CENTER);
		t.setFont(new Font("Nirmala UI",Font.BOLD,30));
		t.setForeground(new Color(0, 144, 41 ));
		superior.add(t,BorderLayout.CENTER);
		
		JLabel extra= new JLabel("Seleccione el autor del cual quiera ver el historial.",JLabel.CENTER);
		extra.setFont(new Font("Nirmala UI",Font.PLAIN,10));
		extra.setForeground(new Color(0, 144, 41 ));
		superior.add(extra,BorderLayout.SOUTH);
		
		add(superior,BorderLayout.NORTH);
		
		JPanel ordenar= new JPanel(new GridLayout(3,1,0,10));
		JLabel relleno1= new JLabel(" ");
		ordenar.add(relleno1);
		JPanel panelP = panelAutores();
		ordenar.add(panelP);
		JLabel relleno2= new JLabel(" ");
		ordenar.add(relleno2);
		add(ordenar, BorderLayout.CENTER);
		
		JButton continuar = new JButton("Ver información Autor");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("Info");
		continuar.addActionListener(this);
		
		add(continuar,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	//Crear panel con Piezas
	public JPanel panelAutores() {
		
		JPanel panel = new JPanel(new BorderLayout());
		JLabel t = new JLabel("Nombre del autor",JLabel.CENTER);
		t.setFont(new Font("Nirmala UI",Font.BOLD,25));
		t.setForeground(new Color(0, 144, 41 ));
		panel.add(t,BorderLayout.NORTH);

		DefaultListModel<String> titulos = new DefaultListModel<String>();
		int i=1;
		for (Autor a:autores) {
			String fila = Integer.toString(i)+ ". "+a.getNombre();
			titulos.addElement(fila);
			i++;
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

		JDialog info = new JDialog();
		info.setTitle("Información Autor");
		info.setSize(700, 500);
		info.setResizable(false);
		info.setLocationRelativeTo(null);
		info.setLayout(new BorderLayout());
		
		Autor a = autores.get(i);
		String infoAutor=Autor.mostrarHistorialArtista(mundo, a);
		
	
		JTextPane m1 = new JTextPane();
		m1.setText(infoAutor);
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
