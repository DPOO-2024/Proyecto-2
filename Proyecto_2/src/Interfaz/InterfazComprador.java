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

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Exceptions.MensajedeErrorException;
import InterfazVentanas.VentanaAutores;
import InterfazVentanas.VentanaComprar;
import InterfazVentanas.VentanaFinalSubasta;
import InterfazVentanas.VentanaParticiparSubasta;
import InterfazVentanas.VentanaPiezas;
import Modelo.Administrador;
import Modelo.Galeria;
import Modelo.Subasta;
import Piezas.Autor;
import Piezas.Pieza;
import Usuarios.Comprador;
import Usuarios.Operador;

public class InterfazComprador extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Comprador comprador;
	private Galeria mundo;
	private InterfazBase base;
	private VentanaComprar ventanaC;
	private JDialog ventanaExtra;
	private JDialog ventanaAumento;
	private JDialog ventanaMostrar;
	private JTextField valor;
	private JDialog ventanaRegistro;
	private JTextField fechaR;
	
	private static final String CERRAR="Cerrar Sesion";
	
	
	public InterfazComprador(InterfazBase interfazBase, Galeria mundo, Comprador comprador) {
		this.mundo=mundo;
		this.base=interfazBase;
		this.comprador=comprador;
		
		setLayout(new BorderLayout());
		
		JLabel relleno= new JLabel(" Menú Comprador ",JLabel.CENTER);
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
		
		JButton guardarG=new JButton("Comprar Pieza");
		guardarG.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		guardarG.setForeground(Color.WHITE);
		guardarG.setPreferredSize(new Dimension(250,50));
		guardarG.setBackground(new Color(0, 144, 41));
		guardarG.setActionCommand("comprar");
		guardarG.addActionListener(this);
		b.add(guardarG);
		
		JButton crearS=new JButton("Aumentar Cupo Compras");
		crearS.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		crearS.setForeground(Color.WHITE);
		crearS.setPreferredSize(new Dimension(250,50));
		crearS.setBackground(new Color(0, 144, 41));
		crearS.setActionCommand("aumentar");
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
		
		JButton historialC=new JButton("Ver Subastas Activas");
		historialC.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		historialC.setForeground(Color.WHITE);
		historialC.setPreferredSize(new Dimension(250,50));
		historialC.setBackground(new Color(0, 144, 41));
		historialC.setActionCommand("subastas activas");
		historialC.addActionListener(this);
		b.add(historialC);
		
		JButton terminarS=new JButton("Registrarse en una subasta");
		terminarS.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		terminarS.setForeground(Color.WHITE);
		terminarS.setPreferredSize(new Dimension(250,50));
		terminarS.setBackground(new Color(0, 144, 41));
		terminarS.setActionCommand("registrarse Subasta");
		terminarS.addActionListener(this);
		b.add(terminarS);
		
		JButton asignarC=new JButton("Participar en subasta");
		asignarC.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		asignarC.setForeground(Color.WHITE);
		asignarC.setPreferredSize(new Dimension(250,50));
		asignarC.setBackground(new Color(0, 144, 41));
		asignarC.setActionCommand("Participar subasta");
		asignarC.addActionListener(this);
		b.add(asignarC);
		
		JButton asignarA=new JButton("Revisar estado/\nresultados subastas");
		asignarA.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		asignarA.setForeground(Color.WHITE);
		asignarA.setPreferredSize(new Dimension(300,50));
		asignarA.setBackground(new Color(0, 144, 41));
		asignarA.setActionCommand("resultados");
		asignarA.addActionListener(this);
		b.add(asignarA);
		
		
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
	
	public void ventanaComprar() {
		 ventanaC = new VentanaComprar(this);
		ventanaExtra = new JDialog();
		ventanaExtra.setTitle("Realizar compra");
		ventanaExtra.setSize(500, 550);
		ventanaExtra.setLocationRelativeTo(null);
		ventanaExtra.setLayout(new BorderLayout());
		
		JButton piezas = new JButton(" Ver Piezas Disponibles");
		piezas.setForeground(Color.WHITE);
		piezas.setPreferredSize(new Dimension(100,30));
		piezas.setBackground(new Color(0, 90, 26));
		piezas.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		piezas.setActionCommand("Piezas Disponibles");
		piezas.addActionListener(this);
		ventanaExtra.add(piezas,BorderLayout.NORTH);
		
		
		ventanaExtra.add(ventanaC);
		
		ventanaExtra.setVisible(true);
		
		
	}
	
	
	public int cantidadPiezasDisponibles() {
		ArrayList<Pieza> piezas = mundo.mostrarPiezasDisponibles();
		int cantidad = piezas.size();
		return cantidad;
	}
	
	public void aumentarCupoComprador() {
		ventanaAumento = new JDialog();
		ventanaAumento.setTitle("Aumentar Cupo de compras");
		ventanaAumento.setSize(600, 250);
		ventanaAumento.setLocationRelativeTo(null);
		ventanaAumento.setLayout(new GridLayout(2, 1, 20, 20));
		JPanel info = new JPanel(new GridLayout(1,2));
		JTextArea relleno = new JTextArea("Ingrese el valor con el cual desea \n aumentar el cupo de sus compras: ");
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,15));
		relleno.setForeground(new Color(0, 144, 41 ));
		info.add(relleno);
		valor = new JTextField("");
		valor.setSize(100, 20);
		valor.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		info.add(valor);
		ventanaAumento.add(info);
		JButton aceptar = new JButton("Aumentar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setPreferredSize(new Dimension(100,15));
		aceptar.setBackground(new Color(0, 90, 26));
		aceptar.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		aceptar.setActionCommand("aceptar aumento");
		aceptar.addActionListener(this);
		ventanaAumento.add(aceptar);
		ventanaAumento.setVisible(true);
	}
	
	
	
	public void registrarseSubasta() {
		ventanaRegistro = new JDialog();
		ventanaRegistro.setTitle("Registrarse en una subasta");
		ventanaRegistro.setSize(600, 250);
		ventanaRegistro.setLocationRelativeTo(null);
		ventanaRegistro.setLayout(new GridLayout(2, 1, 20, 20));
		JPanel info = new JPanel(new GridLayout(1,2));
		JTextArea relleno = new JTextArea("Ingrese la fecha (AAMMDD) de la subasta\n en la que quiere inscribirse : ");
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,15));
		relleno.setForeground(new Color(0, 144, 41 ));
		info.add(relleno);
		fechaR = new JTextField("");
		fechaR.setSize(100, 20);
		fechaR.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		info.add(fechaR);
		ventanaRegistro.add(info);
		JButton aceptar = new JButton("Registrarse");
		aceptar.setForeground(Color.WHITE);
		aceptar.setPreferredSize(new Dimension(100,15));
		aceptar.setBackground(new Color(0, 90, 26));
		aceptar.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		aceptar.setActionCommand("registrarse");
		aceptar.addActionListener(this);
		ventanaRegistro.add(aceptar);
		ventanaRegistro.setVisible(true);
		
		
	}
	
	public void mostrarSubastas() throws MensajedeErrorException {
		ventanaMostrar = new JDialog();
		ventanaMostrar.setTitle("Subastas Activas");
		ventanaMostrar.setSize(800, 600);
		ventanaMostrar.setResizable(false);
		ventanaMostrar.setLocationRelativeTo(null);
		ventanaMostrar.setLayout(new BorderLayout());
		
		JPanel superior = new JPanel(new BorderLayout());
		JLabel t= new JLabel("Subasta Activas",JLabel.CENTER);
		t.setFont(new Font("Nirmala UI",Font.BOLD,30));
		t.setForeground(new Color(0, 144, 41 ));
		superior.add(t,BorderLayout.CENTER);
		
		ventanaMostrar.add(superior,BorderLayout.NORTH);
		
		JPanel ordenar= new JPanel(new GridLayout(3,1,0,10));
		JLabel relleno1= new JLabel(" ");
		ordenar.add(relleno1);
		JPanel panelP = panelLista();
		ordenar.add(panelP);
		JLabel relleno2= new JLabel(" ");
		ordenar.add(relleno2);
		ventanaMostrar.add(ordenar, BorderLayout.CENTER);
		ventanaMostrar.setVisible(true);
	}
	
	
	public JPanel panelLista() throws MensajedeErrorException {
		
		JPanel panel = new JPanel(new BorderLayout());

		DefaultListModel<String> titulos = new DefaultListModel<String>();
		int i=1;
		if (mundo.getSubastasActivas().isEmpty()) {
			throw new MensajedeErrorException("No hay subastas disponibles");

		}
		else {
			for (Subasta s:mundo.getSubastasActivas()) {
				String fila = " Subasta N."+Integer.toString(i)+ " -> "+s.getFechaSubasta();
				titulos.addElement(fila);
				i++;
			}
		}
		
		JList<String> lista = new JList<String>(titulos);
		lista.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lista.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		JScrollPane scroll = new JScrollPane(lista);
		panel.add(scroll,BorderLayout.CENTER);
		
		return panel;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(CERRAR)) {
			int rta = JOptionPane.showConfirmDialog(null, "De verdad quiere cerrar sesion?",
					"Cerrar sesión", JOptionPane.YES_NO_OPTION);
			if (rta == JOptionPane.OK_OPTION) {
				base.interfazIS();}
		}
		else if (comando.equals("comprar")) {
			ventanaComprar();			
		}else if (comando.equals("Piezas Disponibles")) {
			ArrayList<Pieza> piezas = mundo.mostrarPiezasDisponibles();
			VentanaPiezas ventana;
			try {
				ventana = new VentanaPiezas(piezas,"Piezas Propias Disponibles","Elija la pieza de la cual quiera ver información General",mundo);
				ventana.setVisible(true);
			} catch (MensajedeErrorException e1) {
				JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else if (comando.equals("aumentar")) {
			aumentarCupoComprador();
		}else if (comando.equals("aceptar aumento")) {
			mundo.aumentarCupo(comprador.getLogin(), Integer.parseInt(valor.getText()));
			JOptionPane.showMessageDialog(null, "Se realizo el aumento correctamente","aumento de cupo" , JOptionPane.INFORMATION_MESSAGE);
			ventanaAumento.dispose();
			
		}else if (comando.equals("Piezas Disponibles")) {
			ArrayList<Pieza> piezas = mundo.mostrarPiezasDisponibles();
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
			
		}else if (comando.equals("registrarse")) {
			try {
				mundo.participarSubasta(Integer.parseInt(fechaR.getText()),comprador,1);
				JOptionPane.showMessageDialog(null, "Fue registrado exitosamente","Registro subasta" , JOptionPane.INFORMATION_MESSAGE);
				ventanaRegistro.dispose();
			}catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				ventanaRegistro.dispose();
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se pudo resitrar en esa subasta","Error",JOptionPane.ERROR_MESSAGE);
				ventanaRegistro.dispose();
			}	
			
		}else if (comando.equals("registrarse Subasta")) {
			registrarseSubasta();
		}else if (comando.equals("Participar subasta")) {
			JOptionPane.showMessageDialog(null, "Recuerda que para poder participar en una subasta debes estar registrado en ella","Participar subasta" , JOptionPane.INFORMATION_MESSAGE);
			VentanaParticiparSubasta ventana = new VentanaParticiparSubasta(this,mundo);
			ventana.setVisible(true);
		}else if (comando.equals("subastas activas")) {
			try {
				mostrarSubastas();
			} catch (MensajedeErrorException e1) {
				JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
				ventanaMostrar.dispose();
			}}else if (comando.equals("compras realizadas")) {
				
			}else if (comando.equals("resultados")) {
				VentanaFinalSubasta ventana = new VentanaFinalSubasta(this,mundo);
				ventana.setVisible(true);
			}

	}




	public Comprador getComprador() {
		return comprador;
	}


	public void comprarPieza(List<String> info)  {
		try {
			int pieza =Integer.parseInt(info.get(5));
			String fPago = info.get(4);
			String nT =info.get(0);
			String cT = info.get(1);
			String pasarela = info.get(2);
			String nombre = info.get(3);
			comprador.comprarPieza(pieza,fPago,mundo,nT,cT,pasarela,nombre);
			JOptionPane.showMessageDialog(null, "Se realizo la compra correctamente","compra realizada" , JOptionPane.INFORMATION_MESSAGE);
			ventanaExtra.dispose();
		}catch (MensajedeErrorException err) {
			JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			ventanaExtra.dispose();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar la compra","Error",JOptionPane.ERROR_MESSAGE);
			ventanaExtra.dispose();
		}	
		
	}


	public void ofertaPieza(Administrador admin, String string, String string2, Operador operador, Pieza pieza,
			String string3, String string4, String string5, String string6) throws MensajedeErrorException {
		comprador.hacerOferta(admin, string, string2, operador, pieza, string3, string4, string5, string6);
		
	}

}
