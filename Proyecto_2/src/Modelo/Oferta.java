package Modelo;


import Exceptions.MensajedeErrorException;
import Usuarios.Comprador;

public class Oferta {
	
	
	private int valorOferta;
	
	private Comprador comprador;
	
	private Pago pago;
	
	public Oferta(int valorOferta, Comprador comprador, Pago pago) {
		this.comprador=comprador;
		this.valorOferta=valorOferta;
		this.pago = pago;
		
	}

	public int getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(int valorOferta) {
		this.valorOferta = valorOferta;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	public static Oferta generarOferta(int valorOferta, Comprador comprador,Pago pago) throws MensajedeErrorException {
		if(pago.getFormaPago().equalsIgnoreCase("Tarjeta") | pago.getFormaPago().equalsIgnoreCase("Efectivo") | pago.getFormaPago().equalsIgnoreCase("Transferencia")) {
		Oferta oferta = new Oferta(valorOferta,comprador, pago);
		return oferta;}
		else {
			throw new MensajedeErrorException("No es una forma de pago valida");
		}
		
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	
	
	
	
	
	

}

