package Pasarelas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Modelo.Pago;
import Usuarios.Comprador;

public class ApplePay extends PasarelaPago {
	
	private String archivoTransacciones = "ApplePay.log";
	public final static String APPLEPAY= "ApplePay";

	@Override
	public boolean procesarPago(Comprador comprador, Pago pago) throws Exception {
	    // Quitar comas de descripciones
	    String ubicacion = encontrarRuta() + "\\Datos\\" + this.archivoTransacciones;
	    File archivoLog = new File(ubicacion);
	    String info = "";

	    try (FileWriter writer = new FileWriter(archivoLog, true)) { 
	        if (pago.getinfoTarjeta().get(3).equals("no")) {
	            info = pago.getinfoTarjeta().get(0) + "," + pago.getinfoTarjeta().get(1) + "," + pago.getinfoTarjeta().get(2) + "," + comprador.getNombre() + ", exitosa";
	        } else {
	            info = pago.getinfoTarjeta().get(0) + "," + pago.getinfoTarjeta().get(1) + "," + pago.getinfoTarjeta().get(2) + "," + comprador.getNombre() + ", exitosa";
	        }
	        writer.write(info + System.lineSeparator()); 
	    } catch (IOException e) {
	        throw e;
	    }
		return true;

	}
	
	public String encontrarRuta() {
		String ruta = System.getProperty("user.dir");
		return ruta;
	}

	public static String getApplepay() {
		return APPLEPAY;
	}

}
