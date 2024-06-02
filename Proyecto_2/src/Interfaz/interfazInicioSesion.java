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
import Modelo.Empleado;
import Modelo.Galeria;
import Usuarios.Comprador;
import Usuarios.Propietario;


public class interfazInicioSesion extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private int intentos;
	private Galeria mundo;
	private interfazBase base;
	private JDialog ventanaI;
	private String[] datos;
	private ventanaInicioSesion infoVentana;
	private InterfazRegistro infoextra;
	private String interfaz;
	
	private static final String A="admin";
	private static final String E="empleado";
	private static final String C="comprador";
	private static final String P="propietario";
	private static final String R="registrar";
	
	public interfazInicioSesion(interfazBase b,Galeria m) {
		mundo=m;
		base=b;
		
		setLayout(new GridLayout(3,1));
		
		JLabel relleno= new JLabel(" Inicio de sesion ",JLabel.CENTER);
		relleno.setFont(new Font ("Nirmala UI", Font.BOLD, 30));
		relleno.setForeground(new Color(0, 90, 26));
		add(relleno);
		
		JPanel botones = panelBoton();
		add(botones);
		
		JLabel relleno2= new JLabel(" ");
		add(relleno2);
		
		
		setVisible(true);
	}
	
	//Genera los botones de la interfaz
	public JPanel panelBoton() {
		JPanel b= new JPanel();
		b.setLayout(new GridLayout(5, 1, 20, 20));
		
		JButton admin=new JButton("Administrador");
		
		admin.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		admin.setForeground(Color.WHITE);
		admin.setPreferredSize(new Dimension(200,30));
		admin.setBackground(new Color(0, 144, 41));
		admin.setActionCommand(A);
		admin.addActionListener(this);
		b.add(admin);
		
		JButton empleado=new JButton("Empleado");
		empleado.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		empleado.setForeground(Color.WHITE);
		empleado.setPreferredSize(new Dimension(200,30));
		empleado.setBackground(new Color(0, 144, 41));
		empleado.setActionCommand(E);
		empleado.addActionListener(this);
		b.add(empleado);
		
		JButton comprador=new JButton("Comprador");
		comprador.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		comprador.setForeground(Color.WHITE);
		comprador.setPreferredSize(new Dimension(200,30));
		comprador.setBackground(new Color(0, 144, 41));
		comprador.setActionCommand(C);
		comprador.addActionListener(this);
		b.add(comprador);
		
		JButton propietario=new JButton("Propietario");
		propietario.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		propietario.setForeground(Color.WHITE);
		propietario.setPreferredSize(new Dimension(200,30));
		propietario.setBackground(new Color(0, 144, 41));
		propietario.setActionCommand(P);
		propietario.addActionListener(this);
		b.add(propietario);
		
		JButton registrar=new JButton("Registrar");
		registrar.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		registrar.setForeground(Color.WHITE);
		registrar.setPreferredSize(new Dimension(200,30));
		registrar.setBackground(new Color(0, 90, 26));
		registrar.setActionCommand(R);
		registrar.addActionListener(this);
		b.add(registrar);
		
		return b;
	}
	
	//Genera la ventana para el inicio de sesion
	public void ventanaInicio() {
		intentos=0;
		infoVentana = new ventanaInicioSesion();
		
		ventanaI = new JDialog(); 
		ventanaI.setTitle("Ingresar Datos");
		ventanaI.setSize(500, 550);
		ventanaI.setLocationRelativeTo(null);
		ventanaI.setLayout(new BorderLayout());
		
		ventanaI.add(infoVentana,BorderLayout.CENTER);
		
		JButton continuar = new JButton("Ingresar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		continuar.setActionCommand("Continuar");
		continuar.addActionListener(this);
		
		ventanaI.add(continuar,BorderLayout.SOUTH);

		
		ventanaI.setVisible(true);
	}
	
	public void errorIniciosesion() {
		 JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos","Error" , JOptionPane.ERROR_MESSAGE);
	}
	
	//Verifica y genera la interfaz de acuerdo a como se inicio sesion
	public void redireccionInterfaz() {
		try {
			if (interfaz.equals(A)) {
				boolean resp = mundo.inicioSesionAdmin(datos[0], datos[1]);
	    		if (resp) {
	    			base.interfazAdmin();
	    		}
			}else if (interfaz.equals(E)) {
				Empleado resp = mundo.verificarEmpleado(datos[0], datos[1]);
	    		base.interfazEmpleado(resp);
			}else if (interfaz.equals(C)) {
				Comprador resp = mundo.getAdmin().verificarComprador(datos[0], datos[1]);
	    		base.interfazComprador(resp);
			}else if (interfaz.equals(R)) {
				Propietario resp = mundo.getAdmin().verificarPropietario(datos[0], datos[1]);
	    		base.interfazPropietario(resp);
			}			
		}catch( MensajedeErrorException error){
    		errorIniciosesion();
    	}
	}
	
	public void registrarUsuario() {
	
		infoextra = new InterfazRegistro();
		ventanaI = new JDialog(); 
		ventanaI.setTitle("Ingresar Datos");
		ventanaI.setSize(500, 550);
		ventanaI.setLocationRelativeTo(null);
		ventanaI.setLayout(new BorderLayout());
		
		ventanaI.add(infoextra,BorderLayout.CENTER);
		
		JButton continuar = new JButton("Ingresar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("aprovado");
		continuar.addActionListener(this);
		
		ventanaI.add(continuar,BorderLayout.SOUTH);
		
		
		
		ventanaI.setVisible(true);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(A)) {
			ventanaInicio();
			interfaz=A;			
		}else if (comando.equals(E)) {
			ventanaInicio();
			interfaz=E; 	
		}else if (comando.equals(C)) {
			ventanaInicio();
			interfaz=C;
		}else if (comando.equals(P)) {
			ventanaInicio();
			interfaz=P;
		}else if (comando.equals(R)) {
			registrarUsuario();
		}else if (comando.equals("Continuar")) {
			try {
				datos = infoVentana.recogerInfo();
				ventanaI.dispose();
				redireccionInterfaz();	
			}catch(Exception err) {
				intentos+=1;
				if (intentos>3) {
					JOptionPane.showMessageDialog(null, "Ha realizado muchos intentos","Error",JOptionPane.ERROR_MESSAGE);
					ventanaI.dispose();
				}
			}			
		}
		else if (comando.equals("aprovado")) {
			
				try {
					ArrayList<String> datos2 = infoextra.recogerRegistro();
					String rol = infoextra.rolSeleccionado();
					mundo.getAdmin().verificarLogin(datos2.get(4), rol);
					mundo.getAdmin().agregarUsuario(datos2, rol);
					ventanaI.dispose();
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "El usuario que ingreso ya se encuentra registrado","Error",JOptionPane.ERROR_MESSAGE);
					ventanaI.dispose();
				}
					
			
			
		}
	}
	

}


