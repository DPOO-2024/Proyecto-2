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

	@Override
	public boolean procesarPago(Comprador comprador, Pago pago) throws Exception {
		//Quitar comas de descripciones
				String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoTransacciones;
				File archivof = new File(ubicacion);
				
				try (FileWriter writer = new FileWriter(archivof)) {
					String info = pago.getinfoTarjeta().get(0)+","+pago.getinfoTarjeta().get(1) +","+ pago.getinfoTarjeta().get(2)+","+pago.getinfoTarjeta().get(3);
					writer.write(info);
					writer.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		return false;
	}

	public String encontrarRuta() {
		String ruta = System.getProperty("user.dir");
		return ruta;
	}

}
