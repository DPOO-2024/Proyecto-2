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

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Piezas.Pieza;

public class interfazAdministrador extends JPanel implements ActionListener{
	private Galeria mundo;
	private interfazBase base;
	
	private static final String CERRAR="Cerrar Sesion";
	
	
	public interfazAdministrador(interfazBase b,Galeria m) {
		mundo=m;
		base=b;
		
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
		b.setLayout(new GridLayout(6, 2, 20, 20));
		
		JButton guardarG=new JButton("Guardar Galeria");
		guardarG.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		guardarG.setForeground(Color.WHITE);
		guardarG.setPreferredSize(new Dimension(250,50));
		guardarG.setBackground(new Color(0, 144, 41));
		guardarG.setActionCommand("Guardar Galeria");
		guardarG.addActionListener(this);
		b.add(guardarG);
		
		JButton crearS=new JButton("Crear Subasta");
		crearS.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		crearS.setForeground(Color.WHITE);
		crearS.setPreferredSize(new Dimension(250,50));
		crearS.setBackground(new Color(0, 144, 41));
		crearS.setActionCommand("Crear Subasta");
		crearS.addActionListener(this);
		b.add(crearS);
		
		JButton agregarE=new JButton("Agregar Empleado");
		agregarE.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		agregarE.setForeground(Color.WHITE);
		agregarE.setPreferredSize(new Dimension(250,50));
		agregarE.setBackground(new Color(0, 144, 41));
		agregarE.setActionCommand("Agregar Empleado");
		agregarE.addActionListener(this);
		b.add(agregarE);
		
		JButton piezasD=new JButton("Piezas Disponibles");
		piezasD.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		piezasD.setForeground(Color.WHITE);
		piezasD.setPreferredSize(new Dimension(250,50));
		piezasD.setBackground(new Color(0, 144, 41));
		piezasD.setActionCommand("Piezas Disponibles");
		piezasD.addActionListener(this);
		b.add(piezasD);
		
		JButton piezasH=new JButton("Historial de piezas \n (No disponibles)");
		piezasH.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		piezasH.setForeground(Color.WHITE);
		piezasH.setPreferredSize(new Dimension(250,50));
		piezasH.setBackground(new Color(0, 144, 41));
		piezasH.setActionCommand("Piezas Historial");
		piezasH.addActionListener(this);
		b.add(piezasH);
		
		JButton historialP=new JButton("Historial de una Pieza");
		historialP.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		historialP.setForeground(Color.WHITE);
		historialP.setPreferredSize(new Dimension(250,50));
		historialP.setBackground(new Color(0, 144, 41));
		historialP.setActionCommand("Historial Pieza");
		historialP.addActionListener(this);
		b.add(historialP);
		
		JButton historialA=new JButton("Historial de un Artista");
		historialA.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		historialA.setForeground(Color.WHITE);
		historialA.setPreferredSize(new Dimension(250,50));
		historialA.setBackground(new Color(0, 144, 41));
		historialA.setActionCommand("Historial Artista");
		historialA.addActionListener(this);
		b.add(historialA);
		
		JButton historialC=new JButton("Historial de un Cliente");
		historialC.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		historialC.setForeground(Color.WHITE);
		historialC.setPreferredSize(new Dimension(250,50));
		historialC.setBackground(new Color(0, 144, 41));
		historialC.setActionCommand("Historial Cliente");
		historialC.addActionListener(this);
		b.add(historialC);
		
		JButton terminarS=new JButton("Terminar Subasta");
		terminarS.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		terminarS.setForeground(Color.WHITE);
		terminarS.setPreferredSize(new Dimension(250,50));
		terminarS.setBackground(new Color(0, 144, 41));
		terminarS.setActionCommand("Terminar Subasta");
		terminarS.addActionListener(this);
		b.add(terminarS);
		
		JButton asignarC=new JButton("Asignar Cajero");
		asignarC.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		asignarC.setForeground(Color.WHITE);
		asignarC.setPreferredSize(new Dimension(250,50));
		asignarC.setBackground(new Color(0, 144, 41));
		asignarC.setActionCommand("Cajero");
		asignarC.addActionListener(this);
		b.add(asignarC);
		
		JButton asignarA=new JButton("Asignar nuevo \n Administrador");
		asignarA.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		asignarA.setForeground(Color.WHITE);
		asignarA.setPreferredSize(new Dimension(250,50));
		asignarA.setBackground(new Color(0, 144, 41));
		asignarA.setActionCommand("Admin");
		asignarA.addActionListener(this);
		b.add(asignarA);
		
		return b;
	}



	//Boton cerrar sesion
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
	
	//Crear Subasta
	public void crearSubasta(VentanasInfoSubasta ventana) {
		String[] datos=ventana.recogerDatos();
		try {
			mundo.crearSubasta(Integer.parseInt(datos[0]), datos[1]);
			JOptionPane.showMessageDialog(null, "La subasta se creo correctamente","Subasta",JOptionPane.INFORMATION_MESSAGE);
		}catch (MensajedeErrorException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo crear la Subasta","Error",JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void agregarEmpleado(VentanaInfoUsuarios ventana) {
		String[] datos=ventana.recogerDatos();
		try {
			mundo.agregarEmpleado(datos[0], datos[1],datos[2]);
			JOptionPane.showMessageDialog(null, "La subasta se creo correctamente","Subasta",JOptionPane.INFORMATION_MESSAGE);
		}catch (MensajedeErrorException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(CERRAR)) {
			int rta = JOptionPane.showConfirmDialog(null, "De verdad quiere cerrar sesion?",
					"Cerrar sesión", JOptionPane.YES_NO_OPTION);
			if (rta == JOptionPane.OK_OPTION) {
				base.interfazIS();
			}
			
		}else if (comando.equals("Guardar Galeria")) {
			try {
				mundo.guardarGaleria();
				JOptionPane.showMessageDialog(null, "Se guardó la galeria","Guardar Galeria" , JOptionPane.INFORMATION_MESSAGE);
			}catch (Exception err) {
				JOptionPane.showMessageDialog(null, "No se pudo guardar la galeria","Error" , JOptionPane.ERROR_MESSAGE);
			}
		}else if (comando.equals("Crear Subasta")) {
			VentanasInfoSubasta ventana = new VentanasInfoSubasta("Crear Subasta","Crear Subasta","Crear",this);
			ventana.setVisible(true);
		}else if (comando.equals("Agregar Empleado")) {
			VentanaInfoUsuarios ventana = new VentanaInfoUsuarios("Añadir Empleado","Agregar Empleado","Añadir",this);
			ventana.setVisible(true);		
		}else if (comando.equals("Piezas Disponibles")) {
			ArrayList<Pieza> piezas = mundo.mostrarPiezasDisponibles();
			VentanaPiezas ventana = new VentanaPiezas(this,piezas,"Piezas Disponibles");
			ventana.setVisible(true);
			
		}else if (comando.equals("Piezas Historial")) {

		}else if (comando.equals("Historial Pieza")) {

		}else if (comando.equals("Historial Artista")) {

		}else if (comando.equals("Historial Cliente")) {

		}else if (comando.equals("Terminar Subasta")) {

		}else if (comando.equals("Cajero")) {

		}else if (comando.equals("Admin")) {

		}
		
		
	}


	
}
