package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modelo.Empleado;
import Modelo.Galeria;
import Usuarios.Comprador;
import Usuarios.Propietario;

public class InterfazBase extends JFrame{
	private static final long serialVersionUID = 1L;
	private Galeria mundo;
	private JPanel info;
	
	public InterfazBase(Galeria g) {
		mundo=g;
		setTitle(g.getNombre());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(1000,800);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		Logo l = new Logo(g.getNombre());
		
		add(l,BorderLayout.NORTH);

		
		info=new JPanel();
		add(info,BorderLayout.CENTER);	
		interfazIS();
		
		
		setResizable(false);
		setVisible(true);
	}
	
	public void interfazIS() {
		InterfazInicioSesion i = new InterfazInicioSesion(this,mundo);
		info.removeAll();
		info.revalidate();
		info.add(i);
		info.repaint();
		repaint();
	}
	
	public void interfazAdmin() {
		InterfazAdministrador i = new InterfazAdministrador(this,mundo);
		info.removeAll();
		info.revalidate();
		info.add(i);
		info.repaint();
		repaint();
	}
	
	public void interfazEmpleado(Empleado resp) {
		InterfazEmpleado i = new InterfazEmpleado(this,mundo,resp);
		info.removeAll();
		info.revalidate();
		info.add(i);
		info.repaint();
		repaint();
		
	}

	public void interfazComprador(Comprador resp) {
		InterfazComprador i = new InterfazComprador(this,mundo,resp);
		info.removeAll();
		info.revalidate();
		info.add(i);
		info.repaint();
		repaint();
		
	}

	public void interfazPropietario(Propietario resp) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		Galeria galeriaNueva = new Galeria();
		galeriaNueva.cargarGaleria(galeriaNueva);
		new InterfazBase(galeriaNueva);
	}



}
