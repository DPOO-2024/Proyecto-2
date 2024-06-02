package Usuarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
		if( pago.getFormaPago().equalsIgnoreCase("Tarjeta") | pago.getFormaPago().equalsIgnoreCase("Efectivo") | pago.getFormaPago().equalsIgnoreCase("Transferencia")) {
			registrarPago(pago);
			respuesta =true;
		}
	

		return respuesta;
	}
	

	
	
	public  void registrarPago(Pago pago) {
		pagos.add(pago);
	}

	public boolean pagoConTarjeta(Comprador comprador, Pago pago) throws Exception {
		String ubicacion = encontrarRuta() + "\\Datos\\"+ "pasarelas.txt";
		try  {
			List<PasarelaPago> pasarelas = lectorPasarelas(ubicacion);
			String nombrePasarela = pago.getinfoTarjeta().get(2);
			Class<?> clase = Class.forName(nombrePasarela);
			
			PasarelaPago pasarela = (PasarelaPago) clase.getDeclaredConstructor().newInstance(); 
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

	public List<PasarelaPago> lectorPasarelas(String archivo) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		List<PasarelaPago> pasarelas = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea;
		while ((linea = br.readLine()) != null) { 
			Class<?> clase = Class.forName(linea);
			
			PasarelaPago pasarela = (PasarelaPago) clase.getDeclaredConstructor().newInstance(); 
			pasarelas.add(pasarela);
		}
		br.close();
		return pasarelas;
	}
	
	
	
	public String encontrarRuta() {
		String ruta = System.getProperty("user.dir");
		return ruta;
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