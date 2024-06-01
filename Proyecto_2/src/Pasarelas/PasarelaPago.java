package Pasarelas;

import Modelo.Pago;
import Usuarios.Comprador;

public abstract class PasarelaPago {
	
	public abstract boolean procesarPago(Comprador comprador,Pago pago) throws Exception;

}
