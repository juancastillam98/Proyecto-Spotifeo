package excepciones;
/**
 * Excepción que se lanza si el email introducido es incorrecto
 * @author Juan Castilla
 *
 */
public class EmailInvalidoException extends Exception{
	public EmailInvalidoException(String msg) {
		super(msg);
	}
}
