package excepciones;

public class UsuarioYaExiste extends Exception{
	public UsuarioYaExiste(String msg) {
		super(msg);
	}
}
