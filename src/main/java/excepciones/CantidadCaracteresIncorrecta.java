package excepciones;
/**
 * Excepci�n que se lanza si la cantidad de caracteres de la contrase�a es inferior a 4
 * @author Juan Castilla
 *
 */
public class CantidadCaracteresIncorrecta extends Exception{
	public CantidadCaracteresIncorrecta(String msg){
		super(msg);
	}
}
