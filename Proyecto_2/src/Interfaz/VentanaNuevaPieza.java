package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.MensajedeErrorException;
import InterfazPanelesPiezas.PanelEscultura;
import InterfazPanelesPiezas.PanelFotografia;
import InterfazPanelesPiezas.PanelImpresion;
import InterfazPanelesPiezas.PanelOtro;
import InterfazPanelesPiezas.PanelPintura;
import InterfazPanelesPiezas.PanelVideo;

public class VentanaNuevaPieza extends JDialog implements ActionListener{
	private JPanel ventana;
	private JPanel afuera;
	private JScrollPane panel;
	private ArrayList<String> datos;
	private JComboBox<String> tipo;
	private String t;
	private InterfazPropietario interfaz;
	private PanelEscultura infoEscultura;
	private PanelFotografia infoFotografia;
	private PanelImpresion infoImpresion;
	private PanelPintura infoPintura;
	private PanelVideo infoVideo;
	private PanelOtro infoOtro;
	
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
		setSize(950, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel arriba = new JPanel(new BorderLayout());
		JLabel tituloP = new JLabel("Agregar una Pieza",JLabel.CENTER);
		tituloP.setFont(new Font("Nirmala UI",Font.BOLD,30));
		tituloP.setForeground(new Color(0, 144, 41 ));
		arriba.add(tituloP,BorderLayout.CENTER);
		
		JPanel ordenar = new JPanel(new GridLayout(1,4,0,20));
		JLabel relleno= new JLabel(" Elija el ",JLabel.RIGHT);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,20));
		relleno.setForeground(new Color(0, 144, 41 ));
		ordenar.add(relleno);
		
		JLabel relleno1= new JLabel("tipo de Pieza: ",JLabel.LEFT);
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
		afuera = new JPanel(new GridLayout(1,1));
		afuera.add(panel);
		add(afuera,BorderLayout.CENTER);
		
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
			infoFotografia= new PanelFotografia();
			union.add(infoFotografia.getPanel());
		}else if(tipo.equals("Impresion")) {
			infoImpresion= new PanelImpresion();
			union.add(infoImpresion.getPanel());
		}else if(tipo.equals("Pintura")) {
			infoPintura= new PanelPintura();
			union.add(infoPintura.getPanel());
		}else if(tipo.equals("Video")) {
			infoVideo= new PanelVideo();
			union.add(infoVideo.getPanel());
		}else if(tipo.equals("Otro")) {
			infoOtro= new PanelOtro();
			union.add(infoOtro.getPanel());
		}
		
		
		ventana.removeAll();
		ventana.revalidate();
		ventana.add(union);
		ventana.repaint();
		
		panel.repaint();
		
		afuera.repaint();
		
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
	
	
	public ArrayList<String> recogerInfo() throws MensajedeErrorException {
		ArrayList<String> i=new ArrayList<String>();
		
		if (titulo.getText().equals("") || anio.getText().equals("") || lugar.getText().equals("") || 
				autores.getText().equals("") || tipoModalidad.getText().equals("") || valorinicial.getText().equals("") 
				|| mostrar.getText().equals("")|| valorfijo.getText().equals("")) {
				throw new MensajedeErrorException("No deje espacios en blanco");
			}
			ArrayList<String> resp = new ArrayList<String>(Arrays.asList( titulo.getText(),anio.getText(),lugar.getText(),autores.getText(),
					tipoModalidad.getText(),valorinicial.getText(), mostrar.getText(),valorfijo.getText()));		
		
		if (t.equals("Escultura")) {
			i = infoEscultura.getInfo();
		}else if (t.equals("Fotografia")) {
			i = infoFotografia.getInfo();
		}else if (t.equals("Impresion")) {
			i = infoImpresion.getInfo();
		}else if (t.equals("Pintura")) {
			i = infoPintura.getInfo();
		}else if (t.equals("Pintura")) {
			i = infoVideo.getInfo();
		}else if (t.equals("Otro")) {
			i = infoOtro.getInfo();
		}
		
		resp.addAll(i);
		
		return resp;
	}
	
	
	public void llamarFuncion() {
		interfaz.agregarPieza(datos,t);
	}
	
	public ArrayList<String> recogerDatos() {
		return this.datos;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Continuar")) {
			try {
				datos = recogerInfo();
				this.dispose();	
				llamarFuncion();
			}catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
		
		JLabel m1 = new JLabel("Ingrese el titulo:",JLabel.CENTER);;
		m1.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m1.setForeground(new Color(0, 90, 26));
		general.add(m1);
		
		titulo = new JTextField("");
		titulo.setSize(100, 20);
		titulo.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(titulo);
		
		JLabel m2 = new JLabel("Ingrese el año de creación:",JLabel.CENTER);;
		m2.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m2.setForeground(new Color(0, 90, 26));
		general.add(m2);
		
		anio = new JTextField("");
		anio.setSize(100, 20);
		anio.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(anio);
		
		JLabel m3 = new JLabel("Ingrese el lugar de creación:",JLabel.CENTER);;
		m3.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m3.setForeground(new Color(0, 90, 26));
		general.add(m3);
		
		lugar = new JTextField("");
		lugar.setSize(100, 20);
		lugar.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(lugar);
		
		JLabel m4 = new JLabel("Ingrese los autores (separados por comas/Anonimo):",JLabel.CENTER);;
		m4.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m4.setForeground(new Color(0, 90, 26));
		general.add(m4);
		
		autores = new JTextField("");
		autores.setSize(100, 20);
		autores.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(autores);
		
		JTextPane m5 = new JTextPane();
		m5.setText("Si desea aplicar la modalidad de \n\"marginalidad\" ingrese una fecha si \n no ingrese 0: ");
		m5.setEditable(false);
		m5.setOpaque(false);
		m5.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m5.setForeground(new Color(0, 90, 26));
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
		m6.setText("Ingrese el valor inicial de la pieza, \nsi desea que sea incluida en \nuna subasta, si no ingrese 0: ");
		m6.setEditable(false);
		m6.setOpaque(false);
		m6.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m6.setForeground(new Color(0, 90, 26));
        doc = m6.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        general.add(m6);
		
        valorinicial = new JTextField("");
        valorinicial.setSize(100, 20);
        valorinicial.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(valorinicial);
		
		JTextPane m7 = new JTextPane();
		m7.setText("Ingrese  si desea mostrar su Pieza (Si o No): ");
		m7.setEditable(false);
		m7.setOpaque(false);
		m7.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m7.setForeground(new Color(0, 90, 26));
        doc = m7.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        general.add(m7);
		
        mostrar = new JTextField("");
        mostrar.setSize(100, 20);
        mostrar.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(mostrar);
		
		JTextPane m8 = new JTextPane();
		m8.setText("Ingrese el valor fijo al que desea \nvender la Pieza, si no desea que \ntenga valor fijo ingrese 0: ");
		m8.setEditable(false);
		m8.setOpaque(false);
		m8.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m8.setForeground(new Color(0, 90, 26));
        doc = m8.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        general.add(m8);
		
        valorfijo = new JTextField("");
        valorfijo.setSize(100, 20);
        valorfijo.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		general.add(valorfijo);
		
		return general;
		
	}


}
