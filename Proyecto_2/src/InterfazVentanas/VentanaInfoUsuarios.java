package InterfazVentanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Interfaz.InterfazAdministrador;

public class VentanaInfoUsuarios extends JDialog implements ActionListener{
	private JPanel ventana;
	private String tipoPanel;
	private InterfazAdministrador interfaz;
	private JTextField login;
	private JTextField password;
	private ButtonGroup tipoOperador;
	private JRadioButton opY;
	private JRadioButton opN;
	private String[] datos;
	

	
	public VentanaInfoUsuarios(String titulo,String t,String boton,InterfazAdministrador i) {
		tipoPanel=t;
		interfaz=i;
		
		setTitle(titulo);
		setSize(500, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		if (tipoPanel.equals("Agregar Empleado")) {
			ventana = agregarEmpleado();
		}
		
		add(ventana,BorderLayout.CENTER);
		
		JButton continuar = new JButton(boton);
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("Continuar");
		continuar.addActionListener(this);
		
		add(continuar,BorderLayout.SOUTH);
		setVisible(true);
	}

	
	public JPanel agregarEmpleado() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,1));
		
		JLabel relleno= new JLabel("Agregar un Usuario: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,30));
		relleno.setForeground(new Color(0, 144, 41 ));
		p.add(relleno);
		
		JPanel mensaje = new JPanel(new GridLayout(3,2,10,20));
		
		JTextArea m1 = new JTextArea("Login del empleado:");
		m1.setLineWrap(true);
		m1.setEditable(false);
		m1.setWrapStyleWord(true);
		m1.setOpaque(false);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,18));
		m1.setForeground(new Color(0, 144, 41));
		mensaje.add(m1);
		
		login = new JTextField("");
		login.setSize(100, 20);
		login.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		mensaje.add(login);
		
		JTextArea m2 = new JTextArea("Contraseña del empleado:");
		m2.setLineWrap(true);
		m2.setEditable(false);
		m2.setWrapStyleWord(true);
		m2.setOpaque(false);
		m2.setFont(new Font("Nirmala UI",Font.BOLD,18));
		m2.setForeground(new Color(0, 144, 41));
		mensaje.add(m2);
		
		password = new JTextField("");
		password.setSize(100, 20);
		password.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		mensaje.add(password);
		
		tipoOperador = new ButtonGroup();
		
		//Boton Operador ya registrado
		opY= new JRadioButton("Operador");
		opY.setFont(new Font ("Nirmala UI", Font.PLAIN, 20));
		opY.setHorizontalAlignment(SwingConstants.CENTER);
		opY.setForeground(Color.WHITE);
		opY.setBackground(new Color(0, 144, 41) );
		tipoOperador.add(opY);
		mensaje.add(opY);
		
		//Boton nuevo operador
		opN= new JRadioButton("Empleado",true);
		opN.setFont(new Font ("Nirmala UI", Font.PLAIN, 20));
		opN.setForeground(Color.WHITE);
		opN.setHorizontalAlignment(SwingConstants.CENTER);
		opN.setBackground(new Color(0, 144, 41));
		tipoOperador.add(opN);
		mensaje.add(opN);

		p.add(mensaje);
		
		JLabel relleno2= new JLabel(" ");
		p.add(relleno2);
		
		return p;
	}
	
	public String operadorSeleccionado() {
		JRadioButton s = VentanasInfoSubasta.seleccionado(tipoOperador);
		String respuesta="";
		String dif =  s.getText();
		if (dif.equals("Operador")) {
			respuesta="Operador";
		}else if (dif.equals("Empleado")) {
			respuesta="Empleado";
		}
		return respuesta;
	}
	
	public String[] recogerInfo() throws Exception {
		if (tipoPanel.equals("Agregar Empleado")) {
			if (login.getText().equals("") || password.getText().equals("")){
				throw new Exception();
			}
			String[] resp = {login.getText(),password.getText(),operadorSeleccionado()};
			return resp;
		}
		return null;
	}
	
	public String[] recogerDatos() {
		return this.datos;
	}
	
	public void llamarFuncion() {
		if (tipoPanel.equals("Agregar Empleado")) {
			interfaz.agregarEmpleado(this);
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		 if (comando.equals("Continuar")) {
				try {
					datos = recogerInfo();
					this.dispose();	
					llamarFuncion();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Complete los espacios en blanco","Error",JOptionPane.ERROR_MESSAGE);
				}		
			}
	}



	
}
