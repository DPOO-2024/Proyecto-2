package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

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

public class VentanasInfoSubasta extends JDialog implements ActionListener{
	private JPanel ventana;
	private String[] datos;
	private String tipoPanel;
	private JTextField fecha;
	private ButtonGroup tipoOperador;
	private JRadioButton opY;
	private JRadioButton opN;
	private InterfazAdministrador interfaz;

	
	public VentanasInfoSubasta(String titulo,String t,String boton,InterfazAdministrador i) {
		tipoPanel=t;
		interfaz=i;

		setTitle(titulo);
		setSize(500, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		if (tipoPanel.equals("Crear Subasta")) {
			ventana = crearSubasta();
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
	
	//Crear panel para crear subastas
	public JPanel crearSubasta() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1));
		
		JLabel relleno= new JLabel("Crear una Subasta: ",JLabel.CENTER);
		relleno.setFont(new Font("Nirmala UI",Font.BOLD,30));
		relleno.setForeground(new Color(0, 144, 41 ));
		p.add(relleno);
		
		
		JPanel mensaje = new JPanel(new GridLayout(1,2,20,50));
		JTextArea m1 = new JTextArea("Ingrese la fecha (AAMMDD) en la que desea realizar la \nsubasta :");
		m1.setLineWrap(true);
		m1.setEditable(false);
		m1.setWrapStyleWord(true);
		m1.setOpaque(false);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m1.setForeground(new Color(0, 144, 41));
		mensaje.add(m1);
		
		fecha = new JTextField("");
		fecha.setSize(100, 20);
		fecha.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		mensaje.add(fecha);
		
		p.add(mensaje);
		
		JPanel op = new JPanel(new GridLayout(1,2));
		
		tipoOperador = new ButtonGroup();
		
		//Boton Operador ya registrado
		opY= new JRadioButton("Operador ya seleccionado",true);
		opY.setFont(new Font ("Nirmala UI", Font.PLAIN, 15));
		opY.setHorizontalAlignment(SwingConstants.CENTER);
		opY.setForeground(Color.WHITE);
		opY.setBackground(new Color(0, 144, 41) );
		tipoOperador.add(opY);
		op.add(opY);
		
		//Boton nuevo operador
		opN= new JRadioButton("Nuevo operador");
		opN.setFont(new Font ("Nirmala UI", Font.PLAIN, 20));
		opN.setForeground(Color.WHITE);
		opN.setHorizontalAlignment(SwingConstants.CENTER);
		opN.setBackground(new Color(0, 144, 41));
		tipoOperador.add(opN);
		op.add(opN);
		
		p.add(op);
		
		JLabel relleno2= new JLabel(" ");
		p.add(relleno2);	
		return p;
	}
	
	//Recoger informacion ingresada dependiendo la opcion
	public String[] recogerInfo() throws Exception {
		if (tipoPanel.equals("Crear Subasta")) {
			if (fecha.getText().equals("") && (fecha.getText().length()!=6)){
				throw new Exception();
			}
			String[] resp = {fecha.getText(),Integer.toString(operadorSeleccionado())};
			return resp;
		}
		return null;
	
	}
	
	//LLamar a funciones para verificar
	public void llamarFuncion() {
		if (tipoPanel.equals("Crear Subasta")) {
			interfaz.crearSubasta(this);
		}
	}
	
	//Ver que operador esta seleccionado
	public int operadorSeleccionado() {
		JRadioButton s = seleccionado(tipoOperador);
		int respuesta=0;
		String dif =  s.getText();
		if (dif.equals("Operador ya seleccionado")) {
			respuesta=1;
		}else if (dif.equals("Nuevo operador")) {
			respuesta=2;
		}
		return respuesta;
	}
	
	public String[] recogerDatos() {
		return this.datos;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		 if (comando.equals("Continuar")) {
				try {
					datos = recogerInfo();
					this.dispose();	
					llamarFuncion();
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Ingrese una fecha valida","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
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
}
