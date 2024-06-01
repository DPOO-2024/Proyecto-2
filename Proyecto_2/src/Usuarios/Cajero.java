package Usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Pago;
import Pasarelas.PasarelaPago;
import Piezas.Pieza;

public class Cajero extends Empleado{
	public final static String CAJERO= "Cajero";
	
	private  List<Pago> pagos;

	private PasarelaPago miPasarela;
	
	public Cajero(String nombreUsuario, String contraseña, String rol) throws MensajedeErrorException {
		super(nombreUsuario, contraseña, rol);
		this.pagos = new ArrayList<Pago>();
	}



	
	// como se cuando se registra un pago correctamente ???
	public boolean generarPagoCajero(int precio,Pieza pieza,Pago pago, Comprador comprador) throws Exception {
		boolean respuesta =false;
		if( pago.getFormaPago().equalsIgnoreCase("Efectivo") | pago.getFormaPago().equalsIgnoreCase("Transferencia")) {
			registrarPago(pago);
			respuesta =true;
		}
		else if (pago.getFormaPago().equalsIgnoreCase("Tarjeta")){
			try {
				respuesta = pagoConTarjeta(comprador, pago);
			} catch (ClassNotFoundException e) {
				throw e;
			}
		}
		else {
		return respuesta;
		}
		

		return respuesta;
	}
	

	
	
	public  void registrarPago(Pago pago) {
		pagos.add(pago);
	}

	public boolean pagoConTarjeta(Comprador comprador,Pago pago) throws Exception {
		try {
			String nombrePasarela = pago.getinfoTarjeta().get(2);
			Class<?> clase = Class.forName(nombrePasarela);
			
			miPasarela = (PasarelaPago) clase.getDeclaredConstructor(null).newInstance(null);
			
			boolean verificado =miPasarela.procesarPago(comprador,pago);
			
			return verificado;
		} catch (ClassNotFoundException e) {
			throw e;
		}
		catch (IOException e)
		{
		throw e;
		}
		
		}

	public List<Pago> getPagos() {
		return pagos;
	}



	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}



	public static String getCajero() {
		return CAJERO;
	}



	
	
}