package excepciones;
/**
 * Excepci�n que se lanza cuando el campo email est� vac�o
 * @author Juan Castilla 
 */
public class NombreInvalidoException extends Exception{
	public NombreInvalidoException(String msg) {
		super(msg);
	}
}
