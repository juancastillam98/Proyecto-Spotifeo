package excepciones;
/**
 * Excepción que se lanza cuando el campo email está vacío
 * @author Juan Castilla 
 */
public class NombreInvalidoException extends Exception{
	public NombreInvalidoException(String msg) {
		super(msg);
	}
}
