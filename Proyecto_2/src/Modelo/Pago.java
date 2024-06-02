package Modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Piezas.Pieza;
import Usuarios.Comprador;

public class Pago {
	/* registra el pago realizado al vender una pieza, 
	 considerando el valor de la pieza y la persona que lo va a comprar*/
	//M
	private int monto;
	
	private Pieza pieza;
	
	private Comprador comprador;
	
	private String formaPago;
	
	private List<String> infoTarjeta;
	
	private String fecha;
	
	public Pago(int monto, Pieza pieza, String formaPago, Comprador comprador, String numTarjeta, String codSeguridad, String pasarela, String nombre, String fecha) {
        this.monto = monto;
        this.pieza = pieza;
        this.comprador = comprador;
        this.formaPago = formaPago;
        this.infoTarjeta = new ArrayList<>();
        infoTarjeta.add(numTarjeta);
        infoTarjeta.add(codSeguridad);
        infoTarjeta.add(pasarela);
        infoTarjeta.add(nombre);
        this.fecha = fecha;
        
	}
	
	public String getFecha() {
		return fecha;
	}
	public int getMonto() {
		return monto;
	}
	
	public List<String> getinfoTarjeta() {
		return infoTarjeta;
	}


	public void setMonto(int monto) {
		this.monto = monto;
	}


	public Pieza getPieza() {
		return pieza;
	}


	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}


	public Comprador getComprador() {
		return comprador;
	}


	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}


	public String getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}


	public static Pago generarPago(int monto, Pieza pieza,String formaPago ,Comprador comprador,String numTarjeta, String codSeguridad, String pasarela, String nombre2) {
		Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyMMdd");
        String fecha = formato.format(fechaActual);
		Pago pago = new Pago(monto, pieza, formaPago, comprador, numTarjeta, codSeguridad, pasarela, nombre2,fecha);
		return pago;
		
	}
	
	
	

}