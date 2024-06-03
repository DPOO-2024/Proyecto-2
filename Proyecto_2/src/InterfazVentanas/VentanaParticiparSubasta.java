package InterfazVentanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Exceptions.MensajedeErrorException;
import Interfaz.InterfazComprador;
import Modelo.Galeria;
import Modelo.Subasta;
import Piezas.Pieza;

public class VentanaParticiparSubasta extends JDialog implements ActionListener, ItemListener {
	
	private JTextField fecha;
	private Galeria principal;
	private Subasta subasta;
	private InterfazComprador intCom;
	private ButtonGroup Piezas;
	private JDialog participar;
	private JComboBox<String> formaPago;
	private String fPago;
	private JDialog infoTarjeta;
	private JTextField numeroT;
	private JTextField codigoT;
	private JTextField nombreT;
	private ButtonGroup tipoPasarela;
	private JTextField valor;
	private List<String> info = null;
	private static final String CERRAR="Cerrar";

	public VentanaParticiparSubasta(InterfazComprador interfazComprador, Galeria gal) {
		this.principal=gal;
		this.intCom=interfazComprador;
		
		
		setTitle("fecha subasta");
		setSize(500, 300);
		setLocationRelativeTo(null); 
		setLayout(new GridLayout(2, 1, 20, 20));
		JPanel info = new JPanel(new GridLayout(1,2));
		JTextArea relleno = new JTextArea("Ingrese la fecha (AAMMDD) de la subasta \n en la que quiere hacer una oferta : ");
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,15));
		relleno.setForeground(new Color(0, 144, 41 ));
		info.add(relleno);
		fecha = new JTextField("");
		fecha.setSize(100, 20);
		fecha.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		info.add(fecha);
		add(info);
		JButton aceptar = new JButton("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setPreferredSize(new Dimension(100,15));
		aceptar.setBackground(new Color(0, 90, 26));
		aceptar.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		add(aceptar);
		

	   
		setVisible(true);
		
	
	}
	
	public void  participarsubasta() {
		participar=new JDialog();
		participar.setSize(700, 800);
		participar.setTitle("Participar Subasta");
		participar.setResizable(false);
		participar.setLocationRelativeTo(null);
		participar.setLayout(new GridLayout(8,1,20,20));
		JButton piezas = new JButton(" Ver Piezas Disponibles en esta subasta");
		piezas.setForeground(Color.WHITE);
		piezas.setPreferredSize(new Dimension(100,30));
		piezas.setBackground(new Color(0, 90, 26));
		piezas.setFont(new Font ("Book Antiqua", Font.BOLD, 15));
		piezas.setActionCommand("Piezas subasta");
		piezas.addActionListener(this);
		participar.add(piezas);
		
		
		JLabel relleno= new JLabel("Escoja el numero de la pieza que desea comprar: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,20));
		relleno.setForeground(new Color(0, 144, 41 ));
		participar.add(relleno);
		int numPiezas = subasta.getInventario().size();
		
		JPanel op = new JPanel(new GridLayout(1,numPiezas));
		
		Piezas = new ButtonGroup();
		
		for(int i = 1; i <= numPiezas; i++) {
			JRadioButton opcion= new JRadioButton( String.valueOf(i));
			opcion.setFont(new Font ("Nirmala UI", Font.PLAIN, 10));
			opcion.setHorizontalAlignment(SwingConstants.CENTER);
			opcion.setForeground(Color.WHITE);
			opcion.setBackground(new Color(0, 144, 41) );
			Piezas.add(opcion);
			op.add(opcion);
			
		}
		
		participar.add(op);
		
		JLabel titulo= new JLabel("Forma de pago (si no selecciona ninguno por defecto sera efectivo): ",JLabel.CENTER);
		titulo.setFont(new Font("Nirmala UI",Font.BOLD,15));
		titulo.setForeground(new Color(0, 144, 41 ));
		participar.add(titulo);
		
		formaPago = new JComboBox<String>();
		formaPago.addItem("Tarjeta");
		formaPago.addItem("Efectivo");
		formaPago.addItem("Transferencia");
		formaPago.setSelectedItem("Efectivo");
		formaPago.addItemListener(this);
		
		participar.add(formaPago);
		
		JPanel oferta = new JPanel(new GridLayout(1,2));
		JTextArea label = new JTextArea("Ingrese el valor con el valor con el que desea \n competir por la pieza recuerde que debe \nser mayor al valor inicial: ");
		label.setFont(new Font("Nirmala UI",Font.BOLD,15));
		label.setForeground(new Color(0, 144, 41 ));
		oferta.add(label);
		valor = new JTextField("");
		valor.setSize(100, 20);
		valor.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		oferta.add(valor);
		
		participar.add(oferta);
		
		JButton continuar = new JButton("Ofertar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("ofertar");
		continuar.addActionListener(this);
		participar.add(continuar);
		
		
		

		
		JButton cerrar = new JButton("Salir");
		cerrar.setFont(new Font ("Book Antiqua", Font.BOLD, 18));
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(220, 0, 0));
		cerrar.setActionCommand(CERRAR);
		cerrar.addActionListener(this);
		participar.add(cerrar);
		participar.setVisible(true);}
	
	

	public String piezaSeleccionado() throws Exception {
		JRadioButton s = seleccionado(Piezas);
		String respuesta="";
		if (s.getText().equals("")){
			JOptionPane.showMessageDialog(null, "seleccione una pieza","Error",JOptionPane.ERROR_MESSAGE);
			throw new Exception();}
		respuesta =  s.getText();
		return respuesta;
	}
	
	public static JRadioButton seleccionado(ButtonGroup group){
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
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("aceptar")) {
			try {
				if (fecha.getText().equals("")){
					throw new MensajedeErrorException("Ingrese una fecha");
				}
				int fechattt = Integer.parseInt(fecha.getText());
				subasta = principal.participarSubasta(fechattt, intCom.getComprador(), 0);
				JOptionPane.showMessageDialog(null, "Bien !! ya te encuentras en la subasta, ahora has tu oferta","participar subasta" , JOptionPane.INFORMATION_MESSAGE);
				participarsubasta();
				this.dispose();
				
			
			}catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				this.dispose();
				
				
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se encontro una Subasta en esa fecha","Error",JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
		else if (comando.equals("Piezas subasta")) {
			ArrayList<Pieza> piezas = (ArrayList<Pieza>) subasta.getInventario();
			VentanaPiezas ventana;
			try {
				ventana = new VentanaPiezas(piezas,"Piezas Disponibles subasta","Elija la pieza de la cual quiera ver información General",principal);
				ventana.setVisible(true);
			} catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}
		
		else if (comando.equals(CERRAR)) {
			int rta = JOptionPane.showConfirmDialog(null, "De verdad quiere salirte de la subasta?",
					"Cerrar sesión", JOptionPane.YES_NO_OPTION);
			if (rta == JOptionPane.OK_OPTION) {
				subasta.quitarComprador(intCom.getComprador());
				participar.dispose();}
		}
		
		else if (comando.equals("ofertar")) {
			
			if(info ==null) {
				info = new ArrayList<>();
				info.add("no");
				info.add("no");
				info.add("no");
				info.add("no"); 
				info.add(fPago);
				try {
					info.add(piezaSeleccionado());
					if (valor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Ingrese un valor","Error",JOptionPane.ERROR_MESSAGE);
						throw new Exception();
					}
					info.add(valor.getText());
					JOptionPane.showMessageDialog(null, "oferta exitosa","Error",JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
				}
			}
			
			else {
				info.add(fPago);
				try {
					info.add(piezaSeleccionado());
					if (valor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Ingrese un valor","Error",JOptionPane.ERROR_MESSAGE);
						throw new Exception();
					}
					info.add(valor.getText());
					JOptionPane.showMessageDialog(null, "oferta exitosa","Error",JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					participar.dispose();
				}
			}
			
			try {
				ofertaPieza(info);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "no se pudo hacer la oferta","Error",JOptionPane.ERROR_MESSAGE);
				participar.dispose();
			} catch (MensajedeErrorException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				participar.dispose();
			}
			
		}
		else if (comando.equals("guardar")) {
			try {
			if (numeroT.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese un numero","Error",JOptionPane.ERROR_MESSAGE);
				throw new Exception();
			}else if (codigoT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Ingrese una codigo","Error",JOptionPane.ERROR_MESSAGE);
				throw new Exception();
			}
			else if (nombreT.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese un nombre","Error",JOptionPane.ERROR_MESSAGE);
				throw new Exception();
			}
			info = new ArrayList<>();
			info.add(numeroT.getText());
			info.add(codigoT.getText());
			info.add(pasarelaSeleccionado());
			info.add(nombreT.getText()); 
			infoTarjeta.dispose();
			
			
			
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "No se pudo guardar la informacion","Error",JOptionPane.ERROR_MESSAGE);
			infoTarjeta.dispose();
			}
		
		}
	}
	
	
	public void ofertaPieza(List<String> info2) throws NumberFormatException, MensajedeErrorException {
		intCom.ofertaPieza(principal.getAdmin(),info2.get(6),info2.get(4),subasta.getOperador(),subasta.getInventario().get(Integer.parseInt(info2.get(5))-1),info2.get(0),info2.get(1),info2.get(2),info2.get(3));
	}

	public String pasarelaSeleccionado() throws Exception {
		JRadioButton s = seleccionado2(tipoPasarela);
		String respuesta =  s.getText();
		return respuesta;
	}
	
	public static JRadioButton seleccionado2(ButtonGroup group){
        for (Enumeration e=group.getElements(); e.hasMoreElements(); )
        {
            JRadioButton b = (JRadioButton)e.nextElement();
            if (b.getModel() == group.getSelection())
            {
                return b;
            }
        }
        return null;}
		
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange() == ItemEvent.SELECTED) {
	        fPago = e.getItem().toString();

	        
	        if (fPago.equals("Tarjeta")) {
	        	infoTarjeta = new JDialog();
	        	infoTarjeta.setTitle("Información Tarjeta");
	        	infoTarjeta.setSize(600, 700);
	        	infoTarjeta.setLocationRelativeTo(null); 
	        	
	        	infoTarjeta.setLayout(new GridLayout(6,1));
	        	
	        	JLabel relleno= new JLabel("Ingrese los datos de la tarjeta: ",JLabel.CENTER);
	    		relleno.setFont(new Font("Nirmala UI",Font.BOLD,20));
	    		relleno.setForeground(new Color(0, 144, 41 ));
	    		infoTarjeta.add(relleno);
	    		
	    		JPanel info = new JPanel(new GridLayout(1,2));
	    		JLabel m1 = new JLabel("Numero de la tarjeta:",JLabel.CENTER);
	    		m1.setFont(new Font("Nirmala UI",Font.BOLD,20));
	    		m1.setForeground(new Color(0, 144, 41));
	    		info.add(m1);
	    		
	    		numeroT = new JTextField("");
	    		numeroT.setSize(100, 20);
	    		numeroT.setFont(new Font("Nirmala UI",Font.PLAIN,20));
	    		info.add(numeroT);
	    		infoTarjeta.add(info);
	        	
	    		JPanel info1 = new JPanel(new GridLayout(1,2));
	    		JLabel m2 = new JLabel("Codigo de seguridad:",JLabel.CENTER);
	    		m2.setFont(new Font("Nirmala UI",Font.BOLD,20));
	    		m2.setForeground(new Color(0, 144, 41));
	    		info1.add(m2);
	    		
	    		codigoT = new JTextField("");
	    		codigoT.setSize(100, 20);
	    		codigoT.setFont(new Font("Nirmala UI",Font.PLAIN,20));
	    		info1.add(codigoT);
	    		
	    		infoTarjeta.add(info1);
	        	
	    		JPanel pasarelas = new JPanel(new GridLayout(1,3));
	    		tipoPasarela = new ButtonGroup();
	    		
	    		JRadioButton op1= new JRadioButton("Paypal",true);
	    		op1.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
	    		op1.setHorizontalAlignment(SwingConstants.CENTER);
	    		op1.setForeground(Color.WHITE);
	    		op1.setBackground(new Color(0, 144, 41) );
	    		tipoPasarela.add(op1);
	    		pasarelas.add(op1);
	    		

	    		JRadioButton op2= new JRadioButton("ApplePay");
	    		op2.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
	    		op2.setForeground(Color.WHITE);
	    		op2.setHorizontalAlignment(SwingConstants.CENTER);
	    		op2.setBackground(new Color(0, 144, 41));
	    		tipoPasarela.add(op2);
	    		pasarelas.add(op2);
	    		
	    		JRadioButton op3= new JRadioButton("PayU");
	    		op3.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
	    		op3.setForeground(Color.WHITE);
	    		op3.setHorizontalAlignment(SwingConstants.CENTER);
	    		op3.setBackground(new Color(0, 144, 41));
	    		tipoPasarela.add(op3);
	    		pasarelas.add(op3);
	    		
	    		infoTarjeta.add(pasarelas);
	    		
	    		
	    		JPanel info2 = new JPanel(new GridLayout(1,2));
	    		JTextArea m3 = new JTextArea("nombre del titular\n(si es usted escriba no):");
	    		m3.setFont(new Font("Nirmala UI",Font.BOLD,20));
	    		m3.setForeground(new Color(0, 144, 41));
	    		info2.add(m3);
	    		
	    		nombreT = new JTextField("");
	    		nombreT.setSize(100, 20);
	    		nombreT.setFont(new Font("Nirmala UI",Font.PLAIN,20));
	    		info2.add(nombreT);
	    		
	    		infoTarjeta.add(info2);
	    		
	    		JButton continuar = new JButton("Guardar Informacion");
	    		continuar.setForeground(Color.WHITE);
	    		continuar.setPreferredSize(new Dimension(100,30));
	    		continuar.setBackground(new Color(0, 90, 26));
	    		continuar.setActionCommand("guardar");
	    		continuar.addActionListener(this);
	        	infoTarjeta.add(continuar);
	        	
	        	infoTarjeta.setVisible(true);
	        }
	        
		}
		
	}
	
	

}
