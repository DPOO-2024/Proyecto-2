package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modelo.Empleado;
import Modelo.Galeria;
import Usuarios.Comprador;
import Usuarios.Propietario;

public class interfazBase extends JFrame{
	private static final long serialVersionUID = 1L;
	private Galeria mundo;
	private JPanel info;
	
	public interfazBase(Galeria g) {
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
		interfazInicioSesion i = new interfazInicioSesion(this,mundo);
		info.removeAll();
		info.revalidate();
		info.add(i);
		info.repaint();
		repaint();
	}
	
	public void interfazAdmin() {
		interfazAdministrador i = new interfazAdministrador(this,mundo);
		info.removeAll();
		info.revalidate();
		info.add(i);
		info.repaint();
		repaint();
	}
	
	
	public static void main(String[] args) {
		Galeria galeriaNueva = new Galeria();
		galeriaNueva.cargarGaleria(galeriaNueva);
		interfazBase base = new interfazBase(galeriaNueva);
	}

	public void interfazEmpleado(Empleado resp) {
		// TODO Auto-generated method stub
		
	}

	public void interfazComprador(Comprador resp) {
		// TODO Auto-generated method stub
		
	}

	public void interfazPropietario(Propietario resp) {
		// TODO Auto-generated method stub
		
	}
}
