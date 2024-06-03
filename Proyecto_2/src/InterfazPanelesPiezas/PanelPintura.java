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

public class PanelPintura extends JPanel{
	private JPanel panel;
	private JTextField tecnica;
	private JTextField origen;
	private JTextField descripcion;
	private JTextField alto;
	private JTextField ancho;
	private JTextField forma;
	private JTextField tiempo;

	public PanelPintura() {
		panel = new JPanel(new GridLayout(7,2,20,20));
		
		JTextPane m1 = new JTextPane();
		m1.setText("Ingrese la tecnica de la Pieza: ");
		m1.setEditable(false);
		m1.setOpaque(false);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m1.setForeground(new Color(0, 90, 26));
		StyledDocument doc = m1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m1);
		
        tecnica = new JTextField("");
        tecnica.setSize(100, 20);
        tecnica.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(tecnica);
		
		JTextPane m2 = new JTextPane();
		m2.setText("Ingrese el origen de la Pieza: ");
		m2.setEditable(false);
		m2.setOpaque(false);
		m2.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m2.setForeground(new Color(0, 90, 26));
		doc = m2.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m2);
		
		origen = new JTextField("");
		origen.setSize(100, 20);
		origen.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(origen);
		
		JTextPane m3 = new JTextPane();
		m3.setText("Ingrese la descripcion de la Pieza: ");
		m3.setEditable(false);
		m3.setOpaque(false);
		m3.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m3.setForeground(new Color(0, 90, 26));
		doc = m3.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m3);
		
		descripcion = new JTextField("");
		descripcion.setSize(100, 20);
		descripcion.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(descripcion);
		
		
		JTextPane m5 = new JTextPane();
		m5.setText("Ingrese la altura de la Pieza (En pulgadas): ");
		m5.setEditable(false);
		m5.setOpaque(false);
		m5.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m5.setForeground(new Color(0, 90, 26));
		doc = m5.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m5);
		
        alto = new JTextField("");
        alto.setSize(100, 20);
        alto.setFont(new Font("Nirmala UI",Font.PLAIN,18));
        panel.add(alto);

		
		JTextPane m6 = new JTextPane();
		m6.setText("Ingrese el ancho de la Pieza (En pulgadas): ");
		m6.setEditable(false);
		m6.setOpaque(false);
		m6.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m6.setForeground(new Color(0, 90, 26));
		doc = m6.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m6);
		
        ancho = new JTextField("");
        ancho.setSize(100, 20);
        ancho.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(ancho);
		
		JTextPane m7 = new JTextPane();
		m7.setText("Ingrese la forma de la Pieza (Cuadrada, Rectancular, etc): ");
		m7.setEditable(false);
		m7.setOpaque(false);
		m7.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m7.setForeground(new Color(0, 90, 26));
		doc = m7.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m7);
		
        forma = new JTextField("");
        forma.setSize(100, 20);
        forma.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(forma);
		
		JTextPane m8 = new JTextPane();
		m8.setText("Ingrese el tiempo de creación de la Pieza:  ");
		m8.setEditable(false);
		m8.setOpaque(false);
		m8.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m8.setForeground(new Color(0, 90, 26));
		doc = m8.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m8);
		
        tiempo = new JTextField("");
        tiempo.setSize(100, 20);
        tiempo.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(tiempo);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}

	public ArrayList<String> getInfo() throws MensajedeErrorException{
		if (tecnica.getText().equals("") || origen.getText().equals("") || descripcion.getText().equals("") || 
				alto.getText().equals("") || ancho.getText().equals("")|| forma.getText().equals("")|| tiempo.getText().equals("")) {
			throw new MensajedeErrorException("No deje espacios en blanco");
		}
		ArrayList<String> resp = new ArrayList<String>(Arrays.asList(tecnica.getText(),origen.getText(),
				descripcion.getText(),alto.getText(),ancho.getText(),forma.getText(),tiempo.getText()));		
		return resp;
	}

}