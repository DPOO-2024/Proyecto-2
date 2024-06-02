package Pasarelas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Modelo.Inventario;
import Modelo.Pago;
import Persistencia.guardarPiezas;
import Piezas.Pieza;
import Usuarios.Comprador;

public class Paypal extends PasarelaPago {
	
	private String archivoTransacciones = "Paypal.txt";
	public final static String PAYPAL= "Paypal";

	@Override
	public boolean procesarPago(Comprador comprador, Pago pago) throws Exception {
		//Quitar comas de descripciones
				String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoTransacciones;
				File archivof = new File(ubicacion);
				String info="";
				try (FileWriter writer = new FileWriter(archivof)) {
					if(pago.getinfoTarjeta().get(3).equals("no")) {
					 info = pago.getinfoTarjeta().get(0)+","+pago.getinfoTarjeta().get(1) +","+comprador.getNombre()+","+pago.getFecha()+", exitosa";}
					
					else {
						 info =pago.getinfoTarjeta().get(0)+","+pago.getinfoTarjeta().get(1) +","+ pago.getinfoTarjeta().get(3)+","+pago.getFecha()+", exitosa";
					}
					writer.write(info);
					writer.close();
		        } catch (IOException e) {
		           throw e;
		        }
		return true;
	}

	public String encontrarRuta() {
		String ruta = System.getProperty("user.dir");
		return ruta;
	}

	public static String getPaypal() {
		return PAYPAL;
	}
	

}
