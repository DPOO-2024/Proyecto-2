package Exceptions;

/**
 * Esta clase se usa pa enviar mensjaes de error, en donde puede variar el contexto.
 */
@SuppressWarnings("serial")
public class MensajedeErrorException extends Exception
{
	private String m;

    public MensajedeErrorException( String mensaje )
    {
        super( mensaje );
        m=mensaje;
    }
    
    public String getMensaje(){
    	return this.m;
    }
    

}
