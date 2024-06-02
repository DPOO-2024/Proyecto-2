package InterfazPanelesPiezas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.MensajedeErrorException;

public class PanelImpresion extends JPanel{
	private JPanel panel;
	private JTextField material;
	private JTextField tam;
	private JTextField resolucion;
	private JTextField flexibilidad;
	private JTextField resistencia;
	private JTextField descripcion;


	public PanelImpresion() {
		panel = new JPanel(new GridLayout(6,2,20,20));
		
		JTextPane m1 = new JTextPane();
		m1.setText("Ingrese el material de papel de la Pieza: ");
		m1.setEditable(false);
		m1.setOpaque(false);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m1.setForeground(new Color(0, 90, 26));
		StyledDocument doc = m1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m1);
		
        material = new JTextField("");
        material.setSize(100, 20);
        material.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(material);
		
		JTextPane m2 = new JTextPane();
		m2.setText("Ingrese el tamaño de la Pieza (En pulgadas): ");
		m2.setEditable(false);
		m2.setOpaque(false);
		m2.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m2.setForeground(new Color(0, 90, 26));
		doc = m2.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m2);
		
		tam = new JTextField("");
		tam.setSize(100, 20);
		tam.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(tam);
		
		JTextPane m3 = new JTextPane();
		m3.setText("Ingrese la resolución de la Pieza (En ppp): ");
		m3.setEditable(false);
		m3.setOpaque(false);
		m3.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m3.setForeground(new Color(0, 90, 26));
		doc = m3.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m3);
		
		resolucion = new JTextField("");
		resolucion.setSize(100, 20);
		resolucion.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(resolucion);
		
		
		JTextPane m5 = new JTextPane();
		m5.setText("Ingrese la flexibilidad de la \nPieza (Alta, media, baja):  ");
		m5.setEditable(false);
		m5.setOpaque(false);
		m5.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m5.setForeground(new Color(0, 90, 26));
		doc = m5.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m5);
		
        flexibilidad = new JTextField("");
        flexibilidad.setSize(100, 20);
        flexibilidad.setFont(new Font("Nirmala UI",Font.PLAIN,18));
        panel.add(flexibilidad);

		
		JTextPane m6 = new JTextPane();
		m6.setText("Ingrese la resistencia de la \nPieza (Alta, media, baja): ");
		m6.setEditable(false);
		m6.setOpaque(false);
		m6.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m6.setForeground(new Color(0, 90, 26));
		doc = m6.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m6);
		
        resistencia = new JTextField("");
        resistencia.setSize(100, 20);
        resistencia.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(resistencia);
		
		JTextPane m7 = new JTextPane();
		m7.setText("Ingrese la resistencia de la \nPieza (Alta, media, baja): ");
		m7.setEditable(false);
		m7.setOpaque(false);
		m7.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m7.setForeground(new Color(0, 90, 26));
		doc = m7.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m7);
		
        descripcion = new JTextField("");
        descripcion.setSize(100, 20);
        descripcion.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(descripcion);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	
	public ArrayList<String> getInfo() throws MensajedeErrorException{
		if (material.getText().equals("") || tam.getText().equals("") || resolucion.getText().equals("") || 
				flexibilidad.getText().equals("") || resistencia.getText().equals("") || descripcion.getText().equals("")) {
			throw new MensajedeErrorException("No deje espacios en blanco");
		}
		ArrayList<String> resp = new ArrayList<String>(Arrays.asList(material.getText(),tam.getText(),
				resolucion.getText(),flexibilidad.getText(),resistencia.getText(),descripcion.getText()));		
		return resp;
	}

}
