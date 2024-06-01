package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ventanaInicioSesion extends JPanel{
	private JTextField usuario;
	private JTextField password;
	
	public ventanaInicioSesion() {
		
		setLayout(new GridLayout(3,1));
		
		JLabel relleno= new JLabel("Ingrese sus datos: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,30));
		relleno.setForeground(new Color(0, 144, 41 ));
		add(relleno);
		
		JPanel mensaje = new JPanel(new GridLayout(2,2,10,50));
		
		JLabel m1 = new JLabel("Usuario:",JLabel.CENTER);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m1.setForeground(new Color(0, 144, 41));
		mensaje.add(m1);
		
		usuario = new JTextField("");
		usuario.setSize(100, 20);
		usuario.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		mensaje.add(usuario);
		
		JLabel m2 = new JLabel("Contraseña:",JLabel.CENTER);
		m2.setFont(new Font("Nirmala UI",Font.BOLD,25));
		m2.setForeground(new Color(0, 144, 41));
		mensaje.add(m2);
		
		password = new JTextField("");
		password.setSize(100, 20);
		password.setFont(new Font("Nirmala UI",Font.PLAIN,25));
		mensaje.add(password);
		add(mensaje);
		
		JLabel relleno2= new JLabel(" ");
		add(relleno2);
			
	}

	public String[] recogerInfo() throws Exception{
		if (usuario.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Ingrese un usuario","Error",JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}else if (password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese una contraseña","Error",JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}
		String[] resp = {usuario.getText(),password.getText()};
		return resp;
	}
	
}
