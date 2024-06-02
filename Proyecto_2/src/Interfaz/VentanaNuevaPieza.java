package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.MensajedeErrorException;
import InterfazPanelesPiezas.PanelEscultura;

public class VentanaNuevaPieza extends JDialog implements ActionListener{
	private JPanel ventana;
	private JScrollPane panel;
	private ArrayList<String> datos;
	private JComboBox<String> tipo;
	private String t;
	private InterfazPropietario interfaz;
	private PanelEscultura infoEscultura;
	
	//Para guardar la informacion guardada
	private JTextField titulo;
	private JTextField anio;
	private JTextField lugar;
	private JTextField autores;
	private JTextField tipoModalidad;
	private JTextField valorinicial;
	private JTextField mostrar;
	private JTextField valorfijo;
	
	

	
	public VentanaNuevaPieza(InterfazPropietario i) {
		interfaz=i;

		setTitle("Agregar Pieza");
		setSize(800, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel arriba = new JPanel(new BorderLayout());
		JLabel tituloP = new JLabel("Agregar una Pieza",JLabel.CENTER);
		tituloP.setFont(new Font("Nirmala UI",Font.BOLD,30));
		tituloP.setForeground(new Color(0, 144, 41 ));
		arriba.add(tituloP,BorderLayout.CENTER);
		
		JPanel ordenar = new JPanel(new GridLayout(1,4,20,20));
		JLabel relleno= new JLabel(" Elija el ");
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,20));
		relleno.setForeground(new Color(0, 144, 41 ));
		ordenar.add(relleno);
		
		JLabel relleno1= new JLabel("tipo de Pieza: ",JLabel.CENTER);
		relleno1.setFont(new Font("Nirmala UI",Font.BOLD,20));
		relleno1.setForeground(new Color(0, 144, 41 ));
		ordenar.add(relleno1);
		
		tipo=new JComboBox<String>();
		tipo.addItem("");
		tipo.addItem("Escultura");
		tipo.addItem("Fotografia");
		tipo.addItem("Impresion");
		tipo.addItem("Pintura");
		tipo.addItem("Video");
		tipo.addItem("Otro");
		tipo.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
		tipo.setSize(100, 20);
		tipo.setActionCommand("Tipo");
		tipo.addActionListener(this);
		ordenar.add(tipo);
		
		JLabel relleno3= new JLabel(" ");
		ordenar.add(relleno3);
		
		arriba.add(ordenar,BorderLayout.SOUTH);
		
		add(arriba,BorderLayout.NORTH);
		
		ventana=new JPanel();
		panel = new JScrollPane(ventana);
		add(panel,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public void seleccionarTipo(String tipo) throws Exception {
		
		JPanel union= new JPanel(new GridLayout(2,1));
		union.add(general());
		if(tipo.equals("")) {
			throw new Exception();
		}else if(tipo.equals("Escultura")) {
			infoEscultura= new PanelEscultura();
			union.add(infoEscultura.getPanel());
		}else if(tipo.equals("Fotografia")) {
			//union.add(panelFotografia());
		}
		
		panel.removeAll();
		panel.revalidate();
		panel.add(ventana);
		panel.repaint();
		this.repaint();
		
		JButton continuar = new JButton("Agregar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("Continuar");
		continuar.addActionListener(this);
		
		add(continuar,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public String tipoElegido() {
		String ti = (String) tipo.getSelectedItem();
		return ti;

	}
	/*
	private JTextField titulo;
	private JTextField anio;
	private JTextField lugar;
	private JTextField autores;
	private JTextField tipoModalidad;
	private JTextField valorinicial;
	private JTextField mostrar;
	private JTextField valorfijo;
	public ArrayList<String> recogerInfo() {
		
		if (alto.getText().equals("") || ancho.getText().equals("") || profundidad.getText().equals("") || 
				materiales.getText().equals("") || peso.getText().equals("") || electricidad.getText().equals("") || extra.getText().equals("")) {
				throw new MensajedeErrorException("No deje espacios en blanco");
			}
			ArrayList<String> resp = new ArrayList<String>(Arrays.asList(alto.getText(),ancho.getText(),profundidad.getText(),materiales.getText(),
							peso.getText(),electricidad.getText(),extra.getText()));		
			return resp;
		
		if (t.equals("Escultura"))
		
	}
	*/
	
	public void llamarFuncion() {
		interfaz.agregarPieza();
	}
	
	public ArrayList<String> recogerDatos() {
		return this.datos;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Continuar")) {
			try {
				//datos = recogerInfo();
				this.dispose();	
				llamarFuncion();
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Ingrese una fecha valida","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(comando.equals("Tipo")) {
			t = tipoElegido();
			try {
				seleccionarTipo(t);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Elija un tipo de pieza valido","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public JPanel general() {
		JPanel general = new JPanel(new GridLayout(8,2,20,20));
		
		JLabel m1 = new JLabel("Ingrese el titulo:");;
		m1.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m1.setForeground(new Color(0, 90, 26));
		general.add(m1);
		
		titulo = new JTextField("");
		titulo.setSize(100, 20);
		titulo.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(titulo);
		
		JLabel m2 = new JLabel("Ingrese el año de creación:");;
		m2.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m2.setForeground(new Color(0, 90, 26));
		general.add(m2);
		
		anio = new JTextField("");
		anio.setSize(100, 20);
		anio.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(anio);
		
		JLabel m3 = new JLabel("Ingrese el lugar de creación:");;
		m3.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m3.setForeground(new Color(0, 90, 26));
		general.add(m3);
		
		lugar = new JTextField("");
		lugar.setSize(100, 20);
		lugar.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(lugar);
		
		JLabel m4 = new JLabel("Ingrese los autores (separados por comas):");;
		m4.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m4.setForeground(new Color(0, 90, 26));
		general.add(m4);
		
		autores = new JTextField("");
		autores.setSize(100, 20);
		autores.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(autores);
		
		JTextPane m5 = new JTextPane();
		m5.setText("Si desea aplicar la modalidad de \"marginalidad\" ingrese una fecha si no ingrese 0: ");
		m5.setEditable(false);
		m5.setFont(new Font("Nirmala UI",Font.PLAIN,20));
		m5.setForeground(new Color(0,59,20));
        StyledDocument doc = m5.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        general.add(m5);
		
        tipoModalidad = new JTextField("");
        tipoModalidad.setSize(100, 20);
        tipoModalidad.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(tipoModalidad);
		
		JTextPane m6 = new JTextPane();
		m6.setText("Ingrese el valor inicial de la pieza, si desea que sea incluida en una subasta, si no ingrese 0: ");
		m6.setEditable(false);
		m6.setFont(new Font("Nirmala UI",Font.PLAIN,20));
		m6.setForeground(new Color(0,59,20));
        StyledDocument doc1 = m6.getStyledDocument();
        SimpleAttributeSet center1 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc1.getLength(), center, false);
        general.add(m6);
		
        valorinicial = new JTextField("");
        valorinicial.setSize(100, 20);
        valorinicial.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(valorinicial);
		
		JTextPane m7 = new JTextPane();
		m7.setText("Ingrese  si desea mostrar su Pieza (Si o No): ");
		m7.setEditable(false);
		m7.setFont(new Font("Nirmala UI",Font.PLAIN,20));
		m7.setForeground(new Color(0,59,20));
        StyledDocument doc2 = m7.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc2.getLength(), center, false);
        general.add(m7);
		
        mostrar = new JTextField("");
        mostrar.setSize(100, 20);
        mostrar.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(mostrar);
		
		JTextPane m8 = new JTextPane();
		m8.setText("Ingrese el valor fijo al que desea vender la Pieza, si no desea que tenga valor fijo ingrese 0: ");
		m8.setEditable(false);
		m8.setFont(new Font("Nirmala UI",Font.PLAIN,20));
		m8.setForeground(new Color(0,59,20));
        StyledDocument doc3 = m8.getStyledDocument();
        SimpleAttributeSet center3 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center3, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc3.getLength(), center, false);
        general.add(m8);
		
        mostrar = new JTextField("");
        mostrar.setSize(100, 20);
        mostrar.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(mostrar);
		
		return general;
		
	}


}
