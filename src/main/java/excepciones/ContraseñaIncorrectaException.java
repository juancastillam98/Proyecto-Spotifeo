package excepciones;
/**
 * Excepci�n que se lanzan cuando la contrase�a es incorrecta 
 * @author Juan
 */
public class Contrase�aIncorrectaException extends Exception{
	public Contrase�aIncorrectaException(String msg) {
		super(msg);
	}
}
