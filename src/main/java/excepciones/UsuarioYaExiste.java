package excepciones;

public class UsuarioYaExiste extends Exception{
	/**
	 * Excepcion que se lanzada si ya existe el usuario en la bd
	 * @param msg mensaje de excepcion
	 */
	public UsuarioYaExiste(String msg) {
		super(msg);
	}
}
