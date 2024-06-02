package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Modelo.Subasta;
import Piezas.Pieza;

public class VentanaParticiparSubasta extends JDialog implements ActionListener {
	private JDialog pedirfecha;
	private JTextField fecha;
	private Galeria principal;
	private Subasta subasta;
	private InterfazComprador intCom;
	private static final String CERRAR="Cerrar";

	public VentanaParticiparSubasta(InterfazComprador interfazComprador, Galeria gal) {
		this.principal=gal;
		this.intCom=interfazComprador;
		
		
		setTitle("Participar Subasta");
		setSize(500, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		pedirfecha = new JDialog();
		pedirfecha.setTitle("fecha subasta");
		pedirfecha.setSize(600, 700);
		pedirfecha.setLocationRelativeTo(null); 
		pedirfecha.setLayout(new GridLayout(2, 1, 20, 20));
		JPanel info = new JPanel(new GridLayout(1,2));
		JTextArea relleno = new JTextArea("Ingrese la fecha (AAMMDD) de la subasta \n en la que quiere hacer una oferta : ");
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,15));
		relleno.setForeground(new Color(0, 144, 41 ));
		info.add(relleno);
		fecha = new JTextField("");
		fecha.setSize(100, 20);
		fecha.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		info.add(fecha);
		pedirfecha.add(info);
		JButton aceptar = new JButton("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setPreferredSize(new Dimension(100,15));
		aceptar.setBackground(new Color(0, 90, 26));
		aceptar.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		pedirfecha.add(aceptar);
		pedirfecha.setVisible(true);
		
		JButton piezas = new JButton(" Ver Piezas Disponibles en esta subasta");
		piezas.setForeground(Color.WHITE);
		piezas.setPreferredSize(new Dimension(100,30));
		piezas.setBackground(new Color(0, 90, 26));
		piezas.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		piezas.setActionCommand("Piezas subasta");
		piezas.addActionListener(this);
		add(piezas,BorderLayout.NORTH);
		
		JPanel completar = new JPanel(new GridLayout(2,2));
		
		
		
		add(completar,BorderLayout.CENTER);
		
		
		JButton cerrar = new JButton("Salir");
		cerrar.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(220, 0, 0));
		cerrar.setActionCommand(CERRAR);
		cerrar.addActionListener(this);
		add(cerrar,BorderLayout.SOUTH);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("aceptar")) {
			try {
				subasta = principal.participarSubasta(Integer.parseInt(fecha.getText()), intCom.getComprador(), 0);
				pedirfecha.dispose();
			}catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				pedirfecha.dispose();
				this.dispose();
				
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se encontro una Subasta en esa fecha","Error",JOptionPane.ERROR_MESSAGE);
				pedirfecha.dispose();
				this.dispose();
			}
		}
		else if (comando.equals("Piezas subasta")) {
			ArrayList<Pieza> piezas = (ArrayList<Pieza>) subasta.getInventario();
			VentanaPiezas ventana = new VentanaPiezas(piezas,"Piezas Disponibles subasta","Elija la pieza de la cual quiera ver información General",principal);
			ventana.setVisible(true);
			
			
		}
		
		else if (comando.equals(CERRAR)) {
			int rta = JOptionPane.showConfirmDialog(null, "De verdad quiere salirte de la subasta?",
					"Cerrar sesión", JOptionPane.YES_NO_OPTION);
			if (rta == JOptionPane.OK_OPTION) {
				subasta.quitarComprador(intCom.getComprador());
				this.dispose();}
		}
		
	}

}
