package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Piezas.Escultura;
import Piezas.Pieza;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class InterfazPropietario extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Galeria mundo;
	private InterfazBase base;
	private Propietario propietario;
	private JDialog ventanaTS;
	private JDialog ventanaA;
	private JTextField fecha;
	private JTextField login;
	private JTextField password;
	
	private static final String CERRAR="Cerrar Sesion";
	
	
	public InterfazPropietario(InterfazBase b,Galeria m,Propietario p) {
		mundo=m;
		base=b;
		propietario=p;
		
		setLayout(new BorderLayout());
		
		JLabel relleno= new JLabel(" Menú Administrador ",JLabel.CENTER);
		relleno.setFont(new Font ("Nirmala UI", Font.BOLD, 30));
		relleno.setForeground(new Color(0, 90, 26));
		add(relleno, BorderLayout.NORTH);
		
		JPanel botones = panelBoton();
		add(botones,BorderLayout.CENTER);
		
		JPanel boton=botonCerrarSesion();
		add(boton, BorderLayout.SOUTH);
			
		setVisible(true);
		
	}
	
	public JPanel panelBoton() {
		JPanel b= new JPanel();
		b.setLayout(new GridLayout(5, 1, 20, 20));
		
		JLabel relleno= new JLabel("");
		b.add(relleno);
		
		
		JButton guardarG=new JButton("Añadir Pieza");
		guardarG.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		guardarG.setForeground(Color.WHITE);
		guardarG.setPreferredSize(new Dimension(250,50));
		guardarG.setBackground(new Color(0, 144, 41));
		guardarG.setActionCommand("Agregar");
		guardarG.addActionListener(this);
		b.add(guardarG);
		
		JButton crearS=new JButton("Estado de Piezas Propias Disponibles");
		crearS.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		crearS.setForeground(Color.WHITE);
		crearS.setPreferredSize(new Dimension(250,50));
		crearS.setBackground(new Color(0, 144, 41));
		crearS.setActionCommand("Disponibles");
		crearS.addActionListener(this);
		b.add(crearS);
		
		JButton piezasD=new JButton("Ver Historial de Piezas Propias (No disponibles)");
		piezasD.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		piezasD.setForeground(Color.WHITE);
		piezasD.setPreferredSize(new Dimension(250,50));
		piezasD.setBackground(new Color(0, 144, 41));
		piezasD.setActionCommand("No Disponibles");
		piezasD.addActionListener(this);
		b.add(piezasD);
		
		return b;
	}
	
	public JPanel botonCerrarSesion() {
		JPanel o = new JPanel(new GridLayout(1,4));
		
		JLabel relleno= new JLabel(" ");
		o.add(relleno);
		JLabel relleno2= new JLabel(" ");
		o.add(relleno2);
		JLabel relleno3= new JLabel(" ");
		o.add(relleno3);
		
		JButton cerrar = new JButton("Cerrar Sesión");
		cerrar.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(220, 0, 0));
		cerrar.setActionCommand(CERRAR);
		cerrar.addActionListener(this);
		o.add(cerrar);
		return o;
	}
	
	public void agregarPieza(ArrayList<String> datos, String tipo) {

	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(CERRAR)) {
			int rta = JOptionPane.showConfirmDialog(null, "De verdad quiere cerrar sesion?",
					"Cerrar sesión", JOptionPane.YES_NO_OPTION);
			if (rta == JOptionPane.OK_OPTION) {
				base.interfazIS();
			}
			
		}else if (comando.equals("Agregar")) {
			VentanaNuevaPieza ventana = new VentanaNuevaPieza(this);
			ventana.setVisible(true);
		}else if (comando.equals("Disponibles")) {
			ArrayList<Pieza> piezas = propietario.getEstadoPiezas();
			VentanaPiezas ventana;
			try {
				ventana = new VentanaPiezas(piezas,"Piezas Propias Disponibles","Elija la pieza de la cual quiera ver información General",mundo);
				ventana.setVisible(true);
			} catch (MensajedeErrorException e1) {
				JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (comando.equals("No Disponibles")) {
			ArrayList<Pieza> piezas = propietario.getHistorialPiezas();
			VentanaPiezas ventana;
			try {
				ventana = new VentanaPiezas(piezas,"Piezas Propias Disponibles","Elija la pieza de la cual quiera ver información General",mundo);
				ventana.setVisible(true);
			} catch (MensajedeErrorException e1) {
				JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
