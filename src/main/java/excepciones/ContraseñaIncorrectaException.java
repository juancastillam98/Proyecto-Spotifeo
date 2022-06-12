package excepciones;
/**
 * Excepción que se lanzan cuando la contraseña es incorrecta 
 * @author Juan
 */
public class ContraseñaIncorrectaException extends Exception{
	public ContraseñaIncorrectaException(String msg) {
		super(msg);
	}
}
