package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Piezas.Autor;
import Piezas.Pieza;
import Usuarios.Comprador;

public class InterfazAdministrador extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Galeria mundo;
	private InterfazBase base;
	private JDialog ventanaTS;
	private JDialog ventanaA;
	private JTextField fecha;
	private JTextField login;
	private JTextField password;
	
	private static final String CERRAR="Cerrar Sesion";
	
	
	public InterfazAdministrador(InterfazBase b,Galeria m) {
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
		b.setLayout(new GridLayout(8, 2, 20, 20));
		
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
		
		JButton asignarA=new JButton("Asignar nuevo Administrador");
		asignarA.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		asignarA.setForeground(Color.WHITE);
		asignarA.setPreferredSize(new Dimension(250,50));
		asignarA.setBackground(new Color(0, 144, 41));
		asignarA.setActionCommand("Admin");
		asignarA.addActionListener(this);
		b.add(asignarA);
		
		JButton mostrarV=new JButton("Mostrar Mes con Mayor Ventas");
		mostrarV.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		mostrarV.setForeground(Color.WHITE);
		mostrarV.setPreferredSize(new Dimension(250,50));
		mostrarV.setBackground(new Color(0, 144, 41));
		mostrarV.setActionCommand("ventas");
		mostrarV.addActionListener(this);
		b.add(mostrarV);
		
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
			JOptionPane.showMessageDialog(null, "El empleado se agrego correctamente","Subasta",JOptionPane.INFORMATION_MESSAGE);
		}catch (MensajedeErrorException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void terminarsubasta() {
		
		ventanaTS = new JDialog(); 
		ventanaTS.setTitle("Terminar Subasta");
		ventanaTS.setSize(500, 550);
		ventanaTS.setLocationRelativeTo(null);
		ventanaTS.setLayout(new BorderLayout());
		JLabel relleno= new JLabel("Finalizar una Subasta: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,30));
		relleno.setForeground(new Color(0, 144, 41 ));
		ventanaTS.add(relleno,BorderLayout.NORTH);
		
		JPanel fechaPanel = new JPanel(new GridLayout(3,1,20,25));
		JLabel label= new JLabel("Ingrese la fecha (AAMMDD) de la subasta que desea finalizar :",JLabel.CENTER);
		label.setFont(new Font("Nirmala UI",Font.BOLD,16));
		label.setForeground(new Color(0, 144, 41 ));
		fechaPanel.add(label);
		
		fecha = new JTextField("");
		fecha.setSize(100, 20);
		fecha.setFont(new Font("Nirmala UI",Font.PLAIN,30));
		fechaPanel.add(fecha);
		ventanaTS.add(fechaPanel, BorderLayout.CENTER);
		
		JButton continuar = new JButton("finalizar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("finalizar");
		continuar.addActionListener(this);
		
		ventanaTS.add(continuar,BorderLayout.SOUTH);
		
		ventanaTS.setVisible(true);
	}
	


	public void asignarNuevo(String titulo, String label,String comando) {
		
		ventanaA = new JDialog(); 
		ventanaA.setTitle(titulo);
		ventanaA.setSize(600, 550);
		ventanaA.setLocationRelativeTo(null);
		ventanaA.setLayout(new BorderLayout());
		
		JLabel relleno= new JLabel(label,JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,30));
		relleno.setForeground(new Color(0, 144, 41 ));
		ventanaA.add(relleno,BorderLayout.NORTH);
		
		JPanel mensaje = new JPanel(new GridLayout(4,2,40,40));
		
		JLabel rell1 =new JLabel("");
		mensaje.add(rell1);
		
		JLabel rell2 =new JLabel("");
		mensaje.add(rell2);
		
		JLabel m1 = new JLabel("Ingrese el Usuario:",JLabel.CENTER);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m1.setForeground(new Color(0, 144, 41));
		mensaje.add(m1);
		
		login = new JTextField("");
		login.setSize(100, 20);
		login.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		mensaje.add(login);
		
		JLabel m2 = new JLabel("Ingrese la Contraseña:",JLabel.CENTER);
		m2.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m2.setForeground(new Color(0, 144, 41));
		mensaje.add(m2);
		
		password = new JTextField("");
		password.setSize(100, 20);
		password.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		mensaje.add(password);
		
		JLabel rell3 =new JLabel("");
		mensaje.add(rell3);
		
		JLabel rell4 =new JLabel("");
		mensaje.add(rell4);
		
		ventanaA.add(mensaje,BorderLayout.CENTER);
		
		JButton continuar = new JButton("Cambiar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand(comando);
		continuar.addActionListener(this);
		
		ventanaA.add(continuar,BorderLayout.SOUTH);
		
		ventanaA.setVisible(true);	
	}
	


	public void graficoVentas() {
		TreeMap<String,Integer> pagos = mundo.getCajero().generarReportePagos();
		new Grafico(pagos);	
		
		
		JDialog graf = new JDialog();
		graf.setTitle("Grafica Ventas");
		graf.setSize(800, 500);
		graf.setResizable(false);
		graf.setLocationRelativeTo(null);
		graf.setLayout(new BorderLayout());
		
		JPanel superior = new JPanel(new BorderLayout());
		JLabel t= new JLabel("Historial de Ventas",JLabel.CENTER);
		t.setFont(new Font("Nirmala UI",Font.BOLD,30));
		t.setForeground(new Color(0, 144, 41 ));
		superior.add(t,BorderLayout.CENTER);
		
		JLabel extra= new JLabel("En el grafico se observan los mejores meses de ventas para la galeria.",JLabel.CENTER);
		extra.setFont(new Font("Nirmala UI",Font.PLAIN,10));
		extra.setForeground(new Color(0, 144, 41 ));
		superior.add(extra,BorderLayout.SOUTH);
		graf.add(superior,BorderLayout.NORTH);
		
		JPanel dibujo = new Grafico(pagos);
		graf.add(dibujo);
		
		graf.setVisible(true);
		
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
			
		}else if (comando.equals("Terminar Subasta")) {
			terminarsubasta();
			
		}else if (comando.equals("finalizar")) {
			try {
				if (fecha.getText().equals("")){
					throw new MensajedeErrorException("Ingrese una fecha");
				}
				String fechat = fecha.getText();
				int fechai=Integer.parseInt(fechat);
				mundo.terminarSubasta(fechai);
				JOptionPane.showMessageDialog(null, "Se termino la subasta correctamente","Terminar subasta" , JOptionPane.INFORMATION_MESSAGE);
				ventanaTS.dispose();
			} catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se pudo crear la Subasta","Error",JOptionPane.ERROR_MESSAGE);
				ventanaTS.dispose();
			}
		}else if (comando.equals("Cajero")) {
			asignarNuevo("Asignar Cajero","Asignar nuevo cajero: ","cambiar");
		}else if (comando.equals("cambiar")) {
			try {
				if (login.getText().equals("")|| password.getText().equals("")){
					throw new MensajedeErrorException("No deje espacios en blanco");
				}
				mundo.asignarCajero(login.getText(), password.getText());;
				JOptionPane.showMessageDialog(null, "Se cambio el cajero correctamente","Cambiar cajero" , JOptionPane.INFORMATION_MESSAGE);
				ventanaA.dispose();	
			} catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se pudo cambiar el cajero","Error",JOptionPane.ERROR_MESSAGE);
				ventanaA.dispose();
			}	
		}else if (comando.equals("Admin")) {
			asignarNuevo("Asignar Administrador","Asignar nuevo Administrador: ","cambiarA");

		}else if (comando.equals("cambiarA")) {
			try {
				if (login.getText().equals("")|| password.getText().equals("")){
					throw new MensajedeErrorException("No deje espacios en blanco");
				}
				mundo.asignarAdministrador(login.getText(), password.getText());;
				JOptionPane.showMessageDialog(null, "Se cambio el administrador correctamente","Cambiar administrador" , JOptionPane.INFORMATION_MESSAGE);
				ventanaA.dispose();	
				base.interfazIS();
			} catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se pudo cambiar el administrador","Error",JOptionPane.ERROR_MESSAGE);
				ventanaA.dispose();
			}
		}else if (comando.equals("ventas")) {
			graficoVentas();

		}
	}





	
}
