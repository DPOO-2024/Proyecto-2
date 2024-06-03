package Interfaz;

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

import Usuarios.Comprador;

public class VentanaComprar extends JPanel implements ActionListener, ItemListener {
	private InterfazComprador principal;
	private ButtonGroup Piezas;
	private JComboBox<String> formaPago;
	private JTextField numeroT;
	private JTextField codigoT;
	private JTextField nombreT;
	private ButtonGroup tipoPasarela;
	private List<String> info = null;
	private String fPago;
	private JDialog infoTarjeta;
	
	public VentanaComprar(InterfazComprador interfazComprador) {
		this.principal=interfazComprador;
		
		setLayout(new GridLayout(5,1));
		JLabel relleno= new JLabel("Escoja el numero de la pieza que desea comprar: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,20));
		relleno.setForeground(new Color(0, 144, 41 ));
		add(relleno);
		int numPiezas = principal.cantidadPiezasDisponibles();
		
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
		
		add(op);
		
		JLabel titulo= new JLabel("Forma de pago (si no selecciona ninguno por defecto sera efectivo): ",JLabel.CENTER);
		titulo.setFont(new Font("Nirmala UI",Font.BOLD,15));
		titulo.setForeground(new Color(0, 144, 41 ));
		add(titulo);
		
		formaPago = new JComboBox<String>();
		formaPago.addItem("Tarjeta");
		formaPago.addItem("Efectivo");
		formaPago.addItem("Transferencia");
		formaPago.setSelectedItem("Efectivo");
		formaPago.addItemListener(this);
		
		add(formaPago);
		
		JButton continuar = new JButton("comprar");
		continuar.setForeground(Color.WHITE);
		continuar.setPreferredSize(new Dimension(100,30));
		continuar.setBackground(new Color(0, 90, 26));
		continuar.setActionCommand("comprar");
		continuar.addActionListener(this);
		add(continuar);
		
		
		
		
		
	}
	
	
	public String piezaSeleccionado(int numPiezas) throws Exception {
		JRadioButton s = seleccionado(Piezas);
		String respuesta="";
		String dif =  s.getText();
		if (s.getText().equals("")){
			JOptionPane.showMessageDialog(null, "seleccione una pieza","Error",JOptionPane.ERROR_MESSAGE);
			throw new Exception();}
		for(int i = 1; i <= numPiezas; i++) {
		if (Integer.parseInt(dif)==i) {
			respuesta=dif;
		}
		}
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
		
		if (comando.equals("comprar")) {
			
			if(info ==null) {
				info = new ArrayList<>();
				info.add("no");
				info.add("no");
				info.add("no");
				info.add("no"); 
				info.add(fPago);
				try {
					info.add(piezaSeleccionado(principal.cantidadPiezasDisponibles()));
				} catch (Exception e1) {
				}
			}
			
			else {
				info.add(fPago);
				try {
					info.add(piezaSeleccionado(principal.cantidadPiezasDisponibles()));
				} catch (Exception e1) {
				}
			}
			
			principal.comprarPieza(info);
			
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
			
			
			
		}catch(Exception e1) {}
		}
		
	}

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
        return null;
}
	
	
	
	
	

}
