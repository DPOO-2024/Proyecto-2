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
import javax.swing.JTextField;

import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Galeria;
import Piezas.Autor;
import Piezas.Pieza;
import Usuarios.Comprador;

public class InterfazEmpleado extends JPanel implements ActionListener {
		private Galeria mundo;
		private Empleado empleado;
		private InterfazBase base;
		
		private static final String CERRAR="Cerrar Sesion";
		
		
		public InterfazEmpleado(InterfazBase b,Galeria m,Empleado e) {
			mundo=m;
			base=b;
			empleado=e;
			
			setLayout(new BorderLayout());
			
			JLabel relleno= new JLabel(" Menú Empleado ",JLabel.CENTER);
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
			b.setLayout(new GridLayout(5, 2, 20, 20));
			
			JLabel relleno= new JLabel("");
			b.add(relleno);
			
			JLabel relleno2= new JLabel("");
			b.add(relleno2);
			
			JButton guardarG=new JButton("Guardar Galeria");
			guardarG.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
			guardarG.setForeground(Color.WHITE);
			guardarG.setPreferredSize(new Dimension(250,50));
			guardarG.setBackground(new Color(0, 144, 41));
			guardarG.setActionCommand("Guardar Galeria");
			guardarG.addActionListener(this);
			b.add(guardarG);
			
			JButton crearS=new JButton("Tipo de empleado");
			crearS.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
			crearS.setForeground(Color.WHITE);
			crearS.setPreferredSize(new Dimension(250,50));
			crearS.setBackground(new Color(0, 144, 41));
			crearS.setActionCommand("Tipo de empleado");
			crearS.addActionListener(this);
			b.add(crearS);
			
			JButton piezasD=new JButton("Piezas Disponibles");
			piezasD.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
			piezasD.setForeground(Color.WHITE);
			piezasD.setPreferredSize(new Dimension(250,50));
			piezasD.setBackground(new Color(0, 144, 41));
			piezasD.setActionCommand("Piezas Disponibles");
			piezasD.addActionListener(this);
			b.add(piezasD);
			
			JButton piezasH=new JButton("Historial de piezas (No disponibles)");
			piezasH.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
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
					JOptionPane.showMessageDialog(base, "Se guardó la galeria","Guardar Galeria" , JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception err) {
					JOptionPane.showMessageDialog(base, "No se pudo guardar la galeria","Error" , JOptionPane.ERROR_MESSAGE);
				}
			}else if (comando.equals("Tipo de empleado")) {
					String tipoE = empleado.getRol();
					String mensaje = "Usted tiene el rol de "+ tipoE;
					if (tipoE.equals("None")) {
						mensaje= "Usted no tiene ningun rol extra en la galeria";
					}
					JOptionPane.showMessageDialog(base, mensaje ,"Tipo de empleado" , JOptionPane.INFORMATION_MESSAGE);
			}else if (comando.equals("Piezas Disponibles")) {
				ArrayList<Pieza> piezas = mundo.mostrarPiezasDisponibles();
				VentanaPiezas ventana;
				try {
					ventana = new VentanaPiezas(piezas,"Piezas Propias Disponibles","Elija la pieza de la cual quiera ver información General",mundo);
					ventana.setVisible(true);
				} catch (MensajedeErrorException e1) {
					JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
				}	
			}else if (comando.equals("Piezas Historial")) {
				ArrayList<Pieza> piezas = mundo.mostrarHistorialPiezas();
				VentanaPiezas ventana;
				try {
					ventana = new VentanaPiezas(piezas,"Piezas Propias Disponibles","Elija la pieza de la cual quiera ver información General",mundo);
					ventana.setVisible(true);
				} catch (MensajedeErrorException e1) {
					JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if (comando.equals("Historial Pieza")) {
				ArrayList<Pieza> piezas = mundo.mostrarPiezasDisponibles();
				piezas.addAll(mundo.mostrarHistorialPiezas());
				VentanaPiezas ventana;
				try {
					ventana = new VentanaPiezas(piezas,"Piezas Propias Disponibles","Elija la pieza de la cual quiera ver información General",mundo);
					ventana.setVisible(true);
				} catch (MensajedeErrorException e1) {
					JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
				}
			}else if (comando.equals("Historial Artista")) {
				ArrayList<Autor> autores = mundo.getInventario().getAutores();
				VentanaAutores ventana = new VentanaAutores(autores,mundo);
				ventana.setVisible(true);
			}else if (comando.equals("Historial Cliente")) {
				ArrayList<Comprador> compradores = mundo.getAdmin().getCompradores();
				VentanaClientes ventana = new VentanaClientes(compradores,mundo);
				ventana.setVisible(true);
				
			}
		}
}
