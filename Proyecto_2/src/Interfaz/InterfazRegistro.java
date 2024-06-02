package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;

public class InterfazRegistro extends JPanel {
	
	
	private ButtonGroup tipoRol;
	private JRadioButton opC;
	private JRadioButton opP;
	private JTextField usuario;
	private JTextField telefono;
	private JTextField password;
	private JTextField nombre;
	private JTextField correo;

	public InterfazRegistro() {
		setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(7,1,10,30));
		
		JPanel rol = new JPanel(new GridLayout(1,2));
		rol.setPreferredSize(new Dimension(100, 5));
		tipoRol = new ButtonGroup();
		
		opC= new JRadioButton("Comprador",true);
		opC.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
		opC.setHorizontalAlignment(SwingConstants.CENTER);
		opC.setForeground(Color.WHITE);
		opC.setBackground(new Color(0, 144, 41) );
		tipoRol.add(opC);
		rol.add(opC);
		

		opP= new JRadioButton("Propietario");
		opP.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
		opP.setForeground(Color.WHITE);
		opP.setHorizontalAlignment(SwingConstants.CENTER);
		opP.setBackground(new Color(0, 144, 41));
		tipoRol.add(opP);
		rol.add(opP);
		
		p.add(rol);
		
		JLabel relleno= new JLabel("Ingrese sus datos: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,30));
		relleno.setForeground(new Color(0, 144, 41 ));
		p.add(relleno);
		
		JPanel info = new JPanel(new GridLayout(1,2));
		JLabel m1 = new JLabel("Usuario:",JLabel.CENTER);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m1.setForeground(new Color(0, 144, 41));
		info.add(m1);
		
		usuario = new JTextField("");
		usuario.setSize(100, 20);
		usuario.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		info.add(usuario);
		p.add(info);
		JPanel info1 = new JPanel(new GridLayout(1,2));
		JLabel m2 = new JLabel("Contraseña:",JLabel.CENTER);
		m2.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m2.setForeground(new Color(0, 144, 41));
		info1.add(m2);
		
		password = new JTextField("");
		password.setSize(100, 20);
		password.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		info1.add(password);
		
		p.add(info1);
		JPanel info2 = new JPanel(new GridLayout(1,2));
		
		JLabel m3 = new JLabel("Correo:",JLabel.CENTER);
		m3.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m3.setForeground(new Color(0, 144, 41));
		info2.add(m3);
		
		correo = new JTextField("");
		correo.setSize(100, 20);
		correo.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		info2.add(correo);
		
		p.add(info2);
		JPanel info3 = new JPanel(new GridLayout(1,2));
		
		JLabel m4 = new JLabel("Telefono:",JLabel.CENTER);
		m4.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m4.setForeground(new Color(0, 144, 41));
		info3.add(m4);
		
		telefono = new JTextField("");
		telefono.setSize(100, 20);
		telefono.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		info3.add(telefono);
		p.add(info3);
		JPanel info4 = new JPanel(new GridLayout(1,2));
		
		JLabel m5 = new JLabel("Nombre:",JLabel.CENTER);
		m5.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m5.setForeground(new Color(0, 144, 41));
		info4.add(m5);
		
		nombre = new JTextField("");
		nombre.setSize(100, 20);
		nombre.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		info4.add(nombre);

		p.add(info4);
		add(p, BorderLayout.CENTER);
		
	}

	
	public ArrayList<String> recogerRegistro() throws MensajedeErrorException{
		if (usuario.getText().equals("")){
			throw new MensajedeErrorException("Ingrese un usuario");
		}else if (password.getText().equals("")) {
			throw new MensajedeErrorException("Ingrese una contraseña");
		}
		else if (correo.getText().equals("")){
			throw new MensajedeErrorException("Ingrese un correo");
		}else if (nombre.getText().equals("")) {
			throw new MensajedeErrorException("Ingrese su nombre");
		}else if (telefono.getText().equals("")) {
			throw new MensajedeErrorException("Ingrese un telefono");
		}
		ArrayList<String> resp =new ArrayList<>();
		resp.add(password.getText());
		resp.add(telefono.getText());
		resp.add(nombre.getText());
		resp.add(correo.getText());
		resp.add(usuario.getText());
		
		return resp;
	}
	
	
	public String rolSeleccionado() {
		JRadioButton s = seleccionado(tipoRol);
		String respuesta="";
		String dif =  s.getText();
		if (dif.equals("Comprador")) {
			respuesta="Comprador";
		}else if (dif.equals("Propietario")) {
			respuesta="Propietario";
		}
		return respuesta;
	}
	
	public JRadioButton seleccionado(ButtonGroup group){
        for (Enumeration e=group.getElements(); e.hasMoreElements(); )
        {
            JRadioButton b = (JRadioButton)e.nextElement();
            if (b.getModel() == group.getSelection())
            {
                return b;
            }
        }
        return null;
}
	
}
