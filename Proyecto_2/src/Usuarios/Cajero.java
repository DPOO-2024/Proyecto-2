package Usuarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
	
	public  TreeMap<String,Integer > generarReportePagos(){
		
		
		
		 TreeMap<String, Integer> cantidad = new TreeMap<String, Integer>();
		 
		 cantidad.put("Enero",Integer.valueOf(0));
		 cantidad.put("Febrero",Integer.valueOf(0));
		 cantidad.put("Marzo",Integer.valueOf(0));
		 cantidad.put("Abril",Integer.valueOf(0));
		 cantidad.put("Mayo",Integer.valueOf(0));
		 cantidad.put("Junio",Integer.valueOf(0));
		 cantidad.put("Julio",Integer.valueOf(0));
		 cantidad.put("Agosto",Integer.valueOf(0));
		 cantidad.put("Septiembre",Integer.valueOf(0));
		 cantidad.put("Octubre",Integer.valueOf(0));
		 cantidad.put("Noviembre",Integer.valueOf(0));
		 cantidad.put("Diciembre",Integer.valueOf(0));
		 
		 
		 for(Pago p:pagos) {
			 String mes = p.getFecha().substring(2, 4); 
			 int precio = p.getMonto();
			 
			 String mesT="";
			 if (mes.equals("01")) {
				 mesT="Enero";
			 }else if (mes.equals("02")) {
				 mesT="Febrero";
			 }else if (mes.equals("03")) {
				 mesT="Marzo";
			 }else if (mes.equals("04")) {
				 mesT="Abril";
			 }else if (mes.equals("05")) {
				 mesT="Mayo";
			 }else if (mes.equals("06")) {
				 mesT="Junio";
			 }else if (mes.equals("07")) {
				 mesT="Julio";
			 }else if (mes.equals("08")) {
				 mesT="Agosto";
			 }else if (mes.equals("09")) {
				 mesT="Septiembre";
			 }else if (mes.equals("10")) {
				 mesT="Octubre";
			 }else if (mes.equals("11")) {
				 mesT="Noviembre";
			 }else if (mes.equals("12")) {
				 mesT="Diciembre";
			 }
			 
			 Integer val=cantidad.get(mesT);
			 cantidad.put(mesT, val+precio);
		 } 
			 
		 TreeMap<Integer, String> invertido = new TreeMap<>(ordenarDescendente);
	            
		 
		 for (Map.Entry<String,Integer> entry : cantidad.entrySet()) {
			 invertido.put(entry.getValue(), entry.getKey());
		 }
		 
		 int i=0;
		 for (Entry<Integer, String> entry : invertido.entrySet()) {
			 if (i<2) {
				 cantidad.put(entry.getValue(), Integer.valueOf(10));
			 }else if (i<4) {
				 cantidad.put(entry.getValue(), Integer.valueOf(8));
			 }else if (i<6) {
				 cantidad.put(entry.getValue(), Integer.valueOf(6));
			 }else if (i<8) {
				 cantidad.put(entry.getValue(), Integer.valueOf(4));
			 }else if (i<10) {
				 cantidad.put(entry.getValue(), Integer.valueOf(2));
			 }else if (i<12) {
				 cantidad.put(entry.getValue(), Integer.valueOf(0));
			 } 
			 i++;
		 }
		 
		 
		 return cantidad;
	}
	
	public static Comparator<Integer> ordenarDescendente = new Comparator<Integer>(){
	    @Override
	    public int compare(Integer o1, Integer o2) {
	    	return o2.compareTo(o1);
        }
	};

	
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