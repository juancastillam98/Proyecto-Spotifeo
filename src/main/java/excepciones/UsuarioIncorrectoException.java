package excepciones;
/**
 * Excepción que se lanza cuando el usuario introducido, no existe en la base de datos
 * @author ASUS
 *
 */
public class UsuarioIncorrectoException extends Exception{
	public UsuarioIncorrectoException(String msg) {
		super(msg);
	}
}
