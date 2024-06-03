package InterfazPanelesPiezas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.MensajedeErrorException;


public class PanelEscultura extends JPanel{
	private JPanel panel;
	private JTextField alto;
	private JTextField ancho;
	private JTextField profundidad;
	private JTextField materiales;
	private JTextField peso;
	private JTextField electricidad;
	private JTextField extra;
	
	public PanelEscultura() {
		panel = new JPanel(new GridLayout(7,2,20,20));
		
		JLabel m1 = new JLabel("Ingrese el alto de la Pieza:",JLabel.CENTER);;
		m1.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m1.setForeground(new Color(0, 90, 26));
		panel.add(m1);
		
		alto = new JTextField("");
		alto.setSize(100, 20);
		alto.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(alto);
		
		JLabel m2 = new JLabel("Ingrese el ancho de la Pieza: ",JLabel.CENTER);;
		m2.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m2.setForeground(new Color(0, 90, 26));
		panel.add(m2);
		
		ancho = new JTextField("");
		ancho.setSize(100, 20);
		ancho.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(ancho);
		
		JLabel m3 = new JLabel("Ingrese la profundidad de la Pieza: ",JLabel.CENTER);
		m3.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m3.setForeground(new Color(0, 90, 26));
		panel.add(m3);
		
		profundidad = new JTextField("");
		profundidad.setSize(100, 20);
		profundidad.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(profundidad);
		
		
		JTextPane m5 = new JTextPane();
		m5.setText("Ingrese los materiales \n(separados por comas) de la pieza: ");
		m5.setEditable(false);
		m5.setOpaque(false);
		m5.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m5.setForeground(new Color(0, 90, 26));
		StyledDocument doc = m5.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m5);
		
        materiales = new JTextField("");
        materiales.setSize(100, 20);
        materiales.setFont(new Font("Nirmala UI",Font.PLAIN,18));
        panel.add(materiales);
		
		
		JLabel m4 = new JLabel("Ingrese el peso de la Pieza: ",JLabel.CENTER);;
		m4.setFont(new Font ("Nirmala UI", Font.BOLD, 20));
		m4.setForeground(new Color(0, 90, 26));
		panel.add(m4);
		
		peso = new JTextField("");
		peso.setSize(100, 20);
		peso.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(peso);
		
		JTextPane m6 = new JTextPane();
		m6.setText("Ingrese si su Pieza \nrequiere electricidad (Si o No):");
		m6.setEditable(false);
		m6.setOpaque(false);
		m6.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m6.setForeground(new Color(0, 90, 26));
		doc = m6.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m6);
		
        electricidad = new JTextField("");
        electricidad.setSize(100, 20);
        electricidad.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(electricidad);
		
		JTextPane m7 = new JTextPane();
		m7.setText("Ingrese si hay alguna \nespecificacion de la instalación:  ");
		m7.setEditable(false);
		m7.setOpaque(false);
		m7.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m7.setForeground(new Color(0, 90, 26));
		doc = m7.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.add(m7);
		
        extra = new JTextField("");
        extra.setSize(100, 20);
        extra.setFont(new Font("Nirmala UI",Font.PLAIN,18));
        panel.add(extra);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public ArrayList<String> getInfo() throws MensajedeErrorException{
		if (alto.getText().equals("") || ancho.getText().equals("") || profundidad.getText().equals("") || 
			materiales.getText().equals("") || peso.getText().equals("") || electricidad.getText().equals("") || extra.getText().equals("")) {
			throw new MensajedeErrorException("No deje espacios en blanco");
		}
		ArrayList<String> resp = new ArrayList<String>(Arrays.asList(alto.getText(),ancho.getText(),profundidad.getText(),materiales.getText(),
						peso.getText(),electricidad.getText(),extra.getText()));		
		return resp;
	}

}
