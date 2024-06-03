package Usuarios;

import java.util.ArrayList;

import Piezas.Pieza;

public class Propietario extends Usuario{
	
	public final static String PROPIETARIO="Propietario";
	
	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	private ArrayList<Pieza> estadoPiezas;
	
	private ArrayList<Pieza> historialPiezas;
	
	public Propietario(String login, String password , String nombre, String correo, int telefono) {
		super(login, password, "Propietario");
		this.nombre = nombre;
		this.correo = correo;
		this.telefono=telefono;
		this.estadoPiezas = new ArrayList<Pieza>();
		this.historialPiezas = new ArrayList<Pieza>();
	}
	
	public void ingresarPieza(Pieza pieza) {
		estadoPiezas.add(pieza);
	}
	public void venderPieza(Pieza pieza) { 
		estadoPiezas.remove(pieza);
		historialPiezas.add(pieza);  
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Pieza> getEstadoPiezas() {
		return estadoPiezas;
	}

	public void setEstadoPiezas(ArrayList<Pieza> estadoPiezas) {
		this.estadoPiezas = estadoPiezas;
	}

	public ArrayList<Pieza> getHistorialPiezas() {
		return historialPiezas;
	}

	public void setHistorialPiezas(ArrayList<Pieza> historialPiezas) {
		this.historialPiezas = historialPiezas;
	}

	public static String getPropietario() {
		return PROPIETARIO;
	}
	
	

}