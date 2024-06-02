package Usuarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Consola.ConsolaInicial;
import Exceptions.MensajedeErrorException;
import Exceptions.PagoRechazado;
import Modelo.Administrador;
import Modelo.Galeria;
import Modelo.Pago;
import Piezas.Pieza;

public class Comprador extends Usuario{
	
	public static final String COMPRADOR ="Comprador";
	
	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	private int comprasTotales;
	
	private int comprasMaximas;
	
	private ArrayList<String> historialCompras; //nombrePieza,fecha
	
	public Comprador(String login, String password,String nombre, String correo,int telefono,int comprasTotales, int comprasMaximas) {
		super(login, password, "Comprador");
		this.telefono=telefono;
		this.nombre = nombre;
		this.correo=correo;
		this.comprasTotales=comprasTotales;
		this.comprasMaximas=comprasMaximas;
		this.historialCompras=new ArrayList<String>();
	}
	
	
	
    public void comprarPieza(int idx,String formapago, Galeria gal,String numTarjeta,String codSeguridad, String pasarela, String nombre)throws Exception {
		ArrayList<Pieza> piezasDisponibles= gal.getInventario().getPiezasDisponibles();
		Pieza pieza =piezasDisponibles.get(idx-1);

    	try {
			if (pieza.getValorFijo()!=0) {
				gal.getInventario().reservarPieza(pieza);
			}
			else {
				throw new MensajedeErrorException("La pieza solo se puede vender en una subasta");
			}
			if(!pieza.isVendido()) {

    		if (!pieza.equals(null)) {
    			boolean confirmado = gal.getAdmin().confirmarVenta(pieza,this);
	            if ( confirmado){
	            	Pago pago =null;
	    			pago = Pago.generarPago(pieza.getValorFijo(), pieza, formapago, this, numTarjeta,codSeguridad, pasarela, nombre);
	            	if (gal.getCajero().generarPagoCajero(pieza.getValorFijo(),pieza,pago,this)) {
	            		gal.getInventario().moverPieza(pieza);
	            		this.agregarCompra(pieza.getValorFijo());
	            		this.agregarPiezaCompra(pieza.getTitulo(),pieza.getValorFijo());
	            		Propietario pro =(Propietario) pieza.getPropietario();
	            		pro.venderPieza(pieza);
	            		pieza.setVendido(true);
	            	}
	            	else {
	            		gal.getInventario().agregarPieza(pieza);
	            		throw new PagoRechazado();
	            	
	            	}
	            }
	            else {
	            	gal.getInventario().agregarPieza(pieza);
	            	throw new MensajedeErrorException("Superaste el numero de compras maximas contactate con el administrador");
	            }
        
    	
    		}	
    		else {
    			throw new MensajedeErrorException("La pieza no se encuentra disponible");
    		}
    		
			}
			else {
				throw new MensajedeErrorException("La pieza ya fue vendida");
			}
    	}
    	catch(MensajedeErrorException e) {
    		throw e;
    	}catch (ClassNotFoundException e) {
			throw e;
		} 
    	catch (PagoRechazado e) {
			throw e;
		}
    	catch (Exception e) {
			throw e;
		}
    	
    }
	
	
	
	public void agregarCompra(int precio)
	{
		this.comprasTotales+=precio;
	}
	
	public void agregarPiezaCompra( String tituloP,int precio)
	{
		this.historialCompras.add(tituloP);
		Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyMMdd");
        String fecha = formato.format(fechaActual);
		this.historialCompras.add(fecha);
		String precioS= String.valueOf(precio);	
		this.historialCompras.add(precioS);
	}
	
	public void agregarPiezaCompraPersistencia( String tituloP, String fecha,String precio)
	{
		this.historialCompras.add(tituloP);
		this.historialCompras.add(fecha);
		this.historialCompras.add(precio);
	}
	
	
	public void hacerOferta(Administrador admin, String oferta, String formaPago, Operador operador, Pieza pieza, String numTarjeta, String codSeguridad, String pasarela, String nombre2) throws MensajedeErrorException {
		int valor=Integer.parseInt(oferta);
		int valorI = pieza.getValorInicial();
		List<Integer> valores = operador.listaValoresOferta(pieza);
		if (!(valores.contains(valor)) && valor>operador.mayorOferta(pieza) && valor>=valorI) {
			
			Pago pago =null;
			pago = Pago.generarPago(valorI, pieza, formaPago, this, numTarjeta,codSeguridad, pasarela, nombre2);
			operador.crearOferta(valor, this, pieza, admin,pago);
	}
		else {
			throw new MensajedeErrorException("Aumenta tu oferta");
		}
		
		
	}
	
	public static String mostrarHistorialCliente(Comprador c,Galeria gal) {
		try {
    		List<List<String>> compras = gal.getAdmin().historialComprador(c.getLogin());
    		String info="";
    		info=("\nHISTORIAL DEL COMPRADOR: ");
    		info+="\n"+("Piezas que ha comprado: ");
    		int i =0;
    		boolean terminar = false;
    		if(compras.get(0).size()==1) {
    			info+="\n"+("Por el momento no ha realizado ninguna compra");
    		}
    		while(i<compras.get(0).size()) {
    			if(i==compras.get(0).size()-1) {
    				if(compras.size()==1 &&compras.get(0).size()==1) {
    					info+="\n"+("El valor de su coleccion es 0");
    				}
    				else {
    				info+="\n"+("El valor de su coleccion es "+compras.get(0).get(i));
    				}
    				terminar=true;
    			}
    			if (!terminar) {
    		info+="\n"+("- " + compras.get(0).get(i));
			info+="\n"+("Fue comprada en "+compras.get(0).get(i+1));
			info+="\n"+("Por un valor de "+compras.get(0).get(i+2));}
			i=i+3;
    		 }
			if(compras.size()!=1) {
				info+="\n"+("Las piezas de las que es dueño son : ");
				for(String pieza:compras.get(1)) {
					info+="\n- "+(pieza);
				}
			}
			return info;
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
		return null;
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

	public int getComprasTotales() {
		return comprasTotales;
	}

	public void setComprasTotales(int comprasTotales) {
		this.comprasTotales = comprasTotales;
	}

	public int getComprasMaximas() {
		return comprasMaximas;
	}
	
	public void setHistorialCompras(ArrayList<String> piezas) {
		this.historialCompras = piezas;
	}

	public ArrayList<String> getHistorialCompras() {
		return this.historialCompras;
	}

	public void setComprasMaximas(int comprasMaximas) {
		this.comprasMaximas += comprasMaximas;
	}

	public static String getComprador() {
		return COMPRADOR;
	}



	



	


}